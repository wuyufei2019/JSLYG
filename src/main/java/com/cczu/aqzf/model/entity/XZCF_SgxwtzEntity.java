package com.cczu.aqzf.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 安全生产执法--事故询问通知书
 * @author zpc
 * @date 2018年1月18日
 */
@Entity
@Table(name="xzcf_sgxwtz")
public class XZCF_SgxwtzEntity extends BaseEntity {

	private static final long serialVersionUID = 5117595642022874311L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//操作人ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID2;//企业ID

	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//调查主题
	
	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M2;//询问时间
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3;//询问地址

	@Column(name = "M4", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M4;//额外证件材料
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M5;//材料
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M6;//联系人
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M7;//联系电话
	
	@Column(name = "flag", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String flag;//0：未添加1：已添加
}
