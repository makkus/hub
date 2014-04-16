package hub.queries;

import com.google.common.collect.Lists;
import hub.types.dynamic.AuditRecord;
import hub.types.persistent.Username;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import pan.auditdb.Tables;
import things.control.ThingQuery;
import things.exceptions.QueryException;
import things.thing.Thing;
import things.thing.ThingControl;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Markus Binsteiner
 */
public class PanAuditQuery implements ThingQuery<AuditRecord> {

    @Autowired
    private ThingControl tc;

    @Autowired
    private DefaultDSLContext jooq;

    @Autowired
    private UserLookup userLookup;

    @Override
    public List<Thing<AuditRecord>> queryThings(List<Thing> things) throws QueryException {

        List<Thing<Username>> usernames = userLookup.getUsernames(things);
        List<Thing<AuditRecord>> records = Lists.newArrayList();
        for ( Thing<Username> un : usernames ) {
            Thing t = new Thing(un.getValueId(), getAudit(un));
            records.add(t);
        }
        return records;
    }

    private AuditRecord getAudit(Thing<Username> un) {
        Result<Record2<Integer, BigDecimal>> result = jooq
                .select(Tables.AUDIT_USER.DONE, Tables.AUDIT_USER.CORE_HOURS)
                .from(Tables.AUDIT_USER)
                .where(Tables.AUDIT_USER.USER.equal(tc.getValue(un).getValue())).fetch();

        final AuditRecord ar = new AuditRecord();

        result.stream()
                .forEach(r -> ar.addJob(r.getValue(Tables.AUDIT_USER.DONE).toString(), r.getValue(Tables.AUDIT_USER.CORE_HOURS)));


        return ar;
    }
}
