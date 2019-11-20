package com.cczu.aqzf.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 安全生产执法--处罚审批
 * @author zpc
 * @date 2017年08月04日
 */
@Entity
@Table(name="xzcf_cfsp")
public class XZCF_CFSPEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5117595642022874311L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//操作人ID
	

	@Column(name = "ID3", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID3;//立案审批ID

	@Column(name = "dsperson", nullable = true, columnDefinition = "varchar(100)")
	@Setter
	@Getter
	private String dsperson;// （为了导出方便）当事人（企业名称）

	@Column(name = "casename", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String casename;// 案件名称

	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//立案审批编号
	
	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M2;//时间

	@Column(name = "M3", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M3;//法定代表人


	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M4;//公司地址
	
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M5;//案件来源
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M6;//案件经办人

	@Column(name = "M7", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M7;//办案部门负责人意见
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M8;//法制部门合法性审查意见

	@Column(name = "M9", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M9;//分管领导

	@Column(name = "M10", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M10;//分管领导审核意见

	@Column(name = "M11", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M11;//主要领导

	@Column(name = "M12", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M12;//主要领导审核意见
}
