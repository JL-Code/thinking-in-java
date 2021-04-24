package org.example.springboot.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

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
     * 通过 @RequestBody
     *
     * @param appUseagesVO 包装类
     * @return
     */
    @PostMapping("model-embed-collection2")
    public AppUseagesVO bindCollection2(@RequestBody AppUseagesVO appUseagesVO) {
        return appUseagesVO;
    }

    /**
     * 拥有标准的请求头属性
     *
     * @param entity {@link HttpEntity}
     * @return
     */
    @GetMapping("/http-entity")
    public Object getEntity(HttpEntity<AppUseagesVO> entity) {
        HttpHeaders headers = entity.getHeaders();
        AppUseagesVO body = entity.getBody();

        return body;
    }
}
