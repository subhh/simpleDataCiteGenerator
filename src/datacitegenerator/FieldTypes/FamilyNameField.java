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
public class FamilyNameField extends AbstractField {
    
    @Override
    public String getName() {
        return("familyName");
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        return r;
    }
    
}
