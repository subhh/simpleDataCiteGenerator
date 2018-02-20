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
public class AlternateIdentifierField extends AbstractField {
    
    public AlternateIdentifierField () {
        this.name = "AlternateIdentifier";
    }
    private String alternateIdentifierType = null;
    
    public void setAlternateIdentifierType (String s) { this.alternateIdentifierType = s;};
    public String getAlternateIdentifierType () { return this.alternateIdentifierType;};
    
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        if (alternateIdentifierType == null) {
            r = r.concat(this.getName() + ": No alternateIdentifierType defined.\n");
        }
        
        return r;
    }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if (this.getAlternateIdentifierType() != null) {
            Attr attr = doc.createAttribute("alternateIdentifierType");
            attr.setValue(this.getAlternateIdentifierType());
            field.setAttributeNode(attr);
        }
        
        field.appendChild(doc.createTextNode(this.getValue()));
                
        return field;
    }
    
}
