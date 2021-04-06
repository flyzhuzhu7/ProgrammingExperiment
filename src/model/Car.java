package model;

import java.util.Date;

/**
 * @author cmy
 * @create 2021-03-18-9:07
 */
public class Car {
    //车牌号
    private String carNo;
    //到达时间
    private Date arrivalDate;
    //离开时间
    private Date leaveDate;

    public Boolean b;
    public Boolean a;

    public Car() {}

    public Car(String carNo) {
        this.carNo = carNo;
    }

    public String getCarNo() {
        return carNo;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carNo='" + carNo + '\'' +
                '}';
    }
}
