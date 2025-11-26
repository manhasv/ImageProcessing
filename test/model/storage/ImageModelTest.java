package model.storage;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import functions.IComplexFunction;
import functions.IFunction;
import functions.complex.BrightenFunction;
import functions.complex.ScaleComponent;
import functions.simple.BlurFunction;
import functions.simple.HorizontalFlipFunction;
import functions.simple.SepiaFunction;
import functions.simple.SharpeningFunction;
import functions.simple.VerticalFlipFunction;
import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;
import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains all the tests for the ImageModel.
 */
public class ImageModelTest {
  @Test
  public void loadImgTest() {
    IImageProcessing model = new ImageModel();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);

    IPixel[][] input = {{a, b}, {c, d}};
    IImage imgInput = new ImagePPM(2, 2, 255, input);
    model.load(imgInput, "load");
    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void saveImgTest() {
    IImageProcessing model = new ImageModel();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] input = {{a, b}, {c, d}};
    IImage imgInput = new ImagePPM(2, 2, 255, input);

    model.load(imgInput, "load");
    String str = model.saveImg("load");
    String out = "2 2 255 255 90 50 50 70 80 40 50 60 10 20 30 ";

    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
    assertEquals(str, out);
  }

  @Test
  public void effectTestHorizontal() {
    IFunction hori = new HorizontalFlipFunction();
    IImageProcessing model = new ImageModel();


    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] input = {{a, b}, {c, d}};

    IImage imgInput = new ImagePPM(2, 2, 255, input);

    model.load(imgInput, "load");
    model.effect(hori, "load", "horizontal");
    IPixel[][] output = {{b, a}, {d, c}};
    IImage imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("horizontal", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void effectTestVertical() {
    IFunction vert = new VerticalFlipFunction();
    IImageProcessing model = new ImageModel();


    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] input = {{a, b}, {c, d}};
    IPixel[][] output = {{c, d}, {a, b}};
    IImage imgInput = new ImagePPM(2, 2, 255, input);

    model.load(imgInput, "load");
    model.effect(vert, "load", "vertical");

    IImage imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("vertical", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void effectTestBlur() {
    IFunction blur = new BlurFunction();
    IImageProcessing model = new ImageModel();

    IPixel a = new PixelImpl(50, 100, 100);
    IPixel b = new PixelImpl(50, 70, 80);
    IPixel c = new PixelImpl(60, 80, 90);
    IPixel d = new PixelImpl(160, 80, 40);
    IPixel e = new PixelImpl(80, 90, 100);
    IPixel f = new PixelImpl(50, 70, 90);
    IPixel g = new PixelImpl(160, 80, 40);
    IPixel h = new PixelImpl(120, 90, 90);
    IPixel i = new PixelImpl(80, 80, 80);
    IPixel k = new PixelImpl(160, 160, 160);
    IPixel m = new PixelImpl(100, 80, 90);
    IPixel n = new PixelImpl(88, 80, 90);

    IPixel a1 = new PixelImpl(31, 49, 53);
    IPixel b1 = new PixelImpl(47, 59, 63);
    IPixel c1 = new PixelImpl(71, 58, 53);
    IPixel d1 = new PixelImpl(72, 46, 35);
    IPixel e1 = new PixelImpl(55, 68, 73);
    IPixel f1 = new PixelImpl(86, 88, 92);
    IPixel g1 = new PixelImpl(109, 84, 78);
    IPixel h1 = new PixelImpl(91, 62, 55);
    IPixel i1 = new PixelImpl(53, 55, 58);
    IPixel k1 = new PixelImpl(83, 79, 81);
    IPixel m1 = new PixelImpl(86, 70, 70);
    IPixel n1 = new PixelImpl(59, 46, 47);

    IPixel[][] input = {{a, b, c, d},
        {e, f, g, h},
        {i, k, m, n}};

    IPixel[][] output = {{a1, b1, c1, d1},
        {e1, f1, g1, h1},
        {i1, k1, m1, n1}};

    IImage imgInput = new ImagePPM(3, 4, 255, input);

    model.load(imgInput, "load");
    model.effect(blur, "load", "blur");

    IImage imgOutput = new ImagePPM(3, 4, 255, output);
    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("blur", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void effectTestSharpening() {
    IFunction sharpen = new SharpeningFunction();
    IImageProcessing model = new ImageModel();

    IPixel a1 = new PixelImpl(40, 160, 80);
    IPixel b1 = new PixelImpl(4, 8, 16);
    IPixel c1 = new PixelImpl(80, 80, 80);
    IPixel d1 = new PixelImpl(32, 40, 4);
    IPixel e1 = new PixelImpl(16, 16, 16);

    IPixel a2 = new PixelImpl(40, 160, 80);
    IPixel b2 = new PixelImpl(4, 8, 16);
    IPixel c2 = new PixelImpl(80, 80, 80);
    IPixel d2 = new PixelImpl(32, 40, 4);
    IPixel e2 = new PixelImpl(16, 16, 16);

    IPixel a3 = new PixelImpl(4, 8, 16);
    IPixel b3 = new PixelImpl(40, 160, 80);
    IPixel c3 = new PixelImpl(40, 160, 80);
    IPixel d3 = new PixelImpl(32, 40, 4);
    IPixel e3 = new PixelImpl(40, 160, 80);

    IPixel a4 = new PixelImpl(4, 8, 16);
    IPixel b4 = new PixelImpl(4, 8, 16);
    IPixel c4 = new PixelImpl(80, 80, 80);
    IPixel d4 = new PixelImpl(16, 16, 16);
    IPixel e4 = new PixelImpl(16, 16, 16);

    IPixel a5 = new PixelImpl(16, 16, 16);
    IPixel b5 = new PixelImpl(40, 160, 80);
    IPixel c5 = new PixelImpl(80, 80, 80);
    IPixel d5 = new PixelImpl(32, 40, 4);
    IPixel e5 = new PixelImpl(40, 160, 80);


    IPixel[][] input = {{a1, b1, c1, d1, e1},
        {a2, b2, c2, d2, e2},
        {a3, b3, c3, d3, e3},
        {a4, b4, c4, d4, e4},
        {a5, b5, c5, d5, e5}};

    IPixel a11 = new PixelImpl(21, 143, 66);
    IPixel b11 = new PixelImpl(42, 74, 76);
    IPixel c11 = new PixelImpl(84, 14, 53);
    IPixel d11 = new PixelImpl(68, 31, 18);
    IPixel e11 = new PixelImpl(2, 0, 0);

    IPixel a22 = new PixelImpl(27, 194, 88);
    IPixel b22 = new PixelImpl(61, 183, 126);
    IPixel c22 = new PixelImpl(111, 133, 97);
    IPixel d22 = new PixelImpl(95, 151, 64);
    IPixel e22 = new PixelImpl(15, 36, 0);

    IPixel a33 = new PixelImpl(0, 0, 0);
    IPixel b33 = new PixelImpl(53, 203, 128);
    IPixel c33 = new PixelImpl(49, 127, 69);
    IPixel d33 = new PixelImpl(65, 87, 38);
    IPixel e33 = new PixelImpl(8, 100, 31);

    IPixel a44 = new PixelImpl(0, 25, 16);
    IPixel b44 = new PixelImpl(50, 128, 102);
    IPixel c44 = new PixelImpl(114, 162, 117);
    IPixel d44 = new PixelImpl(79, 141, 85);
    IPixel e44 = new PixelImpl(15, 63, 19);

    IPixel a55 = new PixelImpl(0, 0, 2);
    IPixel b55 = new PixelImpl(65, 155, 107);
    IPixel c55 = new PixelImpl(94, 65, 80);
    IPixel d55 = new PixelImpl(65, 42, 29);
    IPixel e55 = new PixelImpl(22, 113, 48);


    IPixel[][] output = {{a11, b11, c11, d11, e11},
        {a22, b22, c22, d22, e22},
        {a33, b33, c33, d33, e33},
        {a44, b44, c44, d44, e44},
        {a55, b55, c55, d55, e55}};

    IImage imgInput = new ImagePPM(5, 5, 255, input);

    model.load(imgInput, "load");
    model.effect(sharpen, "load", "sharpening");

    IImage imgOutput = new ImagePPM(5, 5, 255, output);
    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("sharpening", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void effectTestSepia() {
    IFunction sepia = new SepiaFunction();
    IImageProcessing model = new ImageModel();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);

    PixelImpl a2 = new PixelImpl(178, 159, 123);
    PixelImpl b2 = new PixelImpl(88, 78, 61);
    PixelImpl c2 = new PixelImpl(65, 58, 45);
    PixelImpl d2 = new PixelImpl(24, 22, 17);

    IPixel[][] input = {{a, b}, {c, d}};
    IPixel[][] output = {{a2, b2}, {c2, d2}};
    IImage imgInput = new ImagePPM(2, 2, 255, input);
    //0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131
    model.load(imgInput, "load");
    model.effect(sepia, "load", "sepia");

    IImage imgOutput = new ImagePPM(2, 2, 255, output);
    //"2 2 255 178 159 123 88 78 61 65 58 45 24 22 17 "

    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("sepia", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void effectException() {
    IFunction vert = new VerticalFlipFunction();
    IImageProcessing model = new ImageModel();
    model.effect(vert, "load", "vertical");
  }

  @Test
  public void complexEffectTestBrighten() {
    IComplexFunction brighten = new BrightenFunction();
    IImageProcessing model = new ImageModel();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);

    PixelImpl a2 = new PixelImpl(255, 140, 100);
    PixelImpl b2 = new PixelImpl(100, 120, 130);
    PixelImpl c2 = new PixelImpl(90, 100, 110);
    PixelImpl d2 = new PixelImpl(60, 70, 80);

    IPixel[][] input = {{a, b}, {c, d}};
    IPixel[][] output = {{a2, b2}, {c2, d2}};
    IImage imgInput = new ImagePPM(2, 2, 255, input);

    model.load(imgInput, "load");
    model.complexEff(brighten, "50", "load", "brighten");

    IImage imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("brighten", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void complexEffectBlue() {
    IComplexFunction blue = new ScaleComponent();
    IImageProcessing model = new ImageModel();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);

    PixelImpl a2 = new PixelImpl(50, 50, 50);
    PixelImpl b2 = new PixelImpl(80, 80, 80);
    PixelImpl c2 = new PixelImpl(60, 60, 60);
    PixelImpl d2 = new PixelImpl(30, 30, 30);

    IPixel[][] input = {{a, b}, {c, d}};
    IPixel[][] output = {{a2, b2}, {c2, d2}};
    IImage imgInput = new ImagePPM(2, 2, 255, input);

    model.load(imgInput, "load");
    model.complexEff(blue, "blue-component", "load", "blue");

    IImage imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("blue", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void complexEffectRed() {
    IComplexFunction red = new ScaleComponent();
    IImageProcessing model = new ImageModel();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);

    PixelImpl a2 = new PixelImpl(255, 255, 255);
    PixelImpl b2 = new PixelImpl(50, 50, 50);
    PixelImpl c2 = new PixelImpl(40, 40, 40);
    PixelImpl d2 = new PixelImpl(10, 10, 10);

    IPixel[][] input = {{a, b}, {c, d}};
    IPixel[][] output = {{a2, b2}, {c2, d2}};
    IImage imgInput = new ImagePPM(2, 2, 255, input);

    model.load(imgInput, "load");
    model.complexEff(red, "red-component", "load", "red");

    IImage imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("red", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void complexEffectGreen() {
    IComplexFunction green = new ScaleComponent();
    IImageProcessing model = new ImageModel();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);

    PixelImpl a2 = new PixelImpl(90, 90, 90);
    PixelImpl b2 = new PixelImpl(70, 70, 70);
    PixelImpl c2 = new PixelImpl(50, 50, 50);
    PixelImpl d2 = new PixelImpl(20, 20, 20);

    IPixel[][] input = {{a, b}, {c, d}};
    IPixel[][] output = {{a2, b2}, {c2, d2}};
    IImage imgInput = new ImagePPM(2, 2, 255, input);

    model.load(imgInput, "load");
    model.complexEff(green, "green-component", "load", "green");

    IImage imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("green", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void complexEffectIntensity() {
    IComplexFunction intensity = new ScaleComponent();
    IImageProcessing model = new ImageModel();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);

    PixelImpl a2 = new PixelImpl(131, 131, 131);
    PixelImpl b2 = new PixelImpl(66, 66, 66);
    PixelImpl c2 = new PixelImpl(50, 50, 50);
    PixelImpl d2 = new PixelImpl(20, 20, 20);

    IPixel[][] input = {{a, b}, {c, d}};
    IPixel[][] output = {{a2, b2}, {c2, d2}};
    IImage imgInput = new ImagePPM(2, 2, 255, input);

    model.load(imgInput, "load");
    model.complexEff(intensity, "intensity-component", "load", "intensity");

    IImage imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("intensity", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void complexEffectValue() {
    IComplexFunction value = new ScaleComponent();
    IImageProcessing model = new ImageModel();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);

    PixelImpl a2 = new PixelImpl(255, 255, 255);
    PixelImpl b2 = new PixelImpl(80, 80, 80);
    PixelImpl c2 = new PixelImpl(60, 60, 60);
    PixelImpl d2 = new PixelImpl(30, 30, 30);

    IPixel[][] input = {{a, b}, {c, d}};
    IPixel[][] output = {{a2, b2}, {c2, d2}};
    IImage imgInput = new ImagePPM(2, 2, 255, input);

    model.load(imgInput, "load");
    model.complexEff(value, "value-component", "load", "value");

    IImage imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("value", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void complexEffectLuma() {
    IComplexFunction luma = new ScaleComponent();
    IImageProcessing model = new ImageModel();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);

    PixelImpl a2 = new PixelImpl(122, 122, 122);
    PixelImpl b2 = new PixelImpl(66, 66, 66);
    PixelImpl c2 = new PixelImpl(48, 48, 48);
    PixelImpl d2 = new PixelImpl(18, 18, 18);

    IPixel[][] input = {{a, b}, {c, d}};
    IPixel[][] output = {{a2, b2}, {c2, d2}};
    IImage imgInput = new ImagePPM(2, 2, 255, input);

    model.load(imgInput, "load");
    model.complexEff(luma, "luma-component", "load", "luma-component");

    IImage imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("luma-component", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void getContentsTest() {
    IImageProcessing model = new ImageModel();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);

    IPixel[][] input = {{a, b}, {c, d}};
    IImage imgInput = new ImagePPM(2, 2, 255, input);

    model.load(imgInput, "load");

    Map<String, IImage> map = new HashMap<>();
    map.put("load", imgInput);
    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, IImage> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }
}