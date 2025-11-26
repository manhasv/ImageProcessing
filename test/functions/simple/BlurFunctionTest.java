package functions.simple;

import org.junit.Test;

import functions.IFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;
import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the tests for the BlurFunction.
 */
public class BlurFunctionTest {
  @Test
  public void testBlur() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(160, 80, 40);
    IPixel e = new PixelImpl(80, 90, 100);
    IPixel f = new PixelImpl(50, 70, 90);
    IPixel g = new PixelImpl(160, 80, 40);
    IPixel h = new PixelImpl(120, 90, 90);
    IPixel i = new PixelImpl(80, 80, 80);
    IPixel k = new PixelImpl(160, 160, 160);
    IPixel m = new PixelImpl(100, 80, 90);
    IPixel n = new PixelImpl(88, 80, 90);

    IPixel[][] input = {{a, b, c, d},
        {e, f, g, h},
        {i, k, m, n}};

    String str = "4 3 255 31 49 53 47 59 63 71 58 53 72 46 35 55 68 73 86 88" +
            " 92 109 84 78 91 62 55 53 55 58 83 79 81 86 70 70 59 46 47 ";
    IFunction func = new BlurFunction();
    IImage imginput = new ImagePPM(3, 4, 255, input);
    IImage result = func.simpleGo(imginput);

    assertEquals(result.toString(), str);
  }
}
