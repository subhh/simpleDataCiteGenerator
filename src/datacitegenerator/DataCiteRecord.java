/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator;

import datacitegenerator.FieldTypes.AlternateIdentifierField;
import datacitegenerator.FieldTypes.ContributorField;
import datacitegenerator.FieldTypes.TitleField;
import datacitegenerator.FieldTypes.CreatorField;
import datacitegenerator.FieldTypes.DateField;
import datacitegenerator.FieldTypes.PublicationYearField;
import datacitegenerator.FieldTypes.PublisherField;
import datacitegenerator.FieldTypes.IdentifierField;
import datacitegenerator.FieldTypes.LanguageField;
import datacitegenerator.FieldTypes.ResourceTypeField;
import datacitegenerator.FieldTypes.SubjectField;
import java.util.LinkedList;

/**
 * This class defines the schema for the Datacite metadata format records
 * 
 * @author jfmaas
 */
public class DataCiteRecord {
    
    //Constructor
    public DataCiteRecord () {
        this.id = null;
    }
    
    // DataCite Fields
    
    // mandatory single
    private IdentifierField id;
    private PublisherField publisher;
    private PublicationYearField py;
    private ResourceTypeField resourceType;
    
    // mandatory multiple
    private LinkedList<CreatorField> creators;
    private LinkedList<TitleField> titles;
    
    // optional single
    private LanguageField language;
    
    // optional multiple
    private LinkedList <SubjectField> subjects;
    private LinkedList<ContributorField> contributors;
    private LinkedList<DateField> dates;
    private LinkedList<AlternateIdentifierField> ais;
    
    // Methods
    boolean validate() {
        return true;
    }
    
    // getter, setter, adder
    
    // ID 1
    public void setIdentifier(IdentifierField s) { this.id = s; }
    public IdentifierField getIdentifier() { return this.id; }
    
    // ID 2
    public void addCreator(CreatorField s) { this.creators.add(s); }
    public LinkedList<CreatorField> getCreators() { return this.creators; }
    
    // ID 3
    public void addTitle(TitleField s) { this.titles.add(s); }
    public LinkedList<TitleField> getTitles() { return (this.titles); }
    
    // ID 4
    public void setPublisher(PublisherField s) { this.publisher = s; }
    public PublisherField getPublisher() { return this.publisher; }
    
    // ID 5
    public void setPublicationYear(PublicationYearField s) { this.py = s; }
    public PublicationYearField getPublicationYear() { return this.py; }
    
    // ID 6
    public void addSubjectField(SubjectField s){ this.subjects.add(s); }
    public LinkedList<SubjectField> getSubjects() { return this.subjects; }
    
    // ID 7
    public void addContributorField(ContributorField s){ this.contributors.add(s); }
    public LinkedList<ContributorField> getContributors() { return this.contributors; }
    
    // ID 8
    public void addDateField(DateField s){ this.dates.add(s); }
    public LinkedList<DateField> getDates() { return this.dates; }
    
    // ID 9
    public void setLanguage(LanguageField s) { this.language = s; }
    public LanguageField getLanguage() { return this.language; }
    
    // ID 10
    public void setResourceType(ResourceTypeField s) { this.resourceType = s; }
    public ResourceTypeField getResourceType() { return this.resourceType; }
    
    // ID 11
    public void addAlternateIdentifier(AlternateIdentifierField s){ this.ais.add(s); }
    public LinkedList<AlternateIdentifierField> getAlternateIdentifier() { return this.ais; }
    
}
