package controller;

/**
 * This is an interface that represent a controller for the Image Processing.
 * This interface offer the ability to interact with Image through console/command-line/script
 */

public interface IController {
  /**
   * The run method to initiate the ImageProcessing program.
   *
   * @throws IllegalStateException when there is unexpected error in the program
   */
  void run() throws IllegalStateException;
}
