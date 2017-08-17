package cn.cy.facadePattern;

/**
 * Created by zhouzhaohui on 2017/8/17.
 */
//写信的业务接口
interface ILetterProcess {
//    写信内容
    void writeContext(String context);
//    写信的地址
    void fillEnvelope(String address);
//    将信装入信封
    void letterInotoEnvelope();
//   发送信
    void sendLetter();
}
//业务实现类
class LetterProcessImpl implements ILetterProcess {

    @Override
    public void writeContext(String context) {
        System.out.println("LetterProcessImpl.writeContext()写信的内容:"+context);
    }

    @Override
    public void fillEnvelope(String address) {
        System.out.println("LetterProcessImpl.fillEnvelope()写信的邮寄地址："+address);
    }

    @Override
    public void letterInotoEnvelope() {
        System.out.println("LetterProcessImpl.letterInotoEnvelope()将信装入信封");
    }

    @Override
    public void sendLetter() {
        System.out.println("LetterProcessImpl.sendLetter()邮寄信");
    }
}
//门面类
class LetterFacade {
    private ILetterProcess letterProcessImpl;
    public LetterFacade(ILetterProcess letterProcessImpl) {
        this.letterProcessImpl = letterProcessImpl;
    }

    public void  sendLetter(String context,String address) {
        letterProcessImpl.writeContext(context);
        letterProcessImpl.fillEnvelope(address);
        letterProcessImpl.letterInotoEnvelope();
        letterProcessImpl.sendLetter();
    }
}

public class FacadePattern {
    public static void main(String[] args) {
        ILetterProcess letterProcess = new LetterProcessImpl();
        LetterFacade facade = new LetterFacade(letterProcess);
        facade.sendLetter("hahahha","onetwothree");
    }
}
