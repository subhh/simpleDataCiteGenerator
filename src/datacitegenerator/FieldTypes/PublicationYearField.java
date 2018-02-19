/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jfmaas
 */
public class PublicationYearField extends AbstractField {
    
    @Override
    public String getName() {
        return("PublicationYear");
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        } else {
            if (! value.matches("\\d\\d\\d\\d")) {
                r = r.concat(this.getName() + ": Wrong value format.\n");
            }
        }
        
        return r;
    }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        field.appendChild(doc.createTextNode(this.getValue()));        
                
        return field;
    }
    
}
