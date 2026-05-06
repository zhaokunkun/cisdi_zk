# 分包验证模板取消提交校验 - 技术设计

> 设计时间: 2026-04-23
> 设计人: 开发组

## 1. 概述

### 1.1 需求说明

为进场验证模板和退场验证模板提供"取消提交校验"接口，判断模板是否可取消提交。

### 1.2 设计范围

- Controller 层：新增两个 GET 接口
- Service 层：新增校验方法
- Repository 层：新增引用检查方法
- 本设计不涉及审批流程

## 2. 技术方案

### 2.1 技术栈

遵循 AGENTS.md 规范：JDK 21 + Spring Boot 3.5.5 + MyBatis-Plus 3.5.12

### 2.2 模块设计

```
minmetals-pm-pem-sbc-biz/
  ├── controller/api/
  │   ├── InValidationTempLineController.java   # 进场验证模板 Controller
  │   └── OutValTempLineController.java         # 退场验证模板 Controller
  ├── service/
  │   ├── InValidationLineService.java          # 进场验证 Service（含 canCancelCommit）
  │   └── OutValTempLineService.java            # 退场验证模板 Service（含 canCancelCommit）
  └── repository/
      ├── InValidationLineRepository.java       # 进场验证 Repository（含 existByCode）
      └── OutValLineRepository.java             # 退场验证 Repository（含 existByCode）

minmetals-pm-pem-sbc-model/
  ├── entity/
  │   ├── InValidationTempLineEntity.java       # 进场验证模板实体
  │   ├── OutValTempLineEntity.java             # 退场验证模板实体
  │   ├── InValidationLineEntity.java           # 进场验证单实体
  │   └── OutValLineEntity.java                 # 退场验证单实体
  └── enums/
      └── BizErrorEnum.java                     # 业务错误码
```

## 3. 接口设计

### 3.1 接口列表

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 进场模板取消提交校验 | GET | `/minmetals-pm-pem-sbc/api/in-val-temp-line/canCancelCommit` | 判断进场模板是否可取消提交 |
| 退场模板取消提交校验 | GET | `/minmetals-pm-pem-sbc/api/out-val-temp-line/canCancelCommit` | 判断退场模板是否可取消提交 |

### 3.2 接口详情

#### 进场模板取消提交校验

```
GET /minmetals-pm-pem-sbc/api/in-val-temp-line/canCancelCommit?code=xxx

请求参数:
  code: 进场验证模板编码 (String, 必填)

响应:
{
  "code": "0",
  "message": "success",
  "data": true    // true=可取消, false=不可取消
}
```

#### 退场模板取消提交校验

```
GET /minmetals-pm-pem-sbc/api/out-val-temp-line/canCancelCommit?code=xxx

请求参数:
  code: 退场验证模板编码 (String, 必填)

响应:
{
  "code": "0",
  "message": "success",
  "data": true    // true=可取消, false=不可取消
}
```

## 4. 核心逻辑

### 4.1 Controller 层

```java
// InValidationTempLineController.java
@GetMapping("/canCancelCommit")
public ResVo<Boolean> canCancelCommit(@RequestParam String code) {
    return ResVo.ok(inValidationLineService.canCancelCommit(code));
}

// OutValTempLineController.java
@GetMapping("/canCancelCommit")
public ResVo<Boolean> canCancelCommit(@RequestParam String code) {
    return ResVo.ok(outValTempLineService.canCancelCommit(code));
}
```

### 4.2 Service 层

```java
// InValidationLineService.java
public Boolean canCancelCommit(String templateCode) {
    return !inValidationLineRepository.existByCode(templateCode);
}

// OutValTempLineService.java
public Boolean canCancelCommit(String templateCode) {
    return !outValLineRepository.existByCode(templateCode);
}
```

### 4.3 Repository 层

```java
// InValidationLineRepository.java
public Boolean existByCode(String templateCode) {
    LambdaQueryWrapper<InValidationLineEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(StringUtils.isNotBlank(templateCode),
        InValidationLineEntity::getSubInValidationTempCode, templateCode);
    return inValidationLineMapper.exists(wrapper);
}

// OutValLineRepository.java
public Boolean existByCode(String templateCode) {
    LambdaQueryWrapper<OutValLineEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(StringUtils.isNotBlank(templateCode),
        OutValLineEntity::getOutValTempCode, templateCode);
    return outValLineMapper.exists(wrapper);
}
```

## 5. 数据库设计

### 5.1 关联关系

| 模板表 | 关联字段 | 验证单表 | 关联字段 |
|--------|----------|----------|----------|
| pm_sbc_in_validation_temp_line | code | pm_sbc_in_validation_line | sub_in_validation_temp_code |
| pm_sbc_out_val_temp_line | code | pm_sbc_out_val_line | out_val_temp_code |

### 5.2 索引要求

| 表 | 索引字段 | 类型 | 说明 |
|----|----------|------|------|
| pm_sbc_in_validation_line | sub_in_validation_temp_code | 普通索引 | 提升取消提交校验查询性能 |
| pm_sbc_out_val_line | out_val_temp_code | 普通索引 | 提升取消提交校验查询性能 |

## 6. 上下游依赖

| 系统 | 接口/服务 | 说明 |
|------|-----------|------|
| 低代码平台 | - | Controller 继承低代码平台基类 |

## 7. 评审记录

| 评审时间 | 评审人 | 结论 | 备注 |
|----------|--------|------|------|
| - | - | - | - |
