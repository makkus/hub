package hub.config.hub;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.jolbox.bonecp.BoneCPDataSource;
import hub.queries.SshJobsLister;
import hub.utils.JOOQToSpringExceptionTransformer;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import things.config.mongo.MongoThingConfiguration;
import things.control.ThingReader;
import things.control.ThingWriter;

import javax.sql.DataSource;
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
@ComponentScan(basePackages = "hub.config.hub")
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class HubConfig extends MongoThingConfiguration {


    @Override
    protected String getDatabaseName() {
        return "hub";
    }

    @Bean
    public Multimap<String, ThingReader> thingReaders() throws Exception {

        Multimap<String, ThingReader> map = ArrayListMultimap.create();
        map.put("person/*", mongoConnector());
        map.put("type/*", mongoConnector());
        map.put("role/*", mongoConnector());

        SshJobsLister sjl = new SshJobsLister("uoa");
        map.put("jobs/uoa", sjl);

//        UserAssembly pac = new UserAssembly();

//        map.put("user/*", pac);
//        map.put("user/*", pac);

        return map;
    }

    @Bean
    public TreeMap<String, ThingWriter> thingWriters() throws Exception {

        TreeMap<String, ThingWriter> writerMap = Maps.newTreeMap();
        writerMap.put("person/*", mongoConnector());
        writerMap.put("type/*", mongoConnector());
        writerMap.put("role/*", mongoConnector());

        return writerMap;
    }







    @Autowired
    private Environment env;

    @Bean(destroyMethod = "close", name = "jooqDataSource")
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();

        dataSource.setDriverClass(env.getRequiredProperty("db.driver"));
        dataSource.setJdbcUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));

        return dataSource;
    }

    @Bean
    public LazyConnectionDataSourceProxy lazyConnectionDataSource() {
        return new LazyConnectionDataSourceProxy(dataSource());
    }

    @Bean
    public TransactionAwareDataSourceProxy transactionAwareDataSource() {
        return new TransactionAwareDataSourceProxy(lazyConnectionDataSource());
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(lazyConnectionDataSource());
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider() {
        return new DataSourceConnectionProvider(transactionAwareDataSource());
    }

    @Bean
    public JOOQToSpringExceptionTransformer jooqToSpringExceptionTransformer() {
        return new JOOQToSpringExceptionTransformer();
    }

    @Bean
    public DefaultConfiguration configuration() {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();

        jooqConfiguration.set(connectionProvider());
        jooqConfiguration.set(new DefaultExecuteListenerProvider(
            jooqToSpringExceptionTransformer()
        ));

        String sqlDialectName = env.getRequiredProperty("jooq.sql.dialect");
        SQLDialect dialect = SQLDialect.valueOf(sqlDialectName);
        jooqConfiguration.set(dialect);

        return jooqConfiguration;
    }

    @Bean
    public DefaultDSLContext dsl() {
        return new DefaultDSLContext(configuration());
    }

//    @Bean
//    public DataSourceInitializer dataSourceInitializer() {
//        DataSourceInitializer initializer = new DataSourceInitializer();
//        initializer.setDataSource(dataSource());
//
//        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScript(
//                new ClassPathResource(env.getRequiredProperty("db.schema.script"))
//        );
//
//        initializer.setDatabasePopulator(populator);
//        return initializer;
//    }
}




























//    @Bean
//    public BasicDataSource dataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setUrl("jdbc:h2:target/jooq-example");
//        ds.setDriverClassName("org.h2.Driver");
//        ds.setUsername("sa");
//        ds.setPassword("");
//        return ds;
//    }
