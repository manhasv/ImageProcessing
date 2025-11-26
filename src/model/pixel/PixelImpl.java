package model.pixel;

import java.awt.Color;
import java.io.IOException;

/**
 * This class represents a Pixel in an Image, containing three indexes of RGB.
 * This class can manipulate each index following given commands.
 */
public class PixelImpl implements IPixel {
  private final int redPixel;
  private final int greenPixel;

  private final int bluePixel;

  /**
   * This is the default constructor for the class PixelImpl.
   *
   * @param red   the component red in a pixel
   * @param green the component green in a pixel
   * @param blue  the component blue in a pixel
   * @throws IllegalArgumentException if the pixel value negative and over 255
   */
  public PixelImpl(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("Pixel value should be positive");
    }

    if (red > 255 || green > 255 || blue > 255) {
      throw new IllegalArgumentException("Pixel value should not exceed 255");
    }

    this.redPixel = red;
    this.greenPixel = green;
    this.bluePixel = blue;
  }

  @Override
  public IPixel brighter(int level) {
    int red = 0;
    if (this.redPixel < 255 - level && this.redPixel > level * -1) {
      red = this.redPixel + level;
    } else if (this.redPixel < level * -1) {
      red = 0;
    } else {
      red = 255;
    }

    int green = 0;
    if (this.greenPixel < 255 - level && this.greenPixel > level * -1) {
      green = this.greenPixel + level;
    } else if (this.greenPixel < level * -1) {
      green = 0;
    } else {
      green = 255;
    }

    int blue = 0;
    if (this.bluePixel < 255 - level && this.bluePixel > level * -1) {
      blue = this.bluePixel + level;
    } else if (this.bluePixel < level * -1) {
      blue = 0;
    } else {
      blue = 255;
    }
    return new PixelImpl(red, green, blue);
  }

  /**
   * This method helps to use the sepia effect.
   *
   * @return a sepia pixel
   */
  private IPixel sepia() {
    Double[][] num = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    return this.colorTransformation(num);
  }

  /**
   * This method helps to use the grey-scaled effect.
   *
   * @return a grey-scaled pixel
   */
  private IPixel greyScaled() {
    Double[][] num = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}};
    return this.colorTransformation(num);
  }

  /**
   * This method helps to use the luma effect.
   *
   * @return a luma-scaled pixel
   */
  private IPixel luma() {
    Double i = (0.2126 * redPixel + 0.7152 * greenPixel + 0.0722 * bluePixel);
    int num = 0;
    if (i > 255) {
      num = 255;
    } else if (i < 0) {
      num = 0;
    } else {
      num = i.intValue();
    }
    return new PixelImpl(num, num, num);
  }

  /**
   * This method help to change the value effect of a pixel.
   *
   * @return a value-scaled pixel.
   */
  private IPixel value() {
    int num = 0;
    if (this.redPixel >= this.bluePixel && this.redPixel >= this.greenPixel) {
      num = this.redPixel;
    } else if (this.greenPixel >= this.bluePixel && this.greenPixel >= this.redPixel) {
      num = this.greenPixel;
    } else {
      num = this.bluePixel;
    }
    return new PixelImpl(num, num, num);
  }

  /**
   * This method help to apply the intensity effect to the Pixel.
   *
   * @return a value-scaled pixel
   */
  private IPixel intensity() {
    int num = Math.round((this.redPixel + this.greenPixel + this.bluePixel) / 3);

    return new PixelImpl(num, num, num);
  }

  /**
   * This method help to change rgb to all red-component.
   *
   * @return a red-scaled pixel
   */
  private IPixel red() {
    return new PixelImpl(this.redPixel, this.redPixel, this.redPixel);
  }

  /**
   * This method help to change rgb to all green-component.
   *
   * @return a green-scaled pixel
   */
  private IPixel green() {
    return new PixelImpl(this.greenPixel, this.greenPixel, this.greenPixel);
  }

  /**
   * This method help to change rgb to all blue-component.
   *
   * @return a blue-scaled pixel
   */
  private IPixel blue() {
    return new PixelImpl(this.bluePixel, this.bluePixel, this.bluePixel);
  }

  @Override
  public IPixel changePixel(String type) {
    IPixel p = new PixelImpl(this.redPixel, this.greenPixel, this.bluePixel);
    switch (type) {
      case "red-component":
        p = this.red();
        break;
      case "blue-component":
        p = this.blue();
        break;
      case "green-component":
        p = this.green();
        break;
      case "intensity-component":
        p = this.intensity();
        break;
      case "value-component":
        p = this.value();
        break;
      case "luma-component":
        p = this.luma();
        break;
      case "sepia":
        p = this.sepia();
        break;
      case "greyscale":
        p = this.greyScaled();
        break;
      default:
        throw new IllegalArgumentException("Invalid type");
    }
    return p;
  }

  @Override
  public double modRed(double num) {
    double p = this.redPixel * num;
    return p;
  }

  @Override
  public double modGreen(double num) {
    double p = this.greenPixel * num;
    return p;
  }

  @Override
  public double modBlue(double num) {
    double p = this.bluePixel * num;
    return p;
  }

  @Override
  public int getRed() {
    return this.redPixel;
  }

  @Override
  public int getGreen() {
    return this.greenPixel;
  }

  @Override
  public int getBlue() {
    return this.bluePixel;
  }


  @Override
  public String toString() {
    Appendable str = new StringBuilder();
    try {
      str.append(String.format("%s ", this.redPixel));
      str.append(String.format("%s ", this.greenPixel));
      str.append(String.format("%s ", this.bluePixel));
    } catch (IOException e) {
      throw new IllegalArgumentException("IOException");
    }
    return str.toString();
  }

  @Override
  public Color makeColor() {
    Color c = new Color(this.redPixel, this.greenPixel, this.bluePixel);
    return c;
  }

  /**
   * Apply a matrix transformation to an IPixel.
   *
   * @param base a 2Darray represents a matrix containing the information of changes.
   * @return a modified IPixel applied by the matrix.
   */
  private IPixel colorTransformation(Double[][] base) {
    if (base.length != 3 && base[0].length != 3) {
      throw new IllegalArgumentException();
    }

    Double red = this.redPixel * base[0][0] + this.greenPixel * base[0][1]
            + this.bluePixel * base[0][2];
    Double green = this.redPixel * base[1][0] + this.greenPixel * base[1][1]
            + this.bluePixel * base[1][2];
    Double blue = this.redPixel * base[2][0] + this.greenPixel * base[2][1]
            + this.bluePixel * base[2][2];

    int numRed = 0;
    if (red > 255) {
      numRed = 255;
    } else if (red < 0) {
      numRed = 0;
    } else {
      numRed = red.intValue();
    }

    int numGreen = 0;
    if (red > 255) {
      numGreen = 255;
    } else if (red < 0) {
      numGreen = 0;
    } else {
      numGreen = green.intValue();
    }

    int numBlue = 0;
    if (blue > 255) {
      numBlue = 255;
    } else if (blue < 0) {
      numBlue = 0;
    } else {
      numBlue = blue.intValue();
    }
    return new PixelImpl(numRed, numGreen, numBlue);
  }
}