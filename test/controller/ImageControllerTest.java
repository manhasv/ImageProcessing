

package controller;

import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;
import model.pixel.PixelImpl;
import model.storage.IImageProcessing;
import model.storage.ImageModel;

import static org.junit.Assert.assertTrue;

/**
 * This class contains the test for the ImageController class.
 */
public class ImageControllerTest {

  @Test(expected = IllegalStateException.class)
  public void testNoSuchElementException() {
    Readable testIn = new StringReader("load");
    IImageProcessing model = new ImageModel();

    IController controller = new ImageController(model, testIn);
    controller.run();
  }

  @Test(expected = IllegalStateException.class)
  public void testSimpleFuncException() {
    Readable testIn = new StringReader("load res/image.ppm load horizontal-flip afas hori");
    IImageProcessing model = new ImageModel();

    IController controller = new ImageController(model, testIn);
    controller.run();
  }

  @Test(expected = IllegalStateException.class)
  public void testComplexFuncException() {
    Readable testIn = new StringReader("load res/image.ppm load brighten 10 afas hori");
    IImageProcessing model = new ImageModel();

    IController controller = new ImageController(model, testIn);
    controller.run();
  }

  @Test
  public void testConstructor() {
    Readable testIn = new StringReader("load res/image.ppm load q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] input = {{a, b}, {c, d}};
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);

    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException() {
    Readable testIn = new StringReader("q");
    IController controller = new ImageController(null, testIn);
    controller.run();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionModel() {
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, null);
    controller.run();
  }

  @Test
  public void testPPMLoad() {
    Readable testIn = new StringReader("load res/image.ppm load q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] input = {{a, b}, {c, d}};
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);

    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testPPMSave() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "save res/test.ppm load "
            + "load res/test.ppm save q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] input = {{a, b}, {c, d}};
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("save", imgInput);

    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunHorizontal() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "horizontal-flip load horizontal-flip q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] in = {{a, b}, {c, d}};
    ImagePPM imgInput = new ImagePPM(2, 2, 255, in);
    IPixel[][] out = {{b, a}, {d, c}};
    ImagePPM imgOutput = new ImagePPM(2, 2, 255, out);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("horizontal-flip", imgOutput);

    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunVertical() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "vertical-flip load vertical-flip q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] in = {{a, b}, {c, d}};
    ImagePPM imgInput = new ImagePPM(2, 2, 255, in);
    IPixel[][] out = {{c, d}, {a, b}};
    ImagePPM imgOutput = new ImagePPM(2, 2, 255, out);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("vertical-flip", imgOutput);

    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunBrighten() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "brighten 50 load brighten q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

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
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);

    ImagePPM imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("brighten", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunRed() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "red-component load red-component q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

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
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);

    ImagePPM imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("red-component", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunBlue() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "blue-component load blue q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

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
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);

    ImagePPM imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("blue", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunGreen() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "green-component load green q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

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
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);

    ImagePPM imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("green", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunValue() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "value-component load value q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

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
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);

    ImagePPM imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("value", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunIntensity() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "intensity-component load intensity q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

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
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);

    ImagePPM imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("intensity", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunLuma() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "luma-component load luma-component q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

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
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);

    ImagePPM imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("luma-component", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunSepia() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "sepia load sepia q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

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
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);

    ImagePPM imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("sepia", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunGrey() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "greyscale load greyscale q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

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
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);

    ImagePPM imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("greyscale", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunBlur() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "blur load blur q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);

    PixelImpl a2 = new PixelImpl(75, 38, 31);
    PixelImpl b2 = new PixelImpl(48, 34, 33);
    PixelImpl c2 = new PixelImpl(46, 30, 30);
    PixelImpl d2 = new PixelImpl(29, 25, 28);
    //blur -> {ImagePPM@1418} "2 2 255 75 38 31 48 34 33 46 30 30 29 25 28 "
    IPixel[][] input = {{a, b}, {c, d}};
    IPixel[][] output = {{a2, b2}, {c2, d2}};

    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);

    ImagePPM imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("blur", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunSharpening() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "sharpening load sharpening q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);

    PixelImpl a2 = new PixelImpl(255, 125, 92);
    PixelImpl b2 = new PixelImpl(126, 110, 115);
    PixelImpl c2 = new PixelImpl(118, 95, 100);
    PixelImpl d2 = new PixelImpl(96, 72, 77);
    IPixel[][] input = {{a, b}, {c, d}};
    IPixel[][] output = {{a2, b2}, {c2, d2}};

    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);

    ImagePPM imgOutput = new ImagePPM(2, 2, 255, output);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("sharpening", imgOutput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testPNGLoad() {
    Readable testIn = new StringReader("load res/image.png load q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] input = {{a, b}, {c, d}};
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);

    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testBMPLoad() {
    Readable testIn = new StringReader("load res/image.bmp load q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] input = {{a, b}, {c, d}};
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);

    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testPNGSave() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "save res/image.png load "
            + "load res/image.png save q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] input = {{a, b}, {c, d}};
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("save", imgInput);

    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testBMPSave() {
    Readable testIn = new StringReader("load res/image.ppm load "
            + "save res/image.bmp load "
            + "load res/image.bmp save q");
    IImageProcessing model = new ImageModel();
    IController controller = new ImageController(model, testIn);
    controller.run();

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] input = {{a, b}, {c, d}};
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("load", imgInput);
    map.put("save", imgInput);

    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }
}

