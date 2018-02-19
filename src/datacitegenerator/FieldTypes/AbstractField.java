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
public abstract class AbstractField {
    
    abstract public String validate();
    abstract public String getName();
    
    String value;
    
    public String getValue() {
        return this.value;
    };
    
    public void setValue(String s) {
        this.value = s;
    };
}
