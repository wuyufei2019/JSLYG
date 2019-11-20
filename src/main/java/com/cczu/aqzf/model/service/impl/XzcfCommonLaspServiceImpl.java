package com.cczu.aqzf.model.service.impl;

import com.cczu.aqzf.model.dao.*;
import com.cczu.aqzf.model.dao.impl.*;
import com.cczu.aqzf.model.entity.*;
import com.cczu.aqzf.model.service.IXzcfCommonLaspService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 行政处罚-一般处罚-立案审批实现类
 *
 * @author jason
 */

@Service("PunishCommonLaspService")
public class XzcfCommonLaspServiceImpl implements IXzcfCommonLaspService {

    @Resource
    private XzcfCommonLaspDaoImpl punishcommonlaspdao;
    @Resource
    private AqzfSetBasicInfoDao setbasicdao;
    @Resource
    private XzcfCommonJaspDao xzcfcommonjaspdao;
    @Resource
    private XzcfXwtzDao xzcfXwtzDao;
    @Resource
    private XzcfXwblDao xzcfXwblDao;
    @Resource
    private AqzfZfryDao aqzfZfryDao;
    @Resource
    private XzcfCommonDcDao xzcfcommondcdao;
    @Resource
    private XzcfDCzjDao xzcfDCzjDao;
    @Resource
    private XzcfDCzyclDao xzcfDCzyclDao;
    @Resource
    private PunishSimpleGzDaoImpl punishsimplegzdao;
    @Resource
    private XzcfCssbblDao xzcfCssbblDao;
    @Resource
    private XzcfCommonTzDaoImpl xzcfcommontzdao;
    @Resource
    private XzcfCommonAjcpDaoImpl xzcfcommonajcpdao;
    @Resource
    private XzcfJttlDao xzcfJttlDao;
    @Resource
    private XzcfCfjdDaoImpl punishsimplecfjddao;
    @Resource
    private XzcfFkspDao xzcfFkspDao;
    @Resource
    private XzcfFkpzDao xzcfFkpzDao;

    @Override
    public Long addInfoReturnID(XZCF_YbcfLaspEntity yle) {
        // TODO Auto-generated method stub
        return punishcommonlaspdao.addInfoReturnID(yle);
    }

    @Override
    public Map<String, Object> dataGrid(Map<String, Object> mapData) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = punishcommonlaspdao.dataGrid(mapData);
        int count = punishcommonlaspdao.getTotalCount(mapData);
        map.put("rows", list);
        map.put("total", count);
        return map;
    }
//	@Override
//	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
//		// TODO Auto-generated method stub
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Map<String,Object>> list = punishcommonlaspdao.dataGrid2(mapData);
//		int count = punishcommonlaspdao.getTotalCount2(mapData);
//		map.put("rows", list);
//		map.put("total", count);
//		return map;
//	}

    @Override
    public void deleteInfo(long id) {
        // TODO Auto-generated method stub
        punishcommonlaspdao.deleteInfo(id);
    }

    @Override
    public XZCF_YbcfLaspEntity findInfoById(long id) {
        // TODO Auto-generated method stub
        return punishcommonlaspdao.findInfoById(id);

    }

    @Override
    public void updateInfo(XZCF_YbcfLaspEntity yle) {
        // TODO Auto-generated method stub
        punishcommonlaspdao.updateInfo(yle);
    }
//	
//	@Override
//	public Object[] getMaxYearAndMinYear(){
//		return punishcommonlaspdao.getMaxYearAndMinYear();
//	}
//
//	@Override
//	public List<Map<String, Object>> findGzCaseNameList(String xzqy) {
//		// TODO Auto-generated method stub
//		List<Map<String, Object>> list=punishcommonlaspdao.findGzCaseNameList(xzqy);
//		return list;
//	}
//
//	@Override
//	public List<Map<String,Object>>getYearDate() {
//		// TODO Auto-generated method stub
//		return punishcommonlaspdao.getYearDate();
//	}
//	@Override
//	public List<Object[]>getYearDate2(String year) {
//		// TODO Auto-generated method stub
//		return punishcommonlaspdao.getYearDate2(year);
//	}

    @Override
    public int getTempCount(Map<String, Object> mapData) {
        // TODO Auto-generated method stub
        return punishcommonlaspdao.getTempCount(mapData);
    }

    @Override
    public XZCF_YbcfLaspEntity findTempInfo(String xzqy) {
        // TODO Auto-generated method stub
        return punishcommonlaspdao.findTempInfo(xzqy);
    }

    @Override
    public List<Map<String, Object>> getNumberlist(long id, String xzqy) {
        // TODO Auto-generated method stub
        Object[] obj = (Object[]) punishcommonlaspdao.getNumberlist(id, xzqy).get(0);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("text", obj[i]);
                list.add(map);
            }
        }
        return list;
    }

    @Override
    public Map<String, Object> getWsdcword(Long id) {
        XZCF_YbcfLaspEntity yle = punishcommonlaspdao.findInfoById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ayname", yle.getAyname());
        map.put("filldate", yle.getFilldate() == null ? "" : DateUtils.formatDate(yle.getFilldate(), "yyyy年MM月dd日"));
        map.put("casesource", yle.getCasesource());
        map.put("casename", yle.getCasename());

        map.put("contact", yle.getContact());
        map.put("legalperson", yle.getLegalperson());
        map.put("dsaddress", yle.getDsaddress());
        map.put("ybcode", yle.getYbcode());
        map.put("opinion", yle.getOpinion() == null ? "" : yle.getOpinion());
        map.put("identity1", yle.getIdentity1());
        map.put("identity2", yle.getIdentity2());
        map.put("number", yle.getNumber());


        String ajqk = yle.getAjjbqk() == null ? "" : yle.getAjjbqk();
        if (yle.getCfdxlx().equals("2")) {
            ajqk += "经初步调查，" + yle.getCfryname() + "涉嫌存在安全生产违法行为。";
            map.put("dsperson", yle.getCfryname());
        } else {
            ajqk += "经初步调查，" + yle.getDsperson() + "涉嫌存在安全生产违法行为。";
            map.put("dsperson", yle.getDsperson());
        }
        map.put("casecondition", ajqk);
        return map;
    }

    @Override
    public Map<String, Object> getWsdcsdhzword(Long id) {
        XZCF_YbcfLaspEntity yle = punishcommonlaspdao.findInfoById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        AQZF_SetBasicInfoEntity asb = setbasicdao.findInfor();
        map.put("ssqjc", asb.getSsqjc() == null ? "" : asb.getSsqjc());
        map.put("number", yle.getSdhznumber());
        map.put("casename", yle.getCasename());
        if (yle.getCfdxlx().equals("2")) {
            map.put("qyname", yle.getCfryname());
        } else {
            map.put("qyname", yle.getDsperson());
        }
        String m0 = yle.getNumber();
        map.put("bh", "〔" + DateUtils.getYear() + "〕" + m0.substring(m0.indexOf("〕") + 1, m0.length()));
        map.put("ssqmc", asb.getSsqmc() == null ? "" : asb.getSsqmc());
        return map;
    }

    /**
     * 导出
     *
     * @param response
     * @param mapData
     */
    public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
        String fileName = "立案审批信息表.xls";
        List<Map<String, Object>> list = punishcommonlaspdao.getExport(mapData);
        String[] title = mapData.get("coltext").toString().split(",");
        String[] keys = mapData.get("colval").toString().split(",");
        new ExportExcel(fileName, title, keys, list, response);
    }

    @Override
    public Map<String, Object> getJzword(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();

        XZCF_YbcfLaspEntity lasp = punishcommonlaspdao.findInfoById(id);
        XZCF_YbcfJaspEntity jasp = xzcfcommonjaspdao.findInfoByLaId(id);

        String m0 = lasp.getNumber();
        AQZF_SetBasicInfoEntity bh = setbasicdao.findInfor();
        map.put("jzbh", "（" + bh.getSsqjc() + "）应急案〔" + m0.substring(m0.indexOf("〔") + 1, m0.indexOf("〕")) + "〕" + m0.substring(m0.indexOf("〕") + 1, m0.length()));
        map.put("ajmc", lasp.getCasename());
        map.put("ay", lasp.getAyname());
        map.put("cljg", jasp.getResult());
        map.put("enforcer1", lasp.getEnforcer1());
        map.put("enforcer2", lasp.getEnforcer2());

        //立案审批
        Map<String, Object> laspmap = new HashMap<String, Object>();
        laspmap.put("ayname", lasp.getAyname());
        laspmap.put("filldate", lasp.getFilldate() == null ? "" : DateUtils.formatDate(lasp.getFilldate(), "yyyy年MM月dd日"));
        laspmap.put("casesource", lasp.getCasesource());
        laspmap.put("casename", lasp.getCasename());

        laspmap.put("contact", lasp.getContact());
        laspmap.put("legalperson", lasp.getLegalperson());
        laspmap.put("dsaddress", lasp.getDsaddress());
        laspmap.put("ybcode", lasp.getYbcode());
        laspmap.put("opinion", lasp.getOpinion() == null ? "" : lasp.getOpinion());
        laspmap.put("identity1", lasp.getIdentity1());
        laspmap.put("identity2", lasp.getIdentity2());
        laspmap.put("number", lasp.getNumber());


        String ajqk = lasp.getAjjbqk() == null ? "" : lasp.getAjjbqk();
        if (lasp.getCfdxlx().equals("2")) {
            ajqk += "经初步调查，" + lasp.getCfryname() + "涉嫌存在安全生产违法行为。";
            laspmap.put("dsperson", lasp.getCfryname());
        } else {
            ajqk += "经初步调查，" + lasp.getDsperson() + "涉嫌存在安全生产违法行为。";
            laspmap.put("dsperson", lasp.getDsperson());
        }
        laspmap.put("casecondition", ajqk);

        map.put("a", laspmap);


        //询问通知
        map.put("xwflag", lasp.getXwflag());
        if (lasp.getXwflag().equals("1")) {
            Map<String, Object> xwtzinfo = xzcfXwtzDao.findWordByLaId(id);
            Map<String, Object> xwtz = new HashMap<String, Object>();
            //解析询问时间
            if (xwtzinfo.get("m2") != null && !xwtzinfo.get("m2").toString().equals("")) {
                String a = xwtzinfo.get("m2").toString();
                String[] as1 = a.substring(0, 10).split("-");
                xwtz.put("year", as1[0]);
                xwtz.put("month", as1[1]);
                xwtz.put("day", as1[2]);
                String[] bs1 = a.substring(11, 16).split(":");
                xwtz.put("hour", bs1[0]);
            } else {
                xwtz.put("year", "   ");
                xwtz.put("month", "   ");
                xwtz.put("day", "   ");
                xwtz.put("hour", "   ");
            }
            xwtz.put("m0", xwtzinfo.get("m0") == null || xwtzinfo.get("m0").toString().equals("") ? "" : xwtzinfo.get("m0").toString());
            xwtz.put("address", bh.getAddress() == null ? "" : bh.getAddress());
            if (xwtzinfo.get("cfdxlx").equals("2")) {
                xwtz.put("qyname", xwtzinfo.get("cfryname"));
            } else {
                xwtz.put("qyname", xwtzinfo.get("qyname") == null || xwtzinfo.get("qyname").toString().equals("") ? "         " : xwtzinfo.get("qyname").toString());
            }
            xwtz.put("m1", xwtzinfo.get("m1") == null || xwtzinfo.get("m1").toString().equals("") ? "" : xwtzinfo.get("m1").toString());
            xwtz.put("m3", xwtzinfo.get("m3") == null || xwtzinfo.get("m3").toString().equals("") ? "                 " : xwtzinfo.get("m3").toString());
            xwtz.put("m4", xwtzinfo.get("m4") == null || xwtzinfo.get("m4").toString().equals("") ? "" : xwtzinfo.get("m4").toString());
            xwtz.put("m6", xwtzinfo.get("m6") == null ? "" : xwtzinfo.get("m6").toString());
            xwtz.put("m7", xwtzinfo.get("m7") == null ? "" : xwtzinfo.get("m7").toString());
            xwtz.put("ssqmc", bh.getSsqmc() == null ? "" : bh.getSsqmc());
            String html = "<w:sym w:font=\"Wingdings 2\" w:char=\"0052\"/>";
            String html2 = "<w:t>□</w:t>";
            if (xwtzinfo.get("m8") != null && !xwtzinfo.get("m8").toString().equals("")) {
                String m8 = xwtzinfo.get("m8").toString();
                //,,法定代表人身份证明或者
                String[] s = m8.split(",");
                xwtz.put("sfz", html2);
                for (String str : s) {
                    if (str.equals("身份证"))
                        xwtz.put("sfz", html);
                }
                if (m8.contains("营业执照"))
                    xwtz.put("yyzz", html);
                else
                    xwtz.put("yyzz", html2);
                if (m8.contains("委托书"))
                    xwtz.put("wts", html);
                else
                    xwtz.put("wts", html2);
                if (StringUtils.isNotBlank(xwtzinfo.get("m4").toString()))
                    xwtz.put("qt", html);
                else
                    xwtz.put("qt", html2);
            } else {
                xwtz.put("sfz", html2);
                xwtz.put("yyzz", html2);
                xwtz.put("wts", html2);
                xwtz.put("qt", html2);
            }

            map.put("b", xwtz);
        }

        //询问笔录
        List<XZCF_InterrogationRecordEntity> xwblList = xzcfXwblDao.findByLaid(id);
        List<Map<String, Object>> xwblmapList = new ArrayList<>();
        for (XZCF_InterrogationRecordEntity xwbl : xwblList) {
            if (!StringUtils.isEmpty(xwbl.getM9())) {
                String[] a = xwbl.getM9().split(":");
                xwbl.setM9(a[1]);
            }
            Map<String, Object> xwblmap = new HashMap<String, Object>();
            //询问起始时间解析
            if (xwbl.getM1() != null && !xwbl.getM1().toString().equals("")) {
                String a = xwbl.getM1().toString();
                String[] as1 = a.substring(0, 10).split("-");
                xwblmap.put("year", as1[0]);
                xwblmap.put("month1", as1[1]);
                xwblmap.put("day1", as1[2]);
                String[] bs1 = a.substring(11, 16).split(":");
                xwblmap.put("hour1", bs1[0]);
                xwblmap.put("min1", bs1[1]);
            } else {
                xwblmap.put("year", "    ");
                xwblmap.put("month1", "   ");
                xwblmap.put("day1", "   ");
                xwblmap.put("hour1", "   ");
                xwblmap.put("min1", "   ");
            }
            //询问结束时间解析
            if (xwbl.getM2() != null && !xwbl.getM2().toString().equals("")) {
                String b = xwbl.getM2().toString();
                String[] as2 = b.substring(0, 10).split("-");
                xwblmap.put("month2", as2[1]);
                xwblmap.put("day2", as2[2]);
                String[] bs2 = b.substring(11, 16).split(":");
                xwblmap.put("hour2", bs2[0]);
                xwblmap.put("min2", bs2[1]);
            } else {
                xwblmap.put("month2", "   ");
                xwblmap.put("day2", "  ");
                xwblmap.put("hour2", "   ");
                xwblmap.put("min2", "   ");
            }
            //检查人员解析
            if (!StringUtils.isEmpty(xwbl.getM15())) {
                String c = xwbl.getM15();
                String[] as3 = c.split(",");
                //证件号
                String zjh = "";
                String m15 = "";
                for (int i = 0; i < as3.length; i++) {
                    m15 = m15 + as3[i] + "、";
                    AQZF_TipstaffEntity a = aqzfZfryDao.findByM1(as3[i]);
                    if (a != null) {
                        zjh = zjh + (StringUtils.isEmpty(a.getM3()) ? "" : a.getM3()) + "、";
                    }
                }
                zjh = zjh.substring(0, zjh.length() - 1);
                m15 = m15.substring(0, m15.length() - 1);
                xwblmap.put("m15", m15);
                xwblmap.put("zjh", zjh);
            } else {
                xwblmap.put("m15", "                 ");
                xwblmap.put("zjh", "                 ");
            }
            xwblmap.put("m0", StringUtils.isEmpty(xwbl.getM0()) ? "   " : xwbl.getM0());
            xwblmap.put("m3", StringUtils.isEmpty(xwbl.getM3()) ? "                   " : xwbl.getM3());
            xwblmap.put("m4", StringUtils.isEmpty(xwbl.getM4()) ? "      " : xwbl.getM4());
            xwblmap.put("m5", StringUtils.isEmpty(xwbl.getM5()) ? "   " : xwbl.getM5());
            xwblmap.put("m6", StringUtils.isEmpty(xwbl.getM6()) ? "   " : xwbl.getM6());
            xwblmap.put("m7", StringUtils.isEmpty(xwbl.getM7()) ? "              " : xwbl.getM7());
            xwblmap.put("m8", StringUtils.isEmpty(xwbl.getM8()) ? "                             " : xwbl.getM8());
            xwblmap.put("m9", StringUtils.isEmpty(xwbl.getM9()) ? "           " : xwbl.getM9());
            xwblmap.put("m10", StringUtils.isEmpty(xwbl.getM10()) ? "                                   " : xwbl.getM10());
            xwblmap.put("m11", StringUtils.isEmpty(xwbl.getM11()) ? "              " : xwbl.getM11());

            String ssqmc = bh.getSsqmc() == null ? "" : bh.getSsqmc();
            xwblmap.put("ssqmc", ssqmc);
            //询问人单位职务
            List<Map<String, Object>> xwblxwrlist = new ArrayList<>();
            if (!StringUtils.isEmpty(xwbl.getM12())) {
                String d = xwbl.getM12();
                String[] ds = d.split(",");
                for (int i = 0; i < ds.length; i++) {
                    Map<String, Object> zmap = new HashMap<>();
                    zmap.put("z1", ds[i]);
                    AQZF_TipstaffEntity at1 = aqzfZfryDao.findByM1(ds[i]);
                    if (at1 != null) {
                        zmap.put("z2", ssqmc + "应急管理局" + (StringUtils.isEmpty(at1.getM4()) ? "" : at1.getM4()));
                    } else {
                        zmap.put("z2", ssqmc + "应急管理局");
                    }

                    xwblxwrlist.add(zmap);
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    Map<String, Object> zmap = new HashMap<>();
                    zmap.put("z1", "          ");
                    zmap.put("z2", "                                  ");
                    xwblxwrlist.add(zmap);
                }
            }
            xwblmap.put("Xwrlist", xwblxwrlist);
            //记录人单位职务
            if (!StringUtils.isEmpty(xwbl.getM13())) {
                xwblmap.put("m13", xwbl.getM13());
                AQZF_TipstaffEntity at2 = aqzfZfryDao.findByM1(xwbl.getM13());
                if (at2 != null) {
                    xwblmap.put("m13_1", " " + ssqmc + "应急管理局" + (StringUtils.isEmpty(at2.getM4()) ? "" : at2.getM4()));
                } else {
                    xwblmap.put("m13_1", " " + ssqmc + "应急管理局");
                }

            } else {
                xwblmap.put("m13", "          ");
                xwblmap.put("m13_1", "                                  ");
            }
            xwblmap.put("m14", StringUtils.isEmpty(xwbl.getM14()) ? "                           " : xwbl.getM14());
            xwblmap.put("m16", StringUtils.isEmpty(xwbl.getM16()) ? "                      " : xwbl.getM16());
            //处理m17富文本内容
            String z = xwbl.getM17();
            String M17 = "";
            if (!StringUtils.isEmpty(z)) {
                String[] zs = z.split("</p>");
                for (int i = 0; i < zs.length; i++) {
                    M17 += StringEscapeUtils.escapeHtml3(this.getTextFromHtml(zs[i])) + "<w:p />";
                }
            }
            xwblmap.put("m17", M17);
            xwblmapList.add(xwblmap);
        }
        map.put("c", xwblmapList);

        //调查报告
        map.put("dcflag", lasp.getDcflag());
        if (lasp.getDcflag().equals("1")) {
            XZCF_YbcfDcbgEntity dcbg = xzcfcommondcdao.findInfoByLaId(id);
            Map<String, Object> dcbgmap = new HashMap<String, Object>();
            dcbgmap.put("bgbh", dcbg.getBgbh());
            if (lasp.getCfdxlx().equals("2")) {
                dcbgmap.put("qyname", dcbg.getCfryname());
            } else {
                dcbgmap.put("qyname", dcbg.getQyname());
            }
            dcbgmap.put("anyname", dcbg.getAyname() == null ? "" : dcbg.getAyname());
            dcbgmap.put("cbjg", dcbg.getCbjg() == null ? "" : dcbg.getCbjg());
            dcbgmap.put("researchresult", dcbg.getResearchresult() == null ? "" : dcbg.getResearchresult());
            dcbgmap.put("unlaw", dcbg.getUnlaw() == null ? "" : dcbg.getUnlaw());
            dcbgmap.put("enlaw", dcbg.getEnlaw() == null ? "" : dcbg.getEnlaw());
            dcbgmap.put("xzcf", dcbg.getXzcf() == null ? "" : dcbg.getXzcf());
            dcbgmap.put("cbr", dcbg.getEnforcer1() + (StringUtils.isEmpty(dcbg.getEnforcer2()) ? "" : ("   " + dcbg.getEnforcer2())));

            //证据
            List<Map<String, Object>> czwt = xzcfDCzjDao.dataGridCzwt(dcbg.getID());
            String evidence = "";
            if (czwt.size() > 0) {
                int i = 1;
                for (Map<String, Object> map2 : czwt) {
                    evidence += "    证据" + i + "：" + map2.get("m1").toString() + "<w:p />";
                    i++;
                }
            }
            dcbgmap.put("getevidence", evidence);

            if (lasp.getZycllx().equals("2")) {
                //新自由裁量
                List<Map<String, Object>> zyclList = new ArrayList<>();
                if (lasp.getZycllx().equals("2")) {
                    zyclList = xzcfDCzyclDao.findInfoByDcidTwo(dcbg.getID());
                } else {
                    zyclList = xzcfDCzyclDao.findInfoByDcid(dcbg.getID());
                }
                String zycl = "";
                if (zyclList.size() > 0) {
                    if (zyclList.size() == 1) {
                        zycl += "1、处罚档次：<w:p />";
                        zycl += "   定为" + zyclList.get(0).get("m1").toString() + "。<w:p />";
                        zycl += "2、具体裁量计算：<w:p />";
                        zycl += "   S=" + zyclList.get(0).get("m2").toString() + "。<w:p />";
                    } else {
                        int i = 1;
                        String s = "";
                        String gs = "";
                        float jg = 0;
                        for (Map<String, Object> map2 : zyclList) {
                            zycl += "   " + i + "、" + map2.get("wfxwname").toString() + "：<w:p />";
                            zycl += "   1）处罚档次：<w:p />";
                            zycl += "   定为" + map2.get("m1").toString() + "。<w:p />";
                            zycl += "   2）具体裁量计算：<w:p />";
                            zycl += "   S" + i + "=" + map2.get("m2").toString() + "。<w:p />";
                            if (i == 1) {
                                s = "S=S" + i;
                                gs = "=" + map2.get("m3").toString();
                            } else {
                                s += "+S" + i;
                                gs += "+" + map2.get("m3").toString();
                            }
                            jg += Float.parseFloat(map2.get("m3").toString());
                            i++;
                        }
                        zycl += "   " + i + "、合并处罚：" + s + gs + "≈" + (int) (jg / 100) * 100 + "<w:p />";
                    }
                }
                dcbgmap.put("zycl", zycl);
            }

            map.put("d", dcbgmap);
        }

        //处罚告知书
        map.put("gzflag", lasp.getGzflag());
        if (lasp.getGzflag().equals("1")) {
            XZCF_GzsInfoEntity cfgz = punishsimplegzdao.findInfoByLaId(id);
            XZCF_YbcfDcbgEntity dcbg = xzcfcommondcdao.findInfoByLaId(cfgz.getId1());
            //证据
            List<Map<String, Object>> evidencelist = xzcfDCzjDao.dataGridCzwt(dcbg.getID());

            Map<String, Object> cfgzmap = new HashMap<String, Object>();
            cfgzmap.put("Evidencelist", evidencelist);

            cfgzmap.put("name", cfgz.getName() == null ? "" : cfgz.getName());

            String reg = ".*[\\.。]$";
            cfgzmap.put("illegalact", cfgz.getIllegalact() == null ? "" : (cfgz.getIllegalact().matches(reg) ? cfgz.getIllegalact().substring(0, cfgz.getIllegalact().length() - 1) : cfgz.getIllegalact()) + "，");

            cfgzmap.put("wfxw", cfgz.getWfxw() == null ? "" : cfgz.getWfxw());
            cfgzmap.put("xq", cfgz.getPunishresult() == null ? "" : cfgz.getPunishresult());
            cfgzmap.put("unlaw", cfgz.getUnlaw() == null ? "" : cfgz.getUnlaw());
            cfgzmap.put("enlaw", cfgz.getEnlaw() == null ? "" : cfgz.getEnlaw());
            cfgzmap.put("xzcf", cfgz.getXzcf() == null ? "" : cfgz.getXzcf());
            cfgzmap.put("address", bh.getAddress() == null ? "" : bh.getAddress());
            cfgzmap.put("ybcode", bh.getYbcode() == null ? "" : bh.getYbcode());
            cfgzmap.put("contactname", cfgz.getContactname() == null ? "" : cfgz.getContactname());
            cfgzmap.put("phone", cfgz.getPhone() == null ? "" : cfgz.getPhone());
            cfgzmap.put("number", cfgz.getNumber());
            cfgzmap.put("ssqmc", bh.getSsqmc() == null ? "" : bh.getSsqmc());
            cfgzmap.put("cfgzsj", cfgz.getPunishdate() == null ? "年     月     日" : DateUtils.formatDate(cfgz.getPunishdate(), "yyyy 年 MM 月 dd 日"));

            XZCF_YbcfLaspEntity ybcf = punishcommonlaspdao.findInfoById(cfgz.getId1());
            String cfdx = "";
            if (ybcf.getCfdxlx().equals("2")) {
                cfdx = "你";
            } else {
                cfdx = "你公司";
            }
            cfgzmap.put("cfdx", cfdx);

            map.put("e", cfgzmap);
        }

        //陈述申辩
        map.put("sbflag", lasp.getSbflag());
        if (lasp.getSbflag().equals("1")) {
            XZCF_CssbblEntity jttl = xzcfCssbblDao.findWordByLaId(id);
            Map<String, Object> cssbmap = new HashMap<String, Object>();
            cssbmap.put("m3", StringUtils.isEmpty(jttl.getM3()) ? "" : jttl.getM3());
            cssbmap.put("m4", StringUtils.isEmpty(jttl.getM4()) ? "         " : jttl.getM4());
            cssbmap.put("m5", StringUtils.isEmpty(jttl.getM5()) ? "  " : jttl.getM5());
            cssbmap.put("m6", StringUtils.isEmpty(jttl.getM6()) ? "      " : jttl.getM6());
            cssbmap.put("m7", StringUtils.isEmpty(jttl.getM7()) ? "                             " : jttl.getM7());
            cssbmap.put("m8", StringUtils.isEmpty(jttl.getM8()) ? "" : jttl.getM8());
            cssbmap.put("m9", StringUtils.isEmpty(jttl.getM9()) ? "                             " : jttl.getM9());
            cssbmap.put("m10", StringUtils.isEmpty(jttl.getM10()) ? "" : jttl.getM10());
            cssbmap.put("m11", StringUtils.isEmpty(jttl.getM11()) ? "                   " : jttl.getM11());
            cssbmap.put("m12", StringUtils.isEmpty(jttl.getM12()) ? "         " : jttl.getM12());
            cssbmap.put("m13", StringUtils.isEmpty(jttl.getM13()) ? "         " : jttl.getM13());
            cssbmap.put("m14", StringUtils.isEmpty(jttl.getM14()) ? "         " : jttl.getM14());
            cssbmap.put("m15", StringUtils.isEmpty(jttl.getM15()) ? "         " : jttl.getM15());
            cssbmap.put("m16", StringUtils.isEmpty(jttl.getM16()) ? "" : jttl.getM16());
            cssbmap.put("m17", StringUtils.isEmpty(jttl.getM17()) ? "         " : jttl.getM17());
            //解析时间
            if (jttl.getM1() != null) {
                cssbmap.put("year", (jttl.getM1().getYear() + 1900) + "");
                cssbmap.put("month1", jttl.getM1().getMonth() + 1);
                cssbmap.put("day1", jttl.getM1().getDate());
                cssbmap.put("hour1", jttl.getM1().getHours());
                cssbmap.put("min1", jttl.getM1().getMinutes());
            } else {
                cssbmap.put("year", "   ");
                cssbmap.put("month1", "  ");
                cssbmap.put("day1", "  ");
                cssbmap.put("hour1", "  ");
                cssbmap.put("min1", "  ");
            }
            if (jttl.getM2() != null) {
                cssbmap.put("month2", jttl.getM2().getMonth() + 1);
                cssbmap.put("day2", jttl.getM2().getDate());
                cssbmap.put("hour2", jttl.getM2().getHours());
                cssbmap.put("min2", jttl.getM2().getMinutes());
            } else {
                cssbmap.put("month2", "  ");
                cssbmap.put("day2", "  ");
                cssbmap.put("hour2", "  ");
                cssbmap.put("min2", "  ");
            }

            cssbmap.put("ssqmc", bh.getSsqmc() == null ? "" : bh.getSsqmc());

            map.put("f", cssbmap);
        }


        //听证告知
        map.put("tzflag", lasp.getTzflag());
        if (lasp.getTzflag().equals("1")) {
            XZCF_YbcfTzgzEntity tzgz = xzcfcommontzdao.findInfoByLaId(id);
            XZCF_YbcfDcbgEntity dcbg = xzcfcommondcdao.findInfoByLaId(tzgz.getId1());
            //证据
            List<Map<String, Object>> evidencelist = xzcfDCzjDao.dataGridCzwt(dcbg.getID());

            Map<String, Object> tzgzmap = new HashMap<String, Object>();
            tzgzmap.put("Evidencelist", evidencelist);

            tzgzmap.put("name", tzgz.getName() == null ? "" : tzgz.getName());

            //验证结尾是否是，
            String reg = ".*[\\.。]$";
            tzgzmap.put("illegalact", tzgz.getIllegalact() == null ? "" : (tzgz.getIllegalact().matches(reg) ? tzgz.getIllegalact().substring(0, tzgz.getIllegalact().length() - 1) : tzgz.getIllegalact()) + "，");

            tzgzmap.put("wfxw", tzgz.getWfxw() == null ? "" : tzgz.getWfxw());
            tzgzmap.put("xq", tzgz.getPunishresult() == null ? "" : tzgz.getPunishresult());
            tzgzmap.put("unlaw", tzgz.getUnlaw() == null ? "" : tzgz.getUnlaw());
            tzgzmap.put("enlaw", tzgz.getEnlaw() == null ? "" : tzgz.getEnlaw());
            tzgzmap.put("xzcf", tzgz.getXzcf() == null ? "" : tzgz.getXzcf());
            tzgzmap.put("address", bh.getAddress() == null ? "" : bh.getAddress());
            tzgzmap.put("ybcode", bh.getYbcode() == null ? "" : bh.getYbcode());
            tzgzmap.put("contactname", tzgz.getContactname() == null ? "" : tzgz.getContactname());
            tzgzmap.put("phone", tzgz.getPhone() == null ? "" : tzgz.getPhone());
            tzgzmap.put("number", tzgz.getNumber());
            tzgzmap.put("ssqmc", bh.getSsqmc() == null ? "" : bh.getSsqmc());
            tzgzmap.put("tzgzsj", tzgz.getPunishdate() == null ? "年     月     日" : DateUtils.formatDate(tzgz.getPunishdate(), "yyyy 年 MM 月 dd 日"));

            XZCF_YbcfLaspEntity ybcf = punishcommonlaspdao.findInfoById(tzgz.getId1());
            String cfdx = "";
            if (ybcf.getCfdxlx().equals("2")) {
                cfdx = "你";
            } else {
                cfdx = "你公司";
            }
            tzgzmap.put("cfdx", cfdx);

            map.put("g", tzgzmap);
        }


        //案件呈批
        map.put("cbflag", lasp.getCbflag());
        if (lasp.getCbflag().equals("1")) {
            XZCF_YbcfAjcpEntity ajcp = xzcfcommonajcpdao.findInfoByLaId(id);
            Map<String, Object> ajcpmap = new HashMap<String, Object>();
            //企业个人公有
            if ("1".equals(ajcp.getPercomflag())) {
                ajcpmap.put("punishname", "");
                ajcpmap.put("punishdwname", ajcp.getPunishname());
            } else {
                ajcpmap.put("punishname", ajcp.getPunishname());
                ajcpmap.put("punishdwname", "");
            }
            ajcpmap.put("number", ajcp.getNumber());
            ajcpmap.put("casename", ajcp.getCasename());
            ajcpmap.put("qyaddress", ajcp.getQyaddress() == null ? "" : ajcp.getQyaddress());
            ajcpmap.put("qyyb", ajcp.getQyyb() == null ? "" : ajcp.getQyyb());
            ajcpmap.put("jtyb", ajcp.getJtyb() == null ? "" : ajcp.getJtyb());
            ajcpmap.put("legal", ajcp.getLegal() == null ? "" : ajcp.getLegal());
            ajcpmap.put("sex", ajcp.getSex() == null ? "" : ajcp.getSex());
            ajcpmap.put("age", ajcp.getAge() == null ? "" : ajcp.getAge());
            ajcpmap.put("dwname", ajcp.getDwname() == null ? "" : ajcp.getDwname());
            ajcpmap.put("dwaddress", ajcp.getDwaddress() == null ? "" : ajcp.getDwaddress());
            ajcpmap.put("address", ajcp.getAddress() == null ? "" : ajcp.getAddress());
            ajcpmap.put("casecondition", ajcp.getIllegalactandevidence() == null ? "" : ajcp.getIllegalactandevidence());
            ajcpmap.put("mobile", ajcp.getMobile() == null ? "" : ajcp.getMobile());
            ajcpmap.put("sbrecord", ajcp.getSbrecord() == null ? "" : ajcp.getSbrecord());
            ajcpmap.put("opinion", ajcp.getOpinion() == null ? "" : ajcp.getOpinion());
            ajcpmap.put("duty", ajcp.getDuty() == null ? "" : ajcp.getDuty());
            ajcpmap.put("illegalactandevidence", ajcp.getIllegalactandevidence() == null ? "" : ajcp.getIllegalactandevidence());

            map.put("h", ajcpmap);
        }

        //集体讨论
        map.put("tlflag", lasp.getTlflag());
        if (lasp.getTlflag().equals("1")) {
            XZCF_JttlEntity jttl = xzcfJttlDao.findWordByLaId(id);
            Map<String, Object> jttlmap = new HashMap<String, Object>();
            jttlmap.put("m1", StringUtils.isEmpty(jttl.getM1()) ? "" : jttl.getM1());
            jttlmap.put("m2", StringUtils.isEmpty(jttl.getM2()) ? "" : jttl.getM2());
            jttlmap.put("m5", StringUtils.isEmpty(jttl.getM5()) ? "" : jttl.getM5());
            jttlmap.put("m6", StringUtils.isEmpty(jttl.getM6()) ? "           " : jttl.getM6());
            jttlmap.put("m7", StringUtils.isEmpty(jttl.getM7()) ? "           " : jttl.getM7());
            jttlmap.put("m8", StringUtils.isEmpty(jttl.getM8()) ? "           " : jttl.getM8());
            jttlmap.put("m9", StringUtils.isEmpty(jttl.getM9()) ? "" : jttl.getM9());
            jttlmap.put("m10", StringUtils.isEmpty(jttl.getM10()) ? "" : jttl.getM10());
            jttlmap.put("m11", StringUtils.isEmpty(jttl.getM11()) ? "" : jttl.getM11());
            jttlmap.put("m12", StringUtils.isEmpty(jttl.getM12()) ? "" : jttl.getM12());
            //解析时间
            if (jttl.getM3() != null) {
                jttlmap.put("year1", (jttl.getM3().getYear() + 1900) + "");
                jttlmap.put("month1", jttl.getM3().getMonth() + 1);
                jttlmap.put("day1", jttl.getM3().getDate());
                jttlmap.put("hour1", jttl.getM3().getHours());
                jttlmap.put("min1", jttl.getM3().getMinutes());
            } else {
                jttlmap.put("year1", "   ");
                jttlmap.put("month1", "  ");
                jttlmap.put("day1", "  ");
                jttlmap.put("hour1", "  ");
                jttlmap.put("min1", "  ");
            }
            if (jttl.getM4() != null) {
                jttlmap.put("year2", (jttl.getM4().getYear() + 1900) + "");
                jttlmap.put("month2", jttl.getM4().getMonth() + 1);
                jttlmap.put("day2", jttl.getM4().getDate());
                jttlmap.put("hour2", jttl.getM4().getHours());
                jttlmap.put("min2", jttl.getM4().getMinutes());
            } else {
                jttlmap.put("year2", "   ");
                jttlmap.put("month2", "  ");
                jttlmap.put("day2", "  ");
                jttlmap.put("hour2", "  ");
                jttlmap.put("min2", "  ");
            }

            map.put("i", jttlmap);
        }

        //处罚决定
        map.put("cfjdflag", lasp.getCfjdflag());
        if (lasp.getCfjdflag().equals("1")) {
            XZCF_CfjdInfoEntity cfjd = punishsimplecfjddao.findInfoByLaId(id);
            XZCF_YbcfDcbgEntity dcbg = xzcfcommondcdao.findInfoByLaId(cfjd.getId1());
            //证据
            List<Map<String, Object>> evidencelist = xzcfDCzjDao.dataGridCzwt(dcbg.getID());

            XZCF_GzsInfoEntity cfgz = punishsimplegzdao.findInfoByLaId(cfjd.getId1());
            Map<String, Object> cfjdmap = new HashMap<String, Object>();
            cfjdmap.put("Evidencelist", evidencelist);
            //企业个人公有
            cfjdmap.put("bankname", bh.getBankname() == null ? "" : bh.getBankname());
            cfjdmap.put("account", bh.getAccount() == null ? "" : bh.getAccount());
            cfjdmap.put("gov", bh.getGov() == null ? "" : bh.getGov());
            cfjdmap.put("highgov", bh.getHighgov() == null ? "" : bh.getHighgov());
            cfjdmap.put("court", bh.getCourt() == null ? "" : bh.getCourt());
            cfjdmap.put("number", cfjd.getNumber());
            cfjdmap.put("punishname", cfjd.getPunishname());
            cfjdmap.put("address", cfjd.getAddress());
            cfjdmap.put("mobile", cfjd.getMobile());
            cfjdmap.put("duty", cfjd.getDuty());
            String reg = ".*[\\.。]$";
            cfjdmap.put("illegalactandevidence", cfjd.getIllegalactandevidence() == null ? "" : (cfjd.getIllegalactandevidence().matches(reg) ? cfjd.getIllegalactandevidence().substring(0, cfjd.getIllegalactandevidence().length() - 1) : cfjd.getIllegalactandevidence()) + "，");
            cfjdmap.put("unlaw", cfjd.getUnlaw() == null ? "" : cfjd.getUnlaw());
            cfjdmap.put("enlaw", cfjd.getEnlaw() == null ? "" : cfjd.getEnlaw());
            cfjdmap.put("punishresult", cfjd.getPunishresult() == null ? "" : cfjd.getPunishresult());
            cfjdmap.put("ybcode", cfjd.getYbcode() == null ? "" : cfjd.getYbcode());
            cfjdmap.put("ssqmc", bh.getSsqmc() == null ? "" : bh.getSsqmc());

            cfjdmap.put("wfxw", cfjd.getWfxw() == null ? "" : (cfjd.getWfxw().matches(reg) ? cfjd.getWfxw().substring(0, cfjd.getWfxw().length() - 1) : cfjd.getWfxw()));
            //cfjdmap.put("evidence", cfjd.getEvidence()==null?"":cfjd.getEvidence());
            cfjdmap.put("xzcf", cfjd.getXzcf() == null ? "" : cfjd.getXzcf());
            cfjdmap.put("cfgzsj", cfgz.getPunishdate() == null ? "" : DateUtils.formatDate(cfgz.getPunishdate(), "yyyy年MM月dd日"));
            cfjdmap.put("cfgzsbh", cfgz.getNumber());
            if ("1".equals(cfjd.getPercomflag())) {
                cfjdmap.put("legal", cfjd.getLegal());
            } else {
                cfjdmap.put("sex", cfjd.getSex() == null ? "" : cfjd.getSex());
                cfjdmap.put("age", cfjd.getAge() == null ? "" : cfjd.getAge());
                cfjdmap.put("identity1", cfjd.getIdentity1() == null ? "" : cfjd.getIdentity1());
                cfjdmap.put("dwname", cfjd.getDwname() == null ? "" : cfjd.getDwname());
                cfjdmap.put("dwaddress", cfjd.getDwaddress() == null ? "" : cfjd.getDwaddress());
            }
            cfjdmap.put("cfjdsj", cfjd.getPunishdate() == null ? "年     月     日" : DateUtils.formatDate(cfjd.getPunishdate(), "yyyy 年 MM 月 dd 日"));
            cfjdmap.put("percomflag", cfjd.getPercomflag());//个人0企业1

            map.put("j", cfjdmap);
        }

        //罚款审批
        map.put("fkspflag", lasp.getFkspflag());
        if (lasp.getFkspflag().equals("1")) {
            XZCF_FkspEntity fksp = xzcfFkspDao.findWordByLaId(id);
            Map<String, Object> fkspmap = new HashMap<String, Object>();
            fkspmap.put("m1", StringUtils.isEmpty(fksp.getM1()) ? "" : fksp.getM1());
            fkspmap.put("m2", StringUtils.isEmpty(fksp.getM2()) ? "" : fksp.getM2());
            fkspmap.put("m3", StringUtils.isEmpty(fksp.getM3()) ? "" : fksp.getM3());
            fkspmap.put("m4", StringUtils.isEmpty(fksp.getM4()) ? "" : fksp.getM4());
            fkspmap.put("m5", StringUtils.isEmpty(fksp.getM5()) ? "" : fksp.getM5());
            fkspmap.put("m6", StringUtils.isEmpty(fksp.getM6()) ? "" : fksp.getM6());
            fkspmap.put("m7", StringUtils.isEmpty(fksp.getM7()) ? "" : fksp.getM7());
            fkspmap.put("m8", StringUtils.isEmpty(fksp.getM8()) ? "" : fksp.getM8());

            map.put("k", fkspmap);
        }


        //罚款批准
        List<XZCF_FkpzEntity> fkpzList = xzcfFkpzDao.findWordByLaId(id);
        List<Map<String, Object>> fkpzmapList = new ArrayList<>();
        for (XZCF_FkpzEntity fkpz : fkpzList) {
            XZCF_CfjdInfoEntity cfjd = punishsimplecfjddao.findInfoByLaId(fkpz.getID1());
            Map<String, Object> fkpzmap = new HashMap<String, Object>();
            fkpzmap.put("m1", StringUtils.isEmpty(fkpz.getM1()) ? "" : fkpz.getM1());
            fkpzmap.put("m2", StringUtils.isEmpty(fkpz.getM2()) ? "            " : fkpz.getM2());
            fkpzmap.put("m4", StringUtils.isEmpty(fkpz.getM4()) ? "        " : fkpz.getM4());
            fkpzmap.put("m7", StringUtils.isEmpty(fkpz.getM7()) ? "    " : fkpz.getM7());
            fkpzmap.put("m9", StringUtils.isEmpty(fkpz.getM9()) ? "          " : fkpz.getM9());
            fkpzmap.put("m10", StringUtils.isEmpty(fkpz.getM10()) ? "          " : fkpz.getM10());
            if (cfjd.getPunishdate() != null) {
                fkpzmap.put("year", (cfjd.getPunishdate().getYear() + 1900) + "");
                fkpzmap.put("month", cfjd.getPunishdate().getMonth() + 1);
                fkpzmap.put("day", cfjd.getPunishdate().getDate());
            } else {
                fkpzmap.put("year", "     ");
                fkpzmap.put("month", "   ");
                fkpzmap.put("day", "   ");
            }
            fkpzmap.put("cfjd", cfjd.getNumber());
            if (fkpz.getM6() != null) {
                fkpzmap.put("year2", (fkpz.getM6().getYear() + 1900) + "");
                fkpzmap.put("month2", fkpz.getM6().getMonth() + 1);
                fkpzmap.put("day2", fkpz.getM6().getDate());
            } else {
                fkpzmap.put("year2", "     ");
                fkpzmap.put("month2", "   ");
                fkpzmap.put("day2", "   ");
            }
            if (fkpz.getM8() != null) {
                fkpzmap.put("year3", (fkpz.getM8().getYear() + 1900) + "");
                fkpzmap.put("month3", fkpz.getM8().getMonth() + 1);
                fkpzmap.put("day3", fkpz.getM8().getDate());
            } else {
                fkpzmap.put("year3", "     ");
                fkpzmap.put("month3", "   ");
                fkpzmap.put("day3", "   ");
            }
            String html = "<w:sym w:font=\"Wingdings 2\" w:char=\"0052\"/>";
            String html2 = "<w:t>□</w:t>";
            if (fkpz.getM5().equals("1")) {
                fkpzmap.put("a", html);
                fkpzmap.put("b", html2);
            } else {
                fkpzmap.put("a", html2);
                fkpzmap.put("b", html);
            }
            fkpzmapList.add(fkpzmap);
        }
        map.put("l", fkpzmapList);


        //结案审批
        map.put("jaflag", lasp.getJaflag());
        if (lasp.getJaflag().equals("1")) {
            Map<String, Object> jaspmap = new HashMap<String, Object>();
            //企业个人公有
            if ("1".equals(jasp.getPercomflag())) {
                jaspmap.put("punishname", "");
                jaspmap.put("punishdwname", jasp.getPunishname());
            } else {
                jaspmap.put("punishname", jasp.getPunishname());
                jaspmap.put("punishdwname", "");
            }
            jaspmap.put("number", jasp.getNumber());
            jaspmap.put("casename", jasp.getCasename());
            jaspmap.put("qyaddress", jasp.getQyaddress() == null ? "" : jasp.getQyaddress());
            jaspmap.put("qyyb", jasp.getQyyb() == null ? "" : jasp.getQyyb());
            jaspmap.put("jtyb", jasp.getJtyb() == null ? "" : jasp.getJtyb());
            jaspmap.put("legal", jasp.getLegal() == null ? "" : jasp.getLegal());
            jaspmap.put("sex", jasp.getSex() == null ? "" : jasp.getSex());
            jaspmap.put("age", jasp.getAge() == null ? "" : jasp.getAge());
            jaspmap.put("dwname", jasp.getDwname() == null ? "" : jasp.getDwname());
            jaspmap.put("dwaddress", jasp.getDwaddress() == null ? "" : jasp.getDwaddress());
            jaspmap.put("duty", jasp.getDuty() == null ? "" : jasp.getDuty());
            jaspmap.put("addresss", jasp.getAddress() == null ? "" : jasp.getAddress());
            jaspmap.put("result", jasp.getResult() == null ? "" : jasp.getResult());
            jaspmap.put("mobile", jasp.getMobile() == null ? "" : jasp.getMobile());
            jaspmap.put("exeucondition", jasp.getExeucondition());
            map.put("m", jaspmap);
        }

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

    public String getTextFromHtml(String htmlStr) {
        htmlStr = this.delHTMLTag(htmlStr);
        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        // htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);
        return htmlStr;
    }
}
