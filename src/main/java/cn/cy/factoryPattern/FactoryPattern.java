package cn.cy.factoryPattern;

/**
 * Created by zhouzhaohui on 2017/8/4.
 */


import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * 简单工厂模式
 */
//抽象类
abstract  class Operation {

    abstract double getResult(double firstNum,double secondNum);
}
//加操作
class  Addition extends Operation {

    @Override
    double getResult(double firstNum,double secondNum) {
        return firstNum+secondNum;
    }
}
//减操作
class Minus extends Operation {

    @Override
    double getResult(double firstNum,double secondNum) {
        return firstNum-secondNum;
    }
}
//乘操作
class Multiplication extends Operation {

    @Override
    double getResult(double firstNum,double secondNum) {
        return firstNum*secondNum;
    }
}
//除操作
class Division extends Operation {

    @Override
    double getResult(double firstNum,double secondNum) {
        return firstNum/secondNum;
    }
}
//工厂类
class OperationFactory {
    static Operation createOperation(String o) {
        Operation operation = null;
        switch (o) {
            case "+":
                operation = new Addition();
                break;
            case "-":
                operation = new Minus();
                break;
            case "*":
                operation = new Multiplication();
                break;
            case "/":
                operation = new Division();
                break;
            default:
                operation = new Addition();
        }
        return operation;
    }
}
//---------------分割线--------------------------------------------------------------

/**
 * 工厂方法模式 代码太多 仅以加减为例
 */
//抽象工厂类
abstract class IFactory {
    abstract Operation createOperation();
}
//加法工厂类
class AddFactory extends IFactory {

    @Override
    Operation createOperation() {
        return new Addition();
    }
}

//减法工厂类
class MimusFactory extends IFactory {

    @Override
    Operation createOperation() {
        return new Minus();
    }
}
//-----------------------分割线---------------------------------------------------------------

/**
 * 抽象工厂模式示例
 */
interface Button {
    void  disPlay();
}

class SpringButton implements  Button {

    @Override
    public void disPlay() {
        System.out.println("显示浅绿色按钮");
    }
}

class SummerButton implements  Button {

    @Override
    public void disPlay() {
        System.out.println("显示浅蓝色按钮");
    }
}

//文本框接口：抽象产品
interface  TextField {
    void disPlay();
}

class SpringTextField implements TextField {

    @Override
    public void disPlay() {
        System.out.println("显示浅绿色边框文本框。");
    }
}

class  SummerTextField implements TextField {

    @Override
    public void disPlay() {
        System.out.println("显示浅蓝色边框文本框。");
    }
}
//组合框接口：抽象产品
interface ComboBox {
    void disPlay();
}

class SpringComboBox implements ComboBox {

    @Override
    public void disPlay() {
        System.out.println("显示浅绿色组合框。");
    }
}

class SummerComboBox implements  ComboBox {

    @Override
    public void disPlay() {
        System.out.println("显示浅蓝色组合框。");
    }
}

//界面皮肤工厂接口：抽象工厂
interface  SkinFactory {
    Button createButton();
    TextField createTextField();
    ComboBox createComboBox();

}

//Spring皮肤工厂：具体工厂
class SpringSkinFactory implements  SkinFactory {

    @Override
    public Button createButton() {
        return new SpringButton();
    }

    @Override
    public TextField createTextField() {
        return new SpringTextField();
    }

    @Override
    public ComboBox createComboBox() {
        return new SpringComboBox();
    }
}

class SummerSkinFactory implements SkinFactory {

    @Override
    public Button createButton() {
        return new SummerButton();
    }

    @Override
    public TextField createTextField() {
        return new SummerTextField();
    }

    @Override
    public ComboBox createComboBox() {
        return new SummerComboBox();
    }
}
public class FactoryPattern {

    public static void main(String[] args) {
        //简单工厂测试
        Operation operation = OperationFactory.createOperation("o");
        System.out.println(operation.getResult(2,4));

        //工厂方法模式
        IFactory factory = new AddFactory();
        Operation operation1 = factory.createOperation();
        System.out.println(operation1.getResult(3,4));

        //抽象工厂模式
        SkinFactory skinFactory = new SpringSkinFactory();
        Button button = skinFactory.createButton();
        TextField textField = skinFactory.createTextField();
        ComboBox comboBox = skinFactory.createComboBox();
        button.disPlay();
        textField.disPlay();
        comboBox.disPlay();
    }

}
