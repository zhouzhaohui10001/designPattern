package cn.cy.templatePattern;

/**
 * Created by zhouzhaohui on 2017/8/17.
 */
abstract class Beverage {
    public final void create() {
        boilWater();//把水煮沸
        brew();//用沸水冲泡...
        pourInCup();//把...倒进杯子
        addCoundiments();//加...
    }

     abstract void addCoundiments();

   void pourInCup() {
       System.out.println("倒进杯子");
   };

    abstract void brew();


     void boilWater() {
         System.out.println("煮开水");
     };
}

class Coffee extends Beverage {

    @Override
    void addCoundiments() {
        System.out.println("添加糖和牛奶");
    }

    @Override
    void brew() {
        System.out.println("用水冲咖啡");
    }
}
class Tea extends Beverage {

    @Override
    void addCoundiments() {
        System.out.println("添加蜂蜜");
    }

    @Override
    void brew() {
        System.out.println("用水冲茶");
    }
}
public class TemplatePattern {
    public static void main(String[] args) {
        Beverage beverage = new Tea();
        beverage.create();
    }
}
