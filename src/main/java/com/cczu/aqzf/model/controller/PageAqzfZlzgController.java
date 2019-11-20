package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.AQZF_ReformEntity;
import com.cczu.aqzf.model.entity.AQZF_SafetyCheckRecordEntity;
import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.AQZF_TipstaffEntity;
import com.cczu.aqzf.model.service.*;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


/**
 * 责令整改Controller
 * @author YZH
 */
@Controller
@RequestMapping("aqzf/zlzg")
public class PageAqzfZlzgController extends BaseController {

	@Autowired
	private AqzfJcjlService aqzfJcjlService;
	@Autowired
	private AqzfZlzgService aqzfZlzgService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private AqzfJcnrService aqzfJcnrService;
	@Autowired
	private AqzfZfryService aqzfZfryService;
	
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "aqzf/zlzg/index";
	}
	
	/**
	 * list页面 
	 * @param request
	 */
	@RequiresPermissions("aqzf:zlzg:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qyname", request.getParameter("aqzf_zlzg_qyname"));
		map.put("M3", request.getParameter("aqzf_zlzg_M3"));
		return aqzfZlzgService.dataGrid(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 * id 记录id
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id, Model model) {
		AQZF_ReformEntity zlzg=new AQZF_ReformEntity();
		//根据检查记录id查找检查记录
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(id);
		if(!StringUtils.isBlank(jcjl.getM8())){
			String []zfry = jcjl.getM8().split(",");
			if(zfry.length>1){
				AQZF_TipstaffEntity xzry1=aqzfZfryService.findBym1(zfry[0]);
				if(xzry1!=null){
					zlzg.setM6_1(xzry1.getM1());
					zlzg.setM7_1(xzry1.getM3());
				}
				AQZF_TipstaffEntity xzry2=aqzfZfryService.findBym1(zfry[1]);
				if(xzry2!=null){
					zlzg.setM6_2(xzry2.getM1());
					zlzg.setM7_2(xzry2.getM3());
				}
			}else{
				AQZF_TipstaffEntity xzry=aqzfZfryService.findBym1(zfry[0]);
				if(xzry!=null){
					zlzg.setM6_1(xzry.getM1());
					zlzg.setM7_1(xzry.getM3());
				}
			}
		}
		
		//设置相关信息政府法院
		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
		zlzg.setM4(sbe.getGov()==null?"":sbe.getGov());
		zlzg.setM5(sbe.getCourt()==null?"":sbe.getCourt());

		zlzg.setM8(jcjl.getM2());//负责人
		zlzg.setID2(jcjl.getID2());//企业id
		zlzg.setID1(jcjl.getID());//检查记录id
		zlzg.setM1(jcjl.getM6());//检查日期
		zlzg.setM10(jcjl.getM15());//自由裁量类型
		//存在问题
		List<Map<String,Object>> list = aqzfJcnrService.findAllByJlid(id,jcjl.getM15()+"");
		model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(list));
		model.addAttribute("action", "create");
		model.addAttribute("zlzg",zlzg);
		return "aqzf/zlzg/form";
	}
	
	/**
	 * 添加责令整改信息
	 * @param model,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQZF_ReformEntity entity, Model model) {
		String datasuccess="success";
		//添加责令整改编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(entity.getID1());
		String m0 = jcjl.getM0();
		entity.setM0("（"+bh.getSsqjc()+"）应急责改〔"+m0.substring(m0.indexOf("〔")+1, m0.indexOf("〕"))+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setM11("0");
		aqzfZlzgService.addInfo(entity);
		return datasuccess;
		
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:zlzg:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的责令整改信息
		AQZF_ReformEntity zlzg = aqzfZlzgService.findById(id);
		model.addAttribute("zlzg", zlzg);
		//存在问题
		List<Map<String,Object>> list = aqzfJcnrService.findAllByJlid(zlzg.getID1(),zlzg.getM10()+"");
		model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(list));
		//返回页面
		model.addAttribute("action", "update");
		
		return "aqzf/zlzg/form";
	}
	
	/**
	 * 修改责令整改信息
	 * @param model,model
	 */
	@RequiresPermissions("aqzf:zlzg:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_ReformEntity entity, Model model){
		String datasuccess="success";	
		aqzfZlzgService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除责令整改信息
	 */
	@RequiresPermissions("aqzf:zlzg:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqzfZlzgService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:zlzg:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> zlzg = aqzfZlzgService.findInforById(id);
		//存在问题
		String sgyh = "";
		if(zlzg.get("m2")!=null&&!StringUtils.isEmpty(zlzg.get("m2").toString())){
			String wfxwids = zlzg.get("m2").toString();
			List<Map<String,Object>> list = aqzfJcnrService.findAllByids(wfxwids,zlzg.get("m10")+"");
			int i = 1;
			for (Map<String, Object> map : list) {
				sgyh += i+". 检查情况："+(map.get("wtms")==null?"":map.get("wtms").toString())+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;须整改的问题："+(map.get("zgwt")==null?"":map.get("zgwt").toString())+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;涉嫌违法行为："+(map.get("m1")==null?"":map.get("m1").toString())+"<br/>";
				i++;
			}
		}
		model.addAttribute("czwt", sgyh);
		model.addAttribute("zlzg", zlzg);

		return "aqzf/zlzg/view";
	}

	/**
	 * 导出责令限期整改指令书word
	 * 
	 */
	@RequiresPermissions("aqzf:zlzg:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = aqzfZlzgService.getAjWord(id);
		//设置导出的文件名
		//String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		String filename = map.get("m0").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "zlzgzls.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
}
