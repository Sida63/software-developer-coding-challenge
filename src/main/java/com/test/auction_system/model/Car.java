package com.test.auction_system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private Date starttime;
    private String carname;
    private String carinfor;
    private String publisher;
    private Date endtime;
    private double lowestprice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getCarinfor() {
        return carinfor;
    }

    public void setCarinfor(String carinfor) {
        this.carinfor = carinfor;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public double getLowestprice() {
        return lowestprice;
    }

    public void setLowestprice(double lowestprice) {
        this.lowestprice = lowestprice;
    }
}
