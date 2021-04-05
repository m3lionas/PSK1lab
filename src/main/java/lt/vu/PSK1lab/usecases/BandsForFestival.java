package lt.vu.PSK1lab.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lt.vu.PSK1lab.entities.Band;
import lt.vu.PSK1lab.entities.Festival;
import lt.vu.PSK1lab.persistence.BandsDAO;
import lt.vu.PSK1lab.persistence.FestivalsDAO;

@Model
public class BandsForFestival implements Serializable{

    @Inject
    private FestivalsDAO festivalsDAO;

    @Inject
    private BandsDAO bandsDAO;

    @Getter @Setter
    private Festival festival;

    @Getter
    private Set<Band> festivalBands;

    @Getter
    private List<Band> allBands;

    @Getter @Setter
    private Long bandToAddId;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long festivalId = Long.parseLong(requestParameters.get("festivalId"));
        this.festival = festivalsDAO.findOne(festivalId);
        this.festivalBands = this.festival.getBands();
        this.allBands = bandsDAO.loadAll();
    }

    @Transactional
    public String addBandToFestival() {
        festivalBands = this.festival.getBands();
        Band bandToAdd = bandsDAO.findOne(bandToAddId);
        festivalBands.add(bandToAdd);
        this.festival.setBands(festivalBands);
        festivalsDAO.update(this.festival);
        return "bands?faces-redirect=true&festivalId=" + this.festival.getId();
    }


}
