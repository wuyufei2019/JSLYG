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
 * @ClassName: XZCF_YbcfDcbgEntity
 * @Description: 行政处罚-一般处罚-调查报告
 * @author who
 * @date 2017年7月29日
 * 
 */
@Entity
@Table(name = "xzcf_ybcfdcbg")
public class XZCF_YbcfDcbgEntity extends BaseEntity {
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id1", nullable = true, columnDefinition = "bigint")
	@Setter
	@Getter
	private long id1;// 立案审批id
	
	@Column(name = "id2", nullable = true, columnDefinition = "bigint")
	@Setter
	@Getter
	private long id2;// （后续可能需要，存入企业id）

	@Column(name = "casename", nullable = true, columnDefinition = "varchar(500)")
	@Setter
	@Getter
	private String casename;// 案件名称

	@Column(name = "researchresult", nullable = true, columnDefinition = "varchar(2000)")
	@Setter
	@Getter
	private String researchresult;// 检查出现的问题
	
	@Column(name = "sccids", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String sccids;//sccids(aqzf_safetycheckcontent的id集合)
	
	@Column(name = "unlaw", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String unlaw;//违反条款
	
	@Column(name = "unlawall", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String unlawall;//违反条款详情

	@Column(name = "qyname", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String qyname;//（企业名称）
	
	@Column(name = "qyaddress", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String qyaddress;// 企业地址
	
	@Column(name = "economytype", nullable = true, columnDefinition = "varchar(100)")
	@Setter
	@Getter
	private String economytype;// 经济类型
	
	@Column(name = "legalperson", nullable = true, columnDefinition = "varchar(20)")
	@Setter
	@Getter
	private String legalperson;// 法定负责人
	
	@Column(name = "qyfounddate", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp qyfounddate;// 企业成立时间
	
	@Column(name="businessscope", nullable=true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String businessscope;//经营范围
	
	@Column(name = "researchdate", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp researchdate;//调查时间

	@Column(name = "getevidence", nullable = true, columnDefinition = "varchar(2000)")
	@Setter
	@Getter
	private String getevidence;// 现场检查取证
	
	@Column(name = "xwresearch", nullable = true, columnDefinition = "varchar(2000)")
	@Setter
	@Getter
	private String xwresearch;// 询问调查
	
	@Column(name = "illactevidence", nullable = true, columnDefinition = "varchar(2000)")
	@Setter
	@Getter
	private String illactevidence;// 违法事实
	
	@Column(name = "opinion", nullable = true, columnDefinition = "varchar(2000)")
	@Setter
	@Getter
	private String opinion;// 承办人意见
	@Column(name = "enlaw", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String enlaw;//依据的法律
	
	@Column(name = "punishresult", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String punishresult;//处罚结果

	@Column(name = "enforcer1", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String enforcer1;// 调查人员一

	@Column(name = "enforcer2", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String enforcer2;// 调查人二
	
	@Column(name = "shyj", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String shyj;// 审核意见
	
	@Column(name = "shr", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String shr;// 审核人
	
	@Column(name = "shsj", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp shsj;//审核时间
	
	@Column(name = "spyj", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String spyj;// 审批意见
	
	@Column(name = "spr", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String spr;// 审批人
	
	@Column(name = "spsj", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp spsj;//审批时间
	
	@Column(name = "fzshyj", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String fzshyj;// 法制审核意见
	
	@Column(name = "fzshr", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String fzshr;// 法制审核人
	
	@Column(name = "fzshsj", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp fzshsj;//法制审核时间
	
	@Column(name = "ayname", nullable = true, columnDefinition = "varchar(500)")
	@Setter
	@Getter
	private String ayname;// 案由
	
	@Column(name = "cbjg", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String cbjg;// 承办机构
	
	@Column(name = "bgbh", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String bgbh;// 调查报告编号
	
	@Column(name = "xzcf", nullable = true, columnDefinition = "varchar(500)")
	@Setter
	@Getter
	private String xzcf;// 行政处罚

	@Column(name = "cfryname", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String cfryname;//处罚人员当事人
}
