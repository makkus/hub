/**
 * This class is generated by jOOQ
 */
package pan.auditdb.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AuditUserPrevRecord extends org.jooq.impl.TableRecordImpl<pan.auditdb.tables.records.AuditUserPrevRecord> implements org.jooq.Record13<java.lang.String, java.lang.Long, java.math.BigInteger, java.math.BigInteger, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, java.lang.String, java.math.BigInteger, java.math.BigInteger, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal> {

	private static final long serialVersionUID = -2004802137;

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.user</code>.
	 */
	public void setUser(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.user</code>.
	 */
	public java.lang.String getUser() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.jobs</code>.
	 */
	public void setJobs(java.lang.Long value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.jobs</code>.
	 */
	public java.lang.Long getJobs() {
		return (java.lang.Long) getValue(1);
	}

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.grid_jobs</code>.
	 */
	public void setGridJobs(java.math.BigInteger value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.grid_jobs</code>.
	 */
	public java.math.BigInteger getGridJobs() {
		return (java.math.BigInteger) getValue(2);
	}

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.total_cores</code>.
	 */
	public void setTotalCores(java.math.BigInteger value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.total_cores</code>.
	 */
	public java.math.BigInteger getTotalCores() {
		return (java.math.BigInteger) getValue(3);
	}

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.core_hours</code>.
	 */
	public void setCoreHours(java.math.BigDecimal value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.core_hours</code>.
	 */
	public java.math.BigDecimal getCoreHours() {
		return (java.math.BigDecimal) getValue(4);
	}

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.waiting_time</code>.
	 */
	public void setWaitingTime(java.math.BigDecimal value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.waiting_time</code>.
	 */
	public java.math.BigDecimal getWaitingTime() {
		return (java.math.BigDecimal) getValue(5);
	}

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.month</code>.
	 */
	public void setMonth(java.lang.String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.month</code>.
	 */
	public java.lang.String getMonth() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.year</code>.
	 */
	public void setYear(java.lang.String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.year</code>.
	 */
	public java.lang.String getYear() {
		return (java.lang.String) getValue(7);
	}

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.serial_jobs</code>.
	 */
	public void setSerialJobs(java.math.BigInteger value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.serial_jobs</code>.
	 */
	public java.math.BigInteger getSerialJobs() {
		return (java.math.BigInteger) getValue(8);
	}

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.parallel_jobs</code>.
	 */
	public void setParallelJobs(java.math.BigInteger value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.parallel_jobs</code>.
	 */
	public java.math.BigInteger getParallelJobs() {
		return (java.math.BigInteger) getValue(9);
	}

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.serial_core_hours</code>.
	 */
	public void setSerialCoreHours(java.math.BigDecimal value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.serial_core_hours</code>.
	 */
	public java.math.BigDecimal getSerialCoreHours() {
		return (java.math.BigDecimal) getValue(10);
	}

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.parallel_core_hours</code>.
	 */
	public void setParallelCoreHours(java.math.BigDecimal value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.parallel_core_hours</code>.
	 */
	public java.math.BigDecimal getParallelCoreHours() {
		return (java.math.BigDecimal) getValue(11);
	}

	/**
	 * Setter for <code>pandora_audit.audit_user_prev.total_grid_core_hours</code>.
	 */
	public void setTotalGridCoreHours(java.math.BigDecimal value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>pandora_audit.audit_user_prev.total_grid_core_hours</code>.
	 */
	public java.math.BigDecimal getTotalGridCoreHours() {
		return (java.math.BigDecimal) getValue(12);
	}

	// -------------------------------------------------------------------------
	// Record13 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row13<java.lang.String, java.lang.Long, java.math.BigInteger, java.math.BigInteger, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, java.lang.String, java.math.BigInteger, java.math.BigInteger, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal> fieldsRow() {
		return (org.jooq.Row13) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row13<java.lang.String, java.lang.Long, java.math.BigInteger, java.math.BigInteger, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, java.lang.String, java.math.BigInteger, java.math.BigInteger, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal> valuesRow() {
		return (org.jooq.Row13) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.USER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field2() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.JOBS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigInteger> field3() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.GRID_JOBS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigInteger> field4() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.TOTAL_CORES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field5() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.CORE_HOURS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field6() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.WAITING_TIME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field7() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.MONTH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field8() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.YEAR;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigInteger> field9() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.SERIAL_JOBS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigInteger> field10() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.PARALLEL_JOBS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field11() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.SERIAL_CORE_HOURS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field12() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.PARALLEL_CORE_HOURS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field13() {
		return pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV.TOTAL_GRID_CORE_HOURS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getUser();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value2() {
		return getJobs();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigInteger value3() {
		return getGridJobs();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigInteger value4() {
		return getTotalCores();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value5() {
		return getCoreHours();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value6() {
		return getWaitingTime();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value7() {
		return getMonth();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value8() {
		return getYear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigInteger value9() {
		return getSerialJobs();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigInteger value10() {
		return getParallelJobs();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value11() {
		return getSerialCoreHours();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value12() {
		return getParallelCoreHours();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value13() {
		return getTotalGridCoreHours();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value1(java.lang.String value) {
		setUser(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value2(java.lang.Long value) {
		setJobs(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value3(java.math.BigInteger value) {
		setGridJobs(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value4(java.math.BigInteger value) {
		setTotalCores(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value5(java.math.BigDecimal value) {
		setCoreHours(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value6(java.math.BigDecimal value) {
		setWaitingTime(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value7(java.lang.String value) {
		setMonth(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value8(java.lang.String value) {
		setYear(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value9(java.math.BigInteger value) {
		setSerialJobs(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value10(java.math.BigInteger value) {
		setParallelJobs(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value11(java.math.BigDecimal value) {
		setSerialCoreHours(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value12(java.math.BigDecimal value) {
		setParallelCoreHours(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord value13(java.math.BigDecimal value) {
		setTotalGridCoreHours(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditUserPrevRecord values(java.lang.String value1, java.lang.Long value2, java.math.BigInteger value3, java.math.BigInteger value4, java.math.BigDecimal value5, java.math.BigDecimal value6, java.lang.String value7, java.lang.String value8, java.math.BigInteger value9, java.math.BigInteger value10, java.math.BigDecimal value11, java.math.BigDecimal value12, java.math.BigDecimal value13) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached AuditUserPrevRecord
	 */
	public AuditUserPrevRecord() {
		super(pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV);
	}

	/**
	 * Create a detached, initialised AuditUserPrevRecord
	 */
	public AuditUserPrevRecord(java.lang.String user, java.lang.Long jobs, java.math.BigInteger gridJobs, java.math.BigInteger totalCores, java.math.BigDecimal coreHours, java.math.BigDecimal waitingTime, java.lang.String month, java.lang.String year, java.math.BigInteger serialJobs, java.math.BigInteger parallelJobs, java.math.BigDecimal serialCoreHours, java.math.BigDecimal parallelCoreHours, java.math.BigDecimal totalGridCoreHours) {
		super(pan.auditdb.tables.AuditUserPrev.AUDIT_USER_PREV);

		setValue(0, user);
		setValue(1, jobs);
		setValue(2, gridJobs);
		setValue(3, totalCores);
		setValue(4, coreHours);
		setValue(5, waitingTime);
		setValue(6, month);
		setValue(7, year);
		setValue(8, serialJobs);
		setValue(9, parallelJobs);
		setValue(10, serialCoreHours);
		setValue(11, parallelCoreHours);
		setValue(12, totalGridCoreHours);
	}
}
