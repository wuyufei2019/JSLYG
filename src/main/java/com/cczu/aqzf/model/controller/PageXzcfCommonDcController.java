package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.*;
import com.cczu.aqzf.model.service.*;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 行政处罚--调查报告controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/dcbg")
public class PageXzcfCommonDcController extends BaseController {

	@Autowired
	private XzcfCommonDcService xzcfcommondcservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private IBisQyjbxxService bisqyjbxxservice;
	@Autowired
	private AqzfWfxwService aqzfWfxwService;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	@Autowired
	private XzcfDCzjService xzcfDCzjService;
	@Autowired
	private AqzfJcnrService aqzfJcnrService;
	@Autowired
	private XzcfSgdczjService xzcfSgdczjService;
	@Autowired
	private XzcfDCzyclService xzcfDCzyclService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/dcbg/index";
	}
	
	/**
	 * 自由裁量
	 */
	@RequestMapping(value = "zycl/{sids}")
	public String zycl(@PathVariable("sids") String sids,Model model) {
		model.addAttribute("flag", "1");
		if(!sids.equals("flag")){
			List<Map<String,Object>> list = aqzfJcnrService.findAllByids(sids,"1");
			for (Map<String, Object> map : list) {
				if(map.get("wfid")!=null){
					model.addAttribute("wfid", list.get(0).get("wfid").toString());
					model.addAttribute("flag", "0");
					break;
				}
			}
		}
		return "aqzf/xzcf/ybcf/dcbg/zycl";
	}
	
	/**
	 * 自由裁量（新）
	 */
	@RequestMapping(value = "zycl2/{sids}")
	public String zycl2(@PathVariable("sids") String sids,Model model) {
		model.addAttribute("flag", "1");
		if(!sids.equals("flag")){
			List<Map<String,Object>> list = aqzfJcnrService.findAllByids(sids,"2");
			for (Map<String, Object> map : list) {
				if(map.get("wfid")!=null){
					model.addAttribute("wfid", list.get(0).get("wfid").toString());
					model.addAttribute("flag", "0");
					break;
				}
			}
		}
		return "aqzf/xzcf/ybcf/dcbg/zycl2";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("xzcf:dcbg:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("bh", request.getParameter("ybcf_dcbg_bh"));
		map.put("name", request.getParameter("ybcf_dcbg_name"));
		map.put("qyname", request.getParameter("ybcf_dcbg_qyname"));
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		return xzcfcommondcservice.dataGrid(map);
	}

	/**
	 * 添加调查信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:dcbg:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		XZCF_YbcfDcbgEntity ybe= new XZCF_YbcfDcbgEntity();
		XZCF_YbcfLaspEntity yle=xzcfcommonlaspservice.findInfoById(id);
		BIS_EnterpriseEntity bis= bisqyjbxxservice.findInfoById(yle.getId1());
		ybe.setId1(id);
		ybe.setCasename(yle.getCasename());
		ybe.setAyname(yle.getAyname());
		ybe.setId2(yle.getId1());
		ybe.setCfryname(yle.getCfryname());
		ybe.setQyname(bis.getM1());
		ybe.setQyaddress(bis.getM8());
		ybe.setEconomytype(bis.getM10());
		ybe.setLegalperson(bis.getM19());
		ybe.setQyfounddate(bis.getM4());
		ybe.setBusinessscope(bis.getM14());
		ybe.setEnforcer1(yle.getEnforcer1());
		ybe.setEnforcer2(yle.getEnforcer2());
		AQZF_SetBasicInfoEntity sbe=setbasicservice.findInfor();
		ybe.setCbjg(sbe.getCbjg()==null?"":sbe.getCbjg());
		//案件情况
		ybe.setResearchresult(yle.getAjjbqk());
		
		List<Map<String,Object>> list = aqzfJcnrService.findAllByids(yle.getCasecondition(),yle.getZycllx()+"");
		model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(list));
		
		if(!StringUtils.isEmpty(yle.getSgtzid())){
			List<XZCF_SgdczjEntity> czwt = xzcfSgdczjService.findbyTzid(Long.parseLong(yle.getSgtzid()));
			model.addAttribute("czwt", JsonMapper.getInstance().toJson(czwt));
			model.addAttribute("flag", "1");
		}else{
			model.addAttribute("flag", "0");
		}
		model.addAttribute("ybe", ybe);
		model.addAttribute("cfdxlx",yle.getCfdxlx());
		if(yle.getZycllx().equals("2")){
			//新的自由裁量
			model.addAttribute("action", "createSub2");
			return "aqzf/xzcf/ybcf/dcbg/form2";
		}else{
			//旧
			model.addAttribute("action", "createSub");
			return "aqzf/xzcf/ybcf/dcbg/form";
		}
	}

	/**
	 * 添加调查信息
	 * @param request
	 */
	@RequiresPermissions("xzcf:dcbg:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfDcbgEntity ybe, String czwt, String czwturl, HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		ybe.setS1(t);
		ybe.setS2(t);
		ybe.setS3(0);
		//设置编号
		AQZF_SetBasicInfoEntity sbe=setbasicservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(ybe.getId1());
		String m0 = ybcf.getNumber();
		if(ybcf.getCxflag().equals("1")){
			//已立案
			ybe.setBgbh("（"+sbe.getSsqjc()+"）应急终报〔"+m0.substring(m0.indexOf("〔")+1, m0.indexOf("〕"))+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		}else{
			//预立案
			ybe.setBgbh("预立案");
		}
		
		long id=xzcfcommondcservice.addInfoReturnID(ybe);
		
		if(id>0){
			//获取证据
			if(!StringUtils.isEmpty(czwt)){
				String[] czwt2=czwt.split(",");//证据
				String[] czwturl2=czwturl.split(",");//照片
				for(int i=0;i<czwt2.length;i++){
					XZCF_DczjEntity zj=new XZCF_DczjEntity();
					zj.setID1(id);//检查记录id
					zj.setM1(czwt2[i]);//证据
					try {
						zj.setM2(czwturl2[i]);//照片
					} catch (Exception e) {
						zj.setM2("");
					}
					xzcfDCzjService.addInfo(zj);//添加证据
				}
			}
			
			XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(ybe.getId1());
			yle.setDcflag("1");
			xzcfcommonlaspservice.updateInfo(yle);
			datasuccess = "success";
		}
		return datasuccess;
	}
	
	/**
	 * 添加调查信息2
	 * @param request
	 */
	@RequiresPermissions("xzcf:dcbg:add")
	@RequestMapping(value = "createSub2", method = RequestMethod.POST)
	@ResponseBody
	public String createSub2(XZCF_YbcfDcbgEntity ybe, String czwt, String czwturl, String zyclwfid, String zyclzz1, String zyclzz2, String zycljg, HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		ybe.setS1(t);
		ybe.setS2(t);
		ybe.setS3(0);
		//设置编号
		AQZF_SetBasicInfoEntity sbe=setbasicservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(ybe.getId1());
		String m0 = ybcf.getNumber();
		if(ybcf.getCxflag().equals("1")){
			//已立案
			ybe.setBgbh("（"+sbe.getSsqjc()+"）应急终报〔"+m0.substring(m0.indexOf("〔")+1, m0.indexOf("〕"))+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		}else{
			//预立案
			ybe.setBgbh("预立案");
		}
		
		long id=xzcfcommondcservice.addInfoReturnID(ybe);
		
		if(id>0){
			//获取证据
			if(!StringUtils.isEmpty(czwt)){
				String[] czwt2=czwt.split(",");//证据
				String[] czwturl2=czwturl.split(",");//照片
				for(int i=0;i<czwt2.length;i++){
					XZCF_DczjEntity zj=new XZCF_DczjEntity();
					zj.setID1(id);//检查记录id
					zj.setM1(czwt2[i]);//证据
					try {
						zj.setM2(czwturl2[i]);//照片
					} catch (Exception e) {
						zj.setM2("");
					}
					xzcfDCzjService.addInfo(zj);//添加证据
				}
			}
			
			//自由裁量
			if(!StringUtils.isEmpty(zyclwfid)){
				String[] zyclwfid2=zyclwfid.split(",");//违法行为id
				String[] zyclzz12=zyclzz1.split(",");//处罚档次
				String[] zyclzz22=zyclzz2.split(",");//具体裁量计算
				String[] zycljg2=zycljg.split(",");//结果
				for(int i=0;i<zyclwfid2.length;i++){
					XZCF_DczyclEntity zycl=new XZCF_DczyclEntity();
					zycl.setID1(id);
					zycl.setWfxwid(Long.parseLong(zyclwfid2[i]));
					zycl.setM1(zyclzz12[i]);
					zycl.setM2(zyclzz22[i]);
					zycl.setM3(zycljg2[i]);
					xzcfDCzyclService.addInfo(zycl);
				}
			}
			
			XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(ybe.getId1());
			yle.setDcflag("1");
			xzcfcommonlaspservice.updateInfo(yle);
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改调查报告信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:dcbg:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_YbcfDcbgEntity ybe = xzcfcommondcservice.findInfoById(id);
		model.addAttribute("ybe", ybe);
		
		XZCF_YbcfLaspEntity yle=xzcfcommonlaspservice.findInfoById(ybe.getId1());
		List<Map<String,Object>> list = aqzfJcnrService.findAllByids(yle.getCasecondition(),yle.getZycllx()+"");
		model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(list));
		//查询证据
		List<Map<String, Object>> czwt= xzcfDCzjService.dataGridCzwt(id);
		model.addAttribute("czwt", JsonMapper.getInstance().toJson(czwt));
		model.addAttribute("cfdxlx",yle.getCfdxlx());
		if(yle.getZycllx().equals("2")){
			//新的自由裁量
			model.addAttribute("zycllist", JsonMapper.getInstance().toJson(xzcfDCzyclService.dataGrid(id)));
			model.addAttribute("action", "updateSub2");
			return "aqzf/xzcf/ybcf/dcbg/form2";
		}else{
			//旧
			model.addAttribute("action", "updateSub");
			return "aqzf/xzcf/ybcf/dcbg/form";
		}
	}

	/**
	 * 修改调查报告信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:dcbg:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfDcbgEntity ybe, String czwt, String czwturl, HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		ybe.setS2(t);
		try {
			xzcfcommondcservice.updateInfo(ybe);
			
			xzcfDCzjService.deletebyId1(ybe.getID());
			//获取证据
			if(!StringUtils.isEmpty(czwt)){
				String[] czwt2=czwt.split(",");//证据
				String[] czwturl2=czwturl.split(",");//照片
				for(int i=0;i<czwt2.length;i++){
					XZCF_DczjEntity zj=new XZCF_DczjEntity();
					zj.setID1(ybe.getID());//检查记录id
					zj.setM1(czwt2[i]);//证据
					try {
						zj.setM2(czwturl2[i]);//照片
					} catch (Exception e) {
						zj.setM2("");
					}
					xzcfDCzjService.addInfo(zj);//添加证据
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}
	
	/**
	 * 修改调查报告信息2
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:dcbg:update")
	@RequestMapping(value = "updateSub2")
	@ResponseBody
	public String updateSub2(XZCF_YbcfDcbgEntity ybe, String czwt, String czwturl, String zyclwfid, String zyclzz1, String zyclzz2, String zycljg, HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		ybe.setS2(t);
		try {
			xzcfcommondcservice.updateInfo(ybe);
			
			xzcfDCzjService.deletebyId1(ybe.getID());
			//获取证据
			if(!StringUtils.isEmpty(czwt)){
				String[] czwt2=czwt.split(",");//证据
				String[] czwturl2=czwturl.split(",");//照片
				for(int i=0;i<czwt2.length;i++){
					XZCF_DczjEntity zj=new XZCF_DczjEntity();
					zj.setID1(ybe.getID());//检查记录id
					zj.setM1(czwt2[i]);//证据
					try {
						zj.setM2(czwturl2[i]);//照片
					} catch (Exception e) {
						zj.setM2("");
					}
					xzcfDCzjService.addInfo(zj);//添加证据
				}
			}
			
			xzcfDCzyclService.deletebyId1(ybe.getID());
			//自由裁量
			if(!StringUtils.isEmpty(zyclwfid)){
				String[] zyclwfid2=zyclwfid.split(",");//违法行为id
				String[] zyclzz12=zyclzz1.split(",");//处罚档次
				String[] zyclzz22=zyclzz2.split(",");//具体裁量计算
				String[] zycljg2=zycljg.split(",");//结果
				for(int i=0;i<zyclwfid2.length;i++){
					XZCF_DczyclEntity zycl=new XZCF_DczyclEntity();
					zycl.setID1(ybe.getID());
					zycl.setWfxwid(Long.parseLong(zyclwfid2[i]));
					zycl.setM1(zyclzz12[i]);
					zycl.setM2(zyclzz22[i]);
					zycl.setM3(zycljg2[i]);
					xzcfDCzyclService.addInfo(zycl);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:dcbg:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfDcbgEntity ybe = xzcfcommondcservice.findInfoById(id);
		XZCF_YbcfLaspEntity yle=xzcfcommonlaspservice.findInfoById(ybe.getId1());
		model.addAttribute("ybe", ybe);
		model.addAttribute("cfdxlx",yle.getCfdxlx());
		//查询证据
		List<Map<String, Object>> czwt= xzcfDCzjService.dataGridCzwt(id);
		model.addAttribute("czwt", JsonMapper.getInstance().toJson(czwt));
		return "aqzf/xzcf/ybcf/dcbg/view";
	}

	/**
	 * 删除信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("xzcf:dcbg:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				xzcfcommondcservice.deleteInfo(Long.parseLong(arrids[i]));
				xzcfcommondcservice.updateLaspInfo(Long.parseLong(arrids[i]));
			}
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="删除失败";
		}
		
		return datasuccess;
	}
	
	/**
	 * 导出调查报告word
	 * 
	 */
	@RequiresPermissions("xzcf:dcbg:export")
	@RequestMapping(value = "export/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String  flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
	    Map<String, Object> map = xzcfcommondcservice.getWsdcword(id,flag);
		//设置导出的文件名
	    String filename = map.get("bgbh").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		if(map.get("zycl")!=null){//新自由裁量
			WordUtil.ireportWord(map, "xzcfzjbg2.ftl", filePath, filename, request);
		}else{
			WordUtil.ireportWord(map, "xzcfzjbg.ftl", filePath, filename, request);
		}
		return "/download/" + filename;
	}
	
	/**
	 * 证据添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "choosenr")
	public String choosepic(Model model) {
		return "aqzf/xzcf/ybcf/dcbg/nrForm";
	}
	
	/**
	 * 总览查看信息跳转
	 * @param model
	 */
	@RequestMapping(value = "viewdcbg/{id}")
	public String viewdcbg(@PathVariable("id") long id, Model model) {
		XZCF_YbcfDcbgEntity ybe = xzcfcommondcservice.findInfoByLaId(id);
		model.addAttribute("ybe", ybe);
		//查询证据
		List<Map<String, Object>> czwt= xzcfDCzjService.dataGridCzwt(ybe.getID());
		model.addAttribute("czwt", JsonMapper.getInstance().toJson(czwt));
		return "aqzf/xzcf/ybcf/dcbg/view";
	}
}
