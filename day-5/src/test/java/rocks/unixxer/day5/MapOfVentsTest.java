package rocks.unixxer.day5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MapOfVentsTest {

    @Inject
    MapOfVents mapOfVents;

    @Test
    public void testConsumer_puzzle1() throws FileNotFoundException {
        assertEquals(5, mapOfVents.getMostDangerousAreasCount());
    }

    @Test
    public void testConsumer_puzzle2() throws FileNotFoundException {
        //assertEquals(1924, bingoGame.getResultPuzzle2());
    }
}
