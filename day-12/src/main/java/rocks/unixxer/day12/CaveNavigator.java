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

        findNext(startPoint, new ArrayList<>(), false, false);

        return allPaths.size();
    }

    public int findMoreExits() {
        CavePoint startPoint = allPoints.get("start");

        allPaths = new ArrayList<>();

        findNext(startPoint, new ArrayList<>(), true, false);

        return allPaths.size();
    }

    private void findNext(CavePoint cp, List<CavePoint> visited, boolean step2, boolean weHaveASecond) {
        boolean nextWeHaveASecond = weHaveASecond;
        visited.add(cp);

        if(step2 && !nextWeHaveASecond && cp.isSmall() && visited.stream().filter((cp2) -> cp.getName().equals(cp2.getName())).count() == 2) {
            nextWeHaveASecond = true;
        }

        if("end".equals(cp.getName())) {
            allPaths.add(visited);
            return;
        }

        for(CavePoint next : cp.getNeighbours()) {
            if(!step2) {
                if(next.isSmall() && visited.contains(next)) {
                    continue;
                }
            } else {
                if("start".equals(next.getName())) {
                    continue;
                }

                if(next.isSmall() && visited.contains(next) && nextWeHaveASecond) {
                    continue;
                }
            }

            ArrayList<CavePoint> nextList = new ArrayList<>(visited);
            findNext(next, nextList, step2, nextWeHaveASecond);
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
