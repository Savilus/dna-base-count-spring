package io.sda.szczepanskikrs.sequencer.dna;

public enum DnaMolecule {
    ADENINE, CYTOSINE, GUANINE, THYMINE;

    static final String MOLECULE_REGEX =  "^[A|a|C|c|G|g|T|t]*$";

    static DnaMolecule fromLetter(char letter) {
        return switch (letter) {
            case 'A', 'a' -> ADENINE;
            case 'C', 'c' -> CYTOSINE;
            case 'G', 'g' -> GUANINE;
            case 'T', 't' -> THYMINE;
            default -> throw new IllegalStateException("Unsupported molecule value: " + letter);
        };
    }
}
