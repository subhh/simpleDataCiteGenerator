/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.util.LinkedList;

/**
 *
 * @author jfmaas
 */
public class NameTypeField extends AbstractField {
    
    public NameTypeField () {
        CreatorNameField creatorName = null;
        givenName = null; 
        familyName = null;
        nameIdentifiers = new LinkedList<>();
        affiliations = new LinkedList<>();// 0..N
    }
    
    // mandatory single
    private CreatorNameField creatorName;
        
    // mandatory multiple
    
    // optional single
    private GivenNameField givenName; // 0..1
    private FamilyNameField familyName;// 0..1
    
    // optional multiple
    private LinkedList<NameIdentifierField> nameIdentifiers;// 0..N
    private LinkedList<AffiliationField> affiliations;// 0..N

    // getter, setter, adder
    
    // ID 2.1
    public void setCreatorName(CreatorNameField s) { this.creatorName = s; }
    public CreatorNameField getCreatorName() { return this.creatorName; }
    
    // ID 2.2
    public void setGivenName(GivenNameField s) { this.givenName = s; }
    public GivenNameField getGivenName() { return this.givenName; }

    // ID 2.3
    public void setFamilyName(FamilyNameField s) { this.familyName = s; }
    public FamilyNameField getFamilyName() { return this.familyName; }    
    
    // ID 2.4
    public void addNameIdentifier(NameIdentifierField s) { this.nameIdentifiers.add(s); }
    public LinkedList<NameIdentifierField> getNameIdentifiers() { return this.nameIdentifiers; } 

    // ID 2.5
    public void addAffiliation(AffiliationField s) { this.affiliations.add(s); }
    public LinkedList<AffiliationField> getAffiliations() { return this.affiliations; } 
    
    // abstract
    
    @Override
    public String getName() {
        return("nameType");
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        return r;
    }
    
}
