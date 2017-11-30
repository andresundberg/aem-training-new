package my.aem.project.aem_training_new.core.models;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class, resourceType = "/training/components/content/traininglist")
@Exporter(name = "jackson", extensions = "json")
public class TestModel {
	
	@ScriptVariable
    private Page currentPage;
	
	private ResourceResolver resolver;
	
	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private String message;
	
	private String title;
	private String title2;
	private String title3;
	
	private String trainingTitle;
	
	private List<Training> trainings;
	
	@PostConstruct
	private void initModel(){
		message = "Det andra testmedelandet " + currentPage.getPath();
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getTitle() {
		Iterator<Page> childPages = currentPage.listChildren();
		title = childPages.next().getTitle();
		return title;
	}
	
	public String getTitle3() {
		Iterator<Page> childPages = currentPage.listChildren();
		Page childpage = childPages.next();
		
		Resource r = childpage.adaptTo(Resource.class);

		ValueMap properties = r.getChild("jcr:content/root/trainingdetails").getValueMap();
		title3 = properties.get("jcr:title", String.class);

		return title3;
	}
	
//	public String getTitle3() {
//		Resource resource = resolver.resolve(currentPage.getPath());
//		Page page = resource.adaptTo(Page.class);
//		title3 = page.getTitle();
//		return title3;
//	}
	
	public String getTitle2() {
		Resource resource = currentPage.adaptTo(Resource.class);
		ValueMap properties = resource.getChild("training-details/jcr:content/root/trainingdetails").getValueMap();
		title2 = properties.get("jcr:title", String.class);
		return title2;
	}
	 
//	public String getTrainingTitle() {
//		
//		Resource resource = resolver.getResource(currentPage.getPath() + "/training-details");
//		
//		trainingTitle = resource.getValueMap().get("jcr:title", String.class);
//		return trainingTitle;
//	}
	
}
