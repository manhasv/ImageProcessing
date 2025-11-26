package functions.simple;

import org.junit.Test;

import functions.IFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;
import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the tests for the SepiaFunction.
 */
public class SepiaFunctionTest {
  @Test
  public void testSepia() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(80, 90, 100);

    IPixel[][] input = {{a, b}, {c, d}};

    IFunction func = new SepiaFunction();
    String str = "2 2 255 115 102 80 88 78 61 102 90 70 119 106 82 ";
    IImage imginput = new ImagePPM(2, 2, 255, input);
    IImage result = func.simpleGo(imginput);

    assertEquals(result.toString(), str);
  }
}