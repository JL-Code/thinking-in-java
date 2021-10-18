package org.example.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>创建时间: 2021/10/15 </p>
 * 通过 Package packageInfo = ProjectVersionController.class.getPackage(); 输出应用版本信息
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
public class ProjectVersionController {


    @GetMapping("/project/version")
    public Object listManifestInfo() {

        /**
         * https://docs.oracle.com/javase/7/docs/api/java/lang/Package.html
         */
        Package packageInfo = ProjectVersionController.class.getPackage();

        /**
         * {
         * name: "org.example.springboot.controller",
         * annotations: [ ],
         * declaredAnnotations: [ ],
         * sealed: false,
         * specificationTitle: null,
         * specificationVersion: null,
         * specificationVendor: null,
         * implementationTitle: "springboot",  // 打包后运行才可以看到（IDEA 运行时无）
         * implementationVersion: "1.0-SNAPSHOT", // 打包后运行才可以看到（IDEA 运行时无）
         * implementationVendor: null
         * }
         */

        return packageInfo;
    }
}
