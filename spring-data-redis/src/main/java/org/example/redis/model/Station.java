package com.highzap.cloud.commons.user;

import lombok.Data;

/**
 * <p>描述: [岗位信息] </p>
 * <p>创建时间: 2020/6/20 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class Station {
    /**
     * 0 公司、1 部门
     */
    private String stationId;
    private String stationName;
    private String departmentId;
    private String departmentName;
    private String companyId;
    private String companyName;
}
