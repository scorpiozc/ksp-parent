package cn.com.bjjdsy.common.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomConfigTest {

	@Autowired
	private CustomConfig customConfig;

	@Test
	public void customConfigTest() {
		System.out.println(JSON.toJSONString(customConfig.getFakeTransferDict()));
	}
}
