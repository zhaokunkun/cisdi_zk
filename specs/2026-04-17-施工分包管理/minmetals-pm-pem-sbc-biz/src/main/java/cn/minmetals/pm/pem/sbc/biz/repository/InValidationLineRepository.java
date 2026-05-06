package cn.minmetals.pm.pem.sbc.biz.repository;

import cn.minmetals.pm.pem.sbc.biz.repository.mapper.InValidationLineMapper;
import cn.minmetals.pm.pem.sbc.model.entity.InValidationLineEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 进场验证单 Repository
 *
 * @author minmetals
 */
@Component
public class InValidationLineRepository {

    @Resource
    private InValidationLineMapper inValidationLineMapper;

    /**
     * 判断进场验证单中是否存在引用指定模板编码的记录
     *
     * @param templateCode 进场验证模板编码
     * @return true=存在引用，false=不存在引用
     */
    public Boolean existByCode(String templateCode) {
        LambdaQueryWrapper<InValidationLineEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(templateCode),
                InValidationLineEntity::getSubInValidationTempCode, templateCode);
        return inValidationLineMapper.exists(queryWrapper);
    }
}
