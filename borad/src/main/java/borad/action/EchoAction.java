package borad.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import borad.form.EchoForm;

public class EchoAction {

	@Resource
	@ActionForm
	protected EchoForm echoForm;
	
	@Execute(validator=false)
	public String index(){
		
		
		return "input.jsp";
	}
	
	
	@Execute(validator=false)
	public String next(){
		//seasarではsessionScopeでsessionを管理する
		Map<String, Object> sessionScope = SingletonS2Container.getComponent("sessionScope");
		
		//
		List<EchoForm> echoList = (List<EchoForm>) sessionScope.get("echoList");
		if(echoList == null) {
			echoList = new ArrayList<EchoForm>();
		} 
		EchoForm newForm = new EchoForm();
		
		newForm.setName(echoForm.name);
		newForm.setMessage(echoForm.message);
		echoList.add(newForm);
		sessionScope.put("echoList",echoList);
		
		return "input.jsp";
		//return "/echo?redirect=ture";
	}
}
