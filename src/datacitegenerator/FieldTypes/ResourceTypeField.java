/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jfmaas
 */
public class ResourceTypeField extends AbstractField {
    
    private String resourceTypeGeneral;
    private Map<String,String> h_types;
    
    // Constructor
    
    public ResourceTypeField () {
        resourceTypeGeneral = null;

        h_types = new HashMap<>();
        h_types.put("Audiovisual", "true");
        h_types.put("Collection", "true");
        h_types.put("DataPaper", "true");
        h_types.put("Dataset", "true");        
        h_types.put("Event", "true");        
        h_types.put("Image", "true");        
        h_types.put("InteractiveResource", "true");        
        h_types.put("Model", "true");        
        h_types.put("PhysicalObject", "true");        
        h_types.put("Service", "true");        
        h_types.put("Software", "true");        
        h_types.put("Sound", "true");        
        h_types.put("Text", "true");        
        h_types.put("Workflow", "true");        
        
        h_types.put("Other", "true");        
    }
    
    // getter setter adder
    
    public void setResourceTypeGeneral(String s) { this.resourceTypeGeneral = s;}
    public String getResourceTypeGeneral() { return this.resourceTypeGeneral; }
    
    
    @Override
    public String getName() {
        return("ResourceType");
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        if (resourceTypeGeneral == null) {
            r = r.concat(this.getName() + ": No resourceTypeGeneral defined.\n");
        } else {
            if (h_types.get(resourceTypeGeneral) == null) {
                r = r.concat(this.getName() + " " + resourceTypeGeneral + ": Wrong resourceTypeGeneral.\n");
            }
        }
        
        return r;
    }
    
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        field.appendChild(doc.createTextNode(this.getValue()));
        
        if (this.resourceTypeGeneral != null) {
            Attr attr = doc.createAttribute("resourceTypeGeneral");
            attr.setValue(resourceTypeGeneral);
            field.setAttributeNode(attr);
        }
                
        return field;
    }
}
