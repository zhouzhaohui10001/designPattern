/**
 * Created by zhouzhaohui on 2017/8/22.
 */
public class AutoPackTest {

    public static void main(String[] args) {
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        int i3 =1;
        System.out.println(i1==i2);
        System.out.println(i1==i3);
        System.out.println(i2==i3);
        Integer i4 = new Integer(145);
        int i5 = 145;
        System.out.println(i4==i5);
        Integer i6 = 127;
        int i7 = 127;
        System.out.println(i6==i7);
        Integer i8 = 128;
        int i9 = 128;
        System.out.println(i8==i9);
        Integer i10 = 127;
        System.out.println(i10==i6);
        Integer i11= 128;
        System.out.println(i11==i8);
    }
}
