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
public class LanguageField extends DataciteField {
    
    public LanguageField () {
        this.name="language";
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value != null) {        
            if (!value.matches("")) {
                r = r.concat(this.getName() 
                        + " "
                        + value
                        + " "
                        + ": Wrong language type.\n");
            }
        }
        
        return r;
    }
    
}
