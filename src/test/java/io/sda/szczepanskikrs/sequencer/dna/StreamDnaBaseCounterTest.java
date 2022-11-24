package io.sda.szczepanskikrs.sequencer.dna;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StreamDnaBaseCounterTest {

    @ParameterizedTest
    @ValueSource(strings = {"ATCGQW!DAGTATTGAGCTCTAGCG", "ATXSACGA" , "A AAAKUAA", " ATCGATGCATGOICCCG", "TGGCCUIUYXACACGGT" , "TGGCCGGHJAAA" , " " , "TGGCCGGH%JAAA", "ATCGA#TTGAGCTCTAGCG"})
    public void shouldTrowExceptionIfSequenceIsNotValid(String rawBaseTest){
        var systemUnderTest = new StreamDnaBaseCounter();
        boolean thrown = false;

        // When
        try {
            var result = systemUnderTest.calculateDnaBaseCount(rawBaseTest);
        } catch (Exception e) {
            thrown = true;
        }
        // Then
        assertThat(thrown).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"ATCGATTGAGAACCGGTTCTCTAGCG" , "TAAAGTTTGGGCCGCCCAAACCGT", "GGAAACTGTGTGTGTGAAAGGTTCC" , "AAAAGGGTTGTTCCAAAGGTTCCTC", "AAAGGAAGGTCCTTGTTTCCCAGC" , "AGTCAGTCAGTCAGTCGGTACCCGGAT"})
    public void shouldCalculateMoleculesIfValidSequenceIsProvided(String rawBaseTest){
        var systemUnderTest = new StreamDnaBaseCounter();

        var result = systemUnderTest.calculateDnaBaseCount(rawBaseTest);

        assertThat(result.rawBaseCount().get(DnaMolecule.ADENINE) > 5);
        assertThat(result.rawBaseCount().get(DnaMolecule.CYTOSINE) > 5);
        assertThat(result.rawBaseCount().get(DnaMolecule.GUANINE) > 5);
        assertThat(result.rawBaseCount().get(DnaMolecule.THYMINE) > 5);
    }

    @ParameterizedTest
    @NullSource
    public void shouldThrowExceptionIfNullIsProvided(String input){
        var systemUnderTest = new StreamDnaBaseCounter();
        boolean thrown = false;

        try {
        var result =systemUnderTest.calculateDnaBaseCount(input);
        }catch (Exception e){
            thrown = true;
        }

        assertThat(thrown).isTrue();
    }

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

    @ParameterizedTest
    @EmptySource
    void shouldReturnEmptyMapIfSequenceIsEmptyParametrizedTest(String emptySource){
        var systemUnderTest = new StreamDnaBaseCounter();

        var result = systemUnderTest.calculateDnaBaseCount(emptySource);

        assertThat(result.rawBaseCount().isEmpty()).isTrue();
    }
}
