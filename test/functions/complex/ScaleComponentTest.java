package functions.complex;

import org.junit.Test;

import functions.IComplexFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;
import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the tests for the ScaleComponent.
 */
public class ScaleComponentTest {
  @Test
  public void testBlue() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(80, 90, 100);

    IPixel[][] input = {{a, b}, {c, d}};

    IComplexFunction func = new ScaleComponent();
    String str = "2 2 255 100 100 100 80 80 80 90 90 90 100 100 100 ";
    IImage imginput = new ImagePPM(2, 2, 255, input);
    IImage result = func.complexGo(imginput, "blue-component");

    assertEquals(result.toString(), str);
  }

  @Test
  public void testRed() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(80, 90, 100);

    IPixel[][] input = {{a, b}, {c, d}};

    IComplexFunction func = new ScaleComponent();
    String str = "2 2 255 50 50 50 50 50 50 60 60 60 80 80 80 ";
    IImage imginput = new ImagePPM(2, 2, 255, input);
    IImage result = func.complexGo(imginput, "red-component");

    assertEquals(result.toString(), str);
  }

  @Test
  public void testGreen() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(80, 90, 100);

    IPixel[][] input = {{a, b}, {c, d}};

    IComplexFunction func = new ScaleComponent();
    String str = "2 2 255 100 100 100 70 70 70 80 80 80 90 90 90 ";
    IImage imginput = new ImagePPM(2, 2, 255, input);
    IImage result = func.complexGo(imginput, "green-component");

    assertEquals(result.toString(), str);
  }

  @Test
  public void testValue() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(80, 90, 100);

    IPixel[][] input = {{a, b}, {c, d}};

    IComplexFunction func = new ScaleComponent();
    String str = "2 2 255 100 100 100 80 80 80 90 90 90 100 100 100 ";
    IImage imginput = new ImagePPM(2, 2, 255, input);
    IImage result = func.complexGo(imginput, "value-component");

    assertEquals(result.toString(), str);
  }

  @Test
  public void testLuma() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(80, 90, 100);

    IPixel[][] input = {{a, b}, {c, d}};

    IComplexFunction func = new ScaleComponent();
    String str = "2 2 255 89 89 89 66 66 66 76 76 76 88 88 88 ";
    IImage imginput = new ImagePPM(2, 2, 255, input);
    IImage result = func.complexGo(imginput, "luma-component");

    assertEquals(result.toString(), str);
  }

  @Test
  public void testIntensity() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(80, 90, 100);

    IPixel[][] input = {{a, b}, {c, d}};

    IComplexFunction func = new ScaleComponent();
    String str = "2 2 255 83 83 83 66 66 66 76 76 76 90 90 90 ";
    IImage imginput = new ImagePPM(2, 2, 255, input);
    IImage result = func.complexGo(imginput, "intensity-component");

    assertEquals(result.toString(), str);
  }

  @Test
  public void testGreyScale() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(80, 90, 100);

    IPixel[][] input = {{a, b}, {c, d}};

    IComplexFunction func = new ScaleComponent();
    String str = "2 2 255 89 89 89 66 66 66 76 76 76 88 88 88 ";
    IImage imginput = new ImagePPM(2, 2, 255, input);
    IImage result = func.complexGo(imginput, "greyscale");

    assertEquals(result.toString(), str);
  }

  @Test
  public void testSepia() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(80, 90, 100);

    IPixel[][] input = {{a, b}, {c, d}};

    IComplexFunction func = new ScaleComponent();
    String str = "2 2 255 115 102 80 88 78 61 102 90 70 119 106 82 ";
    IImage imginput = new ImagePPM(2, 2, 255, input);
    IImage result = func.complexGo(imginput, "sepia");

    assertEquals(result.toString(), str);
  }
}