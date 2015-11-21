package borad.action;

import org.seasar.struts.annotation.Execute;

public class SampleAction {
	
	public String message;
	
	@Execute(validator=false)
	public String index(){
		return "index.jsp";
	}
}
