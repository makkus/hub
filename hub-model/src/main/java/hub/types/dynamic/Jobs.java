package hub.types.dynamic;

import java.time.Instant;
import java.util.List;

import things.model.Value;

/**
 * Project: things
 * <p>
 * Written by: Markus Binsteiner
 * Date: 28/03/14
 * Time: 2:32 PM
 */
public class Jobs implements Value {
    
    
    private Instant timestamp;
    private List<JobStatus> jobs;

    public Jobs(List<JobStatus> jobs) {
        this.jobs = jobs;
        timestamp = Instant.now();
    }

    public List<JobStatus> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobStatus> jobs) {
        this.jobs = jobs;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setDate(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
