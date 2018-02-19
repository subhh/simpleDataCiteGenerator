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
public class TitleField extends AbstractField {
    
    // mandatory single
    
    // mandatory multiple
    
    // optional single
    private TitleTypeField titleType;
    
    // optional multiple
    
    // Methods
    
    // getter, setter, adder
    
    // ID 3.1
    public void setTitleType(TitleTypeField s) { this.titleType = s; }
    public TitleTypeField getIdentifier() { return this.titleType; }
    
    // abstract
    
    @Override
    public String getName() {
        return("Title");
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        if (titleType != null) {
            r = r.concat(titleType.validate());
        } 
        
        return r;
    }
}
