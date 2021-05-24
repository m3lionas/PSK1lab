package lt.vu.PSK1lab.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.PSK1lab.entities.Festival;
import lt.vu.PSK1lab.persistence.FestivalsDAO;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Festivals {

    @Inject
    private FestivalsDAO festivalsDAO;

    @Getter @Setter
    private Festival festivalToCreate = new Festival();

    @Getter
    private List<Festival> allFestivals;

    @PostConstruct
    public void init(){
        loadAllFestivals();
    }

    @Transactional
    public String createFestival(){
        this.festivalsDAO.persist(festivalToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllFestivals(){
        this.allFestivals = festivalsDAO.loadAll();
    }
}
