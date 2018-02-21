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
public class GeoLocationField extends DataciteField {
    
    private GeoLocationPointField geolocationPoint;
    private GeoLocationBoxField geolocationBox;
    private GeoLocationPlaceField geolocationPlace;
    private final LinkedList<GeoLocationPolygonField> geolocationPolygons;
    
    public GeoLocationField() {
        this.name = "geoLocation";
        
        geolocationPoint = null;
        geolocationBox = null;
        geolocationPlace = null;
        geolocationPolygons = new LinkedList<>();
    }
    
    public void addGeolocationPoint (GeoLocationPointField s) { this.geolocationPoint = s; }
    public GeoLocationPointField getGeoloactionPoint () { return this.geolocationPoint;}
    
    public void addGeolocationBox (GeoLocationBoxField s) { this.geolocationBox = s; }
    public GeoLocationBoxField getGeolocationBox () { return this.geolocationBox;}
    
    public void addGeolocationPlace (GeoLocationPlaceField s) { this.geolocationPlace = s; }
    public GeoLocationPlaceField getGeoloactionPlace () { return this.geolocationPlace;}
    
    public void addGeolocationPolygon (GeoLocationPolygonField s) { this.geolocationPolygons.add(s); }
    public LinkedList<GeoLocationPolygonField> getGeoloactionPolygon () { return this.geolocationPolygons; }
    
    @Override
    public String validate() {
        String r = "";
        
        if (geolocationPlace != null) { r = r.concat(geolocationPlace.validate());}
        if (geolocationPoint != null) { r = r.concat(geolocationPoint.validate());}
        if (geolocationBox != null) { r = r.concat(geolocationBox.validate());}
        if (geolocationPolygons.size() > 1) { r = r.concat(DataciteToolkit.validateList(geolocationPolygons));}
        
        return r;
    }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if (geolocationPlace != null) { field.appendChild(geolocationPlace.createXML(doc));}
        if (geolocationPoint != null) { field.appendChild(geolocationPoint.createXML(doc));}
        if (geolocationBox != null) { field.appendChild(geolocationBox.createXML(doc));}
        field = DataciteToolkit.createXMLFromList(field, doc, geolocationPolygons);
                
        return field;
    }
        
}
