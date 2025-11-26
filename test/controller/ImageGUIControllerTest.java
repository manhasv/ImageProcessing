package controller;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import model.image.IImage;
import model.image.ImagePPM;
import model.pixel.IPixel;
import model.pixel.PixelImpl;
import model.storage.IImageProcessing;
import model.storage.ImageModel;
import view.IView;
import view.MockGuiView;

import static org.junit.Assert.assertTrue;

/**
 * TThis class contains the test for the ImageGUIController class.
 */
public class ImageGUIControllerTest {
  @Test
  public void testLoad() {
    IImageProcessing model = new ImageModel();
    IView view = new MockGuiView();
    IViewListener controller = new ImageGUIController(model, view);

    controller.viewEventPerformed("load");

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] input = {{a, b}, {c, d}};
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("res/image.ppm", imgInput);

    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testSave() {
    IImageProcessing model = new ImageModel();
    IView view = new MockGuiView();
    IViewListener controller = new ImageGUIController(model, view);

    controller.viewEventPerformed("load");

    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] input = {{a, b}, {c, d}};
    ImagePPM imgInput = new ImagePPM(2, 2, 255, input);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("res/image.ppm", imgInput);

    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }

    controller.viewEventPerformed("save");
    controller.viewEventPerformed("load");

    map.put("res/image.ppm", imgInput);

    Map<String, IImage> output2 = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testHorizontal() {
    IImageProcessing model = new ImageModel();
    IView view = new MockGuiView();
    IViewListener controller = new ImageGUIController(model, view);

    controller.viewEventPerformed("load");


    PixelImpl a = new PixelImpl(255, 90, 50);
    PixelImpl b = new PixelImpl(50, 70, 80);
    PixelImpl c = new PixelImpl(40, 50, 60);
    PixelImpl d = new PixelImpl(10, 20, 30);
    IPixel[][] in = {{a, b}, {c, d}};
    ImagePPM imgInput = new ImagePPM(2, 2, 255, in);
    Map<String, ImagePPM> map = new HashMap<>();
    map.put("res/image.ppm", imgInput);

    Map<String, IImage> output = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output.containsKey(s.getKey())) &&
              (output.get(s.getKey()).toString().equals(s.getValue().toString())));
    }

    controller.viewEventPerformed("horizontal-flip");
    IPixel[][] out = {{b, a}, {d, c}};
    ImagePPM imgOutput = new ImagePPM(2, 2, 255, out);
    map.put("res/image.ppm", imgOutput);

    Map<String, IImage> output2 = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((output2.containsKey(s.getKey())) &&
              (output2.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunBrighten() {
    IImageProcessing model = new ImageModel();
    IView view = new MockGuiView();
    IViewListener controller = new ImageGUIController(model, view);

    controller.viewEventPerformed("load");

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
    map.put("res/image.ppm", imgInput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }

    controller.viewEventPerformed("brighten");
    map.put("res/image.ppm", imgOutput);
    Map<String, IImage> outputMap2 = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap2.containsKey(s.getKey())) &&
              (outputMap2.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }

  @Test
  public void testRunBlur() {
    IImageProcessing model = new ImageModel();
    IView view = new MockGuiView();
    IViewListener controller = new ImageGUIController(model, view);

    controller.viewEventPerformed("load");

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
    map.put("res/image.ppm", imgInput);
    Map<String, IImage> outputMap = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap.containsKey(s.getKey())) &&
              (outputMap.get(s.getKey()).toString().equals(s.getValue().toString())));
    }

    controller.viewEventPerformed("blur");

    map.put("res/image.ppm", imgOutput);
    Map<String, IImage> outputMap2 = model.getContents();
    for (Map.Entry<String, ImagePPM> s : map.entrySet()) {
      assertTrue((outputMap2.containsKey(s.getKey())) &&
              (outputMap2.get(s.getKey()).toString().equals(s.getValue().toString())));
    }
  }
}

