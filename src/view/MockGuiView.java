package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import controller.IViewListener;
import model.pixel.IPixel;

/**
 * This class represents a Mock of a GUI view which can be used to test the functionality of GUI.
 * Also, it can be used to test the transition between MVC design in GUI.
 */
public class MockGuiView implements IView, ActionListener {
  private String currentPath;
  private ArrayList<IViewListener> listenerList;

  /**
   * This is the standard constructor of the MockGuiView which set up the listener and path.
   */
  public MockGuiView() {
    this.listenerList = new ArrayList<>();
    this.currentPath = "res/image.ppm";
  }

  @Override
  public void setViewListeners(IViewListener listener) {
    listenerList.add(listener);
  }

  @Override
  public String fileToOpen() {
    return currentPath;
  }

  @Override
  public String degreeToChange() {
    return "50";
  }

  @Override
  public void receiveImage(BufferedImage img) {
    //This stimulates a GUI view so we do not need this method but we still have to implement in
    // from IView.
  }

  @Override
  public void addHistogram(IPixel[][] p, String type) {
    //This stimulates a GUI view so we do not need this method but we still have to implement in
    // from IView.
  }

  @Override
  public void addMessage(String message) {
    //This stimulates a GUI view so we do not need this method but we still have to implement in
    // from IView.
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String str = e.getActionCommand();
    if (e.getActionCommand().equalsIgnoreCase("brighten")) {
      for (IViewListener viewListener : listenerList) {
        viewListener.viewEventPerformed(str);
      }
    }


    if (str.equalsIgnoreCase("load")) {
      for (IViewListener viewListener : listenerList) {
        viewListener.viewEventPerformed(str);
      }
    }

    if (str.equalsIgnoreCase("save")) {
      for (IViewListener viewListener : listenerList) {
        //this.currentPath = "res/image2.ppm";
        viewListener.viewEventPerformed(str);
      }
    }

    if (!str.equals("load") && !str.equals("save")) {
      for (IViewListener viewListener : listenerList) {
        viewListener.viewEventPerformed(str);
      }
    }
  }
}

