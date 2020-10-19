package io.spring.dataflow.sample.model;

/**
 * <pre>
 *
 * Title: UsageDetail class
 * Description: class description and scope
 *
 * Copyright: Copyright (c) 2020
 * Company: Ginux
 * </pre>
 *
 * @author Maurizio Aru (ginopc@tiscali.it)
 * @version 1.0
 */
public class UsageDetail {

    private String userId;
    private long duration;
    private long data;

    public UsageDetail() {
    }

    public UsageDetail(String userId, long duration, long data) {
        this.userId = userId;
        this.duration = duration;
        this.data = data;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }
}
