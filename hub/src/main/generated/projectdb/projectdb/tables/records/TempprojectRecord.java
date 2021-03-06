/**
 * This class is generated by jOOQ
 */
package projectdb.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TempprojectRecord extends org.jooq.impl.TableRecordImpl<projectdb.tables.records.TempprojectRecord> implements org.jooq.Record4<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String> {

	private static final long serialVersionUID = -430509176;

	/**
	 * Setter for <code>projectdb.tempproject.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>projectdb.tempproject.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>projectdb.tempproject.owner</code>.
	 */
	public void setOwner(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>projectdb.tempproject.owner</code>.
	 */
	public java.lang.String getOwner() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>projectdb.tempproject.lastVisited</code>.
	 */
	public void setLastvisited(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>projectdb.tempproject.lastVisited</code>.
	 */
	public java.lang.Integer getLastvisited() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>projectdb.tempproject.projectString</code>.
	 */
	public void setProjectstring(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>projectdb.tempproject.projectString</code>.
	 */
	public java.lang.String getProjectstring() {
		return (java.lang.String) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String> fieldsRow() {
		return (org.jooq.Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String> valuesRow() {
		return (org.jooq.Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return projectdb.tables.Tempproject.TEMPPROJECT.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return projectdb.tables.Tempproject.TEMPPROJECT.OWNER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return projectdb.tables.Tempproject.TEMPPROJECT.LASTVISITED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return projectdb.tables.Tempproject.TEMPPROJECT.PROJECTSTRING;
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
		return getOwner();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getLastvisited();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getProjectstring();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TempprojectRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TempprojectRecord value2(java.lang.String value) {
		setOwner(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TempprojectRecord value3(java.lang.Integer value) {
		setLastvisited(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TempprojectRecord value4(java.lang.String value) {
		setProjectstring(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TempprojectRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.Integer value3, java.lang.String value4) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TempprojectRecord
	 */
	public TempprojectRecord() {
		super(projectdb.tables.Tempproject.TEMPPROJECT);
	}

	/**
	 * Create a detached, initialised TempprojectRecord
	 */
	public TempprojectRecord(java.lang.Integer id, java.lang.String owner, java.lang.Integer lastvisited, java.lang.String projectstring) {
		super(projectdb.tables.Tempproject.TEMPPROJECT);

		setValue(0, id);
		setValue(1, owner);
		setValue(2, lastvisited);
		setValue(3, projectstring);
	}
}
