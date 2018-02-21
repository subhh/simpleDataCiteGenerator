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
public class FunderIdentifierField extends DataciteField {
    
    private String funderIdentifierType;
    
    private final Map<String,String> h_types;
    
    public FunderIdentifierField() {
        this.name = "funderIdentifier";
        
        funderIdentifierType = null;
        
        h_types = new HashMap<>();
        
        h_types.put("ISNI","true");
        h_types.put("Grid","true");
        h_types.put("Crossref Funder","true");
        h_types.put("Other","true");
    }
    
    public void setFunderIdentifierType (String s) { this.funderIdentifierType = s;}
    public String getFunderIdentifierType() { return this.funderIdentifierType;}
    
    @Override
    public String validate() {
        String r = "";
        if (this.getValue() == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        if(h_types.get(this.getFunderIdentifierType()) == null) {
            r = r.concat(this.getName() 
                    + " "
                    + this.getFunderIdentifierType()
                    + ": Wrong value for funderIdentifierType.\n");
        }
        
        return r;
    }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if(this.getValue() != null) {
            field.appendChild(doc.createTextNode(this.getValue()));
        }
        
        if (this.getFunderIdentifierType() != null) {
            Attr attr = doc.createAttribute("funderIdentifierType");
            attr.setValue(this.getFunderIdentifierType());
            field.setAttributeNode(attr);
        }
                
        return field;
    }
}
