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
public class FactoryPattern {

    public static void main(String[] args) {
        //简单工厂测试
        Operation operation = OperationFactory.createOperation("o");
        System.out.println(operation.getResult(2,4));
    }

}
