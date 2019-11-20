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
 * @ClassName: XZCF_JYCFCssbEntity
 * @Description: 行政处罚-简易处罚-处罚告知
 * @author who
 * @date 2018年1月22日
 *
 */
@Entity
@Table(name="xzcf_jycfinfo")
public class XZCF_JycfInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//企业id
	
	@Column(name = "M0", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M0;//编号
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//当事人（保存）
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M2;//查实行为
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M3;//证据
	
	@Column(name = "M3_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M3_1;//证据照片
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4;//违法行为id集合
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M5;//违法条款
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M6;//处罚依据
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M7;//行政处罚
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M8;//联系人
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M9;//联系电话
	
	@Column(name = "M10", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M10;//告知时间
	
	@Column(name = "userid", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String userid;//创建人id
	
	@Column(name = "csflag", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String csflag;//陈述申辩（0：未1：已生成）
	
	@Column(name = "jdflag", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String jdflag;//处罚决定（0：未1：已生成）
}
