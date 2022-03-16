package pl.rb.zadanie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class Unifeed {
    String instrument;
    String bid;
    String ask;
    String time;

}
