package lt.vu.PSK1lab.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.PSK1lab.entities.Band;

import java.util.List;

@Getter
@Setter
public class FestivalDto {
    private String name;
    private List<BandDto> bands;
}