import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {
    private JLabel titleLabel;
    private JTextArea fortuneTextArea;
    private JButton fortuneButton;
    private JButton quitButton;
    private JScrollPane scrollPane; // Declare scrollPane as an instance variable

    private ArrayList<String> fortunes;
    private int lastFortuneIndex = -1;

    public FortuneTellerFrame() {
        setTitle("Fortune Teller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the image from the resources folder
        ImageIcon fortuneTellerIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("fortuneteller.jpg")));

// Set the icon for the JFrame
        setIconImage(fortuneTellerIcon.getImage());


        initializeFortunes();
        createComponents();
        layoutComponents();
        addActionListeners();

        pack();
        centerFrameOnScreen();
    }

    private void initializeFortunes() {
        fortunes = new ArrayList<>();
        fortunes.add("You will find unexpected treasure in the most unlikely place");
        fortunes.add("Adventure awaits just around the corner");
        fortunes.add("Your sense of humor brings joy to those around you");
        fortunes.add("The journey of a thousand miles begins with a single step");
        fortunes.add("Your kindness will lead you to great success");
        fortunes.add("A delicious meal is in your near future");
        // Add more fortunes here
    }

    private void createComponents() {
        titleLabel = new JLabel("Fortune Teller");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 36));

        fortuneTextArea = new JTextArea(10, 40);
        fortuneTextArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        fortuneTextArea.setEditable(false);

        // Initialize scrollPane here
        scrollPane = new JScrollPane(fortuneTextArea);

        fortuneButton = new JButton("Read My Fortune!");
        quitButton = new JButton("Quit");
    }

    private void layoutComponents() {
        JPanel topPanel = new JPanel();
        topPanel.add(titleLabel);

        JPanel middlePanel = new JPanel();
        middlePanel.add(scrollPane);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(fortuneButton);
        bottomPanel.add(quitButton);

        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(middlePanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addActionListeners() {
        fortuneButton.addActionListener((ActionEvent e) -> {
            showRandomFortune();
        });

        quitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    private void showRandomFortune() {
        int randomIndex;
        do {
            randomIndex = new Random().nextInt(fortunes.size());
        } while (randomIndex == lastFortuneIndex);

        String fortune = fortunes.get(randomIndex);
        fortuneTextArea.append(fortune + "\n");
        lastFortuneIndex = randomIndex;
    }

    private void centerFrameOnScreen() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        int screenWidth = (int) (screenDimension.getWidth() * 0.75);
        int screenHeight = (int) screenDimension.getHeight();
        int frameWidth = getWidth();
        int frameHeight = getHeight();

        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FortuneTellerFrame frame = new FortuneTellerFrame();
            frame.setVisible(true);
        });
    }
}