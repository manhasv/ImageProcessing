package controller;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import functions.IComplexFunction;
import functions.IFunction;
import model.image.IImage;
import model.storage.IImageProcessing;
import view.IView;

/**
 * This class represents the controller to handle the GUI of the Image Processing.
 * This controller take in an IImageProcessing model and an IView view, then after that
 * interpret and execute them. This controller is different with another controller since
 * it now could handle the GUI.
 */
public class ImageGUIController extends AbstractController implements IViewListener {
  private final IImageProcessing model;
  private final IView view;
  private String curPath;
  private final Map<String, IFunction> map;
  private final Map<String, IComplexFunction> mapC;

  /**
   * This is the default constructor for ImageGUIController.
   *
   * @param model the model of the ImageProcessing
   * @param view  the view of the ImageProcessing
   */
  public ImageGUIController(IImageProcessing model, IView view) {
    super(model);
    this.model = model;
    this.view = view;
    view.setViewListeners(this);
    this.map = new HashMap<>();
    this.setMap(map);
    this.mapC = new HashMap<>();
    this.setMapC(mapC);
  }


  @Override
  public void run() throws IllegalStateException {
    // this run method is for the txt UI but we do not use it but we still have to implement
    // since in this class, we extends the AbstractController.
  }

  @Override
  public void viewEventPerformed(String e) {
    if (e == null) {
      this.viewMessage("Some error occur!");
      throw new IllegalArgumentException("String e can't be null");
    }
    String name;
    if (e.equalsIgnoreCase("save")) {

      name = view.fileToOpen();
      String str = model.saveImg(this.curPath);
      if (name.length() > 3) {
        String type = name.substring(name.length() - 3);
        if (type.equalsIgnoreCase("ppm")) {
          this.saveFile(name, str);
        } else {
          this.saveFile2(name, type, this.curPath);
        }
      } else {
        this.viewMessage("Cannot save this file!");
        throw new IllegalStateException();
      }
    }

    if (e.equalsIgnoreCase("load")) {
      name = view.fileToOpen();
      this.curPath = name;
      IImage img;
      if (name.length() > 3) {
        String type = name.substring(name.length() - 3);
        if (type.equalsIgnoreCase("ppm")) {
          img = this.createImage(name);
        } else {
          img = this.createImage2(name);
        }
      } else {
        this.viewMessage("Cannot load the file!");
        throw new IllegalStateException();
      }
      this.model.load(img, name);
      System.out.println("Loaded!");
    }

    if (map.containsKey(e)) {
      name = view.fileToOpen();
      IFunction func = this.map.get(e);
      try {
        model.effect(func, this.curPath, name);
        IImage image = this.model.getImage(name);
        BufferedImage buff = image.makeBuffer();
        this.view.receiveImage(buff);
        this.curPath = view.fileToOpen();
      } catch (IllegalArgumentException a) {
        this.viewMessage("Invalid simple function!");
        throw new IllegalStateException("Invalid simple function\n");
      }
    }

    if (mapC.containsKey(e)) {
      name = view.fileToOpen();
      IComplexFunction func = this.mapC.get(e);
      String type = "";
      if (e.equalsIgnoreCase("brighten")) {
        type = this.view.degreeToChange();
      } else {
        type = e;
      }
      try {
        model.complexEff(func, type, this.curPath, name);
        IImage image = this.model.getImage(curPath);
        BufferedImage buff = image.makeBuffer();
        this.view.receiveImage(buff);
        this.curPath = name;
      } catch (IllegalArgumentException a) {
        this.viewMessage("Invalid complex function!");
        throw new IllegalStateException("Invalid complex function\n");
      }
    }

    if (e.equalsIgnoreCase("red")) {
      this.view.addHistogram(this.model.getImage(this.curPath).pixelHelper(), "red");
    }

    if (e.equalsIgnoreCase("green")) {
      this.view.addHistogram(this.model.getImage(this.curPath).pixelHelper(), "green");
    }

    if (e.equalsIgnoreCase("blue")) {
      this.view.addHistogram(this.model.getImage(this.curPath).pixelHelper(), "blue");
    }
    if (e.equalsIgnoreCase("intensity")) {
      this.view.addHistogram(this.model.getImage(this.curPath).pixelHelper(), "intensity");
    }
  }

  /**
   * This is a helper method that help to add messange to the view.
   *
   * @param message the String message
   */
  private void viewMessage(String message) {
    this.view.addMessage(message);
  }
}

