package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfSetBasicInfoDao;
import com.cczu.aqzf.model.dao.AqzfZfryDao;
import com.cczu.aqzf.model.dao.XzcfXwblDao;
import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.AQZF_TipstaffEntity;
import com.cczu.aqzf.model.entity.XZCF_InterrogationRecordEntity;
import com.cczu.util.common.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("XzcfXwblService")
public class XzcfXwblService {

	@Resource
	private XzcfXwblDao xzcfXwblDao;
	@Resource
	private AqzfZfryDao aqzfZfryDao;
	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	
	/**
	 * 查询询问笔录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfXwblDao.dataGrid(mapData);
		int getTotalCount=xzcfXwblDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		xzcfXwblDao.deleteInfo(id);
	}
	
	/**
	 * 添加询问笔录信息
	 * @param zfry
	 */
	public void addInfo(XZCF_InterrogationRecordEntity zfry) {
		zfry.setS1(new Timestamp(System.currentTimeMillis()));
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
		xzcfXwblDao.addInfo(zfry);
	}

	/**
	 * 根据id查找询问笔录信息
	 * @param id
	 * @return
	 */
	public XZCF_InterrogationRecordEntity findById(Long id) {
		return xzcfXwblDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_InterrogationRecordEntity zfry) {
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
	    xzcfXwblDao.updateInfo(zfry);
	}
	
	/**
	 * 根据立案id查找相关的笔录list
	 * @param id
	 * @return
	 */
	public List<XZCF_InterrogationRecordEntity> findByLaid(long laid) {
		return xzcfXwblDao.findByLaid(laid);
	}

	/**
	 * 获取文书导出word
	 * @param id
	 * @return
	 */
	public Map<String, Object> getWsdcword(Long id) {
		XZCF_InterrogationRecordEntity xwbl = xzcfXwblDao.findInfoById(id);
		if(!StringUtils.isEmpty(xwbl.getM9())){
			String[] a = xwbl.getM9().split(":");
            xwbl.setM9(a[1]);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		//询问起始时间解析
		if(xwbl.getM1()!=null&&!xwbl.getM1().toString().equals("")){
			String a = xwbl.getM1().toString();
			String[] as1 = a.substring(0,10).split("-");
			map.put("year", as1[0]);
			map.put("month1", as1[1]);
			map.put("day1", as1[2]);
			String[] bs1 = a.substring(11,16).split(":");
			map.put("hour1", bs1[0]);
			map.put("min1", bs1[1]);
		}else{
			map.put("year", "    ");
			map.put("month1", "   ");
			map.put("day1", "   ");
			map.put("hour1", "   ");
			map.put("min1", "   ");
		}
		//询问结束时间解析
		if(xwbl.getM2()!=null&&!xwbl.getM2().toString().equals("")){
			String b = xwbl.getM2().toString();
			String[] as2 = b.substring(0,10).split("-");
			map.put("month2", as2[1]);
			map.put("day2", as2[2]);
			String[] bs2 = b.substring(11,16).split(":");
			map.put("hour2", bs2[0]);
			map.put("min2", bs2[1]);
		}else{
			map.put("month2", "   ");
			map.put("day2", "  ");
			map.put("hour2", "   ");
			map.put("min2", "   ");
		}
		//检查人员解析
		if(!StringUtils.isEmpty(xwbl.getM15())){
			String c = xwbl.getM15();
			String[] as3 = c.split(",");
			//证件号
			String zjh = "";
			String m15= "";
			for(int i=0;i<as3.length;i++){
				m15 = m15 + as3[i] + "、";
				AQZF_TipstaffEntity a = aqzfZfryDao.findByM1(as3[i]);
				if(a!=null){
					zjh = zjh + (StringUtils.isEmpty(a.getM3())?"":a.getM3()) + "、";
				}
			}
			zjh=zjh.substring(0,zjh.length()-1);
			m15=m15.substring(0,m15.length()-1);
			map.put("m15", m15);
		    map.put("zjh", zjh);
		}else{
			map.put("m15", "                 ");
			 map.put("zjh", "                 ");
		}
		map.put("m0", StringUtils.isEmpty(xwbl.getM0())?"   ":xwbl.getM0());
		map.put("m3", StringUtils.isEmpty(xwbl.getM3())?"                   ":xwbl.getM3());
		map.put("m4", StringUtils.isEmpty(xwbl.getM4())?"      ":xwbl.getM4());
		map.put("m5", StringUtils.isEmpty(xwbl.getM5())?"   ":xwbl.getM5());
		map.put("m6", StringUtils.isEmpty(xwbl.getM6())?"   ":xwbl.getM6());
		map.put("m7", StringUtils.isEmpty(xwbl.getM7())?"              ":xwbl.getM7());
		map.put("m8", StringUtils.isEmpty(xwbl.getM8())?"                             ":xwbl.getM8());
		map.put("m9", StringUtils.isEmpty(xwbl.getM9())?"           ":xwbl.getM9());
		map.put("m10", StringUtils.isEmpty(xwbl.getM10())?"                                   ":xwbl.getM10());
		map.put("m11", StringUtils.isEmpty(xwbl.getM11())?"              ":xwbl.getM11());
		
		AQZF_SetBasicInfoEntity bh =setbasicdao.findInfor();
		String ssqmc = bh.getSsqmc()==null?"":bh.getSsqmc();
		map.put("ssqmc", ssqmc);
		//询问人单位职务
		List<Map<String,Object>> list = new ArrayList<>();
		if(!StringUtils.isEmpty(xwbl.getM12())){
			String d = xwbl.getM12();
			String[] ds = d.split(",");
			for(int i=0;i<ds.length;i++){
				Map<String,Object> zmap = new HashMap<>();
				zmap.put("z1", ds[i]);
				AQZF_TipstaffEntity at1 = aqzfZfryDao.findByM1(ds[i]);
				if(at1!=null){
					zmap.put("z2", ssqmc+"应急管理局"+(StringUtils.isEmpty(at1.getM4())?"":at1.getM4()));
				}else{
					zmap.put("z2", ssqmc+"应急管理局");
				}
				
				list.add(zmap);
			}
		}else{
			for(int i=0;i<2;i++){
				Map<String,Object> zmap = new HashMap<>();
				zmap.put("z1", "          ");
				zmap.put("z2", "                                  ");
				list.add(zmap);
			}
		}
		map.put("Xwrlist",list);
		//记录人单位职务
		if(!StringUtils.isEmpty(xwbl.getM13())){
			map.put("m13",xwbl.getM13());
			AQZF_TipstaffEntity at2 = aqzfZfryDao.findByM1(xwbl.getM13());
			if(at2!=null){
				map.put("m13_1"," "+ssqmc+"应急管理局"+(StringUtils.isEmpty(at2.getM4())?"":at2.getM4()));
			}else{
				map.put("m13_1"," "+ssqmc+"应急管理局");
			}
			
		}else{
			map.put("m13","          ");
			map.put("m13_1","                                  ");
		}	
		map.put("m14", StringUtils.isEmpty(xwbl.getM14())?"                           ":xwbl.getM14());
		map.put("m16", StringUtils.isEmpty(xwbl.getM16())?"                      ":xwbl.getM16());
		//处理m17富文本内容
		String z = xwbl.getM17();
		String M17 = "";
		if(!StringUtils.isEmpty(z)){
			String[] zs = z.split("</p>");
			for(int i=0;i<zs.length;i++){
				M17 += StringEscapeUtils.escapeHtml3(this.getTextFromHtml(zs[i]))+"<w:p />";
			}
		}
		map.put("m17", M17);
		return map;
	}
	
	//处理富文本
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
	
	public String delHTMLTag(String htmlStr) {
		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
		return htmlStr.trim(); // 返回文本字符串
	}

	public String getTextFromHtml(String htmlStr){
		htmlStr = this.delHTMLTag(htmlStr);
		htmlStr = htmlStr.replaceAll("&nbsp;", "");
		// htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);
		return htmlStr;
	}
}
