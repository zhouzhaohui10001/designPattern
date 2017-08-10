适配器模式笔记
参考文章：http://www.cnblogs.com/java-my-life/archive/2012/04/13/2442795.html
适配器模式把一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
适配器模式有类的适配器模式和对象的适配器模式两种不同的形式。
类适配器模式（主要是继承）
  类的适配器模式把适配的类的API转换成为目标类的API。
类图如下：
   ![Image text](https://github.com/zhouzhaohui10001/designPattern/raw/master/images/classAdapter.png)
在上图中可以看出，Adaptee类并没有sampleOperation2()方法，而客户端则期待这个方法。为使客户端能够使用Adaptee类，
提供一个中间环节，即类Adapter，把Adaptee的API与Target类的API衔接起来。Adapter与Adaptee是继承关系，
这决定了这个适配器模式是类的：
模式所涉及的角色有：
       目标(Target)角色：这就是所期待得到的接口。注意：由于这里讨论的是类适配器模式，因此目标不可以是类。
       源(Adapee)角色：现在需要适配的接口。
       适配器(Adaper)角色：适配器类是本模式的核心。适配器把源接口转换成目标接口。显然，这一角色不可以是接口，
       而必须是具体类。
示例：像是你的电脑的电源一样，可以将220v的电压转化为你电脑能够承受的电压，又如从美国带回来的电器，
需要一个适配电源将电压220v改成110v

对象适配器
  与类的适配器模式一样，对象的适配器模式把被适配的类的API转换成为目标类的API，与类的适配器模式不同的是，
  对象的适配器模式不是使用继承关系连接到Adaptee类，
  而是使用委派关系连接到Adaptee类。（感觉项目中这种用的多 错觉？？）
类图
    ![Image text](https://github.com/zhouzhaohui10001/designPattern/raw/master/images/objectAdapter.png)
从上图可以看出，Adaptee类并没有sampleOperation2()方法，而客户端则期待这个方法。为使客户端能够使用Adaptee类，
需要提供一个包装(Wrapper)类Adapter。
这个包装类包装了一个Adaptee的实例，从而此包装类能够把Adaptee的API与Target类的API衔接起来。
Adapter与Adaptee是委派关系，这决定了适配器模式是对象的。
