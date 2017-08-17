package cn.cy.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

import javax.imageio.*;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import com.sun.media.imageio.plugins.tiff.TIFFImageWriteParam;
/**
 * Created by zhouzhaohui on 2017/8/16.
 */
public class ImageIOHelper {

    public static File createImage(File imageFile, String imageFormat) {
        File tempFile = null;
        try {
            Iterator readers = ImageIO.getImageReadersByFormatName(imageFormat);
            ImageReader reader = (ImageReader) readers.next();

            ImageInputStream iis = ImageIO.createImageInputStream(imageFile);
            reader.setInput(iis);
            //Read the stream metadata
            IIOMetadata streamMetadata = reader.getStreamMetadata();

            //Set up the writeParam
            TIFFImageWriteParam tiffWriteParam = new TIFFImageWriteParam(Locale.CHINESE);
            tiffWriteParam.setCompressionMode(ImageWriteParam.MODE_DISABLED);

            //Get tif writer and set output to file
            Iterator writers = ImageIO.getImageWritersByFormatName("tiff");
            ImageWriter writer = (ImageWriter) writers.next();

            BufferedImage bi = reader.read(0);
            IIOImage image = new IIOImage(bi,null,reader.getImageMetadata(0));
            tempFile = tempImageFile(imageFile);
            ImageOutputStream ios = ImageIO.createImageOutputStream(tempFile);
            writer.setOutput(ios);
            writer.write(streamMetadata, image, tiffWriteParam);
            ios.close();

            writer.dispose();
            reader.dispose();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    private static File tempImageFile(File imageFile) {
        String path = imageFile.getPath();
        StringBuffer strB = new StringBuffer(path);
        strB.insert(path.lastIndexOf('.'),0);
        return new File(strB.toString().replaceFirst("(?<=//.)(//w+)$", "tif"));
    }
}
