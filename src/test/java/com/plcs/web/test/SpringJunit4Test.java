//package com.plcs.web.test;
//
//import com.plcs.web.common.plcsbatchrequest.PlctBatchRequest;
//import com.plcs.web.modules.sys.dao.DictDao;
//import com.plcs.web.modules.sys.entity.Dict;
//import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdAllocateHst;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.List;
//import java.util.Map;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration({
//	"classpath:spring-mvc.xml",
//	"classpath:spring-context.xml",
//	"classpath:spring-context-activiti.xml",
//	"classpath:spring-context-jedis.xml",
//	"classpath:spring-context-shiro.xml"
//})
//public class SpringJunit4Test {
//
//    @Autowired
//	DictDao dictDao;
//    @Autowired
//    private PlctBatchRequest plctBatchRequest;
//
//
//	@Test
//	public void test() {
//		List<Dict> dicts = dictDao.findList(new Dict());
//		for(Dict dict: dicts) {
//			System.out.println(dict.getLabel());
//		}
//	}
//
//	@Test
//	public void doAllocateCaseTest() {
//		Map<String, Object> reqResultMap = plctBatchRequest.doAllocateCase();
//		System.out.println(reqResultMap.get("msg"));
//	}
//
//	@Test
//	public void stopAllocateCase() {
//		WsxdAllocateHst wsxdAllocateHst = new WsxdAllocateHst();
//		wsxdAllocateHst.setLoanBillNo("01");
//		Map<String, Object> reqResultMap = plctBatchRequest.stopAllocateCase(wsxdAllocateHst);
//		System.out.println(reqResultMap.get("msg"));
//	}
//
//
//}
