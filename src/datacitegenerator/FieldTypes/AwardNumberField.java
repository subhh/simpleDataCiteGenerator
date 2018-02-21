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
public class AwardNumberField extends DataciteField {
    
    URI awardURI;
    
    public AwardNumberField() {
        this.name = "awardNumber";
        
        this.awardURI = null;
    }
    
    public void setAwardURI (URI s) { this.awardURI = s;}
    public URI getAwardURI() { return this.awardURI;}
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());

        if(this.getValue() != null) {
            field.appendChild(doc.createTextNode(this.getValue()));
        }

        if (this.getAwardURI() != null) {
            Attr attr = doc.createAttribute("awardURI");
            attr.setValue(this.getAwardURI().toString());
            field.setAttributeNode(attr);
        }
                
        return field;
    }
}
