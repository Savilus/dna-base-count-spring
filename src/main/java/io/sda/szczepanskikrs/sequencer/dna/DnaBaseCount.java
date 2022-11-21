package io.sda.szczepanskikrs.sequencer.dna;

import java.util.Map;

public record DnaBaseCount(Map<DnaMolecule, Long> rawBaseCount) {
}

