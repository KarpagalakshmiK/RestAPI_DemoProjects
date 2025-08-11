package POJO;

public class Getcourse {
	
	//shortcut for getter setter-alt+shift+s
	
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private Courses_Nested courses;
	private String linkedIn;
	
	public String getInstructor() {
		return instructor;
	}
	
	public void setInstructor(String ins) {
		this.instructor=ins;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url=url;
	}
	public String getservices() {
		return services;
	}
	
	public void setServices(String serv) {
		this.services=serv;
	}
	public String getExpertise() {
		return expertise;
	}
	
	public void setExpertise(String exp) {
		this.expertise=exp;
	}
	public POJO.Courses_Nested getCourses() {
		return courses;
	}
	
	public void setCourses(POJO.Courses_Nested cour) {
		this.courses=cour;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	
	public void setLinkedIn(String link) {
		this.linkedIn=link;
	}
	

}
