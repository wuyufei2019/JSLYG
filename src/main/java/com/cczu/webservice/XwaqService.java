package com.cczu.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

/*
 * 不安全行为接口
 */
@WebService
public interface XwaqService {
	
	/*
	 * 行为类型页面
	 * str1：企业id
	 */
	String Xwlx(@WebParam(name="str1") String str1);
	
	/*
	 * 添加观察记录
	 * str1：观察记录主表(yhpc_observations_main)  str2:观察记录副表list(对象yhpc_observations_sec) 
	 */
	String addgcjl(@WebParam(name="str1") String str1,
				  @WebParam(name="str2") String str2);
	
	/*
	 * 观察记录页面
	 * str1：页码  str2:页数  str3:企业id str4:观察时间起  str5:观察时间止
	 */
	String Gcjl(@WebParam(name="str1") String str1,
				@WebParam(name="str2") String str2,
				@WebParam(name="str3") String str3,
				@WebParam(name="str4") String str4,
				@WebParam(name="str5") String str5);
	
	/*
	 * 观察记录详细页面
	 * str1：观察记录id
	 */
	String GcjlView(@WebParam(name="str1") String str1);
	
	/*
	 * 观察记录统计页面
	 * str1：企业id
	 */
	String GcjlCount(@WebParam(name="str1") String str1);
	
	/*
	 * 行为安全详细页面（即观察记录副表）
	 * str1：企业id
	 */
	String XwaqView(@WebParam(name="str1") String str1);
	
	/*
	 * 部门下拉
	 * str1：企业id str2：用户类型(usertype)
	 */
	String Depart(@WebParam(name="str1") String str1,
				  @WebParam(name="str2") String str2);
}
