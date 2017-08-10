package cn.cy.adapterPattern;

/**
 * Created by zhouzhaohui on 2017/8/10.
 */
//Adapee 源目标
class AmericaPower {

  void threeStep() {
        System.out.println("我是三边形插头");
    }
}
//target
interface ChinaPower {
    void twoStep();
}
//adapter
class AdapterPower extends AmericaPower implements ChinaPower {

    @Override
    public void twoStep() {
        this.threeStep();
    }
}
//------------------------------分割线------------------------------------------------------
//对象适配器
interface  Target {

    //源没有的方法
    void sampleOperation2();
}
//源目标
class  Adaptee {
    void sampleOperation1() {
        System.out.println("源方法");
    }
}

//adapter
class Adapter implements Target {
    private Adaptee adaptee;
    private Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }


    @Override
    public void sampleOperation2() {
        this.adaptee.sampleOperation1();
    }
}
public class AdapterPattern {
    public static void main(String[] args) {
        //类适配器测试
        ChinaPower chinaPower = new AdapterPower();
        chinaPower.twoStep();
    }
}
