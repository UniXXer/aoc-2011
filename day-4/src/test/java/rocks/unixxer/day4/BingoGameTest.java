package rocks.unixxer.day4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BingoGameTest {

    @Inject
    BingoGame bingoGame;

    @Test
    public void testConsumer_puzzle1() throws FileNotFoundException {
        assertEquals(3, bingoGame.getBoards().size());
        
        assertEquals(4512, bingoGame.play());
    }

    //@Test
    public void testConsumer_puzzle2() throws FileNotFoundException {
        
    }
}