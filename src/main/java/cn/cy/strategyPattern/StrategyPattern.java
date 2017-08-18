package cn.cy.strategyPattern;

import javax.xml.bind.SchemaOutputResolver;

/**
 * Created by zhouzhaohui on 2017/8/18.
 */
//出行旅游  策略模式
interface  TravelStrategy {
    void travelAlgorithm();
}
//做飞机出行旅游 具体策略类
class  AirPlanelStrategy implements  TravelStrategy {

    @Override
    public void travelAlgorithm() {
        System.out.println("travel by AirPlain");
    }
}
class TrainStrategy implements TravelStrategy {

    @Override
    public void travelAlgorithm() {
        System.out.println("travel by Train");
    }
}
class BicycleStrategy implements TravelStrategy {

    @Override
    public void travelAlgorithm() {
        System.out.println("travel by Bicycle");
    }
}
//环境类
class PersonContext {
    private TravelStrategy travelStrategy;
    public PersonContext(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    void travel() {
        travelStrategy.travelAlgorithm();
    }
}
public class StrategyPattern {
    public static void main(String[] args) {
//        坐火车旅行
        PersonContext context = new PersonContext(new TrainStrategy());
        context.travel();
//       坐飞机旅行
        PersonContext context1 = new PersonContext(new AirPlanelStrategy());
        context1.travel();
    }
}
