import javax.swing.*;

public class FortuneTellerViewer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                FortuneTellerFrame frame = new FortuneTellerFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error creating frame: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}