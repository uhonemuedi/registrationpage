import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationPage extends JFrame implements ActionListener {

    // UI components
    JLabel labelTitle, labelName, labelSurname, labelEmail, labelPhone, labelUsername, labelPassword;
    JTextField textName, textSurname, textEmail, textPhone, textUsername;
    JPasswordField textPassword;
    JButton togglePasswordBtn; // Button to show/hide password
    JButton buttonRegister;

    // Registered credentials
    String registeredUsername;
    String registeredPassword;

    public RegistrationPage() {
        setTitle("ChatApp Registration Page");

        // Main panel layout setup
        JPanel mainPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        mainPanel.setBackground(new Color(255, 228, 240));
        setContentPane(mainPanel);

        // Title
        labelTitle = new JLabel("User Registration", JLabel.CENTER);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitle.setOpaque(true);
        labelTitle.setBackground(new Color(255, 182, 193));
        labelTitle.setForeground(Color.BLACK);

        // Labels
        labelName = new JLabel("First Name:");
        labelSurname = new JLabel("Last Name:");
        labelEmail = new JLabel("Email:");
        labelPhone = new JLabel("Cell Phone (e.g. 0834567891):");
        labelUsername = new JLabel("Username:");
        labelPassword = new JLabel("Password:");

        // Text fields
        textName = new JTextField();
        textSurname = new JTextField();
        textEmail = new JTextField();
        textPhone = new JTextField();
        textUsername = new JTextField();
        textPassword = new JPasswordField();

        // Eye icon button to toggle password visibility
        togglePasswordBtn = new JButton("👁");
        togglePasswordBtn.setPreferredSize(new Dimension(50, 30));
        togglePasswordBtn.setFocusPainted(false);
        togglePasswordBtn.setBackground(Color.WHITE);
        togglePasswordBtn.addActionListener(e -> {
            togglePasswordVisibility(textPassword);
        });

        // Register button
        buttonRegister = new JButton("Register");
        buttonRegister.addActionListener(this);
        buttonRegister.setBackground(new Color(255, 105, 180));
        buttonRegister.setForeground(Color.WHITE);
        buttonRegister.setFocusPainted(false);
        buttonRegister.setFont(new Font("Arial", Font.BOLD, 14));

        // Adding components to the panel
        mainPanel.add(labelTitle); mainPanel.add(new JLabel());
        mainPanel.add(labelName); mainPanel.add(textName);
        mainPanel.add(labelSurname); mainPanel.add(textSurname);
        mainPanel.add(labelEmail); mainPanel.add(textEmail);
        mainPanel.add(labelPhone); mainPanel.add(textPhone);
        mainPanel.add(labelUsername); mainPanel.add(textUsername);

        // Combine password field and eye icon
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBackground(new Color(255, 228, 240));
        passwordPanel.add(textPassword, BorderLayout.CENTER);
        passwordPanel.add(togglePasswordBtn, BorderLayout.EAST);
        mainPanel.add(labelPassword);
        mainPanel.add(passwordPanel);

        mainPanel.add(new JLabel()); mainPanel.add(buttonRegister);

        // Frame setup
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Toggle password visibility on/off
    private void togglePasswordVisibility(JPasswordField passwordField) {
        if (passwordField.getEchoChar() == '•') {
            passwordField.setEchoChar((char) 0); // Show password
        } else {
            passwordField.setEchoChar('•'); // Hide password
        }
    }

    // Handle register button click
    @Override
    public void actionPerformed(ActionEvent e) {
        String firstName = textName.getText();
        String lastName = textSurname.getText();
        String email = textEmail.getText();
        String phone = textPhone.getText();
        String username = textUsername.getText();
        String password = new String(textPassword.getPassword());

        // Validate inputs
        if (!isValidUsername(username)) {
            JOptionPane.showMessageDialog(this, "Username must contain an underscore (_) and be no more than 5 characters long.");
            return;
        }

        if (!isValidPassword(password)) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long, include an uppercase letter, a number, and a special character.");
            return;
        }

        if (!isValidSAphone(phone)) {
            return;
        }

        // Save credentials
        registeredUsername = username;
        registeredPassword = password;

        JOptionPane.showMessageDialog(this, "Your account has been registered successfully!");

        // Open login dialog
        new LoginPage(); // Open login page after registration Page Java
        dispose(); // Close the registration page Java
    }

    // Validate username format
    private boolean isValidUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Validate password format
    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#$%^&*()_+=<>?/{}~|].*");
    }

    // Validate South African phone number
    private boolean isValidSAphone(String phone) {
        if (phone.matches("0\\d{9}")) {
            JOptionPane.showMessageDialog(null, "Cell phone number successfully added: +27" + phone.substring(1));
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted. It must be 10 digits and start with 0.");
            return false;
        }
    }

    // Main method
    public static void main(String[] args) {
        new RegistrationPage();
    }
}
