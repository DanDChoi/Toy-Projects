package com.example.demo.before;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contract {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private double commission;

    @Column(nullable = false)
    private String commissionType;

    @Column(nullable = false)
    private String commissionCutting;

    public Contract() {}

    public Contract(String company, double commission, String commissionType, String commissionCutting){
        this.company = company;
        this.commission = commission;
        this.commissionType = commissionType;
        this.commissionCutting = commissionCutting;
    }

    public Long getId(){
        return id;
    }

    public String getCompany(){
        return company;
    }

    public double getCommission(){
        return commission;
    }

    public String getCommissionType(){
        return commissionType;
    }

    public String getCommissionCutting(){
        return commissionCutting;
    }


}
