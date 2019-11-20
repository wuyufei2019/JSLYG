package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName: Bis_InfoChange
 * @Description: 企业变更
 * @author 
 * @date 2018年06月05日
 *
 */
@Entity
@Table(name = "bis_infochange")
public class Bis_InfoChange extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
   
	@Column(name = "qyid", nullable = true,columnDefinition="bigint")
	@Setter
	@Getter
	private Long qyid;// 企业id
   
	@Column(name = "modification", nullable = true,columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String modification;// 变更事项
   
	@Column(name = "content", nullable = true,columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String content;// 变更内容
	
	@Column(name = "bisnewjson", nullable = true,columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String bisnewjson;// 变更后的企业json

	@Column(name = "bisoldjson", nullable = true,columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String bisoldjson;// 变更前的企业json

	@Column(name = "applyer", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String applyer;// 申请人
   
	@Column(name = "reviewer", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String reviewer;// 审核人
	
	@Column(name = "result", nullable = true,columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String result;// 审核结果  null未审核 1通过-0未通过
   
}
