package list;

import model.Car;

/**
 * @author cmy
 * @create 2021-03-18-9:06
 */
public class CarNode {

    Car element;
    CarNode next;

    public CarNode (Car car) {
        this.element = car;
    }

    public CarNode(Car car, CarNode next) {
        this.element = car;
        this.next = next;
    }

}
