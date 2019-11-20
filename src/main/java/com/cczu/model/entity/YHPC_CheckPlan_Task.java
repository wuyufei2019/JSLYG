package com.cczu.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 隐患排查---巡检班次巡检点任务表
 *
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name = "yhpc_checkplan_task")
public class YHPC_CheckPlan_Task implements Serializable {

    private static final long serialVersionUID = 5739935339538232699L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    public Long ID;

    /**
     * 巡检班次ID
     */
    @Column(name = "ID1", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    private Long ID1;

    //班次任务的详细表id
    @Column(name = "detail_id", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    private Long detailId;

    /**
     * 巡检点ID
     */
    @Column(name = "pointid", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    private Long pointid;

    /**
     * 巡检人用户ID
     */
    @Column(name = "userid", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    private Integer userid;

    //检查时间范围开始时间
    @Column(name = "starttime")
    @Setter
    @Getter
    private Timestamp starttime;

    //检查时间范围结束时间
    @Column(name = "endtime")
    @Setter
    @Getter
    private Timestamp endtime;

    /**
     * 巡查点类型（1风险点/2隐患排查点、自定义巡检点）
     */
    @Column(name = "checkpointtype")
    @Setter
    @Getter
    private Integer checkpointtype;

    //任务类型 日检、周检、月检、年检
    @Column(name = "type")
    @Setter
    @Getter
    private String type;

    //巡检状态
    @Column(name = "status")
    @Setter
    @Getter
    private Integer status;

    public static final int CHECK_STATUS_GEN = 1;//生成任务
    public static final int CHECK_STATUS_FINISH = 2;//dfasfd
    public static final int CHECK_STATUS_EXPIRATION = 3;//过期
}
