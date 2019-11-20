package com.cczu.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

/*
 * 在线考试接口
 */
@WebService
public interface AqpxService {

	/*
	 * 日常培训考试页面
	 * str1：用户id
	 */
	String DaliyExam(@WebParam(name="str1") String str1);
	
	/*
	 * 三级安全培训考试页面
	 * str1：企业id
	 */
	String SjjyExam(@WebParam(name="str1") String str1);
	
	/*
	 * 外来方培训考试页面
	 * str1：企业id
	 */
	String WlfExam(@WebParam(name="str1") String str1);
	
	/*
	 * 随机生成试卷
	 * str1：企业id  str2:课程id
	 */
	String showKJList(@WebParam(name="str1") String str1,
					  @WebParam(name="str2") String str2);
	
	/*
	 * 提交考试记录
	 * str1：课程id  str2:题目类型及答案
	 * 提交后返回试卷标识h
	 */
	String submitKS(@WebParam(name="str1") String str1,
			  	    @WebParam(name="str2") String str2);
	
	/*
	 * 查看考试试卷记录
	 * str1：试卷标识
	 */
	String viewSJ(@WebParam(name="str1") String str1);
}
