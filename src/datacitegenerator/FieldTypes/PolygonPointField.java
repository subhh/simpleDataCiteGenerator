/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jfmaas
 */
public class PolygonPointField extends DataciteField {
    
    private String pointLatitude;
    private String pointLongitude;
    
    public PolygonPointField() {
        this.name = "polygonPoint";
     
        pointLatitude = null;
        pointLongitude = null;
    }
    
    public void setPointLatitude (String s) { this.pointLatitude = s; }
    public String getPointLatitude () { return this.pointLatitude;}
    
    public void setPointLongitude (String s) { this.pointLongitude = s; }
    public String getPointLongitude () { return this.pointLongitude;}
    
    @Override
    public String validate() {
        String r = "";
        
        if (this.pointLatitude == null)  { r = r.concat(this.getName() + ": No pointLatitude defined!");}
        if (this.pointLongitude == null) { r = r.concat(this.getName() + ": No pointLongitude defined!");}
        
        return r;
    }
        
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if (this.pointLatitude == null) {            
            Element n = doc.createElement("pointLatitude");
            n.appendChild(doc.createTextNode(this.pointLatitude));
            field.appendChild(n);
        }
        
        if (this.pointLongitude == null) {            
            Element n = doc.createElement("pointLongitude");
            n.appendChild(doc.createTextNode(this.pointLongitude));
            field.appendChild(n);
        }
                
        return field;
    }
}
