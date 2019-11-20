package com.cczu.aqzf.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: XZCF_CfjdInfoEntity
 * @Description: 行政处罚-一般处罚-处罚决定
 * @author who
 * @date 2017年7月29日
 *
 */
@Entity
@Table(name="xzcf_cfjd")
public class XZCF_CfjdInfoEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long id1;//一般处罚字段  对应立案审批的id

	@Column(name = "punishdate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp punishdate;//开始时间
	
	@Column(name = "percomflag", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String percomflag;//企业、个人标志位 0：个人 1：企业

	//-------企业个人共有字段
	@Column(name = "punishname", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String punishname;//受处单位或者个人名称
	
	
	@Column(name = "mobile", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String mobile;//受处单位或者个人联系电话
	
	@Column(name = "address", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String address;//受处单位或者个人联系地址    企业对应企业地址   个人对应个人家庭住址
	
	@Column(name = "duty", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String duty;//受处单位法人或者个人职务    企业对应法人职务   个人对应个人职务
	//-------
	
	//-------企业字段
	@Column(name = "ybcode", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String ybcode;//邮编
	
	
	@Column(name = "legal", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String legal;//法人
	//-------
	
	//-------个人字段
	@Column(name = "sex", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String sex;//性别
	
	@Column(name = "age", nullable = true, columnDefinition="int")
	@Setter
	@Getter	
	private Integer age;//性别
	
	@Column(name = "identity1", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String identity1;//身份证
	
	@Column(name = "dwname", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String dwname;//单位名称
	
	@Column(name = "dwaddress", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String dwaddress;//单位地址
	
	//-------
	
	@Column(name = "illegalactandevidence", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String illegalactandevidence;//违法事实和证据
	
	@Column(name = "unlaw", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String unlaw;//违反的法律
	
	@Column(name = "enlaw", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String enlaw;//依据的法律
	@Column(name = "number", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String number;//编号
	
	@Column(name = "punishresult", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String punishresult;//处罚结果
	
	@Column(name = "wfxw", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String wfxw;//违法行为
	
	@Column(name = "evidence", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String evidence;//证据
	
	@Column(name = "xzcf", nullable = true, columnDefinition = "varchar(500)")
	@Setter
	@Getter
	private String xzcf;// 行政处罚
}
