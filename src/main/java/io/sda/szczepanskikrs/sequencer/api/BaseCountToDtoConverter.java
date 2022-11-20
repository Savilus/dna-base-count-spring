package io.sda.szczepanskikrs.sequencer.api;

import io.sda.szczepanskikrs.sequencer.api.dto.DnaBaseCountDto;
import io.sda.szczepanskikrs.sequencer.dna.DnaBaseCount;
import io.sda.szczepanskikrs.sequencer.dna.DnaMolecule;
import org.springframework.stereotype.Component;

// Rejestrujemy komponent
@Component
public class BaseCountToDtoConverter implements BaseCountConverter<DnaBaseCountDto> {
    @Override
    public DnaBaseCountDto convertToType(DnaBaseCount input) {
        // Konwersja DnaBaseCount -> DnaBaseCountDto
        var dnaBaseCountMap = input.rawBaseCount();

        return new DnaBaseCountDto(
                dnaBaseCountMap.get(DnaMolecule.ADENINE).intValue(),
                dnaBaseCountMap.get(DnaMolecule.THYMINE).intValue(),
                dnaBaseCountMap.get(DnaMolecule.GUANINE).intValue(),
                dnaBaseCountMap.get(DnaMolecule.CYTOSINE).intValue()
        );
    }
}
