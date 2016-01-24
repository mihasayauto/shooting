package board.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.seasar.struts.annotation.Execute;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.User;

public class LoginAction {
	@Resource
	protected HttpServletRequest request;

	@Resource
	public HttpSession session;

	@Resource
	protected HttpServletResponse response;
	
	public String name;

	@Execute (validator=false)
	public String index(){
		return "login.jsp";
	}
	//facebookログイン
	@Execute (validator=false)
	public String facebookLogin() throws Exception{
		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId("1627730634158113", "8cca4eca5976b8045e6657083201b24a");
		facebook.setOAuthPermissions("public_profile, email, publish_actions");    
		request.getSession().setAttribute("facebook", facebook);
	    StringBuffer callbackURL = request.getRequestURL(); 
	    int index = callbackURL.lastIndexOf("/");
	    callbackURL.replace(index, callbackURL.length(), "").append("/login/callbackFacebook");
	    response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));
	    return null;
		}
	@Execute(validator=false)
	public String callbackFacebook() throws Exception{
		Facebook facebook = (Facebook)request.getSession().getAttribute("facebook");
		String oauthCode = request.getParameter("code");
		facebook.getOAuthAccessToken(oauthCode); 
		if(oauthCode == null){
			session.invalidate();
			return "login.jsp";
			}
		session.setAttribute("facebook",facebook);
		User fUser = facebook.getMe();
		name = fUser.getName();
		session.setAttribute("name", name);
		return "/board?redirect=true";
		}
	/*twitterログイン
	public static final String CONSUMER_KEY = "7ZVgfKiOvBDcDFpytRWSA";
	public static final String CONSUMER_SECRET = "JmeJVeym78arzmGthrDUshQyhkq6nWA9tWLUKxc";
	@Execute (validator=false)
	public String twitterLogin() throws Exception{
	Twitter twitter = TwitterFactory.getSingleton();
	twitter.setOAuthConsumer(CONSUMER_KEY,CONSUMER_SECRET);
	String callbackURL = request.getRequestURL().toString().replaceAll(".do","/callback");
	String ACCESSTOKEN=null;
	String ACCESSSECRET=null;
	RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
	session.setAttribute("RequestToken",requestToken);
	response.sendRedirect(requestToken.getAuthorizationURL());
	session.setAttribute("Twitter",twitter);
	name = twitter.getScreenName();
	session.setAttribute("name", name);
	return "/board?redirect=true";
	}*/
}