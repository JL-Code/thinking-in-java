package org.example.redis.model;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public class UserInfo implements Serializable {

    public UserInfo() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDefaultStation() {
        return defaultStation;
    }

    public void setDefaultStation(String defaultStation) {
        this.defaultStation = defaultStation;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    private String id;
    /**
     * 用户默认公司 ID
     */
    private String companyId;
    /**
     * 用户默认部门 ID
     */
    private String departmentId;
    /**
     * 用户默认岗位 ID
     */
    private String defaultStation;
    private String userCode;
    private String name;
    private Boolean disabled;
    private Boolean deleted;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    private List<Station> stations;

    /**
     * 用户当前岗位信息
     *
     * @return {@link Station} or null。
     */
    public Station getCurrentStation() {
        if (stations == null || stations.isEmpty()) return null;
        Stream<Station> stream = stations.stream().filter(s -> s.getStationId().equals(this.defaultStation));
        Optional<Station> optional = stream.findFirst();
        return optional.isPresent() ? optional.get() : null;
    }

}