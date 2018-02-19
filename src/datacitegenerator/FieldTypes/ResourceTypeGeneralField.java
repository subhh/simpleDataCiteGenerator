/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.util.LinkedList;

/**
 *
 * @author jfmaas
 */
public class ResourceTypeGeneralField extends AbstractField {
   
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
        
        return r;
    }
    
    private LinkedList <String> allowedTypes = new LinkedList<String>();
    
    public ResourceTypeGeneralField () {
        allowedTypes.add("Audiovisual");
        allowedTypes.add("Collection");
        allowedTypes.add("DataPaper");
        allowedTypes.add("Dataset");
        allowedTypes.add("Event");
        allowedTypes.add("Image");
        allowedTypes.add("InteractiveResource");
        allowedTypes.add("Model");
        allowedTypes.add("PhysicalObject");
        allowedTypes.add("Service");
        allowedTypes.add("Software");
        allowedTypes.add("Sound");
        allowedTypes.add("Text");
        allowedTypes.add("Workflow");
        allowedTypes.add("Other");
    }
    
}
