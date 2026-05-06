package cn.minmetals.pm.pem.sbc.biz.repository;

import cn.minmetals.pm.pem.sbc.biz.repository.mapper.OutValLineMapper;
import cn.minmetals.pm.pem.sbc.model.entity.OutValLineEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 退场验证单 Repository
 *
 * @author minmetals
 */
@Component
public class OutValLineRepository {

    @Resource
    private OutValLineMapper outValLineMapper;

    /**
     * 判断退场验证单中是否存在引用指定模板编码的记录
     *
     * @param templateCode 退场验证模板编码
     * @return true=存在引用，false=不存在引用
     */
    public Boolean existByCode(String templateCode) {
        LambdaQueryWrapper<OutValLineEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(templateCode),
                OutValLineEntity::getOutValTempCode, templateCode);
        return outValLineMapper.exists(queryWrapper);
    }
}
