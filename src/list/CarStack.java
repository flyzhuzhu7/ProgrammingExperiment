package list;

import model.Car;

import java.util.Iterator;

/**
 * @author cmy
 * @create 2021-03-18-9:05
 */
public class CarStack implements Iterable<Car> {

    //最大容量
    private int MAXSIZE;
    //栈中元素个数
    private int size = 0;
    //栈顶元素，虚拟头节点
    private CarNode top = new CarNode(null);

    public CarStack() {}

    public CarStack(int maxSize) {
        MAXSIZE = maxSize;
    }


    /**
     * 入栈
     * @param car
     * @return
     */
    public boolean push(Car car) {
        //栈为空，直接入栈
        if (size == 0) {
            top.next = new CarNode(car);
            size++;
            return true;
        }

        //栈满，则提示插入失败
        if (size == MAXSIZE) {
            System.out.println("停车区车位已满，已进入候车区！");
            return false;
        }

        //栈不为空且不为满，用头插法入栈
        CarNode node = new CarNode(car);
        node.next = top.next;
        top.next = node;
        size++;
        return true;
    }

    /**
     * 出栈
     * @return
     */
    public Car pop() {
        if (size == 0) return null;
        CarNode cur = top.next;
        top.next = top.next.next;
        size--;
        return cur.element;
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    //判满
    public boolean isFull() {
        return size == MAXSIZE;
    }

    /**
     *
     * @return 栈的当前元素个数
     */
    public int size() {
        return size;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        CarNode node = top.next;
        while (node != null) {
            builder.append(node.element);
            builder.append("\n");
            node = node.next;
        }
        return builder.toString();
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {
            CarNode carNode = top;

            @Override
            public boolean hasNext() {
                return carNode.next != null;
            }

            @Override
            public Car next() {
                Car car = carNode.next.element;
                carNode = carNode.next;
                return car;
            }
        };
    }
}
