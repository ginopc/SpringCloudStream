package io.spring.dataflow.sample.model;

/**
 * <pre>
 *
 * Title: UsageCostDetail class
 * Description: Usage Cost Detail DTO
 *
 * Copyright: Copyright (c) 2020
 * Company:
 * </pre>
 *
 * @author Maurizio Aru (ginopc@tiscali.it)
 * @version 1.0
 */
public class UsageCostDetail {


    private String userId;
    private double callCost;
    private double dataCost;

    public UsageCostDetail() {
    }

    public UsageCostDetail(String userId, double callCost, double dataCost) {
        this.userId = userId;
        this.callCost = callCost;
        this.dataCost = dataCost;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getCallCost() {
        return callCost;
    }

    public void setCallCost(double callCost) {
        this.callCost = callCost;
    }

    public double getDataCost() {
        return dataCost;
    }

    public void setDataCost(double dataCost) {
        this.dataCost = dataCost;
    }

    @Override
    public String toString(){
        return "{" +
                "\"user\": " + this.getUserId() +
                ", \"callCost\" : " + this.getCallCost() +
                ", \"dataCost\" : " + this.getDataCost() +
                "}";
    }
}
