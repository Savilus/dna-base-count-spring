package io.sda.szczepanskikrs.sequencer.api.dto;

import lombok.Data;

@Data
public class DnaBaseCountDto {

   final Integer aCount;
   final Integer tCount;
   final Integer gCount;
   final Integer cCount;

   @Override
   public String toString() {
      return "DnaBaseCountDto{" +
              "adenineCount=" + aCount +
              ", thymineCount=" + tCount +
              ", guanineCount=" + gCount +
              ", cytosineCount=" + cCount +
              '}';
   }
}
