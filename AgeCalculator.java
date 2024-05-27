import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AgeCalculator extends JFrame {

    private JTextField birthDateField;
    private JLabel resultLabel;

    public AgeCalculator() {
        // Set up the frame
        setTitle("Age Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Add components to the panel
        panel.add(new JLabel("Enter your birth date (YYYY-MM-DD):"));
        birthDateField = new JTextField();
        panel.add(birthDateField);

        JButton calculateButton = new JButton("Calculate Age");
        panel.add(calculateButton);

        resultLabel = new JLabel("Your age will be displayed here.");
        panel.add(resultLabel);

        // Add the panel to the frame
        add(panel);

        // Add action listener to the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAge();
            }
        });
    }

    private void calculateAge() {
        String birthDateText = birthDateField.getText();
        try {
            LocalDate birthDate = LocalDate.parse(birthDateText, DateTimeFormatter.ISO_LOCAL_DATE);
            // Calculate the age
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthDate, currentDate);
            // Display the result
            resultLabel.setText("You are " + age.getYears() + " years old.");
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            resultLabel.setText("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AgeCalculator().setVisible(true);
            }
        });
    }
}
