package org.example.mnc.wechat.message;

import org.example.mnc.Message;

import java.util.List;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public abstract class BaseMessage implements Message {
    private List<String> touser;
    private List<String> toparty;
    private List<String> totag;
    private int toall;
    private int agentid;
    private int safe;

    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

    public List<String> getToparty() {
        return toparty;
    }

    public void setToparty(List<String> toparty) {
        this.toparty = toparty;
    }

    public List<String> getTotag() {
        return totag;
    }

    public void setTotag(List<String> totag) {
        this.totag = totag;
    }

    public int getToall() {
        return toall;
    }

    public void setToall(int toall) {
        this.toall = toall;
    }

    public int getAgentid() {
        return agentid;
    }

    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }

    public int getSafe() {
        return safe;
    }

    public void setSafe(int safe) {
        this.safe = safe;
    }

    public abstract String getMsgType();
}
