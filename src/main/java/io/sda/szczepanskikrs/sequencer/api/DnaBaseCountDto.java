package io.sda.szczepanskikrs.sequencer.api;

import lombok.Data;

@Data
public class DnaBaseCountDto {
   final Integer aCount;
   final Integer tCount;
   final Integer gCount;
   final Integer cCount;
}
