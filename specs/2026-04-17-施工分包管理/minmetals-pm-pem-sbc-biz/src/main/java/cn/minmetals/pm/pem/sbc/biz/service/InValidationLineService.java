package cn.minmetals.pm.pem.sbc.biz.service;

import cn.minmetals.pm.pem.sbc.biz.repository.InValidationLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 进场验证单 Service
 *
 * @author minmetals
 */
@Service
@RequiredArgsConstructor
public class InValidationLineService {

    private final InValidationLineRepository inValidationLineRepository;

    /**
     * 判断进场验证模板是否可以取消提交
     * <p>
     * 如果进场验证单表中不存在引用该模板的记录，则可以取消提交
     *
     * @param templateCode 进场验证模板编码
     * @return true=可以取消提交，false=不可取消提交
     */
    public Boolean canCancelCommit(String templateCode) {
        return !inValidationLineRepository.existByCode(templateCode);
    }
}
