# 分包验证模板取消提交校验 - 任务清单

> 更新时间: 2026-04-23

## 任务总览

| 状态 | 数量 |
|------|------|
| 待开始 | 2 |
| 进行中 | 0 |
| 已完成 | 5 |

---

## 后端任务

### [x] 任务1: 创建 Entity 类 ✅
- 负责人: 
- 预估: 0.5h
- 描述: 
  - 创建 `InValidationTempLineEntity.java`（进场验证模板实体，表 `pm_sbc_in_validation_temp_line`）
  - 创建 `OutValTempLineEntity.java`（退场验证模板实体，表 `pm_sbc_out_val_temp_line`）
  - 创建 `InValidationLineEntity.java`（进场验证单实体，表 `pm_sbc_in_validation_line`，含 `subInValidationTempCode` 字段）
  - 创建 `OutValLineEntity.java`（退场验证单实体，表 `pm_sbc_out_val_line`，含 `outValTempCode` 字段）

### [x] 任务2: 创建 Mapper 接口 ✅
- 负责人: 
- 预估: 0.5h
- 描述:
  - 创建 `InValidationLineMapper.java`（继承 BaseMapper）
  - 创建 `OutValLineMapper.java`（继承 BaseMapper）
  - 创建对应 Mapper XML 文件

### [x] 任务3: 创建 Repository 层 ✅
- 负责人: 
- 预估: 0.5h
- 描述:
  - 创建 `InValidationLineRepository.java`，实现 `existByCode(String templateCode)` 方法
    - 使用 LambdaQueryWrapper 查询 `sub_in_validation_temp_code` 字段
    - 调用 `mapper.exists(wrapper)` 返回是否存在
  - 创建 `OutValLineRepository.java`，实现 `existByCode(String templateCode)` 方法
    - 使用 LambdaQueryWrapper 查询 `out_val_temp_code` 字段
    - 调用 `mapper.exists(wrapper)` 返回是否存在

### [x] 任务4: 创建 Service 层 ✅
- 负责人: 
- 预估: 0.5h
- 描述:
  - 创建 `InValidationLineService.java`，实现 `canCancelCommit(String templateCode)` 方法
    - 逻辑：`return !inValidationLineRepository.existByCode(templateCode)`
  - 创建 `OutValTempLineService.java`，实现 `canCancelCommit(String templateCode)` 方法
    - 逻辑：`return !outValLineRepository.existByCode(templateCode)`

### [x] 任务5: 创建 Controller 层 ✅
- 负责人: 
- 预估: 0.5h
- 描述:
  - 创建 `InValidationTempLineController.java`
    - `GET /minmetals-pm-pem-sbc/api/in-val-temp-line/canCancelCommit`
    - 参数：`@RequestParam String code`
    - 返回：`ResVo<Boolean>`
  - 创建 `OutValTempLineController.java`
    - `GET /minmetals-pm-pem-sbc/api/out-val-temp-line/canCancelCommit`
    - 参数：`@RequestParam String code`
    - 返回：`ResVo<Boolean>`

### [ ] 任务6: 确认数据库索引
- 负责人: 
- 预估: 0.5h
- 描述:
  - 确认 `pm_sbc_in_validation_line.sub_in_validation_temp_code` 是否有索引
  - 确认 `pm_sbc_out_val_line.out_val_temp_code` 是否有索引
  - 如无索引，提交 DDL 变更

---

## 测试任务

### [ ] 任务7: 接口测试
- 负责人: 
- 预估: 1h
- 测试用例:

| 用例编号 | 场景 | 前置条件 | 预期结果 |
|----------|------|----------|----------|
| TC001 | 进场模板-未被引用 | 创建进场模板A，无验证单引用 | 返回 `true` |
| TC002 | 进场模板-已被引用 | 创建进场模板A，存在验证单引用A | 返回 `false` |
| TC003 | 退场模板-未被引用 | 创建退场模板B，无验证单引用 | 返回 `true` |
| TC004 | 退场模板-已被引用 | 创建退场模板B，存在验证单引用B | 返回 `false` |
| TC005 | 参数为空 | code 传空字符串 | 返回 `true`（空模板编码视为未引用） |
