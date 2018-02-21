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
public class DescriptionField extends DataciteField {
    
    private String descriptionType;
    private String language;
    
    public DescriptionField() {
        this.name = "description";
        this.language = "de-DE";
        
        this.descriptionType = null;
    }
    
    public void setDescriptionType (String s) { this.descriptionType = s; }
    private String getDescriptionType () { return this.descriptionType; }
    
    public void setLanguage (String s) { this.language = s; }
    private String getLanguage () { return this.language; }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if (this.language != null) {
            Attr attr = doc.createAttribute("xml:lang");
            attr.setValue(this.language);
            field.appendChild(attr);
        }

        if (this.descriptionType != null) {
            Attr attr = doc.createAttribute("descriptionType");
            attr.setValue(this.descriptionType);
            field.appendChild(attr);
        }
        
        if(this.getValue() != null) {
            field.appendChild(doc.createTextNode(this.getValue()));
        }
        
        return field;
    }
    
}
