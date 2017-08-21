package cn.cy.brigdePattern;

/**
 * Created by zhouzhaohui on 2017/8/21.
 */
abstract class AbstractRoad {
    protected  AbstractCar car;

    public void setCar(AbstractCar car) {
        this.car = car;
    }
    abstract void run();
}
class SpeedWay extends AbstractRoad {

    @Override
    void run() {
        car.run();
        System.out.println("高速公路上行驶");
    }
}
class Street extends AbstractRoad {

    @Override
    void run() {
        car.run();
        System.out.println("城市道路上行驶");
    }
}
abstract class AbstractCar {
    abstract void run();
}
//小汽车
class Car extends AbstractCar {

    @Override
    void run() {
        System.out.println("小汽车在");
    }
}
//公共汽车
class Bus extends AbstractCar {

    @Override
    void run() {
        System.out.println("公共汽车在说");
    }
}
public class BrigdePattern {
    public static void main(String[] args) {
        //--------高速上跑小车---------
        AbstractRoad road = new SpeedWay();
        AbstractCar car = new Car();
        road.setCar(car);
        road.run();
    }
}
