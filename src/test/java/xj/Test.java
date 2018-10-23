package xj;

import java.util.HashSet;
import java.util.Set;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.gafis.xj.webservice.ICheckService;
import com.gafis.xj.webservice.impl.CheckServiceImpl;

public class Test {
	/*public static void main(String[] args) {
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.getInInterceptors().add(new LoggingInInterceptor());
 	    factoryBean.setServiceClass(ICheckService.class);
        factoryBean.setAddress("http://localhost:10008/xj/check/checkService?wsdl"); 
        ICheckService service=(ICheckService) factoryBean.create();
        service.check("askjdaslkjdhakshd");
	}*/
	
	public static void main(String[] args) {
		Set aa=new HashSet<>();
		String b="1";
		aa.add(b);
		aa.add(b);
		System.out.println(aa.size());
	}
	
}
