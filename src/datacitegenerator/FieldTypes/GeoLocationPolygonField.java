/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jfmaas
 */
public class GeoLocationPolygonField extends DataciteField {
    
    private final LinkedList<PolygonPointField> fs;
    
    public GeoLocationPolygonField() {
        this.name = "geoLocationPolygon";
        
        fs = new LinkedList<>();
    }
    
    public void addPolygonPoint (PolygonPointField s) { this.fs.add(s); }
    public LinkedList<PolygonPointField> getPolygonPoints () { return this.fs;}
    
    @Override
    public String validate() {
        String r = "";
        if (this.fs.size() < 4) {
            r = r.concat(this.getName() + ": Less than 4 polygonPoints defined.\n");
        }
        r = r.concat(DataciteToolkit.validateList(fs));
        
        return r;
    }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());

        field = DataciteToolkit.createXMLFromList(field, doc, this.fs);
                
        return field;
    }
    
}
