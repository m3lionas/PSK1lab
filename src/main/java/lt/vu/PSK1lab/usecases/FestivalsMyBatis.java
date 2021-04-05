package lt.vu.PSK1lab.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.PSK1lab.mybatis.dao.FestivalMapper;
import lt.vu.PSK1lab.mybatis.model.Festival;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class FestivalsMyBatis {
    @Inject
    private FestivalMapper festivalMapper;

    @Getter
    private List<Festival> allFestivals;

    @Getter @Setter
    private Festival festivalToCreate = new Festival();

    @PostConstruct
    public void init() {
        this.loadAllFestivals();
    }

    private void loadAllFestivals() {
        this.allFestivals = festivalMapper.selectAll();
    }

    @Transactional
    public String createFestival() {
        festivalMapper.insert(festivalToCreate);
        return "/mybatis/festivals?faces-redirect=true";
    }
}
