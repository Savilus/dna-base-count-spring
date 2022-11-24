package io.sda.szczepanskikrs.sequencer.dna;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StreamDnaBaseCounter implements DnaBaseCounter {
    @Override
    public DnaBaseCount calculateDnaBaseCount(String rawBase) {
        return new DnaBaseCount(rawBase.chars()
                .mapToObj(o -> (char) o)
                .map(DnaMolecule::fromLetter)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        );
    }

}
