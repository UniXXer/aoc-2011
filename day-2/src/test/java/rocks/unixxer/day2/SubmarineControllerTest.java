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
        assertEquals(15, submarineController.getSubmarine().getPositionTask1());
        assertEquals(10, submarineController.getSubmarine().getDepthTask1());

        assertEquals(15, submarineController.getSubmarine().getPosition());
        assertEquals(60, submarineController.getSubmarine().getDepth());

    }
}
