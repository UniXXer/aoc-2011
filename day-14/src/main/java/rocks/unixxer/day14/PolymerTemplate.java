package rocks.unixxer.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PolymerTemplate {

    private Map<String, String> insertions = new HashMap<>();
    private Map<String, Long> polymerParts = new HashMap<>();
    private Map<String, Long> elements = new HashMap<>();

    public void reset() {
        insertions = new HashMap<>();
        polymerParts = new HashMap<>();
        elements = new HashMap<>();
    }

    public long analysePolymer() {        
        List<Entry<String, Long>> collect = elements.entrySet().stream()
            .sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
            .collect(Collectors.toList());

        long leastCommon = collect.get(0).getValue();    
        long mostCommon = collect.get(collect.size() - 1).getValue();

        return mostCommon - leastCommon;
    }

    public void growPolymer(int steps) {        

        for(int i = 1; i <= steps; i++) {
            Map<String, Long> newPolymerParts = new HashMap<>();
            for(Entry<String, Long> e : polymerParts.entrySet()) {
                String insertation = insertions.get(e.getKey());
                insertPolymer(newPolymerParts, e.getKey().charAt(0) + insertation, e.getValue());
                insertPolymer(newPolymerParts, insertation + e.getKey().charAt(1), e.getValue());
                addElement(insertation, e.getValue());
            }

            polymerParts = newPolymerParts;
            
        }
    }

    private void insertPolymer(Map<String, Long> polymerParts, String polymer, long amount) {
        Long currentAmount = polymerParts.get(polymer);

        if(currentAmount == null) {
            polymerParts.put(polymer, amount);
        } else {
            polymerParts.put(polymer, currentAmount + amount);
        }
    }

    private void addElement(String s, long count) {
        Long current = elements.get(s);

        if(current == null) {
            elements.put(s, count);
        } else {
            elements.put(s, count + current);
        }
    }

    public void parseInput(String s, int lineNumber) {
        if(lineNumber == 0) {
            addElement(s.charAt(0) + "", 1);
            for(int i = 1; i < s.length(); i++) {
                addElement(s.charAt(i) + "", 1);
                
                String part = s.charAt(i - 1) + "" + s.charAt(i) + "";
                Long long1 = polymerParts.get(part);

                if(long1 == null) {
                    polymerParts.put(part, 1L);
                } else {
                    polymerParts.put(part, long1 + 1L);
                }
            }

        } else {
            if(s.indexOf(" -> ") > 0) {
                String[] split = s.split(" -> ");
                insertions.put(split[0].trim(), split[1].trim());
            }
        }
    }

    public Map<String, Long> getThePolymer() {
        return polymerParts;
    }

    public Map<String, Long> getTheElements() {
        return elements;
    }
    
    public record Amount(String s, long amount) {}
}
