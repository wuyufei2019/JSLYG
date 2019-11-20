package com.cczu.sys.system.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.CompRolePermission;
import com.cczu.sys.system.entity.Permission;

/**
 * @description 企业内部角色与权限关系DAO
 * @author jason
 * @date 2017年12月26日
 */
@Repository
public class CompRolePermissionDao extends HibernateDao<CompRolePermission, Integer>{
	
	/**
	 * 查询角色拥有的权限id
	 * @param roleId
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> findPermissionIds(Integer roleId){
		String hql="select rp.permission.id from CompRolePermission rp where rp.compRole.id=?0";
		Query query= createQuery(hql, roleId);
		return query.list();
	}

	/**
	 * 删除角色权限
	 * @param roleId
	 * @param permissionId
	 */
	public void deleteRP(Integer roleId,Integer permissionId){
		String hql="delete CompRolePermission rp where rp.compRole.id=?0 and rp.permission.id=?1";
		batchExecute(hql, roleId,permissionId);
	}
	/**
	 * 删除某一角色拥有的所有权限
	 * @param roleId
	 */
	public void deleteALlPerByRole(Integer roleId){
		String hql="delete CompRolePermission rp where rp.compRole.id=?0";
		batchExecute(hql, roleId);
	}

	
	
	
	
	
	public String getSqlContent(){
		StringBuffer sql=new StringBuffer();
		sql.append("select p.* from t_permission p ");
		sql.append("INNER JOIN t_comp_role_permission rp on p.ID=rp.PERMISSION_ID ");
		sql.append("INNER JOIN t_comp_role r ON r.id=rp.COMPROLE_ID ");
		sql.append("INNER JOIN t_comp_user_role ur ON ur.role_id =rp.COMPROLE_ID ");
		sql.append("INNER JOIN t_user u ON u.id = ur.USER_ID ");
		return sql.toString();
	}
	
	
	/**
	 * 查询企业用户拥有的企业内部角色对应权限集合
	 * @param userId
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<Permission> findPermissionByUid(Integer userId){
		String sql=getSqlContent();
		sql+= " where u.id=?0  order by p.sort" ;
		SQLQuery sqlQuery=createSQLQuery(sql,userId);
		sqlQuery.addEntity(Permission.class);
		return sqlQuery.list();
	}
	
	/**
	 * 查询企业用户拥有的企业内部角色对应菜单集合
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Permission> findMenuByUid(Integer userId){
		String sql=getSqlContent();
		sql+="where p.TYPE='F' and u.id=?0 order by p.sort";
		SQLQuery sqlQuery=createSQLQuery(sql.toString(), userId);
		sqlQuery.addEntity(Permission.class);
		return sqlQuery.list();
	}
	
}
