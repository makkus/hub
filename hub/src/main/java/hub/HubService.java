package hub;

import com.unboundid.ldap.sdk.LDAPException;
import hub.config.hub.HubConfig;
import org.springframework.boot.SpringApplication;
import things.exceptions.ThingException;
import things.exceptions.ValueException;

/**
 * Project: hub
 * <p/>
 * Written by: Markus Binsteiner
 * Date: 12/03/14
 * Time: 10:56 AM
 */
public class HubService {
    

	public static void main(String[] args) throws ValueException, ThingException, LDAPException, InterruptedException {

        SpringApplication.run(HubConfig.class);

	}




}
