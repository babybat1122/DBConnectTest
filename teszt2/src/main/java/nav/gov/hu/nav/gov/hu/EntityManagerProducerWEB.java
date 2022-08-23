package nav.gov.hu.nav.gov.hu;

import hu.gov.nav.bef.commons.lib.model.service.SecurityDBServiceEntityManager;
import hu.gov.nav.bef.commons.web.cdi.qualifiers.BevFeldWebUtilsEntityManager;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Web módú EntityManager injektor producer
 * <p>
 * Minden injektálás újabb példányt hoz létre, mert az EntityManager nem
 * szálbiztos Webes alkalmazásnál ennek amegoldásnak nem sok értleme van
 * /egyszterűbb lenne a sima '@PersistenceContext(unitName =...)' annotáció / De
 * a webes és batch alkalmazásban azonos Service osztályokat használunk más-más
 * EntityManage injektálással Emiatt a Web és a Batch módban más és más az
 * EntityManage provider
 * <p>
 * Az osztálynak nincs CDI szkópja, emiatt lesz minden esetben új példány az
 * injektálás során
 *
 * @author u626374
 */
@Slf4j
public class EntityManagerProducerWEB {

    @PersistenceContext
    private EntityManager em;

    /**
     * CDI Entity manager injektáló Az olyan osztályok, amelyeket közösen
     * használ a Batch és a web, azoknak itt állítunk elő EntityManager példányt
     *
     * @return saját EntityManager példány
     */
    @Produces
    @BevFeldWebUtilsEntityManager //WebUtils, Törzs
    @SecurityDBServiceEntityManager //Az EBevallasCommons csomagban helyet kapó hu.gov.nav.bevfeld.ebevallas.commons.model.service.SecurityUtilsDBService osztály számára állítja elő az EntityManager példányt
    public EntityManager createEntityManager() {
        return em;
    }

}
