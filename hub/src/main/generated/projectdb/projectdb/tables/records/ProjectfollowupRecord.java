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
public class ProjectfollowupRecord extends org.jooq.impl.UpdatableRecordImpl<projectdb.tables.records.ProjectfollowupRecord> implements org.jooq.Record5<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String> {

	private static final long serialVersionUID = 435550129;

	/**
	 * Setter for <code>projectdb.projectfollowup.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>projectdb.projectfollowup.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>projectdb.projectfollowup.projectId</code>.
	 */
	public void setProjectid(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>projectdb.projectfollowup.projectId</code>.
	 */
	public java.lang.Integer getProjectid() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>projectdb.projectfollowup.adviserId</code>.
	 */
	public void setAdviserid(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>projectdb.projectfollowup.adviserId</code>.
	 */
	public java.lang.Integer getAdviserid() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>projectdb.projectfollowup.date</code>.
	 */
	public void setDate(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>projectdb.projectfollowup.date</code>.
	 */
	public java.lang.String getDate() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>projectdb.projectfollowup.notes</code>.
	 */
	public void setNotes(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>projectdb.projectfollowup.notes</code>.
	 */
	public java.lang.String getNotes() {
		return (java.lang.String) getValue(4);
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
	// Record5 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row5<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row5<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return projectdb.tables.Projectfollowup.PROJECTFOLLOWUP.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return projectdb.tables.Projectfollowup.PROJECTFOLLOWUP.PROJECTID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return projectdb.tables.Projectfollowup.PROJECTFOLLOWUP.ADVISERID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return projectdb.tables.Projectfollowup.PROJECTFOLLOWUP.DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return projectdb.tables.Projectfollowup.PROJECTFOLLOWUP.NOTES;
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
	public java.lang.Integer value2() {
		return getProjectid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getAdviserid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getNotes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProjectfollowupRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProjectfollowupRecord value2(java.lang.Integer value) {
		setProjectid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProjectfollowupRecord value3(java.lang.Integer value) {
		setAdviserid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProjectfollowupRecord value4(java.lang.String value) {
		setDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProjectfollowupRecord value5(java.lang.String value) {
		setNotes(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProjectfollowupRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.Integer value3, java.lang.String value4, java.lang.String value5) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ProjectfollowupRecord
	 */
	public ProjectfollowupRecord() {
		super(projectdb.tables.Projectfollowup.PROJECTFOLLOWUP);
	}

	/**
	 * Create a detached, initialised ProjectfollowupRecord
	 */
	public ProjectfollowupRecord(java.lang.Integer id, java.lang.Integer projectid, java.lang.Integer adviserid, java.lang.String date, java.lang.String notes) {
		super(projectdb.tables.Projectfollowup.PROJECTFOLLOWUP);

		setValue(0, id);
		setValue(1, projectid);
		setValue(2, adviserid);
		setValue(3, date);
		setValue(4, notes);
	}
}
