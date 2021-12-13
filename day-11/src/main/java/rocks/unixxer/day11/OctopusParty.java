package rocks.unixxer.day11;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

@ApplicationScoped
public class OctopusParty {
    @Inject
    Logger log;

    private int[][] octos = new int[10][10];

    public void reset() {
        octos = new int[10][10];
    }

    public int partyFlasher(int partyRounds, boolean calcAll) {
        int flashes = 0;

        for(int pr = 1; pr <= partyRounds; pr++) {

            int flashAmount = flashes;

            // STEP 1 increase energy
            for(int x = 0; x < 10; x++) {
                for(int y = 0; y < 10; y++) {
                    octos[x][y]++;
                }
            }

            // STEP  2 FLASH
            boolean flashed = false;
            do {
                flashed = false;

                for(int x = 0; x < 10; x++) {
                    for(int y = 0; y < 10; y++) {
                        int curr = octos[x][y];

                        if(curr < 0 ) {
                            log.error("ERROR: ");
                        }

                        if(curr > 9) {
                            flashes++;
                            flashed = true;
                            octos[x][y] = 0;

                            incAdj(x-1, y-1);
                            incAdj(x, y-1);   
                            incAdj(x+1, y-1);
                            
                            incAdj(x-1, y);                        
                            incAdj(x+1, y);                                            
                            
                            incAdj(x-1, y+1);
                            incAdj(x, y+1);
                            incAdj(x+1, y+1);
                        }
                    }
                }

            } while(flashed);

            int flashedDudes = flashes - flashAmount;            
            if(flashedDudes == 100) {
                log.info("Flashed " + flashedDudes + " in round " + pr);
                if(calcAll) {
                    return pr;
                }
            } 
        }

        return flashes;
    }

    private void incAdj(int x, int y) {
        if(x > -1 && y > -1 && x < 10 && y < 10){
            if(octos[x][y] > 0) {
                octos[x][y]++;
            }
        }
    }

    public void addLine(String line, int no) {
        for(int i=0; i<10; i++) {
            octos[i][no] = Integer.parseInt(line.charAt(i) + "");
        }
    }
    
}
