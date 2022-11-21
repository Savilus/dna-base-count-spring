package io.sda.szczepanskikrs.sequencer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Supplier;

// Springu weź moje metody i zrób z nich beany
@Configuration
@Slf4j
public class ApplicationConfig {

    @Bean
    // To będzie nasz komponent
    Consumer<String> output(){return log::info;}

    @Bean
    Supplier<LocalDateTime> localDateTimeSupplier() {
        Supplier<LocalDateTime> dateTimeSupplier = LocalDateTime::now;
        return dateTimeSupplier;
    }
}

