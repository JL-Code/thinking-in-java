package org.example.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>创建时间: 2021/3/14 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
@RequestMapping("/mvc/bind")
public class MvcBindTestController {
    /**
     * @param appUseagesVO
     * @return
     */
    @PostMapping("model-embed-collection1")
    public AppUseagesVO bindCollection1(AppUseagesVO appUseagesVO) {
        return appUseagesVO;
    }

    /**
     * @param appUseagesVO
     * @return
     */
    @PostMapping("model-embed-collection2")
    public AppUseagesVO bindCollection2(@RequestBody AppUseagesVO appUseagesVO) {
        return appUseagesVO;
    }
}
