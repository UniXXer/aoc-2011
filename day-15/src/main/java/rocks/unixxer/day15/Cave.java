package rocks.unixxer.day15;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Cave {

    private int[][] risks;

    public int lowestRiskPath() {
        Set<Point> unvisitedPoints = new TreeSet<>(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int compare = Integer.compare(p1.risk(), p2.risk());

                if(compare == 0) {
                    compare = Integer.compare(p1.x(), p2.x());
                }

                if(compare == 0) {
                    compare = Integer.compare(p1.y(), p2.y());
                }

                return compare;
            }
        });
        Set<Point> visitedPoints = new HashSet<>();
        Point start = new Point(0, 0, 0, false);
        unvisitedPoints.add(start);

        while(!unvisitedPoints.isEmpty()) {
            Point next = unvisitedPoints.iterator().next();

            visitedPoints.add(next.visited());
            unvisitedPoints.remove(next);

            if(next.x() == risks.length -1 && next.y() == risks.length -1) {
                // end reached
                return next.risk;
            }

            Set<Point> determineAdjs = determineAdjs(next, visitedPoints);
            unvisitedPoints.addAll(determineAdjs);
        }

        return 0;
    }

    // Thank you reddit
    public int evaluateRisk() {
        int[][] riskMap = risks;

		// A 2d array to track the total risk involved to travel from each and every
		// node to the bottom-right corner of the map.
		int[][] riskSums = new int[riskMap.length][riskMap[0].length];
		// Initialize the riskSum at every node to a large number. 1,000,000 should do
		// fine. We can't use Integer.MAX_VALUE since we will loop through and possibly
		// add to these values. We don't want to overflow.
		for (int r = 0; r < riskSums.length; r++) {
			for (int c = 0; c < riskSums[0].length; c++) {
				riskSums[r][c] = 1_000_000;
			}
		}
		// The riskSum for the bottom-right node is 0.
		riskSums[riskSums.length - 1][riskSums[0].length - 1] = 0;

		// The idea is to loop through the riskSums array from the bottom-right to the
		// top-left and update the riskSum at each node based on the riskSums of it's
		// neighbors. We will change the riskSum at each node to reflect the minimum
		// risk+riskSum of each of it's neighbors. If a change is made to the map, we
		// will loop back through in case that change triggered another potential
		// improvement to the graph. We will continue to loop through until no changes
		// are made.
		boolean changeMade = true;
		while (changeMade) {
			changeMade = false;
			for (int r = riskSums.length - 1; r >= 0; r--) {
				for (int c = riskSums[0].length - 1; c >= 0; c--) {
					// Four neighbors:
					// riskMap[r][c] : the risk to enter this 1 node.
					// riskSum[r][c] : the total risk involved in traveling to the bottom-right
					// from this node.
					int min = Integer.MAX_VALUE;
					if (r - 1 >= 0)
						min = Math.min(min, riskMap[r - 1][c] + riskSums[r - 1][c]);
					if (r + 1 < riskSums.length)
						min = Math.min(min, riskMap[r + 1][c] + riskSums[r + 1][c]);
					if (c - 1 >= 0)
						min = Math.min(min, riskMap[r][c - 1] + riskSums[r][c - 1]);
					if (c + 1 < riskSums[0].length)
						min = Math.min(min, riskMap[r][c + 1] + riskSums[r][c + 1]);

					// If a change is being made to a node, we will have to loop back through again.
					int oldRisk = riskSums[r][c];
					riskSums[r][c] = Math.min(riskSums[r][c], min);
					if (riskSums[r][c] != oldRisk)
						changeMade = true;
				}
			}
		}
		// We now know the riskSum at every single node, but all we wanted was the
		// riskSum at 0,0.
		return (riskSums[0][0]);
	}

    private Set<Point> determineAdjs(Point current, Set<Point> visits) {
        return Arrays.asList(
            new Point(current.x - 1, current.y, 0, false),
            new Point(current.x + 1, current.y, 0, false),
            new Point(current.x, current.y - 1, 0, false),
            new Point(current.x, current.y + 1, 0, false))
            .stream()
            .filter((p) -> p.x() > -1 && p.x() < risks.length && p.y() > -1 && p.y() < risks.length)
            .filter((p) -> !visits.contains(p))
            .map((p) -> p.addRisk(risks[p.x()][p.y()] + current.risk()))
            .collect(Collectors.toSet());
    }

    // thank you reddit
    public void expandMap() {
        int[][] newRisks = new int[5 * risks.length][5 * risks[0].length];
		for (int r = 0; r < risks.length; r++) {
			for (int c = 0; c < risks[0].length; c++) {
				int val = risks[r][c]; // a value from original small map
				// We will place increments of this value (wrapped around 9) at corresponding
				// locations in the large grid (25 locations all together).
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						// Here, we will calculate the 'newNum' to be placed in the large grid. The
						// 'newNum'
						// will be 'val' incremented by how many 5x5 grids we moved left AND how many
						// 5x5
						// grids we moved down into the expansion of the map (so, i+j).
						int newNum = val + i + j;
						// The largest increment to 'val' will be 8, since the bottom-right 5x5 grid is
						// over 4 and down 4 from the upper-left grid. Since, the largest value for
						// 'val' is 9, the largeset value of 'newNum' is 17. So, let's just check if
						// newNum > 9 and decrement by 9 if it is.
						if (newNum > 9)
							newNum -= 9;
						// Place newNum all over the new larger map.
						newRisks[r + i * risks.length][c + j * risks[0].length] = newNum;
					}
				}

			}
		}

		risks = newRisks;
    }

    public void parseLine(List<String> lines) {
        risks = new int[lines.size()][lines.size()];

        for(int x = 0; x < lines.size(); x++) {
            for(int y = 0; y < lines.size(); y++) {
                risks[x][y] = Integer.parseInt(lines.get(x).charAt(y) + "");
            }
        }
    }
    
    public record Point(int x, int y, int risk, boolean v) {
        public Point addRisk(int r) {
            return new Point(x, y, risk + r, v);
        }

        public Point visited() {
            return new Point(x, y, risk, true);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Point)) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y && risk == point.risk;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, risk);
        }
    }
}
