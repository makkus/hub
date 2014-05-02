package hub.config.hub;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import things.config.mongo.MongoThingConfiguration;
import things.control.ThingLookup;
import things.control.ThingReader;
import things.control.ThingWriter;

import java.util.TreeMap;

/**
 * Project: things-to-build
 * <p>
 * Written by: Markus Binsteiner
 * Date: 11/04/14
 * Time: 8:46 PM
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@PropertySource("classpath:pan_audit.properties")
@PropertySource("classpath:projectdb.properties")
@PropertySource("classpath:sshJobLister.properties")
@PropertySource(value = "file:/etc/hub/hub.properties", ignoreResourceNotFound = true)
@PropertySource(value = "file:${HOME}/.hub/hub.properties", ignoreResourceNotFound = true)
@ComponentScan(basePackages = "hub.config.hub")
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class HubConfig extends MongoThingConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return "hub";
    }


    @Bean(name = "thingReaders")
    public Multimap<String, ThingReader> thingReaders() throws Exception {

        Multimap<String, ThingReader> map = ArrayListMultimap.create();
        map.put("person/*", mongoConnector());
        map.put("type/*", mongoConnector());
        map.put("role/*", mongoConnector());

        return map;
    }

    @Bean(name = "thingWriters")
    public TreeMap<String, ThingWriter> thingWriters() throws Exception {

        TreeMap<String, ThingWriter> writerMap = Maps.newTreeMap();
        writerMap.put("person/*", mongoConnector());
        writerMap.put("type/*", mongoConnector());
        writerMap.put("role/*", mongoConnector());

        return writerMap;
    }


    @Bean(name = "thingLookups")
    public Multimap<String, ThingLookup> thingLookups() throws Exception {

        Multimap<String, ThingLookup> map = ArrayListMultimap.create();
        map.put("person/*", mongoConnector());
        map.put("type/*", mongoConnector());
        map.put("role/*", mongoConnector());

        return map;
    }
    

}




























//    @Bean
//    public BasicDataSource panAuditDataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setUrl("jdbc:h2:target/jooq-example");
//        ds.setDriverClassName("org.h2.Driver");
//        ds.setUsername("sa");
//        ds.setPassword("");
//        return ds;
//    }
