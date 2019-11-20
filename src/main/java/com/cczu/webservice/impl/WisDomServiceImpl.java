package com.cczu.webservice.impl;

import com.alibaba.fastjson.JSON;
import com.cczu.aqzf.model.dao.AqzfJcjlDao;
import com.cczu.aqzf.model.service.*;
import com.cczu.model.dao.*;
import com.cczu.model.entity.*;
import com.cczu.model.service.*;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.comm.utils.security.Digests;
import com.cczu.sys.comm.utils.security.Encodes;
import com.cczu.sys.system.dao.DepartmentDao;
import com.cczu.sys.system.dao.UserDao;
import com.cczu.sys.system.entity.AppVersionEntity;
import com.cczu.sys.system.entity.Dict;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.AppVersionService;
import com.cczu.sys.system.service.BarrioService;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.util.common.WordUtil;
import com.cczu.webservice.WisDomService;
import com.cczu.zxjkyj.service.IMonitorEdmDataService;
import com.cczu.zxjkyj.service.IMonitorGasDataService;
import com.cczu.zxjkyj.service.IMonitorGwgyDataService;
import com.cczu.zxjkyj.service.IMonitorTankDataService;
import com.fasterxml.jackson.databind.JavaType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebService(endpointInterface = "com.cczu.webservice.WisDomService")
public class WisDomServiceImpl implements WisDomService {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserDao userDao;
	@Resource
	private DepartmentDao departmentDao;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisCjxxService bisCjxxService;
	@Autowired
	private IBisWlxxService bisWlxxService;
	@Autowired
	private IBisCkxxService bisCkxxService;
	@Autowired
	private IBisCgxxService bisCgxxService;
	@Autowired
	private IBisTzsbxxService bisTzsbxxService;
	@Autowired
	private IBisYgxxService bisYgxxService;
	@Autowired
	private IBisAqpxxxService bisAqpxxxService;
	@Autowired
	private BisScsbService bisScsbService;
	@Autowired
	private BisGygcService bisGygcService;
	@Autowired
	private BisAqssService bisAqssService;
	@Autowired
	private IBisOccupharmService bisOccupharmService;
	@Autowired
	private IBisOccupharmreportService bisOccupharmreportService;
	@Autowired
	private IBisOccupIllexamService bisOccupIllexamService;
	@Autowired
	private BisYgtjService bisYgtjService;
	@Autowired
	private IBisGwgyService bisGwgyService;
	@Autowired
	private IBisHazardService bisHazardService;
	@Autowired
	private IErmTxlService ermTxlService;
	@Autowired
	private IMonitorTankDataService MonitorTankDataService;
	@Autowired
	private IMonitorGwgyDataService monitorGwgyDataService;
	@Autowired
	private IMonitorEdmDataService MonitorEdmDataService;
	@Autowired
	private IErmYjzzzzService ermYjzzzzService;
	@Autowired
	private IErmYjdwService ermYjdwService;
	@Autowired
	private IErmYjzbService ermYjzbService;
	@Autowired
	private IErmYjwzService ermYjwzService;
	@Autowired
	private IErmYjzjService ermYjzjService;
	@Autowired
	private IErmBncsService ermBncsService;
	@Autowired
	private IErmYjcbkService ermYjcbkService;
	@Autowired
	private IErmYjyaglService ermYjyaglService;
	@Autowired
	private IErmYjczjsService ermYjczjsService;
	@Autowired
	private IErmYjyyService ermYjyyService;
	@Autowired
	private ITdicAcawxhxpService  acawxhxpService;
	@Autowired
	private IAcaPoolFireService acaPoolFireService;
	@Autowired
	private IAcaJetFireService acaJetFireService;
	@Autowired
	private IAcaFireballService acaFireballService;
	@Autowired
	private IAcaPhysicalService acaPhysicalService;
	@Autowired
	private IAcaVceService acaVceService;
	@Autowired
	private IAcaGasphysicalService acaGasphysicalService;
	@Autowired
	private IAcaLeakageService acaLeakageService;
	@Autowired
	private IAcaInstantleakageService acaInstantleakageService;
	@Autowired
	private ISekbAqscfgService sekbAqscfgService;
	@Autowired
	private ISekbAqscjsbzService sekbAqscjsbzService;
	@Autowired
	private ISekbSgalService sekbSgalService;
	@Autowired
	private ISekbWhpaqxxService sekbWhpaqxxService;
	@Autowired
	private ISekbMsdsService sekbMsdsService;
	@Autowired
	private IIssueAqwjfbService issueAqwjfbService;
	@Autowired
	private IIssueWjcdjsService issueWjcdjsService;
	@Autowired
	private IIssueAqscdtService issueAqscdtService;
	@Autowired
	private AqjgAqbaService aqjgAqbaService;
	@Autowired
	private IAqjgCheckPlanService aqjgcheckplanservice;
	@Autowired
	private IAqjgCheckRecordService aqjgcheckrecordservice;
	@Autowired
	private AqjgDSFJcjlService aqjgDSFJcjlService;
	@Autowired
	private AqjgDSFManageService manageService;
	@Autowired
	private AQJGDSFFwxmbbService bbService;
	@Autowired
	private IAqjgCfjlService AqjgCfjlService;
	@Autowired
	private IAqjgPjService AqjgPjService;
	@Autowired
	private IDwZyspService dwDhzyService;
	@Autowired
	private AqjgZXGLSxjlService sxjlService;
	@Autowired
	private AqjgZXGLHmdService hmdService;
	@Autowired
	private IAqjgsjglService aqjgsjglservice ;
	@Autowired
	private SysHomeService syshomeservice;
	@Autowired
	private IBisQyjbxxDao bisQyjbxx;
	@Resource
	private IBisCjxxDao bisCjxxDao;
	@Resource
	private IBisWlxxDao bisWlxxDao;
	@Resource
	private IBisCkxxDao bisCkxxDao;
	@Resource
	private IBisCgxxDao bisCgxxDao;
	@Resource
	private IBisTzsbxxDao bisTzsbxxDao;
	@Resource
	private IBisAqpxxxDao bisAqpxxxDao;
	@Resource
	private IBisGwgyDao bisGwgyDao;
	@Resource
	private IBisOccupharmDao bisOccupharmDao;
	@Resource
	private IBisOccupharmreportDao bisOccupharmreportDao;
	@Resource
	private IBisOccupIllexamDao bisOccupIllexamDao;
	@Resource
	private IBisHazardDao bisHazardDao;
	@Resource
	private IBisHazardIdentityDao bisHazardIdentityDao;
	@Resource
	private IBisAqwlglDao bisAqwlglDao;
	@Resource
	private IIssueAqscdtDao aqscdtDao;
	@Resource
	private IIssueAqwjfbDao aqwjfbDao;
	@Resource
	private IIssueWjcdjsDao issueWjcdjsDao;
	@Autowired
	private IEadYjjcAccidentWordDao eadYjjcWordDao;
	@Autowired
	private ISekbAqscfgDao iSekbAqscfgDao;
	@Autowired
	private ISekbAqscjsbzDao iSekbAqscjsbzDao;
	@Autowired
	private ISekbMsdsDao iSekbMsdsDao;
	@Autowired
	private ISekbSgalDao iSekbSgalDao;
	@Autowired
	private ISekbWhpaqxxDao iSekbWhpaqxxDao;
	@Autowired
	private IBisAqwlglService bisAqwlglService;
	@Autowired
	private IMonitorGasDataService monitorGasDataService;
	@Autowired
	private IMonitorEdmDataService monitorEdmDataService;
	@Autowired
	private SekbCshfhjlService sekbCshfhjlService;
	@Autowired
	private IMonitorTankDataService monitorTankDataService;
	@Autowired
	private TsWarningDataService tsWarningDataService;
	@Autowired
	private IBisHazardIdentityService biszdwxysbxx;
	@Autowired
	private YhpcYhpcjlService yhpcYhpcjlService;
	@Autowired
	private WghglWgdService wghglWgdService;
	@Autowired
	private FxgkFxjgService fxgkfxjgservice;
	@Autowired 
	TsVideoService tsVideoService;
	@Autowired
	private BisYjxxService bisYjxxService;
	@Autowired
	private BisFcxxService bisFcxxService;
	@Autowired
	private BisSxkjService bisSxkjService;
	@Autowired
	private BisZybcService bisZybcService;
	@Autowired
	private BisCbsService bisCbsxxService;
	@Autowired
	private BisHxnyxxService bisHxnyxxService;
	@Autowired
	private WghglWgyxjjlzgService wghglWgyxjjlzgService;
	@Autowired
	private YhpcTjfxService yhpcTjfxService;
	@Autowired
	private YhpcXjdztService yhpcXjdztService;
	@Autowired
	private YhpcXjjlService yhpcXjjlService;
	@Autowired
	private FxgkFxxxService sxgkfxxxService;
	@Autowired
	private YhpcXjjdService yhpcXjjdService;
	@Resource
	private WebServiceContext webServiceContext;
	@Autowired
	private YhpcYhpcdService yhpcYhpcdService;
	@Autowired
	private AppVersionService appVersionService;
	
	/**
	 * 登录
	 * @param str1
	 * @param str2
	 * @param str3
	 * @return
	 */
	@Override
	public String Login(String str1, String str2, String str3) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = userService.getUser(str1);
			if (user == null) {
				map.put("status", "异常");
				map.put("result", "登录失败:用户名或密码不正确");
				return JsonMapper.getInstance().toJson(map);
			}
			byte[] salt = Encodes.decodeHex(user.getSalt());
			byte[] hashPassword = Digests.sha1(str2.getBytes(), salt, 1024);
			if (user.getPassword().equals(Encodes.encodeHex(hashPassword))) {
				List<Role> list = roleService.findRoleById(user.getId());
				if (list == null || list.size() == 0) {
					map.put("status", "异常");
					map.put("result", "登录失败:未分配用户角色");
					return JsonMapper.getInstance().toJson(map);
				}
				String role = list.get(0).getRoleCode();
				map.put("status", "正常");
				map.put("result", "登录成功");
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("userinfo", user);
				dataMap.put("role", role);
				//安监
				if(str3.equals("0")){
					if("ajcountyadmin".equals(role)){
						dataMap.put("department", "安监局管理员");
					}else if("ajcounty".equals(role)){
						dataMap.put("department", "安监局普通用户");
					}else if("ajtownadmin".equals(role)){
						dataMap.put("department", "镇安监管理员");
					}else if("ajtown".equals(role)){
						dataMap.put("department", "镇安监普通用户");
					}else{
						map.put("status", "异常");
						map.put("result", "登录失败:未分配用户角色");
						return JsonMapper.getInstance().toJson(map);
					}
				}
				//企业
				if(str3.equals("1")){
					if("companyadmin".equals(role)){
						dataMap.put("department", "企业管理员");
					}else if("company".equals(role)){
						dataMap.put("department", "企业普通用户");
					}else{
						map.put("status", "异常");
						map.put("result", "登录失败:未分配用户角色");
						return JsonMapper.getInstance().toJson(map);
					}
					Map<String,Object> gwmap = new HashMap<>();
					gwmap.put("usertype", user.getUsertype());
					gwmap.put("userid", user.getId());
					Map<String,Object> gwbm = userService.getGwAndBm(gwmap);
					dataMap.put("gwbm", gwbm);
				}
				map.put("data", JsonMapper.getInstance().toJson(dataMap));
				return JsonMapper.getInstance().toJson(map);
			} else {
				map.put("status", "异常");
				map.put("result", "登录失败:用户名或密码不正确");
				return JsonMapper.getInstance().toJson(map);
			}
		} catch (Exception e) {
			map.put("status", "异常");
			map.put("result", "登录失败:" + e.getMessage());
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 修改密码
	 * str1 json userId：用户id  oldPassword:旧密码  newPassword:新密码
	 */
	@Override
	public String updatePwd(String str1) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//参数解析
			Map<?, ?> mapParam = JSON.parseObject(str1, Map.class);  
			String userId = mapParam.get("userId").toString();
			String oldPassword = mapParam.get("oldPassword").toString();//旧密码
			String newPassword = mapParam.get("newPassword").toString();//新密码
			User user = userService.getUserSqlById(Integer.parseInt(userId));;
			byte[] saltByte =Encodes.decodeHex(user.getSalt()) ;
			if(userService.checkPassword(user, oldPassword)){
				byte[] hashNewPassword = Digests.sha1(newPassword.getBytes(),saltByte, 1024);
				user.setPassword(Encodes.encodeHex(hashNewPassword));
				userDao.save(user);
				map.put("status", "正常");
				map.put("result", "正常");
				map.put("data", "修改成功");
			}else{
				map.put("status", "正常");
				map.put("result", "正常");
				map.put("data", "旧密码不正确!");
			}
		} catch (Exception e) {
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data", "网络异常!" + e.getMessage());
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 版本控制
	 * str1（1:安监2：企业）
	 */
	public String Bbkz(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,类型为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			AppVersionEntity app = appVersionService.findVersionNoBytype(str1);
			map.put("data", JsonMapper.getInstance().toJson(app));
			 
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 安监首页(行政区域、监管分类)
	 */
	public String Syxx(String str1,String str2){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,行政区域为空");
		}else{
			tmap.put("xzqy", str1);
			if(!"".equals(str2)&&!"0".equals(str2)&&str2!=null){
				tmap.put("jglx",str2);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data", JsonMapper.getInstance().toJson(syshomeservice.getDateForApp(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 首页物料信息
	 * str1（页码）、str2（页数）、str3（行政区域）、str4（监管分类）、str5（企业名）、str6（物料名）
	 */
	@Override
	public String Sywlxx(String str1, String str2, String str3, String str4, String str5,String str6) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str3);
			if(!"".equals(str4)&&!"0".equals(str4)&&str4!=null){
				tmap.put("jglx",str4);
			}
			if(!"".equals(str5)){
				tmap.put("qyname",str5);
			}
			if(!"".equals(str6)){
				tmap.put("m1",str6);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(bisWlxxService.dataGrid2(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 首页高危工艺
	 * str1（页码）、str2（页数）、str3（行政区域）、str4（监管分类）、str5（企业名）、str6（高危工艺名）
	 */
	@Override
	public String Sygwgy(String str1, String str2, String str3, String str4, String str5,String str6) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str3);
			if(!"".equals(str4)&&!"0".equals(str4)&&str4!=null){
				tmap.put("jglx",str4);
			}
			if(!"".equals(str5)){
				tmap.put("qyname",str5);
			}
			if(!"".equals(str6)){
				tmap.put("m1",str6);
			}
			map.put("status", "成功");
			map.put("result", "正常");
			map.put("data",JsonMapper.getInstance().toJson(bisGwgyService.ajdataGrid(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 首页重大危险源
	 * str1（页码）、str2（页数）、str3（行政区域）、str4（监管分类）、str5（企业名）、str6（重大危险源等级1234）
	 */
	@Override
	public String Syzdwxy(String str1, String str2, String str3, String str4, String str5,String str6) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str3);
			if(!"".equals(str4)&&!"0".equals(str4)&&str4!=null){
				tmap.put("jglx",str4);
			}
			if(!"".equals(str5)){
				tmap.put("qyname",str5);
			}
			if(!"".equals(str6)){
				tmap.put("wxydj",str6);
			}
			map.put("status", "成功");
			map.put("result", "正常");
			map.put("data",JsonMapper.getInstance().toJson(bisHazardService.dataGrid(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 首页网格监管
	 * str1（页码）、str2（页数）、str3（行政区域）、str4（监管分类）、str5（企业名）
	 */
	@Override
	public String Sywgjg(String str1, String str2, String str3, String str4, String str5) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str3);
			if(!"".equals(str4)&&!"0".equals(str4)&&str4!=null){
				tmap.put("jglx",str4);
			}
			if(!"".equals(str5)){
				tmap.put("qyname",str5);
			}
			map.put("status", "成功");
			map.put("result", "正常");
			map.put("data",JsonMapper.getInstance().toJson(wghglWgdService.dataGrid1(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 首页网格未整改
	 * str1（页码）、str2（页数）、str3（行政区域）、str4（监管分类）、str5（企业名）、str6（隐患状态0未整改1企业整改完成2审核未通过3审核通过）
	 */
	@Override
	public String Sywgwzg(String str1, String str2, String str3, String str4, String str5,  String str6) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str3);
			if(!"".equals(str4)&&!"0".equals(str4)&&str4!=null){
				tmap.put("jglx",str4);
			}
			if(!"".equals(str5)){
				tmap.put("qyname",str5);
			}
			if(!"".equals(str6)){
				tmap.put("dangerstatus",str6);
			}
			map.put("status", "成功");
			map.put("result", "正常");
			map.put("data",JsonMapper.getInstance().toJson(wghglWgyxjjlzgService.dataGrid(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 首页安全培训
	 * str1（页码）、str2（页数）、str3（行政区域）、str4（监管分类）、str5（企业名）
	 */
	@Override
	public String Syaqpx(String str1, String str2, String str3, String str4, String str5) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str3);
			if(!"".equals(str4)&&!"0".equals(str4)&&str4!=null){
				tmap.put("jglx",str4);
			}
			if(!"".equals(str5)){
				tmap.put("qyname",str5);
			}
			tmap.put("time", "0");
			map.put("status", "成功");
			map.put("result", "正常");
			map.put("data",JsonMapper.getInstance().toJson(bisAqpxxxService.ajdataGrid(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 首页特种设备
	 * str1（页码）、str2（页数）、str3（行政区域）、str4（监管分类）、str5（企业名）、str6（特种设备名称）
	 */
	@Override
	public String Sytzsb(String str1, String str2, String str3, String str4, String str5,String str6) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str3);
			if(!"".equals(str4)&&!"0".equals(str4)&&str4!=null){
				tmap.put("jglx",str4);
			}
			if(!"".equals(str5)){
				tmap.put("qyname",str5);
			}
			if(!"".equals(str6)){
				tmap.put("m1",str6);
			}
			tmap.put("time1","0");
			map.put("status", "成功");
			map.put("result", "正常");
			map.put("data",JsonMapper.getInstance().toJson(bisTzsbxxService.ajdataGrid(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 首页职业病危害检测
	 * str1（页码）、str2（页数）、str3（行政区域）、str4（监管分类）、str5（企业名）、str6（检测机构）
	 */
	@Override
	public String Syzybwh(String str1, String str2, String str3, String str4, String str5,String str6) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str3);
			if(!"".equals(str4)&&!"0".equals(str4)&&str4!=null){
				tmap.put("jglx",str4);
			}
			if(!"".equals(str5)){
				tmap.put("qyname",str5);
			}
			if(!"".equals(str6)){
				tmap.put("m1",str6);
			}
			tmap.put("time","0");
			map.put("status", "成功");
			map.put("result", "正常");
			map.put("data",JsonMapper.getInstance().toJson(bisOccupharmreportService.dataGridAJ(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 首页风险管控
	 * str1（页码）、str2（页数）、str3（行政区域）、str4（监管分类）、str5（企业名）、str6（1红2橙3黄4蓝）
	 */
	@Override
	public String Syfxgk(String str1, String str2, String str3, String str4, String str5,String str6) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str3);
			if(!"".equals(str4)&&!"0".equals(str4)&&str4!=null){
				tmap.put("jglx",str4);
			}
			if(!"".equals(str5)){
				tmap.put("qyname",str5);
			}
			if(!"".equals(str6)){
				tmap.put("fxdj",str6);
			}
			map.put("status", "成功");
			map.put("result", "正常");
			map.put("data",JsonMapper.getInstance().toJson(fxgkfxjgservice.dateGrid(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 管控对策
	 * str1（风险监管id）
	 */
	@Override
	public String Fxgkdc(String str1) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,风险监管id为空");
		}else{
			map.put("status", "成功");
			map.put("result", "正常");
			tmap.put("qyid", str1);
			map.put("data",fxgkfxjgservice.getSgtypeByQyid(tmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 企业首页(企业id,userid)
	 */
	public String QySyxx(String str1,String str2){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,userid为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("qyid", str1);
			tmap.put("uid", str2);
			map.put("data", JsonMapper.getInstance().toJson(syshomeservice.getQyDate(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 首页隐患（企业）
	 * str1（页码）str2（每页个数）str3（巡检点关键字）str4（企业id）str5（隐患处理情况0：未整改1：企业整改完成2：审核未通过3：审核通过）str6（1：隐患自查2：网格隐患）
	 */
	public String Syqyyhjl(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("qyid", str4);
			if(!"".equals(str3)){
				tmap.put("jcdname", str3);
			}
			if(!"".equals(str5)){
				tmap.put("zgzt", str5);
			}
			if(!"".equals(str6)){
				tmap.put("yhtype", str6);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(yhpcYhpcjlService.dataGridForSyApp(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/***********************************************一企一档********************************************************/
	
	/**
	 * 获取企业缩略图
	 * 
	 * @param str1 pageNo（页码）
	 * @param str2 pageSize（）
	 * @param str3  关键字
	 * @param str4  行政区域
	 * @param str5  省
	 * @param str6  市
	 * @param str7  区
	 * @param str8  监管分类
	 * @return
	 */
	@Override
	public String QyList(String str1, String str2, String str3,String str4,String str5,String str6,String str7,String str8) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}
		else{
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!str3.equals("")){
				tmap.put("qyname", str3);
			}
//			if(!str5.equals("")){
//				tmap.put("sheng", str5);
//			}
//			if(!str6.equals("")){
//				tmap.put("shi", str6);
//			}
//			if(!str7.equals("")){
//				tmap.put("xian", str7);
//			}
			tmap.put("xzqy",str4);
			if(!"".equals(str8)&&!"0".equals(str8)&&str8!=null){
				tmap.put("jglx",str8);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",
					JsonMapper.getInstance().toJson(
							bisQyjbxxService.dataGridForApp(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 企业list 
	 * idjson  {"id":11,"text":"企业名称"}
	 */
	public String QyListidjson(String str1,String str2,String str3){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		map.put("status", "正常");
		map.put("result", "成功");
		tmap.put("xzqy", str1);
		if(!"".equals(str2)&&!"0".equals(str2)&&str2!=null){
			tmap.put("jglx",str2);
		}
		if(!"".equals(str3)){
			tmap.put("qyname", str3);
		}
		map.put("data", bisQyjbxxService.getQyIdjson(tmap));
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 获取企业信息
	 * @param str1
	 *            企业id
	 * @param str2
	 *            0:企业基本信息1:车间信息;2:物料信息;3:仓库管理;4:储罐信息;5:特种设备信息;6:员工信息;7:安全培训信息;8:生产设备信息;
	 *            9:公用工程信息;10:安全设施;11:职业病危害因素;12:检测报告管理;13:职业病体检;14:员工体检信息;15:危险工艺;16:重大危险源信息;
	 *            17:冶金信息;18:粉尘信息;19:受限空间;20:作业班次;21:承包商信息;22:化学能源信息;23:重点监管危化品
	 * @param str3 pageNo（页码）
	 * @param str4 pageSize（每页个数）
	 * str5（关键字）str6（行政区域 ）str7（）str8（）str9（监管类型）
	 * @return
	 */
	@Override
	public String QyInfo(String str1, String str2, String str3,String str4,String str5,String str6,String str7,String str8,String str9) {
		System.out.println(str7);
		System.out.println(str8);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapresult = new HashMap<String, Object>();
		if(str2.equals("")){
			mapresult.put("status", "异常");
			mapresult.put("result", "type未获得");
			return JsonMapper.getInstance().toJson(mapresult);
		}
		if(!str3.equals("")){
			map.put("pageNo", str3);
		}
		if(!str4.equals("")){
			map.put("pageSize", str4);
		}
		if(!"".equals(str1)){
			map.put("qyid", str1);
		}
		if(!"".equals(str6)){
			map.put("xzqy", str6);
			if(!"".equals(str9)&&!"0".equals(str9)&&str9!=null){
				map.put("jglx",str9);
			}
		}
		mapresult.put("status", "正常");
		mapresult.put("result", "正常");
		if("".equals(str8)){
			str8 = "";
		}
		if ("0".equals(str2)) {
			// 企业基本信息
			mapresult.put("data", JsonMapper.getInstance().toJson(bisQyjbxx.findByQyIDForApp(map)));
		} else if ("1".equals(str2)) {
			// 车间信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("cjname", str8);
				}else if("2".equals(str7)){
					map.put("hzwxx", str8);
				}else if("3".equals(str7)){
					map.put("jzjg", str8);
				}else if("4".equals(str7)){
					map.put("cs", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisCjxxService.ajdataGrid(map)));
		} else if ("2".equals(str2)) {
			// 物料信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}else if("2".equals(str7)){
					map.put("leibie", str8);
				}else if("3".equals(str7)){
					map.put("ccfs", str8);
				}else if("4".equals(str7)){
					map.put("zdjg", str8);
				}else if("5".equals(str7)){
					map.put("jd", str8);
				}else if("6".equals(str7)){
					map.put("yzd", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisWlxxService.dataGridAJ(map)));
		} else if ("3".equals(str2)) {
			// 仓库信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}else if("2".equals(str7)){
					map.put("m4", str8);
				}else if("3".equals(str7)){
					map.put("m5", str8);
				}else if("4".equals(str7)){
					map.put("m6", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisCkxxService.dataGridAJ(map)));
		} else if ("4".equals(str2)) {
			// 储罐信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}else if("2".equals(str7)){
					map.put("m6", str8);
				}else if("3".equals(str7)){
					map.put("m2", str8);
				}else if("4".equals(str7)){
					map.put("m3", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisCgxxService.dataGridAJ(map)));
		} else if ("5".equals(str2)) {
			// 特种设备信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}else if("2".equals(str7)){
					map.put("m3", str8);
				}else if("3".equals(str7)){
					map.put("time1", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisTzsbxxService.ajdataGrid(map)));
		} else if ("6".equals(str2)) {
			// 员工信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("ygname", str8);
				}else if("2".equals(str7)){
					map.put("gw", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisYgxxService.dataGridAJ(map)));
		} else if ("7".equals(str2)) {
			// 安全培训信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m2", str8);
				}else if("2".equals(str7)){
					map.put("m6", str8);
				}else if("3".equals(str7)){
					map.put("time", str8);
				}else if("4".equals(str7)){
					map.put("m1", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisAqpxxxService.ajdataGrid(map)));
		} else if ("8".equals(str2)) {
			// 生产设备信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m3", str8);
				}else if("2".equals(str7)){
					map.put("m8", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisScsbService.dataGrid2(map)));
		} else if ("9".equals(str2)) {
			// 公用工程信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}else if("2".equals(str7)){
					map.put("m3", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisGygcService.dataGrid2(map)));
		} else if ("10".equals(str2)) {
			// 安全设施
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}else if("2".equals(str7)){
					map.put("m9", str8);
				}else if("3".equals(str7)){
					map.put("m7", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisAqssService.dataGrid2(map)));
		} else if ("11".equals(str2)) {
			// 职业病危害因素
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m5", str8);
				}else if("2".equals(str7)){
					map.put("m1", str8);
				}else if("3".equals(str7)){
					map.put("m2", str8);
				}else if("4".equals(str7)){
					map.put("m3", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisOccupharmService.ajdataGrid(map)));
		} else if ("12".equals(str2)) {
			// 检测报告管理
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}else if("2".equals(str7)){
					map.put("time", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisOccupharmreportService.dataGridAJ(map)));
		} else if ("13".equals(str2)) {
			// 职业病体检
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m2", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisOccupIllexamService.ajdataGrid(map)));
		} else if ("14".equals(str2)) {
			// 员工体检信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}else if("2".equals(str7)){
					map.put("ygname", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisYgtjService.dataGrid2(map)));
		} else if ("15".equals(str2)) {
			// 危险工艺
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisGwgyService.ajdataGrid(map)));
		} else if ("16".equals(str2)) {
			// 重大危险源信息
			if(!"".equals(str1)){
				map.put("qyid", "");
				map.put("qyid2", str1);
			}
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					if(!"".equals(str8)){
						map.put("wxydj", str8);
					}
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisHazardService.dataGrid(map)));
		} else if ("17".equals(str2)) {
			// 冶金信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}else if("2".equals(str7)){
					map.put("m3", str8);
				}else if("3".equals(str7)){
					map.put("m4", str8);
				}else if("4".equals(str7)){
					map.put("m7", str8);
				}else if("5".equals(str7)){
					map.put("m8", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisYjxxService.dataGrid2(map)));
		} else if ("18".equals(str2)) {
			// 粉尘信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisFcxxService.dataGrid2(map)));
		} else if ("19".equals(str2)) {
			// 受限空间
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m5", str8);
				}else if("2".equals(str7)){
					map.put("m7", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisSxkjService.dataGrid2(map)));
		} else if ("20".equals(str2)) {
			// 作业班次
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisZybcService.dataGrid2(map)));
		} else if ("21".equals(str2)) {
			// 承包商信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisCbsxxService.dataGrid2(map)));
		} else if ("22".equals(str2)) {
			// 化学能源信息
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}else if("2".equals(str7)){
					map.put("m5", str8);
				}else if("3".equals(str7)){
					map.put("m6", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisHxnyxxService.dataGrid2(map)));
		} else if ("23".equals(str2)) {
			// 重点监管危化品
			if(!"".equals(str5)){
				map.put("qyname", str5);
			}
			if(!"".equals(str7)){
				if("1".equals(str7)){
					map.put("m1", str8);
				}else if("2".equals(str7)){
					map.put("leibie", str8);
				}else if("3".equals(str7)){
					map.put("ccfs", str8);
				}else if("5".equals(str7)){
					map.put("jd", str8);
				}else if("6".equals(str7)){
					map.put("yzd", str8);
				}
			}
			mapresult.put("data", JsonMapper.getInstance().toJson(bisWlxxService.dataGrid2(map)));
		} 
		return JsonMapper.getInstance().toJson(mapresult);
	}
	
	/**
	 * 根据高危工艺名称获取高危工艺数据
	 * str1 高危工艺名称
	 */
	@Override
	public String GwgySjHq(String str1) {
		Map<String, Object> map = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "高危工艺名称未获取");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data",JsonMapper.getInstance().toJson(bisGwgyService.findByM0(str1)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 查看重大危险源信息 
	 * str3 企业id 
	 */
	@Override
	public String ZdwxyxxCk(String str1, String str2, String str3) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
			
		}else if(str3.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");
			
			BIS_HazardEntity bhy = bisHazardService.findqyid(Long.parseLong(str3));
			if (bhy != null) {
				tmap.put("hdyid", bhy.getID());
				tmap.put("pageNo", str1);
				tmap.put("pageSize", str2);
				map.put("data",JsonMapper.getInstance().toJson(biszdwxysbxx.dataGridApp(tmap)));
			} else {
				map.put("data",null);
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 企业重大危险源信息 
	 * str1 企业id 
	 */
	@Override
	public String Qyzdwxy(String str1) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,企业id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");
			BIS_HazardEntity bh = bisHazardService.findqyid(Long.parseLong(str1));
			if (bh != null) {
				List<BIS_HazardIdentityEntity> list = biszdwxysbxx.findListHdid(bh.getID());
				if (list.size() > 0) {
					double Qnum = 0, a = 1;// a校正系数
					for (BIS_HazardIdentityEntity bis : list) {
						if (StringUtils.isNotEmpty(bis.getM5())) {
							Qnum = Qnum + Double.valueOf(bis.getM5());
						}
					}
					if (bh.getM3() == null) {
					} else if (bh.getM3() <= 0)
						a = 0.5;
					else if (0 < bh.getM3() && bh.getM3() < 30)
						a = 1;
					else if (30 <= bh.getM3() && bh.getM3() < 50)
						a = 1.2;
					else if (50 <= bh.getM3() && bh.getM3() < 100)
						a = 1.5;
					else
						a = 2;
					bh.setM9(String.valueOf(Qnum * a));
					bisHazardService.updateInfo(bh);
				}
			}
			map.put("data",bh);
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/*************************************************在线监控预警*********************************************************/
	
	/**
	 * 储罐企业list
	 * str1（页码）、str2（每页个数）、str3（关键字，可为空）、str4（行政区域）、str5（监管分类）
	 */
	public String CgjkQylist(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",MonitorTankDataService.getqylist(tmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 根据企业id查看储罐最新监控信息
	 * @param str1 企业id
	 */
	@Override
	public String CgjkInfo(String str1) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data",
					JsonMapper.getInstance().toJson(
							MonitorTankDataService.dataGridByQyid(Long.parseLong(str1))));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 根据物质名查询物资具体信息
	 * @param str1
	 * @return
	 */
	@Override
	public String WzInfoCx(String str1) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,物质为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");	
			List<TMESK_MsdsEntity> list=sekbMsdsService.findByWzName(str1);
			if(list!=null&&list.size()>0)
				map.put("data",JsonMapper.getInstance().toJson(list.get(0)));
			else{
				map.put("data",null);
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 高危工艺企业list
	 */
	public String GwgyQylist(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",monitorGwgyDataService.getqylist(tmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 根据企业id查看高危工艺最新监控信息
	 * @param str1 企业id
	 */
	@Override
	public String GwgyInfo(String str1) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data",
					JsonMapper.getInstance().toJson(
							monitorGwgyDataService.findInfoById(Long.parseLong(str1))));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 根据高危物质名查询高危物质具体信息
	 */
	@Override
	public String GwgyInfoCk(String str1) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,高危工艺名字为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");	
			map.put("data",JsonMapper.getInstance().toJson(bisGwgyService.findByM0(str1)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 气体浓度企业list
	 */
	public String QtndQylist(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",monitorGasDataService.getqylist(tmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 根据企业id查看气体浓度最新监控信息
	 * @param str1 企业id
	 */
	@Override
	public String QtndInfo(String str1) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data",
					JsonMapper.getInstance().toJson(
							monitorGasDataService.findInfoById(Long.parseLong(str1))));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 二道门企业
	 * @param str1 页码
	 * @param str2 每页个数
	 * @param str3 关键字
	 * @param str4 行政区域
	 * str5监管分类
	 */
	@Override
	public String EdmQylist(String str1, String str2, String str3, String str4,String str5) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",monitorEdmDataService.getqylist(tmap));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 根据企业id查看二道门最新监控信息
	 * @param str1 企业id
	 */
	@Override
	public String EdmInfo(String str1) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data",
					JsonMapper.getInstance().toJson(
							monitorEdmDataService.findInfoById(Long.parseLong(str1))));
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 视频监控企业list
	 * @param str1 页码
	 * @param str2 每页个数
	 * @param str3 关键字
	 * @param str4 行政区域
	 * str5 监管分类
	 */
	@Override
	public String SpjkQylist(String str1, String str2, String str3, String str4,String str5) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",tsVideoService.getqylistapp(tmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 根据企业id查看视频监控信息
	 * @param str1 企业id
	 */
	@Override
	public String SpjkInfo(String str1) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data",tsVideoService.findByQyid(Long.parseLong(str1)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 报警信息（安监）
	 * str1（页码）、str2（页数）、str3（关键字）、str4（处理类型 0.未处理 1.已处理）、str5（监控类型 1.储罐 2.气体 3.高危工艺）、str6（行政区域）、str7（监管分类）
	 */
	public String Bjxx(String str1,String str2,String str3,String str4,String str5,String str6,String str7){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str5.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,type为空");
		}else if(str6.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			tmap.put("xzqy",str6);
			if(!"".equals(str7)&&!"0".equals(str7)&&str7!=null){
				tmap.put("jglx",str7);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			if(!"".equals(str4)){
				if("0".equals(str4)||"1".equals(str4)){
					tmap.put("status", str4);
				}else{
					return "处理类型有误";
				}
			}
			if("1".equals(str5)||"2".equals(str5)||"3".equals(str5)){
				tmap.put("type", str5);
			}else{
				return "类别有误";
			}
			map.put("data",JsonMapper.getInstance().toJson(tsWarningDataService.dataGridApp(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 报警信息（企业）
	 * str1（页码）、str2（页数）、str3（处理类型 0.未处理 1.已处理）、str4（监控类型 1.储罐 2.气体 3.高危工艺）、str5（企业id）
	 */
	public String Qybjxx(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,type为空");
		}else if(str5.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			tmap.put("qyid",str5);
			if(!"".equals(str3)){
				if("0".equals(str3)||"1".equals(str3)){
					tmap.put("status", str3);
				}else{
					return "处理类型有误";
				}
			}
			if("1".equals(str4)||"2".equals(str4)||"3".equals(str4)){
				tmap.put("type", str4);
			}else{
				return "类别有误";
			}
			map.put("data",JsonMapper.getInstance().toJson(tsWarningDataService.dataGridApp(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 物料实时大数据
	 * str1 行政区域、企业id str2 1.物料类别实时 2.物料名称实时 str3类别 0安监 1企业 str4 监管分类
	 */
	public String Wlsssj(String str1,String str2,String str3,String str4){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,行政区域或企业id为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,类别为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			if("0".equals(str3)){
			    tmap.put("xzqy",str1);
			    if(!"".equals(str4)&&!"0".equals(str4)&&str4!=null){
					tmap.put("jglx",str4);
				}
			}else if("1".equals(str3)){
				tmap.put("qyid",str1);
			}
			if("1".equals(str2)){
				tmap.put("type","pie");
			}else if("2".equals(str2)){
				tmap.put("type","bar");
			}else{
				return "类型没输入";
			}
			map.put("data",monitorTankDataService.getMatSsDate(tmap));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 物料波动大数据
	 * str1 行政区域、企业id  str2 关键字 str3 开始日期 str4 结束日期 str5类别 0安监 1企业str6监管分类
	 */
	public String Wlbdsj(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,行政区域或企业id为空");
		}else if(str3.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,开始日期为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,结束日期为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			if("0".equals(str5)){
			    tmap.put("xzqy",str1);
			    if(!"".equals(str6)&&!"0".equals(str6)&&str6!=null){
					tmap.put("jglx",str6);
				}
			}else if("1".equals(str5)){
				tmap.put("qyid",str1);
			}
			tmap.put("datestart", str3);
			tmap.put("dateend", str4);
			tmap.put("qyname", str2);
			map.put("data",monitorTankDataService.getMatLsbdDate(tmap));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 吞吐量实时数据
	 * str1 行政区域、企业id str2 关键字 str3 开始时间 str4 结束时间 str5 1.物料 2.时间 str6 1.当选择物料时是类别 当选择时间时是每日 2.当选择物料时是名称 当选择时间时是每月 str7类别 0安监 1企业str8监管分类
	 */
	public String Ttlsj(String str1,String str2,String str3,String str4,String str5,String str6,String str7,String str8){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,行政区域或企业id为空");
		}else if(str5.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,大类别为空");
		}else if(str6.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,小类别为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			if("0".equals(str7)){
			    tmap.put("xzqy",str1);
			    if(!"".equals(str8)&&!"0".equals(str8)&&str8!=null){
					tmap.put("jglx",str8);
				}
			}else if("1".equals(str7)){
				tmap.put("qyid",str1);
			}
			tmap.put("qyname", str2);
			tmap.put("datestart", str3);
			tmap.put("dateend", str4);
			if("1".equals(str5)){
				//物料
				if("1".equals(str6)){
					//物料类别
					tmap.put("type","type");
				}else if("2".equals(str6)){
				    //物料名称
					tmap.put("type","name");
				}else{
					return "小类别错误";
				}
				map.put("data",JsonMapper.getInstance().toJson(monitorTankDataService.getMatTtlDate(tmap)));
			}else if("2".equals(str5)){
				//时间
				if("1".equals(str6)){
					//每日
					tmap.put("type","day");
				}else if("2".equals(str6)){
				    //每月
					tmap.put("type","month");
				}else{
					return "小类别错误";
				}
				map.put("data",monitorTankDataService.getTtlDateByTime(tmap));
			}else{
				return "大类别错误";
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 动态风险云图
	 * str1 行政区域str2监管分类
	 */
	public String Dtfxyt(String str1,String str2){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("xzqy",str1);
			if(!"".equals(str2)&&!"0".equals(str2)&&str2!=null){
				tmap.put("jglx",str2);
			}
			//查询所有企业
			List<BIS_EnterpriseEntity> list= bisQyjbxxService.dataListE(tmap);
			//返回的list
			List<Map<String,Object>> relist =new ArrayList<Map<String,Object>>();
			for (BIS_EnterpriseEntity bis :list){
				Map<String,Object> map2= new HashMap<String, Object>();
				map2.put("lng", bis.getM16());
				map2.put("lat", bis.getM17());
				map2.put("count", (int)(Math.random()*100+1));
				relist.add(map2);
			}
			map.put("data",JsonMapper.getInstance().toJson(relist));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/*************************************************事故应急********************************************************/
	
	/**
	 * 安监端应急资源管理
	 * @param str1 页码
	 * @param str2 每页个数
	 * @param str3 关键字
	 * @param str4 行政区域
	 * @param str5
	 *            1:应急通讯录2:应急组织职责3:应急队伍4:应急装备5:应急物资6:应急专家7:避难场所
	 *            8:应急储备罐9:应急预案管理10:应急处置技术11:应急医院
	 * @param str6 1:安监 2:企业
	 * @param str7 企业ID
	 * str8 监管分类
	 */
	@Override
	public String Yjzy(String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapresult = new HashMap<String, Object>();
		if(str1.equals("")){
			mapresult.put("status", "异常");
			mapresult.put("result", "异常,page为空");
		}else if(str2.equals("")){
			mapresult.put("status", "异常");
			mapresult.put("result", "异常,pageSize为空");
		}else if(str4.equals("")){
			mapresult.put("status", "异常");
			mapresult.put("result", "异常,xzqy为空");
		}
		else{
			mapresult.put("status", "正常");
			mapresult.put("result", "成功");
			map.put("pageNo",str1);
			map.put("pageSize",str2);
			if("1".equals(str6)){
				map.put("xzqy", str4);
		    	if(!"".equals(str8)&&!"0".equals(str8)&&str8!=null){
		    		map.put("jglx",str8);
				}
			}
			if ("1".equals(str5)) {
				//应急通讯录
			    map.put("usertype","0");
			    map.put("cxtype", str6);
			    if(!"".equals(str3)){
			    	 map.put("xm", str3);
			    }
			    if("2".equals(str6)){
			    	if(!"".equals(str7)){
			    		map.put("qyid", str7);
			    	}	
			    }
			    mapresult.put("data", JsonMapper.getInstance().toJson(ermTxlService.dataGrid(map)));
			}else if ("2".equals(str5)) {
				// 应急组织职责
				map.put("usertype","0");
				map.put("cxtype", str6);
				if(!"".equals(str3)){
					map.put("zznm", str3);
			    }
				if("2".equals(str6)){
					if(!"".equals(str7)){
			    		map.put("qyid", str7);
			    	}	
				}
			    mapresult.put("data", JsonMapper.getInstance().toJson(ermYjzzzzService.dataGrid(map)));	    
			}else if ("3".equals(str5)) {
				// 应急队伍
				if(!"".equals(str3)){
					map.put("dwname", str3);
			    }
				mapresult.put("data", JsonMapper.getInstance().toJson(ermYjdwService.dataGrid(map)));
			}else if ("4".equals(str5)) {
				// 应急装备
				map.put("usertype","0");
				map.put("cxtype", str6);
				if(!"".equals(str3)){
					map.put("zbname", str3);
				}
				if("2".equals(str6)){
					if(!"".equals(str7)){
			    		map.put("qyid", str7);
			    	}	
				}
				mapresult.put("data", JsonMapper.getInstance().toJson(ermYjzbService.dataGrid(map)));
			}else if ("5".equals(str5)) {
				// 应急物资
				map.put("usertype","0");
				map.put("cxtype", str6);
				if(!"".equals(str3)){
					map.put("wzname", str3);
				}
				if("2".equals(str6)){
					if(!"".equals(str7)){
			    		map.put("qyid", str7);
			    	}	
				}
				mapresult.put("data", JsonMapper.getInstance().toJson(ermYjwzService.dataGrid(map)));
			}else if ("6".equals(str5)) {
				// 应急专家
				map.put("usertype","0");
				map.put("cxtype", str6);
				if(!"".equals(str3)){
					map.put("zjname", str3);
				}
				if("2".equals(str6)){
					if(!"".equals(str7)){
			    		map.put("qyid", str7);
			    	}	
				}
				mapresult.put("data", JsonMapper.getInstance().toJson(ermYjzjService.dataGrid(map)));
			}else if ("7".equals(str5)) {
				// 避难场所
				if(!"".equals(str3)){
					map.put("csname", str3);
				}
				mapresult.put("data", JsonMapper.getInstance().toJson(ermBncsService.dataGrid(map)));
			}else if ("8".equals(str5)) {
				// 应急储备罐
				if(!"".equals(str3)){
					map.put("cbkname", str3);
				}
				mapresult.put("data", JsonMapper.getInstance().toJson(ermYjcbkService.dataGrid(map)));
			}else if ("9".equals(str5)) {
				// 应急预案管理
				map.put("usertype","0");
				map.put("cxtype", str6);
				if(!"".equals(str3)){
					map.put("number", str3);
				}
				if("2".equals(str6)){
					if(!"".equals(str7)){
			    		map.put("qyid", str7);
			    	}	
				}
				mapresult.put("data", JsonMapper.getInstance().toJson(ermYjyaglService.dataGrid(map)));
			}else if ("10".equals(str5)) {
				// 应急处置技术
				if(!"".equals(str3)){
					map.put("zwname", str3);
				}
				mapresult.put("data", JsonMapper.getInstance().toJson(ermYjczjsService.dataGrid(map)));
			}else if ("11".equals(str5)) {
				// 应急医院
				if(!"".equals(str3)){
					map.put("yyname", str3);
				}
				mapresult.put("data", JsonMapper.getInstance().toJson(ermYjyyService.dataGrid(map)));
			}    
		}
		return JsonMapper.getInstance().toJson(mapresult);
	}
	
	/**
	 * 应急企业list
	 * @param str1 页码
	 * @param str2 每页个数
	 * @param str3 关键字
	 * @param str4 行政区域
	 * str5 监管分类
	 * str6 1.应急通讯录2.应急组织职责3.应急装备4.应急物资5.应急专家6.应急预案管理
	 */
	@Override
	public String YjzyQyList(String str1, String str2, String str3, String str4,String str5,String str6) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			tmap.put("cxtype","2");
			map.put("status", "正常");
			map.put("result", "成功");
			if ("1".equals(str6)) {
				map.put("data",ermTxlService.getqylistapp(tmap));	
			}else if("2".equals(str6)){
				map.put("data",ermYjzzzzService.getqylistapp(tmap));	
			}else if("3".equals(str6)){
				map.put("data",ermYjzbService.getqylistapp(tmap));	
			}else if("4".equals(str6)){
				map.put("data",ermYjwzService.getqylistapp(tmap));	
			}else if("5".equals(str6)){
				map.put("data",ermYjzjService.getqylistapp(tmap));	
			}else if("6".equals(str6)){
				map.put("data",ermYjyaglService.getqylistapp(tmap));	
			}
			
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 事故后果物质获得
	 * @param str1 
	 */
	public String Sghgwz(String str1) {
	    return acawxhxpService.findByName(str1);
	}
	
	/**
	 * 事故后果物质信息
	 * @param str1  物资id
	 */
	public String SghgwzInfo(String str1) {
		if(str1.equals("")){
		    return "id error";
		}
	    return acawxhxpService.acawxhxpJsonByTid(Long.parseLong(str1));
	}
	
	/**
	 * 池火灾
	 */
	public String Chzjs(String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8) throws Exception{
		ACA_PoolFireEntity aca = new ACA_PoolFireEntity();
		aca.setM1(str1);
		aca.setM2(str2);
		aca.setM3(str3);
		aca.setM4(str4);
		aca.setM5(str5);
		aca.setM6(str6);
		aca.setM7(str7);
		aca.setM8(str8);
		Map<String,Object> map = new HashMap<>();
		List<TMESK_MsdsEntity> list = sekbMsdsService.findByWzName(str1);
		map.put("yjcl", JsonMapper.getInstance().toJson(list.get(0)));
		List<TMESK_ProtectionDistanceEntity> list2=sekbCshfhjlService.findByWzName(str1);
		map.put("ssjl", JsonMapper.getInstance().toJson(list2.get(0)));
		map.put("jljs", acaPoolFireService.countSave(aca));
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 喷射火
	 */
	public String Pshjs(String str1, String str2, String str3, String str4, String str5, String str6) throws Exception{
		ACA_JetFireEntity aca = new ACA_JetFireEntity();
		aca.setM1(str1);
		aca.setM2(str2);
		aca.setM3(str3);
		aca.setM4(str4);
		aca.setM5(str5);
		aca.setM6(str6);
		Map<String,Object> map = new HashMap<>();
		List<TMESK_MsdsEntity> list = sekbMsdsService.findByWzName(str1);
		map.put("yjcl", JsonMapper.getInstance().toJson(list.get(0)));
		List<TMESK_ProtectionDistanceEntity> list2=sekbCshfhjlService.findByWzName(str1);
		map.put("ssjl", JsonMapper.getInstance().toJson(list2.get(0)));
		map.put("jljs", acaJetFireService.countSave(aca));
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 火球
	 */
	public String Hqjs(String str1, String str2, String str3) throws Exception{
		ACA_FireballEntity aca = new ACA_FireballEntity();
		aca.setM1(str1);
		aca.setM2(str2);
		aca.setM3(str3);
		Map<String,Object> map = new HashMap<>();
		List<TMESK_MsdsEntity> list = sekbMsdsService.findByWzName(str1);
		map.put("yjcl", JsonMapper.getInstance().toJson(list.get(0)));
		List<TMESK_ProtectionDistanceEntity> list2=sekbCshfhjlService.findByWzName(str1);
		map.put("ssjl", JsonMapper.getInstance().toJson(list2.get(0)));
		map.put("jljs", acaFireballService.countSave(aca));
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 物理
	 */
	public String Wljs(String str1, String str2) throws Exception{
		ACA_PhysicalEntity aca = new ACA_PhysicalEntity();
		aca.setM1(str1);
		aca.setM2(str2);
		Map<String,Object> map = new HashMap<>();
		map.put("jljs", acaPhysicalService.countSave(aca));
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 化学爆炸
	 */
	public String Hxjs(String str1, String str2,String str3) throws Exception{
		ACA_VceEntity aca = new ACA_VceEntity();
		aca.setM1(str1);
		aca.setM2(str2);
		aca.setM3(str3);
		Map<String,Object> map = new HashMap<>();
		List<TMESK_MsdsEntity> list = sekbMsdsService.findByWzName(str1);
		map.put("yjcl", JsonMapper.getInstance().toJson(list.get(0)));
		List<TMESK_ProtectionDistanceEntity> list2=sekbCshfhjlService.findByWzName(str1);
		map.put("ssjl", JsonMapper.getInstance().toJson(list2.get(0)));
		map.put("jljs", acaVceService.countSave(aca));
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 压缩气体爆炸
	 */
	public String Ysqtjs(String str1, String str2,String str3,String str4) throws Exception{
		ACA_GasphysicalEntity aca = new ACA_GasphysicalEntity();
		aca.setM1(str1);
		aca.setM2(str2);
		aca.setM3(str3);
		aca.setM4(str4);
		Map<String,Object> map = new HashMap<>();
		List<TMESK_MsdsEntity> list = sekbMsdsService.findByWzName(str1);
		map.put("yjcl", JsonMapper.getInstance().toJson(list.get(0)));
		List<TMESK_ProtectionDistanceEntity> list2=sekbCshfhjlService.findByWzName(str1);
		map.put("ssjl", JsonMapper.getInstance().toJson(list2.get(0)));
		map.put("jljs", acaGasphysicalService.countSave(aca));
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 持续泄漏
	 */
	public String Cxxljs(String str1, String str2,String str3,String str4,String str5, String str6,String str7,String str8,String str9, String str10,String str11,String str12,String str13,String str14) throws Exception{
		ACA_LeakageEntity aca = new ACA_LeakageEntity();
		aca.setM1(str1);
		aca.setM2(str2);
		aca.setM3(str3);
		aca.setM4(str4);
		aca.setM5(str5);
		aca.setM6(str6);
		aca.setM7(str7);
		aca.setM8(str8);
		aca.setM9(str9);
		aca.setM10(str10);
		aca.setM11(str11);
		aca.setM12(str12);
		aca.setM13(str13);
		aca.setM14(str14);
		Map<String,Object> map = new HashMap<>();
		List<TMESK_MsdsEntity> list = sekbMsdsService.findByWzName(str1);
		map.put("yjcl", JsonMapper.getInstance().toJson(list.get(0)));
		List<TMESK_ProtectionDistanceEntity> list2=sekbCshfhjlService.findByWzName(str1);
		map.put("ssjl", JsonMapper.getInstance().toJson(list2.get(0)));
		map.put("jljs", acaLeakageService.countSave(aca));
		return JsonMapper.getInstance().toJson(map);
	}	
	
	/**
	 * 瞬时泄漏
	 */
	public String Ssxljs(String str1, String str2,String str3,String str4,String str5, String str6,String str7,String str8) throws Exception{
		ACA_InstantleakageEntity aca = new ACA_InstantleakageEntity();
		aca.setM1(str1);
		aca.setM2(str2);
		aca.setM3(str3);
		aca.setM4(str4);
		aca.setM5(str5);
		aca.setM6(str6);
		aca.setM7(str7);
		aca.setM8(str8);
		aca.setM9("10");
		Map<String,Object> map = new HashMap<>();
		List<TMESK_MsdsEntity> list = sekbMsdsService.findByWzName(str1);
		map.put("yjcl", JsonMapper.getInstance().toJson(list.get(0)));
		List<TMESK_ProtectionDistanceEntity> list2=sekbCshfhjlService.findByWzName(str1);
		map.put("ssjl", JsonMapper.getInstance().toJson(list2.get(0)));
		map.put("jljs", acaInstantleakageService.countSave(aca));
		return JsonMapper.getInstance().toJson(map);
	}
	
	/***********************************************安全文件发布***********************************************************/
	
	/**
	 * 安全文件管理list
	 * @param str1 页码
	 * @param str2 每页个数
	 * @param str3 关键字
	 * @param str4 行政区域
	 * @param str5 企业id
	 * str6 监管分类
	 * str7 是否产看 0否1是
	 */
	public String Aqwjgl(String str1,String str2,String str3,String str4,String str5,String str6,String str7){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			if(str4.equals("")&&!str5.equals("")){
				//企业
				tmap.put("pageNo",str1);
				tmap.put("pageSize",str2);
				if(!"".equals(str3)){
					tmap.put("wjname",str3);
				}
				tmap.put("uid", str5);
				if(!"".equals(str7)){
					tmap.put("islook",str7);
				}
				map.put("data", JsonMapper.getInstance().toJson(issueAqwjfbService.dataGrid2(tmap)));
			}else if(!str4.equals("")&&str5.equals("")){
				//安监
				tmap.put("pageNo",str1);
				tmap.put("pageSize",str2);
				if(!"".equals(str3)){
					tmap.put("wjname",str3);
				}
				tmap.put("xzqy", str4);
				if(!"".equals(str6)&&!"0".equals(str6)&&str6!=null){
		    		tmap.put("jglx",str6);
				}
				map.put("data", JsonMapper.getInstance().toJson(issueAqwjfbService.dataGrid(tmap)));
			}else{
				map.put("status", "异常");
				map.put("result", "异常,未进入安监或企业");
				return JsonMapper.getInstance().toJson(map);
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 文件传递与接收
	 * @param str1 页码
	 * @param str2 每页个数
	 * @param str3 企业名称
	 * @param str4 id、str5（文件id）、str6（行政区域）、str7（监管分类）、str8（查阅情况1:未2:已、下载情况3:未4:已、回执情况5:未6:已）
	 */
	public String Wjcdjs(String str1,String str2,String str3,String str4,String str5,String str6,String str7,String str8){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str3)){
				tmap.put("qyname",str3);
			}
			tmap.put("uid", str4);
			tmap.put("xzqy", str6);
			if(!"".equals(str7)&&!"0".equals(str7)&&str7!=null){
	    		tmap.put("jglx",str7);
			}
			tmap.put("wjid",str5);
			if(!"".equals(str8)){
				if("1".equals(str8)){
					tmap.put("m1",0);
				}else if("2".equals(str8)){
					tmap.put("m1",1);
				}else if("3".equals(str8)){
					tmap.put("m2",0);
				}else if("4".equals(str8)){
					tmap.put("m2",1);
				}else if("5".equals(str8)){
					tmap.put("m5","0");
				}else if("6".equals(str8)){
					tmap.put("m5","1");
				}
			}
			map.put("data", JsonMapper.getInstance().toJson(issueWjcdjsService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 文件生产动态信息
	 * @param str1 页码
	 * @param str2 每页个数
	 * @param str3 文件名称
	 * @param str4 行政区域
	 * str5监管分类
	 */
	public String Aqscdt(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str3)){
				tmap.put("wjname",str3);
			}
			if(!"".equals(str4)){
				tmap.put("xzqy",str4);
				if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
					tmap.put("jglx",str5);
				}
			}
			map.put("data", JsonMapper.getInstance().toJson(issueAqscdtService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 文件查看下载（企业）
	 * @param str1 qyid
	 * @param str2 文件id
	 * @param str3 类型1：查看2：下载
	 */
	public String Qywjckxz(String str1,String str2,String str3){
		Map<String, Object> map = new HashMap<String, Object>();
		if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,类型为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,文件id为空");
		}else if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,企业id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			if("1".equals(str3)){
				issueWjcdjsService.updateIsorNotLook(Long.parseLong(str1), Long.parseLong(str2));
			}else if("2".equals(str3)){
				issueWjcdjsService.updateIsorNotDownload(Long.parseLong(str1), Long.parseLong(str2));
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/*********************************************专家知识库*******************************************************/
	
	/**
	 * 安全生产法律
	 * @param str1 
	 * @param str2
	 *            1:法律2:法规3:规章4:文件5:安全生产技术标准
	 * @param str3 页码
	 * @param str4 每页个数
	 */
	public String Aqsc(String str1,String str2,String str3,String str4){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,type为空");
		}else if(str3.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str3);
			tmap.put("pageSize",str4);
			if("5".equals(str2)){
				tmap.put("btname", str1);
				map.put("data", JsonMapper.getInstance().toJson(sekbAqscjsbzService.dataGrid(tmap)));
				
			}else{
				tmap.put("type", str2);
				tmap.put("btname", str1);
				map.put("data", JsonMapper.getInstance().toJson(sekbAqscfgService.dataGrid(tmap)));
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 事故案例
	 * @param str1 标题
	 * @param str2 类型
	 * @param str3 页码
	 * @param str4 每页个数
	 */
	public String Sgal(String str1,String str2,String str3,String str4){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo",str3);
		map.put("pageSize",str4);
		if(!"".equals(str1)){
			map.put("btname", str1);
		}
		if(!"".equals(str2)){
			map.put("sglb", str2);
		}
		return JsonMapper.getInstance().toJson(sekbSgalService.dataGrid(map));
	}
	
	/**
	 * 危化品安全信息
 	 * @param 化学品名称
	 * @param 中文名称
	 * @param 是否剧毒 0：否1：是
	 * @param str4 页码
	 * @param str5 每页个数
	 */
	public String Whpaq(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		if(!"".equals(str1)){
			map.put("pname", str1);
		}
		if(!"".equals(str2)){
			map.put("cas", str2);
		}
		//map.put("jdp", str3);
		map.put("pageNo",str4);
		map.put("pageSize",str5);
		return JsonMapper.getInstance().toJson(sekbWhpaqxxService.dataGrid(map));
	}
	
	/**
	 * SDS
	 * @param 化学品
	 * @param 中文名称
	 * @param str3 页码
	 * @param str4 每页个数
	 */
	public String SDS(String str1,String str2,String str3,String str4){
		Map<String, Object> map = new HashMap<String, Object>();
		if(!"".equals(str1)){
			map.put("zwname", str1);
		}
		if(!"".equals(str2)){
			map.put("zwname2", str2);
		}
		map.put("pageNo",str3);
		map.put("pageSize",str4);
		return JsonMapper.getInstance().toJson(sekbMsdsService.dataGrid(map));
	}
	
	/********************************************安全监管***********************************************************/
	
	/**
	 * 检查工作计划
	 * @param str1 页码
	 * @param str2 每页个数
	 * @param str3 检查计划名称
	 * @param str4 年份
	 * @param str5 企业id
	 * str6行政区域 str7监管分类
	 */
	public String Jcgzjh(String str1,String str2,String str3,String str4,String str5,String str6,String str7){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str3)){
				tmap.put("name",str3);
			}
			if(!"".equals(str4)){
				tmap.put("year", str4);
			}
			if(!"".equals(str5)&&"".equals(str6)){
				//企业
				tmap.put("id", str5);
			}else if("".equals(str5)&&!"".equals(str6)){
				//安监
				tmap.put("uxzqy", str6);
				if(!"".equals(str7)&&!"0".equals(str7)&&str7!=null){
					tmap.put("ujglx",str7);
				}
			}
			map.put("data", JsonMapper.getInstance().toJson(aqjgcheckplanservice.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 检查记录专项检查list
	 * @param str1 userid
	 * @param str2 行政区域
	 * @param str3 日期
	 * str4(监管分类) str5(2.初查1.复查)
	 */
	public String JcjlZxmc(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		map.put("status", "正常");
		map.put("result", "成功");
		if(str2.equals("")&&!str1.equals("")){
			//企业
			tmap.put("userid", str1);
			tmap.put("time", str3);
			Map<String,Object> zmap = new HashMap<String, Object>();
			zmap.put("rows",aqjgcheckrecordservice.getAjZxjcApp(tmap));
			map.put("data", JsonMapper.getInstance().toJson(zmap));
		}else if(!str2.equals("")&&str1.equals("")){
			//安监
			tmap.put("xzqy", str2);
			if(!"".equals(str4)&&!"0".equals(str4)&&str4!=null){
				tmap.put("jglx",str4);
			}
			if(!"".equals(str5)){
				tmap.put("flag",str5);
			}
			if(!"".equals(str3)){
				tmap.put("time", str3);
			}
			Map<String,Object> zmap = new HashMap<String, Object>();
			zmap.put("rows",aqjgcheckrecordservice.getAjZxjcApp(tmap));
			map.put("data", JsonMapper.getInstance().toJson(zmap));
		}else{
			map.put("status", "异常");
			map.put("result", "异常,未进入安监或企业");
			return JsonMapper.getInstance().toJson(map);
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 检查记录
	 * @param str1 userid
	 * @param str2 行政区域
	 * @param str3 日期
	 * @param str4 专项检查名
	 * str5 页码 str6 页数 str7 关键字 str8监管分类str9(2.初查1.复查)
	 */
	public String Jcjl(String str1,String str2,String str3,String str4,String str5,String str6,String str7,String str8,String str9){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str5.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str6.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str5);
			tmap.put("pageSize",str6);
			if(!"".equals(str9)){
				tmap.put("flag",str9);
			}
			if(!"".equals(str3)){
				tmap.put("time",str3);
			}
			if(str2.equals("")&&!str1.equals("")){
				//企业
				tmap.put("type", "2");
				if(!"".equals(str4)){
					tmap.put("zxjc", str4);
				}
				tmap.put("userid", str1);
				Map<String,Object> zmap = new HashMap<String, Object>();
				zmap.put("rows",aqjgcheckrecordservice.getAjJcjlApp(tmap));
				map.put("data", JsonMapper.getInstance().toJson(zmap));
			}else if(!str2.equals("")&&str1.equals("")){
				//安监
				tmap.put("type", "1");
				tmap.put("zxjc", str4);
				tmap.put("xzqy", str2);
				if(!"".equals(str8)&&!"0".equals(str8)&&str8!=null){
					tmap.put("jglx",str8);
				}
				if(!"".equals(str7)){
					tmap.put("qyname",str7);
				}
				Map<String,Object> zmap = new HashMap<String, Object>();
				zmap.put("rows",aqjgcheckrecordservice.getAjJcjlApp(tmap));
				map.put("data", JsonMapper.getInstance().toJson(zmap));
			}else{
				map.put("status", "异常");
				map.put("result", "异常,未进入安监或企业");
				return JsonMapper.getInstance().toJson(map);
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 第三方检查记录
	 * @param str1 页码
	 * @param str2 每页个数
	 * @param str3 企业名称
	 * @param str4 企业id
	 * @param str5 行政区域
	 * str6监管类型
	 */
	public String Dsfjcjl(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			if("".equals(str5)&&!str4.equals("")){
				//企业
				tmap.put("pageNo",str1);
				tmap.put("pageSize",str2);
				tmap.put("qyid",str4);
				map.put("data", JsonMapper.getInstance().toJson(aqjgDSFJcjlService.dataGrid(tmap)));
			}else if(!str5.equals("")&&"".equals(str4)){
				//安监
				tmap.put("pageNo",str1);
				tmap.put("pageSize",str2);
				tmap.put("xzqy", str5);
				if(!"".equals(str6)&&!"0".equals(str6)&&str6!=null){
					tmap.put("jglx",str6);
				}
				if(!"".equals(str3)){
					tmap.put("qyname",str3);
				}
				map.put("data", JsonMapper.getInstance().toJson(aqjgDSFJcjlService.dataGrid2(tmap)));
			}else{
				map.put("status", "异常");
				map.put("result", "异常,未进入安监或企业");
				return JsonMapper.getInstance().toJson(map);
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 检查统计分析
	 * @param str1 年份
	 * str2行政区域 str3监管分类
	 */
	public String Jctjfx(String str1,String str2,String str3){
		Map<String, Object> map = new HashMap<String, Object>();// 返回map
		Map<String, Object> param = new HashMap<String, Object>();//参数map
		String[] month = { "01月", "02月", "03月", "04月", "05月", "06月", "07月",
				"08月", "09月", "10月", "11月", "12月" };
		String year=StringUtils.isBlank(str1)?DateUtils.getYear():str1;
		param.put("year", year);
		param.put("uxzqy", str2);
		if(!"".equals(str3)&&!"0".equals(str3)&&str3!=null){
			param.put("ujglx",str3);
		}
		List<Object[]> maplist=aqjgcheckplanservice.getYearDate2(param);
		List<Object> listx = new ArrayList<>();
		List<Object> listqy = new ArrayList<>();// 月计划检查次数
		List<Object> listfin = new ArrayList<>();// 完成次数，企业复检完即算完成
		List<Object> listunfin = new ArrayList<>();// 未完成次数，企业只完成初检或未检查。
		int qycount = 0;
		int count = 0;
		int uncount = 0;
		// 获取每月的各个数量
		try {
			for (int i = 0; i < month.length; i++) {
				listx.add(month[i]);
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("name", month[i]);
				for (Object[] obs : maplist) {
					if ((obs[0].toString().substring(
							obs[0].toString().indexOf("-") + 1, obs[0].toString()
									.length())).equals(String.valueOf(i + 1))) {
						qycount += obs[1].toString().split(",").length;// 获取当月的计划检查次数
						uncount += (int) obs[2];// 获取当月的未完成次数
						count += (int) obs[3];// 企业复检完成
					}
				}
				listqy.add(qycount);
				listfin.add(count);
				listunfin.add(uncount);
				qycount = 0;
				count = 0;
				uncount = 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("xdata", listx);// x轴
		map.put("qycount", listqy);// 计划检查次数
		map.put("fincount", listfin);// 完成次数
		map.put("unfincount", listunfin);//企业只完成初查次数
//		之前返回参数
//		map.put("xdata", listx);// x轴
//		map.put("qycount", listqy);// 计划检查次数
//		map.put("fincount", listfin);// 完成次数
//		map.put("unfincount", listunfin);//企业只完成初查次数
//		map.put("ajfincount", listajfin);// 安监初检
//		map.put("ajfjfincount", listajfjfin);//安监复检
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 第三方管理
	 * str1（页码）、str2（页数）、str3（单位名称）
	 */
	public String Dsf(String str1,String str2,String str3){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str3)){
				tmap.put("dwname", str3);
			}
			map.put("data", JsonMapper.getInstance().toJson(manageService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 服务项目报备
	 * str1（页码）、str2（页数）、str3（安监：企业名关键字，企业：单位名称关键字）、str4（行政区域）、str5（监管类型）、str6（qyid）
	 */
	public String Fwxmbb(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if("".equals(str4)&&!"".equals(str6)){
				//企业
				if(!"".equals(str3)){
					tmap.put("dwname", str3);
				}
				tmap.put("qyid", str6);
				map.put("data", JsonMapper.getInstance().toJson(bbService.dataGrid(tmap)));
			}else if(!"".equals(str4)&&"".equals(str6)){
				//安监
				tmap.put("xzqy", str4);
				if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
					tmap.put("jglx",str5);
				}
				if(!"".equals(str3)){
					tmap.put("qyname", str3);
				}
				map.put("data", JsonMapper.getInstance().toJson(bbService.dataGridAQ(tmap)));
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 征信管理
	 * str1（页码）str2（页数）str3（关键字）str4（1.失信名单2.黑名单）str5（行政区域）、str6（监管类型）
	 */
	public String Zxgl(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,type为空");
		}else if(str5.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			tmap.put("xzqy", str5);
			if(!"".equals(str6)&&!"0".equals(str6)&&str6!=null){
				tmap.put("jglx",str6);
			}
			if("1".equals(str4)){
				if(!"".equals(str3)){
					tmap.put("qyname", str3);
				}
				map.put("data", JsonMapper.getInstance().toJson(sxjlService.dataGridAQ(tmap)));
			}else if("2".equals(str4)){
				if(!"".equals(str3)){
					tmap.put("qyname", str3);
				}
				map.put("data", JsonMapper.getInstance().toJson(hmdService.dataGridAQ(tmap)));
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 事件录入
	 * str1（页码）str2（页数）str3（关键字企业名）
	 */
	public String Sjgl(String str1,String str2,String str3){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str3)){
				tmap.put("dwname",str3);
			}
			map.put("data", JsonMapper.getInstance().toJson(aqjgsjglservice.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 事件录入统计分析
	 * str1（年份）
	 */
	public String Sjgltjfx(String str1){
		//返回map
		Map<String ,Object> remap = new HashMap<String,Object>();
		//查询map
		Map<String ,Object> querymap= new HashMap<String,Object>();
		String[] month={"01月","02月","03月","04月","05月","06月","07月","08月","09月","10月","11月","12月"};
		List<Object> listcount=new ArrayList<Object>();
		List<Object> listmonth=new ArrayList<Object>();
		
		String year= str1;
		
		year=(StringUtils.isNotBlank(year)?year:DateUtils.getYear());
		int  count=0;
		for(int i=0; i<month.length;i++){
			querymap.put("date",year+"-"+(month[i].replace("月", "")));
		    count=aqjgsjglservice.getCountEveryMonth(querymap);
		    querymap.remove("date");
			listcount.add(count);
			listmonth.add(month[i]);
		}
		remap.put("xdate",listmonth);
		remap.put("ydate",listcount);
		return JsonMapper.getInstance().toJson(remap);
	}
	
	/**
	 * 安全备案企业list
	 * str1（页码）、str2（每页个数）、str3（企业名，可为空）、str4（行政区域）、str5（监管分类）
	 */
	public String AqbaQylist(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",aqjgAqbaService.getqylistapp(tmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 安全备案管理
	 * @param str1 页码
	 * @param str2 每页个数
	 * @param str3 企业id
	 */
	public String Aqba(String str1,String str2,String str3){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			tmap.put("qyid", str3);
			tmap.put("usertype", "0");
			map.put("data", JsonMapper.getInstance().toJson(aqjgAqbaService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 特殊作业报备企业list
	 * str1（页码）、str2（每页个数）、str3（企业名，可为空）、str4（行政区域）、str5（监管分类）、str6（2.本厂1.外包）
	 */
	public String TszybbQylist(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str4.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			if(!"".equals(str6)){
				tmap.put("zydw",str6);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",dwDhzyService.getqylistapp(tmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 特殊作业报备
	 * str1（页码）str2（页数）str3（qyid）、str4（2.本厂1.外包）
	 */
	public String Tszybb(String str1,String str2,String str3,String str4){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str3.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);			
			tmap.put("qyid", str3);
			if(!"".equals(str4)){
				tmap.put("zydw",str4);
			}
			tmap.put("usertype","0");
			map.put("data", JsonMapper.getInstance().toJson(dwDhzyService.dataGridApp(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	
	/***********************************************隐患排查************************************************/
	
	/**
	 * 隐患巡检点状态-巡检点统计
	 * str1（页码）str2（页数）str3（行政区域）、str4（监管类型）、str5（关键字企业名）、str6（开始时间 如2017-01-03）、str7（结束时间）
	 */
	public String Yhdtjfx(String str1,String str2,String str3,String str4,String str5,String str6,String str7){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if(str3.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);			
			tmap.put("xzqy", str3);
			if(!"".equals(str4)&&!"0".equals(str4)&&str4!=null){
				tmap.put("jglx",str4);
			}
			if(!"".equals(str5)){
				tmap.put("qyname", str5);
			}
			if(!"".equals(str6)){
				tmap.put("starttime", str6);
			}
			if(!"".equals(str7)){
				tmap.put("finishtime", str7);
			}
			map.put("data", JsonMapper.getInstance().toJson(yhpcTjfxService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 隐患点状态
	 * @param str1 qyid
	 * @return
	 */
	public String Yhdzt(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("qyid",str1);
			List<Map<String,Object>> list = yhpcXjdztService.dataGrid(tmap);
			//避免jstl表达式多次循环判断风险点等级
			List<Map<String,Object>> listr=new ArrayList<>();//红
			List<Map<String,Object>> listo=new ArrayList<>();//橙	
			List<Map<String,Object>> listy=new ArrayList<>();//黄
			List<Map<String,Object>> listb=new ArrayList<>();//蓝
			List<Map<String,Object>> listd=new ArrayList<>();//自定义
			//{红/橙/黄/蓝/自定义{正常，异常，未巡检},所有检查点{正常，异常，未巡检}}
			int tj[][]= {{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0,list.size()}};
			for(Map<String,Object> m:list){
				if("1".equals(m.get("yanse")+"")){
					listr.add(m);
					if("0".equals(m.get("checkresult"))){
						tj[0][0]++;
						tj[5][0]++;
					}
					else if("1".equals(m.get("checkresult"))){
						tj[0][1]++;
						tj[5][1]++;
					}
					else{
						tj[0][2]++;
						tj[5][2]++;
					}
				}else if("2".equals(m.get("yanse")+"")){
					listo.add(m);
					if("0".equals(m.get("checkresult"))){
						tj[1][0]++;
						tj[5][0]++;					
					}
					else if("1".equals(m.get("checkresult"))){
						tj[1][1]++;
						tj[5][1]++;					
					}
					else {
						tj[1][2]++;
						tj[5][2]++;					
					}
				}else if("3".equals(m.get("yanse")+"")){
					listy.add(m);
					if("0".equals(m.get("checkresult"))){
						tj[2][0]++;
						tj[5][0]++;					
					}
					else if("1".equals(m.get("checkresult"))){
						tj[2][1]++;
						tj[5][1]++;					
					}
					else {
						tj[2][2]++;
						tj[5][2]++;					
					}
				}else if("4".equals(m.get("yanse")+"")){
					listb.add(m);
					if("0".equals(m.get("checkresult"))){
						tj[3][0]++;
						tj[5][0]++;					
					}
					else if("1".equals(m.get("checkresult"))){
						tj[3][1]++;
						tj[5][1]++;					
					}
					else {
						tj[3][2]++;
						tj[5][2]++;					
					}
				}else if("5".equals(m.get("yanse")+"")){
					listd.add(m);
					if("0".equals(m.get("checkresult"))){
						tj[4][0]++;
						tj[5][0]++;					
					}
					else if("1".equals(m.get("checkresult"))){
						tj[4][1]++;
						tj[5][1]++;					
					}
					else {
						tj[4][2]++;
						tj[5][2]++;					
					}
				}
			}
			Map<String, Object> ztmap = new HashMap<String, Object>();
            ztmap.put("listr",listr);
            ztmap.put("listo",listo);
            ztmap.put("listy",listy);
            ztmap.put("listb",listb);
            ztmap.put("listd",listd);
            ztmap.put("tj",tj);
			map.put("data", JsonMapper.getInstance().toJson(ztmap));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 隐患点巡检记录（企业）
	 * str1(页码) str2（页数）str3（qyid） str4（隐患点id Yhdzt接口获取的jcdid） str5（隐患点类型 Yhdzt接口获取的jcdtype）str6（隐患点名称）str7（开始时间）str8（结束时间）str9（有无隐患0无隐患1有隐患）
	 */
	public String Yhdxjjl(String str1,String str2,String str3,String str4,String str5,String str6,String str7,String str8,String str9){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);	
			if(!"".equals(str3)){
				tmap.put("qyid",str3);
			}
			if(!"".equals(str4)){
				tmap.put("jcdid",str4);		
			}
			if(!"".equals(str5)){
				tmap.put("jcdtype",str5);
			}
			if(!"".equals(str6)){
				tmap.put("jcdname",str6);
			}
			if(!"".equals(str7)){
				tmap.put("starttime",str7);
			}
			if(!"".equals(str8)){
				tmap.put("finishtime",str8);
			}
			if(!"".equals(str9)){
				tmap.put("checkresult",str9);
			}
			map.put("data", JsonMapper.getInstance().toJson(yhpcXjjlService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 隐患点巡检记录（安监）
	 * str1(页码) str2（页数）str3（企业名）str4（隐患点名称）str5（开始时间）str6（结束时间）str7（有无隐患0无隐患1有隐患）str8（xzqy）str9（jglx）
	 */
	public String Yhdajxjjl(String str1,String str2,String str3,String str4,String str5,String str6,String str7,String str8,String str9){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);	
			if(!"".equals(str3)){
				tmap.put("qyname",str3);
			}
			if(!"".equals(str4)){
				tmap.put("jcdname",str4);		
			}
			if(!"".equals(str5)){
				tmap.put("starttime",str5);
			}
			if(!"".equals(str6)){
				tmap.put("finishtime",str6);
			}
			if(!"".equals(str7)){
				tmap.put("checkresult",str7);
			}
			if(!"".equals(str8)){
				tmap.put("xzqy",str8);
			}
			if(!"".equals(str9)&&!"0".equals(str9)&&str9!=null){
				tmap.put("jglx",str9);
			}
			map.put("data", JsonMapper.getInstance().toJson(yhpcXjjlService.ajdataGridForApp(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 隐患巡检记录企业list
	 * str1 页码str2 每页个数str3 关键字str4 行政区域str5 监管分类
	 */
	public String Yhdxjjlqylist(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str4)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",yhpcXjjlService.getqylistForApp(tmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 巡检记录详情
	 * str1 （页码）、str2（每页个数）、str3（巡检记录id）、str4（隐患点id）、str5（type）
	 */
	public String Yhdxjjlxq(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,巡检记录id为空");
		}else if("".equals(str4)){
			map.put("status", "异常");
			map.put("result", "异常,隐患id为空");
		}else if("".equals(str5)){
			map.put("status", "异常");
			map.put("result", "异常,type为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xjjlid", str3);
			tmap.put("jcdid", str4);
			tmap.put("type", str5);
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(sxgkfxxxService.xjnrdataGrid3(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
//	/**
//	 * 隐患排查记录企业list
//	 * str1（页码）str2（每页个数）str3（关键字）str4（行政区域）str5（监管分类）
//	 */
//	public String Yhdyhpcjlqylist(String str1,String str2,String str3,String str4,String str5){
//		Map<String, Object> map = new HashMap<String, Object>();
//		Map<String, Object> tmap = new HashMap<String, Object>();
//		if("".equals(str1)){
//			map.put("status", "异常");
//			map.put("result", "异常,page为空");
//		}else if("".equals(str2)){
//			map.put("status", "异常");
//			map.put("result", "异常,pageSize为空");
//		}else if("".equals(str4)){
//			map.put("status", "异常");
//			map.put("result", "异常,xzqy为空");
//		}else{
//			tmap.put("pageNo", str1);
//			tmap.put("pageSize", str2);
//			tmap.put("xzqy", str4);
//			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
//				tmap.put("jglx",str5);
//			}
//			if(!"".equals(str3)){
//				tmap.put("qyname", str3);
//			}
//			map.put("status", "正常");
//			map.put("result", "成功");
//			map.put("data",yhpcYhpcjlService.getqylistForApp(tmap));	
//		}
//		return JsonMapper.getInstance().toJson(map);
//	}
	
	/**
	 * 隐患排查记录list（安监）
	 * str1（页码）str2（每页个数）str3（企业名关键字）str4（行政区域）str5（监管分类 ）str6（隐患处理情况0：未整改1：企业整改完成2：审核未通过3：审核通过）
	 */
	public String Yhdyhpcjl(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			if(!"".equals(str6)){
				tmap.put("zgzt", str6);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(yhpcYhpcjlService.dataGridForApp(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 隐患排查记录list（企业）
	 * str1（页码）str2（每页个数）str3（巡检点关键字）str4（企业id）str5（隐患处理情况0：未整改1：企业整改完成2：审核未通过3：审核通过）
	 */
	public String Yhdqyyhpcjl(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("qyid", str4);
			if(!"".equals(str3)){
				tmap.put("jcdname", str3);
			}
			if(!"".equals(str5)){
				tmap.put("zgzt", str5);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(yhpcYhpcjlService.dataGridForApp(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 隐患整改复查list
	 * str1（页码）、str2（页数）、str3（隐患记录id）
	 */
	public String Yhzgfclist(String str1,String str2,String str3){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,隐患记录id为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("yhjlid", str3);
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(wghglWgyxjjlzgService.zglistdataGrid(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 隐患监督与考核
	 * str1（页码）str2（页数）str3（关键字企业名）、str4（行政区域）、str5（监管类型）、str6（开始时间 如2017-01-03）、str7（结束时间）
	 */
	public String Yhjdkh(String str1,String str2,String str3,String str4,String str5,String str6,String str7) throws ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str4)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String end=dateFormat.format(date);//默认结束时间
			String start=end.substring(0,4)+"-01-01";//默认开始时间
			if(!"".equals(str6)){
				start = str6;
			}
			if(!"".equals(str7)){
				end = str7;
			}
			//计算年检的应查次数乘积
			int nj=(int) Math.ceil(Integer.parseInt(end.substring(0,3))-Integer.parseInt(start.substring(0,3)))+1;
			//计算月检的应查次数乘积
			Date beginDate = dateFormat.parse(start);
			Date endDate= dateFormat.parse(end);    
			long day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);//相差天数
			int yj=(int)(day/31)+1;
			//计算周检的应查次数乘积
			int zj=(int)(day/7)+1;
			//计算日检的应查次数乘积
			int rj=(int) day;
			tmap.put("nj",nj);
			tmap.put("yj",yj);
			tmap.put("zj",zj);
			tmap.put("rj",rj);
			tmap.put("start", start);
			tmap.put("end", end);
			
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(yhpcXjjdService.khdataGrid(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 隐患统计分析1
	 * str1（行政区域）str2（监管类型）
	 */
	public String Yhtjfx1(String str1,String str2){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("xzqy", str1);
			if(!"".equals(str2)&&!"0".equals(str2)&&str2!=null){
				tmap.put("jglx",str2);
			}
			Map<String, Object> map2 = yhpcTjfxService.tjcount(tmap);
			//企业总数
			int getQyCount=Integer.parseInt(map2.get("qysum").toString());
			//已安装企业数
			int getAzQyCount=Integer.parseInt(map2.get("azsum").toString());
			//本月已巡检企业数
			int getXjQyCount=Integer.parseInt(map2.get("yxjsum").toString());
			//本月检查记录数
			int getJcCount=Integer.parseInt(map2.get("jcsum").toString());
			//本月检查隐患数
			int getYhCount=Integer.parseInt(map2.get("jcyhsum").toString());
			//本月未整改隐患数
			int getWzgYhCount=Integer.parseInt(map2.get("wzgsum").toString());
			//本月已整改隐患数
			int getYzgYhCount=Integer.parseInt(map2.get("yzgsum").toString());
			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("qycount", getQyCount);
			map3.put("azqycount", getAzQyCount);
			map3.put("xjqycount", getXjQyCount);
			map3.put("wxjqycount", getQyCount-getXjQyCount);
			map3.put("jccount", getJcCount);
			map3.put("yhcount", getYhCount);
			map3.put("wzgcount", getWzgYhCount);
			map3.put("yzgcount", getYzgYhCount);
			map.put("data",JsonMapper.getInstance().toJson(map3));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 隐患统计分析2
	 * str1（行政区域）、str2（监管类型）、str3（1：天2：月3：年）
	 */
	public String Yhtjfx2(String str1,String str2,String str3){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,类型为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("xzqy", str1);
			if(!"".equals(str2)&&!"0".equals(str2)&&str2!=null){
				tmap.put("jglx",str2);
			}
			Map<String, Object> map2 = new HashMap<String, Object>();
			List<Integer> list = new ArrayList<>();
			Date d = new Date();
			if("1".equals(str3)){
				map2 = yhpcTjfxService.getDayCount(tmap);
				int a = d.getDate();
				d.setDate(d.getDate() - 1);
				int b = d.getDate();
				d.setDate(d.getDate() - 1);
				int c = d.getDate();
				list.add(c);
				list.add(b);
				list.add(a);
			}else if("2".equals(str3)){
				map2 = yhpcTjfxService.getMonthCount(tmap);
				int a = d.getMonth()+1;
				d.setMonth(d.getMonth() - 1);
				int b = d.getMonth()+1;
				d.setMonth(d.getMonth() - 1);
				int c = d.getMonth()+1;
				list.add(c);
				list.add(b);
				list.add(a);
			}else if("3".equals(str3)){
				map2 = yhpcTjfxService.getYearCount(tmap);
				list.add(d.getYear()+1898);
				list.add(d.getYear()+1899);
				list.add(d.getYear()+1900);	
			}
			map2.put("x", list);
			map.put("data",JsonMapper.getInstance().toJson(map2));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 随手拍隐患list（安监）只查看安监发现
	 * str1（页码）str2（每页个数）str3（企业名关键字）str4（行政区域）str5（监管分类 ）str6（隐患处理情况0：未整改3：已整改）
	 * @return
	 */
	public String SspyhList(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str4)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			if(!"".equals(str6)){
				tmap.put("yhzt", str6);
			}
			tmap.put("xz", "0");//发现人的usertype为安监
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(yhpcSspService.dataGridForApp(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 随手拍隐患list（企业）
	 * str1（页码）str2（每页个数）str3（qyid）str4（隐患处理情况0：未整改3：已整改）
	 * @return
	 */
	public String SspqyyhList(String str1,String str2,String str3,String str4){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("qyid", str3);
			if(!"".equals(str4)){
				tmap.put("yhzt", str4);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(yhpcSspService.dataGridForApp(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 企业隐患处理提交
	 * str1（json）str2（1:隐患点2:网格点3:随手拍）
	 * @return
	 */
	public String YhjlQycl(String str1,String str2){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data", "操作成功");
			//向整改复查表里添加一条记录
			Timestamp t = DateUtils.getSysTimestamp();
			YHPC_HandleRecordEntity ycr = (YHPC_HandleRecordEntity)JsonMapper.fromJsonString(str1, YHPC_HandleRecordEntity.class);
			ycr.setHandletime(t);
			wghglWgyxjjlzgService.addwgzgjl(ycr);
			//修改隐患记录主表状态
			if("1".equals(str2)){
				//隐患点  分整改和审核
				if(ycr.getHandleType()==1){
					//整改
					wghglWgyxjjlzgService.updateDangerstatus(ycr.getDangerid(),"1");
				}else{
					//复查
					if(ycr.getHandlestatus()==1){
						wghglWgyxjjlzgService.updateDangerstatus(ycr.getDangerid(),"2");
					}else{
						wghglWgyxjjlzgService.updateDangerstatus(ycr.getDangerid(),"3");
					}
				}
			}else if("2".equals(str2)){
				//网格点 只有整改
				wghglWgyxjjlzgService.updateDangerstatus(ycr.getDangerid(),"1");
			}else if("3".equals(str2)){
				//随手拍 只有整改
				wghglWgyxjjlzgService.updateDangerstatus(ycr.getDangerid(),"3");
			}
		}catch(Exception e){
			map.put("status", "异常");
			map.put("result", "异常");
			map.put("data", "网络异常!" + e.getMessage());
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 企业班次
	 * str1（页码）、str2（页数）、str3（类型1:日2:周3:月4:年）、str4（1：企业班次2：我的班次）、str5（）
	 */
	public String Yhqybc(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			if(!"".equals(str3)){
				tmap.put("type", str3);
			}
			if("1".equals(str4)){
				tmap.put("qyid", str5);
				map.put("data",JsonMapper.getInstance().toJson(yhpcCheckPlanService.dataGrid(tmap)));	
			}else if("2".equals(str4)){
				tmap.put("userid", str5);
				map.put("data",JsonMapper.getInstance().toJson(yhpcCheckPlanService.myrwdataGrid(tmap)));	
			}else{
				map.put("status", "异常");
				map.put("result", "异常");
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 企业班次详情
	 * @param str1（班次id）
	 * @return
	 */
	public String Yhqybcxq(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,班次id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			YHPC_CheckPlanEntity bcrw = yhpcCheckPlanService.findById(Long.parseLong(str1));
			String xjrynames = "";
			String jcdnames = "";
			List<YHPC_CheckPlan_Time> rwsjlist1 = null;
			List<YHPC_CheckPlan_Time> rwsjlist2 = new ArrayList<>();
			if(bcrw.getType().equals("2")){
				for (YHPC_CheckPlan_Time yct : rwsjlist1) {
					switch (yct.getStarttime()) {
					case "1":
						yct.setStarttime("周一");
						break;
					case "2":
						yct.setStarttime("周二");
						break;
					case "3":
						yct.setStarttime("周三");
						break;
					case "4":
						yct.setStarttime("周四");
						break;
					case "5":
						yct.setStarttime("周五");
						break;
					case "6":
						yct.setStarttime("周六");
						break;
					case "7":
						yct.setStarttime("周日");
						break;
					default:
						break;
					};
					switch (yct.getEndtime()) {
					case "1":
						yct.setEndtime("周一");
						break;
					case "2":
						yct.setEndtime("周二");
						break;
					case "3":
						yct.setEndtime("周三");
						break;
					case "4":
						yct.setEndtime("周四");
						break;
					case "5":
						yct.setEndtime("周五");
						break;
					case "6":
						yct.setEndtime("周六");
						break;
					case "7":
						yct.setEndtime("周日");
						break;
					default:
						break;
					};
					rwsjlist2.add(yct);
				}
			}else if(bcrw.getType().equals("4")){
				for (YHPC_CheckPlan_Time yct : rwsjlist1) {
					switch (yct.getStarttime()) {
					case "1":
						yct.setStarttime("一月初");
						break;
					case "2":
						yct.setStarttime("二月初");
						break;
					case "3":
						yct.setStarttime("三月初");
						break;
					case "4":
						yct.setStarttime("四月初");
						break;
					case "5":
						yct.setStarttime("五月初");
						break;
					case "6":
						yct.setStarttime("六月初");
						break;
					case "7":
						yct.setStarttime("七月初");
						break;
					case "8":
						yct.setStarttime("八月初");
						break;
					case "9":
						yct.setStarttime("九月初");
						break;
					case "10":
						yct.setStarttime("十月初");
						break;
					case "11":
						yct.setStarttime("十一月初");
						break;
					case "12":
						yct.setStarttime("十二月初");
						break;
					default:
						break;
					};
					switch (yct.getEndtime()) {
					case "1":
						yct.setEndtime("一月末");
						break;
					case "2":
						yct.setEndtime("二月末");
						break;
					case "3":
						yct.setEndtime("三月末");
						break;
					case "4":
						yct.setEndtime("四月末");
						break;
					case "5":
						yct.setEndtime("五月末");
						break;
					case "6":
						yct.setEndtime("六月末");
						break;
					case "7":
						yct.setEndtime("七月末");
						break;
					case "8":
						yct.setEndtime("八月末");
						break;
					case "9":
						yct.setEndtime("九月末");
						break;
					case "10":
						yct.setEndtime("十月末");
						break;
					case "11":
						yct.setEndtime("十一月末");
						break;
					case "12":
						yct.setEndtime("十二月末");
						break;	
					default:
						break;
					};
					rwsjlist2.add(yct);
				}
			}else{
				rwsjlist2 = rwsjlist1;
			}
			tmap.put("rwsjlist", rwsjlist2);
			//巡检人员数据拼接
			List<Map<String,Object>> list1 = yhpcCheckPlanService.getidname(bcrw.getID());
			if(list1.size()>0){
				for (Map<String, Object> map2 : list1) {
					xjrynames = xjrynames + map2.get("NAME") +",";
				}
				xjrynames = xjrynames.substring(0, xjrynames.length()-1);
			}
			//巡检点数据拼接
			List<Map<String,Object>> list2 = null;
			if(list2.size()>0){
				for (Map<String, Object> map2 : list2) {
					jcdnames = jcdnames + map2.get("name") +",";
				}
				jcdnames = jcdnames.substring(0, jcdnames.length()-1);
			}
			tmap.put("xjrynames", xjrynames);
			tmap.put("jcdnames", jcdnames);
			tmap.put("bcrw", bcrw);
			map.put("data",JsonMapper.getInstance().toJson(tmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 企业隐患自查点信息
	 * str1（页码）、str2（每页个数）、str3（隐患点名称关键字）、str4（qyid）
	 */
	public String Yhqyzcd(String str1,String str2,String str3,String str4){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			if(!"".equals(str3)){
				tmap.put("yhdname", str3);
			}
			tmap.put("qyid", str4);
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(yhpcYhpcdService.dataGrid1(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 企业二维码扫描信息
	 * str1（二维码）、str2（qyid）
	 */
	public String Qyewmxx(String str1,String str2){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,二维码为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else{
			List<Map<String, Object>> qyxcdlist = yhpcYhpcdService.findInforByewmForApp(str1);
			if(qyxcdlist.size()>1){
				map.put("status", "异常");
				map.put("result", "异常,绑定二维码重复,请及时与运维人员联系！");
			}else if(qyxcdlist.size()<1){
				map.put("status", "异常");
				map.put("result", "异常,二维码无效,请及时与运维人员联系！");
			}else{
				//判断该点是否属于登录人的企业
				Map<String,Object> qyxcdmap = qyxcdlist.get(0);
				if(qyxcdmap.get("id1").toString().equals(str2)){
					map.put("status", "正常");
					map.put("result", "成功");
					tmap.put("xcdxx",qyxcdmap);
					//查询最新巡检记录
					String type = "";
					if(qyxcdmap.get("type").toString().equals("1")){
						type = "fxd";
					}else{
						type = "yhd";
					}
					tmap.put("newxjjl", yhpcXjjlService.getnewXjjlForApp(Long.parseLong(qyxcdmap.get("id").toString()),type));
					map.put("data",tmap);	
				}else{
					map.put("status", "异常");
					map.put("result", "异常,该巡检点不在您所属企业中！");
				}
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 企业巡检班次选择
	 * str1（userid）、str2（巡检点id）、str3（type巡检点类型）
	 */
	public String Qyxjbc(String str1,String str2,String str3){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,userid为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,巡检点id为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,巡检点类型为空");
		}else{
			tmap.put("userid", str1);
			tmap.put("cpid", str2);
			tmap.put("cptype", str3);
			
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(yhpcCheckPlanService.qyxjbcForApp(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 自查点巡检内容
	 * str1（巡检点id）、str2（type巡检点类型）
	 */
	public String Zcdxjnr(String str1,String str2){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		Map<String, Object> tmap2 = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,巡检点id为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,巡检点类型为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("xjid", str1);
			tmap.put("xjtype", str2);
			tmap2.put("rows", yhpcYhpcdService.zcdxjnrForApp(tmap));
			map.put("data",JsonMapper.getInstance().toJson(tmap2));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 自查点巡检提交
	 */
	public String Qyxjtj(String str1,String str2){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data", "操作成功");
			Timestamp t = DateUtils.getSysTimestamp();
			//添加巡检记录
			YHPC_CheckResultEntity ycr = (YHPC_CheckResultEntity)JsonMapper.fromJsonString(str1, YHPC_CheckResultEntity.class);
			//ycr.setCheckpointtype("2");
			ycr.setCreatetime(t);
			wghglWgyxjjlService.addycrForApp(ycr);
			
			//添加隐患记录
			if(!com.cczu.util.common.StringUtils.isEmpty(str2)){
				JsonMapper jsonMapper = new JsonMapper();
				JavaType javatype = jsonMapper.createCollectionType(ArrayList.class,YHPC_CheckHiddenInfoEntity.class);
				List<YHPC_CheckHiddenInfoEntity> ychlist = jsonMapper.fromJson(str2, javatype);
				if(ychlist.size()>0){
					for (YHPC_CheckHiddenInfoEntity ych : ychlist) {
						ych.setCheckresult_id(ycr.getID());
						ych.setCreatetime(t);
						//ych.setCheckpointtype((long) 2);
						yhpcSspService.addssp(ych);
					}
				}
			}
		}catch(Exception e){
			map.put("status", "异常");
			map.put("result", "异常");
			map.put("data", "网络异常!" + e.getMessage());
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 企业rfid扫描信息
	 * str1（rfid）、str2（qyid）
	 */
	public String Qyrfidxx(String str1,String str2){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,rfid为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else{
			List<Map<String, Object>> qyxcdlist = yhpcYhpcdService.findInforByrfidForApp(str1);
			if(qyxcdlist.size()>1){
				map.put("status", "异常");
				map.put("result", "异常,绑定rfid重复,请及时与运维人员联系！");
			}else if(qyxcdlist.size()<1){
				map.put("status", "异常");
				map.put("result", "异常,rfid无效,请及时与运维人员联系！");
			}else{
				//判断该点是否属于登录人的企业
				Map<String,Object> qyxcdmap = qyxcdlist.get(0);
				if(qyxcdmap.get("id1").toString().equals(str2)){
					map.put("status", "正常");
					map.put("result", "成功");
					tmap.put("xcdxx",qyxcdmap);
					//查询最新巡检记录
					String type = "";
					if(qyxcdmap.get("type").toString().equals("1")){
						type = "fxd";
					}else{
						type = "yhd";
					}
					tmap.put("newxjjl", yhpcXjjlService.getnewXjjlForApp(Long.parseLong(qyxcdmap.get("id").toString()),type));
					map.put("data",tmap);	
				}else{
					map.put("status", "异常");
					map.put("result", "异常,该巡检点不在您所属企业中！");
				}
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/***********************************************网格化************************************************/
	@Autowired
	private WghglWgyxjjlService wghglWgyxjjlService;
	@Autowired
	private WghglXjszService wghglXjszService;
	@Autowired
	private YhpcCheckPlanService yhpcCheckPlanService;
	@Autowired
	private BarrioService barrioService;
	@Autowired
	private WghglTjfxService wghglTjfxService;
	@Autowired
	private YhpcSspService yhpcSspService;
	
	/**
	 * 巡检记录
	 * str1(页码) str2（页数）str3（企业名关键字） str4（xzqy） str5（jglx）str6（检查结果1：有隐患0：无隐患）str7（开始时间）str8（结束时间）
	 */
	public String Wgdxjjl(String str1,String str2,String str3,String str4,String str5,String str6,String str7,String str8){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str4)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname",str3);
			}
			if(!"".equals(str6)){
				tmap.put("checkresult",str6);
			}
			if(!"".equals(str7)){
				tmap.put("starttime",str7);
			}
			if(!"".equals(str8)){
				tmap.put("finishtime",str8);
			}
			map.put("data", JsonMapper.getInstance().toJson(wghglWgyxjjlService.dataGridForApp(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格巡检记录详情
	 * str1 （页码）、str2（每页个数）、str3（巡检记录id）、str4（网格点id）
	 */
	public String Wgdxjjlxq(String str1,String str2,String str3,String str4){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,巡检记录id为空");
		}else if("".equals(str4)){
			map.put("status", "异常");
			map.put("result", "异常,隐患id为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xjjlid", str3);
			tmap.put("jcdid", str4);
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(wghglWgdService.xjnrdataGrid(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格点隐患排查记录list（安监）
	 * str1（页码）str2（每页个数）str3（企业名关键字）str4（行政区域）str5（监管分类 ）str6（隐患处理情况0：未整改1：企业整改完成2：审核未通过3：审核通过）
	 */
	public String Wgdyhpcjl(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str4)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			if(!"".equals(str6)){
				tmap.put("dangerstatus", str6);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(wghglWgyxjjlzgService.dataGridForApp(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格点隐患排查记录list（企业）
	 * str1（页码）str2（每页个数）str3（qyid）str4（隐患处理情况0：未整改1：企业整改完成2：审核未通过3：审核通过）
	 */
	public String Wgdyhpcqyjl(String str1,String str2,String str3,String str4){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,qyid为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("qyid", str3);
			if(!"".equals(str4)){
				tmap.put("dangerstatus", str4);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(wghglWgyxjjlzgService.dataGridForApp(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格点信息
	 * str1（页码）str2（每页个数）str3（企业名关键字）str4（行政区域）str5（监管分类 ）str6(1：网格区域下2：直属)
	 */
	public String Wgdxx(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str4)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			if("1".equals(str6)){
				tmap.put("xzqy", str4);
			}else if("2".equals(str6)){
				tmap.put("wgxzqy", str4);
			}
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(wghglWgdService.dataGrid1(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格班次信息
	 * str1（页码）str2（每页个数）str3（班次名关键字）str4（行政区域）
	 */
	public String WgBcxx(String str1,String str2,String str3,String str4){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str4)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str3)){
				tmap.put("bcname", str3);
			}
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(wghglXjszService.dataGrid(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格班次详情
	 * str1（班次id）
	 */
	public String WgBcxq(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,班次id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			YHPC_CheckPlanEntity bcrw = wghglXjszService.findById(Long.parseLong(str1));
			String jcdnames = "";
			List<YHPC_CheckPlan_Time> rwsjlist1 = null;
			List<YHPC_CheckPlan_Time> rwsjlist2 = new ArrayList<>();
			if(bcrw.getType().equals("4")){
				for (YHPC_CheckPlan_Time yct : rwsjlist1) {
					switch (yct.getStarttime()) {
					case "1":
						yct.setStarttime("一月初");
						break;
					case "2":
						yct.setStarttime("二月初");
						break;
					case "3":
						yct.setStarttime("三月初");
						break;
					case "4":
						yct.setStarttime("四月初");
						break;
					case "5":
						yct.setStarttime("五月初");
						break;
					case "6":
						yct.setStarttime("六月初");
						break;
					case "7":
						yct.setStarttime("七月初");
						break;
					case "8":
						yct.setStarttime("八月初");
						break;
					case "9":
						yct.setStarttime("九月初");
						break;
					case "10":
						yct.setStarttime("十月初");
						break;
					case "11":
						yct.setStarttime("十一月初");
						break;
					case "12":
						yct.setStarttime("十二月初");
						break;
					default:
						break;
					};
					switch (yct.getEndtime()) {
					case "1":
						yct.setEndtime("一月末");
						break;
					case "2":
						yct.setEndtime("二月末");
						break;
					case "3":
						yct.setEndtime("三月末");
						break;
					case "4":
						yct.setEndtime("四月末");
						break;
					case "5":
						yct.setEndtime("五月末");
						break;
					case "6":
						yct.setEndtime("六月末");
						break;
					case "7":
						yct.setEndtime("七月末");
						break;
					case "8":
						yct.setEndtime("八月末");
						break;
					case "9":
						yct.setEndtime("九月末");
						break;
					case "10":
						yct.setEndtime("十月末");
						break;
					case "11":
						yct.setEndtime("十一月末");
						break;
					case "12":
						yct.setEndtime("十二月末");
						break;	
					default:
						break;
					};
					rwsjlist2.add(yct);
				}
			}else{
				rwsjlist2 = rwsjlist1;
			}
			tmap.put("rwsjlist", rwsjlist2);
			//巡检点数据拼接
//			List<Map<String,Object>> list2 = wghglXjszService.getidname2(bcrw.getID());
//			if(list2.size()>0){
//				for (Map<String, Object> jcdmap : list2) {
//					jcdnames = jcdnames + jcdmap.get("name") +",";
//				}
//				jcdnames = jcdnames.substring(0, jcdnames.length()-1);
//			}
			tmap.put("jcdnames", jcdnames);
			tmap.put("bcrw", bcrw);
			map.put("data",JsonMapper.getInstance().toJson(tmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格信息
	 * str1（行政区域）
	 */
	public String Wgxx(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		Map<String, Object> allmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("xzqy", str1);
			tmap.put("len", str1.length()+6);
			allmap.put("wgxx",wghglWgdService.WgxxForApp(tmap));
			allmap.put("xswglist",wghglWgdService.XswgForApp(tmap));
			map.put("data",JsonMapper.getInstance().toJson(allmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格成员
	 * str1（行政区域）
	 */
	public String Wgcylist(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("usertype", 0);
			tmap.put("eqxzqy", str1);
			map.put("data",JsonMapper.getInstance().toJson(barrioService.getWgUser(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格点统计
	 * str1(页码) str2（页数）str3（企业名关键字） str4（xzqy） str5（jglx）str6（1:月检2：年检）str7（月检：2017-09 年检：2017）
	 */
	public String Wgdtjfx(String str1,String str2,String str3,String str4,String str5,String str6,String str7){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str4)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else if("".equals(str6)){
			map.put("status", "异常");
			map.put("result", "异常,巡检类型为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			tmap.put("xzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("jglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("qyname",str3);
			}
			if("1".equals(str6)){
				tmap.put("yf",str7);
				map.put("data", JsonMapper.getInstance().toJson(wghglTjfxService.xjdyjdataGrid(tmap)));
			}else if("2".equals(str6)){
				tmap.put("nf",str7);
				map.put("data", JsonMapper.getInstance().toJson(wghglTjfxService.xjdnjdataGrid(tmap)));
			}
			
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格人员统计
	 * str1(页码) str2（页数）str3（网格员关键字） str4（xzqy） str5（jglx）str6（1:月检2：年检）str7（月检：2017-09 年检：2017）
	 */
	public String Wgrytjfx(String str1,String str2,String str3,String str4,String str5,String str6,String str7){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str4)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else if("".equals(str6)){
			map.put("status", "异常");
			map.put("result", "异常,巡检类型为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			tmap.put("userxzqy", str4);
			if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
				tmap.put("userjglx",str5);
			}
			if(!"".equals(str3)){
				tmap.put("ryname",str3);
			}
			if("1".equals(str6)){
				tmap.put("yf",str7);
				map.put("data", JsonMapper.getInstance().toJson(wghglTjfxService.xjryyjdataGrid(tmap)));
			}else if("2".equals(str6)){
				tmap.put("nf",str7);
				map.put("data", JsonMapper.getInstance().toJson(wghglTjfxService.xjrynjdataGrid(tmap)));
			}
			
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格班次统计
	 * str1(页码) str2（页数）str3（班次名关键字） str4（xzqy） str5（1:月检2：年检）str6（月检：2017-09 年检：2017）
	 */
	public String Wgbctjfx(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str4)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else if("".equals(str5)){
			map.put("status", "异常");
			map.put("result", "异常,巡检类型为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			tmap.put("wgcode", str4);
			if(!"".equals(str3)){
				tmap.put("bcname",str3);
			}
			if("1".equals(str5)){
				tmap.put("yf",str6);
				map.put("data", JsonMapper.getInstance().toJson(wghglTjfxService.bcyjdataGrid(tmap)));
			}else if("2".equals(str5)){
				tmap.put("nf",str6);
				map.put("data", JsonMapper.getInstance().toJson(wghglTjfxService.bcnjdataGrid(tmap)));
			}
			
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 统计分析-巡检记录
	 * str1(页码) str2（页数）str3（1:网格点2:巡检人员3:班次） str4（id） str5（1:月检2：年检）str6（月检：2017-09 年检：2017）
	 */
	public String Tjfxxjjl(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else if("".equals(str3)){
			map.put("status", "异常");
			map.put("result", "异常,统计类型为空");
		}else if("".equals(str5)){
			map.put("status", "异常");
			map.put("result", "异常,巡检类型为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if("1".equals(str3)){
				tmap.put("xjdid",str4);
			}else if("2".equals(str3)){
				tmap.put("xjryid",str4);
			}else if("3".equals(str3)){
				tmap.put("bcid",str4);
			}
			if("1".equals(str5)){
				tmap.put("yf",str6);
			}else if("2".equals(str5)){
				tmap.put("nf",str6);
			}
			map.put("data", JsonMapper.getInstance().toJson(wghglWgyxjjlService.dataGridForApp(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格随手拍提交
	 * str1
	 */
	public String Ssp(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data", "操作成功");
			YHPC_CheckHiddenInfoEntity ych = (YHPC_CheckHiddenInfoEntity)JsonMapper.fromJsonString(str1, YHPC_CheckHiddenInfoEntity.class);
			Timestamp t = DateUtils.getSysTimestamp();
			ych.setCreatetime(t);
			yhpcSspService.addssp(ych);
		}catch(Exception e){
			map.put("status", "异常");
			map.put("result", "异常");
			map.put("data", "网络异常!" + e.getMessage());
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格隐患处理list
	 * str1（页码）、str2（每页个数）、str3（企业名关键字）、str4（userid）
	 */
	public String Wgyhcl(String str1,String str2,String str3,String str4){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			if(!"".equals(str3)){
				tmap.put("qyname", str3);
			}
			tmap.put("dangerstatus", "1");
			tmap.put("useridapp",str4);
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(wghglWgyxjjlzgService.dataGridForApp(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格隐患处理详情
	 * str1 隐患id
	 */
	public String Wgyhclxq(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,隐患记录id为空");
		}else{
			tmap.put("yhjlid", str1);
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data",JsonMapper.getInstance().toJson(wghglWgyxjjlzgService.zglistForApp(tmap)));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格隐患审核
	 * str1
	 */
	public String Wgyhsh(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data", "操作成功");
			//向整改复查表里添加一条记录
			Timestamp t = DateUtils.getSysTimestamp();
			YHPC_HandleRecordEntity ycr = (YHPC_HandleRecordEntity)JsonMapper.fromJsonString(str1, YHPC_HandleRecordEntity.class);
			ycr.setHandletime(t);
			wghglWgyxjjlzgService.addwgzgjl(ycr);
			//修改隐患记录主表状态
			if(ycr.getHandlestatus()==1){
				wghglWgyxjjlzgService.updateDangerstatus(ycr.getDangerid(),"2");
			}else{
				wghglWgyxjjlzgService.updateDangerstatus(ycr.getDangerid(),"3");
			}
		}catch(Exception e){
			map.put("status", "异常");
			map.put("result", "异常");
			map.put("data", "网络异常!" + e.getMessage());
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格二维码扫描信息
	 * str1（二维码）、str2（xzqy）
	 */
	public String Wgewmxx(String str1,String str2){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,二维码为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			List<Map<String, Object>> wgdlist = wghglWgdService.findInforByewmForApp(str1);
			if(wgdlist.size()>1){
				map.put("status", "异常");
				map.put("result", "异常,绑定二维码重复,请及时与运维人员联系！");
			}else if(wgdlist.size()<1){
				map.put("status", "异常");
				map.put("result", "异常,二维码无效,请及时与运维人员联系！");
			}else{
				Map<String,Object> wgdmap = wgdlist.get(0);
				if(wgdmap.get("xzqy").toString().equals(str2)){
					map.put("status", "正常");
					map.put("result", "成功");
					tmap.put("wgdxx",wgdmap);
					tmap.put("newxjjl", wghglWgyxjjlService.getnewXjjlForApp(Long.parseLong(wgdmap.get("id").toString())));
					map.put("data",tmap);	
				}else{
					map.put("status", "异常");
					map.put("result", "异常,该网格点不在您的直属管辖点中,如发现隐患,请使用随手拍！");
				}
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 查看网格员的班次
	 * str1(xzqy)
	 */
	public String Wgbcbyxzqy(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("rows", wghglXjszService.wgybcForApp(str1));
			map.put("data",JsonMapper.getInstance().toJson(tmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格点巡检内容
	 * str1(wgdid网格点id)
	 */
	public String Wgdxjnr(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,wgdid为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("rows", wghglWgdService.wgdxjnrForApp(Long.parseLong(str1)));
			map.put("data",JsonMapper.getInstance().toJson(tmap));	
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格点巡检提交
	 */
	public String Wgxjtj(String str1,String str2){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data", "操作成功");
			Timestamp t = DateUtils.getSysTimestamp();
			//添加巡检记录
			YHPC_CheckResultEntity ycr = (YHPC_CheckResultEntity)JsonMapper.fromJsonString(str1, YHPC_CheckResultEntity.class);
			ycr.setCheckpointtype("2");
			ycr.setCreatetime(t);
			wghglWgyxjjlService.addycrForApp(ycr);
			
			//添加隐患记录
			if(!com.cczu.util.common.StringUtils.isEmpty(str2)){
				JsonMapper jsonMapper = new JsonMapper();
				JavaType javatype = jsonMapper.createCollectionType(ArrayList.class,YHPC_CheckHiddenInfoEntity.class);
				List<YHPC_CheckHiddenInfoEntity> ychlist = jsonMapper.fromJson(str2, javatype);
				if(ychlist.size()>0){
					for (YHPC_CheckHiddenInfoEntity ych : ychlist) {
						ych.setCheckresult_id(ycr.getID());
						ych.setCreatetime(t);
						ych.setCheckpointtype((long) 2);
						yhpcSspService.addssp(ych);
					}
				}
			}
		}catch(Exception e){
			map.put("status", "异常");
			map.put("result", "异常");
			map.put("data", "网络异常!" + e.getMessage());
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 网格rfid扫描信息
	 * str1（rfid）、str2（xzqy）
	 */
	public String Wgrfidxx(String str1,String str2){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		System.out.println(str1);
		System.out.println(str2);
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,rfid为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,xzqy为空");
		}else{
			List<Map<String, Object>> wgdlist = wghglWgdService.findInforByrfidForApp(str1);
			if(wgdlist.size()>1){
				map.put("status", "异常");
				map.put("result", "异常,绑定rfid重复,请及时与运维人员联系！");
			}else if(wgdlist.size()<1){
				map.put("status", "异常");
				map.put("result", "异常,rfid无效,请及时与运维人员联系！");
			}else{
				Map<String,Object> wgdmap = wgdlist.get(0);
				if(wgdmap.get("xzqy").toString().equals(str2)){
					map.put("status", "正常");
					map.put("result", "成功");
					tmap.put("wgdxx",wgdmap);
					tmap.put("newxjjl", wghglWgyxjjlService.getnewXjjlForApp(Long.parseLong(wgdmap.get("id").toString())));
					map.put("data",tmap);	
				}else{
					map.put("status", "异常");
					map.put("result", "异常,该网格点不在您的直属管辖点中,如发现隐患,请使用随手拍！");
				}
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/***********************************************风险管控************************************************/
	
	@Autowired
	private FxgkTjfxService fxgkTjfxService;
	@Resource
	private ICommDictDao icommdicdao;
	@Resource
	private ITic_AccidentHandleDao accidentHandleDao;
	
	/**
	 * 风险点信息
	 * str1 （页码）、str2（每页个数）、str3（企业id）、str4（风险点名称关键字）、str5（1红2橙3黄4蓝）、str6（行政区域）、str7（监管分类）、str8（企业名关键字）
	 */
	public String Fxdxx(String str1,String str2,String str3,String str4,String str5,String str6,String str7,String str8){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str3)&&"".equals(str6)){
				tmap.put("qyid",str3);
			}else if("".equals(str3)&&!"".equals(str6)){
				tmap.put("xzqy", str6);
				if(!"".equals(str7)&&!"0".equals(str7)&&str7!=null){
					tmap.put("jglx",str7);
				}
				if(!"".equals(str8)){
					tmap.put("qyname",str8);
				}
			}
			if(!"".equals(str4)){
				tmap.put("m1",str4);
			}
			if(!"".equals(str5)){
				tmap.put("fxfj",str5);
			}
			map.put("data", JsonMapper.getInstance().toJson(sxgkfxxxService.dataGridForApp(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 按风险分类
	 * str1（xzqy）、str2（jglx）、str3（qyid企业端用安监端传空）
	 */
	public String Fxdtjt1(String str1, String str2, String str3) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("type","fxfl");
		List<Dict> fllist=icommdicdao.dataList(mapData);
		List<String> xlist = new ArrayList<>();
		for (Dict dict : fllist) {
			xlist.add(dict.getLabel());
		}
		map.put("status", "正常");
		map.put("result", "成功");
		if(!"".equals(str1)&&"".equals(str3)){
			tmap.put("xzqy", str1);
			if(!"".equals(str2)&&!"0".equals(str2)&&str2!=null){
				tmap.put("jglx",str2);
			}
		}else if("".equals(str1)&&!"".equals(str3)){
			tmap.put("qyid",str3);
		}else{
			map.put("status", "异常");
			map.put("result", "异常");
			return JsonMapper.getInstance().toJson(map);
		}
		Map<String, Object> datamap = new HashMap<String, Object>();
		for(int i=1; i<=4; i++){
			tmap.put("m9", i);
			List<Map<String,Object>> fl = fxgkTjfxService.FXDjFLSer(tmap);
			List<Integer> list = new ArrayList<>();
			for(int j=0; j<xlist.size(); j++){
				list.add(0);
				for (Map<String, Object> map2 : fl) {
					if(map2.get("fenlei").toString().equals(xlist.get(j))){
						list.add(j,(Integer) map2.get("num"));
					}
				}
			}
			datamap.put(""+i, list);
		}
		datamap.put("x", xlist);
		map.put("data",JsonMapper.getInstance().toJson(datamap));	
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 按分险等级
	 * str1（xzqy）、str2（jglx）、str3（qyid企业端用安监端传空）
	 */
	public String Fxdtjt2(String str1, String str2, String str3) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		map.put("status", "正常");
		map.put("result", "成功");
		if(!"".equals(str1)&&"".equals(str3)){
			tmap.put("xzqy", str1);
			if(!"".equals(str2)&&!"0".equals(str2)&&str2!=null){
				tmap.put("jglx",str2);
			}
		}else if("".equals(str1)&&!"".equals(str3)){
			tmap.put("qyid",str3);
		}else{
			map.put("status", "异常");
			map.put("result", "异常");
			return JsonMapper.getInstance().toJson(map);
		}
		Map<String, Object> yanse = new HashMap<String, Object>();
		tmap.put("m9", "1");
		int hong = fxgkTjfxService.getFXDCountSer(tmap);
		yanse.put("hong", hong);
		tmap.put("m9", "2");
		int cheng = fxgkTjfxService.getFXDCountSer(tmap);
		yanse.put("cheng", cheng);
		tmap.put("m9", "3");
		int huang = fxgkTjfxService.getFXDCountSer(tmap);
		yanse.put("huang", huang);
		tmap.put("m9", "4");
		int lan = fxgkTjfxService.getFXDCountSer(tmap);
		yanse.put("lan", lan);
		map.put("data",JsonMapper.getInstance().toJson(yanse));	
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 按易发事故
	 * str1（xzqy）、str2（jglx）、str3（qyid企业端用安监端传空）
	 */
	public String Fxdtjt3(String str1, String str2, String str3) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		List<Tdic_AccidentHandle> sglist=accidentHandleDao.findAllList();
		List<String> xlist = new ArrayList<>();
		for (Tdic_AccidentHandle tdic_AccidentHandle : sglist) {
			xlist.add(tdic_AccidentHandle.getM1());
		}
		map.put("status", "正常");
		map.put("result", "成功");
		if(!"".equals(str1)&&"".equals(str3)){
			tmap.put("xzqy", str1);
			if(!"".equals(str2)&&!"0".equals(str2)&&str2!=null){
				tmap.put("jglx",str2);
			}
		}else if("".equals(str1)&&!"".equals(str3)){
			tmap.put("qyid",str3);
		}else{
			map.put("status", "异常");
			map.put("result", "异常");
			return JsonMapper.getInstance().toJson(map);
		}
		Map<String, Object> datamap = new HashMap<String, Object>();
		
		List<Map<String,Object>> fl = fxgkTjfxService.FXDjSgSer(tmap);
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		List<Integer> list3 = new ArrayList<>();
		List<Integer> list4 = new ArrayList<>();
		for (int j = 0; j < xlist.size(); j++) {
			Integer num1=0,num2=0,num3=0,num4=0;
			list1.add(j,0);list2.add(j,0);list3.add(j,0);list4.add(j,0);
			for (int i = 0; i < fl.size(); i++) {
				Map<String,Object> map2 = (Map<String,Object>) fl.get(i);
				String shigu1=xlist.get(j);
				if ((map2.get("shigu").toString()).indexOf(shigu1)!=-1) {
					if(map2.get("yanse").toString().equals("1")){
						num1=num1+Integer.parseInt(map2.get("num").toString());
						list1.add(j,num1);
					}
					if(map2.get("yanse").toString().equals("2")){
						num2=num2+Integer.parseInt(map2.get("num").toString());
						list2.add(j,num2);
					}
					if(map2.get("yanse").toString().equals("3")){
						num3=num3+Integer.parseInt(map2.get("num").toString());
						list3.add(j,num3);
					}
					if(map2.get("yanse").toString().equals("4")){
						num4=num4+Integer.parseInt(map2.get("num").toString());
						list4.add(j,num4);
					}
				}
			}
		}
		datamap.put("1", list1);
		datamap.put("2", list2);
		datamap.put("3", list3);
		datamap.put("4", list4);
		datamap.put("x", xlist);
		map.put("data",JsonMapper.getInstance().toJson(datamap));
		return JsonMapper.getInstance().toJson(map);
	}
	
	
	
	/***********************************************安全生产执法************************************************/
	
	@Autowired
	private IAqzfJcjhService AqzfJcjhService;
	@Autowired
	private AqzfZfryService aqzfZfryService;
	@Autowired
	private IAqzfJcbkService AqzfJcbkService;
	@Autowired
	private IAqzfJcdyService AqzfJcdyService;
	@Autowired
	private AqzfFcyjService aqzfFcyjService;
	@Autowired
	private AqzfZlzgService aqzfZlzgService;
	@Autowired
	private AqzfClcsService aqzfClcsService;
	@Autowired
	private AqzfJcfaService aqzfJcfaService;
	@Autowired
	private AqzfJcjlService aqzfJcjlService;
	@Resource
	private AqzfJcjlDao aqzfJcjlDao;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	
	/**
	 * 检查计划
	 * str1 （页码）、str2（每页个数）、str3（企业关键字）、str4（年）、str5（月）、str6（行政区域）、str7（监管类型）
	 */
	public String Aqzfjcjh(String str1,String str2,String str3,String str4,String str5,String str6,String str7){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str6)){
				tmap.put("xzqy", str6);
				if(!"".equals(str7)&&!"0".equals(str7)&&str7!=null){
					tmap.put("jglx",str7);
				}
			}
			if(!"".equals(str3)){
				tmap.put("qyname",str3);
			}
			if(!"".equals(str4)){
				tmap.put("year",str4);
			}
			if(!"".equals(str5)){
				tmap.put("month",str5+"月");
			}
			map.put("data", JsonMapper.getInstance().toJson(AqzfJcjhService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 检查方案
	 * str1 （页码）、str2（每页个数）、str3（企业关键字）、str4（年）、str5（月）、str6（操作类型0：未添加检查1：已检查2：已添加责令整改3：已添加复查意见）、str7（行政区域）、str8（监管类型）
	 */
	public String Aqzfjcfa(String str1,String str2,String str3,String str4,String str5,String str6,String str7,String str8){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str7)){
				tmap.put("xzqy", str7);
				if(!"".equals(str8)&&!"0".equals(str8)&&str8!=null){
					tmap.put("jglx",str8);
				}
			}
			if(!"".equals(str3)){
				tmap.put("qyname",str3);
			}
			if(!"".equals(str4)){
				tmap.put("year",str4);
			}
			if(!"".equals(str5)){
				tmap.put("month",str5+"月");
			}
			if(!"".equals(str6)){
				tmap.put("cz",str6);
			}
			map.put("data", JsonMapper.getInstance().toJson(aqzfJcfaService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 检查方案详情
	 * str1（检查方案id）
	 */
	public String Aqzfjcfaxq(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,检查方案id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data", JsonMapper.getInstance().toJson(aqzfJcfaService.getJcnrForApp(Long.parseLong(str1))));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 检查记录
	 * str1 （页码）、str2（每页个数）、str3（企业关键字）、str4（检查起始时间）、str5（检查结束时间）、str6（行政区域）、str7（监管类型）
	 */
	public String Aqzfjcjl(String str1,String str2,String str3,String str4,String str5,String str6,String str7){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str6)){
				tmap.put("xzqy", str6);
				if(!"".equals(str7)&&!"0".equals(str7)&&str7!=null){
					tmap.put("jglx",str7);
				}
			}
			if(!"".equals(str3)){
				tmap.put("qyname",str3);
			}
			if(!"".equals(str4)){
				tmap.put("M6",str4);
			}
			if(!"".equals(str5)){
				tmap.put("M7",str5);
			}
			map.put("data", JsonMapper.getInstance().toJson(aqzfJcjlService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 检查记录详情
	 * str1（检查记录id）
	 */
	public String Aqzfjcjlxq(String str1){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,检查记录id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			List<Map<String,Object>> list=aqzfJcjlDao.dataGridNr2(str1);
			List<Map<String, Object>> czwt = aqzfJcjlService.dataGridCzwt(Long.parseLong(str1));
			tmap.put("czwt", czwt);
			tmap.put("jcnr",list);
			map.put("data", JsonMapper.getInstance().toJson(tmap));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 现场处理措施
	 * str1 （页码）、str2（每页个数）、str3（企业关键字）、str4（检查日期 如：2017-09-09）、str5（行政区域）、str6（监管类型）
	 */
	public String Aqzfclcs(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str5)){
				tmap.put("xzqy", str5);
				if(!"".equals(str6)&&!"0".equals(str6)&&str6!=null){
					tmap.put("jglx",str6);
				}
			}
			if(!"".equals(str3)){
				tmap.put("qyname",str3);
			}
			if(!"".equals(str4)){
				tmap.put("M1",str4);
			}
			map.put("data", JsonMapper.getInstance().toJson(aqzfClcsService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 责令限期整改
	 * str1 （页码）、str2（每页个数）、str3（企业关键字）、str4（整改完毕时间 如：2017-09-09）、str5（行政区域）、str6（监管类型）
	 */
	public String Aqzfzlzg(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str5)){
				tmap.put("xzqy", str5);
				if(!"".equals(str6)&&!"0".equals(str6)&&str6!=null){
					tmap.put("jglx",str6);
				}
			}
			if(!"".equals(str3)){
				tmap.put("qyname",str3);
			}
			if(!"".equals(str4)){
				tmap.put("M3",str4);
			}
			map.put("data", JsonMapper.getInstance().toJson(aqzfZlzgService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 整改复查意见
	 * str1 （页码）、str2（每页个数）、str3（企业关键字）、str4（整改完毕时间 如：2017-09-09）、str5（行政区域）、str6（监管类型）
	 */
	public String Aqzffcyj(String str1,String str2,String str3,String str4,String str5,String str6){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str5)){
				tmap.put("xzqy", str5);
				if(!"".equals(str6)&&!"0".equals(str6)&&str6!=null){
					tmap.put("jglx",str6);
				}
			}
			if(!"".equals(str3)){
				tmap.put("qyname",str3);
			}
			if(!"".equals(str4)){
				tmap.put("M1",str4);
			}
			map.put("data", JsonMapper.getInstance().toJson(aqzfFcyjService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 检查表库
	 * str1 （页码）、str2（每页个数）、str3（检查单元）、str4（检查内容关键字）
	 */
	public String Aqzfjcbk(String str1,String str2,String str3,String str4){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str3)){
				tmap.put("jcdy",str3);
			}
			if(!"".equals(str4)){
				tmap.put("jcnr",str4);
			}
			map.put("data", JsonMapper.getInstance().toJson(AqzfJcbkService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 检查单元
	 */
	public String Aqzfjcdy(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", JsonMapper.getInstance().toJson(AqzfJcdyService.getjcdylist()));
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 执法人员
	 * str1 （页码）、str2（每页个数）、str3（姓名关键字）、str4（行政区域）、str5（监管类型）
	 */
	public String Aqzfzfry(String str1,String str2,String str3,String str4,String str5){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if("".equals(str1)){
			map.put("status", "异常");
			map.put("result", "异常,page为空");
		}else if("".equals(str2)){
			map.put("status", "异常");
			map.put("result", "异常,pageSize为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo",str1);
			tmap.put("pageSize",str2);
			if(!"".equals(str4)){
				tmap.put("xzqy", str4);
				if(!"".equals(str5)&&!"0".equals(str5)&&str5!=null){
					tmap.put("jglx",str5);
				}
			}
			if(!"".equals(str3)){
				tmap.put("xm",str3);
			}
			map.put("data", JsonMapper.getInstance().toJson(aqzfZfryService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	/**
	 * 文书导出
	 * @param str1(类型1：检查方案2：检查记录3：现场处理措施4：责令限期整改5：整改复查意见)
	 * @param str2 id
	 * @return
	 */
	public String Aqzfwsdc(String str1,String str2){
		Map<String, Object> tmap = new HashMap<String, Object>();
		HttpServletRequest request = (HttpServletRequest) webServiceContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";//设置导出的文件名
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";//设置模板路径
		if("1".equals(str1)){
			Map<String, Object> map = aqzfJcfaService.getAjWord(Long.parseLong(str2));
			WordUtil.ireportWord(map, "xcjcfa.ftl", filePath, filename, request);
		}else if("2".equals(str1)){
			Map<String,Object> map = aqzfJcjlService.getAjWord(Long.parseLong(str2));
			WordUtil.ireportWord(map, "xcjcjl.ftl", filePath, filename, request);
		}else if("3".equals(str1)){
			Map<String, Object> map = aqzfClcsService.getAjWord(Long.parseLong(str2));
			WordUtil.ireportWord(map, "clcsjds.ftl", filePath, filename, request);
		}else if("4".equals(str1)){
			Map<String, Object> map = aqzfZlzgService.getAjWord(Long.parseLong(str2));
			WordUtil.ireportWord(map, "zlzgzls.ftl", filePath, filename, request);
		}else if("5".equals(str1)){
			Map<String, Object> map = aqzfFcyjService.getAjWord(Long.parseLong(str2));
			WordUtil.ireportWord(map, "zgfcyj.ftl", filePath, filename, request);
		}
		tmap.put("wsdz","/download/" + filename);
		return JsonMapper.getInstance().toJson(tmap);
	}
}
