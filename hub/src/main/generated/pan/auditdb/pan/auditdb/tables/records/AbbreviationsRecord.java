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
public class AbbreviationsRecord extends org.jooq.impl.UpdatableRecordImpl<pan.auditdb.tables.records.AbbreviationsRecord> implements org.jooq.Record2<java.lang.String, java.lang.String> {

	private static final long serialVersionUID = 506849627;

	/**
	 * Setter for <code>pandora_audit.abbreviations.id</code>.
	 */
	public void setId(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>pandora_audit.abbreviations.id</code>.
	 */
	public java.lang.String getId() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>pandora_audit.abbreviations.decode</code>.
	 */
	public void setDecode(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>pandora_audit.abbreviations.decode</code>.
	 */
	public java.lang.String getDecode() {
		return (java.lang.String) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.String> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return pan.auditdb.tables.Abbreviations.ABBREVIATIONS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return pan.auditdb.tables.Abbreviations.ABBREVIATIONS.DECODE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getDecode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbbreviationsRecord value1(java.lang.String value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbbreviationsRecord value2(java.lang.String value) {
		setDecode(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbbreviationsRecord values(java.lang.String value1, java.lang.String value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached AbbreviationsRecord
	 */
	public AbbreviationsRecord() {
		super(pan.auditdb.tables.Abbreviations.ABBREVIATIONS);
	}

	/**
	 * Create a detached, initialised AbbreviationsRecord
	 */
	public AbbreviationsRecord(java.lang.String id, java.lang.String decode) {
		super(pan.auditdb.tables.Abbreviations.ABBREVIATIONS);

		setValue(0, id);
		setValue(1, decode);
	}
}