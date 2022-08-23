package nav.gov.hu.web.view;

import hu.gov.nav.bef.commons.lib.IGlobalConstants;
import hu.gov.nav.bef.commons.lib.exception.BevFeldException;
import hu.gov.nav.bef.commons.web.view.LoginResult;
import hu.gov.nav.bef.commons.web.view.SessionManagerBase;
import nav.gov.hu.IWebAppConstants;
import nav.gov.hu.web.jsf.JSFUtils;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author u626374
 */
@Named
@SessionScoped
@Slf4j
public class SessionManager extends SessionManagerBase {

    private static final long serialVersionUID = 1L;

    @PostConstruct
    public void init() {
        initLoginPage();
        log.trace(String.format("SessionManager - init, remoteUser: %s, sessionID: %s", JSFUtils.getRemoteUser(), JSFUtils.getSessionID()));
    }

    /**
     *
     */
    @PreDestroy
    public void destroy() {
        log.trace(String.format("SessionManager - destroy, remoteUser: %s, sessionID: %s", JSFUtils.getRemoteUser(), JSFUtils.getSessionID()));
    }

    /**
     * Login lap beállítása
     * <p>
     */
    @Override
    protected void initLoginPage() {
        //Login page beállítása
        super.loginPage = IWebAppConstants.PAGE_LOGIN;
    }

    /**
     * Bejelentkezés
     *
     * @throws BevFeldException
     */
    public void login() throws BevFeldException {

        //Leszedjük a bejelentkezett felhasználó nevet
        String remoteUser = JSFUtils.getRemoteUser();

        //Ha nem található a remoteUser(), akkor baj van...
        if (remoteUser == null) {
            log.warn("null a getRemoteUser()!!");
            JSFUtils.logOut(super.isCallFromBevFeldMenu(), IGlobalConstants.LOGOUT_ERROR_MSG_CLASS, "Nem állapítható meg a bejelentkezett user (getRemoteUser())");
            return;
        }

        //Bejelentkeztetés, DolgozoDTO feltöltés, srtb...
        LoginResult loginResult = super.continueLoginProcedure();

        //Ha van hiba a bejeneltkezés alatt
        if (LoginResult.ErrorCode.LOGIN_OK != loginResult.getErrorCode()) {

            log.warn(loginResult.getMsg());

            switch (loginResult.getErrorCode()) {

                case NINCS_IGAZGATOSAG_KODJA:
                    JSFUtils.logOut(super.isCallFromBevFeldMenu(), IGlobalConstants.LOGOUT_ERROR_MSG_CLASS, loginResult.getMsg());
                    return;

                case SZI_RENDSZER_NEM_ERHETO_EL:
                    JSFUtils.logOut(super.isCallFromBevFeldMenu(), IGlobalConstants.LOGOUT_WARN_MSG_CLASS, loginResult.getMsg());
                    return;

                case NINCS_AZ_SZI_RENDSZERBEN: //FacesContext.getCurrentInstance().responseComplete();
                    JSFUtils.logOut(super.isCallFromBevFeldMenu(), IGlobalConstants.LOGOUT_ERROR_MSG_CLASS, loginResult.getMsg());
                    return;

                default:
                    break;
            }

        }

        //Nem metódus annotációval, hanem programmatikusan ellenőrizzük az user lekérdező jogát
        //Így szofisztikáltabban tudunk visszajelezni a felhasználó felé, hogy nincs joga használni az alkalmazást
        //if (!isUserInRole(IWebAppConstants.ROLE_LEKERDEZO)) {
        if (!isUserInRole(IWebAppConstants.ROLE_LEKERDEZO)) {

            log.warn(String.format("A(z) %s user nem rendelkezik a(z) '%s' joggal -> egyből logout", remoteUser, "BEF_BFF001"));
            JSFUtils.logOut(super.isCallFromBevFeldMenu(), IGlobalConstants.LOGOUT_WARN_MSG_CLASS, "Önnek nincs megfelelő jogosultsága az alkalmazás használatához!");
            return;

        }

        //A bejelentkezett felhasználó illetékességi kódjának kiegészítése
//        {
//            Set<String> illetKodokSet = dolgozoDto.getIlletKodokSet();
//
//            // Ha van APP_BEF_LK51 joga, akkor az 51-est is láthatja )
//            if (this.isUserInRole(IWebAppConstants.ROLE_LEKERDEZO_51)) {
//                illetKodokSet.add("51");
//            }
//
//            //Saját megyét láthat + régiós megyét
//            if (isUserInRole(IWebAppConstants.ROLE_LEKERDEZO_REGIOS)) {
//                Set<String> regiosIgazgatosagKodok = dolgozoDto.getRegiosIgazgatosagKodok();
//                regiosIgazgatosagKodok.stream().forEach((regiosIgkod) -> {
//                    illetKodokSet.add(regiosIgkod);
//                });
//            }
//
//            //Minden megyét láthat, kivéve 51-est
//            if (this.isUserInRole(IWebAppConstants.ROLE_LEKERDEZO_ORSZAGOS)) {
//                kodkCache.getIgazgatosagKodok_Exclude51().stream().forEach((igkod) -> {
//                    illetKodokSet.add(String.format("%02d", igkod));
//                });
//            }
//        }
        //Mehet a startlapra a redir
        JSFUtils.redirect(IWebAppConstants.PAGE_MAINFORM, null, null);
    }

    /**
     * Rendelkezik Admin joggal?
     *
     * @return true -> igen
     */
    public boolean isUserAdmin() {
        return isUserInRole(IWebAppConstants.ROLE_ADMIN);
    }

    /**
     * Rendelkezik lekérdező szerepkörrel?
     *
     * @return true -> igen
     */
    public boolean isUserLekerdezo() {
        return isUserInRole(IWebAppConstants.ROLE_LEKERDEZO);
    }

}
