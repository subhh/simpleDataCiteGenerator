/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.net.URI;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jfmaas
 */
public class RightsField extends DataciteField {
    
    private URI rightsURI;
    
    public RightsField() {
        this.name = "rights";
        
        this.rightsURI = null;
    }
    
    public void setRightsURI (URI s) { this.rightsURI = s; }
    private URI getRightsURI () { return this.rightsURI; }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if(this.getValue() != null) {
            field.appendChild(doc.createTextNode(this.getValue()));
        }
        
        // field rightsURI (0..1)
        
        if (this.rightsURI != null) {
            Attr attr = doc.createAttribute("rightsURI");
            attr.setValue(rightsURI.toString());
            field.appendChild(attr);
        }
        
        return field;
    }
    
}
