package hub.queries.users;

import com.google.common.collect.Lists;
import hub.types.dynamic.AuditRecord;
import hub.types.persistent.Username;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import pan.auditdb.Tables;
import things.control.ThingQuery;
import things.exceptions.QueryException;
import things.thing.Thing;
import things.thing.ThingControl;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author: Markus Binsteiner
 */
public class PanAuditQuery implements ThingQuery<AuditRecord> {
    
    @Autowired
    private ThingControl tc;

    @Resource(name = "panAuditContext")
    private DefaultDSLContext jooq;

    @Autowired
    private UserUtils userUtils;

    @Override
    public List<Thing<AuditRecord>> query(List<Thing> things, Map<String, String> params) throws QueryException {

        List<Thing<Username>> usernames = userUtils.getUsernames(things);
        List<Thing<AuditRecord>> records = Lists.newArrayList();
        for ( Thing<Username> un : usernames ) {
            Thing t = new Thing(un.getValueId(), getAudit(un));
            records.add(t);
        }
        return records;
    }

    private AuditRecord getAudit(Thing<Username> un) {

        SelectConditionStep<Record2<Integer, BigDecimal>> query = jooq
                .select(Tables.AUDIT_USER.DONE, Tables.AUDIT_USER.CORE_HOURS)
                .from(Tables.AUDIT_USER)
                .where(Tables.AUDIT_USER.USER.equal(tc.getValue(un).getValue()));

        Result<Record2<Integer, BigDecimal>> result = query.fetch();

        final AuditRecord ar = new AuditRecord();

        result.stream()
                .forEach(r -> ar.addJob(r.getValue(Tables.AUDIT_USER.DONE).toString(), r.getValue(Tables.AUDIT_USER.CORE_HOURS)));

        return ar;
    }
}
