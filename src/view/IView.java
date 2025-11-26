package view;

import java.awt.image.BufferedImage;

import controller.IViewListener;
import model.pixel.IPixel;

/**
 * This is an interface that represent a view for the Image Processing.
 * This view interface offer the ability that help in making all the UI desgin and listerning
 * for the command.
 */
public interface IView {

  /**
   * This method helps to link the GUI view with its listener.
   * With this, the view listener can listen to events enacted on the button and label on GUI
   * and behave accordingly to connect the data between model and GUI.
   *
   * @param listener the listener
   */
  void setViewListeners(IViewListener listener);

  /**
   * This mwthod helps to return the current path of the image that user are working with in
   * a String type.
   *
   * @return a string current path
   */
  String fileToOpen();

  /**
   * This method receive a BufferedImage Object from the controller and show it to the user.
   * If needed, we also add a scroll panel around this image.
   *
   * @param img an BufferedImage containing info of an image from the storage.
   */
  void receiveImage(BufferedImage img);

  /**
   * This is the method that help to add the histogram to the histogramPanel.
   *
   * @param p    a 2d arrya of pixel
   * @param type the type of the histogram such as red, green ,blue, or intensity
   */
  void addHistogram(IPixel[][] p, String type);

  /**
   * This method helps make a pop-up message panel, indicating a bug or error.
   * This can be triggered from the system if an Exception occurs.
   *
   * @param message a String to help users informed of the error.
   */
  void addMessage(String message);

  /**
   * This is the method that help to return the value when the users input in the brighten
   * function in String.
   *
   * @return the value of brighten in String
   */
  String degreeToChange();
}


