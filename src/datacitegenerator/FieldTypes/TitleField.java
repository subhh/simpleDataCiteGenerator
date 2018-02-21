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
public class TitleField extends DataciteField {
   
    // optional single
    private String titleLang;
    private String titleType;
    private final Map<String,String> h_types;
    
    // Constructor
    
    public TitleField () {
        this.name = "title";
        
        titleLang = "de-DE";
        titleType = null;
        
        h_types = new HashMap<>();
        h_types.put("AlternatveTitle", "true");
        h_types.put("SubTitle", "true");
        h_types.put("TranslatedTitle", "true");
        h_types.put("Other", "true");
    }
    
    // Methods
    
    // getter, setter, adder
    
    // ID 3.1
    public void setTitleType(String s) { this.titleType = s; }
    public String getTitleType() { return this.titleType; }
    
    public void setTitleLanguage(String s) { this.titleLang = s; }
    public String getTitleLanguage() { return this.titleLang; }
    
    // abstract
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        if ((this.titleType != null) && (h_types.get(this.titleType) == null)) {
            r = r.concat(this.getName() 
                    + " "
                    + this.getTitleType()
                    + ": Wrong title type.\n");
        }
        
        return r;
    }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        field.appendChild(doc.createTextNode(this.getValue()));
        
        if (this.titleLang != null) {
            Attr attr = doc.createAttribute("xml:lang");
            attr.setValue(titleLang);
            field.setAttributeNode(attr);
        }
        
        if (this.titleType != null) {
            Attr attr = doc.createAttribute("titleType");
            attr.setValue(titleType);
            field.setAttributeNode(attr);
        }
                
        return field;
    }
}
