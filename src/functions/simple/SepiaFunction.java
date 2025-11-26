package functions.simple;

import functions.IComplexFunction;
import functions.IFunction;
import functions.complex.ScaleComponent;
import model.image.IImage;

/**
 * This is the function object class of the sepia Function.
 * This offers the ability to change the Image to a sepia-ed Image, which is essentially
 * applying a matrix to each pixel to a corresponding value which offer a vintage view to
 * the original Image.
 */
public class SepiaFunction implements IFunction {
  @Override
  public IImage simpleGo(IImage img) {
    IComplexFunction scale = new ScaleComponent();
    return scale.complexGo(img, "sepia");
  }
}