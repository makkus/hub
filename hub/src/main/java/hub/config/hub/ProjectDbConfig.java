package hub.config.hub;

import com.jolbox.bonecp.BoneCPDataSource;
import hub.utils.JOOQToSpringExceptionTransformer;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

/**
 * Configuration to setup access to pan admin database.
 *
 * @author: Markus Binsteiner
 */
@Configuration
public class ProjectDbConfig {
    
    
    @Autowired
    private Environment env;


    // =============================================================================
    // Pan audit connector

    @Bean(destroyMethod = "close", name = "projectDbDataSource")
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();

        dataSource.setDriverClass(env.getRequiredProperty("projectDB.db.driver"));
        dataSource.setJdbcUrl(env.getRequiredProperty("projectDB.db.url"));
        dataSource.setUsername(env.getRequiredProperty("projectDB.db.username"));
        dataSource.setPassword(env.getRequiredProperty("projectDB.db.password"));

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

        String sqlDialectName = env.getRequiredProperty("projectDB.jooq.sql.dialect");
        SQLDialect dialect = SQLDialect.valueOf(sqlDialectName);
        jooqConfiguration.set(dialect);

        return jooqConfiguration;
    }

    @Bean(name = "projectDbContext")
    public DefaultDSLContext dsl() {
        return new DefaultDSLContext(configuration());
    }
}
