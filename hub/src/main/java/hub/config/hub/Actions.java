package hub.config.hub;

import com.google.common.collect.Maps;
import hub.actions.ClearMongoDatabase;
import hub.actions.LdapImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import things.control.ThingAction;

import java.util.TreeMap;

/**
 * @author: Markus Binsteiner
 */
@Configuration
@Import(HubConfig.class)
public class Actions {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public LdapImporter ldapImporter() throws Exception {
        LdapImporter ldapImporter = new LdapImporter();
        return ldapImporter;
    }

    @Bean
    public TreeMap<String, ThingAction> thingActions() throws Exception {

        TreeMap<String, ThingAction> actionMap = Maps.newTreeMap();

        actionMap.put("import_uoa_ldap", ldapImporter());

        ClearMongoDatabase cmd = new ClearMongoDatabase(mongoTemplate);

        actionMap.put("clear_mongo", cmd);

        return actionMap;
    }
}
