package model.pixel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the tests for the PixelImpl.
 */
public class PixelImplTest {

  @Test
  public void testConstructorPixelImpl() {
    IPixel a = new PixelImpl(70, 70, 70);
    IPixel result = a.brighter(20);
    String str = "90 90 90 ";
    assertEquals(result.toString(), str);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegRed() {
    IPixel p1 = new PixelImpl(-10, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegGreen() {
    IPixel p = new PixelImpl(50, -10, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegBlue() {
    IPixel p3 = new PixelImpl(50, 100, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorLargerRed() {
    IPixel p4 = new PixelImpl(300, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorLargerGreen() {
    IPixel p5 = new PixelImpl(50, 300, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorLargerBlue() {
    IPixel p6 = new PixelImpl(50, 100, 300);
  }

  @Test
  public void testBrighten() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel result = a.brighter(20);
    String str = "70 120 120 ";
    assertEquals(result.toString(), str);
  }

  @Test
  public void testDarken() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel result = a.brighter(-20);
    String str = "30 80 80 ";
    assertEquals(result.toString(), str);
  }

  @Test
  public void testBrightenOver255() {
    IPixel a = new PixelImpl(100, 10, 100);
    IPixel result = a.brighter(200);
    String str = "255 210 255 ";
    assertEquals(result.toString(), str);
  }

  @Test
  public void testBrightenLess0() {
    IPixel a = new PixelImpl(100, 10, 100);
    IPixel result = a.brighter(-200);
    String str = "0 0 0 ";
    assertEquals(result.toString(), str);
  }


  @Test
  public void testChangePixelRed() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel result = a.changePixel("red-component");
    String str = "50 50 50 ";
    assertEquals(result.toString(), str);
  }

  @Test
  public void testChangePixelGreen() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel result = a.changePixel("green-component");
    String str = "100 100 100 ";
    assertEquals(result.toString(), str);
  }

  @Test
  public void testChangePixelBlue() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel result = a.changePixel("blue-component");
    String str = "100 100 100 ";
    assertEquals(result.toString(), str);
  }

  @Test
  public void testChangePixelIntensity() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel result = a.changePixel("intensity-component");
    String str = "83 83 83 ";
    assertEquals(result.toString(), str);
  }

  @Test
  public void testChangePixelValue() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel result = a.changePixel("value-component");
    String str = "100 100 100 ";
    assertEquals(result.toString(), str);
  }

  @Test
  public void testChangePixelLuma() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel result = a.changePixel("luma-component");
    String str = "89 89 89 ";
    assertEquals(result.toString(), str);
  }

  @Test
  public void testChangePixelMaxLuma() {
    IPixel a = new PixelImpl(255, 255, 255);
    IPixel result = a.changePixel("luma-component");
    String str = "254 254 254 ";
    assertEquals(result.toString(), str);
  }

  @Test
  public void testChangePixelGreyScale() {
    IPixel a = new PixelImpl(50, 200, 100);
    IPixel result = a.changePixel("greyscale");
    String str = "160 160 160 ";
    assertEquals(result.toString(), str);
  }

  @Test
  public void testChangePixelSepia() {
    IPixel a = new PixelImpl(50, 200, 100);
    IPixel result = a.changePixel("sepia");
    String str = "192 171 133 ";
    assertEquals(result.toString(), str);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePixelInvalidType1() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel result = a.changePixel("fbhrunu");
    String str = "50 100 100 ";
    assertEquals(result.toString(), str);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePixelInvalidType2() {
    IPixel a = new PixelImpl(50, 100, 100);
    IPixel result = a.changePixel("120");
    String str = "50 100 100 ";
    assertEquals(result.toString(), str);
  }

  @Test
  public void testToString() {
    IPixel a = new PixelImpl(50, 100, 100);
    assertEquals("50 100 100 ", a.toString());
  }
  
}