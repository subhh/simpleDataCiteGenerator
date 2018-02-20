/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jfmaas
 */
public class ContributorField extends AbstractField{
   
    Map<String,String> h_types;
    
    // mandatory single
    String contributorName;
    String contributorType;    
    
    // mandatory multiple
    
    // optional single
    String familyName;
    String givenName;
    
    // optional multiple
    private LinkedList<NameIdentifierField> nameIdentifiers;// 0..N
    private LinkedList<String> affiliations;// 0..N
    
    public ContributorField() {
        
        this.name = "Creator";
        
        this.contributorType = null;
        this.contributorName = null;
        this.familyName = null;
        this.givenName = null;
        this.nameIdentifiers = new LinkedList<>();
        this.affiliations = new LinkedList<>();

        h_types = new HashMap<>();
        
        h_types.put("ContactPerson", "true");
        h_types.put("DataCollector", "true");
        h_types.put("DataCurator", "true");
        h_types.put("DataManager", "true");        
        h_types.put("Distributor", "true");        
        h_types.put("Editor", "true");        
        h_types.put("HostingInstitution", "true");        
        h_types.put("Producer", "true");        
        h_types.put("ProjectLeader", "true");        
        h_types.put("ProjectManager", "true");        
        h_types.put("ProjectMember", "true");        
        h_types.put("RegistrationAgency", "true");        
        h_types.put("RegistrationAuthority", "true");        
        h_types.put("RelatedPerson", "true");        
        h_types.put("Researcher", "true");        
        h_types.put("ResearchGroup", "true");        
        h_types.put("RightsHolder", "true");        
        h_types.put("Sponsor", "true");        
        h_types.put("Supervisor", "true");        
        h_types.put("WorkPackageLeader", "true");        
        
        h_types.put("Other", "true");        
    }

    // getter, setter, adder
    
    public void setContributorType(String s) { this.contributorType = s; }
    public String getContributorType() { return this.contributorType; }
    
    public void setContributorName(String s) { this.contributorName = s; }
    public String getContributorName() { return this.contributorName; }
    
    public void setGivenName(String s) { this.givenName = s; }
    public String getGivenName() { return this.givenName; }

    public void setFamilyName(String s) { this.familyName = s; }
    public String getFamilyName() { return this.familyName; }    
    
    public void addNameIdentifier(NameIdentifierField s) { this.nameIdentifiers.add(s); }
    public LinkedList<NameIdentifierField> getNameIdentifiers() { return this.nameIdentifiers; } 

    public void addAffiliation(String s) { this.affiliations.add(s); }
    public LinkedList<String> getAffiliations() { return this.affiliations; } 
    
    // abstract
    
    @Override
    public String validate() {
        String r = "";
        //if (value == null) {
        //    r = r.concat(this.getName() + ": No value defined.\n");
        //}
        if (this.contributorType == null) {
            r = r.concat(this.getName() + ": contributorType not defined.\n");
        }
        
        if (this.contributorName == null) {
            r = r.concat(this.getName() + ": contributorName not defined.\n");
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
        
        //  contributorType (1)
        if (this.contributorType != null) {
            Attr attr = doc.createAttribute("contributorType");
            attr.setValue(contributorType);
            field.setAttributeNode(attr);
            // field nameType (0..1)
        }
        
        // contributorName
        if (this.contributorType != null) {
            Element n = doc.createElement("contributorName");
            n.appendChild(doc.createTextNode(this.contributorName));   
        }

        // givenName
        if (this.givenName != null) {
            Element n = doc.createElement("givenName");
            n.appendChild(doc.createTextNode(this.givenName));   
        }

        // familyName
        if (this.familyName != null) {
            Element n = doc.createElement("familyName");
            n.appendChild(doc.createTextNode(this.familyName));   
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
