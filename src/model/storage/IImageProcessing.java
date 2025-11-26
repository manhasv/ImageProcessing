package model.storage;

import java.util.Map;
import functions.IComplexFunction;
import functions.IFunction;
import model.image.IImage;

/**
 * This interface represents most of the operation for Image Processing.
 * such as save, load, and create effect.
 */
public interface IImageProcessing {
  /**
   * This method will help users to save their image after processing.
   *
   * @param str the name of the image from the storage that user want to save
   */
  String saveImg(String str);

  /**
   * This method will help users to load an image.
   *
   * @param img the received image
   * @param out the name of the image that users want to load into the storage
   */
  void load(IImage img, String out);

  /**
   * Given a function by the controller, the model will ask for an image under this func, then
   * make changes accordingly.
   *
   * @param func the Function to apply to the new Image
   * @param in   give us the original Image in the Map
   * @param out  is the destination of the new Image in the Map
   */
  void effect(IFunction func, String in, String out);

  /**
   * Given a function by the controller, the model will ask for an image under this func, then
   * make changes accordingly.
   *
   * @param func the Function to apply to the new Image
   * @param type the type for the complex functions that users want to input
   * @param in   give us the original Image in the Map
   * @param out  is the destination of the new Image in the Map
   */
  void complexEff(IComplexFunction func, String type, String in, String out);

  /**
   * Return a HashMap containing the information of current storage.
   *
   * @return a Map containing info about the key and the image
   */
  Map<String, IImage> getContents();

  /**
   * Given a key value, make a copy of the IImage associated with that key value in the storage.
   * @param str the key value
   * @return a copy of the IIMage.
   */
  IImage getImage(String str);
}
