package borad.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import borad.form.EchoForm;

public class EchoAction {

	@Resource
	@ActionForm
	protected EchoForm echoForm;
	
	public List<EchoForm> echoList;
	
	@Execute(validator=false)
	public String index(){
		this.echoList = new ArrayList<EchoForm>();
		return "input.jsp";
	}
	
	@Execute(validator=false)
	public String next(){
		echoList.add(echoForm);
		return "input.jsp";
		//return "/echo?redirect=ture";
	}
}
