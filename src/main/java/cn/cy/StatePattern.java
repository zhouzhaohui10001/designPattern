package cn.cy;

/**
 * Created by zhou on 2017/8/18.
 * http://www.cnblogs.com/ysw-go/p/5404918.html
 * http://www.cnblogs.com/itTeacher/archive/2012/12/04/2801597.html
 */
//电梯state类
abstract class LiftState {
    Lift lift;
    public LiftState(Lift lift) {
        this.lift = lift;
    }
//开门
    abstract void open();
//关门
    abstract void close();
//运行
    abstract void run();
//停止
    abstract void stop();
}
class OpenState extends LiftState {

    public OpenState(Lift lift) {
        super(lift);
    }

    @Override
    void open() {
        System.out.println("处于开门状态，什么也不做");
    }

    @Override
    void close() {
        System.out.println("处于开门状态：关门");
        lift.setLiftState(lift.getCloseState());
    }

    @Override
    void run() {
        System.out.println("处于开门状态，什么也不做");
    }

    @Override
    void stop() {
        System.out.println("处于开门状态，什么也不做");
    }
}
class CloseState extends LiftState {

    public CloseState(Lift lift) {
        super(lift);
    }

    @Override
    void open() {
        System.out.println("处于关门状态，什么也不做");
    }

    @Override
    void close() {
        System.out.println("处于关门状态，什么也不做");
    }

    @Override
    void run() {
        System.out.println("处于关门状态：运行");
        lift.setLiftState(lift.getRunState());
    }

    @Override
    void stop() {
        System.out.println("处于关门状态，什么也不做");
    }
}
class RunState extends LiftState {
    public RunState(Lift lift) {
        super(lift);
    }

    @Override
    void open() {
        System.out.println("处于运行状态，什么也不做");
    }

    @Override
    void close() {
        System.out.println("处于运行状态，什么也不做");
    }

    @Override
    void run() {
        System.out.println("处于运行状态，什么也不做");
    }

    @Override
    void stop() {
        System.out.println("处于运行状态：停止ֹ");
        lift.setLiftState(lift.getStopState());
    }
}
class StopState extends LiftState {

    public StopState(Lift lift) {
        super(lift);
    }
    @Override
    void open() {
        System.out.println("处于停止状态：开门");
        lift.setLiftState(lift.getOpenState());
    }

    @Override
    void close() {
        System.out.println("处于停止状态，什么也不做");
    }

    @Override
    void run() {
        System.out.println("处于停止状态：运行");
        lift.setLiftState(lift.getStopState());
    }

    @Override
    void stop() {
        System.out.println("处于停止状态，什么也不做");
    }
}
//Context
class Lift {
    LiftState openState ;
    LiftState closeState;
    LiftState runState;
    LiftState stopState;
    public Lift() {
        openState = new OpenState(this);
        closeState = new CloseState(this);
        runState = new RunState(this);
        stopState = new StopState(this);
        liftState = stopState;
    }
    private LiftState liftState;
    //委托给状态对象执行
    public void stop() {
        liftState.stop();
    }

    public void run() {
        liftState.run();
    }

    public void close() {
        liftState.close();
    }

    public void open() {
        liftState.open();
    }
    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
    }

    public LiftState getStopState() {
        return stopState;
    }

    public LiftState getOpenState() {
        return openState;
    }

    public LiftState getCloseState() {
        return closeState;
    }

    public LiftState getRunState() {
        return runState;
    }
}
public class StatePattern {
    public static void main(String[] args) {
        Lift lift = new Lift();
        lift.run();
        lift.close();
        lift.stop();
        lift.open();
    }
}
