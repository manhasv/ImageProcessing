package model.image;

import org.junit.Test;

import model.pixel.IPixel;
import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the ImageModel class.
 */
public class ImagePPMTest {
  @Test
  public void testConstructorImagePPM() {
    IPixel a = new PixelImpl(50, 50, 50);
    IPixel b = new PixelImpl(40, 40, 40);
    IPixel c = new PixelImpl(30, 30, 30);
    IPixel d = new PixelImpl(20, 20, 20);

    IPixel[][] input = {{a, b}, {c, d}};
    IImage imginput = new ImagePPM(2, 2, 255, input);

    assertEquals(imginput.pixelHelper(), input);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorIPixelNull() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel[][] i = null;
    IImage img1 = new ImagePPM(6, 3, 255, i);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegHeight() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel[][] aa = {{a, b}};
    IImage img2 = new ImagePPM(-10, 3, 255, aa);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegWidth() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel[][] aa = {{a, b}};
    IImage img3 = new ImagePPM(6, -60, 255, aa);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegMaxValue() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel[][] aa = {{a, b}};
    IImage img4 = new ImagePPM(6, 3, -100, aa);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorTooLargeMaxValue() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel[][] aa = {{a, b}};
    IImage img5 = new ImagePPM(6, 3, 270, aa);
  }

  @Test
  public void testToString() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(80, 90, 100);
    IPixel[][] input = {{a, b}, {c, d}};
    IImage imginput = new ImagePPM(2, 2, 255, input);
    assertEquals("2 2 255 50 100 100 50 70 80 60 80 90 80 90 100 ", imginput.toString());
  }

}