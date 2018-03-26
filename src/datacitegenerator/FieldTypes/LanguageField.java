/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacitegenerator.FieldTypes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jfmaas
 */
public class LanguageField extends DataciteField {
    
    private final Map<String,String> h_lang;
    
    public LanguageField () {
        this.name="language";
             
        h_lang = mapLanguageCodes();
    }
    
    @Override
    public String validate() {
        String r = "";
        if (value != null) {        
            if (h_lang.get(value) != "true") {
                r = r.concat(this.getName() 
                        + " "
                        + value
                        + " "
                        + ": Wrong language type.\n");
            }
        }
        
        return r;
    }
    
    private HashMap<String,String> mapLanguageCodes() {
        // insert ISO 639-1 language codes plus mult
        
        HashMap<String,String> m = new HashMap<>();
        
        // add mul until better way is found
        List<String> y = Arrays.asList( "ab", "aa","af","ak","sq","am",
                "ar","an","hy","as","av","ae","ay","az","bm","ba","eu","be",
                "bn","bh","bi","bs","br","bg","my","ca","ch","ce","ny","zh",
                "cv","kw","co","cr","hr","cs","da","dv","nl","dz","en","eo",
                "et","ee","fo","fj","fi","fr","ff","gl","ka","de","el","gn",
                "gu","ht","ha","he","hz","hi","ho","hu","ia","id","ie","ga",
                "ig","ik","io","is","it","iu","ja","jv","kl","kn","kr","ks",
                "kk","km","ki","rw","ky","kv","kg","ko","ku","kj","la","lb",
                "lg","li","ln","lo","lt","lu","lv","gv","mk","mg","ms","ml",
                "mt","mi","mr","mh","mn","na","nv","nd","ne","ng","nb","nn",
                "no","ii","nr","oc","oj","cu","om","or","os","pa","pi","fa",
                "pox","pl","ps","pt","qu","rm","rn","ro","ru","sa","sc","sd",
                "se","sm","sg","sr","gd","sn","si","sk","sl","so","st","es",
                "su","sw","ss","sv","ta","te","tg","th","ti","bo","tk","tl",
                "tn","to","tr","ts","tt","tw","ty","ug","uk","ur","uz","ve",
                "vi","vo","wa","cy","wo","fy","xh","yi","yo","za","zu","mul");
                
                for (String s : y) {
                    m.put(s, "true");
                }
                return m;
    }
}
