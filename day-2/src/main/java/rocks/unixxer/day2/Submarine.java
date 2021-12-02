package rocks.unixxer.day2;

public class Submarine {

    private Integer depthTask1;
    private Integer positionTask1;

    private Integer depth;
    private Integer position;
    private Integer aim;

    public Submarine() {
        this.depthTask1 = 0;
        this.positionTask1 = 0;

        this.depth = 0;
        this.position = 0;
        this.aim = 0;
    }

    public void forward(Integer steps) {
        this.positionTask1 += steps;
        this.position += steps;
        this.depth += (aim * steps);
    }
    
    public void up(Integer steps) {
        this.depthTask1 -= steps;
        this.aim -= steps;
    }

    public void down(Integer steps) {
        this.depthTask1 += steps;
        this.aim += steps;
    }

    public Integer getDepthTask1() {
        return depthTask1;
    }

    public Integer getPositionTask1() {
        return positionTask1;
    }

    public Integer getDepth() {
        return depth;
    }

    public Integer getPosition() {
        return position;
    }

    public Integer getAim() {
        return aim;
    }
}
