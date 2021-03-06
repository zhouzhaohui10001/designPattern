观察者模式笔记
参考文章：http://www.jianshu.com/p/d55ee6e83d66
         http://www.cnblogs.com/wangjq/archive/2012/07/12/2587966.html
概述
   　有时被称作发布/订阅模式，观察者模式定义了一种一对多的依赖关系， 让多个观察者对象同时监听某一个主题对象。
   这个主题对象在状态发生变化时，会通知所有观察者对象，使它们能够自动更新自己
   观察者模式面向的需求是：A对象（观察者）对B对象（被观察者）的某种变化高度敏感，需要在B变化的一瞬间做出反应。
   举个例子，新闻里喜闻乐见的警察抓小偷，警察需要在小偷伸手作案的时候实施抓捕。在这个例子里，警察是观察者、
   小偷是被观察者，警察需要时刻盯着小偷的一举一动，才能保证不会错过任何瞬间。程序里的观察者和这种真正的【观察】
   略有不同，观察者不需要时刻盯着被观察者（例如A不需要每隔1ms就检查一次B的状态），二是采用注册(_Register_)或者
   成为订阅(_Subscribe_)的方式告诉被观察者：我需要你的某某状态，你要在它变化时通知我。采取这样被动的观察方式，
   既省去了反复检索状态的资源消耗，也能够得到最高的反馈速度。
好处
   观察者模式将观察者和主题（被观察者）彻底解耦，主题只知道观察者实现了某一接口（也就是Observer接口）。并不需要
   观察者的具体类是谁、做了些什么或者其他任何细节。任何时候我们都可以增加新的观察者。因为主题唯一依赖的东西是一个
   实现了Observer接口的对象列表。
模式内的角色说明
 1 抽象主题（Subject）：俗称被观察者，它把所有观察者对象的引用保存到一个集合里，每个主题都可以有任何数量的观察者。
   抽象主题是一个接口，声明增加和删除观察者对象的方法。
 2 具体主题（ConcreteSubject）：真正具体有状态变化的主体对象；实现抽象主题，在具体主题内部状态改变时，给所有登记
   过的观察者发出通知。
 3 抽象观察者（Observer）：为所有的具体观察者定义一个接口，声明更新方法，在得到主题通知时更新自己。
 4 具体观察者（ConcreteObserver）：实现抽象观察者角色所要求的更新接口，以便使本身的状态与主题状态协调。
类图
   观察者模式通常基于Subject和__Observer__接口类来设计，下面是是类图：
  ![Image text](https://github.com/zhouzhaohui10001/designPattern/raw/master/images/observer.png)

 基于观察者模式实现一个小需求demo
   需求：
   气象局需要我们构建一套系统，这系统有两个公告牌，分别用于显示当前的实时天气和未来几天的天气预报。
   当气象局发布新的天气数据（WeatherData）后，两个公告牌上显示的天气数据必须实时更新。气象局同时要求我们
   保证程序拥有足够的可扩展性，因为后期随时可能要新增新的公告牌