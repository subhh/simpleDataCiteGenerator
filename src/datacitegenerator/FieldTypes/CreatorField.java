/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.util.Iterator;
import java.util.LinkedList;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jfmaas
 */
public class CreatorField extends AbstractField {
    
    public CreatorField() {  
        this.name = "Creator";
        this.creatorName = null;
        this.givenName = null;
        this.familyName = null;
        this.nameIdentifiers = new LinkedList<>();// 0..N
        this.affiliations = new LinkedList<>();// 0..N
    }
    
    // mandatory single
    String creatorName;
        
    // mandatory multiple
    
    // optional single
    String givenName = null; // 0..1
    String familyName = null;// 0..1
    String nameType = null;
    
    // optional multiple
    private LinkedList<NameIdentifierField> nameIdentifiers;// 0..N
    private LinkedList<String> affiliations;// 0..N

    // getter, setter, adder
    
    // ID 2.1
    public void setCreatorName(String s) { this.creatorName = s; }
    public String getCreatorName() { return this.creatorName; }
    
    // ID 2.1.1
    public void setNameType(String s) { this.nameType = s; }
    public String getNameType() { return this.nameType; }

    
    // ID 2.2
    public void setGivenName(String s) { this.givenName = s; }
    public String getGivenName() { return this.givenName; }

    // ID 2.3
    public void setFamilyName(String s) { this.familyName = s; }
    public String getFamilyName() { return this.familyName; }    
    
    // ID 2.4
    public void addNameIdentifier(NameIdentifierField s) { this.nameIdentifiers.add(s); }
    public LinkedList<NameIdentifierField> getNameIdentifiers() { return this.nameIdentifiers; } 

    // ID 2.5
    public void addAffiliation(String s) { this.affiliations.add(s); }
    public LinkedList<String> getAffiliations() { return this.affiliations; } 
    
    // abstract
    
    @Override
    public String validate() {
        String r = "";
        //if (value == null) {
        //    r = r.concat(this.getName() + ": No value defined.\n");
        //}
        if (this.creatorName == null) {
            r = r.concat(this.getName() + ": creatorName not defined.\n");
        }
        
        if (this.creatorName == null) {
            r = r.concat(this.getName() + ": creatorName not defined.\n");
        }
        
        if (this.nameIdentifiers.size() > 0) {
            Iterator it = this.nameIdentifiers.iterator();
            while (it.hasNext()) {
                NameIdentifierField n = (NameIdentifierField) it.next();
                r = r.concat(n.validate());
            }
        }
        
        return r;
    }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        // field creatorName (1)
        
        if (this.creatorName != null) {
            Element n = doc.createElement("creatorName");
            n.appendChild(doc.createTextNode(this.creatorName));
            // field nameType (0..1)
            if (this.nameType != null) {
                Attr attr = doc.createAttribute("nameType");
                attr.setValue(nameType);
                n.appendChild(attr);
            }
            field.appendChild(n);
        }
        
        // field givenName (0..1)
        if (this.givenName != null) {
            Element n = doc.createElement("givenName");
            n.appendChild(doc.createTextNode(this.givenName));
            field.appendChild(n);
        }
        
        // field familyName (0..1)
        if (this.familyName != null) {
            Element n = doc.createElement("familyName");
            n.appendChild(doc.createTextNode(this.familyName));
            field.appendChild(n);
        }
        
        // field nameIdentifiers (0..n)
        if (this.nameIdentifiers.size() > 0) {
            Iterator it = this.nameIdentifiers.iterator();
            while (it.hasNext()) {
                NameIdentifierField n = (NameIdentifierField) it.next();
                field.appendChild(n.createXML(doc));
            }
        }
        
        // field affiliation (0..n)
        if (this.affiliations.size() > 0) {
            Iterator it = this.affiliations.iterator();
            while (it.hasNext()) {
                String n = (String) it.next();
                Element e = doc.createElement("affiliation");
                e.appendChild(doc.createTextNode(n));
                field.appendChild(e);
            }
        }
                
        return field;
    }
    
}
