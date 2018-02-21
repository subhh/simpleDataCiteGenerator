/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.util.Iterator;
import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jfmaas
 */
public class DataciteToolkit extends DataciteField {

    public static String validateList(LinkedList <? extends DataciteField> list) {
        String r = "";
        if (list.size() > 0) {
            for (DataciteField f : list) {
                r = r.concat(f.validate());
            }
        }
        return r;
    }
    
    public static Element createXMLFromList(Element e, Document doc, LinkedList <? extends DataciteField> list) {
        list.forEach((f) -> {
            e.appendChild(f.createXML(doc));
        }); 
        return e;
    }
}