package board.form;

import org.seasar.struts.annotation.IntegerType;
import org.seasar.struts.annotation.Required;

public class BoardForm {
	@Required(target = "next")
	
	
	
	public String message;
	
	public String getMessage() {
		
		return this.message;
	}
	public void setMessage(String message) {
		
		this.message = message;
	}
	@IntegerType
	public String page;
}
