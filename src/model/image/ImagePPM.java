package model.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

import model.pixel.IPixel;
import model.pixel.PixelImpl;

/**
 * This class represents an Image of file ppm, including its height, width, maxValue
 * and the pixel of the image.
 */
public class ImagePPM implements IImage {
  private final int height;
  private final int width;
  private final int maxValue;
  private final IPixel[][] pixelArray;

  /**
   * This is the constructor of the ImagePPM class that take the height,
   * the width, and the maxValue of the image.
   *
   * @param height   the height of the image
   * @param width    the width of the image
   * @param maxValue the max value of a pixel
   */
  public ImagePPM(int height, int width, int maxValue) {
    this(height, width, maxValue, new IPixel[height][width]);
  }

  /**
   * This is the constructor of the ImagePPM class that take the height,
   * the width, the maxValue of the image, and a pixel array.
   *
   * @param height     the height of the image
   * @param width      the width of the image
   * @param maxValue   the max value of a pixel
   * @param pixelArray this array represents an array of pixel in an image
   */
  public ImagePPM(int height, int width, int maxValue, IPixel[][] pixelArray) {
    if (height < 0 || width < 0 || maxValue < 0 || maxValue > 255 || pixelArray == null) {
      throw new IllegalArgumentException("Incorrect input for ImagePPM");
    }
    this.height = height;
    this.width = width;
    this.maxValue = maxValue;
    this.pixelArray = pixelArray;
  }

  @Override
  public IPixel[][] pixelHelper() {
    IPixel[][] ret = new IPixel[pixelArray.length][];
    for (int i = 0; i < pixelArray.length; i++) {
      ret[i] = pixelArray[i].clone();
    }
    return ret;
  }

  @Override
  public BufferedImage makeBuffer() {
    BufferedImage buff = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color c = this.pixelArray[i][j].makeColor();
        int rgb = c.getRGB();
        buff.setRGB(j, i, rgb);
      }
    }
    return buff;
  }

  @Override
  public String toString() {
    Appendable str = new StringBuilder();
    try {
      str.append(String.format("%s ", this.width));
      str.append(String.format("%s ", this.height));
      str.append(String.format("%s ", this.maxValue));
      this.toStringPArray(str);
    } catch (IOException e) {
      throw new IllegalArgumentException("Illegal Argument Exception.");
    }
    return str.toString();
  }

  /**
   * Return a String of the 2D array in this image.
   *
   * @param ap an Appendable containing the information
   */
  private void toStringPArray(Appendable ap) {
    for (int i = 0; i < pixelArray.length; i++) {
      for (int j = 0; j < pixelArray[1].length; j++) {
        try {
          ap.append(String.format("%s", this.pixelArray[i][j].toString()));
        } catch (IOException e) {
          throw new IllegalArgumentException("Illegal Argument Exception.");
        }
      }
    }
  }

  @Override
  public IPixel[][] make2dPixel(Scanner sc) {
    IPixel[][] pixel = new IPixel[this.height][this.width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        IPixel p = new PixelImpl(r, g, b);
        pixel[i][j] = p;
      }
    }
    return pixel;
  }

  @Override
  public IPixel[][] make2dPixel2(BufferedImage image) {
    IPixel[][] pixel = new IPixel[this.height][this.width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int p = image.getRGB(j, i);
        Color c = new Color(p);
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        IPixel pix = new PixelImpl(r, g, b);
        pixel[i][j] = pix;
      }
    }
    return pixel;
  }
}