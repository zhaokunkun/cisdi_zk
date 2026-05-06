package cn.minmetals.pm.pem.sbc.model.entity;

import cn.cisdigital.elite.forge.infra.commons.model.entity.ArchivableAndTenantableAndVersionableEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 进场验证模板实体
 *
 * @author minmetals
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_sbc_in_validation_temp_line")
public class InValidationTempLineEntity extends ArchivableAndTenantableAndVersionableEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 进场验证模板编码
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
     * 审批状态
     */
    @TableField(value = "approval_status", keepGlobalFormat = true)
    private String approvalStatus;

    /**
     * 进场验证模板名称
     */
    @TableField(value = "name", keepGlobalFormat = true)
    private String name;

    /**
     * 共享策略
     */
    @TableField(value = "shared_policy", keepGlobalFormat = true)
    private String sharedPolicy;

    /**
     * 备注
     */
    @TableField(value = "remarks", keepGlobalFormat = true)
    private String remarks;
}
