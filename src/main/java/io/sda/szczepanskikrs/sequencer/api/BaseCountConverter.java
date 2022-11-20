package io.sda.szczepanskikrs.sequencer.api;

import io.sda.szczepanskikrs.sequencer.dna.DnaBaseCount;

public interface BaseCountConverter<T> {
    T convertToType(DnaBaseCount input);
}
