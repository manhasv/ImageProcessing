package controller;

/**
 * This interface represents the listener to the Action performed in the GUI.
 * Depends on the action, the view listener will behave accordingly.
 */
public interface IViewListener {
  /**
   * This method transmit the action performed in a Gui View to this listener.
   * @param str the String containing the command function to the listener.
   */
  void viewEventPerformed(String str);
}

