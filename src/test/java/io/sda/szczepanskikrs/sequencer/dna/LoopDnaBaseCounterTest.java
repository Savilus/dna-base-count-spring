package io.sda.szczepanskikrs.sequencer.dna;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LoopDnaBaseCounterTest {

    @Test
    public void should_calculateExpectedBaseCount_when_validSequenceProvided() {
        // Given
        Map<DnaMolecule, Long> dnaMoleculeMap = new HashMap<>();
        DnaBaseCount dnaBaseCount = new DnaBaseCount(dnaMoleculeMap);
        dnaMoleculeMap.put(DnaMolecule.ADENINE, 10l);
        dnaMoleculeMap.put(DnaMolecule.CYTOSINE, 10l);
        dnaMoleculeMap.put(DnaMolecule.GUANINE, 10l);
        dnaMoleculeMap.put(DnaMolecule.THYMINE, 10l);

        var systemUnderTest = new LoopDnaBaseCounter();
        var dnaSequence = "ACATCTGCGGTCGAACTATTAACCACCCGGGTAGGGTTAT";

        // When
        var result = systemUnderTest.calculateDnaBaseCount(dnaSequence);

        // Then

        assertThat(result).isEqualTo(dnaBaseCount);
    }

    @Test
    public void shouldThrowExceptionWhileNoValidSequenceIsProvided() {
        // Given
        var systemUnderTest = new StreamDnaBaseCounter();
        var dnaSequence = "!ATCGATTGAGCTCTAGCG";
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
        var dnaSequence = "ATCGATTGAGCTC TAGCG";
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

        // When

        var result = systemUnderTest.calculateDnaBaseCount(dnaSequence);

        // Then
        assertThat(result.rawBaseCount().isEmpty()).isTrue();
    }

}