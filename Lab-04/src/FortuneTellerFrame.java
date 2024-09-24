import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {
    private JTextArea fortuneArea;
    private ArrayList<String> fortunes;
    private int lastFortuneIndex = -1;
    private Random random = new Random();

    public FortuneTellerFrame() {
        setTitle("Silas's Fortune Teller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 255)); // Light blue background

        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        ImageIcon icon = null;
        try {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/currysc_fortune_teller_image.png")));
            Image resizedImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            icon = new ImageIcon(resizedImg);
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            e.printStackTrace();
        }
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(imageLabel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Fortune Teller");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(new Color(70, 70, 120)); // Dark blue text
        topPanel.add(titleLabel, BorderLayout.SOUTH);

        topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(topPanel, BorderLayout.NORTH);

        // Middle panel
        fortuneArea = new JTextArea(10, 30);
        fortuneArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        fortuneArea.setEditable(false);
        fortuneArea.setLineWrap(true);
        fortuneArea.setWrapStyleWord(true);
        fortuneArea.setBackground(new Color(255, 255, 240)); // Light yellow background
        fortuneArea.setForeground(new Color(60, 60, 60)); // Dark gray text
        JScrollPane scrollPane = new JScrollPane(fortuneArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(new Color(200, 200, 255), 2)
        ));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        bottomPanel.setOpaque(false);
        JButton fortuneButton = new JButton("Read My Fortune!");
        JButton quitButton = new JButton("Quit");
        Font buttonFont = new Font("SansSerif", Font.BOLD, 16);
        fortuneButton.setFont(buttonFont);
        quitButton.setFont(buttonFont);
        fortuneButton.setBackground(new Color(100, 150, 255));
        fortuneButton.setForeground(new Color(50, 0, 80)); // Dark purple text
        quitButton.setBackground(new Color(255, 100, 100));
        quitButton.setForeground(new Color(50, 0, 80)); // Dark purple text
        bottomPanel.add(fortuneButton);
        bottomPanel.add(quitButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action listeners
        fortuneButton.addActionListener(e -> displayFortune());
        quitButton.addActionListener(e -> System.exit(0));

        // Initialize fortunes
        initializeFortunes();

        // Set frame size and position
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = (int) (screenSize.width * 0.6);
        int height = (int) (screenSize.height * 0.7);
        setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);

        // Add some padding to the main frame
        ((JPanel)getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    private void initializeFortunes() {
        fortunes = new ArrayList<>();
        fortunes.add("You will find unexpected joy in the little things today.");
        fortunes.add("A pleasant surprise is waiting for you around the corner.");
        fortunes.add("Your creativity will lead you to great success.");
        fortunes.add("An old friend will bring exciting news your way.");
        fortunes.add("Your hard work will pay off in ways you never imagined.");
        fortunes.add("A journey of a thousand miles begins with a single step.");
        fortunes.add("Your sense of humor will brighten someone's day today.");
        fortunes.add("Good fortune will be yours if you stay positive.");
        fortunes.add("An opportunity will arise to showcase your hidden talents.");
        fortunes.add("Your kindness will return to you tenfold.");
        fortunes.add("A dream you have will come true when you least expect it.");
        fortunes.add("Your future is as bright as your faith.");
    }

    private void displayFortune() {
        int index;
        do {
            index = random.nextInt(fortunes.size());
        } while (index == lastFortuneIndex);

        lastFortuneIndex = index;
        String fortune = fortunes.get(index);
        fortuneArea.append(fortune + "\n\n");
    }
}