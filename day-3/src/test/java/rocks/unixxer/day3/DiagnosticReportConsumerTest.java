package rocks.unixxer.day3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class DiagnosticReportConsumerTest {

    @Inject
    DiagnosticReportConsumer diagnosticReportConsumer;

    @Test
    public void testConsumer_puzzle1() throws FileNotFoundException {
        assertEquals(22, diagnosticReportConsumer.getGammaRate());
        assertEquals(9, diagnosticReportConsumer.getEpsilonrate());
    }

    @Test
    public void testConsumer_puzzle2() throws FileNotFoundException {
        assertEquals(23, diagnosticReportConsumer.findOxygenGeneratorRating());
        assertEquals(10, diagnosticReportConsumer.findCo2ScrubberRating());
    }
}
