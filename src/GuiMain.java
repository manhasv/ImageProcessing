import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import controller.IController;
import controller.ImageController;

import controller.ImageGUIController;
import model.storage.IImageProcessing;
import model.storage.ImageModel;
import view.IView;
import view.ImageGuiView;

/**
 * This is the main class to implement to run this program. This main supports running
 * multiple ways for commandline.
 */
public class GuiMain {
  /**
   * The method used to run the program.
   *
   * @param args the Array containing the input.
   */

  public static void main(String[] args) throws FileNotFoundException {
    IImageProcessing model = new ImageModel();
    if (args.length >= 1) {
      for (int i = 0; i < args.length; i++) {
        Readable readable;
        if (args[i].equals("-file")) {
          readable = new FileReader(args[i + 1]);
          IController controller = new ImageController(model, readable);
          controller.run();
        } else if (args[i].equals("-text")) {
          readable = new InputStreamReader(System.in);
          IController controller = new ImageController(model, readable);
          controller.run();
        }
      }
    } else {
      IView view = new ImageGuiView();
      IController controller = new ImageGUIController(model, view);
    }
  }
}