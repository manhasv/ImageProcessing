package view;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.pixel.IPixel;

/**
 * This class represents a histogram model, showing the red, green, blue and intensity components.
 * For the histogram, the x-axis represents the pixel value, and the y-axis represents the
 * counts of pixel value.
 */
public class Histogram extends JPanel {
  private IPixel[][] data;
  private String type;
  private int cellDimension;

  /**
   * This is the standard constructor for the histogram that take in the 2d pixel value
   * and a string type of the data such as red, green, blue, or intensity.
   *
   * @param data 2d array
   * @param type type of the histogram such as red, green, blue, or intensity
   */
  public Histogram(IPixel[][] data, String type) {
    super();
    this.data = data;
    this.type = type;
    setPreferredSize(new Dimension(257, 320));
    this.setBackground(Color.WHITE);
    this.cellDimension = 2;
    if (data == null) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * This is the helper method that help to count the total number of pixel value corresponding to
   * the type of histogram, which are red, green, blue, and intensity.
   *
   * @param type the type of the histogram, which are red, green , blue, and intensity
   * @return the result in an array
   */
  private int[] setData(String type) {
    int[] rgb = new int[256];
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data.length; j++) {
        IPixel p = data[i][j];
        if (type.equals("red")) {
          int red = p.getRed();
          int num = rgb[red];
          rgb[red] = num + 1;
        }
        if (type.equals("green")) {
          int green = p.getGreen();
          int num = rgb[green];
          rgb[green] = num + 1;
        }
        if (type.equals("blue")) {
          int blue = p.getBlue();
          int num = rgb[blue];
          rgb[blue] = num + 1;
        }
        if (type.equals("intensity")) {
          int r = p.getRed();
          int b = p.getBlue();
          int g = p.getGreen();
          int intensity = (r + b + g) / 3;
          int num = rgb[intensity];
          rgb[intensity] = num + 1;
        }
      }
    }
    return rgb;
  }

  /**
   * This is the overrided method that we use to create the histogram.
   *
   * @param g the graphic g
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int originY = 310;
    int[] rr = this.setData(this.type);
    int max = rr[0];

    for (int i = 1; i < rr.length; i++) {
      if (rr[i] > max) {
        max = rr[i];
      }
    }
    double scale = (double) 300 / max;

    for (int i = 0; i < 256; i++) {
      if (this.type.equalsIgnoreCase("red")) {
        g.setColor(Color.RED);
      }
      if (this.type.equalsIgnoreCase("green")) {
        g.setColor(Color.GREEN);
      }
      if (this.type.equalsIgnoreCase("blue")) {
        g.setColor(Color.BLUE);
      }
      if (this.type.equalsIgnoreCase("intensity")) {
        g.setColor(Color.BLACK);
      }

      g.drawLine(this.getX() + i * cellDimension, originY,
              this.getX() + i * cellDimension, (int) (originY - (rr[i] * scale)));

    }
  }
}