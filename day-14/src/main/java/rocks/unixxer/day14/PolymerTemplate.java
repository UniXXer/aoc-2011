package rocks.unixxer.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PolymerTemplate {

    private String thePolymer;
    private Map<String, String> insertions = new HashMap<>();

    public void reset() {
        thePolymer = null;
        insertions = new HashMap<>();
    }

    public long analysePolymer() {
        List<Entry<Integer, Long>> collect = thePolymer.chars()
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
            .collect(Collectors.toList());

        long leastCommon = collect.get(0).getValue();    
        long mostCommon = collect.get(collect.size() - 1).getValue(); 

        return mostCommon - leastCommon;
    }

    public void growPolymer(int steps) {        
        for(int i = 1; i <= steps; i++) {
            StringBuilder sb = new StringBuilder();
            String first;
            String second = "";
            for(int j = 0; j < thePolymer.length() - 1; j++) {
                first = thePolymer.charAt(j) + "";
                second = thePolymer.charAt(j+1) + "";
                
                sb.append(first);
                sb.append(insertions.get(first + second));                
            }
            sb.append(second);
            thePolymer = sb.toString();
        }
    }

    public void parseInput(String s, int lineNumber) {
        if(lineNumber == 0) {
            thePolymer = s;
        } else {
            if(s.indexOf(" -> ") > 0) {
                String[] split = s.split(" -> ");
                insertions.put(split[0].trim(), split[1].trim());
            }
        }
    }

    public String getThePolymer() {
        return thePolymer;
    }
    
}
