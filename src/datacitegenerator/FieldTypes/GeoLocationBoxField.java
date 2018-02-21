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
public class GeoLocationBoxField extends DataciteField {
    
    private String westBoundLongitude;
    private String eastBoundLongitude;
    private String southBoundLatitude;
    private String northBoundLatitude;
    
    public GeoLocationBoxField() {
        this.name = "geoLocationBox";
     
        westBoundLongitude = null;
        eastBoundLongitude = null;    
        southBoundLatitude = null;
        northBoundLatitude = null;
    }
    
    public void setWestBoundLongitude (String s) { this.westBoundLongitude = s; }
    public String getWestBoundLongitude () { return this.westBoundLongitude;}
    
    public void setEastBoundLongitude (String s) { this.eastBoundLongitude = s; }
    public String getEastBoundLongitude () { return this.eastBoundLongitude;}
    
    public void setSouthBoundLatitude (String s) { this.southBoundLatitude = s; }
    public String getSouthBoundLatitude () { return this.southBoundLatitude;}
    
    public void setNorthBoundLatitude (String s) { this.northBoundLatitude = s; }
    public String getNorthBoundLatitude () { return this.northBoundLatitude;}
    
    @Override
    public String validate() {
        String r = "";
        
        if (this.westBoundLongitude == null) { r = r.concat(this.getName() + ": No westBoundLongitude defined!");}
        if (this.eastBoundLongitude == null) { r = r.concat(this.getName() + ": No eastBoundLongitude defined!");}
        if (this.southBoundLatitude == null) { r = r.concat(this.getName() + ": No southBoundLatitude defined!");}
        if (this.northBoundLatitude == null) { r = r.concat(this.getName() + ": No northBoundLatitude defined!");}
        
        return r;
    }
        
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if (this.westBoundLongitude == null) {            
            Element n = doc.createElement("westBoundLongitude");
            n.appendChild(doc.createTextNode(this.westBoundLongitude));
            field.appendChild(n);
        }
        
        if (this.eastBoundLongitude == null) {            
            Element n = doc.createElement("eastBoundLongitude");
            n.appendChild(doc.createTextNode(this.eastBoundLongitude));
            field.appendChild(n);
        }
        
        if (this.southBoundLatitude == null) {            
            Element n = doc.createElement("southBoundLatitude");
            n.appendChild(doc.createTextNode(this.southBoundLatitude));
            field.appendChild(n);
        }
        
        if (this.northBoundLatitude == null) {            
            Element n = doc.createElement("northBoundLatitude");
            n.appendChild(doc.createTextNode(this.northBoundLatitude));
            field.appendChild(n);
        }
                
        return field;
    }
}
