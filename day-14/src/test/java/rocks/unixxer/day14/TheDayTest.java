package rocks.unixxer.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TheDayTest {

    @Inject
    SolveTasks solveTasks;

    @Inject
    PolymerTemplate polymerTemplate;

    @Test
    public void testConsumer_puzzle1() throws FileNotFoundException {
        assertEquals(1588, solveTasks.solveTask1());
    }

    @Test
    public void testGrow() throws FileNotFoundException {     
        solveTasks.solveTask1();   
        assertEquals(3073, polymerTemplate.getTheElements().values().stream().mapToLong((l) -> Long.valueOf(l)).sum());
    }

    @Test
    public void testConsumer_puzzle2() throws FileNotFoundException {
        assertEquals(2188189693529L, solveTasks.solveTask2());
    }
}
