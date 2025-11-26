package functions.simple;

import functions.IFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;
import model.pixel.PixelImpl;

/**
 * This is the object class that represent the effect Blur in the Image Processing.
 * It helps blur the image, make it harder to make out the detail in an Image.
 *
 */
public class BlurFunction implements IFunction {

  @Override
  public IImage simpleGo(IImage img) {
    IPixel[][] p = img.pixelHelper();
    IImage ret = this.blur(p);
    return ret;
  }

  /**
   * This method help to go through a 2D Array and access each pixel and apply blur effect to them.
   * With the changed 2D Array, we can create a new IImage with the blurred Pixels.
   * It can also clamp the value of each channel if it exceeds the permitted range.
   * @param pixelArray the sample 2DArray from the original IImage.
   * @return a blurred IImage
   */
  private IImage blur(IPixel[][] pixelArray) {
    IPixel[][] ret = new IPixel[pixelArray.length][pixelArray[0].length];
    for (int i = 0; i < pixelArray.length; i++) {
      for (int j = 0; j < pixelArray[0].length; j++) {
        Double red = this.helperBlur("red", i, j, pixelArray);
        Double green = this.helperBlur("green", i, j, pixelArray);
        Double blue = this.helperBlur("blue", i, j, pixelArray);

        IPixel p = new PixelImpl(red.intValue(), green.intValue(), blue.intValue());
        ret[i][j] = p;
      }
    }
    return new ImagePPM(pixelArray.length, pixelArray[0].length, 255, ret);
  }

  /**
   * This method helps to apply the 3x3 kernel to a channel of a Pixel.
   * @param color the name of the channel red, green, or blue
   * @param i the row position of the given pixel
   * @param j the col position of the given pixel
   * @param pixelArray the sample 2D Array, so that we can draw the data from.
   * @return a new value for the wanted channel.
   */
  private double helperBlur(String color, int i, int j, IPixel[][] pixelArray) {
    return this.changeAll(color, i - 1, j - 1, 0.0625, pixelArray)
            + this.changeAll(color, i - 1, j, 0.125, pixelArray)
            + this.changeAll(color, i - 1, j + 1, 0.0625, pixelArray)
            + this.changeAll(color, i, j - 1, 0.125, pixelArray)
            + this.changeAll(color, i, j, 0.25, pixelArray)
            + this.changeAll(color, i, j + 1, 0.125, pixelArray)
            + this.changeAll(color, i + 1, j - 1, 0.0625, pixelArray)
            + this.changeAll(color, i + 1, j, 0.125, pixelArray)
            + this.changeAll(color, i + 1, j + 1, 0.0625, pixelArray);
  }

  /**
   * This helper method helps to change a value of a channel, and helps with kernel.
   * This method can check the edge cases when the pixel is at the rear of the 2dArray.
   * @param color the given color name of the channel in Pixel
   * @param i the row position of the given pixel
   * @param j the col position of the given pixel
   * @param level the value of the kernel channel
   * @param pixelArray the sample 2D Array, so that we can draw the data from.
   * @return a new component value for the wanted channel.
   */
  private double changeAll(String color, int i, int j, double level, IPixel[][] pixelArray) {
    double num;
    if (i < 0 || j < 0 || i > pixelArray.length - 1 || j > pixelArray[0].length - 1) {
      num = 0;
    } else {
      switch (color) {
        case "red":
          num = pixelArray[i][j].modRed(level);
          break;
        case "green":
          num = pixelArray[i][j].modGreen(level);
          break;
        case "blue":
          num = pixelArray[i][j].modBlue(level);
          break;
        default:
          throw new IllegalArgumentException("Can't find effect");
      }
    }
    return num;
  }
}
