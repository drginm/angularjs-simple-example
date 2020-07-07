package co.dlighthouse.aem.angularjs.simpleexample.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import org.apache.sling.api.SlingHttpServletRequest;
import com.adobe.cq.export.json.ComponentExporter;
import org.apache.sling.models.annotations.Exporter;
import com.adobe.cq.export.json.ExporterConstants;


@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {ComponentExporter.class},
        resourceType = BasicModel.BASIC_COMPONENT_RESOURCETYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class BasicModel implements ComponentExporter {
    protected static final String BASIC_COMPONENT_RESOURCETYPE = "angularjs-simple-example/components/basic-component";
    @ValueMapValue(injectionStrategy=InjectionStrategy.OPTIONAL)
    protected String text;
    @ValueMapValue(injectionStrategy=InjectionStrategy.OPTIONAL)
    protected Boolean checkbox;

    public String getText() {
        return text;
    }
    public Boolean isCheckbox() {
        return checkbox;
    }
    public String getExportedType() {
        return BASIC_COMPONENT_RESOURCETYPE;
    }
}
