package hub.queries;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import hub.types.dynamic.JobStatus;
import hub.types.dynamic.Jobs;
import hub.types.persistent.Username;
import things.control.ThingReader;
import things.control.TypeRegistry;
import things.exceptions.KeyRuntimeException;
import things.exceptions.ThingRuntimeException;
import things.exceptions.TypeRuntimeException;
import things.model.PersistentValue;
import things.thing.Thing;
import things.thing.ThingControl;
import things.utils.MatcherUtils;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Project: things
 * <p>
 * Written by: Markus Binsteiner
 * Date: 28/03/14
 * Time: 12:48 PM
 */
public class SshJobsLister implements ThingReader {

    private ThingControl tc;
    private JSch jsch = new JSch();

    private String site;
    
    private String jobsType = TypeRegistry.getType(Jobs.class);

    public SshJobsLister(String site) {

        if ( MatcherUtils.isGlob(site) ) {
            throw new TypeRuntimeException("Specified site '"+site+"' can't be glob");
        }
        this.site = site;

        try {
            jsch.setKnownHosts(System.getProperty("user.home")+"/.ssh/known_hosts");
            jsch.setConfig("PreferredAuthentications", "publickey");

            jsch.addIdentity(System.getProperty("user.home")+"/.ssh/id_markus");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void setTc(ThingControl thingControl) {
        this.tc = thingControl;
    }

    private static JobStatus createJobStatus(String line) {

        String[] tokens = line.split("!");

        if ( tokens.length != 7 ) {
            return null;
        }

        JobStatus js = new JobStatus();
        js.setJobid(tokens[0]);
        js.setJobname(tokens[1]);
        js.setQueueDate(tokens[2]);
        js.setDispatchDate(tokens[3]);
        js.setHost(tokens[4]);
        js.setUsername(tokens[5]);
        js.setJobstatus(tokens[6]);

        return js;

    }


    public List<Thing> getOtherThingsByTypeAndKey(Thing parent, String queryType, String key) {
        if (! MatcherUtils.wildCardMatch(site, key)) {
            throw new KeyRuntimeException("JobFactory does only accept keys that can be matched with "+ site, key);
        }

        if ( ! MatcherUtils.wildCardMatch(jobsType, queryType) ) {
            throw new TypeRuntimeException("Job connector can only process types "+jobsType);
        }

        // check parent type


        List<Thing> identities = null;

        identities = tc.getOtherThingsByTypeAndKey(parent, Username.class, key);

        if ( identities == null || identities.size() == 0 ) {
            throw new ThingRuntimeException(parent, "No identities found for person "+parent.getId());
        }


        final List<Thing> result = Lists.newArrayList();

        Set<String> usernames = identities.stream().map(id -> ((Username)tc.getUntypedValue(id)).getValue()).collect(Collectors.toSet());

        Jobs jobs = getJobs(usernames);

        Thing t = new Thing(site, jobs);

        return Lists.newArrayList(t);
    }

    public Set<String> lookupThingsForValue(Serializable value) {
        return null;
    }

    @Override
    public PersistentValue readValue(String type, String valueId) {
        return null;
    }

//    public List<Thing> findThingsByTypeAndKey(String type, String key) {
//
//        if (! MatcherUtils.wildCardMatch(site, key)) {
//            throw new KeyRuntimeException("JobFactory does only accept keys that can be matched with "+ site, key);
//        }
//
//        if ( ! MatcherUtils.wildCardMatch(jobsType, type) ) {
//            throw new TypeRuntimeException("Job connector can only process types "+jobsType);
//        }
//
//        Jobs jobs = getJobs(null);
//
//        Thing t = new Thing(site, jobs);
//
//        return Lists.newArrayList(t);
//    }

    private List<JobStatus> retrieveJobs(Session session, String username) throws Exception {

        Channel channel = session.openChannel("exec");
        List<JobStatus> result = Lists.newArrayList();
        try {
            String command = "llq -r %id %jn %dq %dd %h %o %st";
            if ( ! Strings.isNullOrEmpty(username) ) {
                command = command+" -u "+username;
            }
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);

            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();
            Scanner sc = new Scanner(in);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                JobStatus js = createJobStatus(line);
                if (js != null) {
                    result.add(js);
                }
            }
        } finally {
            channel.disconnect();
        }

        return result;
    }

    private Jobs getJobs(Set<String> usernames) {

        final List<JobStatus> result = Lists.newArrayList();

        try {

            final Session session = jsch.getSession("user.markus", "login.uoa.nesi.org.nz", 22);
            session.connect();

            if ( usernames == null || usernames.size() == 0 ) {
                // all jobs
                List<JobStatus> allJobs = retrieveJobs(session, null);
                result.addAll(allJobs);
            } else {
                for (String username : usernames) {

                    List<JobStatus> jobs = retrieveJobs(session, username);
                    result.addAll(jobs);

                }
            }

            session.disconnect();


        } catch (Exception e) {
            //TODO error handling
            e.printStackTrace();
        }

        Jobs jobs = new Jobs(result);
        return jobs;
    }

}
