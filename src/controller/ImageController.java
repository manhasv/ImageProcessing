package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import functions.IComplexFunction;
import functions.IFunction;
import model.image.IImage;
import model.storage.IImageProcessing;

/**
 * This class represents the controller of the Image Processing.
 * This controller take in a Readable which will contain the commands. The controller then
 * interpret and execute them.
 */
public class ImageController extends AbstractController {
  private Readable myInputStream;
  private final IImageProcessing model;
  private final Map<String, IFunction> map;
  private final Map<String, IComplexFunction> mapC;


  /**
   * The standard constructor for the controller ImageController.
   *
   * @param model is a MarbleSolitaireModel
   * @param in    is a Readable
   * @throws IllegalArgumentException if any of the argument is null
   */
  public ImageController(IImageProcessing model, Readable in)
          throws IllegalArgumentException {
    super(model);
    if (model == null || in == null) {
      throw new IllegalArgumentException("Input should not be null");
    }
    this.myInputStream = Objects.requireNonNull(in);
    this.model = Objects.requireNonNull(model);
    this.map = new HashMap<>();
    this.setMap(map);
    this.mapC = new HashMap<>();
    this.setMapC(mapC);

  }

  @Override
  public void run() throws IllegalStateException {
    Scanner scanner = new Scanner(myInputStream);
    try {
      while (scanner.hasNext()) {
        String input;
        input = scanner.next();
        if (this.checkQuit(input)) {
          System.out.println("Quitted");
          break;
        }
        this.caseLoad(input, scanner);
        this.caseSave(input, scanner);
        this.caseMap(input, scanner);
        this.caseMapC(input, scanner);
      }
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Throw Illegal State Exception.");
    }
  }

  /**
   * Handling the loading command from the terminal/console when using the text-UI.
   * @param input the command.
   * @param scanner the inputStream.
   */
  private void caseLoad(String input, Scanner scanner) {
    if (input.equalsIgnoreCase("load")) {
      String name = scanner.next();
      IImage img;
      if (name.length() > 3) {
        String type = name.substring(name.length() - 3);
        if (type.equalsIgnoreCase("ppm")) {
          img = this.createImage(name);
        } else {
          img = this.createImage2(name);
        }
      } else {
        throw new IllegalStateException();
      }

      String out = scanner.next();

      this.model.load(img, out);
      System.out.println("Loaded!");
    }
  }

  /**
   * Handling the saving command from the terminal/console when using the text-UI.
   * @param input the command.
   * @param scanner the inputStream.
   */
  private void caseSave(String input, Scanner scanner) {
    if (input.equalsIgnoreCase("save")) {
      String name = scanner.next();
      String out = scanner.next();
      String str = model.saveImg(out);
      if (name.length() > 3) {
        String type = name.substring(name.length() - 3);
        if (type.equalsIgnoreCase("ppm")) {
          this.saveFile(name, str);
        } else {
          this.saveFile2(name, type, out);
        }
      } else {
        throw new IllegalStateException();
      }
    }
  }

  /**
   * Handling the simple command from the terminal/console when using the text-UI.
   * @param input the command.
   * @param scanner the inputStream.
   */
  private void caseMap(String input, Scanner scanner) {
    if (map.containsKey(input)) {
      IFunction func = this.map.get(input);
      String in = scanner.next();
      String out = scanner.next();
      try {
        model.effect(func, in, out);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Invalid simple function\n");
      }
    }
  }

  /**
   * Handling the complex command from the terminal/console when using the text-UI.
   * @param input the command.
   * @param scanner the inputStream.
   */
  private void caseMapC(String input, Scanner scanner) {
    if (mapC.containsKey(input)) {
      IComplexFunction func = this.mapC.get(input);
      String type = "";
      if (input.equalsIgnoreCase("brighten")) {
        type = scanner.next();

      } else {
        type = input;
      }
      String in = scanner.next();
      String out = scanner.next();
      try {
        model.complexEff(func, type, in, out);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Invalid complex function\n");
      }
    }
  }
}


