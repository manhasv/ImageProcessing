package functions;

import model.image.IImage;

/**
 * This is the interface represent all the complex function in the Image Processing.
 * A complex function is the function that take in an image and a String to do some
 * processing operation from the complex package.
 * The effect from complex package include: changing to value, red, blue, or green component
 * and brighten image.
 */
public interface IComplexFunction {
  /**
   * This method help to do some processing operation from the complex package to the input image.
   *
   * @param img an input image
   * @return a new image after doing some processing operation
   */
  IImage complexGo(IImage img, String str);
}
