package xj;

import org.springframework.boot.test.TestRestTemplate.HttpClientOption;

public class ThreadTest implements   Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			HttpUtil.doPost("http://192.168.1.243:8888/xj/collector/report?sitenumber=3701001&cardid=370100000001251221&reportresult=1&failedreason=上报服务器连接失败&reportdate=20180227", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
} 