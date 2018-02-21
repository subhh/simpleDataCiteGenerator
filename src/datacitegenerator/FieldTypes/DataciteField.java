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
public class DataciteField {
    
    String value;
    String name;
    
    // Methods
    
    public String getName() {
        return(this.name);
    }
    
    public String getValue() {
        return this.value;
    };
    
    public void setValue(String s) {
        this.value = s;
    };
    
    public String validate() {
        String r = "";
        if (this.getValue() == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        return r;
    }
    
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if(this.getValue() != null) {
            field.appendChild(doc.createTextNode(this.getValue()));
        }
                
        return field;
    }
}
