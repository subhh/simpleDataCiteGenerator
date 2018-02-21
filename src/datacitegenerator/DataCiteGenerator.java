/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator;

import datacitegenerator.FieldTypes.*;

import java.io.File;

/**
 *
 * @author jfmaas
 */
public class DataCiteGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DataCiteRecord rc = new DataCiteRecord();

        // Set the data
        
        IdentifierField id = new IdentifierField();
        id.setValue("1.2.3.4");
        id.setIdentifierType("IPv4-Adress");
        rc.setIdentifier(id);
        
        TitleField tf = new TitleField();
        tf.setValue("The Adventures of Robinson Crusoe");
        tf.setTitleType("Blahtitle");
        rc.addTitle(tf);
        
        TitleField tf2 = new TitleField();
        tf2.setValue("The Lord of the Rings");
        tf2.setTitleType("Other");
        rc.addTitle(tf2);
        
        PublisherField pf = new PublisherField();
        pf.setValue("Noname Books");
        rc.setPublisher(pf);
        
        PublicationYearField py = new PublicationYearField();
        py.setValue("1973");
        rc.setPublicationYear(py);
        
        ResourceTypeField rt = new ResourceTypeField();
        rt.setValue("Audiobook");
        rt.setResourceTypeGeneral("Sound");
        rc.setResourceType(rt);

        // Output
        System.out.println(rc.validate());
        rc.createXML(new File("/home/jfmaas/out.xml"));
        
    }
    
}
