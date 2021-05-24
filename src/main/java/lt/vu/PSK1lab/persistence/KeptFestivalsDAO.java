package lt.vu.PSK1lab.persistence;

import lt.vu.PSK1lab.entities.Festival;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Alternative
public class KeptFestivalsDAO extends FestivalsDAO{
    @Inject
    EntityManager em;

    @Override
    public List<Festival> loadAll() {
        return em.createQuery("select f from Festival f where lower(f.state) not like '%cancelled%'", Festival.class).getResultList();
    }
}