package io.sda.szczepanskikrs.sequencer.api;

import io.sda.szczepanskikrs.sequencer.api.dto.ThymineDto;
import io.sda.szczepanskikrs.sequencer.dna.DnaBaseCount;
import io.sda.szczepanskikrs.sequencer.dna.DnaMolecule;
import org.springframework.stereotype.Component;

@Component
public class ThymineBaseCountConverter implements BaseCountConverter<ThymineDto> {

    @Override
    public ThymineDto convertToType(DnaBaseCount input) {
        return new ThymineDto(input.rawBaseCount().get(DnaMolecule.THYMINE).intValue());
    }
}
