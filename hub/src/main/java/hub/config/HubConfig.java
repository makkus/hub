package hub.config;

/**
 * Project: things-to-build
 * <p>
 * Written by: Markus Binsteiner
 * Date: 11/04/14
 * Time: 8:46 PM
 */
public class HubConfig {

    //    public Multimap<String, ThingReader> thingReaders() throws Exception {
//
//        Multimap<String, ThingReader> map = ArrayListMultimap.create();
//        map.put("person/*", mongoConnector());
//        map.put("type/*", mongoConnector());
//        map.put("role/*", mongoConnector());
//
//        SshJobsLister sjl = new SshJobsLister("uoa");
//        map.put("jobs/uoa", sjl);
//
////        UserAssembly pac = new UserAssembly();
//
////        map.put("user/*", pac);
////        map.put("user/*", pac);
//
//        return map;
//    }

//    @Bean
//    public TreeMap<String, ThingWriter> thingWriters() throws Exception {
//
//        TreeMap<String, ThingWriter> writerMap = Maps.newTreeMap();
//        writerMap.put("person/*", mongoConnector());
//        writerMap.put("type/*", mongoConnector());
//        writerMap.put("role/*", mongoConnector());
//
//        return writerMap;
//    }

//    @Bean
//    public TreeMap<String, ThingQuery> thingQueries() throws Exception {
//
//        UserLookup ul = new UserLookup();
//
//        TreeMap<String, ThingQuery> queryMap = Maps.newTreeMap();
//
//        queryMap.put("lookup_user", ul);
//
//        return queryMap;
//    }

//    @Bean
//    public TreeMap<String, ThingAction> thingActions() throws Exception {
//
//        LdapImporter ldapImporter = new LdapImporter();
//
//        TreeMap<String, ThingAction> actionMap = Maps.newTreeMap();
//
//        actionMap.put("import_uoa_ldap", ldapImporter);
//
//        ClearMongoDatabase cmd = new ClearMongoDatabase(mongoConfiguration.mongoTemplate());
//
//        actionMap.put("clear_mongo", cmd);
//
//        return actionMap;
//    }
}
