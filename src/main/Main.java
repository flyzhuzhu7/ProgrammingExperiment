package main;

import model.Car;
import service.CarPark;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author cmy
 * @create 2021-03-18-9:25
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

//        var list = new ArrayList<String>();
        CarPark carPark = new CarPark();
//        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String carNo;

        while(true) {
            menu();
            int item = -1;
            while (true) {
                //随机生成1-3的数据
//                item = random.nextInt(3) + 1;
                item = scanner.nextInt();
                if(item>0 && item <5)
                    break;
                System.out.println("\n 输入有误，请重新选择： 1~4: ");
            }
            switch(item) {
                case 1:
                    System.out.println("请输入车牌号：");
                    //随机生成一个车牌号
//                    carNo = random.nextInt(1000) + "";
                    carNo = scanner.next();
//                    System.out.println("请输入车牌号：" + carNo);
                    carPark.arrival(carNo);
//                    list.add(carNo);
                    break;
                case 2:
//                    System.out.println(item);
//                    int index = random.nextInt(list.size());
//                    carNo = list.get(index);
                    System.out.println("请输入车牌号：");
                    carNo = scanner.next();
                    Car car = carPark.leave(carNo);
                    if (car != null) {
                        long time = (car.getLeaveDate().getTime() - car.getArrivalDate().getTime())/1000;
                        DecimalFormat df = new DecimalFormat("#.00");
                        String fee = df.format(carPark.charging(car));
                        System.out.println("车辆"+carNo+"停车时长"+time+"秒，共收费"+fee+"元。");
                    }
                    break;
                case 3:
                    carPark.showPark();
                    carPark.showWaiting();
                    break;
                case 4:
                    System.exit(0);
            }
//            Thread.sleep(1000);
        }

    }

    public static void menu() {
        System.out.println("\n\t§※§※§※§※§※§ 欢迎使用停车场系统.§※§※§※§※§※§\t\n");
        System.out.println("\t※◎※◎※◎※◎  1. 车辆到达登记.※◎※◎※◎※◎\t");
        System.out.println("\t※◎※◎※◎※◎  2. 车辆离开登记.※◎※◎※◎※◎\t");
        System.out.println("\t※◎※◎※◎※◎  3. 显示车辆信息.※◎※◎※◎※◎\t");
        System.out.println("\t※◎※◎※◎※◎  4. 退出系统.※◎※◎※◎※◎\t");
        System.out.println("\n\t请选择：\t");
    }

}
