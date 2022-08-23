/*
 *  ------------------------------------------------------------------------------------
 *
 *  teszt2 project
 *
 *  Module:  teszt2 (teszt2)
 *  File:    dinoService.java
 *  Created: 2022.08.23. 10:51:21
 *
 *  @Author  u320729
 *
 *  ------------------------------------------------------------------------------------
 */
package nav.gov.hu.service;

import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nav.gov.hu.entity.Fact;

/**
 *
 * @author u320729
 */
@Slf4j
@Stateless
public class dinoService {

    @PersistenceContext
    private EntityManager em2;

    public Fact getFact(int id) {
        TypedQuery<Fact> fquery = em2.createNamedQuery("Fact.findById", Fact.class);
        fquery.setParameter("rnd", id);
        return fquery.getSingleResult();
    }

    @Getter
    @Setter
    public int rnd;
    Random r = new Random();

    @Getter
    @Setter
    public int min = 1;

    @Getter
    @Setter
    public int max = 20;

    @PostConstruct
    public void getRndNumber() {
        int rnd = r.nextInt(max + 1 - min) + min;
    }
}
