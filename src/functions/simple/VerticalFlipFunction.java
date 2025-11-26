package functions.simple;

import functions.IFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;

/**
 * This is the object class that represent the effect VerticalFlip in the Image Processing.
 * This effect flip by the y-axis of the Image.
 */
public class VerticalFlipFunction implements IFunction {
  @Override
  public IImage simpleGo(IImage img) {
    IPixel[][] p = img.pixelHelper();
    IImage ret = this.verticalFlipHelper(p);
    return ret;
  }

  /**
   * This is the helper method that help to flip the image vertically, by the y-axis.
   * E.g:
   * 1 2 3 4
   * 5 6 7 8
   * will turn into
   * 5 6 7 8
   * 1 2 3 4
   */
  private IImage verticalFlipHelper(IPixel[][] pixelArray) {
    for (int r = 0; r < pixelArray.length; r++) {
      int h = pixelArray.length - 1;
      for (int c = 0; c < pixelArray[0].length; c++) {
        IPixel p = pixelArray[r][c];
        pixelArray[r][c] = pixelArray[h - r][c];
        pixelArray[h - r][c] = p;
      }
      if (r >= h / 2) {
        break;
      }
    }
    return new ImagePPM(pixelArray.length, pixelArray[0].length, 255, pixelArray);
  }
}
