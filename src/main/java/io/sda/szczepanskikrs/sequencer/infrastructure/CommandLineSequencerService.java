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
import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class CommandLineSequencerService implements CommandLineRunner {
    // Finalne pole wstrzykiwane przez konstruktor.
    private final DnaBaseCounter baseCounter;
    private final BaseCountConverter<DnaBaseCountDto> converter;
    private final BaseCountConverter<ThymineDto> thymineDtoBaseCountConverter;
    private final Consumer<String> outputConsumer;
    private final Supplier<LocalDateTime> localDateTime;

    //Autowired nie jest wymagane ale jest dobrą praktyką przy wstrzykiwaniu przez konstruktor.
    @Autowired
    public CommandLineSequencerService(
            DnaBaseCounter baseCounter,
            BaseCountConverter<DnaBaseCountDto> converter,
            BaseCountConverter<ThymineDto> thymineDtoBaseCountConverter,
            Consumer<String> outputConsumer,
            Supplier<LocalDateTime> localDateTime) {
        this.baseCounter = baseCounter;
        this.converter = converter;
        this.thymineDtoBaseCountConverter = thymineDtoBaseCountConverter;
        this.outputConsumer = outputConsumer;
        this.localDateTime = localDateTime;
    }

    @Override
    // Opdala się po starcie aplikacji
    public void run(String... args) throws Exception {
        String sequence = "";
        // Konwersja do DTO
        long start = System.nanoTime();
        DnaBaseCount dnaBaseCount = baseCounter.calculateDnaBaseCount(sequence);
        long stop = System.nanoTime();
        DnaBaseCountDto dto = converter.convertToType(dnaBaseCount);
        // Konwersja do Thyminy
        ThymineDto thymineDto = thymineDtoBaseCountConverter.convertToType(dnaBaseCount);
        // Printujemy DnaBaseCountDto oraz czas w jakim są wykonane obliczenia
        outputConsumer.accept(dto.toString());
        outputConsumer.accept("Total time of calculations: " + ((stop - start) * 0.000001) + " milliseconds");
        // Printujemy ThymineDTO
        outputConsumer.accept(thymineDto.toString());
        // Printujemy czas w którym wykonała się metoda run
        outputConsumer.accept("LocalDateTime: " + localDateTime.get());

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