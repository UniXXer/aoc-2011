package rocks.unixxer;

import io.quarkus.test.junit.QuarkusTest;
import rocks.unixxer.day1.SolveTask1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import javax.inject.Inject;

@QuarkusTest
public class Day1SolveTask1Test {

    @Inject
    SolveTask1 solveTask1;

    @Test
    public void testSolve() throws FileNotFoundException {
        Integer solve = solveTask1.solve("/input.txt");
        assertEquals(7, solve);
    }

    @Test
    public void testReadData() throws FileNotFoundException {
        List<Integer> data = solveTask1.readData("/input.txt");

        assertEquals(10, data.size());
    }

}