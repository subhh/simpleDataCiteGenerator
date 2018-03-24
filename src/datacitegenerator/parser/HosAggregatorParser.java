/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.parser;

import datacitegenerator.DataCiteRecord;
import datacitegenerator.FieldTypes.AlternateIdentifierField;
import datacitegenerator.FieldTypes.CreatorField;
import datacitegenerator.FieldTypes.DateField;
import datacitegenerator.FieldTypes.IdentifierField;
import datacitegenerator.FieldTypes.LanguageField;
import datacitegenerator.FieldTypes.PublicationYearField;
import datacitegenerator.FieldTypes.PublisherField;
import datacitegenerator.FieldTypes.RelatedIdentifierField;
import datacitegenerator.FieldTypes.TitleField;
import datacitegenerator.FieldTypes.SubjectField;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author jfmaas
 */
public class HosAggregatorParser extends DataCiteGeneratorParser {
    
    LinkedList<DataCiteRecord> myRecords = new LinkedList<>();

    public HosAggregatorParser() {
        inputfile = null;
        outputfile = null;
    }
    
    @Override
    public void parse() {

        DataCiteRecord record = null;
        
        String context = "";
        boolean in_context = false;
      
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                factory.createXMLEventReader(new FileReader(inputfile));

            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
               
                switch(event.getEventType()) {
               
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();
                    
                    // doc beginning? finish old record, create new record
                    if(qName.equalsIgnoreCase("doc")) { 
                        if (record != null) {
                            myRecords.add(record);
                        }
                        record = new DataCiteRecord();
                    }
                    
                    if (qName.equalsIgnoreCase("field")) { 
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        context = attributes.next().getValue();
                        in_context = true;
                    }

                break;

                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    String content =  characters.getData();
                    
                    if(in_context) { 
                        in_context = false;
                        boolean wasProcessed = false;
                        
                        // do nothing with collections
                        if (context.equalsIgnoreCase("collection")) {
                            wasProcessed = true;
                        };
                        
                        // store identifier directly
                        if (context.equalsIgnoreCase("identifier")) {
                            IdentifierField id = new IdentifierField();
                            id.setValue(content);
                            record.setIdentifier(id);
                            wasProcessed = true;
                        }
                        
                        // store identifierField. Asume identifier field.
                        if (context.equalsIgnoreCase("identifierfield")) {
                            IdentifierField id = record.getIdentifier();
                            if (id == null) {
                                throw new HosSolrParseException("identifierField found but no identifier specified.");
                            }
                            id.setIdentifierType(content);
                            record.setIdentifier(id);
                            wasProcessed = true;
                        }
                        
                        // store creatorName
                        if (context.equalsIgnoreCase("creatorname")) {
                            CreatorField creator = new CreatorField();
                            creator.setCreatorName(content);
                            record.addCreator(creator);
                            wasProcessed = true;
                        }
                        
                        // store title(s)
                        if (context.equalsIgnoreCase("title")) {
                            TitleField t = new TitleField();
                            t.setValue(content);
                            record.addTitle(t);
                            wasProcessed = true;
                        }
                        
                        // store title language
                        if (context.equalsIgnoreCase("titlelang")) {
                            try {
                                TitleField t = record.getTitles().getLast();
                                t.setTitleLanguage(content);
                            } catch (NoSuchElementException e) {
                                    throw new HosSolrParseException("Title lang was set but no title was defined before! ");
                            }
                            wasProcessed = true;
                        }
                        
                        // store publisher
                        if (context.equalsIgnoreCase("publisher")) {
                            PublisherField p = new PublisherField();
                            p.setValue(content);
                            record.setPublisher(p);
                            wasProcessed = true;
                        }

                        // store publication year
                        if (context.equalsIgnoreCase("publicationyear")) {
                            PublicationYearField p = new PublicationYearField();
                            p.setValue(content);
                            record.setPublicationYear(p);
                            wasProcessed = true;
                        }
                        
                        // do nothing with university
                        if (context.equalsIgnoreCase("university")) {
                            wasProcessed = true;
                        };
                        
                        // do nothing with institute
                        if (context.equalsIgnoreCase("institut")) {
                            wasProcessed = true;
                        };
                        if (context.equalsIgnoreCase("institute")) {
                            wasProcessed = true;
                        };
                        
                        // store subjects
                        if (context.equalsIgnoreCase("subject")) {
                            SubjectField s = new SubjectField();
                            s.setValue(content);
                            record.addSubjectField(s);
                            wasProcessed = true;
                        }                
                        
                        if (context.equalsIgnoreCase("subjectddc")) {
                            SubjectField s = new SubjectField();
                            s.setValue(content);
                            s.setSubjectScheme("DDC");
                            try {
                                s.setSchemeURI(new URI("https://www.oclc.org/en/dewey.html"));
                            } catch (Exception e) {};
                            record.addSubjectField(s);
                            wasProcessed = true;
                        }                
                        
                        // store dates
                        if (context.equalsIgnoreCase("date")) {
                            DateField d = new DateField();
                            d.setValue(content);
                            record.addDateField(d);
                            wasProcessed = true;
                        }   
                        
                        // store dateType. assume former date field
                        if (context.equalsIgnoreCase("datetype")) {
                            try {
                                DateField d = record.getDates().getLast();
                                d.setDateType(content);
                            } catch (NoSuchElementException e) {
                                    throw new HosSolrParseException("dateType was set but no date was defined before! ");
                            }
                            wasProcessed = true;
                        }

                        // store dateInformation. assume former date field
                        if (context.equalsIgnoreCase("dateinformation")) {
                            try {
                                DateField d = record.getDates().getLast();
                                d.setDateInformation(content);
                            } catch (NoSuchElementException e) {
                                    throw new HosSolrParseException("dateInfomration was set but no date was defined before! ");
                            }
                            wasProcessed = true;
                        }
                        
                        // store language
                        if (context.equalsIgnoreCase("language")) {
                            LanguageField l = new LanguageField();
                            l.setValue(content);
                            record.setLanguage(l);
                            wasProcessed = true;
                        }
                        
                        // store alternateIdentifier
                        if (context.equalsIgnoreCase("alternateidentifier")) {
                            AlternateIdentifierField d = new AlternateIdentifierField();
                            d.setValue(content);
                            record.addAlternateIdentifier(d);
                            wasProcessed = true;
                        }   
                        
                        // store AlternateIdentifierType. assume former AlternateIdentifier field
                        if (context.equalsIgnoreCase("AlternateIdentifierfield")) {
                            try {
                                AlternateIdentifierField d = record.getAlternateIdentifiers().getLast();
                                d.setAlternateIdentifierType(content);
                            } catch (NoSuchElementException e) {
                                    throw new HosSolrParseException("AlternateIdentifierType was set but no alternateIdentifier was defined before! ");
                            }
                            wasProcessed = true;
                        }
                        
                        // store relatedIdentifier
                        if (context.equalsIgnoreCase("relatedidentifier")) {
                            RelatedIdentifierField d = new RelatedIdentifierField();
                            d.setValue(content);
                            record.addRelatedIdentifier(d);
                            wasProcessed = true;
                        }   
                        
                        // store RelatedIdentifierType. assume former RelatedIdentifier field
                        if (context.equalsIgnoreCase("RelatedIdentifierfield")) {
                            try {
                                RelatedIdentifierField d = record.getRelatedIdentifiers().getLast();
                                d.setRelatedIdentifierType(content);
                            } catch (NoSuchElementException e) {
                                    throw new HosSolrParseException("RelatedIdentifierType was set but no RelatedIdentifier was defined before! ");
                            }
                            wasProcessed = true;
                        }
                        
                        // store RelationType. assume former RelatedIdentifier field
                        if (context.equalsIgnoreCase("relationtype")) {
                            try {
                                RelatedIdentifierField d = record.getRelatedIdentifiers().getLast();
                                d.setRelationType(content);
                            } catch (NoSuchElementException e) {
                                    throw new HosSolrParseException("RelationType was set but no RelatedIdentifier was defined before! ");
                            }
                            wasProcessed = true;
                        }
                        // weiter mit relatedMetadataScheme
                        // Vorsicht danach: SchemeURI und SchemeType können 
                        // wahrscheinlich an mehreren Stellen auftreten.
                        // Kontext muss geklärt werden (was kam davo?)
                        // oder erst einmal ohne?
                        
                        // unknown field? This isn't good. Die, die, die...
                        if (!wasProcessed) {
                            throw new HosSolrParseException("Unknown field: " + content);
                        }
                    }

                break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    
                    // Doc ending? store final record
                    if(endElement.getName().getLocalPart().equalsIgnoreCase("doc")) {
                        myRecords.add(record);
                    }
                break;
                } 
            }
        } catch (FileNotFoundException e) { e.printStackTrace();} 
          catch (XMLStreamException e) { e.printStackTrace();}
    }

    @Override
    public List<DataCiteRecord> getRecords() { return myRecords;  }
        
    @Override
    public String validate() {
        String result = "";
        
        for (DataCiteRecord dc : myRecords) {
            result = result.concat(dc.validate());
            result = result.concat("\n\n");
        }
        
        return result;
    }    
}


/*
{
	"add-field" : {
        "name":"collection",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
        "indexed":"true"
    },
    "add-field" : {
        "name":"identifier",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
        "indexed":"true"
    },
    "add-field" : {
        "name":"identifierType",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
        "indexed":"true"
    },
    "add-field" : {
        "name":"creatorName",
        "type":"text_general",
        "multiValued":true,
        "stored":true,
         "indexed":"true"
    },
    "add-field" : {
        "name":"title",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
        "indexed":"true"
    },
    "add-field" : {
        "name":"titleLang",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
        "indexed":"true"
    },
    "add-field" : {
        "name":"publisher",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
        "indexed":"true"
    },
    "add-field" : {
        "name":"publicationYear",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
        "indexed":"true"
    },
    "add-field" : {
        "name":"university",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
        "indexed":"true"
    },
    "add-field" : {
        "name":"institut",
        "type":"text_general",
        "multiValued":false,
        "stored":true
    },
    "add-field" : {
        "name":"subject",
        "type":"text_general",
        "multiValued":true,
        "stored":true,
         "indexed":"true"
    },
    "add-field" : {
        "name":"subjectDDC",
        "type":"text_general",
        "multiValued":false,
        "stored":true
    },
    "add-field" : {
        "name":"date",
        "type":"text_general",
        "multiValued":false,
        "stored":true
    },
    "add-field" : {
        "name":"resourceType",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
         "indexed":"true",
         "indexed":"true"
    },
    "add-field" : {
        "name":"language",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
         "indexed":"true",
    },
    "add-field" : {
        "name":"format",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
         "indexed":"true",
          "indexed":"true"
    },
    "add-field" : {
        "name":"rightsURI",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
         "indexed":"true"
    },
    "add-field" : {
        "name":"resourceTypeGeneral",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
         "indexed":"true"
    },
    "add-field" : {
        "name":"description",
        "type":"text_general",
        "multiValued":true,
        "stored":true,
         "indexed":"true"
    },
    "add-field" : {
        "name":"descriptionType",
        "type":"text_general",
        "multiValued":false,
        "stored":true,
         "indexed":"true"
}



<add>
  <doc>
    <field name="authors">Patrick Eagar</field>
    <field name="subject">Sports</field>
    <field name="dd">796.35</field>
    <field name="numpages">128</field>
    <field name="desc"></field>
    <field name="price">12.40</field>
    <field name="title">Summer of the all-rounder: Test and championship cricket in England 1982</field>
    <field name="isbn">0002166313</field>
    <field name="yearpub">1982</field>
    <field name="publisher">Collins</field>
  </doc>
  <doc>
  ...
  </doc>
</add>


*/