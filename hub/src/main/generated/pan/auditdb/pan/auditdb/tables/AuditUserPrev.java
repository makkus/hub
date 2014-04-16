/**
 * This class is generated by jOOQ
 */
package pan.auditdb.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AuditUserPrev extends org.jooq.impl.TableImpl<pan.auditdb.tables.records.AuditUserPrevRecord> {

	private static final long serialVersionUID = -1571582158;

	/**
	 * The singleton instance of <code>pandora_audit.audit_user_prev</code>
	 */
	public static final pan.auditdb.tables.AuditUserPrev AUDIT_USER_PREV = new pan.auditdb.tables.AuditUserPrev();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<pan.auditdb.tables.records.AuditUserPrevRecord> getRecordType() {
		return pan.auditdb.tables.records.AuditUserPrevRecord.class;
	}

	/**
	 * The column <code>pandora_audit.audit_user_prev.user</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.lang.String> USER = createField("user", org.jooq.impl.SQLDataType.VARCHAR.length(45), this, "");

	/**
	 * The column <code>pandora_audit.audit_user_prev.jobs</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.lang.Long> JOBS = createField("jobs", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>pandora_audit.audit_user_prev.grid_jobs</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.math.BigInteger> GRID_JOBS = createField("grid_jobs", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(23), this, "");

	/**
	 * The column <code>pandora_audit.audit_user_prev.total_cores</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.math.BigInteger> TOTAL_CORES = createField("total_cores", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(32), this, "");

	/**
	 * The column <code>pandora_audit.audit_user_prev.core_hours</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.math.BigDecimal> CORE_HOURS = createField("core_hours", org.jooq.impl.SQLDataType.DECIMAL.precision(45, 2), this, "");

	/**
	 * The column <code>pandora_audit.audit_user_prev.waiting_time</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.math.BigDecimal> WAITING_TIME = createField("waiting_time", org.jooq.impl.SQLDataType.DECIMAL.precision(35, 2), this, "");

	/**
	 * The column <code>pandora_audit.audit_user_prev.month</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.lang.String> MONTH = createField("month", org.jooq.impl.SQLDataType.VARCHAR.length(2), this, "");

	/**
	 * The column <code>pandora_audit.audit_user_prev.year</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.lang.String> YEAR = createField("year", org.jooq.impl.SQLDataType.VARCHAR.length(4), this, "");

	/**
	 * The column <code>pandora_audit.audit_user_prev.serial_jobs</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.math.BigInteger> SERIAL_JOBS = createField("serial_jobs", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(23), this, "");

	/**
	 * The column <code>pandora_audit.audit_user_prev.parallel_jobs</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.math.BigInteger> PARALLEL_JOBS = createField("parallel_jobs", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(23), this, "");

	/**
	 * The column <code>pandora_audit.audit_user_prev.serial_core_hours</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.math.BigDecimal> SERIAL_CORE_HOURS = createField("serial_core_hours", org.jooq.impl.SQLDataType.DECIMAL.precision(45, 2), this, "");

	/**
	 * The column <code>pandora_audit.audit_user_prev.parallel_core_hours</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.math.BigDecimal> PARALLEL_CORE_HOURS = createField("parallel_core_hours", org.jooq.impl.SQLDataType.DECIMAL.precision(45, 2), this, "");

	/**
	 * The column <code>pandora_audit.audit_user_prev.total_grid_core_hours</code>.
	 */
	public final org.jooq.TableField<pan.auditdb.tables.records.AuditUserPrevRecord, java.math.BigDecimal> TOTAL_GRID_CORE_HOURS = createField("total_grid_core_hours", org.jooq.impl.SQLDataType.DECIMAL.precision(45, 2), this, "");

	/**
	 * Create a <code>pandora_audit.audit_user_prev</code> table reference
	 */
	public AuditUserPrev() {
		this("audit_user_prev", null);
	}

	/**
	 * Create an aliased <code>pandora_audit.audit_user_prev</code> table reference
	 */
	public AuditUserPrev(java.lang.String alias) {
		this(alias, pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV);
	}

	private AuditUserPrev(java.lang.String alias, org.jooq.Table<pan.auditdb.tables.records.AuditUserPrevRecord> aliased) {
		this(alias, aliased, null);
	}

	private AuditUserPrev(java.lang.String alias, org.jooq.Table<pan.auditdb.tables.records.AuditUserPrevRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, pan.auditdb.PandoraAudit.PANDORA_AUDIT, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public pan.auditdb.tables.AuditUserPrev as(java.lang.String alias) {
		return new pan.auditdb.tables.AuditUserPrev(alias, this);
	}

	/**
	 * Rename this table
	 */
	public pan.auditdb.tables.AuditUserPrev rename(java.lang.String name) {
		return new pan.auditdb.tables.AuditUserPrev(name, null);
	}
}
