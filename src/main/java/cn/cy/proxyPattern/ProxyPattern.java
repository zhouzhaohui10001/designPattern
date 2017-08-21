package cn.cy.proxyPattern;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhouzhaohui on 2017/8/21.
 */
//---------------------------静态代理-----------------------------------
interface IUserDao {
    void save();
}
class UserDaoImpl implements IUserDao {

    @Override
    public void save() {
        System.out.println("数据保存成功。。。");
    }
}
class UserDaoProxy implements IUserDao {
    private IUserDao userDao;
    public UserDaoProxy(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println("开始事务。。。。");
        userDao.save();
        System.out.println("提交事务。。。。");
    }
}
//------------JDK动态代理--------------------------------------
class UserDaoJDKProxy {
    private Object target;
    public UserDaoJDKProxy(Object target) {
        this.target = target;
    }

//    getProxyInstance 给目标生成代理对象
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开始事务。。。。。。");
                Object returnValue = method.invoke(target,args);
                System.out.println("提交事务。。。。。。");
                return returnValue;
            }
        });
    }
}
//--------------cglib动态代理------------------------------------------
class MyInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // o 是代理类的对象。CGLib生成的代理类是目标类的子类
        // + System.out.println(Business.class.isInstance(o)); 会打印 true
        // objects 是参数列表
        // method 是目标方法
        // 直接使用 method.invoke(o, objects); 方式，或导致StackOverFlow
        // + 因为 代理类重写了父类的该方法，所以其实调用的是代理类的方法，
        // + 而代理类的方法，又调用了intercept方法。。。

// 因此，调用父类的方法，应该使用MethodProxy.invokeSuper()方法
        System.out.println("开始事务。。。。。。。。。。");
//        注意 这里调用的是invokeSuper 因为o为代理对象，如果想用invoke要把目标类传过来（如设置为成员变量）
        Object returnValue = methodProxy.invokeSuper(o,objects);
        System.out.println("提交事务。。。。。。。");
        return returnValue;
    }
}
class CglibProxy {
    private Object target;
    public CglibProxy(Object target) {
        this.target = target;
    }
    public Object getProxyInstance() {
//        1.工具类
        Enhancer enhancer = new Enhancer();
//        2.设置父类
        enhancer.setSuperclass(target.getClass());
//        3.设置回调函数
        enhancer.setCallback(new MyInterceptor());
//        4.创建子类（代理对象）
       return enhancer.create();
    }
}
public class ProxyPattern {
    public static void main(String[] args) {
//        静态代理
        IUserDao target = new UserDaoImpl();
        UserDaoProxy proxy = new UserDaoProxy(target);
        proxy.save();
//        JDK动态代理
        IUserDao proxyJDK = (IUserDao)new UserDaoJDKProxy(target).getProxyInstance();
        proxyJDK.save();
//        Cglib动态代理
        UserDaoImpl userDao = new UserDaoImpl();
        UserDaoImpl cglibProxy = (UserDaoImpl)new CglibProxy(userDao).getProxyInstance();
        cglibProxy.save();

    }
}
