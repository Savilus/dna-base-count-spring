package io.sda.szczepanskikrs.sequencer.dna;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StreamDnaBaseCounterTest {
    @Test
    public void should_calculateExpectedBaseCount_when_validSequenceProvided() {
        // Given
        var systemUnderTest = new StreamDnaBaseCounter();
        var dnaSequence = "ATCGATTGAGCTCTAGCG";
        var expectedThymineCount = 5;
        var expectedAdenineCount = 4;
        var expectedCytosineCount = 4;
        var expectedGuanineCount = 5;

        // When
        var result = systemUnderTest.calculateDnaBaseCount(dnaSequence);

        // Then
        var thymineCount = result.rawBaseCount().get(DnaMolecule.THYMINE);
        var adenineCount = result.rawBaseCount().get(DnaMolecule.ADENINE);
        var guanineCount = result.rawBaseCount().get(DnaMolecule.GUANINE);
        var cytosineCount = result.rawBaseCount().get(DnaMolecule.CYTOSINE);
        assertThat(thymineCount).isEqualTo(expectedThymineCount);
        assertThat(adenineCount).isEqualTo(expectedAdenineCount);
        assertThat(guanineCount).isEqualTo(expectedGuanineCount);
        assertThat(cytosineCount).isEqualTo(expectedCytosineCount);
    }

    @Test
    public void shouldThrowExceptionWhileNoValidSequenceIsProvided() {
        // Given
        var systemUnderTest = new StreamDnaBaseCounter();
        var dnaSequence = "ATCGATTxwGAGCTCTAGCG";
        boolean thrown = false;

        // When
        try {
            var result = systemUnderTest.calculateDnaBaseCount(dnaSequence);
        } catch (Exception e) {
            thrown = true;
        }
        // Then
        assertThat(thrown).isTrue();
    }

    @Test
    public void shouldThrowExceptionWhileSequenceWithSpacesIsProvided() {
        // Given
        var systemUnderTest = new StreamDnaBaseCounter();
        var dnaSequence = "A  TCGATTGAGCTC TAGCG";
        boolean thrown = false;

        // When
        try {
            var result = systemUnderTest.calculateDnaBaseCount(dnaSequence);
        } catch (Exception e) {
            thrown = true;
        }
        // Then
        assertThat(thrown).isTrue();
    }

    @Test
    void shouldReturnEmptyMapIfSequenceIsEmpty() {
        // Given
        var systemUnderTest = new StreamDnaBaseCounter();
        var dnaSequence = "";
        Map<DnaMolecule, Long> dnaMoleculeMap = new HashMap<>();
        DnaBaseCount dnaBaseCount = new DnaBaseCount(dnaMoleculeMap);

        // When

        var result = systemUnderTest.calculateDnaBaseCount(dnaSequence);

        // Then
        assertThat(result).isEqualTo(dnaBaseCount);
    }
}
