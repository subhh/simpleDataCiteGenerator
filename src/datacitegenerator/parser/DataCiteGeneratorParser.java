/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.parser;

import java.io.File;

/**
 *
 * @author jfmaas
 */
public abstract class DataCiteGeneratorParser {
    File inputfile;
    File outputfile;
    
    public void setInputfile(String inputfile) {
        this.inputfile = new File(inputfile);
    }
    
    public void setOutputfile (String outputfile) {
        this.outputfile = new File(outputfile);
    }
    
    public void setInputfile(File inputfile) {
        this.inputfile = inputfile;
    }
    
    public void setOutputfile (File outputfile) {
        this.outputfile = outputfile;
    }
    
    public abstract void parse();
    
    public abstract String validate();
}
