package cn.cy.chainPattern;

/**
 * Created by zhou on 2017/8/19.
 */
//抽象处理者
abstract class Handler {
    protected  Handler nextHandler = null;

    public Handler getNextHandler() {
        return nextHandler;
    }
//设置下一个请求的对象
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
//    处理聚餐费用的申请
    abstract String handleFeeRequest(String user,double fee);
}
//具体的处理角色
class ProjectManager extends Handler {

    @Override
    String handleFeeRequest(String user, double fee) {
        String returnStr = "";
        if(fee<500) {
            if("张三".equals(user))
            {
                returnStr = "成功：项目经理同意【" + user + "】的聚餐费用，金额为" + fee + "元";
            }else
            {
                //其他人一律不同意
                returnStr = "失败：项目经理不同意【" + user + "】的聚餐费用，金额为" + fee + "元";
            }
        }else {
            //超过500，继续传递给级别更高的人处理
            if(getNextHandler() != null)
            {
                return getNextHandler().handleFeeRequest(user, fee);
            }
        }

        return returnStr;
    }
}
class DeptManager extends Handler {

    @Override
    String handleFeeRequest(String user, double fee) {
        String str = "";
        //部门经理的权限只能在1000以内
        if(fee < 1000)
        {
            //为了测试，简单点，只同意张三的请求
            if("张三".equals(user))
            {
                str = "成功：部门经理同意【" + user + "】的聚餐费用，金额为" + fee + "元";
            }else
            {
                //其他人一律不同意
                str = "失败：部门经理不同意【" + user + "】的聚餐费用，金额为" + fee + "元";
            }
        }else
        {
            //超过1000，继续传递给级别更高的人处理
            if(getNextHandler() != null)
            {
                return getNextHandler().handleFeeRequest(user, fee);
            }
        }
        return str;
    }

}

class GeneralManager extends Handler {

    @Override
    String handleFeeRequest(String user, double fee) {
        String str = "";
        //总经理的权限很大，只要请求到了这里，他都可以处理
        if(fee >= 1000)
        {
            //为了测试，简单点，只同意张三的请求
            if("张三".equals(user))
            {
                str = "成功：总经理同意【" + user + "】的聚餐费用，金额为" + fee + "元";
            }else
            {
                //其他人一律不同意
                str = "失败：总经理不同意【" + user + "】的聚餐费用，金额为" + fee + "元";
            }
        }else
        {
            //如果还有后继的处理对象，继续传递
            if(getNextHandler() != null)
            {
                return getNextHandler().handleFeeRequest(user, fee);
            }
        }
        return str;
    }

}
public class ChainPattern {
    public static void main(String[] args) {
        Handler h1 = new ProjectManager();
        Handler h2 = new DeptManager();
        Handler h3 = new GeneralManager();
        h1.setNextHandler(h2);
        h2.setNextHandler(h3);
        String str = h1.handleFeeRequest("张三",700);
        System.out.println(str);
    }
}
