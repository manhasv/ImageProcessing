package functions.simple;

import org.junit.Test;

import functions.IFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;
import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the tests for the GreyScaleFunction.
 */
public class GreyScaleFunctionTest {
  @Test
  public void greyScaleTest() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(80, 90, 100);

    IPixel[][] input = {{a, b}, {c, d}};

    IFunction func = new GreyScaleFunction();
    String str = "2 2 255 89 89 89 66 66 66 76 76 76 88 88 88 ";
    IImage imginput = new ImagePPM(2, 2, 255, input);
    IImage result = func.simpleGo(imginput);

    assertEquals(result.toString(), str);
  }
}