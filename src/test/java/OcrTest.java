import cn.cy.ocr.OCR;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zhouzhaohui on 2017/8/16.
 */
class Employee {
    private String name;
    private double salary;
    private Date hireDay;
    public Employee(){};
    public Employee(String n,double s,int year,int month,int day) {
           name = n;
           salary = s;
        GregorianCalendar calendar = new GregorianCalendar(year,month-1,day);
          hireDay = calendar.getTime();
    }
    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary*byPercent/100;
        salary+=raise;
    }
}

class Manager extends Employee {
    private double bonus;
    public Manager(String n,double s,int year,int month,int day) {
        super(n,s,year,month,day);
        bonus = 0;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    public double getSalary() {
        return getSalary()+bonus;
    }
}
public class OcrTest {

    public static void main(String[] args) {
//        String path = "E://admin//timg.jpg";
//        System.out.println("ORC Test Begin......");
//        try {
//            String valCode = new OCR().recognizeText(new File(path), "jpg");
//            System.out.println(valCode);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("ORC Test End......");
    }
}

