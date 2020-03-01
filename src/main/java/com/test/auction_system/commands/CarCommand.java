package com.test.auction_system.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CarCommand {

    @NotNull
    @NotEmpty
    private String carname;
    private String carinfor;
    private double lowestprice;

    @NotNull
    @NotEmpty
    private String publisher;

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

    public double getLowestprice() {
        return lowestprice;
    }

    public void setLowestprice(double lowsetprice) {
        this.lowestprice = lowsetprice;
    }
}
