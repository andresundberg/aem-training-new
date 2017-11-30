package my.aem.project.aem_training_new.core.models;

import my.aem.project.aem_training_new.core.models.Training;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
	
	private String value;
	private List<Training> trainings;
	private List<String> months;
	

	
	public Collection<Training> getTrainings() {
		if(trainings == null) {
			trainings = new ArrayList<Training>();
			createTraining();
		}
		return trainings;
	}
	
	public void createTraining() {
	Iterator<Page> childPages = currentPage.listChildren();
	while(childPages.hasNext()) {
		Page child = childPages.next();
		Training training = new Training(getValue("jcr:title", child), getValue("jcr:type", child), getValue("jcr:date", child), getValue("jcr:location", child), getValue("jcr:description", child));
		trainings.add(training);
		}
	}
	
	public String getValue(String name, Page page) {
		Resource resource = page.adaptTo(Resource.class);
		ValueMap properties = resource.getChild("jcr:content/root/trainingdetails").getValueMap();
		value = properties.get(name, String.class);
		return value;
	}
	
	public List<String> getMonths() {
		months = new ArrayList<String>();
		
		String month1 = "April";
		String month2 = "May";
		String month3 = "June";
		
		months.add(month1);
		months.add(month2);
		months.add(month3);
		
		return months;
	}
}
