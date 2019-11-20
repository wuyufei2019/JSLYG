package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_TankEntity
 * @Description: 企业基本信息-储罐
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_tank")
public class BIS_TankEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1106837690821192296L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID1;//企业编号

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//储罐名称
	
	@Column(name = "M2", nullable = true, length = 20)
	@Setter
	@Getter
	private String M2;//储罐类型
	
	@Column(name = "M3", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M3;//容积
	
	@Column(name = "M4", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M4;//材质
	
	@Column(name = "M5", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M5;//数量
	
	@Column(name = "M6", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M6;//火灾危险性等级
	
	@Column(name = "M7", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M7;//物料名称

	@Column(name = "M8", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M8;//CAS号
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M9;//位号
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M10;//罐径
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M11;//罐高
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M12;//储罐区面积
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String M13;//有无防火堤 1：有，0：无
	
	@Column(name = "M14", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M14;//防火堤所围面积
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M15;//现场照片
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M16;//图纸
	
	@Column(name = "ID2", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID2;//采集实时容量的设备通道id（TS_DeviceChanel id）
	
	@Column(name = "ID3", nullable = true, length = 8)
	@Setter
	@Getter
	private String ID3;//危化品类别
	
	@Column(name = "level1", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double level1;//高液位预警
	
	@Column(name = "level2", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double level2;//低液位预警
	
	@Column(name = "temperature1", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double temperature1;//高温度预警
	
	@Column(name = "temperature2", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double temperature2;//低温度预警
	
	@Column(name = "pressure1", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double pressure1;//高压力预警
	
	@Column(name = "pressure2", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double pressure2;//低压力预警
	
	@Column(name = "r1", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r1;//液位点号
	
	@Column(name = "r2", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r2;//温度点号
	
	@Column(name = "r3", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r3;//压力点号
	
	@Column(name = "r4", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r4;//流量点号
}
