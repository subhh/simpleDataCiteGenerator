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
public class PublicationYearField extends DataciteField {
    
    public PublicationYearField () {
        name = "publicationYear";
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        } else {
            if (! value.matches("\\d\\d\\d\\d")) {
                r = r.concat(this.getName() + ": Wrong value format.\n");
            }
        }
        
        return r;
    }
}
