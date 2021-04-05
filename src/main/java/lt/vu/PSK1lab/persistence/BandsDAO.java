package lt.vu.PSK1lab.persistence;

import lt.vu.PSK1lab.entities.Band;
import lt.vu.PSK1lab.entities.Festival;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class BandsDAO {

    @Inject
    private EntityManager em;

    public List<Band> loadAll() {
        return em.createNamedQuery("Band.findAll", Band.class).getResultList();
    }

    public void persist(Band band){
        this.em.persist(band);
    }

    public Band findOne(Long id){
        return em.find(Band.class, id);
    }

    public Band update(Band band){
        return em.merge(band);
    }
}
