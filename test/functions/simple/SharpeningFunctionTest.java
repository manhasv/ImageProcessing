package functions.simple;

import org.junit.Test;

import functions.IFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;
import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the tests for the SharpeningFunction.
 */
public class SharpeningFunctionTest {
  @Test
  public void testSharpening() {
    IPixel a1 = new PixelImpl(40, 160, 80);
    IPixel b1 = new PixelImpl(4, 8, 16);
    IPixel c1 = new PixelImpl(80, 80, 80);
    IPixel d1 = new PixelImpl(32, 40, 4);
    IPixel e1 = new PixelImpl(16, 16, 16);

    IPixel a2 = new PixelImpl(40, 160, 80);
    IPixel b2 = new PixelImpl(4, 8, 16);
    IPixel c2 = new PixelImpl(80, 80, 80);
    IPixel d2 = new PixelImpl(32, 40, 4);
    IPixel e2 = new PixelImpl(16, 16, 16);

    IPixel a3 = new PixelImpl(4, 8, 16);
    IPixel b3 = new PixelImpl(40, 160, 80);
    IPixel c3 = new PixelImpl(40, 160, 80);
    IPixel d3 = new PixelImpl(32, 40, 4);
    IPixel e3 = new PixelImpl(40, 160, 80);

    IPixel a4 = new PixelImpl(4, 8, 16);
    IPixel b4 = new PixelImpl(4, 8, 16);
    IPixel c4 = new PixelImpl(80, 80, 80);
    IPixel d4 = new PixelImpl(16, 16, 16);
    IPixel e4 = new PixelImpl(16, 16, 16);

    IPixel a5 = new PixelImpl(16, 16, 16);
    IPixel b5 = new PixelImpl(40, 160, 80);
    IPixel c5 = new PixelImpl(80, 80, 80);
    IPixel d5 = new PixelImpl(32, 40, 4);
    IPixel e5 = new PixelImpl(40, 160, 80);


    IPixel[][] input = {{a1, b1, c1, d1, e1},
        {a2, b2, c2, d2, e2},
        {a3, b3, c3, d3, e3},
        {a4, b4, c4, d4, e4},
        {a5, b5, c5, d5, e5}};

    String str = "5 5 255 21 143 66 42 74 76 84 14 53 68 31 18 2 0 0 27 194 88 61 183 126 111 " +
            "133 97 95 151 64 15 36 0 0 0 0 53 203 128 49 127 69 65 87 38 8 100 31 0 25 16 50 " +
            "128 102 114 162 117 79 141 85 15 63 19 0 0 2 65 155 107 94 65 80 65 42 29 22 113 48 ";


    IFunction func = new SharpeningFunction();
    IImage imginput = new ImagePPM(5, 5, 255, input);
    IImage result = func.simpleGo(imginput);

    assertEquals(result.toString(), str);
  }
}
