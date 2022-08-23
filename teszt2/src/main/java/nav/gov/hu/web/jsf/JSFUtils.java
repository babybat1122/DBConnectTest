package nav.gov.hu.web.jsf;

import hu.gov.nav.bef.commons.lib.IGlobalConstants;
import hu.gov.nav.bef.commons.web.jsf.JSFUtilsBase;
import nav.gov.hu.IWebAppConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * JSF testreszabott statikus utility-k
 *
 * @author bt
 */
@Slf4j
public class JSFUtils extends JSFUtilsBase {

    /**
     * Logout üzenettel
     *
     * @param callFromBevfeldMenu BevFeldMenu-ből lett hívva az alkalmazás?
     * @param logoutParamName üzenet osztály szint
     * @param logoutMsg megjelenítendő üzenet
     */
    public static void logOut(boolean callFromBevfeldMenu, String logoutParamName, String logoutMsg) {

        //Ha BevFeld menüből hívták, akkor csak egy hibalapot mutatunk meg, majd becsukjuk az alkalmazást
        if (callFromBevfeldMenu) {
            //Ha hiba van, akkor megmutatjuk a hibaüzenetet
            if (!IGlobalConstants.LOGOUT_INFO_MSG_CLASS.equals(logoutParamName)) {
                JSFUtils.redirect(IWebAppConstants.PAGE_ERRORPAGE_TO_BEVFELDMENU, logoutParamName, logoutMsg);
            }
            return;
        }

        //Redir a loginra - a paramter cookie-ban lesz átadva a login JSF lapnak
        JSFUtils.redirect(IWebAppConstants.PAGE_LOGIN, logoutParamName, logoutMsg);

        JSFUtilsBase.killSession();
    }

    /**
     * Hónapnév -> hónapszám konverzió
     *
     * @param honapNev
     *
     * @return
     */
    public static String honapNevekToHonapSzam(String honapNev) {
        switch (honapNev.toLowerCase()) {
            case "január":
                return "01";
            case "február":
                return "02";
            case "március":
                return "03";
            case "április":
                return "04";
            case "május":
                return "05";
            case "június":
                return "06";
            case "július":
                return "07";
            case "augusztus":
                return "08";
            case "szeptember":
                return "09";
            case "október":
                return "10";
            case "november":
                return "11";
            case "december":
                return "12";
        }

        return "ismeretlen";
    }
}
