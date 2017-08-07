package cn.cy.observerPattern;

/**
 * Created by zhouzhaohui on 2017/8/4.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者接口
 */
interface  Subject {
    //添加观察者
    void  registerObserver(Observer observer);
    //移除观察者
    void  removeObserver(Observer observer);
    //通知观察者
    void notifyObserver();
}

/**
 * 观察者
 */
interface  Observer {
    void update();
}

/**
 * 公共牌显示公共接口
 */
interface  DisplayElement {
    void  display();
}

/**
 * 具体带功能的被观察者 天气数据
 */
class WeatherDataerData implements  Subject {

    private float temperature;//温度
    private float humidity;//湿度
    private float pressure;//气压
    private List<Float> forecastTemperatures;//未来几天的温度
//    观察者集合
    private  List<Observer> observers;
    public WeatherDataerData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
         this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
         this.observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer :observers) {
             observer.update();
        }
    }

    public void setMeasurements(float temperature,float humidity,float pressure,List<Float> forecastTemperatures) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.forecastTemperatures = forecastTemperatures;
        notifyObserver();
    }
    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public List<Float> getForecastTemperatures() {
        return forecastTemperatures;
    }
}

/**
 * 显示当前天气的显示牌
 */
class CurrentConditiondDsiplay implements  Observer,DisplayElement {


    private float temperature;//温度
    private float humidity;//湿度
    private float pressure;//气压
    private  WeatherDataerData weatherDataerData;

    public CurrentConditiondDsiplay(WeatherDataerData weatherDataerData) {
        this.weatherDataerData = weatherDataerData;
        this.weatherDataerData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("当前温度为：" + this.temperature + "℃");
        System.out.println("当前湿度为：" + this.humidity);
        System.out.println("当前气压为：" + this.pressure);

    }

    @Override
    public void update() {
        this.temperature = weatherDataerData.getTemperature();
        this.humidity = weatherDataerData.getHumidity();
        this.pressure = weatherDataerData.getPressure();
        display();
    }
}

/**
 * 未来几天天气展示板
 */
class ForecastDisplay implements  Observer,DisplayElement {

    private WeatherDataerData weatherDataerData;
    private List<Float> forecastTemperatures;

    public ForecastDisplay(WeatherDataerData weatherDataerData) {
        this.weatherDataerData = weatherDataerData;
        this.weatherDataerData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("未来几天的气温");
        int count = forecastTemperatures.size();
        for (int i = 0; i < count; i++) {
            System.out.println("第" + i + "天:" + forecastTemperatures.get(i) + "℃");
        }
    }

    @Override
    public void update() {
        this.forecastTemperatures = weatherDataerData.getForecastTemperatures();
        display();
    }
}

public class ObserverPattern {

    public static void main(String[] args) {
        WeatherDataerData weatherDataerData = new WeatherDataerData();
        CurrentConditiondDsiplay currentConditiondDsiplay = new CurrentConditiondDsiplay(weatherDataerData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherDataerData);
        List<Float> forecastTemperatures = new ArrayList<Float>();
        forecastTemperatures.add(22f);
        forecastTemperatures.add(-1f);
        forecastTemperatures.add(9f);
        forecastTemperatures.add(23f);
        forecastTemperatures.add(27f);
        forecastTemperatures.add(30f);
        forecastTemperatures.add(10f);
        weatherDataerData.setMeasurements(22f, 0.8f, 1.2f, forecastTemperatures);
    }
}

