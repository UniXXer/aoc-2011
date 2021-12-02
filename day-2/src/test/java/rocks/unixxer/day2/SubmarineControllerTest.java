package rocks.unixxer.day2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class SubmarineControllerTest {
    @Inject
    SubmarineController submarineController;

    @Test
    public void testSubmarine() throws FileNotFoundException {
        List<String> readData = readData("/input.txt");
        
        readData.forEach((cmd) -> {
            submarineController.command(cmd);
        });

        assertEquals(15, submarineController.getSubmarine().getPosition());
        assertEquals(10, submarineController.getSubmarine().getDepth());

    }

    public List<String> readData(String filename) throws FileNotFoundException {
        List<String> result = new ArrayList<>();

        try (Scanner s = new Scanner(new InputStreamReader(SolveTasks.class.getResourceAsStream(filename)))) {
            while (s.hasNext()) {
                result.add(s.nextLine());
            }
            return result;
        }
    }
}
