package nav.gov.hu.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import nav.gov.hu.entity.Animal;

/**
 *
 * @author u320729
 */
@Slf4j
@Stateless
public class testService {

    @PersistenceContext
    private EntityManager em;

    public List<Animal> getAllatList(String faj) {

        TypedQuery<Animal> query = em.createNamedQuery("Animal.findByFaj", Animal.class);
        query.setParameter("allfaj", faj);
        return query.getResultList();
        //List<Animal> resultList = em.createNamedQuery("Animal.findByFaj", Animal.class).getResultList();
        //return resultList;
    }
}
