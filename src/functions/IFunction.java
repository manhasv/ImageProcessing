package functions;

import model.image.IImage;

/**
 * This is the interface represent all the simple function in the Image Processing.
 * A simple function is the function that only take in an image then do some processing operation
 * which include in the simple package.
 * The effect from simple package include: horizontal flip, vertical flip.
 */
public interface IFunction {
  /**
   * This method help to do some processing operation from the simple package to the input image.
   *
   * @param img an input image
   * @return a new image after doing some processing operation
   */
  IImage simpleGo(IImage img);
}
