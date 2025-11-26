package model.pixel;

import java.awt.Color;

/**
 * this interface represents a Pixel in an image, containing three indexes of RGB.
 */
public interface IPixel {

  /**
   * This method return a pixel after brightening/darkenning the amount that user want
   * to change in a pixel.
   *
   * @param level the amount that user want to change in a pixel
   * @return IPixel the new Pixel
   */
  IPixel brighter(int level);

  /**
   * This method helps to accommodate the type of change and change the pixel accordingly.
   *
   * @param type a type that users want to change
   * @return a desired pixel
   */
  IPixel changePixel(String type);

  /**
   * Apply changes to the red channel of this pixel only.
   *
   * @param num the degree of change
   * @return a modified value for red
   */
  double modRed(double num);

  /**
   * Apply changes to the green channel of this pixel only.
   *
   * @param num the degree of change
   * @return a modified value for green
   */
  double modGreen(double num);

  /**
   * Apply changes to the blue channel of this pixel only.
   *
   * @param num the degree of change
   * @return a modified value for blue
   */
  double modBlue(double num);

  /**
   * Make a Color object contains the rgb value of this pixel.
   *
   * @return a Color object
   */
  Color makeColor();

  /**
   * This is method that would return the red value of the pixel.
   *
   * @return red value
   */
  int getRed();

  /**
   * This is the method that would return the green value of the pixel.
   *
   * @return green value
   */
  int getGreen();

  /**
   * This is the method that would return the blue value of the pixel.
   *
   * @return blue value
   */
  int getBlue();
}
