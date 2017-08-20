package cn.cy.visitorPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhou on 2017/8/20.
 */
//抽象元素
abstract class Medicine {
    String name;
    double price;
    public Medicine(String name,double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    abstract void accept(Visitor visitor);
}
//具体元素
class MedicineA extends Medicine {
  public MedicineA(String name,double price) {
      super(name,price);
  }
    @Override
    void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}
class MedicineB extends Medicine {

    public MedicineB(String name,double price) {
        super(name,price);
    }
    @Override
    void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}
//抽象访问者
abstract class Visitor {
    protected  String name;

    public void setName(String name) {
        this.name = name;
    }
    abstract void visitor(MedicineA medicineA);
    abstract void visitor(MedicineB medicineB);

}
//具体访问者：划价员
class Charger extends Visitor {

    @Override
    void visitor(MedicineA medicineA) {
        System.out.println("划价员：" + name +"给药" + medicineA.getName() +"划价:" + medicineA.getPrice());
    }

    @Override
    void visitor(MedicineB medicineB) {
        System.out.println("划价员：" + name +"给药" + medicineB.getName() +"划价:" + medicineB.getPrice());
    }
}
//药房工作者
class WorkerOfPharmacy extends Visitor {

    @Override
    void visitor(MedicineA medicineA) {
        System.out.println("药房工作者：" + name + "拿药 ：" + medicineA.getName());
    }

    @Override
    void visitor(MedicineB medicineB) {
        System.out.println("药房工作者：" + name + "拿药 ：" + medicineB.getName());
    }
}
//对象结构 药单
class  Presciption {
    List<Medicine> medicineList = new ArrayList<>();
    public void addMedicine(Medicine medicine){
        medicineList.add(medicine);
    }

    public void removeMedicien(Medicine medicine){
        medicineList.remove(medicine);
    }
    void accept(Visitor visitor) {
        for (Medicine medicine : medicineList) {
            medicine.accept(visitor);
        }
    }
}
public class VisitorPattern {
    public static void main(String[] args) {
        Presciption presciption = new Presciption();
        MedicineA a = new MedicineA("板蓝根", 11.0);
        MedicineB b = new MedicineB("感康", 14.3);
        presciption.addMedicine(a);
        presciption.addMedicine(b);
        Visitor charger = new Charger();
        charger.setName("张三");

        Visitor workerOfPharmacy = new WorkerOfPharmacy();
        workerOfPharmacy.setName("李四");

        presciption.accept(charger);
        System.out.println("-------------------------------------");
        presciption.accept(workerOfPharmacy);
    }
}
