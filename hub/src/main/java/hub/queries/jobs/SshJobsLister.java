package hub.queries.jobs;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import hub.types.dynamic.JobStatus;
import hub.types.dynamic.Jobs;
import hub.types.persistent.Person;
import hub.types.persistent.Username;
import hub.queries.users.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import things.control.ThingQuery;
import things.control.TypeRegistry;
import things.exceptions.QueryException;
import things.exceptions.TypeRuntimeException;
import things.thing.Thing;
import things.thing.ThingControl;
import things.utils.MatcherUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Project: things
 * <p>
 * Written by: Markus Binsteiner Date: 28/03/14 Time: 12:48 PM
 */
public class SshJobsLister implements ThingQuery<Jobs> {

	@Autowired
	private ThingControl tc;
    @Autowired
    private UserUtils utils;

	private JSch jsch = new JSch();

	private final String site;

    private final String host_name;
    private final int host_port;
    private final String ssh_username;

	private String jobsType = TypeRegistry.getType(Jobs.class);

	public SshJobsLister(String sitename, String ssh_username, String host, int port, String ssh_key_file, String known_hosts_file) {

        this.site = sitename;

		if (MatcherUtils.isGlob(site)) {
			throw new TypeRuntimeException("Specified site '" + site
					+ "' can't be glob");
		}

        this.host_name = host;
        this.host_port = port;

        this.ssh_username = ssh_username;

		try {
			jsch.setKnownHosts(known_hosts_file);
			jsch.setConfig("PreferredAuthentications", "publickey");

			jsch.addIdentity(ssh_key_file);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private Jobs getJobs(Set<String> usernames) {

		final List<JobStatus> result = Lists.newArrayList();

		try {

			final Session session = jsch.getSession(ssh_username,
					host_name, host_port);
			session.connect();


				for (String username : usernames) {

					List<JobStatus> jobs = LoadLeveler.retrieveJobs(session,
							username);
					result.addAll(jobs);


			}

			session.disconnect();

		} catch (Exception e) {
			// TODO error handling
			e.printStackTrace();
		}

		Jobs jobs = new Jobs(result);
		return jobs;
	}

	@Override
	public List<Thing<Jobs>> query(List<Thing> things,
			Map<String, String> queryParams) throws QueryException {

        Set<String> usernames = Sets.newHashSet();
        for (Thing t : things) {
            if ( TypeRegistry.equalsType(t.getThingType(), Person.class) ) {
                usernames.addAll(utils.lookupUsernames(t).stream().map(un -> tc.getValue(un).getValue()).collect(Collectors.toSet()));
            } else if (TypeRegistry.equalsType(t.getThingType(), Username.class)) {
                Username un = (Username) tc.getValue(t);
                usernames.add(un.getValue());
            }
        }

        Jobs jobs = getJobs(usernames);
        Thing t = new Thing(site, jobs);

        return Lists.newArrayList(t);
	}
}
