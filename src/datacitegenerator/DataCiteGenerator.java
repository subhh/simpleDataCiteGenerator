/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator;

import datacitegenerator.FieldTypes.*;
import datacitegenerator.parser.DataCiteGeneratorParser;
import datacitegenerator.parser.HosAggregatorParser;

import java.io.File;
import java.util.List;
import org.apache.commons.cli.*;

/**
 *
 * @author jfmaas
 */
public class DataCiteGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Options options = new Options();

        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", "output", true, "output file path and prefix");
        output.setRequired(true);
        options.addOption(output);

        Option parseropt = new Option("p", "parser", true, "parser to use. Option(s): HosAggregator");
        parseropt.setRequired(true);
        options.addOption(parseropt);
        
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            return;
        }

        String inputFilePath = cmd.getOptionValue("input");
        String outputFilePath = cmd.getOptionValue("output");
        String parserName = cmd.getOptionValue("parser");
;
        
        DataCiteGeneratorParser p = null;
        
        switch (parserName) {
            case "HosAggregator":
                p = new HosAggregatorParser();
                break;
            
            default: 
                System.out.println("Unknown parser name. Exiting.");
                System.exit(0);
                break;
        }
        
        p.setInputfile(inputFilePath);
        p.setOutputfile(outputFilePath);
        
        p.parse();
        
        // Validate
        System.out.println(p.validate());
        
        List<DataCiteRecord> records = p.getRecords();
                
        // Write Records based on file prefix
        int count = records.size();
        String digits = Integer.toString(count);
        digits = "%0" + Integer.toString(digits.length()) + "d";
        int i = 0;
        for (DataCiteRecord record : records) {
            i++;
            
            String filename = outputFilePath + String.format(digits,i) + ".xml";
            record.writeXML(new File(filename));
            //int yourInteger = 30;
            //String formattedNumber = String.format("%03d", yourInteger);
            
            // end after 10 records for testing purposes
            if (i > 9) { break; }
        }

        
        /*
        
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
        
        TitleField tf3 = new TitleField();
        tf3.setValue("beluga- Der Wal und ich");
        rc.addTitle(tf3);
        
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
        rc.createXML(new File("c:/local/out.xml"));
        
        */
    }
    
}
