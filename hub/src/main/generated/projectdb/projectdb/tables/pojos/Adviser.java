/**
 * This class is generated by jOOQ
 */
package projectdb.tables.pojos;

/**
 * This class is generated by jOOQ.
 *
 * NeSI and CeR staff
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Adviser implements java.io.Serializable {

	private static final long serialVersionUID = -469642654;

	private java.lang.Integer  id;
	private java.lang.String   fullname;
	private java.lang.String   email;
	private java.lang.String   phone;
	private java.lang.String   address;
	private java.lang.String   institution;
	private java.lang.String   division;
	private java.lang.String   department;
	private java.lang.String   pictureurl;
	private java.lang.String   startdate;
	private java.lang.String   enddate;
	private java.lang.String   notes;
	private java.lang.Byte     isadmin;
	private java.lang.String   tuakiriuniqueid;
	private java.lang.String   tuakiritoken;
	private java.sql.Timestamp lastmodified;

	public Adviser() {}

	public Adviser(
		java.lang.Integer  id,
		java.lang.String   fullname,
		java.lang.String   email,
		java.lang.String   phone,
		java.lang.String   address,
		java.lang.String   institution,
		java.lang.String   division,
		java.lang.String   department,
		java.lang.String   pictureurl,
		java.lang.String   startdate,
		java.lang.String   enddate,
		java.lang.String   notes,
		java.lang.Byte     isadmin,
		java.lang.String   tuakiriuniqueid,
		java.lang.String   tuakiritoken,
		java.sql.Timestamp lastmodified
	) {
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.institution = institution;
		this.division = division;
		this.department = department;
		this.pictureurl = pictureurl;
		this.startdate = startdate;
		this.enddate = enddate;
		this.notes = notes;
		this.isadmin = isadmin;
		this.tuakiriuniqueid = tuakiriuniqueid;
		this.tuakiritoken = tuakiritoken;
		this.lastmodified = lastmodified;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getFullname() {
		return this.fullname;
	}

	public void setFullname(java.lang.String fullname) {
		this.fullname = fullname;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getPhone() {
		return this.phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	public java.lang.String getAddress() {
		return this.address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getInstitution() {
		return this.institution;
	}

	public void setInstitution(java.lang.String institution) {
		this.institution = institution;
	}

	public java.lang.String getDivision() {
		return this.division;
	}

	public void setDivision(java.lang.String division) {
		this.division = division;
	}

	public java.lang.String getDepartment() {
		return this.department;
	}

	public void setDepartment(java.lang.String department) {
		this.department = department;
	}

	public java.lang.String getPictureurl() {
		return this.pictureurl;
	}

	public void setPictureurl(java.lang.String pictureurl) {
		this.pictureurl = pictureurl;
	}

	public java.lang.String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(java.lang.String startdate) {
		this.startdate = startdate;
	}

	public java.lang.String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(java.lang.String enddate) {
		this.enddate = enddate;
	}

	public java.lang.String getNotes() {
		return this.notes;
	}

	public void setNotes(java.lang.String notes) {
		this.notes = notes;
	}

	public java.lang.Byte getIsadmin() {
		return this.isadmin;
	}

	public void setIsadmin(java.lang.Byte isadmin) {
		this.isadmin = isadmin;
	}

	public java.lang.String getTuakiriuniqueid() {
		return this.tuakiriuniqueid;
	}

	public void setTuakiriuniqueid(java.lang.String tuakiriuniqueid) {
		this.tuakiriuniqueid = tuakiriuniqueid;
	}

	public java.lang.String getTuakiritoken() {
		return this.tuakiritoken;
	}

	public void setTuakiritoken(java.lang.String tuakiritoken) {
		this.tuakiritoken = tuakiritoken;
	}

	public java.sql.Timestamp getLastmodified() {
		return this.lastmodified;
	}

	public void setLastmodified(java.sql.Timestamp lastmodified) {
		this.lastmodified = lastmodified;
	}
}
