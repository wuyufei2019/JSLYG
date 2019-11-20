package com.cczu.webservice.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.cczu.model.dao.IAqpxKcxxDao;
import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_ExamresultsEntity;
import com.cczu.model.entity.AQPX_ItembankEntity;
import com.cczu.model.entity.AQPX_PlanEntity;
import com.cczu.model.entity.AQPX_TestguizeEntity;
import com.cczu.model.entity.AQPX_TestpaperEntity;
import com.cczu.model.service.AqpxSjjyHistoryService;
import com.cczu.model.service.AqpxWlfpxHistoryService;
import com.cczu.model.service.IAqpxGzxxService;
import com.cczu.model.service.IAqpxJhxxService;
import com.cczu.model.service.IAqpxKCxxService;
import com.cczu.model.service.IAqpxKscjService;
import com.cczu.model.service.IAqpxKsjlService;
import com.cczu.model.service.IAqpxStkxxService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.webservice.AqpxService;

@WebService(endpointInterface = "com.cczu.webservice.AqpxService")
public class AqpxServiceImpl implements AqpxService {

	@Autowired
	private UserService userService;//用户service
	@Autowired
	private IAqpxJhxxService aqpxjhxxService;//培训计划service
	@Autowired
	private IAqpxKCxxService aqpxKCxxService;//课程信息service
	@Autowired
	private IAqpxStkxxService aqpxStkxxService;//试题库service
	@Autowired
	private IAqpxGzxxService aqpxGzxxService;//出题规则service
	@Autowired
	private IAqpxKsjlService aqpxKsjlService;//考试记录service
	@Autowired
	private IAqpxKscjService aqpxKscjService;//考试成绩service
	@Autowired
	private AqpxSjjyHistoryService sjjyHistoryService;//三级教育记录Service
	@Autowired
	private AqpxWlfpxHistoryService wlfpxHistoryService;//外来方培训记录Service
	@Autowired
	private IAqpxKcxxDao aqpxKcxxDao;//课程信息dao
	
	/**
	 * 日常培训考试页面
	 * str1：用户id
	 */
	@Override
	public String DaliyExam(String str1) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,人员id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");
			User user=userService.getUserById(Integer.parseInt(str1));
			List<AQPX_PlanEntity> plan=aqpxjhxxService.findInfoByBmid(user.getId2(), user.getDepartmen());
			List<Map<String, Object>> list=new ArrayList<>();
			if(plan!=null){
				for(AQPX_PlanEntity jh:plan){
					List<Map<String, Object>> temp= aqpxKcxxDao.getlistByKcids2ForApp(jh.getID2(),jh.getM1(),jh.getID(),jh.getM5().toString(),jh.getM6().toString());
					list.addAll(temp);
				}
				map.put("data", JsonMapper.getInstance().toJson(list));
			}
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 三级安全培训考试页面
	 * str1：企业id
	 */
	@Override
	public String SjjyExam(String str1) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,人员id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");
			tmap.put("qyid", str1);
			tmap.put("type", 3);//外来方培训课程
			map.put("data", JsonMapper.getInstance().toJson(aqpxKcxxDao.findByContentForApp(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 外来方培训考试页面
	 * str1：企业id
	 */
	@Override
	public String WlfExam(String str1) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,人员id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");
			tmap.put("qyid", str1);
			tmap.put("type", 2);//三级安全教育培训课程
			map.put("data", JsonMapper.getInstance().toJson(aqpxKcxxDao.findByContentForApp(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 随机生成试卷
	 * str1：企业id  str2:课程id
	 */
	@Override
	public String showKJList(String str1, String str2) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,企业id为空");
		}
		if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,课程id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "正常");
			AQPX_TestguizeEntity stgz=aqpxGzxxService.findkc(Long.parseLong(str1), Long.parseLong(str2));
			AQPX_CourseEntity ac = aqpxKCxxService.findbyid(Long.parseLong(str2)); 
			map.put("data", JsonMapper.getInstance().toJson(aqpxStkxxService.getSjByGz(Long.parseLong(str2), stgz.getM1(), stgz.getM2(), stgz.getM3(), stgz.getM4())));
			map.put("kcid", Long.parseLong(str2));//课程id
			map.put("stgz", stgz);//试题规则
			map.put("kcname", ac.getM1());//课程名
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 提交考试记录
	 * str1：课程id  str2:题目类型及答案
	 */
	@Override
	public String submitKS(String str1, String str2) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,企业id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			int score=0;//总分
			String timecount="";//考试时间
			String planid="";//计划id
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			AQPX_TestguizeEntity stgz=aqpxGzxxService.findkc(sessionuser.getQyid(), Long.parseLong(str1));
			
			AQPX_TestpaperEntity st=new AQPX_TestpaperEntity();
			st.setID1(sessionuser.getQyid());
			st.setID3((long)sessionuser.getId());
			st.setID4(stgz.getID());
			st.setID5(Long.parseLong(str1));
			st.setM3(new java.sql.Date(new Date().getTime()));
			String bs=DateUtils.getDateRandom();//试卷标识
			st.setH(bs);
			//获取所有试题结果 并保存
			Map<String, String[]> stmap=(Map<String, String[]>)JsonMapper.fromJsonString(str2, Map.class);
			for(Map.Entry<String, String[]> entry:stmap.entrySet()){  
				String key=entry.getKey();
				String values[]=entry.getValue();
				if(key.equals("time"))
					timecount=values[0];
				if(key.equals("planid"))
					planid=values[0];
				if(key.indexOf("dx_")==0||key.indexOf("tk_")==0||key.indexOf("pd_")==0){
					String id=key.substring(3).trim();
					String val =values[0];
					st.setID2(Long.parseLong(id));
					st.setM2(val);
					AQPX_ItembankEntity itembankEntity=aqpxStkxxService.findByid(Long.parseLong(id));
					if(itembankEntity.getM8().equalsIgnoreCase(val)){//判断答案是否正确 不分大小写
						switch (itembankEntity.getM1()) { //根据题目类型和出题规则打分
						case "1":st.setM1(stgz.getM5()); score+=stgz.getM5(); break;//单选
						case "3":st.setM1(stgz.getM7()); score+=stgz.getM7(); break;//填空
						case "4":st.setM1(stgz.getM8()); score+=stgz.getM8(); break;//判断
						}
					}
					aqpxKsjlService.addInfo(st);
				}else if(key.indexOf("dsx_")==0){
					String id=key.substring(4).trim();
					st.setID2(Long.parseLong(id));
					st.setM2(StringUtils.join(values));
					AQPX_ItembankEntity itembankEntity=aqpxStkxxService.findByid(Long.parseLong(id));
					if(itembankEntity.getM8().equalsIgnoreCase(StringUtils.join(values))){//判断多选答案是否正确 不分大小写
						st.setM1(stgz.getM6()); score+=stgz.getM6();
					}
					aqpxKsjlService.addInfo(st);
				}  
				
			} 
			AQPX_ExamresultsEntity examresultsEntity =new AQPX_ExamresultsEntity();
			examresultsEntity.setID1(sessionuser.getQyid());
			examresultsEntity.setID2((long)sessionuser.getId());
			examresultsEntity.setID3(Long.parseLong(str1));
			if(!StringUtils.isEmpty(planid))
				examresultsEntity.setID4(Long.parseLong(planid));
			examresultsEntity.setH(bs);
			examresultsEntity.setM1(score);
			examresultsEntity.setM2(timecount);
			if(score>=stgz.getM9()){
				examresultsEntity.setM3("合格");
			}else{
				examresultsEntity.setM3("不合格");
			}
			aqpxKscjService.addInfo(examresultsEntity);
			
			AQPX_CourseEntity kc=aqpxKCxxService.findbyid(Long.parseLong(str1));
			//三级教育考试记录
			if(kc.getM5().equals("2"))
				sjjyHistoryService.CreateHistoryByOnlineTest(sessionuser.getId(), examresultsEntity,kc);
			//外来方考试记录
			if(kc.getM5().equals("3"))
//				wlfpxHistoryService.CreateHistoryByOnlineTest(examresultsEntity,request);
			return "本次考试成绩："+score+" 分";
			map.put("h", examresultsEntity.getH());
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/*
	 * 查看考试试卷记录
	 * str1：试卷标识
	 */
	@Override
	public String viewSJ(String str1) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,试卷标识为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data", JsonMapper.getInstance().toJson(aqpxKsjlService.getsjxx(str1)));
		}
		return JsonMapper.getInstance().toJson(map);
	}

}
