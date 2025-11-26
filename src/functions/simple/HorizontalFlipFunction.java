package functions.simple;

import functions.IFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;

/**
 * This is the object class that represent the effect HorizontalFlip in the Image Processing.
 * This effect flip by the x-axis of the Image.
 */
public class HorizontalFlipFunction implements IFunction {

  @Override
  public IImage simpleGo(IImage img) {
    IPixel[][] p = img.pixelHelper();
    IImage ret = this.horizontalFlipHelper(p);
    return ret;
  }

  /**
   * This is the helper method that help to flip the image horizontally.
   * E.g:
   * 1 2 3 4
   * 5 6 7 8
   * will turn into
   * 4 3 2 1
   * 8 7 6 5
   */
  private IImage horizontalFlipHelper(IPixel[][] pixelArray) {
    for (int i = 0; i < pixelArray.length; i++) {
      for (int j = 0; j < pixelArray[0].length; j++) {
        int w = pixelArray[0].length - 1;
        IPixel p = pixelArray[i][j];
        pixelArray[i][j] = pixelArray[i][w - j];
        pixelArray[i][w - j] = p;

        if (j >= w / 2) {
          break;
        }
      }
    }
    return new ImagePPM(pixelArray.length, pixelArray[0].length, 255, pixelArray);
  }
}
