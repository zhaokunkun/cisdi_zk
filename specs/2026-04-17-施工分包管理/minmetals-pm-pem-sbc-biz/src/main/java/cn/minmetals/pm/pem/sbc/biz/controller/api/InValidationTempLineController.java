package cn.minmetals.pm.pem.sbc.biz.controller.api;

import cn.cisdigital.elite.forge.infra.commons.model.vo.ResVo;
import cn.minmetals.pm.pem.sbc.biz.constants.ApiConstants;
import cn.minmetals.pm.pem.sbc.biz.service.InValidationLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 进场验证模板 Controller
 *
 * @author minmetals
 */
@RestController
@RequestMapping(value = ApiConstants.APP_CODE + "/api/in-val-temp-line/")
@RequiredArgsConstructor
public class InValidationTempLineController {

    private final InValidationLineService inValidationLineService;

    /**
     * 判断进场验证模板是否可以取消提交
     *
     * @param code 进场验证模板编码
     * @return true=可以取消提交，false=不可取消提交
     */
    @GetMapping("/canCancelCommit")
    public ResVo<Boolean> canCancelCommit(@RequestParam String code) {
        return ResVo.ok(inValidationLineService.canCancelCommit(code));
    }
}
