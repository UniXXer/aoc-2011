package rocks.unixxer.day2;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SubmarineController {
    
    private Submarine submarine = new Submarine();

    public void command(String cmd) {
        String[] split = cmd.split(" ");

        Integer steps = Integer.parseInt(split[1]);

        switch (split[0]) {
            case "forward" : submarine.forward(steps); break;
            case "down" : submarine.down(steps); break;
            case "up" :  submarine.up(steps); break;
        }
    }

    public Submarine getSubmarine() {
        return submarine;
    }
}
