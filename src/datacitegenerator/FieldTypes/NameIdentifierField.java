/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.net.URI;
import java.util.LinkedList;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jfmaas
 */
public class NameIdentifierField extends AbstractField {
    
    // mandatory single
    private String nameIdentifierScheme = null;
        
    // mandatory multiple
    
    // optional single
    private URI schemeURI = null;
    
    // optional multiple

    // getter, setter, adder
    
    // ID 2.4.1
    public void setNameIdentifierScheme(String s) { this.nameIdentifierScheme = s; }
    public String getCreatorName() { return this.nameIdentifierScheme; }
    
    // ID 2.4.2
    public void setSchemeURI(URI s) { this.schemeURI = s; }
    public URI getSchemeURI() { return this.schemeURI; }
    
    // abstract
    @Override
    public String getName() {
        return("nameIdentifier");
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        if (nameIdentifierScheme == null) {
            r = r.concat(this.getName() + " " + this.value + ": No nameIdentifierScheme defined.\n");
        }
        
        return r;
    }
    
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if (nameIdentifierScheme != null) {
            Attr attr = doc.createAttribute("nameIdentifierScheme");
            attr.setValue(nameIdentifierScheme);
            field.setAttributeNode(attr);
        }
        
        if (schemeURI != null) {
            Attr attr = doc.createAttribute("schemeURI");
            attr.setValue(schemeURI.toString());
            field.setAttributeNode(attr);
        }
        
        field.appendChild(doc.createTextNode(this.getValue()));
                
        return field;
    }
}
