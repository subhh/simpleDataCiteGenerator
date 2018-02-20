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
import datacitegenerator.FieldTypes.SizeField;
import datacitegenerator.FieldTypes.FormatField;
import datacitegenerator.FieldTypes.RelatedIdentifierField;
import datacitegenerator.FieldTypes.VersionField;

import java.io.File;

import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * This class defines the schema for the Datacite metadata format records
 * 
 * @author jfmaas
 */
public class DataCiteRecord {
    
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
    private VersionField version;
    
    // optional multiple
    private LinkedList<SubjectField> subjects;
    private LinkedList<ContributorField> contributors;
    private LinkedList<DateField> dates;
    private LinkedList<RelatedIdentifierField> ris;
    private LinkedList<AlternateIdentifierField> ais;
    private LinkedList<SizeField> sizes;
    private LinkedList<FormatField> formats;
    
    //Constructor
    public DataCiteRecord () {
        this.id = null;
        this.publisher = null;
        this.py = null;
        this.resourceType = null;
        
        creators = new LinkedList<>();
        titles= new LinkedList<>();
        
        language = null;
        
        subjects = new LinkedList<>();
        contributors = new LinkedList<>();
        dates = new LinkedList<>();
        ais = new LinkedList<>();
        ris = new LinkedList<>();
        sizes = new LinkedList<>();
        formats = new LinkedList<>();
        version = null;
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
    public LinkedList<AlternateIdentifierField> getAlternateIdentifiers() { return this.ais; }
    
    // ID 12
    public void addRelatedIdentifier(RelatedIdentifierField s){ this.ris.add(s); }
    public LinkedList<RelatedIdentifierField> getRelatedIdentifiers() { return this.ris; }

    // ID 13
    public void addSize(SizeField s){ this.sizes.add(s); }
    public LinkedList<SizeField> getSizes() { return this.sizes; }
    
    // ID 14
    public void addFormat(FormatField s){ this.formats.add(s); }
    public LinkedList<FormatField> getFormats() { return this.formats; }
    
    // ID 15
    public void setVersion(VersionField s){ this.version = s; }
    public VersionField getVersion() { return this.version; }
    
    public String validate() {
        String r = "";
        Iterator i = null;
        
        // Identifier
        if (id != null) {
            r = r.concat(id.validate());
        } else {
            r = r.concat("No identifier is set!\n");
        }
        
        // creator
        if (creators.size() < 1) {
            r = r.concat("No creator specified!\n");
        } else {
            i = this.creators.iterator();
            while(i.hasNext()) {
                CreatorField f = (CreatorField) i.next();
                r = r.concat(f.validate());
            }
        }
        
        // title
        if (titles.size() < 1) {
            r = r.concat("No title specified!\n");
        } else {
            i = this.titles.iterator();
            while(i.hasNext()) {
                TitleField f = (TitleField) i.next();
                r = r.concat(f.validate());
            }
        }
        
        // publisher
        if (publisher != null) {
            r = r.concat(publisher.validate());
        } else {
            r = r.concat("No publisher is set!\n");
        }
        
        // publication year
        if (py != null) {
            r = r.concat(py.validate());
        } else {
            r = r.concat("No publication year is set!\n");
        }
        
        // subjects
        if (subjects.size() > 0) {
            i = this.titles.iterator();
            while(i.hasNext()) {
                SubjectField f = (SubjectField) i.next();
                r = r.concat(f.validate());
            }
        }
        
        // contributors
        if (contributors.size() > 0) {
            i = this.titles.iterator();
            while(i.hasNext()) {
                ContributorField f = (ContributorField) i.next();
                r = r.concat(f.validate());
            }
        }
        
        // dates
        if (dates.size() > 0) {
            i = this.dates.iterator();
            while(i.hasNext()) {
                DateField f = (DateField) i.next();
                r = r.concat(f.validate());
            }
        }
        
        // language
        if (language != null) {
            r = r.concat(language.validate());
        }
        
        // resourcetype
        if (resourceType == null) {
            r = r.concat("resourceType not specified!\n");
        } else {
            r = r.concat(resourceType.validate());
        }
        
        // alternateIdentifier
        if (ais.size() > 0) {
            i = ais.iterator();
            while(i.hasNext()) {
                AlternateIdentifierField f = (AlternateIdentifierField) i.next();
                r = r.concat(f.validate());
            }
        }
        
        // relatedIdentifier
        if (ris.size() > 0) {
            i = ris.iterator();
            while(i.hasNext()) {
                RelatedIdentifierField f = (RelatedIdentifierField) i.next();
                r = r.concat(f.validate());
            }
        }
        
        // size
        if (sizes.size() > 0) {
            i = sizes.iterator();
            while(i.hasNext()) {
                SizeField f = (SizeField) i.next();
                r = r.concat(f.validate());
            }
        }
        // version
        if (this.version != null) {
            r = r.concat(this.version.validate());
        }
        
        return r;
    }
    
    public void createXML(){
        
        try {
            
            Iterator i = null;

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            
            Element rootElement = doc.createElement("resource");
            Attr attr = doc.createAttribute("xsi:schemaLocation");
            attr.setValue("http://datacite.org/schema/kernel-4 http://schema.datacite.org/meta/kernel-4.1/metadata.xsd");
            rootElement.setAttributeNode(attr);
            
            // add Identifier
            if (id != null) {
                rootElement.appendChild(id.createXML(doc));
            }   
            
            // add Creators
            i = this.creators.iterator();
            while(i.hasNext()) {
                CreatorField f = (CreatorField) i.next();
                rootElement.appendChild(f.createXML(doc));
            }
            
            // add titles
            i = this.titles.iterator();
            while(i.hasNext()) {
                TitleField f = (TitleField) i.next();
                rootElement.appendChild(f.createXML(doc));
            }

            // add publisher
            if (id != null) {
                rootElement.appendChild(publisher.createXML(doc));
            }  

            // add publication year
            if (id != null) {
                rootElement.appendChild(py.createXML(doc));
            }      
            
            // add subjects
            i = this.subjects.iterator();
            while(i.hasNext()) {
                SubjectField f = (SubjectField) i.next();
                rootElement.appendChild(f.createXML(doc));
            }
            
            // add contributors
            i = this.contributors.iterator();
            while(i.hasNext()) {
                ContributorField f = (ContributorField) i.next();
                rootElement.appendChild(f.createXML(doc));
            }
            
            // add dates
            i = this.dates.iterator();
            while(i.hasNext()) {
                DateField f = (DateField) i.next();
                rootElement.appendChild(f.createXML(doc));
            }
            
            // language
            if (language != null) {
                rootElement.appendChild(language.createXML(doc));
            }
            
            // resourceType
            if (resourceType != null){
                rootElement.appendChild(resourceType.createXML(doc));
            }
            
            // add alternateIdentifiers
            i = this.ais.iterator();
            while(i.hasNext()) {
                AlternateIdentifierField f = (AlternateIdentifierField) i.next();
                rootElement.appendChild(f.createXML(doc));
            }
            
            // add relatedIdentifiers
            i = this.ris.iterator();
            while(i.hasNext()) {
                RelatedIdentifierField f = (RelatedIdentifierField) i.next();
                rootElement.appendChild(f.createXML(doc));
            }
            
            // add size
            i = this.sizes.iterator();
            while(i.hasNext()) {
                SizeField f = (SizeField) i.next();
                rootElement.appendChild(f.createXML(doc));
            }
            
            // add format
            i = this.formats.iterator();
            while(i.hasNext()) {
                FormatField f = (FormatField) i.next();
                rootElement.appendChild(f.createXML(doc));
            }
            
            // add version
            if (version != null) {
                rootElement.appendChild(version.createXML(doc));
            }   
            
            // End
            doc.appendChild(rootElement);
        
        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
	}
        
    }
}
