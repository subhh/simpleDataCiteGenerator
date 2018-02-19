/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.net.URI;
import java.util.HashMap;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jfmaas
 */
public class SubjectField extends AbstractField {
    
    private String subjectLang;
    private String subjectScheme;
    private URI schemeURI;
    private URI subjectURI;
    
    
    public SubjectField () {
            subjectLang = "de-DE";
            subjectScheme = null;
            schemeURI = null;
            subjectURI = null;
        }
    
    // Methods
    
    // getter, setter, adder
    
    public void setSubjectLang(String s) { this.subjectLang = s; }
    public String getSubjectLang() { return this.subjectLang; }
    
    public void setSubjectScheme(String s) { this.subjectScheme = s; }
    public String getSubjectScheme() { return this.subjectScheme; }
    
    public void setSchemeURI(URI s) { this.schemeURI = s; }
    public URI getSchemeURI() { return this.schemeURI; }
    
    public void setValueURI(URI s) { this.subjectURI = s; }
    public URI getValueURI() { return this.subjectURI; }
    
    @Override
    public String getName() {
        return("Subject");
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        return r;
    }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        field.appendChild(doc.createTextNode(this.getValue()));
        
        if (this.subjectLang != null) {
            Attr attr = doc.createAttribute("xml:lang");
            attr.setValue(subjectLang);
            field.setAttributeNode(attr);
        }
        
        if (this.schemeURI != null) {
            Attr attr = doc.createAttribute("schemeURI");
            attr.setValue(schemeURI.toString());
            field.setAttributeNode(attr);
        }
        
        if (this.subjectScheme != null) {
            Attr attr = doc.createAttribute("subjectScheme");
            attr.setValue(subjectScheme);
            field.setAttributeNode(attr);
        }

        if (this.subjectURI != null) {
            Attr attr = doc.createAttribute("subjectURI");
            attr.setValue(subjectURI.toString());
            field.setAttributeNode(attr);
        }
        
        return field;
    }
    
}
