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
public class CreatorNameField extends AbstractField {
    
    // mandatory single
        
    // mandatory multiple
    
    // optional single
    private NameTypeField nameType = null; // 0..1
    
    // optional multiple
    
    // getter, setter, adder
    
    // ID 2.1
    public void setNameType(NameTypeField s) { this.nameType = s; }
    public NameTypeField getNameType() { return this.nameType; }
    
    // abstract
    
    @Override
    public String getName() {
        return("creatorName");
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        if (this.nameType != null) {
            r = r.concat(this.nameType.validate());
        }
        
        return r;
    }
    
}
