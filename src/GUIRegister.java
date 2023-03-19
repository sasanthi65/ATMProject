import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class GUIRegister {
    private ImageIcon icon;
    private JLabel lbl;

    public GUIRegister() {
        JPanel pane = new JPanel();
        pane.setLayout(null);
        //pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Register"));
        pane.setSize(490, 500);

        icon = new ImageIcon(this.getClass().getResource("/logo.jpg"));
        lbl = new JLabel(icon);
        lbl.setSize(490, 500);

        JLabel customerName = new JLabel("Customer Name");
        customerName.setFont(new Font("Tahoma", Font.BOLD, 15));
        customerName.setForeground(Color.BLACK);
        customerName.setLocation(70, 10);
        customerName.setSize(150, 30);

        JLabel nic = new JLabel("NIC");
        nic.setFont(new Font("Tahoma", Font.BOLD, 15));
        nic.setForeground(Color.BLACK);
        nic.setLocation(70, 50);
        nic.setSize(120, 30);

        JLabel tel = new JLabel("Telephone");
        tel.setFont(new Font("Tahoma", Font.BOLD, 15));
        tel.setForeground(Color.BLACK);
        tel.setLocation(70, 90);
        tel.setSize(150, 30);

        JLabel address = new JLabel("Address");
        address.setFont(new Font("Tahoma", Font.BOLD, 15));
        address.setForeground(Color.BLACK);
        address.setLocation(70, 130);
        address.setSize(150, 30);

        JLabel gender = new JLabel("Gender");
        gender.setFont(new Font("Tahoma", Font.BOLD, 15));
        gender.setForeground(Color.BLACK);
        gender.setLocation(70, 230);
        gender.setSize(120, 30);

        JLabel password = new JLabel("Password");
        password.setFont(new Font("Tahoma", Font.BOLD, 15));
        password.setForeground(Color.BLACK);
        password.setLocation(70, 270);
        password.setSize(120, 30);

        JLabel confirmPassword = new JLabel("Confirm Password");
        confirmPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
        confirmPassword.setForeground(Color.BLACK);
        confirmPassword.setLocation(70, 310);
        confirmPassword.setSize(150, 30);

        JTextField customerText = new JTextField();
        customerText.setLocation(230, 10);
        customerText.setSize(140, 30);


        JTextField nicText = new JTextField();
        nicText.setLocation(230, 50);
        nicText.setSize(140, 30);
        nicText.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String value = nicText.getText();
                int l = value.length();
                if (l >= 12){
                    JOptionPane.showMessageDialog(pane,"NIC numbers should be within the range of 0-12");
                    nicText.setEditable(false);
                }
                nicText.setEditable(true);
            }
        });

        JTextField telText = new JTextField();
        telText.setLocation(230, 90);
        telText.setSize(140, 30);
        telText.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been released.
             *
             * @param e
             */
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String value = telText.getText();
                if (value.length() > 10){
                    telText.setEditable(false);
                    JOptionPane.showMessageDialog(pane,"Telephone number must consists 10 digits");
                }
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                        e.getKeyCode() == KeyEvent.VK_ENTER) {
                    telText.setEditable(true);
                } else {
                    telText.setEditable(false);
                    JOptionPane.showMessageDialog(pane, "Please enter only numeric digits(0-9)");
                    telText.setEditable(true);
                }
            }
        });

        JTextArea addressText = new JTextArea();
        addressText.setLocation(230, 130);
        addressText.setSize(140, 90);
        addressText.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        addressText.setLineWrap(true);

        String s1[] = {"Male", "Female"};

        JComboBox genderSelect = new JComboBox(s1);
        genderSelect.setSize(140, 30);
        genderSelect.setLocation(230, 230);

        JPasswordField p1 = new JPasswordField();
        p1.setLocation(230, 270);
        p1.setSize(140, 30);
        p1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                String value = p1.getText();
                if (value.length() > 4) {
                    p1.setEditable(false);
                    JOptionPane.showMessageDialog(pane, "Please enter 4 numeric digits only");
                }
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    p1.setEditable(true);
                } else {
                    p1.setEditable(false);
                    JOptionPane.showMessageDialog(pane, "Please enter only numeric digits(0-9)");
                    p1.setEditable(true);
                }
            }
        });

        JPasswordField p2 = new JPasswordField();
        p2.setLocation(230, 310);
        p2.setSize(140, 30);
        p2.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                String value = p2.getText();
                if (value.length() > 4) {
                    p2.setEditable(false);
                    JOptionPane.showMessageDialog(pane, "Please enter 4 numeric digits only");
                }
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    p2.setEditable(true);
                } else {
                    p2.setEditable(false);
                    JOptionPane.showMessageDialog(pane, "Please enter only numeric digits(0-9)");
                    p2.setEditable(true);
                }
            }
        });

        JButton reg = new JButton("Register");
        reg.setLocation(50, 390);
        reg.setSize(140, 30);

        JButton back = new JButton("Back");
        back.setLocation(290, 390);
        back.setSize(140, 30);

        JFrame frame = new JFrame("Register");
        frame.setBounds(200, 200, 490, 500);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        pane.add(reg);
        pane.add(back);
        pane.add(customerText);
        pane.add(nicText);
        pane.add(telText);
        pane.add(addressText);
        pane.add(genderSelect);
        pane.add(p1);
        pane.add(p2);
        pane.add(customerName);
        pane.add(nic);
        pane.add(tel);
        pane.add(address);
        pane.add(gender);
        pane.add(password);
        pane.add(confirmPassword);
        pane.add(lbl);

        frame.setVisible(true);

        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int random = ThreadLocalRandom.current().nextInt(1000000);
                try {
                    Register rg = new Register();
                    rg.countLines();
                    rg.writeRegisterDetails((Integer.toString(random)), customerText.getText(), nicText.getText(), telText.getText(),
                            addressText.getText(), genderSelect.getSelectedItem().toString(),String.valueOf(p1.getPassword()), String.valueOf(p2.getPassword()));
                    if (rg.complete == true){
                        new GUILogin();
                        frame.dispose();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUILogin();
                frame.dispose();
            }
        });
    }
}
