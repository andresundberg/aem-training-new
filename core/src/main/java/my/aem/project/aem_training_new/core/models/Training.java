package my.aem.project.aem_training_new.core.models;

public class Training {
	private String title;
	private String type;
	private String date;
	private String location;
	private String description;
	
	public Training(String title, String type, String date, String location, String description) {
		this.title = title;
		this.type = type;
		this.date = date;
		this.location = location;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public String getDate() {
		return date;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}
	
}
