package lt.vu.PSK1lab.usecases;

import lombok.Getter;
import lt.vu.PSK1lab.entities.Band;
import lt.vu.PSK1lab.persistence.BandsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class Bands {
    @Inject
    BandsDAO bandsDAO;

    @Getter
    List<Band> allBands;

    @PostConstruct
    public void init() {
        loadAllBands();
    }

    private void loadAllBands() {
        this.allBands = bandsDAO.loadAll();
    }

}
