/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

/**
 *
 * @author jfmaas
 */
public class IdentifierField extends AbstractField {
    
    IdentifierTypeField i = null;
    
    // getters,  setters and adders
    public IdentifierTypeField getIdentifierType() { return i; }
    public void setIdentifierType(IdentifierTypeField s) { this.i = s; }
    
    // abstract
    @Override
    public String getName() {
        return("Identifier");
    }
    
    @Override
    public String validate() {
        String r = "";
        if (i == null) {
            r = r.concat(this.getName() + ": No IdentifierFieldType defined.\n");
        } else {
            r = r.concat(i.validate());
        }
        
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        return r;
    }
}
