/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.parser;

import datacitegenerator.DataCiteRecord;
import datacitegenerator.FieldTypes.CreatorField;
import datacitegenerator.FieldTypes.IdentifierField;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;

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
                        
                        // do nothing with collections
                        if (context.equalsIgnoreCase("collection")) {};
                        
                        // store identifier directly
                        if (context.equalsIgnoreCase("identifier")) {
                            IdentifierField id = new IdentifierField();
                            id.setValue(content);
                            record.setIdentifier(id);
                        }
                        
                        // store identifierField. Asume identifier field.
                        if (context.equalsIgnoreCase("identifierfield")) {
                            IdentifierField id = record.getIdentifier();
                            if (id == null) {
                                throw new HosSolrParseException("identifierField found but no identifier specified.");
                            }
                            id.setIdentifierType(content);
                            record.setIdentifier(id);
                        }
                        
                        // store creatorName
                        if (context.equalsIgnoreCase("creatorname")) {
                            CreatorField creator = new CreatorField();
                            creator.setCreatorName(content);
                            record.addCreator(creator);
                        }
                        
                        // store
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
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (XMLStreamException e) {
         e.printStackTrace();
      }
   }

    @Override
    public String validate() {
        String result = "";
        
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