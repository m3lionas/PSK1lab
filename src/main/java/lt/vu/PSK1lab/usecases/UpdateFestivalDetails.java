package lt.vu.PSK1lab.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.PSK1lab.entities.Festival;
import lt.vu.PSK1lab.interceptors.LoggedInvocation;
import lt.vu.PSK1lab.persistence.FestivalsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@RequestScoped
@Named
@Getter @Setter
public class UpdateFestivalDetails implements Serializable, IUpdateFestivalDetails {

    private Festival festival;

    @Inject
    private FestivalsDAO festivalsDAO;

    @Inject
    private IUpdateFestivalDetails updateFestivalDetails;

    @PostConstruct
    private void init() {
        System.out.println("UpdateFestivalName INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long festivalId = Long.parseLong(requestParameters.get("festivalId"));
        this.festival = festivalsDAO.findOne(festivalId);
    }

    @Override
    public void checkFestivalName(String festivalName)
    {
        if ( this.festival.getName().contains(",")) {
            throw new IllegalArgumentException("Festival name cannot contain , character");
        }
    }

    @Override
    @Transactional
    @LoggedInvocation
    public String updateFestivalName() {
        updateFestivalDetails.checkFestivalName(this.festival.getName());
        try{
            festivalsDAO.update(this.festival);
        } catch (OptimisticLockException e) {
            return "/bands.xhtml?faces-redirect=true&festivalId=" + this.festival.getId() + "&error=optimistic-lock-exception";
        }
        return "index.xhtml?faces-redirect=true";
    }
}