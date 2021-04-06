package list;

import model.Car;

import java.util.Iterator;

/**
 * 队尾入队，队头出队
 * @author cmy
 * @create 2021-03-18-9:06
 */
public class CarQueue implements Iterable<Car> {

    //队列头部
    private CarNode front;
    //队列尾部
    private CarNode rear;
    //队列长度
    private int size;

    /**
     * 队列判空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @return 队列长度
     */
    public int  size() {
        return size;
    }

    /**
     * 入队
     * @param car
     * @return
     */
    public boolean offer(Car car) {
        //当前队列为空
        if (isEmpty()) {
            front = new CarNode(car);
            rear = front;
            size++;
            return true;
        }
        //队列不为空
        CarNode node = new CarNode(car);
        rear.next = node;
        rear = rear.next;
        size++;
        return true;
    }

    public void remove(Car car) {
        if (size() == 0) return;
        String no = car.getCarNo();
        //只有一个节点，或者头节点就是要找的节点
        if (front.element.getCarNo().equals(no)) {
            front = front.next;
            size--;
        }

        CarNode carNode = front;
        //遍历队列
        while (carNode != null && carNode.next != null) {
            //找到当前节点
            if (carNode.next.element.getCarNo().equals(no)) {
                carNode.next = carNode.next.next;
                size--;
                break;
            }
            carNode = carNode.next;
        }
    }

    /**
     * 出队
     * @return
     */
    public Car poll() {
        //当前队列为空
        if (isEmpty()) return null;
        //队列不为空
        Car car = front.element;
        front = front.next;
        size--;
        return car;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        CarNode node = front;
        while (node != null) {
            builder.append(node.element);
            builder.append("\n");
            node = node.next;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        CarQueue queue = new CarQueue();
        Car car1 = new Car("111");
        Car car2 = new Car("222");
        Car car3 = new Car("333");
        Car car4 = new Car("444");
        Car car5 = new Car("555");
        queue.offer(car1);

        System.out.println(queue);
        queue.remove(new Car("111"));
        System.out.println(queue);
        queue.remove(new Car("555"));
        System.out.println(queue);
        queue.remove(new Car("333"));
        System.out.println(queue);
        CarStack stack = new CarStack();
        stack.push(car1);
        stack.push(car2);
        stack.push(car3);
        for (Car car : stack) {
            System.out.println(car);
        }
        for (Car car : queue) {
            System.out.println(car);
        }
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<>() {
            CarNode carNode = front;

            @Override
            public boolean hasNext() {
                return carNode != null;
            }

            @Override
            public Car next() {
                Car car = carNode.element;
                carNode = carNode.next;
                return car;
            }
        };
    }
}
