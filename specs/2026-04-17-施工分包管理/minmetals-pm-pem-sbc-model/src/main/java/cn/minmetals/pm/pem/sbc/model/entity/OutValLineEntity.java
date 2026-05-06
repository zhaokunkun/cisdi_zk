package cn.minmetals.pm.pem.sbc.model.entity;

import cn.cisdigital.elite.forge.infra.commons.model.entity.ArchivableAndTenantableAndVersionableEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 退场验证单实体
 *
 * @author minmetals
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_sbc_out_val_line")
public class OutValLineEntity extends ArchivableAndTenantableAndVersionableEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 单据编号
     */
    @TableField(value = "code", keepGlobalFormat = true)
    private String code;

    /**
     * 单据状态
     */
    @TableField(value = "biz_status", keepGlobalFormat = true)
    private String bizStatus;

    /**
     * 组织
     */
    @TableField(value = "org", keepGlobalFormat = true)
    private String org;

    /**
     * 使用状态
     */
    @TableField(value = "status", keepGlobalFormat = true)
    private Short status;

    /**
     * 创建组织
     */
    @TableField(value = "create_org", keepGlobalFormat = true)
    private String createOrg;

    /**
     * 完成时间
     */
    @TableField(value = "complete_time", keepGlobalFormat = true)
    private LocalDateTime completeTime;

    /**
     * 流程ID
     */
    @TableField(value = "process_id", keepGlobalFormat = true)
    private Long processId;

    /**
     * 流程实例ID
     */
    @TableField(value = "process_instance_id", keepGlobalFormat = true)
    private String processInstanceId;

    /**
     * 当前节点编码
     */
    @TableField(value = "current_node_code", keepGlobalFormat = true)
    private String currentNodeCode;

    /**
     * 申请人
     */
    @TableField(value = "apply_by", keepGlobalFormat = true)
    private String applyBy;

    /**
     * 申请时间
     */
    @TableField(value = "apply_time", keepGlobalFormat = true)
    private LocalDateTime applyTime;

    /**
     * 审批状态
     */
    @TableField(value = "approval_status", keepGlobalFormat = true)
    private String approvalStatus;

    /**
     * 项目编号
     */
    @TableField(value = "proj_code", keepGlobalFormat = true)
    private String projCode;

    /**
     * 分包项目编号
     */
    @TableField(value = "subcontract_project_code", keepGlobalFormat = true)
    private String subcontractProjectCode;

    /**
     * 分包项目名称
     */
    @TableField(value = "subcontract_project_name", keepGlobalFormat = true)
    private String subcontractProjectName;

    /**
     * 分包合同编号
     */
    @TableField(value = "subcontract_contract_code", keepGlobalFormat = true)
    private String subcontractContractCode;

    /**
     * 分包合同名称
     */
    @TableField(value = "subcontract_contract_name", keepGlobalFormat = true)
    private String subcontractContractName;

    /**
     * 分包单位
     */
    @TableField(value = "subcontractor_name", keepGlobalFormat = true)
    private String subcontractorName;

    /**
     * 分包单位编码
     */
    @TableField(value = "subcontractor_code", keepGlobalFormat = true)
    private String subcontractorCode;

    /**
     * 分包工程范围
     */
    @TableField(value = "subcontract_scope", keepGlobalFormat = true)
    private String subcontractScope;

    /**
     * 退场验证模板编码
     */
    @TableField(value = "out_val_temp_code", keepGlobalFormat = true)
    private String outValTempCode;

    /**
     * 退场验证模板名称
     */
    @TableField(value = "out_val_temp_name", keepGlobalFormat = true)
    private String outValTempName;

    /**
     * 申请退场时间
     */
    @TableField(value = "exit_time", keepGlobalFormat = true)
    private LocalDateTime exitTime;
}
