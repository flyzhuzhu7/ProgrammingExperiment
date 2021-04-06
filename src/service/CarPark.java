package service;

import list.CarNode;
import list.CarQueue;
import list.CarStack;
import model.Car;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author cmy
 * @create 2021-03-18-9:23
 */
public class CarPark {

    //存储已经在停车场的车的车牌号
    ArrayList<String> temp = new ArrayList<>();

    //停车区
    CarStack stack = new CarStack(3);
    //中转区
    CarStack tempStack = new CarStack(3);
    //候车区
    CarQueue queue = new CarQueue();

    /**
     * 将carNo车牌的汽车驶入，如果停车场有车位则进入停车场，设定入场时间，否则在便道上等待
     * @param carNo 车牌号的String值
     * @return
     */
    public boolean arrival(String carNo) {
        Car car = new Car(carNo);
        //判断当前车辆是否存在停车场
        boolean s = isStack(carNo);
        boolean q = isQueue(carNo);
        if (s || q) {
            System.out.println("车辆已经在停车场！");
            return false;
        }
        //停车场已满
        if (stack.isFull()) {
            System.out.println("停车区已满，已进入候车区！");
            //进入候车区
            queue.offer(car);
            return true;
        }
        //未满，则进入停车场
        car.setArrivalDate(new Date());
        stack.push(car);
        return true;
    }

    /**
     * 将carNo车牌的汽车驶离停车场，设定离开时间，同时便道汽车进入停车场
     * @param carNo 车牌号的String值
     * @return 离开的汽车
     */
    public Car leave(String carNo) {
        //要出去的车辆
        Car car = null;
        boolean s = isStack(carNo);
        boolean q = isQueue(carNo);
        if (!s && !q) {
            System.out.println("车辆不在停车场！");
            return null;
        }
        //要出去的车辆在栈中
        if (s) {
            while (!stack.isEmpty()) {
                Car curCar = stack.pop();
                //当前车辆是要离开的车辆
                if (curCar.getCarNo().equals(carNo)) {
                    car = curCar;
                    break;
                }
                //不是要离开的车辆，则进入中转区
                tempStack.push(curCar);
            }
            //中转的车回停车场
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
        }
        //要出去的车辆在候车区
        if (q) {
            queue.remove(new Car(carNo));
            return null;
        }
//        while (!stack.isEmpty()) {
//           Car curCar = stack.pop();
//           //当前车辆是要离开的车辆
//           if (curCar.getCarNo().equals(carNo)) {
//               car = curCar;
//               break;
//           }
//           //不是要离开的车辆，则进入中转区
//           tempStack.push(curCar);
//        }
//        //中转的车回停车场
//        while (!tempStack.isEmpty()) {
//            stack.push(tempStack.pop());
//        }
//        if (car == null) {
//            System.out.println("停车区不存在车牌号为" + carNo + "的车辆！");
//            return null;
//        }
        car.setLeaveDate(new Date());
        //候车区如果有车，则直接入停车区
        if (!queue.isEmpty()) {
            Car cur = queue.poll();
            cur.setArrivalDate(new Date());
            arrival(cur.getCarNo());
        }
        return car;
    }

    /**
     * 根据车辆的出入时间，计算费用及停车时长
     * @param car
     * @return 停车费用
     */
    public double charging(Car car) {
        if (car == null) return 0;
        double fee = (car.getLeaveDate().getTime() - car.getArrivalDate().getTime()) * 1000;
        return fee;
    }

    /**
     * 显示所有车库车辆信息
     */
    public void showPark() {
        System.out.println("停车区：\n" + stack);
    }

    /**
     * 显示所有在便道上等待信息
     */
    public void showWaiting() {
        System.out.println("候车区：\n" + queue);
    }

    private boolean isStack(String no) {
        for (Car car : stack) {
            //匹配
            if (car.getCarNo().equals(no)) {
                return true;
            }
        }
        return false;
    }
    private boolean isQueue(String no) {
        for (Car car : queue) {
            //匹配
            if (car.getCarNo().equals(no)) {
                return true;
            }
        }
        return false;
    }

}
