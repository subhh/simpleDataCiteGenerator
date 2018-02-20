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
public class DateField extends AbstractField {
    
    private String dateType = null;
    private String dateInformation = null;
    
    private Map<String,String> h_types;
    
    // mandatory single
    
    public String getDateType() { return dateType; }
    public void setDateType(String s) { this.dateType = s; }
    
    // optional single
    
    public String getDateInformation() { return dateInformation; }
    public void setDateInformation(String s) { this.dateInformation = s; }
    
    public DateField () {
        
        this.name = "Date";
        
        dateType = null;
        dateInformation = null;
        
        h_types = new HashMap<>();
        h_types.put("Accepted", "true");
        h_types.put("Available", "true");
        h_types.put("Copyrighted", "true");
        h_types.put("Collected", "true");        
        h_types.put("Created", "true");        
        h_types.put("Issued", "true");        
        h_types.put("Submitted", "true");        
        h_types.put("Updated", "true");        
        h_types.put("Valid", "true");        
        h_types.put("Other", "true");
        
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        if (dateType == null) {
            r = r.concat(this.getName() + ": No dateType defined.\n");
        } else {
            if (h_types.get(dateInformation) == null) {
                r = r.concat(this.getName() 
                        + " "
                        + dateInformation
                        + " "
                        + " : Wrong dateInformation.\n");
            }
        }
        
        return r;
    }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if (this.getDateType() != null) {
            Attr attr = doc.createAttribute("dateType");
            attr.setValue(this.getDateType());
            field.setAttributeNode(attr);
        }
        
        if (this.getDateInformation() != null) {
            Attr attr = doc.createAttribute("dateInformation");
            attr.setValue(this.getDateInformation());
            field.setAttributeNode(attr);
        }
        
        field.appendChild(doc.createTextNode(this.getValue()));
                
        return field;
    }
    
}
