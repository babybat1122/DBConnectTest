package nav.gov.hu;

import hu.gov.nav.bef.commons.lib.IGlobalConstants;
import java.text.SimpleDateFormat;

/**
 *
 * @author u626374
 */
public interface IWebAppConstants extends IGlobalConstants {

    //Audit naplózáshoz
    String IMPK_RENDSZER_NEV = "BEF";

    String USERS_GUIDE_URL = "/teszt2/teszt2 - felhasználói kézikönyv.pdf";

    String APPNAME = "teszt2";
    String LONG_APPNAME = "BEVFELD teszt2 Project";
    String PAGE_MAINFORM = "/faces/pages/mainPage.xhtml";

    //Jogok nevek
    String ROLE_ADMIN = "APP_BEF_ADM";
    String ROLE_LEKERDEZO = "APP_BEF_TLEK";
    String ROLE_LEKERDEZO_REGIOS = "APP_BEF_RLEK";
    String ROLE_LEKERDEZO_ORSZAGOS = "APP_BEF_MLAT";
    String ROLE_LEKERDEZO_51 = "APP_BEF_LK51";
    String ROLE_EBEV_TERVEZOI = "APP_QB42";

    //Üzemeltetői menü
    String ROLE_BEFADM_ALKALAMAZASNAPLO = "APP_BEF_ADM";

    String PROJECT_STAGE_EBEVALLAS_TEST = "Test";

    String PAGE_WELCOME = "/faces/pages/afterLogin.xhtml";
    String PAGE_LOGIN = "/faces/pages/testuser/testUserMegadasa.xhtml";
    String PAGE_NINCSJOGA = "/faces/error/403-forbidden.xhtml";
    String PAGE_ERRORPAGE_TO_BEVFELDMENU = "/faces/error/errorToBevFeldMenu.xhtml"; //Menüből hívták, de van bejelentkezési hiba, amit meg kell mutatni

    //Dátum formázás
    SimpleDateFormat SDF = new SimpleDateFormat(IGlobalConstants.DATE_FORMAT_PATTERN);
    SimpleDateFormat SDTF = new SimpleDateFormat(IWebAppConstants.DATE_TIME_FORMAT_PATTERN);

}
