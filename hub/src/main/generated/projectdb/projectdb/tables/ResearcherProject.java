/**
 * This class is generated by jOOQ
 */
package projectdb.tables;

/**
 * This class is generated by jOOQ.
 *
 * Maps researchers onto a project
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ResearcherProject extends org.jooq.impl.TableImpl<projectdb.tables.records.ResearcherProjectRecord> {

	private static final long serialVersionUID = 1679757514;

	/**
	 * The singleton instance of <code>projectdb.researcher_project</code>
	 */
	public static final projectdb.tables.ResearcherProject RESEARCHER_PROJECT = new projectdb.tables.ResearcherProject();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<projectdb.tables.records.ResearcherProjectRecord> getRecordType() {
		return projectdb.tables.records.ResearcherProjectRecord.class;
	}

	/**
	 * The column <code>projectdb.researcher_project.researcherId</code>.
	 */
	public final org.jooq.TableField<projectdb.tables.records.ResearcherProjectRecord, java.lang.Integer> RESEARCHERID = createField("researcherId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>projectdb.researcher_project.projectId</code>.
	 */
	public final org.jooq.TableField<projectdb.tables.records.ResearcherProjectRecord, java.lang.Integer> PROJECTID = createField("projectId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>projectdb.researcher_project.researcherRoleId</code>.
	 */
	public final org.jooq.TableField<projectdb.tables.records.ResearcherProjectRecord, java.lang.Integer> RESEARCHERROLEID = createField("researcherRoleId", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>projectdb.researcher_project.notes</code>.
	 */
	public final org.jooq.TableField<projectdb.tables.records.ResearcherProjectRecord, java.lang.String> NOTES = createField("notes", org.jooq.impl.SQLDataType.CLOB.length(65535).nullable(false), this, "");

	/**
	 * Create a <code>projectdb.researcher_project</code> table reference
	 */
	public ResearcherProject() {
		this("researcher_project", null);
	}

	/**
	 * Create an aliased <code>projectdb.researcher_project</code> table reference
	 */
	public ResearcherProject(java.lang.String alias) {
		this(alias, projectdb.tables.ResearcherProject.RESEARCHER_PROJECT);
	}

	private ResearcherProject(java.lang.String alias, org.jooq.Table<projectdb.tables.records.ResearcherProjectRecord> aliased) {
		this(alias, aliased, null);
	}

	private ResearcherProject(java.lang.String alias, org.jooq.Table<projectdb.tables.records.ResearcherProjectRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, projectdb.Projectdb.PROJECTDB, aliased, parameters, "Maps researchers onto a project");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<projectdb.tables.records.ResearcherProjectRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<projectdb.tables.records.ResearcherProjectRecord, ?>>asList(projectdb.Keys.RESEARCHER_PROJECT_IBFK_1, projectdb.Keys.RESEARCHER_PROJECT_IBFK_2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public projectdb.tables.ResearcherProject as(java.lang.String alias) {
		return new projectdb.tables.ResearcherProject(alias, this);
	}

	/**
	 * Rename this table
	 */
	public projectdb.tables.ResearcherProject rename(java.lang.String name) {
		return new projectdb.tables.ResearcherProject(name, null);
	}
}
