package com.cczu.zxjkyj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author wbth
 * @ClassName: BIS_Monitor_Point_MaintainEntity
 * @Description: 企业基本信息-监测指标基础信息
 * @date 2019年10月12日
 */
@Entity
@Table(name = "monitor_point_maintain")
public class Monitor_PointMaintainEntity {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1106837690821192296L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    public Long ID;//编号

    @Column(name = "qyid", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    public Long qyid;//企业id

    @Column(name = "targetCode",  columnDefinition = "varchar(100)")
    @Setter
    @Getter
    private String targetCode;//指标编码（设备编码(19位)+指标类型编码(2位)+3位流水号 ）

    @Column(name = "equipCode",  columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String equipCode;//设备编码

    @Column(name = "targetName",  columnDefinition = "varchar(100)")
    @Setter
    @Getter
    private String targetName;//指标名称

    @Column(name = "targetType",  columnDefinition = "varchar(6)")
    @Setter
    @Getter
    private String targetType;//指标类型，WD:温度;YL:压力;YW:液位;QT:气体

    @Column(name = "unit",  columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String unit;//计量单位

    @Column(name = "thresholdUp",  columnDefinition = "numeric(12,2)")
    @Setter
    @Getter
    private Float thresholdUp;//阈值上限

    @Column(name = "thresholdUpplus",  columnDefinition = "numeric(12,2)")
    @Setter
    @Getter
    private Float thresholdUpplus;//阈值上上限

    @Column(name = "thresholdDown",  columnDefinition = "numeric(12,2)")
    @Setter
    @Getter
    private Float thresholdDown;//阈值下限

    @Column(name = "thresholdDownplus",  columnDefinition = "numeric(12,2)")
    @Setter
    @Getter
    private Float thresholdDownplus;//阈值下下限

    @Column(name = "rangeUp",  columnDefinition = "numeric(12,2)")
    @Setter
    @Getter
    private Float rangeUp;//量程上限

    @Column(name = "rangeDown",  columnDefinition = "numeric(12,2)")
    @Setter
    @Getter
    private Float rangeDown;//量程下限

    @Column(name = "targetDescribe",  columnDefinition = "varchar(200)")
    @Setter
    @Getter
    private String targetDescribe;//描述

    @Column(name = "bitNo",  columnDefinition = "varchar(100)")
    @Setter
    @Getter
    private String bitNo;//位号

    @Column(name = "createDate",  columnDefinition = "datetime")
    @Setter
    @Getter
    private Timestamp createDate;//创建时间

    @Column(name = "createBy",  columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String createBy;//创建人

    @Column(name = "updateDate",  columnDefinition = "datetime")
    @Setter
    @Getter
    private Timestamp updateDate;//最后修改时间

    @Column(name = "updateBy",  columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String updateBy;//最后修改人

    /* 实时数据 */


    @Column(name = "value",  columnDefinition = "numeric(12,2)")
    @Setter
    @Getter
    private Float value;//实时值

    @Column(name = "collectTime",  columnDefinition = "datetime")
    @Setter
    @Getter
    private Timestamp collectTime;//采集时间

    @Column(name = "point",columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String point;//采集的点位字符串，用于和opc 或modbus sis 等系统取数据

    @Column(name = "noreport")
    @Setter
    @Getter
    private Integer noreport;//是否不需要上报 1:是 其他否

    @Column(name = "status",  columnDefinition = "char(1)")
    @Setter
    @Getter
    private Integer status;//状态  1；无信息 2：离线 3：低限报警 4：高限报警 5:正常

    @Column(name = "del",  columnDefinition = "char(1)")
    @Setter
    @Getter
    private Integer del;//删除标记

    @Column(name = "multiply",  columnDefinition = "numeric(8,3) default 1")
    @Setter
    @Getter
    private Float multiply;//相乘值


}
