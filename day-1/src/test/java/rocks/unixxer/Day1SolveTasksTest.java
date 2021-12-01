package rocks.unixxer;

import io.quarkus.test.junit.QuarkusTest;
import rocks.unixxer.day1.SolveTasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import javax.inject.Inject;

@QuarkusTest
public class Day1SolveTasksTest {

    @Inject
    SolveTasks solveTask1;

    @Test
    public void testSolveTask1() throws FileNotFoundException {
        Integer solve = solveTask1.solveTask1("/input.txt");
        assertEquals(7, solve);
    }

    @Test
    public void testSolveTask2() throws FileNotFoundException {
        Integer solve = solveTask1.solveTask2("/input.txt");
        assertEquals(5, solve);
    }

    @Test
    public void testReadData() throws FileNotFoundException {
        List<Integer> data = solveTask1.readData("/input.txt");

        assertEquals(10, data.size());
    }

}