/**
 * Created by zhouzhaohui on 2017/8/4.
 */
public class AppTest {

    public static void main(String[] args) {
        double firstPay;
        double tmp =61;
        double num =0;
        for(int i=1;i<12;i++) {
            tmp=2*tmp;
            num= num+tmp;
        }
        System.out.println(num);
    }
}
