/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator;

import datacitegenerator.FieldTypes.*;

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
    private final LinkedList<CreatorField> creators;
    private final LinkedList<TitleField> titles;
    
    // optional single
    private LanguageField language;
    private VersionField version;
    
    // optional multiple
    private final LinkedList<SubjectField> subjects;
    private final LinkedList<ContributorField> contributors;
    private final LinkedList<DateField> dates;
    private final LinkedList<RelatedIdentifierField> ris;
    private final LinkedList<AlternateIdentifierField> ais;
    private final LinkedList<SizeField> sizes;
    private final LinkedList<FormatField> formats;
    private final LinkedList<RightsField> rights;
    private final LinkedList<DescriptionField> descriptions;
    private final LinkedList<GeoLocationField> geolocations;
    private final LinkedList<FundingReferenceField> fundings;
    
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
        rights = new LinkedList<>();
        descriptions = new LinkedList<>();
        geolocations = new LinkedList<>();
        fundings = new LinkedList<>();
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
    
    // ID 16
    public void addRights(RightsField s){ this.rights.add(s); }
    public LinkedList<RightsField> getRights() { return this.rights; }
    
    // ID 17
    public void addDescription(DescriptionField s){ this.descriptions.add(s); }
    public LinkedList<DescriptionField> getDescriptions() { return this.descriptions; }
    
    // ID 18
    public void addGeolocation(GeoLocationField s){ this.geolocations.add(s); }
    public LinkedList<GeoLocationField> getGeolocations() { return this.geolocations; }
    
    public String validate() {
        
        String r = "";
        
        // Identifier
        if (id == null) { r = r.concat("No identifier specified!\n");} 
        else { r = r.concat(id.validate());}

        // creator
        if (creators.size() < 1) { r = r.concat("No creator specified!\n"); } 
        r = r.concat(DataciteToolkit.validateList(creators));

        // title
        if (titles.size() < 1) { r = r.concat("No title specified!\n"); }
        r = r.concat(DataciteToolkit.validateList(titles));

        // publisher
        if (publisher == null) { r = r.concat("No publisher specified!\n"); }
        else { r = r.concat(publisher.validate());}

        // publication year
        if (py == null) { r = r.concat("No publication year specified!\n"); } 
        else { r = r.concat(py.validate());}

        // subjects
        r = r.concat(DataciteToolkit.validateList(subjects));

        // contributors
        r = r.concat(DataciteToolkit.validateList(contributors));

        // dates
        r = r.concat(DataciteToolkit.validateList(dates));

        // language
        if (language != null) { r = r.concat(language.validate()); }

        // resourcetype
        if (resourceType == null) { r = r.concat("No resourceType specified!\n"); }
        else {r = r.concat(resourceType.validate());}

        // alternateIdentifier
        r = r.concat(DataciteToolkit.validateList(ais));

        // relatedIdentifier
        r = r.concat(DataciteToolkit.validateList(ris));

        // size
        r = r.concat(DataciteToolkit.validateList(sizes));
        
        // format
        r = r.concat(DataciteToolkit.validateList(formats));
        
        // version
        if (this.version != null) { r = r.concat(this.version.validate()); }
        
        // rights
        r = r.concat(DataciteToolkit.validateList(rights));
        
        // descriptions
        r = r.concat(DataciteToolkit.validateList(descriptions));
        
        // geolocations
        r = r.concat(DataciteToolkit.validateList(geolocations));
        
        return r;
    }
    
    public void createXML(File xmlfile){
        
        try {
            
            Iterator i = null;

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            
            Element rootElement = doc.createElement("resource");
            rootElement.setAttributeNS(
                    "http://www.w3org.2001/XMLSchema-instance",
                    "xsi:schema-location",
                    "http://datacite.org/schema/kernel-4 http://schema.datacite.org/meta/kernel-4.1/metadata.xsd"
            
            );
            //Attr attr = doc.createAttribute("xsi:schemaLocation");
            //attr.setValue("http://datacite.org/schema/kernel-4 http://schema.datacite.org/meta/kernel-4.1/metadata.xsd");
            //rootElement.setAttributeNode(attr);
            
            // add Fields
            
            // Identifier
            if (id != null) { rootElement.appendChild(id.createXML(doc)); }   
            
            // Creators
            rootElement = DataciteToolkit.createXMLFromList(rootElement, doc, this.creators);
            
            // titles
            rootElement = DataciteToolkit.createXMLFromList(rootElement, doc, this.titles);

            // publisher
            if (publisher != null) { rootElement.appendChild(publisher.createXML(doc)); }  

            // publication year
            if (py != null) { rootElement.appendChild(py.createXML(doc)); }      
            
            // subjects
            rootElement = DataciteToolkit.createXMLFromList(rootElement, doc, this.subjects);

            // contributors
            rootElement = DataciteToolkit.createXMLFromList(rootElement, doc, this.contributors);
            
            // dates
            rootElement = DataciteToolkit.createXMLFromList(rootElement, doc, this.dates);
            
            // language
            if (language != null) { rootElement.appendChild(language.createXML(doc)); }
            
            // resourceType
            if (resourceType != null){ rootElement.appendChild(resourceType.createXML(doc)); }
            
            // alternateIdentifiers
            rootElement = DataciteToolkit.createXMLFromList(rootElement, doc, this.ais);
            
            // relatedIdentifiers
            rootElement = DataciteToolkit.createXMLFromList(rootElement, doc, this.ris);
            
            // size
            rootElement = DataciteToolkit.createXMLFromList(rootElement, doc, this.sizes);
            
            // format
            rootElement = DataciteToolkit.createXMLFromList(rootElement, doc, this.formats);

            // version
            if (version != null) { rootElement.appendChild(version.createXML(doc)); }   
            
            // rights
            rootElement = DataciteToolkit.createXMLFromList(rootElement, doc, this.rights);
            
            // description
            rootElement = DataciteToolkit.createXMLFromList(rootElement, doc, this.descriptions);

            // geolocations
            rootElement = DataciteToolkit.createXMLFromList(rootElement, doc, this.geolocations);
            
            // End
            doc.appendChild(rootElement);
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlfile);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);
        
	  } catch (ParserConfigurationException | TransformerException pce) {
		pce.printStackTrace();
	  }
        
    }
   
}
