import cn.cy.ocr.OCR;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhouzhaohui on 2017/8/16.
 */
public class OcrTest {

    public static void main(String[] args) {
        String path = "E://admin//timg.jpg";
        System.out.println("ORC Test Begin......");
        try {
            String valCode = new OCR().recognizeText(new File(path), "jpg");
            System.out.println(valCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ORC Test End......");
    }
}

