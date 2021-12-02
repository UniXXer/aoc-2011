package rocks.unixxer.day2;

public class Submarine {

    private Integer depth;
    private Integer position;

    public Submarine() {
        this.depth = 0;
        this.position = 0;
    }

    public void forward(Integer steps) {
        this.position += steps;
    }
    
    public void up(Integer steps) {
        this.depth -= steps;
    }

    public void down(Integer steps) {
        this.depth += steps;
    }

    public Integer getDepth() {
        return depth;
    }

    public Integer getPosition() {
        return position;
    }
}
