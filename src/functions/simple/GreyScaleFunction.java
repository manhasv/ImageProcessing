package functions.simple;

import functions.IFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;

/**
 * This is the function object class of the greyscale Function.
 * This offers the ability to change the Image to a greyScaled Image, which is essentially
 * applying a matrix to each pixel to a corresponding value which offer a grey-like view to
 * the original Image.
 */
public class GreyScaleFunction implements IFunction {
  @Override
  public IImage simpleGo(IImage img) {
    IPixel[][] p = img.pixelHelper();
    IImage ret = this.scaleImage("greyscale", p);
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