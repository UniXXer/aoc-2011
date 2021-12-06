package rocks.unixxer.day6;

public class Laternenfish {
    private int age;

    public Laternenfish() {
        age = 8;
    }

    public Laternenfish(int a) {
        age = a;
    }
    
    public int age() {
        return age--;
    }

    public boolean hasBorn() {
        if(age == -1) {
            age = 6;
            return true;
        }

        return false;
    }
}
