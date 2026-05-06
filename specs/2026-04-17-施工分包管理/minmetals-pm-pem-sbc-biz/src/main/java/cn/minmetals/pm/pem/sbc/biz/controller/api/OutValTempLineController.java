package cn.minmetals.pm.pem.sbc.biz.controller.api;

import cn.cisdigital.elite.forge.infra.commons.model.vo.ResVo;
import cn.minmetals.pm.pem.sbc.biz.constants.ApiConstants;
import cn.minmetals.pm.pem.sbc.biz.service.OutValTempLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 退场验证模板 Controller
 *
 * @author minmetals
 */
@RestController
@RequestMapping(value = ApiConstants.APP_CODE + "/api/out-val-temp-line/")
@RequiredArgsConstructor
public class OutValTempLineController {

    private final OutValTempLineService outValTempLineService;

    /**
     * 判断退场验证模板是否可以取消提交
     *
     * @param code 退场验证模板编码
     * @return true=可以取消提交，false=不可取消提交
     */
    @GetMapping("/canCancelCommit")
    public ResVo<Boolean> canCancelCommit(@RequestParam String code) {
        return ResVo.ok(outValTempLineService.canCancelCommit(code));
    }
}
