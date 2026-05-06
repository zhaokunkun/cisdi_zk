# AGENTS.md - 五矿项目 AI 开发规范

> 本文件是五矿项目的基础规范文档，每次 AI 启动时必读。
> 位置：项目根目录

## 项目信息

- **项目名称**：五矿项目管理系统
- **最后更新**：2026-04-23

---

## 技术栈

| 类别 | 技术 | 版本 |
|------|------|------|
| JDK | Amazon Corretto | 21 |
| Web容器 | TongWeb嵌入版 | 8.0 |
| 框架 | Spring Boot | 3.5.5 |
| 框架 | Spring Cloud | 2025.0.0 |
| ORM | MyBatis-Plus | 3.5.12 |
| 序列化 | Jackson | 2.19.2 |
| 分布式事务 | Seata | 2.5.0 |
| 服务间调用 | Spring 6 HttpExchange | - |
| 缓存 | Caffeine / Redis (Redisson) | - |
| 线程上下文 | TransmittableThreadLocal | 2.14.5 |

---

## 代码层级结构（必须严格遵守）

```
<service>-client:     内部接口定义 (xxxClient)
<service>-biz:        业务模块
  ├── common/
  │   ├── exception/   异常类
  │   └── util/       工具类
  ├── 模块A/
  │   ├── controller/
  │   │   ├── api/        前端接口 (入参Param → 出参Vo)
  │   │   ├── innerapi/   内部接口 (入参Param → 出参Dto)
  │   │   └── openapi/    开放接口 (入参Param → 出参Dto)
  │   ├── service/       业务服务
  │   └── repository/
  │       ├── mapper/    MyBatis Mapper
  │       └── xxxRepository  ORM服务类
<service>-model:      POJO和MapStruct转换
  ├── dto/    内部传输类
  ├── param/  controller入参
  ├── entity/ ORM实体
  ├── vo/     controller出参
  ├── enums/  枚举类
  └── converter/ MapStruct转换
```

---

## 禁止事项 ⚠️

| 序号 | 禁止项 |
|------|--------|
| 1 | 禁止绕过 Service 直接操作 Mapper |
| 2 | 禁止在 Controller 层写逻辑代码 |
| 3 | 禁止在循环/递归里调用外部服务接口 |
| 4 | 禁止用 lambdaQuery 进行多次单表查询 |
| 5 | 禁止使用 @Select/@Insert/@Update 注解，必须用 Mapper XML |
| 6 | 禁止用 @Value 注解读取 yaml 配置 |
| 7 | 禁止直接返回 Entity，必须用 DTO/VO |
| 8 | 禁止在 Service 外使用 @Transactional |
| 9 | 禁止复杂嵌套，应立即中止 |
| 10 | 禁止使用 @RestTemplate，用 RestClient |
| 11 | 禁止使用 Fastjson，用 Jackson |
| 12 | 禁止使用 Hutool |
| 13 | 禁止 e.printStackTrace()、System.out |

---

## 接口规范

### 请求方式

- 仅允许使用 **GET** 和 **POST**
- 禁止 RESTful 风格路径参数
- URL 使用 kebab-case: `/get-user-info`

### 统一响应格式

```json
{
  "code": "0",
  "message": "success",
  "data": {}
}
```

### 错误码规范

- `"0"` = 成功
- `"-1"` = 系统异常
- 其他: 9位错误码
  - 前3位: 产品唯一标识
  - 中3位: 服务唯一标识
  - 后3位: 业务自定义码 (从1开始)

### 序列化规范

- 使用 Jackson（禁止 Fastjson）
- Long 类型用字符串交互（防精度丢失）
- 枚举用 CODE 值，不用 name
- null 字段不能忽略

---

## POJO 规范

### 四种类型

| 类型 | 用途 | 层级 |
|------|------|------|
| Entity | ORM映射 | Repository |
| Param | 请求入参 | Controller 入 |
| Dto | 内部传输 | Service 间 |
| Vo | 响应出参 | Controller 出 |

### 标准写法

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "user.name.notBlank")
    private String name;
}
```

---

## 枚举规范

```java
@Getter
@RequiredArgsConstructor
public enum StatusEnum implements BizEnum {
    ENABLE(1, "common.status.enable"),
    DISABLE(0, "common.status.disable");

    private final int code;
    private final String messageKey;
}
```

---

## 异常处理

- 使用 `@RestControllerAdvice` 统一处理异常
- 自定义业务异常必须继承 `BusinessException`
- 禁止 `try-catch` 吞掉异常不处理

---

## 日志规范

- 使用 `@Slf4j` + log 对象
- 使用占位符: `log.info("id={}", id)`
- 核心流程日志带唯一识别关键字
- 敏感信息禁止打印

---

## 事务规范

- 事务注解加在 public 方法
- 非必要不用分布式事务
- 分布式事务用 Seata TCC 模式
- 事务只围住最小必要的数据库代码

---

## 线程池规范

- 必须通过 ThreadPoolExecutor 创建
- 必须指定有意义的线程名称
- 必须包装 TtlRunnable
- 必须提供 Micrometer 监控指标

---

## 低代码平台集成

- 前端使用低代码平台拖拽生成
- 后端定制开发需遵循上述分层规范
- 与低代码平台交互使用统一接口适配器模式

---

## Entity 规范

- 必须有 `private static final long serialVersionUID = 1L;`
- 每个字段必须有 `@TableField(value = "列名", keepGlobalFormat = true)` 注解
- 继承基类的 import 路径必须与项目现有代码一致（`cn.cisdigital.elite.forge.infra.commons.model.entity`）

## AI 代码生成原则

1. **只生成本次需求相关的代码**，不要顺手加额外的方法或逻辑
2. 涉及新需求先按 OpenSpec 工作流创建 Change Spec（见 `.codebuddy/rules/openspec-workflow.mdc`）
4. 数据库索引任务只需记录到任务清单，不随代码一起开发
5. 接口测试用例由 AI 生成，开发者自行执行验证

## AI 开发提示

1. 每次开发前先阅读本文件
2. 涉及数据库操作前确认 Mapper 是否存在
3. 涉及新需求先创建 Change Spec
4. 遇到不确定的问题先查现有 skill
