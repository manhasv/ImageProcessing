package view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.IViewListener;
import model.pixel.IPixel;

/**
 * This class represents the View of the Image Processing, showing the Graphical user interface.
 * This class will handle everything that are related to UI Desgin, and it also implements the
 * ActionListern to receive actionevent in actionPerformed funtion.
 */
public class ImageGuiView extends JFrame implements IView, ActionListener {
  private JLabel fileOpenDisplay;
  private JLabel radioDisplay;
  private JPanel histogramPanel1;
  private JPanel imagePanel;
  private ArrayList<IViewListener> listenerList;
  private String currentPath;
  private Map<String, String> map;
  private String degree;

  /**
   * This is the default constructor for ImageGuiView class. This will construct the UI design
   * of this GUI.
   */
  public ImageGuiView() {
    listenerList = new ArrayList<>();
    setTitle("Image Processor: ");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    JPanel leftMainPanel = new JPanel();
    leftMainPanel.setLayout(new BoxLayout(leftMainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(leftMainPanel);


    JPanel toolsPanel = new JPanel();
    toolsPanel.setBorder(BorderFactory.createTitledBorder("Tools"));
    toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.PAGE_AXIS));
    leftMainPanel.add(toolsPanel);

    //file open
    JPanel fileOpenPanel = new JPanel();
    fileOpenPanel.setLayout(new BoxLayout(fileOpenPanel, BoxLayout.PAGE_AXIS));
    toolsPanel.add(fileOpenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileOpenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileOpenPanel.add(fileOpenDisplay);
    //fileOpenPanel.setPreferredSize(new Dimension(100,50));


    //radio buttons
    JPanel funtionsPanel = new JPanel();
    funtionsPanel.setLayout(new BoxLayout(funtionsPanel, BoxLayout.PAGE_AXIS));
    toolsPanel.add(funtionsPanel);


    ButtonGroup rGroup1 = new ButtonGroup();

    radioDisplay = new JLabel("Choose the effects");
    funtionsPanel.add(radioDisplay);

    ArrayList<JRadioButton> radioButtons2 = new ArrayList<JRadioButton>();
    radioButtons2.add(new JRadioButton("HorizontalFlip"));
    radioButtons2.add(new JRadioButton("VerticalFlip"));
    radioButtons2.add(new JRadioButton("Blur"));
    radioButtons2.add(new JRadioButton("Sharpening"));
    radioButtons2.add(new JRadioButton("Sepia"));
    radioButtons2.add(new JRadioButton("GreyScale"));
    radioButtons2.add(new JRadioButton("Value-Component"));
    radioButtons2.add(new JRadioButton("Red-Component"));
    radioButtons2.add(new JRadioButton("Blue-Component"));
    radioButtons2.add(new JRadioButton("Green-Component"));
    radioButtons2.add(new JRadioButton("Luma-Component"));
    radioButtons2.add(new JRadioButton("Intensity-Component"));


    for (int i = 0; i < radioButtons2.size(); i++) {
      radioButtons2.get(i).setActionCommand("effect" + (i + 1));
      radioButtons2.get(i).addActionListener(this);
      rGroup1.add(radioButtons2.get(i));
      funtionsPanel.add(radioButtons2.get(i));
    }

    JPanel inputDialogPanel = new JPanel();
    inputDialogPanel.setLayout(new BoxLayout(inputDialogPanel, BoxLayout.Y_AXIS));
    funtionsPanel.add(inputDialogPanel);

    JButton inputButton = new JButton("Brighten");
    inputButton.setActionCommand("brighten");
    inputButton.addActionListener(this);
    inputDialogPanel.add(inputButton);

    JLabel brightDisplay = new JLabel("Default");
    inputDialogPanel.add(brightDisplay);

    radioDisplay = new JLabel("Your choosen effect is:");
    funtionsPanel.add(radioDisplay);

    //file save panel
    JPanel fileSavePanel = new JPanel();
    fileSavePanel.setLayout(new BoxLayout(fileSavePanel, BoxLayout.PAGE_AXIS));
    toolsPanel.add(fileSavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    fileSavePanel.add(fileSaveButton);
    JLabel fileSaveDisplay = new JLabel("File path will appear here");
    fileSavePanel.add(fileSaveDisplay);

    //histogram options panel
    JPanel histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram Option"));
    histogramPanel.setLayout(new BoxLayout(histogramPanel, BoxLayout.PAGE_AXIS));
    leftMainPanel.add(histogramPanel);

    JButton redButton = new JButton("Red Histogram");
    redButton.setActionCommand("RedHis");
    redButton.addActionListener(this);
    histogramPanel.add(redButton);

    JButton greenButton = new JButton("Green Histogram");
    greenButton.setActionCommand("GreenHis");
    greenButton.addActionListener(this);
    histogramPanel.add(greenButton);

    JButton blueButton = new JButton("Blue Histogram");
    blueButton.setActionCommand("BlueHis");
    blueButton.addActionListener(this);
    histogramPanel.add(blueButton);

    JButton intensityButton = new JButton("Intensity Histogram");
    intensityButton.setActionCommand("IntensityHis");
    intensityButton.addActionListener(this);
    histogramPanel.add(intensityButton);

    //histogram panel
    histogramPanel1 = new JPanel();
    histogramPanel1.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramPanel1.setLayout(new GridLayout(1, 0, 10, 10));
    leftMainPanel.add(histogramPanel1);

    //show an image with a scrollbar
    imagePanel = new JPanel();
    imagePanel.setPreferredSize(new Dimension(600, 700));
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);

    pack();
    setVisible(true);

    //Work with the map
    this.map = new HashMap<>();
    map.put("effect1", "horizontal-flip");
    map.put("effect2", "vertical-flip");
    map.put("effect3", "blur");
    map.put("effect4", "sharpening");
    map.put("effect5", "sepia");
    map.put("effect6", "greyscale");
    map.put("brighten", "brighten");
    map.put("effect7", "value-component");
    map.put("effect8", "red-component");
    map.put("effect9", "blue-component");
    map.put("effect10", "green-component");
    map.put("effect11", "luma-component");
    map.put("effect12", "intensity-component");
    map.put("Open file", "load");
    map.put("Save file", "save");
    map.put("RedHis", "red");
    map.put("GreenHis", "green");
    map.put("BlueHis", "blue");
    map.put("IntensityHis", "intensity");
  }

  @Override
  public String fileToOpen() {
    return this.currentPath;
  }

  @Override
  public String degreeToChange() {
    return this.degree;
  }

  @Override
  public void receiveImage(BufferedImage img) {
    if (img == null) {
      this.addMessage("There is no image");
    }
    ImageIcon image = new ImageIcon(img);
    JLabel imageLabel = new JLabel(image);
    this.addScrollPane(imageLabel);
  }

  /**
   * This is the helper method that help to open the dialog load
   * file of the GUI file.
   */
  private void loadFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif", "png", "ppm", "bmp");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(ImageGuiView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileOpenDisplay.setText(f.getAbsolutePath());
      ImageIcon img = new ImageIcon(f.getAbsolutePath());
      JLabel imageLabel = new JLabel(img);
      this.addScrollPane(imageLabel);
      this.currentPath = f.getAbsolutePath();
    } else {
      this.addMessage("Did not load file!");
      throw new IllegalArgumentException();
    }
  }

  /**
   * This is the helper method that help to opem the dialog save file
   * of the GUI file.
   */
  private void saveFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(ImageGuiView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      this.currentPath = f.getAbsolutePath();
    } else {
      this.addMessage("Did not save file!");
      throw new IllegalArgumentException();
    }
  }

  /**
   * This helper method help to add the scroll pane of the GUI.
   *
   * @param com component of JFrame, in this case, a  JPanel include an image
   */
  private void addScrollPane(Component com) {
    JScrollPane scrollPane = new JScrollPane(com);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    imagePanel.removeAll();
    imagePanel.add(scrollPane);
    imagePanel.validate();
    imagePanel.repaint();
  }

  @Override
  public void addHistogram(IPixel[][] p, String type) {
    JPanel histogram = new Histogram(p, type);
    histogramPanel1.removeAll();
    histogramPanel1.add(histogram);
    histogramPanel1.validate();
    histogramPanel1.repaint();
  }

  @Override
  public void addMessage(String message) {
    JOptionPane.showMessageDialog(ImageGuiView.this,
            message, "Error Found!", JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void setViewListeners(IViewListener listener) {
    listenerList.add(listener);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String str = this.map.get(e.getActionCommand());
    if (e.getActionCommand().equalsIgnoreCase("brighten")) {
      this.degree = JOptionPane.showInputDialog("value");
      //brightDisplay.setText(degree);
      for (IViewListener viewListener : listenerList) {
        //this.currentPath = str + currentPath;
        viewListener.viewEventPerformed(str);
      }
    }

    if (str.equalsIgnoreCase("load")) {
      for (IViewListener viewListener : listenerList) {
        this.loadFile();
        viewListener.viewEventPerformed(str);
      }
    }

    if (str.equalsIgnoreCase("save")) {
      for (IViewListener viewListener : listenerList) {
        this.saveFile();
        viewListener.viewEventPerformed(str);
      }
    }

    if (map.containsKey(e.getActionCommand()) && !str.equals("load")  && !str.equals("save")) {
      radioDisplay.setText(str + " was selected");
      for (IViewListener viewListener : listenerList) {
        //this.currentPath = str + currentPath;
        viewListener.viewEventPerformed(str);
      }
    }
  }
}

