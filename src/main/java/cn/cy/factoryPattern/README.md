工厂模式笔记
参考文章：https://www.zhihu.com/question/24843188?sort=created (知乎三楼的回答非常经典)
         http://www.jianshu.com/p/03519d5e47b6

我们经常一些功能类似的类，所以我们的思路是对进行抽象，使用接口暴露公共的方法，使用抽象类来提供公共的实现。（好好理解）
1.简单工厂模式
    简单工厂模式（simple factory）是类的创建模式，又叫静态工厂方法（static factory method）模式。
    简单工厂模式就是由一个工厂类根据传入的参数决定创建哪一种的产品类。
    简单工厂模式中定义一个抽象类，抽象类中声明公共的特征及属性，抽象子类继承自抽象类，去实现具体的操作。
    工厂类根据外界需求，在工厂类中创建对应的抽象子类实例并传给外界，而对象的创建是由外界决定的。
    外界只需要知道抽象子类对应的参数即可，而不需要知道抽象子类的创建过程，在外界使用时甚至不用引入抽象子类。
    简单工厂模式将抽象子类的创建，和关于抽象子类相关的业务逻辑分离，降低对象间的耦合度。由于工厂类只是为外界创建对象，
    所以并不需要实例化工厂类对象，只需要为外界提供类方法即可。外界需要什么类型的抽象子类，只需要传递对应的参数即可。
    外界不需要知道具体的抽象子类，只需要使用抽象类即可。
    简单工厂模式主要包含三部分：
        工厂类：根据外界的需求，决定创建并返回哪个具体的抽象子类。
        抽象类：定义抽象子类所需的属性和方法，子类通过继承自抽象类获得这些方法。
        抽象子类：继承自抽象类，是具体操作的实现者，根据需求重写父类继承过来的方法。
    简单工厂模式主要适用于抽象子类的业务逻辑相同，但具体实现不同的情况。不同的操作子类执行同样的方法，最后的结果却是不同的，
    这也是多态的一种表现方式(行为功能相似)
    示例：简单的加减乘除的基础运算例子当作需求
    明显的弊端：增加抽象子类后 工厂类需要修改  不符合开闭原则
2.工厂方法模式
   工厂方法模式和简单工厂模式十分类似，大致结构是基本类似的。不同在于工厂方法模式对工厂类进行了进一步的抽象，
   将之前的一个工厂类抽象成了抽象工厂和工厂子类，抽象工厂定义一个创建抽象子类的接口，
   抽象工厂的子类实现这些接口并决定实例化哪个抽象子类。工厂子类决定着创建哪个抽象子类，外界决定着创建哪种工厂子类，
   抽象子类和工厂子类是一一对应的。很好的解决了简单工厂模式的弊端 遵循了开闭原则单工厂模式代码。
   会有一个问题，就是如果新增加其他运算功能，需要创建一个抽象子类，但是还需要修改工厂类中的代码逻辑，
   这种设计是不符合开放封闭原则的。开放封闭原则对于修改是关闭的，对于扩展是开放的。
   而且将所有的操作子类的判断和实例化都由一个工厂类完成，如果业务比较复杂会导致工厂类负担较重。

   原理：
   工厂方法模式将之前负责生成具体抽象子类的工厂类，抽象为工厂抽象类和工厂子类组成的一系列类。每创建一个抽象子类，
   就需要创建一个工厂子类，并且一一对应，由工厂子类去生成对应的抽象子类，由外界使用方来决定生成哪个工厂子类。
   这样在增加新的需求时，就不需要对工厂抽象类进行修改，而是对应新增的抽象子类创建对应的工厂子类即可。
    类图：
    ![Image text](https://github.com/zhouzhaohui10001/designPattern/raw/master/images/factoryMethod.png)

    优点
    工厂方法模式的的优点在于更大的灵活性，增加或删除某种运算都不会对其他地方造成影响，更佳符合开放封闭原则。
    而且对抽象的使用更佳深入，将工厂类也抽象为了抽象工厂类和工厂子类，外界调用更加灵活，这也是对多态的一种体现。
    缺点
     工厂方法模式的缺点也是非常显而易见的，工厂方法模式中新增一个抽象子类，意味着工厂子类要跟着成对增加，
     这样会造成生成过多的类，工厂方法模式的复杂度也会随之增加。

3.抽象工厂模式
    抽象工厂模式和工厂方法模式很相似，但是抽象工厂模式将抽象发挥的更加极致，是三种工厂模式中最抽象的一种设计模式。抽象工厂模式，
    也叫做Kit模式，提供了创建一系列相关抽象子类的接口，而无需指定它们具体的类型。
    抽象工厂模式为创建一组对象提供了一种解决方案。与工厂方法模式相比，抽象工厂模式中的具体工厂不只是创建一种产品，
    它负责创建一族产品(相关联的多种产品)
    何时使用：系统的产品有多于一个的产品族，而系统只消费其中某一族的产品。
     如何解决：在一个产品族里面，定义多个产品。
    关键代码：在一个工厂里聚合多个同类产品。

   角色：
      AbstractFactory（抽象工厂）：它声明了一组用于创建一族产品的方法，每一个方法对应一种产品。
      ConcreteFactory（具体工厂）：它实现了在抽象工厂中声明的创建产品的方法，生成一组具体产品，
      这些产品构成了一个产品族，每一个产品都位于某个产品等级结构中。
      AbstractProduct（抽象产品）：它为每种产品声明接口，在抽象产品中声明了产品所具有的业务方法。
      ConcreteProduct（具体产品）：它定义具体工厂生产的具体产品对象，实现抽象产品接口中声明的业务方法。
      示例：Sunny软件公司欲开发一套界面皮肤库，可以对Java桌面软件进行界面美化。为了保护版权，该皮肤库源代码
      不打算公开，而只向用户提供已打包为jar文件的class字节码文件。用户在使用时可以通过菜单来选择皮肤，
      不同的皮肤将提供视觉效果不同的按钮、文本框、组合框等界面元素，其结构示意图如图1所示：
          ![Image text](https://github.com/zhouzhaohui10001/designPattern/raw/master/images/skin.png)
          像不像两个工厂抽象模式

   类图示例：
          ![Image text](https://github.com/zhouzhaohui10001/designPattern/raw/master/images/abstractFactory.png)
   适用场景
        在以下情况下可以考虑使用抽象工厂模式：
          (1) 一个系统不应当依赖于产品类实例如何被创建、组合和表达的细节，这对于所有类型的工厂模式都是很重要的，用户无须关心对象的创建过程，将对象的创建和使用解耦。
          (2) 系统中有多于一个的产品族，而每次只使用其中某一产品族。可以通过配置文件等方式来使得用户可以动态改变产品族，也可以很方便地增加新的产品族。
          (3) 属于同一个产品族的产品将在一起使用，这一约束必须在系统的设计中体现出来。同一个产品族中的产品可以是没有任何关系的对象，但是它们都具有一些共同的约束，如同一操作系统下的按钮和文本框，按钮与文本框之间没有直接关系，但它们都是属于某一操作系统的，此时具有一个共同的约束条件：操作系统的类型。
          (4) 产品等级结构稳定，设计完成之后，不会向系统中增加新的产品等级结构或者删除已有的产品等级结构。