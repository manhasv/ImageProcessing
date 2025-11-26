package functions.complex;

import functions.IComplexFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;

/**
 * This is the object class that represent the bright effect of the Image Processing.
 * This function offers the ability to increase/decrease the pixel value of an image by
 * a given degree.
 * This allows users to brighten or darken an Image.
 */

public class BrightenFunction implements IComplexFunction {
  @Override
  public IImage complexGo(IImage img, String str) {
    IPixel[][] p = img.pixelHelper();
    int degree;
    try {
      degree = Integer.parseInt(str);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("This is not a number!");
    }
    IImage ret = this.brighterImage(degree, p);
    return ret;
  }

  /**
   * This method help to change the level in rgb for the whole image.
   * It can be used to brighten if given a positive level
   * otherwise, it can also be used to darken an image if given a negative level
   *
   * @param level the amount that user want to change in rgb
   */
  private IImage brighterImage(int level, IPixel[][] pixelArray) {
    for (int i = 0; i < pixelArray.length; i++) {
      for (int j = 0; j < pixelArray[0].length; j++) {
        IPixel p = pixelArray[i][j].brighter(level);
        pixelArray[i][j] = p;
      }
    }
    return new ImagePPM(pixelArray.length, pixelArray[0].length, 255, pixelArray);
  }
}
