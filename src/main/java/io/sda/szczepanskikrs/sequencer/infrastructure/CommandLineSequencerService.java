package io.sda.szczepanskikrs.sequencer.infrastructure;

import io.sda.szczepanskikrs.sequencer.dna.DnaBaseCounter;
import io.sda.szczepanskikrs.sequencer.dna.StreamDnaBaseCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Service
public class CommandLineSequencerService implements CommandLineRunner {
    private final DnaBaseCounter baseCounter;

    @Autowired
    public CommandLineSequencerService(DnaBaseCounter baseCounter) {
        this.baseCounter = baseCounter;
    }

    @Override
    // Opdala się po starcie aplikacji
    public void run(String... args) throws Exception {
        String sequence = "CGTAAAAAATTACAACGTCCTTTGGCTATCTCTTAAACTCCTGCTAAATGCTCGTGCTTTCCAATTATGTAAGCGTTCCGAGACGGGGTGGTCGATTCTGAGGACAAAGGTCAAGATGGAGCGCATCGAACGCAATAAGGATCATTTGATGGGACGTTTCGTCGACAAAGTCTTGTTTCGAGAGTAACGGCTACCGTCTTCGATTCTGCTTATAACACTATGTTCTTATGAAATGGATGTTCTGAGTTGGTCAGTCCCAATGTGCGGGGTTTCTTTTAGTACGTCGGGAGTGGTATTATATTTAATTTTTCTATATAGCGATCTGTATTTAAGCAATTCATTTAGGTTATCGCCGCGATGCTCGGTTCGGACCGCCAAGCATCTGGCTCCACTGCTAGTGTCCTAAATTTGAATGGCAAACACAAATAAGATTTAGCAATTCGTGTAGACGACCGGGGACTTGCATGATGGGAGCAGCTTTGTTAAACTACGAACGTAAT";
        System.out.println(baseCounter.calculateDnaBaseCount(sequence));
    }

    @PostConstruct
    // Odpala się po stworzeniu beana.
    public void printDate() {
        System.out.println(Instant.now());
    }
}
