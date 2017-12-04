package my.aem.project.aem_training_new.core.models;

import my.aem.project.aem_training_new.core.models.Training;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.time.temporal.TemporalAdjusters;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class, resourceType = "/training/components/content/traininglist")
@Exporter(name = "jackson", extensions = "json")
public class TrainingList {
	
	@ScriptVariable
	private Page currentPage;
	
	private static final String TITLE = "jcr:trainingTitle";
	private static final String TYPE = "jcr:trainingType";
	private static final String DATE = "jcr:trainingDate";
	private static final String LOCATION = "jcr:location";
	private static final String DESCRIPTION = "jcr:trainingDescription";
	
	private String path = "jcr:content/root/trainingdetails";
	private String value;
	private List<Training> trainings;
	private List<String> months;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private LocalDateTime currentDate;
	
	public Collection<Training> getTrainings() throws ParseException {
		if(trainings == null) {
			trainings = new ArrayList<Training>();
			createTraining();
		}
		return trainings;
	}
	
	public void createTraining() throws ParseException {
	Iterator<Page> childPages = currentPage.listChildren();
	while(childPages.hasNext()) {
		Page child = childPages.next();
		Date date = sdf.parse(getValue(DATE, child));
		Training training = new Training(getValue(TITLE, child), getValue(TYPE, child), date, getValue(LOCATION, child), getValue(DESCRIPTION, child));
		trainings.add(training);
		}
	}
	
	public String getValue(String name, Page page) {
		Resource resource = page.adaptTo(Resource.class);
		ValueMap properties = resource.getChild(path).getValueMap();
		value = properties.get(name, String.class);
		return value;
	}
	
	public List<String> getMonths() {
		LocalDate localDate;
		String date;
		String month;
		
		months = new ArrayList<String>();
		currentDate = LocalDateTime.now();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		date = dtf.format(currentDate);
		localDate = LocalDate.parse(date, dtf.ofPattern("yyyy/MM/dd"));
		
		
		for(int i = 0; i < 3; i++) {
			month = localDate.format(dtf.ofPattern("MMMM")).toString();
			months.add(month);
			localDate = localDate.with(TemporalAdjusters.firstDayOfNextMonth());
		}
		return months;
	}
}
