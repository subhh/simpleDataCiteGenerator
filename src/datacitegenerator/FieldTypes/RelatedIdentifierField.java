/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jfmaas
 */
public class RelatedIdentifierField extends DataciteField {
    
    private String relatedIdentifierType;
    private String relationType;
    private String relatedMetadataScheme;
    private URI    schemeURI;
    private String schemeType;
    
    private final Map<String,String> id_types;
    private final Map<String,String> rel_types;
    
    public RelatedIdentifierField () {
        name = "relatedIdentifier";
        
        relatedIdentifierType = null;
        relationType = null;
        relatedMetadataScheme = null;
        schemeURI = null;
        schemeType = null;
        
        id_types = new HashMap<>();
        id_types.put("ARK","true");
        id_types.put("arXiv","true");
        id_types.put("bibcode","true");
        id_types.put("DOI","true");
        id_types.put("EAN13","true");
        id_types.put("EISSN","true");
        id_types.put("Handle","true");
        id_types.put("IGSN","true");
        id_types.put("ISBN","true");
        id_types.put("ISSN","true");
        id_types.put("ISTC","true");
        id_types.put("LISSN","true");
        id_types.put("LSID","true");
        id_types.put("PMID","true");
        id_types.put("PURL","true");
        id_types.put("UPC","true");
        id_types.put("URL","true");
        id_types.put("URN","true");
        
        rel_types = new HashMap<>();
        
        rel_types.put("IsCitedBy","true");
        rel_types.put("Cites","true");
        rel_types.put("IsSupplementTo","true");
        rel_types.put("IsSupplementedBy","true");
        rel_types.put("IsContinuedBy","true");
        rel_types.put("Continues","true");
        rel_types.put("IsDescribed by","true");
        rel_types.put("Describes","true");
        rel_types.put("HasMetadata","true");
        rel_types.put("IsMetadataFor","true");
        rel_types.put("HasVersion","true");
        rel_types.put("IsVersionOf","true");
        rel_types.put("IsNewVersionOf","true");
        rel_types.put("IsPreviousVersionOf","true");
        rel_types.put("IsPartOf","true");
        rel_types.put("HasPart","true");
        rel_types.put("IsReferencedBy","true");
        rel_types.put("References","true");
        rel_types.put("IsDocumentedBy","true");
        rel_types.put("Documents","true");
        rel_types.put("IsCompiledBy","true");
        rel_types.put("Compiles","true");
        rel_types.put("IsVariantFormOf","true");
        rel_types.put("IsOriginalFormOf","true");
        rel_types.put("IsIdenticalTo","true");
        rel_types.put("IsReviewedBy","true");
        rel_types.put("Reviews","true");
        rel_types.put("IsDerivedFrom","true");
        rel_types.put("IsSourceOf","true");
        rel_types.put("IsRequiredBy","true");
        rel_types.put("Requires","true");
    }
    
    public void setRelatedIdentifierType (String s) { this.relatedIdentifierType = s;};
    public String getRelatedIdentifierType () { return this.relatedIdentifierType;};
    
    public void setRelationType (String s) { this.relationType = s;};
    public String getRelationType () { return this.relationType;};
    
    public void setRelatedMetadataScheme (String s) { this.relatedMetadataScheme = s;};
    public String getRelatedMetadataScheme () { return this.relatedMetadataScheme;};
    
    public void setSchemeURI (URI s) { this.schemeURI = s;};
    public URI getSchemeURI () { return this.schemeURI;};

    public void setSchemeType (String s) { this.schemeType = s;};
    public String getSchemeType () { return this.schemeType;};
    
    @Override
    public String validate() {
        String r = "";
        if (value == null) {
            r = r.concat(this.getName() + ": No value defined.\n");
        }
        
        if (relatedIdentifierType == null) {
            r = r.concat(this.getName() + ": No relatedIdentifierType defined.\n");
        } else {
            if (id_types.get(relatedIdentifierType) == null) {
                r = r.concat(this.getName() 
                        + " "
                        + relatedIdentifierType
                        + " "
                        + ": Unknown relatedIdentifierType.\n");
            }
        }

        if (relationType == null) {
            r = r.concat(this.getName() + ": No relationType defined.\n");
        } else {
            if (rel_types.get(relationType) == null) {
                r = r.concat(this.getName() 
                        + " "
                        + relationType
                        + " "
                        + ": Unknown relationType.\n");
            }
        }
        
        return r;
    }
    
    @Override
    public Element createXML(Document doc){
        Element field = doc.createElement(this.getName());
        
        if (this.getRelatedIdentifierType() != null) {
            Attr attr = doc.createAttribute("relatedIdentifierType");
            attr.setValue(this.getRelatedIdentifierType());
            field.setAttributeNode(attr);
        }

        if (this.getRelationType() != null) {
            Attr attr = doc.createAttribute("relationType");
            attr.setValue(this.getRelationType());
            field.setAttributeNode(attr);
        }
        
        if (this.getRelatedMetadataScheme() != null) {
            Attr attr = doc.createAttribute("relatedMetadataScheme");
            attr.setValue(this.relatedMetadataScheme);
            field.setAttributeNode(attr);
        }
        
        if (this.getSchemeURI() != null) {
            Attr attr = doc.createAttribute("schemeURI");
            attr.setValue(this.schemeURI.toString());
            field.setAttributeNode(attr);
        }

        if (this.getSchemeType() != null) {
            Attr attr = doc.createAttribute("schemeType");
            attr.setValue(this.schemeType);
            field.setAttributeNode(attr);
        }
        
        field.appendChild(doc.createTextNode(this.getValue()));
                
        return field;
    }
    
}
