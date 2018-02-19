/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.net.URI;

/**
 *
 * @author jfmaas
 */
public abstract class AbstractURIField {
    
    abstract public String validate();
    abstract public String getName();
    
    URI value;
    
    public URI getURI() {
        return this.value;
    };
    
    public void setURI(URI s) {
        this.value = s;
    };
}
