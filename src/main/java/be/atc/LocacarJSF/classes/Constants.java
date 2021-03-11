package be.atc.LocacarJSF.classes;

import javax.faces.context.FacesContext;

/**
 * @author Younes Arifi
 * Constant values used
 */
public final class Constants {

    public static final String SMTPHOST = "smtp.gmail.com";
    public static final String SMTPPORT = "465";
    public static final String SMTPSOCKETFACTORYCLASS = "javax.net.ssl.SSLSocketFactory";
    public static final String SMTPSOCKETFACTORYPORT = "465";
    public static final String MYACCOUNTEMAIL = "locacarbacinfo@gmail.com";
    public static final String MYPASSWORDEMAIL = "bacInfo123";

    public static final String UPLOADS = "/uploads/";
    public static final String GENERATED_FILES = "/generated_files/";
    public static final String IMAGES = "/images/";


    public static final String APP_ROOT_DIR = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/");
    public static final String UPLOADS_ROOT_DIR = APP_ROOT_DIR + UPLOADS;
    public static final String FILE_OUT_PUT_STREAM = UPLOADS_ROOT_DIR + GENERATED_FILES;
    public static final String FILE_OUTPUT_IMAGE = UPLOADS_ROOT_DIR + IMAGES;


}
