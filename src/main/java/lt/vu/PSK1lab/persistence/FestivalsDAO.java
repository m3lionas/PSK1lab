package lt.vu.PSK1lab.persistence;

import lt.vu.PSK1lab.entities.Festival;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class FestivalsDAO {

    @Inject
    private EntityManager em;

    public List<Festival> loadAll() {
        return em.createNamedQuery("Festival.findAll", Festival.class).getResultList();
    }
    public void persist(Festival festival){
        this.em.persist(festival);
    }

    public Festival findOne(Long id){
        return em.find(Festival.class, id);
    }

    public Festival update(Festival festival){
        return em.merge(festival);
    }
}
