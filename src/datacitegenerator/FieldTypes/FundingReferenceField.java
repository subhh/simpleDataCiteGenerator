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
public class FundingReferenceField extends DataciteField {
    
    // mandatory single
    FunderNameField funderName;
    
    // optional single
    FunderIdentifierField fid;
    AwardNumberField awardNumber;
    AwardTitleField awardTitle;
    
    public FundingReferenceField() {  
        this.name = "fundingReference";
        
        funderName = null;
        fid = null;
        awardNumber = null;
        awardTitle = null;
    }

    // getter, setter, adder
    
    public void setFunderName(FunderNameField s) { this.funderName = s; }
    public FunderNameField getFunderName() { return this.funderName; }
    
    public void setFunderIdentifier(FunderIdentifierField s) { this.fid = s; }
    public FunderIdentifierField getNameType() { return this.fid; }
    
    public void setGivenName(AwardNumberField s) { this.awardNumber = s; }
    public AwardNumberField getGivenName() { return this.awardNumber; }
    
    public void setFamilyName(AwardTitleField s) { this.awardTitle = s; }
    public AwardTitleField getFamilyName() { return this.awardTitle; }    
    
    @Override
    public String validate() {
        String r = "";
 
        if (this.funderName == null) { r = r.concat(this.getName() + ": funderName not defined.\n"); }
        r = r.concat(fid.validate());
        r = r.concat(awardNumber.validate());
        
        return r;
    }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if (funderName != null) { field.appendChild(funderName.createXML(doc)); }
        if (fid != null) { field.appendChild(fid.createXML(doc)); }
        if (awardNumber != null) { field.appendChild(awardNumber.createXML(doc)); }
        if (awardTitle != null) { field.appendChild(awardTitle.createXML(doc)); }

        return field;
    }
    
}
