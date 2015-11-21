package borad.form;

import org.seasar.struts.annotation.Required;

public class EchoForm {
	@Required(target = "next")
	public String name;
	public String message;
	
	public String getName() {
		return this.name;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message = message;
	}
}
