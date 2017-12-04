package my.aem.project.aem_training_new.core.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Training {
	private String title;
	private String type;
	private Date date;
	private String location;
	private String description;
	private String month;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
	
	public Training(String title, String type, Date date, String location, String description) {
		this.title = title;
		this.type = type;
		this.date = date;
		this.location = location;
		this.description = description;
		this.month = sdf.format(date);
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public Date getDate() {
		return date;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}
	
	public String getMonth() {
		return month;
	}
	
}
