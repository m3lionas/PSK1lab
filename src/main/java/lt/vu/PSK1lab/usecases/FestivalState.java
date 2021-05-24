package lt.vu.PSK1lab.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.PSK1lab.entities.Festival;
import lt.vu.PSK1lab.persistence.FestivalsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class FestivalState implements Serializable {
    @Inject
    private FestivalsDAO festivalsDAO;

    @Getter
    @Setter
    private List<String> states;

    @Getter
    @Setter
    private Festival festival;

    @Getter
    @Setter
    private String selectedState;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long festivalId = Long.parseLong(requestParameters.get("festivalId"));
        this.festival = festivalsDAO.findOne(festivalId);

        this.states = Arrays.asList("Cancelled", "To-be", "Ongoing", "Passed");
    }

    @Transactional
    public String changeFestivalState() {
        this.festival.setState(selectedState);
        try {
            festivalsDAO.update(this.festival);
        }
        catch (OptimisticLockException e) {
            return "/bands.xhtml?faces-redirect=true&festivalId=" + this.festival.getId() + "&error=optimistic-lock-exception";
        }
        return "bands?faces-redirect=true&festivalId=" + this.festival.getId();
    }
}
