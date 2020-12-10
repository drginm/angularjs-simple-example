package co.dlighthouse.aem.angularjs.simpleexample.core.models;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;

import com.adobe.cq.export.json.ComponentExporter;
import org.apache.sling.models.annotations.Exporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {ComponentExporter.class},
        resourceType = InterestingContentModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class InterestingContentModel implements ComponentExporter {
    protected static final String RESOURCE_TYPE = "angularjs-simple-example/components/interesting-content";

    @ValueMapValue(injectionStrategy=InjectionStrategy.OPTIONAL)
    protected String url;

    @SlingObject
    private ResourceResolver resourceResolver;

    private List<Page> interestingPages;

    @PostConstruct
    protected void init() {
        if(url != null) {
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

            Page parentPage = pageManager.getPage(url);

            if(parentPage != null) {
                Iterator<Page> iterator = parentPage.listChildren();

                interestingPages = StreamSupport.stream(Spliterators
                                                            .spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
                                                            .collect(Collectors.toList());
            }
        }
    }

    public List<Page> getInterestingPages() {
        return interestingPages;
    }

    public String getUrl() {
        return url;
    }

    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}
