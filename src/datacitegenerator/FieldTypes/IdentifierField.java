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
public class IdentifierField extends AbstractField {
    
    String identifierType;
    
    public IdentifierField () {
        name = "Identifier";
        identifierType = null;
    }
    
    // getters,  setters and adders
    public String getIdentifierType() { return identifierType; }
    public void setIdentifierType(String s) { this.identifierType = s; }
    
    // abstract
    @Override
    public String validate() {
        String r = "";
        if (identifierType == null) {
            r = r.concat(this.getName() + ": No IdentifierFieldType defined.\n");
        }
        
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        return r;
    }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if (this.getIdentifierType() != null) {
            Attr attr = doc.createAttribute("identifierType");
            attr.setValue(this.getIdentifierType());
            field.setAttributeNode(attr);
        }
        
        field.appendChild(doc.createTextNode(this.getValue()));
                
        return field;
    }
}
