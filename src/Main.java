import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import controller.IController;
import controller.ImageController;
import model.storage.IImageProcessing;
import model.storage.ImageModel;

/**
 * This is the main class to implement to run function of this program.
 */
public class Main {
  /**
   * The method used to run the program.
   *
   * @param args the Array containing the input.
   */
  public static void main(String[] args) throws FileNotFoundException {
    Readable readable = new InputStreamReader(System.in);

    if (args.length > 0) {
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-file")) {
          readable = new FileReader(args[i + 1]);
        }
      }
    }

    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, readable);
    controller.run();
  }
}