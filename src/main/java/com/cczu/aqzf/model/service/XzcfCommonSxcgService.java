package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfSetBasicInfoDao;
import com.cczu.aqzf.model.dao.XzcfCommonSxcgDao;
import com.cczu.aqzf.model.dao.impl.XzcfCfjdDaoImpl;
import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfSxcgEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行政处罚-一般处罚-事先处理实现类
 * 
 * @author jason
 * 
 */

@Service("XzcfCommonSxcgService")
public class XzcfCommonSxcgService {
	
	@Resource
	private XzcfCommonSxcgDao xzcfcommonsxcgdao;
	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	@Resource
	private XzcfCfjdDaoImpl punishsimplecfjddao;
	 
	public Long addInfoReturnID(XZCF_YbcfSxcgEntity yse) {
		// TODO Auto-generated method stub
		return xzcfcommonsxcgdao.addInfoReturnID(yse);
	}

	 
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfcommonsxcgdao.dataGrid(mapData);
		int count = xzcfcommonsxcgdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
	 
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xzcfcommonsxcgdao.deleteInfo(id);
	}

	 
	public XZCF_YbcfSxcgEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return xzcfcommonsxcgdao.findInfoById(id);

	}

	 
	public void updateInfo( XZCF_YbcfSxcgEntity yse) {
		// TODO Auto-generated method stub
		xzcfcommonsxcgdao.updateInfo(yse);
	}

	 
	public XZCF_YbcfSxcgEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		return xzcfcommonsxcgdao.findInfoByLaId(laid);
	}

	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 xzcfcommonsxcgdao.updateLaspInfo(id);
		
	}

	/**
	 * 获取文书导出word
	 */
	public Map<String, Object> getWsdcword(Long id, String flag) {
		XZCF_YbcfSxcgEntity yse;
		AQZF_SetBasicInfoEntity sbe=setbasicdao.findInfor();
		
		if("la".equals(flag)){
			yse=xzcfcommonsxcgdao.findInfoByLaId(id);
		}
		else
			yse= xzcfcommonsxcgdao.findInfoById(id);
		XZCF_CfjdInfoEntity cfjd=punishsimplecfjddao.findInfoByLaId(yse.getId1());
		String html="<w:sym w:font=\"Wingdings 2\" w:char=\"0052\"/>";
		String html2="<w:t>□</w:t>";
	    Calendar cal = Calendar.getInstance();
		cal.setTime(cfjd.getPunishdate());
		Map<String, Object> map=new HashMap<String, Object>();
	     map.put("dw",(yse.getQyname().length()>=4?"你单位":"你"));
		 map.put("number", yse.getNumber());
		 map.put("qyname", yse.getQyname());
		 map.put("year",String.valueOf(cal.get(cal.YEAR)));
		 map.put("month",cal.get(cal.MONTH)+1);
		 map.put("day",cal.get(cal.DAY_OF_MONTH));
		 map.put("xzjd",yse.getXzjd());
		 
		 if(yse.getFinedate()!=null){
			 cal.setTime(yse.getFinedate());
			 map.put("fineyear",String.valueOf(cal.get(cal.YEAR)));
			 map.put("finemonth",cal.get(cal.MONTH)+1);
			 map.put("fineday",cal.get(cal.DAY_OF_MONTH));
			 map.put("fine",StringUtils.isEmpty(yse.getFine())?"          ":digitUppercase(Double.parseDouble(yse.getFine())));
			 map.put("extrafine",StringUtils.isEmpty(yse.getExtrafine())?"         ":digitUppercase(Double.parseDouble(yse.getExtrafine())));
			 map.put("allfine",StringUtils.isEmpty(yse.getAllfine())?"         ":digitUppercase(Double.parseDouble(yse.getAllfine())));
			 map.put("bankname", sbe.getBankname()==null?"":sbe.getBankname());
			 map.put("jq",html);
		 }else{
			 map.put("fineyear","     ");
			 map.put("finemonth","    ");
			 map.put("fineday","   ");
			 map.put("fine","          ");
			 map.put("extrafine","         ");
			 map.put("allfine","         ");
			 map.put("bankname","                        ");
			 map.put("jq",html2);
		 }
		 
		 if(StringUtils.isNotBlank(yse.getExtraxzjd()))
				map.put("qt",html);
			else
				map.put("qt",html2);
		 map.put("extraxzjd", StringUtils.isEmpty(yse.getExtraxzjd())?"                                              ":yse.getExtraxzjd());
		 map.put("account", sbe.getAccount()==null?"":sbe.getAccount());
		 map.put("ssqmc", sbe.getSsqmc()==null?"":sbe.getSsqmc());
		return map;
	}

	/**
     * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零
     * 要用到正则表达式
     */
    public static String digitUppercase(double n){
        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = {{"元", "万", "亿"},
                     {"", "拾", "佰", "仟"}};
 
        n = Math.abs(n);
         
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if(s.length()<1){
            s = "整";    
        }
        int integerPart = (int)Math.floor(n);
 
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }
}
