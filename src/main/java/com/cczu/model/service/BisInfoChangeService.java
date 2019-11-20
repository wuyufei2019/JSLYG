package com.cczu.model.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.cczu.model.dao.BisInfoChangeDao;
import com.cczu.model.dao.impl.BisQyjbxxDaoImpl;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.Bis_InfoChange;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业变更Service
 * @author 
 * @date 2018年06月05日
 */
@Transactional(readOnly=true)
@Service("BisInfoChangeService")
public class BisInfoChangeService {
	
	@Resource
	private BisInfoChangeDao bisInfoChangeDao;
	@Resource
	private BisQyjbxxDaoImpl bisQyjbxxDao;

	public void addInfo(Bis_InfoChange entity,String bisnewjson) throws Exception {
		// TODO Auto-generated method stub
		ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		if (User.USERTYPE.QY.getUsertype().equals(user.getUsertype())) {
			//数据库存储的企业实体
			BIS_EnterpriseEntity oldbis = bisQyjbxxDao.findInfoById(entity.getQyid());
			//前台存储的页面实体
			BIS_EnterpriseEntity newbis = JSON.parseObject(bisnewjson, BIS_EnterpriseEntity.class,
					                      Feature.AllowISO8601DateFormat, Feature.InitStringFieldAsEmpty);
			//声明两个map存储新旧变化
			HashMap<String,Object> newmap= new HashMap<>();
			HashMap<String,Object> oldmap= new HashMap<>();
			getDifArrtibute(oldbis,newbis,newmap,oldmap);
			entity.setBisoldjson(JSON.toJSON(oldmap).toString());
			entity.setBisnewjson(JSON.toJSON(newmap).toString());
			bisInfoChangeDao.save(entity);
		}
	}

	/**
	 * 对比两个对象，将不同属性存进map
	 * @param oldbis
	 * @param newbis
	 * @param mapnew
	 * @param mapold
	 */
	private void getDifArrtibute(BIS_EnterpriseEntity oldbis, BIS_EnterpriseEntity newbis,
                                 HashMap<String, Object> mapnew, HashMap<String, Object> mapold) {

		Map<String, String> newmap = null;
		Map<String, String> oldmap = null;
		try {
			newmap = BeanUtils.describe(newbis);
			oldmap = BeanUtils.describe(oldbis);
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (newmap != null) {
				for (Map.Entry<String, String> entry : newmap.entrySet()) {
					if ((StringUtils.isBlank(oldmap.get(entry.getKey())) && StringUtils.isBlank(entry.getValue()))
							|| StringUtils.equals(oldmap.get(entry.getKey()),entry.getValue())) {
					} else {
						mapnew.put(entry.getKey(),entry.getValue());
						mapold.put(entry.getKey(),oldmap.get(entry.getKey()));
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

	}
	/*	Class clazz = oldbis.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			try {
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
				Method getMethod = pd.getReadMethod();
				Object oldo = getMethod.invoke(oldbis);
				Object  = getMethod.invoke(newbis);
				String s1 = oldo == null ? "" : oldo.toString();//避免空指针异常
				String s2 = newo == null ? "" : newo.toString();//避免空指针异常
				if (!s1.equals(s2)) {
					oldmap1.put(field.getName(), s1);
					newma1p.put(field.getName(), s2);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}*/

	}

	public long addInfoReId(Bis_InfoChange entity) {
		// TODO Auto-generated method stub
		bisInfoChangeDao.save(entity);
         return entity.getID();
	}
	
	public void updateInfo(Bis_InfoChange entity) {
		bisInfoChangeDao.save(entity);
	}
	/**
	 * 审核信息
	 * @param result 审核结果 reviewer 审核人
	 */
	public void reviewInfo(long id ,String result,String reviewer) {
		//保存企业变更审核信息
		Bis_InfoChange entity = bisInfoChangeDao.find(id);
		Timestamp t = DateUtils.getSystemTime();
		entity.setResult(result);
		entity.setReviewer(reviewer);
		entity.setS2(t);
		bisInfoChangeDao.save(entity);
		//保存企业信息
		if("1".equals(result)){
			String newjson  = entity.getBisnewjson();
			BIS_EnterpriseEntity bis = bisQyjbxxDao.findInfoById(entity.getQyid());
			try {
				BeanUtils.copyProperties(bis,JSON.parseObject(newjson,Map.class));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			bis.setS2(t);
			bisQyjbxxDao.save(bis);
		}
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		bisInfoChangeDao.deleteInfo(id);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, String>> list=bisInfoChangeDao.dataGrid(mapData);
		int getTotalCount=bisInfoChangeDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public List<Map<String, Object>> dataGridForQy(Map<String, Object> map){
		List<Map<String, Object>> list=bisInfoChangeDao.dataGridForQy(map);
		return  list;
	}

	
	public Bis_InfoChange findById(Long id) {
		// TODO Auto-generated method stub
		return bisInfoChangeDao.find(id);
	}

	public List<Bis_InfoChange> FindNotReviewListByQyid(long qyid){
		return bisInfoChangeDao.find(Restrictions.eq("S3",0),Restrictions.eq("qyid",qyid),Restrictions.isNull("result"));
	}
	
}


