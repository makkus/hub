/**
 * This class is generated by jOOQ
 */
package projectdb.tables.records;

/**
 * This class is generated by jOOQ.
 *
 * For KPI NESI-9, these are it's sub categories
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class KpicodeRecord extends org.jooq.impl.UpdatableRecordImpl<projectdb.tables.records.KpicodeRecord> implements org.jooq.Record2<java.lang.Integer, java.lang.String> {

	private static final long serialVersionUID = 537960217;

	/**
	 * Setter for <code>projectdb.kpicode.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>projectdb.kpicode.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>projectdb.kpicode.code</code>.
	 */
	public void setCode(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>projectdb.kpicode.code</code>.
	 */
	public java.lang.String getCode() {
		return (java.lang.String) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.String> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.String> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return projectdb.tables.Kpicode.KPICODE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return projectdb.tables.Kpicode.KPICODE.CODE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public KpicodeRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public KpicodeRecord value2(java.lang.String value) {
		setCode(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public KpicodeRecord values(java.lang.Integer value1, java.lang.String value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached KpicodeRecord
	 */
	public KpicodeRecord() {
		super(projectdb.tables.Kpicode.KPICODE);
	}

	/**
	 * Create a detached, initialised KpicodeRecord
	 */
	public KpicodeRecord(java.lang.Integer id, java.lang.String code) {
		super(projectdb.tables.Kpicode.KPICODE);

		setValue(0, id);
		setValue(1, code);
	}
}
