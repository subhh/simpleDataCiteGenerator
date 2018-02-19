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
public class TitleTypeField extends AbstractField {
    
    @Override
    public String getName() {
        return("titleType");
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        boolean b = false;
        
        if (value.equals("AlternativeTitle")) {
            b = true;
        }
        if (value.equals("Subtitle")) {
            b = true;
        }
        if (value.equals("TranslatedTitle")) {
            b = true;
        }
        if (value.equals("Other")) {
            b = true;
        }
        
        if (b == false) { 
            r = r.concat(this.getName() + ": Wrong vaule.\n");
        }
        
        return r;
    }
    
}
