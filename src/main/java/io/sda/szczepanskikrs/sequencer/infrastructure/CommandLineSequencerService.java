package io.sda.szczepanskikrs.sequencer.infrastructure;

import io.sda.szczepanskikrs.sequencer.api.BaseCountConverter;
import io.sda.szczepanskikrs.sequencer.api.dto.DnaBaseCountDto;
import io.sda.szczepanskikrs.sequencer.api.dto.ThymineDto;
import io.sda.szczepanskikrs.sequencer.dna.DnaBaseCount;
import io.sda.szczepanskikrs.sequencer.dna.DnaBaseCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.function.Consumer;

@Service
public class CommandLineSequencerService implements CommandLineRunner {
    // Finalne pole wstrzykiwane przez konstruktor.
    private final DnaBaseCounter baseCounter;
    private final BaseCountConverter<DnaBaseCountDto> converter;
    private final BaseCountConverter<ThymineDto> thymineDtoBaseCountConverter;
    private final Consumer<String> outputConsumer;

    //Autowired nie jest wymagane ale jest dobrą praktyką przy wstrzykiwaniu przez konstruktor.
    @Autowired
    public CommandLineSequencerService(
            DnaBaseCounter baseCounter,
            BaseCountConverter<DnaBaseCountDto> converter,
            BaseCountConverter<ThymineDto> thymineDtoBaseCountConverter, Consumer<String> outputConsumer) {
        this.baseCounter = baseCounter;
        this.converter = converter;
        this.thymineDtoBaseCountConverter = thymineDtoBaseCountConverter;
        this.outputConsumer = outputConsumer;
    }

    @Override
    // Opdala się po starcie aplikacji
    public void run(String... args) throws Exception {
        String sequence = "CGTAAAAAATTACAACGTCCTTTGGCTATCTCTTAAACTCCTGCTAAATGCTCGTGCTTTCCAATTATGTAAGCGTTCCGAGACGGGGTGGTCGATTCTGAGGACAAAGGTCAAGATGGAGCGCATCGAACGCAATAAGGATCATTTGATGGGACGTTTCGTCGACAAAGTCTTGTTTCGAGAGTAACGGCTACCGTCTTCGATTCTGCTTATAACACTATGTTCTTATGAAATGGATGTTCTGAGTTGGTCAGTCCCAATGTGCGGGGTTTCTTTTAGTACGTCGGGAGTGGTATTATATTTAATTTTTCTATATAGCGATCTGTATTTAAGCAATTCATTTAGGTTATCGCCGCGATGCTCGGTTCGGACCGCCAAGCATCTGGCTCCACTGCTAGTGTCCTAAATTTGAATGGCAAACACAAATAAGATTTAGCAATTCGTGTAGACGACCGGGGACTTGCATGATGGGAGCAGCTTTGTTAAACTACGAACGTAAT";
        // Konwersja do DTO
        DnaBaseCount dnaBaseCount = baseCounter.calculateDnaBaseCount(sequence);
        DnaBaseCountDto dto = converter.convertToType(dnaBaseCount);
        // Konwersja do Thyminy
        ThymineDto thymineDto = thymineDtoBaseCountConverter.convertToType(dnaBaseCount);
        // Printujemy DnaBaseCountDto
        outputConsumer.accept(dto.toString());
        // Printujemy ThymineDTO
        outputConsumer.accept(thymineDto.toString());
    }

    @PostConstruct
    // Odpala się po stworzeniu beana.
    public void printDate() {
        System.out.println(Instant.now());
    }
}


/**
 * @SpringBootApplication - Startuje aplikacje (od classpatha przez skanowanie komponentów)
 * @SpringBootTest - Przygotowuje kontekst testowy i startuje aplikacje gotową do testów.
 * @Component - Oznacza co ma być wzięte pod uwagę przy starcie aplikacji (na poziomie klasy).
 * @Bean -  Oznacza co ma być wzięte pod uwagę przy starcie aplikacji (na poziomie metody).
 * @Service = @Component
 * @Respository = @Component
 * @Configuration = Oznacza że dana klasa zawiera konfigurajcę w postaci @Beanów
 * @TestConfiguration = @Configuration tylko z Beanami które są testowe.
 * @Autowired = pola, metody i konstruktory które mają być wstrzyknięte
 * @Primary = w przypadku kiedy mamy wiele komponentów wybieraj ten jako domyślny
 * @Scope = definiuje ile instancji będzie tworzonych i jaki będzie ich cykl życia ( domyślnie Singleton ).
 * - singleton
 * - prototype
 * - request
 * - session
 * - globalSession
 * - application

 * @PreDestory - Może(ale nie musi) być wywołany kiedy bean jest niszczony przez kontekst.
 * @PostConstruct - Wywoływane po stworzeniu beana, do wszelkiego rodzaju setupów.
 * @ComponentScan - Pozwala na zdefiniowanie pakietów które mają być skanowane, domyślnie skanuje pakiet klasy na którą go nakładamy.
 */