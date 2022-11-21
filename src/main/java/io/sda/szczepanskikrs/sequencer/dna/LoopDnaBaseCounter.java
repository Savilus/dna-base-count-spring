package io.sda.szczepanskikrs.sequencer.dna;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class LoopDnaBaseCounter implements DnaBaseCounter{
    @Override
    public DnaBaseCount calculateDnaBaseCount(String rawBase) {
            String[] splitRawBase = rawBase.split("");

            Map<DnaMolecule, Long> dnaMoleculeMap = new HashMap<>();
            DnaBaseCount dnaBaseCount = new DnaBaseCount(dnaMoleculeMap);

        for(int i = 0; i < splitRawBase.length; i++){
            char molecule = splitRawBase[i].charAt(0);
            if(!dnaMoleculeMap.containsKey(DnaMolecule.fromLetter(molecule))){
                dnaMoleculeMap.put(DnaMolecule.fromLetter(molecule), 1l);
                continue;
            }
                switch (molecule){
                    case 'A' , 'a' -> dnaMoleculeMap.put(DnaMolecule.ADENINE, dnaMoleculeMap.get(DnaMolecule.ADENINE) + 1);
                    case 'C' , 'c' -> dnaMoleculeMap.put(DnaMolecule.CYTOSINE, dnaMoleculeMap.get(DnaMolecule.CYTOSINE) + 1);
                    case 'G' , 'g' -> dnaMoleculeMap.put(DnaMolecule.GUANINE, dnaMoleculeMap.get(DnaMolecule.GUANINE) + 1);
                    case 'T' , 't' -> dnaMoleculeMap.put(DnaMolecule.THYMINE, dnaMoleculeMap.get(DnaMolecule.THYMINE) + 1);
                    default -> throw new IllegalStateException("Wrong DNA molecule!");

                }
            }
            return dnaBaseCount;
    }
}
