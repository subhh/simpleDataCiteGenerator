/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.util.LinkedList;

/**
 *
 * @author jfmaas
 */
public class NameIdentifierField extends AbstractField {
    
    // mandatory single
    private NameIdentifierSchemeField nameIdentifierScheme = null;
        
    // mandatory multiple
    
    // optional single
    private SchemeURIField schemeURI = null;
    
    // optional multiple

    // getter, setter, adder
    
    // ID 2.4.1
    public void setNameIdentifierScheme(NameIdentifierSchemeField s) { this.nameIdentifierScheme = s; }
    public NameIdentifierSchemeField getCreatorName() { return this.nameIdentifierScheme; }
    
    // ID 2.4.2
    public void setSchemeURI(SchemeURIField s) { this.schemeURI = s; }
    public SchemeURIField getSchemeURI() { return this.schemeURI; }
    
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
            r = r.concat(this.getName() + ": No nameIdentifierScheme defined.\n");
        } else {
            r = r.concat(this.nameIdentifierScheme.validate());
        }
        
        if (schemeURI != null) {
            r = r.concat(this.schemeURI.validate());
        }
        
        return r;
    }
    
}
