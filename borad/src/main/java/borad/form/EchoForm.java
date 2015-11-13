package borad.form;

import org.seasar.struts.annotation.Required;

public class EchoForm {
	@Required(target = "next")
	public String name;
	
	public String message;
}
