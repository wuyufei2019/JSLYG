package com.cczu.aqzf.http.api;

import com.cczu.aqzf.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.aqzf.model.service.*;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.util.common.WordUtil;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * app文书导出
 * @author zpc
 * @date 2018/03/22
 */
@Controller
@RequestMapping("api/wsdc")
public class ApiWsdcController extends BaseController {
	
	@Autowired
	private AqzfJcfaService aqzfJcfaService;
	@Autowired
	private AqzfJcjlService aqzfJcjlService;
	@Autowired
	private AqzfClcsService aqzfClcsService;
	@Autowired
	private AqzfZlzgService aqzfZlzgService;
	@Autowired
	private AqzfFcyjService aqzfFcyjService;
	
	/**
	 * 监督检查文书导出
	 */
	@RequestMapping(value = "jdjc")
	@ResponseBody
	public String getJdjcwsdc(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		String type = request.getParameter("type");
		Map<String, Object> map = new HashMap<>();
		//设置导出的文件名
		String oldfilename = "lsws.doc";
		String filename = "";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		if(type.equals("jcfa")){
			map = aqzfJcfaService.getAjWord(id);
			filename = map.get("m0").toString() + ".doc";
			WordUtil.ireportWord(map, "xcjcfa.ftl", filePath, oldfilename, request);
		}else if(type.equals("jcjl")){
			map = aqzfJcjlService.getAjWord(id);
			filename = map.get("m0").toString() + ".doc";
			WordUtil.ireportWord(map, "xcjcjl.ftl", filePath, oldfilename, request);
		}else if(type.equals("clcs")){
			map = aqzfClcsService.getAjWord(id);
			filename = map.get("m0").toString() + ".doc";
			WordUtil.ireportWord(map, "clcsjds.ftl", filePath, oldfilename, request);
		}else if(type.equals("zlzg")){
			map = aqzfZlzgService.getAjWord(id);
			filename = map.get("m0").toString() + ".doc";
			WordUtil.ireportWord(map, "zlzgzls.ftl", filePath, oldfilename, request);
		}else if(type.equals("fcyj")){
			map = aqzfFcyjService.getAjWord(id);
			filename = map.get("m0").toString() + ".doc";
			WordUtil.ireportWord(map, "zgfcyj.ftl", filePath, oldfilename, request);
		}
		jacobchange(filePath,oldfilename,filename);
	    
		return "/download/" + filename;
	}
	
	
	@Autowired
	private IXzcfCommonLaspService punishcommonlaspservice;
	@Autowired
	private XzcfXwtzService xzcfXwtzService;
	@Autowired
	private XzcfXwblService xzcfXwblService;
	@Autowired
	private XzcfCommonDcService xzcfcommondcservice;
	@Autowired
	private IPunishSimpleGzService punishsimplegzservice;
	@Autowired
	private XzcfCssbblService xzcfCssbblService;
	@Autowired
	private IXzcfCommonTzService xzcfcommontzservice;
	@Autowired
	private IXzcfCommonAjcpService xzcfcommonajcpservice;
	@Autowired
	private XzcfJttlService xzcfJttlService;
	@Autowired
	private IXzcfCfjdService punishsimplecfjdservice;
	@Autowired
	private XzcfFkspService xzcfFkspService;
	@Autowired
	private XzcfFkpzService xzcfFkpzService;
	@Autowired
	private XzcfCommonSxcgService xzcfcommonsxcgservice;
	@Autowired
	private XzcfCommonQzzxService xzcfcommonqzzxservice;
	@Autowired
	private XzcfCommonJaspService XzcfCommonJaspService;
	
	/**
	 * 行政处罚文书导出
	 */
	@RequestMapping(value = "xzcf")
	@ResponseBody
	public String getXzcfwsdc(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		String type = request.getParameter("type");//文书类型
		String flag = request.getParameter("flag");//id类型
		Map<String, Object> map = new HashMap<>();
		//设置导出的文件名
		String oldfilename = "lsws.doc";
		String filename = "";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		if(type.equals("lasp")){
			map = punishcommonlaspservice.getWsdcword(id);
			filename = map.get("number").toString() + ".doc";
			WordUtil.ireportWord(map, "lasprecord.ftl", filePath, oldfilename, request);
		}else if(type.equals("sdhz")){
			map = punishcommonlaspservice.getWsdcsdhzword(id);
			filename = map.get("number").toString() + ".doc";
			WordUtil.ireportWord(map, "xzcfsdhz.ftl", filePath, oldfilename, request);
		}else if(type.equals("xwtz")){
			map = xzcfXwtzService.getWsdcword(id,flag);
			filename = map.get("m0").toString() + ".doc";
			WordUtil.ireportWord(map, "xwtzs.ftl", filePath, oldfilename, request);
		}else if(type.equals("xwbl")){
			map = xzcfXwblService.getWsdcword(id);
			filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
			WordUtil.ireportWord(map, "xwbl.ftl", filePath, oldfilename, request);
		}else if(type.equals("dcbg")){
			map = xzcfcommondcservice.getWsdcword(id,flag);
			filename = map.get("bgbh").toString() + ".doc";
			WordUtil.ireportWord(map, "xzcfzjbg.ftl", filePath, oldfilename, request);
		}else if(type.equals("cfgz")){
			map = punishsimplegzservice.getWsdcword(id,flag);
			filename = map.get("number").toString() + ".doc";
			WordUtil.ireportWord(map, "gzsrecord.ftl", filePath, oldfilename, request);
		}else if(type.equals("cssb")){
			map = xzcfCssbblService.getWsdcword(id,flag);
			filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
			WordUtil.ireportWord(map, "xzcssbbl.ftl", filePath, oldfilename, request);
		}else if(type.equals("tzgz")){
			map = xzcfcommontzservice.getWsdcword(id,flag);
			filename = map.get("number").toString() + ".doc";
			WordUtil.ireportWord(map, "tzgzrecord.ftl", filePath, oldfilename, request);
		}else if(type.equals("ajcp")){
			map = xzcfcommonajcpservice.getWsdcword(id,flag);
			filename = map.get("number").toString() + ".doc";
			WordUtil.ireportWord(map, "ajcprecord.ftl", filePath, oldfilename, request);
		}else if(type.equals("jttl")){
			map = xzcfJttlService.getWsdcword(id,flag);
			filename = map.get("m1").toString() + ".doc";
			WordUtil.ireportWord(map, "xzjttl.ftl", filePath, oldfilename, request);
		}else if(type.equals("cfjd")){
			XZCF_CfjdInfoEntity jce ;
			if("la".equals(flag)){
				jce=punishsimplecfjdservice.findInfoByLaId(id);
			}else{
				jce= punishsimplecfjdservice.findInfoById(id);
			}
			map = punishsimplecfjdservice.getWsdcword(id,flag);
			filename = map.get("number").toString() + ".doc";
			if("1".equals(jce.getPercomflag())){
				WordUtil.ireportWord(map, "comcfjd.ftl", filePath, oldfilename, request);
			}else{
				WordUtil.ireportWord(map, "percfjd.ftl", filePath, oldfilename, request);
			}
		}else if(type.equals("fksp")){
			map = xzcfFkspService.getWsdcword(id,flag);
			filename = map.get("m1").toString() + ".doc";
			WordUtil.ireportWord(map, "xzfksp.ftl", filePath, oldfilename, request);
		}else if(type.equals("fkpz")){
			map = xzcfFkpzService.getWsdcword(id,flag);
			filename = map.get("m1").toString() + ".doc";
			WordUtil.ireportWord(map, "xzfkpz.ftl", filePath, oldfilename, request);
		}else if(type.equals("sxcg")){
			map = xzcfcommonsxcgservice.getWsdcword(id,flag);
			filename = map.get("number").toString() + ".doc";
			WordUtil.ireportWord(map, "xzcfsxcg.ftl", filePath, oldfilename, request);
		}else if(type.equals("qzzx")){
			map = xzcfcommonqzzxservice.getWsdcword(id,flag);
			filename = map.get("number").toString() + ".doc";
			WordUtil.ireportWord(map, "xzcfqzzx.ftl", filePath, oldfilename, request);
		}else if(type.equals("jasp")){
			map = XzcfCommonJaspService.getWsdcword(id,flag);
			filename = map.get("number").toString() + ".doc";
			WordUtil.ireportWord(map, "xzcfjasp.ftl", filePath, oldfilename, request);
		}
		jacobchange(filePath,oldfilename,filename);
	    
		return "/download/" + filename;
	}
	
	/**
	 * 将freemarker生成Word文档另存为新的Word文档
	 * @param filePath
	 * @param oldfilename
	 * @param filename
	 */
	public void jacobchange(String filePath,String oldfilename,String filename){
		ActiveXComponent _app = new ActiveXComponent("Word.Application");
	    _app.setProperty("Visible", Variant.VT_FALSE);
	    Dispatch documents = _app.getProperty("Documents").toDispatch();
	    // 打开FreeMarker生成的Word文档
	    Dispatch doc = Dispatch.call(documents, "Open", filePath + File.separator + oldfilename, Variant.VT_FALSE, Variant.VT_TRUE).toDispatch();
	    // 另存为新的Word文档
	    Dispatch.call(doc, "SaveAs", filePath + File.separator + filename, Variant.VT_FALSE, Variant.VT_TRUE);
	    Dispatch.call(doc, "Close", Variant.VT_FALSE);
	    _app.invoke("Quit", new Variant[] {});
	    ComThread.Release();
	}
}
