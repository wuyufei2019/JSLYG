package com.cczu.webservice;

import java.text.ParseException;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface WisDomService {
	String Login(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3);

	String updatePwd(@WebParam(name="str1") String str1);
	
	String Bbkz(@WebParam(name = "str1") String str1);
		
	String QyList(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8);
	
	String QyInfo(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8,
			@WebParam(name = "str9") String str9);

	String GwgySjHq(@WebParam(name = "str1") String str1);
	
	String ZdwxyxxCk(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3);
	
	String Qyzdwxy(@WebParam(name = "str1") String str1);
	
	String CgjkQylist(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String CgjkInfo(@WebParam(name = "str1") String str1);
	
	String WzInfoCx(@WebParam(name = "str1") String str1);
	
	String GwgyQylist(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String GwgyInfo(@WebParam(name = "str1") String str1);
	
	String GwgyInfoCk(@WebParam(name = "str1") String str1);
	
	String QtndQylist(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String QtndInfo(@WebParam(name = "str1") String str1);
	
	String EdmQylist(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String EdmInfo(@WebParam(name = "str1") String str1);
	
	String SpjkQylist(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String SpjkInfo(@WebParam(name = "str1") String str1);
	
	String Yjzy(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8);
	
	String YjzyQyList(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Sghgwz(@WebParam(name = "str1") String str1);
	
	String SghgwzInfo(@WebParam(name = "str1") String str1);
	
	String Chzjs(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8) throws Exception;
	
	String Pshjs(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6) throws Exception;
	
	String Hqjs(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3) throws Exception;
	
	String Wljs(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2) throws Exception;
	
	String Hxjs(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3) throws Exception;
	
	String Ysqtjs(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4) throws Exception;
	
	String Cxxljs(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8,
			@WebParam(name = "str9") String str9,
			@WebParam(name = "str10") String str10,
			@WebParam(name = "str11") String str11,
			@WebParam(name = "str12") String str12,
			@WebParam(name = "str12") String str13,
			@WebParam(name = "str12") String str14) throws Exception;
	
	String Ssxljs(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8) throws Exception;
	
	String Aqsc(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4);
	
	String Sgal(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4);
	
	String Whpaq(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String SDS(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4);
	
	String Aqwjgl(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7);
	
	String Wjcdjs(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8);
	
	String Aqscdt(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String Qywjckxz(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3);
	
	String AqbaQylist(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String Aqba(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3);
	
	String Jcgzjh(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7);
	
	String JcjlZxmc(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String Jcjl(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8,
			@WebParam(name = "str9") String str9);
	
	String Dsfjcjl(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Jctjfx(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3);
	
	String Dsf(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3);
	
	String Fwxmbb(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String TszybbQylist(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Tszybb(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4);
	
	String Zxgl(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Sjgl(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3);
	
	String Sjgltjfx(@WebParam(name = "str1") String str1);
	
	String Syxx(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2);
	
	String QySyxx(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2);
	
	String Syqyyhjl(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Wlsssj(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4);
	
	String Wlbdsj(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Ttlsj(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8);
	
	String Dtfxyt(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2);
	
	String Bjxx(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7);
	
	String Qybjxx(@WebParam(name = "str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String Sywlxx(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Sygwgy(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Syzdwxy(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Sywgjg(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String Syaqpx(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String Sytzsb(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Syzybwh(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Syfxgk(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Fxgkdc(@WebParam(name="str1") String str1);
	
	String Sywgwzg(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Yhdtjfx(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7);
	
	String Yhdzt(@WebParam(name="str1") String str1);
	
	String Yhdxjjl(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8,
			@WebParam(name = "str9") String str9);
	
	String Yhdajxjjl(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8,
			@WebParam(name = "str9") String str9);
	
	String Yhdxjjlqylist(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String Yhdxjjlxq(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3,
			@WebParam(name="str4") String str4,
			@WebParam(name="str5") String str5);
	
//	String Yhdyhpcjlqylist(@WebParam(name="str1") String str1,
//			@WebParam(name="str2") String str2,
//			@WebParam(name="str3") String str3,
//			@WebParam(name="str4") String str4,
//			@WebParam(name="str5") String str5);
	
	String Yhdyhpcjl(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3,
			@WebParam(name="str4") String str4,
			@WebParam(name="str5") String str5,
			@WebParam(name="str6") String str6);
	
	String Yhdqyyhpcjl(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3,
			@WebParam(name="str4") String str4,
			@WebParam(name="str5") String str5);
	
	String Yhzgfclist(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3);
	
	String Yhjdkh(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3,
			@WebParam(name="str4") String str4,
			@WebParam(name="str5") String str5,
			@WebParam(name="str6") String str6,
			@WebParam(name="str7") String str7) throws ParseException;
	
	String Yhtjfx1(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2);
	
	String Yhtjfx2(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3);
	
	String SspyhList(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3,
			@WebParam(name="str4") String str4,
			@WebParam(name="str5") String str5,
			@WebParam(name="str6") String str6);
	
	String SspqyyhList(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3,
			@WebParam(name="str4") String str4);
	
	String YhjlQycl(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2);
	
	String Yhqybc(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String Yhqybcxq(@WebParam(name="str1") String str1);
	
	String Yhqyzcd(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4);
	
	String Qyewmxx(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2);
	
	String Qyxjbc(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3);
	
	String Zcdxjnr(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2);
	
	String Qyxjtj(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2);
	
	String Qyrfidxx(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2);
	
	String Wgdxjjl(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8);
	
	String Wgdxjjlxq(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3,
			@WebParam(name="str4") String str4);
	
	String Wgdyhpcjl(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3,
			@WebParam(name="str4") String str4,
			@WebParam(name="str5") String str5,
			@WebParam(name="str6") String str6);
	
	String Wgdyhpcqyjl(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3,
			@WebParam(name="str4") String str4);
	
	String Wgdxx(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3,
			@WebParam(name="str4") String str4,
			@WebParam(name="str5") String str5,
			@WebParam(name="str6") String str6);
	
	String WgBcxx(@WebParam(name="str1") String str1,
			@WebParam(name="str2") String str2,
			@WebParam(name="str3") String str3,
			@WebParam(name="str4") String str4);
	
	String WgBcxq(@WebParam(name="str1") String str1);
	
	String Wgxx(@WebParam(name="str1") String str1);
	
	String Wgcylist(@WebParam(name="str1") String str1);
	
	String Wgdtjfx(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7);
	
	String Wgrytjfx(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7);
	
	String Wgbctjfx(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Tjfxxjjl(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String QyListidjson(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3);
	
	String Ssp(@WebParam(name="str1") String str1);
	
	String Wgyhcl(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4);
	
	String Wgyhclxq(@WebParam(name="str1") String str1);
	
	String Wgyhsh(@WebParam(name="str1") String str1);
	
	String Wgewmxx(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2);
	
	String Wgbcbyxzqy(@WebParam(name="str1") String str1);
	
	String Wgdxjnr(@WebParam(name="str1") String str1);
	
	String Wgxjtj(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2);
	
	String Wgrfidxx(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2);
	
	String Fxdxx(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8);
	
	String Fxdtjt1(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3);
	
	String Fxdtjt2(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3);
	
	String Fxdtjt3(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3);
	
	String Aqzfjcjh(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7);
	
	String Aqzfjcfa(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7,
			@WebParam(name = "str8") String str8);
	
	String Aqzfjcfaxq(@WebParam(name="str1") String str1);
	
	String Aqzfjcjl(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6,
			@WebParam(name = "str7") String str7);
	
	String Aqzfjcjlxq(@WebParam(name="str1") String str1);
	
	String Aqzfclcs(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Aqzfzlzg(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Aqzffcyj(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5,
			@WebParam(name = "str6") String str6);
	
	String Aqzfjcbk(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4);
	
	String Aqzfjcdy();
	
	String Aqzfzfry(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2,
			@WebParam(name = "str3") String str3,
			@WebParam(name = "str4") String str4,
			@WebParam(name = "str5") String str5);
	
	String Aqzfwsdc(@WebParam(name="str1") String str1,
			@WebParam(name = "str2") String str2);
}
