package functions.complex;

import functions.IComplexFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;

/**
 * This class represents the scale components function.
 * It can handle effects such as "blue-component", "luma-component", etc.
 * This method can apply change to Image at the Pixel-level.
 */

public class ScaleComponent implements IComplexFunction {
  @Override
  public IImage complexGo(IImage img, String str) {
    IPixel[][] p = img.pixelHelper();
    IImage ret = this.scaleImage(str, p);
    return ret;
  }

  /**
   * This method help to scale an Image using the given type.
   *
   * @param type the effect that users want to apply to the image
   */
  private IImage scaleImage(String type, IPixel[][] pixelArray) {
    for (int i = 0; i < pixelArray.length; i++) {
      for (int j = 0; j < pixelArray[0].length; j++) {
        IPixel p = pixelArray[i][j].changePixel(type);
        pixelArray[i][j] = p;
      }
    }
    return new ImagePPM(pixelArray.length, pixelArray[0].length, 255, pixelArray);
  }
}
