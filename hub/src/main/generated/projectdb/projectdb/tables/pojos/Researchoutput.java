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
public class Researchoutput implements java.io.Serializable {

	private static final long serialVersionUID = 2076127770;

	private java.lang.Integer id;
	private java.lang.Integer projectid;
	private java.lang.Integer typeid;
	private java.lang.String  description;
	private java.lang.String  link;
	private java.lang.String  date;
	private java.lang.Integer adviserid;

	public Researchoutput() {}

	public Researchoutput(
		java.lang.Integer id,
		java.lang.Integer projectid,
		java.lang.Integer typeid,
		java.lang.String  description,
		java.lang.String  link,
		java.lang.String  date,
		java.lang.Integer adviserid
	) {
		this.id = id;
		this.projectid = projectid;
		this.typeid = typeid;
		this.description = description;
		this.link = link;
		this.date = date;
		this.adviserid = adviserid;
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

	public java.lang.Integer getTypeid() {
		return this.typeid;
	}

	public void setTypeid(java.lang.Integer typeid) {
		this.typeid = typeid;
	}

	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getLink() {
		return this.link;
	}

	public void setLink(java.lang.String link) {
		this.link = link;
	}

	public java.lang.String getDate() {
		return this.date;
	}

	public void setDate(java.lang.String date) {
		this.date = date;
	}

	public java.lang.Integer getAdviserid() {
		return this.adviserid;
	}

	public void setAdviserid(java.lang.Integer adviserid) {
		this.adviserid = adviserid;
	}
}
