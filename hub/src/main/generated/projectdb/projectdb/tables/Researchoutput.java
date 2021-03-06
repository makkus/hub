/**
 * This class is generated by jOOQ
 */
package projectdb.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Researchoutput extends org.jooq.impl.TableImpl<projectdb.tables.records.ResearchoutputRecord> {

	private static final long serialVersionUID = -1126817747;

	/**
	 * The singleton instance of <code>projectdb.researchoutput</code>
	 */
	public static final projectdb.tables.Researchoutput RESEARCHOUTPUT = new projectdb.tables.Researchoutput();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<projectdb.tables.records.ResearchoutputRecord> getRecordType() {
		return projectdb.tables.records.ResearchoutputRecord.class;
	}

	/**
	 * The column <code>projectdb.researchoutput.id</code>.
	 */
	public final org.jooq.TableField<projectdb.tables.records.ResearchoutputRecord, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>projectdb.researchoutput.projectId</code>.
	 */
	public final org.jooq.TableField<projectdb.tables.records.ResearchoutputRecord, java.lang.Integer> PROJECTID = createField("projectId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>projectdb.researchoutput.typeId</code>.
	 */
	public final org.jooq.TableField<projectdb.tables.records.ResearchoutputRecord, java.lang.Integer> TYPEID = createField("typeId", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>projectdb.researchoutput.description</code>.
	 */
	public final org.jooq.TableField<projectdb.tables.records.ResearchoutputRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB.length(65535), this, "");

	/**
	 * The column <code>projectdb.researchoutput.link</code>.
	 */
	public final org.jooq.TableField<projectdb.tables.records.ResearchoutputRecord, java.lang.String> LINK = createField("link", org.jooq.impl.SQLDataType.CLOB.length(65535), this, "");

	/**
	 * The column <code>projectdb.researchoutput.date</code>.
	 */
	public final org.jooq.TableField<projectdb.tables.records.ResearchoutputRecord, java.lang.String> DATE = createField("date", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

	/**
	 * The column <code>projectdb.researchoutput.adviserId</code>.
	 */
	public final org.jooq.TableField<projectdb.tables.records.ResearchoutputRecord, java.lang.Integer> ADVISERID = createField("adviserId", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>projectdb.researchoutput</code> table reference
	 */
	public Researchoutput() {
		this("researchoutput", null);
	}

	/**
	 * Create an aliased <code>projectdb.researchoutput</code> table reference
	 */
	public Researchoutput(java.lang.String alias) {
		this(alias, projectdb.tables.Researchoutput.RESEARCHOUTPUT);
	}

	private Researchoutput(java.lang.String alias, org.jooq.Table<projectdb.tables.records.ResearchoutputRecord> aliased) {
		this(alias, aliased, null);
	}

	private Researchoutput(java.lang.String alias, org.jooq.Table<projectdb.tables.records.ResearchoutputRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, projectdb.Projectdb.PROJECTDB, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<projectdb.tables.records.ResearchoutputRecord, java.lang.Integer> getIdentity() {
		return projectdb.Keys.IDENTITY_RESEARCHOUTPUT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<projectdb.tables.records.ResearchoutputRecord> getPrimaryKey() {
		return projectdb.Keys.KEY_RESEARCHOUTPUT_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<projectdb.tables.records.ResearchoutputRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<projectdb.tables.records.ResearchoutputRecord>>asList(projectdb.Keys.KEY_RESEARCHOUTPUT_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<projectdb.tables.records.ResearchoutputRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<projectdb.tables.records.ResearchoutputRecord, ?>>asList(projectdb.Keys.RESEARCHOUTPUT_IBFK_1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public projectdb.tables.Researchoutput as(java.lang.String alias) {
		return new projectdb.tables.Researchoutput(alias, this);
	}

	/**
	 * Rename this table
	 */
	public projectdb.tables.Researchoutput rename(java.lang.String name) {
		return new projectdb.tables.Researchoutput(name, null);
	}
}
