/**
 * This class is generated by jOOQ
 */
package projectdb.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Projectfollowup implements java.io.Serializable {

	private static final long serialVersionUID = 1823662195;

	private java.lang.Integer id;
	private java.lang.Integer projectid;
	private java.lang.Integer adviserid;
	private java.lang.String  date;
	private java.lang.String  notes;

	public Projectfollowup() {}

	public Projectfollowup(
		java.lang.Integer id,
		java.lang.Integer projectid,
		java.lang.Integer adviserid,
		java.lang.String  date,
		java.lang.String  notes
	) {
		this.id = id;
		this.projectid = projectid;
		this.adviserid = adviserid;
		this.date = date;
		this.notes = notes;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getProjectid() {
		return this.projectid;
	}

	public void setProjectid(java.lang.Integer projectid) {
		this.projectid = projectid;
	}

	public java.lang.Integer getAdviserid() {
		return this.adviserid;
	}

	public void setAdviserid(java.lang.Integer adviserid) {
		this.adviserid = adviserid;
	}

	public java.lang.String getDate() {
		return this.date;
	}

	public void setDate(java.lang.String date) {
		this.date = date;
	}

	public java.lang.String getNotes() {
		return this.notes;
	}

	public void setNotes(java.lang.String notes) {
		this.notes = notes;
	}
}
