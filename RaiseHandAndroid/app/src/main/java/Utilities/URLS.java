package Utilities;

/**
 * @author sae1
 * This is the class that helps with android volley calls by making final urls
 */

public class URLS {
    private static final String ROOT_URL = "http://proj-309-sa-b-3.cs.iastate.edu/android/";
    public static final String URL_REGISTER = ROOT_URL + "apiSign.php";
    public static final String URL_STRING_LOGIN= ROOT_URL+"api.php";
    public static final String URL_TOPICS= ROOT_URL+"topics.php";
    public static final String URL_QUESTIONS=ROOT_URL+"postQ.php";
    public static final String URL_REPLY=ROOT_URL+"postR.php";
    public static final String URL_UPVOTE=ROOT_URL+"upvoteQ.php";
    public static final String URL_UPVOTER=ROOT_URL+"upvoteR.php";
    public static final String URL_ENDORSEMENT= ROOT_URL+"endorseR.php";
    public static final String URL_ENDORSEMENTQ= ROOT_URL+"endorseQ.php";
    public static final String URL_FORGOTPASS= ROOT_URL+"forgotPassword.php";
    public static final String URL_RESETPASS= ROOT_URL+"resetPassword.php";
    public static final String URL_REPORTQ=ROOT_URL+"reportQ.php";
    public static final String URL_REPORTR=ROOT_URL+"reportR.php";
}
