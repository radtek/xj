package interview;

import com.gafis.xj.model.FUser;

import net.sf.json.JSONObject;

public class Test {
	
	@org.junit.Test
	public void test1(){
		FUser fUser=new FUser();
		fUser.setUserId(1);
		fUser.setTelNumber("123");
		fUser.setCustName("asd");
		
		JSONObject json=JSONObject.fromObject(fUser);
		System.out.println(json.toString());
	}
}
