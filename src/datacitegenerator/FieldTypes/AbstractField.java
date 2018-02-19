/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
    
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        field.appendChild(doc.createTextNode(this.getValue()));
                
        return field;
    }
}
