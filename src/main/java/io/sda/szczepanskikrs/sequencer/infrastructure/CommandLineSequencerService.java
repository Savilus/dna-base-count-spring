package io.sda.szczepanskikrs.sequencer.infrastructure;

import io.sda.szczepanskikrs.sequencer.api.BaseCountConverter;
import io.sda.szczepanskikrs.sequencer.api.dto.DnaBaseCountDto;
import io.sda.szczepanskikrs.sequencer.dna.DnaBaseCount;
import io.sda.szczepanskikrs.sequencer.dna.DnaBaseCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Service
public class CommandLineSequencerService implements CommandLineRunner {
    // Finalne pole wstrzykiwane przez konstruktor.
    private final DnaBaseCounter baseCounter;
    private final BaseCountConverter<DnaBaseCountDto> converter;

    //Autowired nie jest wymagane ale jest dobrą praktyką przy wstrzykiwaniu przez konstruktor.
    @Autowired
    public CommandLineSequencerService(
            DnaBaseCounter baseCounter,
            BaseCountConverter<DnaBaseCountDto> converter
    ) {
        this.baseCounter = baseCounter;
        this.converter = converter;
    }

    @Override
    // Opdala się po starcie aplikacji
    public void run(String... args) throws Exception {
        String sequence = "CGTAAAAAATTACAACGTCCTTTGGCTATCTCTTAAACTCCTGCTAAATGCTCGTGCTTTCCAATTATGTAAGCGTTCCGAGACGGGGTGGTCGATTCTGAGGACAAAGGTCAAGATGGAGCGCATCGAACGCAATAAGGATCATTTGATGGGACGTTTCGTCGACAAAGTCTTGTTTCGAGAGTAACGGCTACCGTCTTCGATTCTGCTTATAACACTATGTTCTTATGAAATGGATGTTCTGAGTTGGTCAGTCCCAATGTGCGGGGTTTCTTTTAGTACGTCGGGAGTGGTATTATATTTAATTTTTCTATATAGCGATCTGTATTTAAGCAATTCATTTAGGTTATCGCCGCGATGCTCGGTTCGGACCGCCAAGCATCTGGCTCCACTGCTAGTGTCCTAAATTTGAATGGCAAACACAAATAAGATTTAGCAATTCGTGTAGACGACCGGGGACTTGCATGATGGGAGCAGCTTTGTTAAACTACGAACGTAAT";
        // Konwersja do DTO
        DnaBaseCount dnaBaseCount = baseCounter.calculateDnaBaseCount(sequence);
        DnaBaseCountDto dto = converter.convertToType(dnaBaseCount);

        // Printujemy DnaBaseCountDto
        System.out.println(dto);
    }

    @PostConstruct
    // Odpala się po stworzeniu beana.
    public void printDate() {
        System.out.println(Instant.now());
    }
}
