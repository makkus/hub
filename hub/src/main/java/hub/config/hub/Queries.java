package hub.config.hub;

import com.google.common.collect.Maps;
import hub.queries.PanAuditQuery;
import hub.queries.UserLookup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import things.control.ThingQuery;

import java.util.TreeMap;

/**
 * @author: Markus Binsteiner
 */
@Configuration
public class Queries {

    @Bean
    public UserLookup userLookup() {
        return new UserLookup();
    }

    @Bean
    public PanAuditQuery panAuditQuery() {
        return new PanAuditQuery();
    }
    
    @Bean
    public TreeMap<String, ThingQuery> thingQueries() throws Exception {

        TreeMap<String, ThingQuery> queryMap = Maps.newTreeMap();
        queryMap.put("lookup_user", userLookup());
        queryMap.put("audit_lookup", panAuditQuery());

        return queryMap;
    }
}
