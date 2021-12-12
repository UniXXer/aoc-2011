package rocks.unixxer.day12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CaveNavigator {

    private Map<String, CavePoint> allPoints = new HashMap<>();
    private List<List<CavePoint>> allPaths;

    public int findExits() {
        CavePoint startPoint = allPoints.get("start");

        allPaths = new ArrayList<>();

        findNext(startPoint, new ArrayList<>());

        return allPaths.size();
    }

    private void findNext(CavePoint cp, List<CavePoint> visited) {
        visited.add(cp);

        if("end".equals(cp.getName())) {
            allPaths.add(visited);
            return;
        }

        for(CavePoint next : cp.getNeighbours()) {
            if(next.isSmall() && visited.contains(next)) {
                continue;
            }

            ArrayList<CavePoint> nextList = new ArrayList<>(visited);
            findNext(next, nextList);
        }
    }

    public void addPath(String path) {
        String[] splits = path.split("-");
        String start = splits[0];
        String end = splits[1];

        CavePoint startPoint = getPoint(start.trim());
        CavePoint endPoint = getPoint(end.trim());

        startPoint.addNeighbour(endPoint);
        endPoint.addNeighbour(startPoint);
    }

    public CavePoint getPoint(String s) {
        CavePoint cavePoint = allPoints.get(s);

        if(cavePoint == null) {
            cavePoint = new CavePoint(s);
            allPoints.put(cavePoint.getName(), cavePoint);
        }

        return cavePoint;
    }

    class CavePoint {
        private String name;
        private Map<String, CavePoint> neighbours = new HashMap<>();

        public CavePoint(String n) {
            this.name = n;
        }

        public boolean isSmall() {
            return !name.toUpperCase().equals(name);
        }

        public void addNeighbour(CavePoint cp) {
            neighbours.put(cp.getName(), cp);
        }

        public Collection<CavePoint> getNeighbours() {
            return neighbours.values();
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "["+name+"]";
        }
    }
    
}
