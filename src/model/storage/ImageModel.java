package model.storage;

import java.util.HashMap;
import java.util.Map;
import functions.IComplexFunction;
import functions.IFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;

/**
 * This is the image model class of the Image Processing, which help to store the file image to
 * the given name and after do most of the operation for Image Proccessing such as
 * save, load, and create effect.
 */
public class ImageModel implements IImageProcessing {
  private final Map<String, IImage> imageStorage;

  /**
   * This is the standard constructor for the ImageModel.
   */
  public ImageModel() {
    imageStorage = new HashMap<String, IImage>();
  }

  /**
   * This method helps to put the file image to the storage as a name of str.
   *
   * @param str   the name of the image that user want to input after doing some Image Processing
   * @param image the initial file image
   */
  private void putImage(String str, IImage image) {
    this.imageStorage.put(str, image);
  }

  @Override
  public String saveImg(String str) {
    IImage img = this.imageStorage.getOrDefault(str, null);
    System.out.println(str);
    if (img == null) {
      System.out.println("Can't find image to save");
    }

    return img.toString();
  }

  @Override
  public void effect(IFunction func, String in, String out) {
    IImage img = this.imageStorage.getOrDefault(in, null);
    if (img == null) {
      throw new IllegalArgumentException("There is no image by this name in the storage");
    }
    IImage output = func.simpleGo(img);
    this.putImage(out, output);
  }

  // the println helps me to keep track of the computer progress when save and change pictures.
  // feel free to uncomment them and test the code!
  @Override
  public void load(IImage img, String out) {
    this.putImage(out, img);
    //System.out.println("Loaded!");
  }

  @Override
  public void complexEff(IComplexFunction func, String type, String in, String out) {
    IImage img = this.imageStorage.getOrDefault(in, null);
    if (img == null) {
      throw new IllegalArgumentException("There is no image by this name in the storage");
    }
    IImage output = func.complexGo(img, type);
    //System.out.println("Done!");
    this.putImage(out, output);

  }

  @Override
  public Map<String, IImage> getContents() {
    return  new HashMap<>(this.imageStorage);
  }

  @Override
  public IImage getImage(String str) {
    IImage img = this.imageStorage.getOrDefault(str, null);
    if (img == null) {
      throw new IllegalArgumentException("There is no image by this name in the storage");
    }
    IPixel[][] p = img.pixelHelper();
    return new ImagePPM(p.length, p[0].length, 255, p);
  }
}

