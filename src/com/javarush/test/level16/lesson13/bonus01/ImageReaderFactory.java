package com.javarush.test.level16.lesson13.bonus01;


import com.javarush.test.level16.lesson13.bonus01.common.*;

/**
 * Created by lollipop on 11.06.2016.
 */
public class ImageReaderFactory
{
    public static ImageReader getReader(ImageTypes types) {
        if (types == ImageTypes.PNG) return new PngReader();
        if (types == ImageTypes.JPG) return new JpgReader();
        if (types == ImageTypes.BMP) return new BmpReader();
        throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}
