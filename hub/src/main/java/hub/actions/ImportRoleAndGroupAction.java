package hub.actions;

import com.google.common.collect.Maps;
import hub.queries.users.UserUtils;
import hub.types.persistent.Person;
import hub.types.persistent.Role;
import org.jooq.*;
import org.jooq.impl.DefaultDSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import projectdb.Tables;
import things.control.ThingAction;
import things.exceptions.ThingException;
import things.exceptions.ThingRuntimeException;
import things.exceptions.ValueException;
import things.thing.Thing;
import things.thing.ThingControl;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author: Markus Binsteiner
 */
public class ImportRoleAndGroupAction implements ThingAction {

    private static final Logger myLogger = LoggerFactory.getLogger(ImportRoleAndGroupAction.class);

    public static final String PROJECT_DB_KEY = "projectdb";
    public static final String PROJECT_DB_ADMIN_VALUE = "admin";
    public static final String PROJECT_DB_ADVISER_VALUE = "adviser";

    private Map<String, Thing<Role>> roles = Maps.newConcurrentMap();

    @Autowired
    private ThingControl tc;

    @Autowired
    private UserUtils userUtils;

    @Resource(name = "projectDbContext")
    private DefaultDSLContext jooq;

    private Map<Integer, String> projectMap = null;
    private Map<Integer, String> roleMap = null;
    
    @Override
    public String execute(String s, List<Thing> things, Map<String, String> stringStringMap) {
        
        Stream<Thing<Person>> persons = userUtils.getPersonStream(things);

        persons.forEach(p -> checkProjectDb(p));
        return null;
    }

    private void checkProjectDb(Thing person) {
        checkResearcherRole(person);
        checkAdviserAndAdminRole(person);
    }

    public synchronized Map<Integer, String> getProjectMap() {
        if ( projectMap == null ) {
            SelectJoinStep<Record2<Integer, String>> condition = jooq.select(Tables.PROJECT.ID, Tables.PROJECT.PROJECTCODE)
                    .from(Tables.PROJECT);
            projectMap = Maps.newHashMap();
            Result<Record2<Integer, String>> result = condition.fetch();
            result.forEach(rec -> projectMap.put(rec.getValue(Tables.PROJECT.ID), rec.getValue(Tables.PROJECT.PROJECTCODE)));
        }
        return projectMap;
    }

    public synchronized Map<Integer, String> getRoleMap() {
        if ( roleMap == null ) {
            SelectJoinStep<Record> condition = jooq.select().from(Tables.RESEARCHERROLE);
            roleMap = Maps.newHashMap();
            Result<Record> result = condition.fetch();
            result.forEach(rec -> roleMap.put(rec.getValue(Tables.RESEARCHERROLE.ID), rec.getValue(Tables.RESEARCHERROLE.NAME)));
        }
        return roleMap;
    }
    
    private Thing<Role> getRole(String key, String rolename) throws ValueException, ThingException {
        
        if ( roles.get(key+"_"+rolename) == null ) {
            Role temp = new Role(rolename);
            Optional<Thing> thing = tc.findUniqueThingByKeyAndValue(key, temp);
            if ( ! thing.isPresent() ) {
                Thing t = tc.createThing(key, temp);
                roles.put(key+"_"+rolename, t);
            } else {
                roles.put(key+"_"+rolename, thing.get());
            }
        }

        return roles.get(key+"_"+rolename);
    }

    private void checkResearcherRole(Thing<Person> person) {

        try {
            Person p = tc.getValue(person);

            SelectConditionStep<Record2<Integer, String>> condition = null;

                condition = jooq.select(Tables.RESEARCHER.ID, Tables.RESEARCHER.FULLNAME)
                        .from(Tables.RESEARCHER)
                        .where(Tables.RESEARCHER.FULLNAME.contains(p.getFirst_name()))
                        .and(Tables.RESEARCHER.FULLNAME.contains(p.getLast_name()));
            if (! StringUtils.isEmpty(p.getMiddle_names())) {
                condition = condition.and(Tables.RESEARCHER.FULLNAME.contains(p.getMiddle_names()));
            }

            Result<Record2<Integer, String>> result = condition.fetch();

            if ( result == null || result.size() == 0) {
                // means no researcher
                myLogger.debug("Could not find researcher role for: "+p.nameToString());
                System.out.println("Could not find researcher role for: "+p.nameToString());
                return;
            } else if ( result.size() > 1 ) {
                myLogger.debug("Found multiple results for person: "+p.nameToString());
                System.out.println("Found multiple results for person: "+p.nameToString());
                return;
            }

            Integer id = result.get(0).getValue(Tables.RESEARCHER.ID);

            SelectConditionStep<Record2<Integer, Integer>> condition2 = jooq.select(Tables.RESEARCHER_PROJECT.PROJECTID, Tables.RESEARCHER_PROJECT.RESEARCHERROLEID)
                    .from(Tables.RESEARCHER_PROJECT)
                    .where(Tables.RESEARCHER_PROJECT.RESEARCHERID.equal(id));

            Result<Record2<Integer, Integer>> projectsForResearcher = condition2.fetch();

            if ( projectsForResearcher.size() == 0 ) {
                System.out.println("No projects found for "+p.nameToString());
            } else {
                System.out.println(projectsForResearcher.size()+" projects found for "+p.nameToString());
            }

            for ( Record2<Integer, Integer> rec : projectsForResearcher ) {

                Integer projectId = rec.getValue(Tables.RESEARCHER_PROJECT.PROJECTID);
                Integer roleId = rec.getValue(Tables.RESEARCHER_PROJECT.RESEARCHERROLEID);

                String projectName = getProjectMap().get(projectId);
                if ( StringUtils.isEmpty(projectName)) {
                    throw new ThingRuntimeException("Can't find project for id: "+projectId);
                }

                String roleName = getRoleMap().get(roleId);
                if (StringUtils.isEmpty(roleName)) {
                    throw new ThingRuntimeException("Can't find role for id: "+roleId);
                }

                Thing<Role> role = getRole(projectName, roleName);
                tc.addThingToThing(person, role);

            }

        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }

    }


    private void checkAdviserAndAdminRole(Thing<Person> person)  {

        try {
            Person p = tc.getValue(person);

            SelectConditionStep<Record3<String, Integer, Byte>> condition = null;

                condition = jooq.select(Tables.ADVISER.FULLNAME, Tables.ADVISER.ID, Tables.ADVISER.ISADMIN).from(Tables.ADVISER)
                        .where(Tables.ADVISER.FULLNAME.contains(p.getFirst_name()))
                        .and(Tables.ADVISER.FULLNAME.contains(p.getLast_name()));
            if (!StringUtils.isEmpty(p.getMiddle_names())) {
                condition = condition.and(Tables.ADVISER.FULLNAME.contains(p.getMiddle_names()));

            }

            Result<Record3<String, Integer, Byte>> result = condition.fetch();

            if (result.size() == 0) {
                return;
            } else if (result.size() > 1) {
                System.out.println("More than one match found for: " + p.nameToString() + ". Ignoring...");
            }

            String fullname = result.get(0).getValue(Tables.ADVISER.FULLNAME);
            Integer id = result.get(0).getValue(Tables.ADVISER.ID);
            Byte isAdmin = result.get(0).getValue(Tables.ADVISER.ISADMIN);

            Thing<Role> adviserRole = getRole(PROJECT_DB_KEY, PROJECT_DB_ADVISER_VALUE);
            myLogger.debug("Adding adviser role' to " + p.nameToString());
            tc.addThingToThing(person, adviserRole);


            if (isAdmin.intValue() != 0) {
                Thing<Role> adminRole = getRole(PROJECT_DB_KEY, PROJECT_DB_ADMIN_VALUE);
                myLogger.debug("Adding admin role to " + p.nameToString());
                tc.addThingToThing(person, adminRole);
            }

        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }

    }
}
