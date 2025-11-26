package functions.simple;

import org.junit.Test;

import functions.IFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;
import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the tests for the VerticalFlipFunction.
 */
public class VerticalFlipFunctionTest {
  @Test
  public void testVerticalFlipFunction() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(80, 90, 100);

    IPixel[][] input = {{a, b}, {c, d}};

    IFunction func = new VerticalFlipFunction();
    String str = "2 2 255 60 80 90 80 90 100 50 100 100 50 70 80 ";
    IImage imgInput = new ImagePPM(2, 2, 255, input);
    IImage result = func.simpleGo(imgInput);

    assertEquals(result.toString(), str);
  }
}