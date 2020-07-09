package org.example.redis.model;

/**
 * <p>描述: [岗位信息] </p>
 * <p>创建时间: 2020/6/20 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class Station {
    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 0 公司、1 部门
     */
    private String stationId;
    private String stationName;
    private String departmentId;
    private String departmentName;
    private String companyId;
    private String companyName;

    public Station(String stationId, String stationName, String departmentId, String departmentName, String companyId, String companyName) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.companyId = companyId;
        this.companyName = companyName;
    }
}
