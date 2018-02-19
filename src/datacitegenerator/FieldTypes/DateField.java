/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

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
    
    public String getDateType() { return dateType; }
    public void setDateType(String s) { this.dateType = s; }
    
    public String getDateInformation() { return dateInformation; }
    public void setDateInformation(String s) { this.dateInformation = s; }
    
    @Override
    public String getName() {
        return("Date");
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        if (dateType == null) {
            r = r.concat(this.getName() + ": No dateType defined.\n");
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
