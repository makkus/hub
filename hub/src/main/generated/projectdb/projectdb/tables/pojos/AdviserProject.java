/**
 * This class is generated by jOOQ
 */
package projectdb.tables.pojos;

/**
 * This class is generated by jOOQ.
 *
 * Maps advisers into project
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AdviserProject implements java.io.Serializable {

	private static final long serialVersionUID = 675154496;

	private java.lang.Integer adviserid;
	private java.lang.Integer projectid;
	private java.lang.Integer adviserroleid;
	private java.lang.String  notes;

	public AdviserProject() {}

	public AdviserProject(
		java.lang.Integer adviserid,
		java.lang.Integer projectid,
		java.lang.Integer adviserroleid,
		java.lang.String  notes
	) {
		this.adviserid = adviserid;
		this.projectid = projectid;
		this.adviserroleid = adviserroleid;
		this.notes = notes;
	}

	public java.lang.Integer getAdviserid() {
		return this.adviserid;
	}

	public void setAdviserid(java.lang.Integer adviserid) {
		this.adviserid = adviserid;
	}

	public java.lang.Integer getProjectid() {
		return this.projectid;
	}

	public void setProjectid(java.lang.Integer projectid) {
		this.projectid = projectid;
	}

	public java.lang.Integer getAdviserroleid() {
		return this.adviserroleid;
	}

	public void setAdviserroleid(java.lang.Integer adviserroleid) {
		this.adviserroleid = adviserroleid;
	}

	public java.lang.String getNotes() {
		return this.notes;
	}

	public void setNotes(java.lang.String notes) {
		this.notes = notes;
	}
}