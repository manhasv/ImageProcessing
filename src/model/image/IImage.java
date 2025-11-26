package model.image;

import java.awt.image.BufferedImage;
import java.util.Scanner;

import model.pixel.IPixel;

/**
 * This interface represents an Image.
 * It allows users and the programs to interact with the Image
 */
public interface IImage {
  /**
   * Return a String version of this image.
   *
   * @return a String
   */
  String toString();

  /**
   * Given a Scanner, read the remaining into the pixelArray.
   *
   * @param sc a Scanner with info about the image
   * @return a 2DArray Pixels containing the info of the image
   */
  IPixel[][] make2dPixel(Scanner sc);

  /**
   * Read the data from a BufferedImage and parse the value into a pixelArray correspondingly.
   *
   * @param image a BufferedImage with values of a given Image.
   * @return a 2DArray Pixels containing the info of the image
   */
  IPixel[][] make2dPixel2(BufferedImage image);

  /**
   * Copy the data of the current 2Darray of IPixels and parse them into a BufferedImage.
   *
   * @return a BufferedImage contains the info of this image.
   */
  BufferedImage makeBuffer();

  /**
   * Make a copy of this image's 2dArray of IPixels.
   *
   * @return a 2DArray of IPixels contains the info of this Image.
   */
  IPixel[][] pixelHelper();

}
