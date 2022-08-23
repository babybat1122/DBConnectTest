package nav.gov.hu.web.jsf;

import hu.gov.nav.bef.commons.web.jsf.AbstractJSFFunctions;
import hu.gov.nav.bef.commons.web.model.cache.BevFeldKodokCache;
import nav.gov.hu.IWebAppConstants;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

/**
 * JSF testreszabott függvények
 *
 * @author bt
 */
@Named
@ApplicationScoped
@Slf4j
public class JsfFunctions extends AbstractJSFFunctions {

    @Inject
    private BevFeldKodokCache bevFeldKodokCache;

    @Override
    public String getAppName() {
        return IWebAppConstants.APPNAME;
    }

    @Override
    public String getLongAppName() {
        return IWebAppConstants.LONG_APPNAME;
    }

    @Override
    public String getDatabaseInstanceName() {
        return bevFeldKodokCache.getDatabaseInstanceName();
    }

    @Override
    public String getUsersGuideUrl() {
        return this.getDocPublisherRestUriStr() + IWebAppConstants.USERS_GUIDE_URL;
    }

    /**
     * Az DocPublisher REST full URI-ja
     *
     * @return szerver URI str
     */
    private String getDocPublisherRestUriStr() {
        try {
            FacesContext ctxt = FacesContext.getCurrentInstance();
            ExternalContext ext = ctxt.getExternalContext();
            URI uri = new URI(ext.getRequestScheme(),
                    null, ext.getRequestServerName(), ext.getRequestServerPort(),
                    //ext.getRequestContextPath(),
                    "/DocPublisher/rest/docs",
                    null, null);
            String uriStr = uri.toASCIIString();

            return uriStr;

        } catch (URISyntaxException e) {
            log.error("hiba", e);
        }

        return null;
    }

    /**
     * Hónap nevek
     *
     * @return
     */
    public List<String> getHonapNevek() {
        List<String> result = new LinkedList<>();
        result.add("Január");
        result.add("Február");
        result.add("Március");
        result.add("Április");
        result.add("Május");
        result.add("Június");
        result.add("Július");
        result.add("Augusztus");
        result.add("Szeptember");
        result.add("Október");
        result.add("November");
        result.add("December");

        return result;
    }

    @Override
    /**
     * Primefaces táblázat lapozó érték kitalálása Az összes rekordhoz képest
     * állítja össze a lehetséges rekord/lap választólist értékét
     *
     * @param tableRowCnt aktuális rekordok száma
     *
     * @return PF tábláknál a sorok száma
     */
    public String getRowsPerPageTemplate(int tableRowCnt) {

        StringBuilder sb = new StringBuilder();

        sb.append("15");

        //----------------------------------------
        if (tableRowCnt > 30) {
            sb.append(",30");
        }
        if (tableRowCnt > 30 && tableRowCnt <= 50) {
            sb.append(",50");
            return sb.toString();
        }
        //----------------------------------------
        if (tableRowCnt > 50) {
            sb.append(",50");
        }
        if (tableRowCnt > 50 && tableRowCnt <= 75) {
            sb.append(",").append(tableRowCnt);
            return sb.toString();
        }
        //----------------------------------------
        if (tableRowCnt > 75) {
            sb.append(",75");
        }
        if (tableRowCnt > 75 && tableRowCnt <= 100) {
            sb.append(",").append(tableRowCnt);
            return sb.toString();
        }
        //----------------------------------------
        if (tableRowCnt > 100) {
            sb.append(",100");
        }
        if (tableRowCnt > 101 && tableRowCnt <= 500) {
            sb.append(",").append(tableRowCnt);
            return sb.toString();
        }
        //----------------------------------------
        if (tableRowCnt > 500) {
            sb.append(",500");
        }
        if (tableRowCnt > 501 && tableRowCnt <= 1_000) {
            sb.append(",").append(tableRowCnt);
            return sb.toString();
        }
        //----------------------------------------
        if (tableRowCnt > 1_000) {
            sb.append(",1000");
        }
        if (tableRowCnt > 1_001 && tableRowCnt <= 5_000) {
            sb.append(",").append(tableRowCnt);
            return sb.toString();
        }
        //----------------------------------------
        if (tableRowCnt > 5_000) {
            sb.append(",5000");
        }
        if (tableRowCnt > 5_001 && tableRowCnt <= 6_000) {
            sb.append(",").append(tableRowCnt);
        }

        return sb.toString();
    }
}
