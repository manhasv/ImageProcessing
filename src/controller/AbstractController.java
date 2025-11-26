package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import javax.imageio.ImageIO;

import functions.IComplexFunction;
import functions.IFunction;
import functions.complex.BrightenFunction;
import functions.complex.ScaleComponent;
import functions.simple.BlurFunction;
import functions.simple.GreyScaleFunction;
import functions.simple.HorizontalFlipFunction;
import functions.simple.SepiaFunction;
import functions.simple.SharpeningFunction;
import functions.simple.VerticalFlipFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;
import model.storage.IImageProcessing;

/**
 * This is the abstract class for the controller.
 * This class contains the method which helps loading/saving different types of images.
 */
public abstract class AbstractController implements IController {
  private final IImageProcessing model;

  /**
   * This is the standard constructor for this abstract class, which helps set up the model.
   *
   * @param model a model for ImageProcessing.
   */
  public AbstractController(IImageProcessing model) {
    if (model == null) {
      throw new IllegalArgumentException("Input should not be null");
    }
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public abstract void run() throws IllegalStateException;

  /**
   * This method sets up the map which contains the shortcut for different simple effects.
   * This method helps to set up the command pattern.
   *
   * @param map linking String and the Function Objects
   */
  protected void setMap(Map<String, IFunction> map) {
    map.put("horizontal-flip", new HorizontalFlipFunction());
    map.put("vertical-flip", new VerticalFlipFunction());
    map.put("blur", new BlurFunction());
    map.put("sharpening", new SharpeningFunction());
    map.put("sepia", new SepiaFunction());
    map.put("greyscale", new GreyScaleFunction());
  }

  /**
   * This method sets up the map which contains the shortcut for different simple effects.
   * This method helps to set up the command pattern.
   *
   * @param mapC linking String and the Complex Function Objects.
   */
  protected void setMapC(Map<String, IComplexFunction> mapC) {
    mapC.put("brighten", new BrightenFunction());
    mapC.put("value-component", new ScaleComponent());
    mapC.put("red-component", new ScaleComponent());
    mapC.put("blue-component", new ScaleComponent());
    mapC.put("green-component", new ScaleComponent());
    mapC.put("luma-component", new ScaleComponent());
    mapC.put("intensity-component", new ScaleComponent());
  }

  /**
   * This method helps to check if the use try to quit out of the app.
   *
   * @param str a String
   * @return true if the user input "q", "Q", "quit", or "Quit"
   */
  protected boolean checkQuit(String str) {
    return str.equalsIgnoreCase("q") || str.equalsIgnoreCase("quit");
  }

  /**
   * This method helps to read the file name and create the image accordingly.
   * This method only applies to PPM file since it is the odd one and is not supported by
   * Image.IO.
   *
   * @param filename the name of the initial file
   * @return a new image
   */
  protected IImage createImage(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("wrong file type!");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    IImage img = new ImagePPM(height, width, maxValue);
    IPixel[][] p = img.make2dPixel(sc);
    IImage ret = new ImagePPM(height, width, maxValue, p);
    return ret;
  }

  /**
   * This method helps to read the file name and create the image accordingly.
   * This method applies to PNG, JPG, and BMP files and all others supported by Imago.IO.
   *
   * @param filename the name of the initial file
   * @return a new image
   */
  protected IImage createImage2(String filename) {
    BufferedImage img;
    try {
      img = ImageIO.read(new File(filename));
    } catch (IOException e) {
      System.out.println("File " + filename + " not found!");
      throw new IllegalStateException();
    }
    System.out.println(img.getType());
    int height = img.getHeight();
    int width = img.getWidth();
    IImage image = new ImagePPM(height, width, 255);
    IPixel[][] array = image.make2dPixel2(img);
    IImage ret = new ImagePPM(height, width, 255, array);
    return ret;
  }

  /**
   * This method helps to save the file with a given pathname.
   * This method only applies to PPM file since it is the odd one and is not supported by
   * Image.IO.
   *
   * @param name a String name of the wanted File
   * @param str  a String of the contents of the image for the PPM file
   */
  protected void saveFile(String name, String str) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(name));
      writer.write("P3\n");
      writer.write(str);
      writer.close();
      System.out.println("Saved!");
    } catch (IOException e) {
      throw new IllegalStateException("Cannot save this image");
    }
  }

  /**
   * This method helps to save the file with a given pathname.
   * This method
   *
   * @param name the name of the wanted file
   * @param type the type of the file that we want to save
   * @param img  is the key value of the img in the storage
   * @throws IllegalStateException when the file is not writable
   */
  protected void saveFile2(String name, String type, String img) throws IllegalStateException {
    IImage image = this.model.getImage(img);
    BufferedImage buff = image.makeBuffer();
    System.out.println(type);
    System.out.println(type);
    File file = new File(name);

    try {
      ImageIO.write(buff, type, file);
      System.out.println("Saved!");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}

