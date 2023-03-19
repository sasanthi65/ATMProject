import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GUILogin {
    private ImageIcon icon ;
    private JLabel lbl ;

    public GUILogin(){
        JPanel pane = new JPanel();
        pane.setLayout(null);
        pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login"));
        pane.setSize(600,400);

        icon = new ImageIcon(this.getClass().getResource("/logo.jpg"));
        lbl = new JLabel(icon);
        lbl.setSize(600, 400);

        JLabel l1 = new JLabel("Account Number");
        l1.setFont(new Font("Tahoma",Font.BOLD,14));
        l1.setForeground(Color.BLACK);
        l1.setLocation(150, 110);
        l1.setSize(150, 30);

        JTextField t1 = new JTextField();
        t1.setLocation(300, 110);
        t1.setSize(140,30 );

        JLabel l2 = new JLabel("PIN Number");
        l2.setFont(new Font("Tahoma",Font.BOLD,14));
        l2.setLocation(150, 150);
        l2.setForeground(Color.BLACK);
        l2.setSize(120, 30);

        JPasswordField p1 = new JPasswordField();
        p1.setLocation(300, 150);
        p1.setSize(140,30 );
        p1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke){
                String value = p1.getText();
                int l = value.length();
                if (value.length() > 4) {
                    p1.setEditable(false);
                    JOptionPane.showMessageDialog(pane, "Please enter 4 numeric digits only");
                }
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'|| ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    p1.setEditable(true);
                } else {
                    p1.setEditable(false);
                    JOptionPane.showMessageDialog(pane, "Please enter only numeric digits(0-9)");
                    p1.setEditable(true);
                }
            }
        });

        JLabel l3 = new JLabel("Need to create new account?");
        l3.setFont(new Font("Tahoma",Font.BOLD,14));
        l3.setForeground(Color.BLACK);
        l3.setBounds(190, 240, 240, 50);

        JButton login = new JButton("login");
        login.setLocation(220, 210);
        login.setSize(140, 35);

        JButton reg = new JButton("Register");
        reg.setLocation(220, 290);
        reg.setSize(140, 35);

        JFrame frame = new JFrame("login");
        frame.setBounds(200,200,600, 400);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(pane);
        pane.add(t1);
        pane.add(p1);
        pane.add(login);
        pane.add(reg);
        pane.add(l1);
        pane.add(l2);
        pane.add(l3);
        pane.add(lbl);

        frame.setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login lg = new Login();
                lg.countLines();
                try {
                    lg.readLoginDetails(t1.getText(),String.valueOf(p1.getPassword()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (lg.isSuccessful == true){
                    new GUIHome(t1.getText()); // pass account number
                    frame.dispose();
                }else {
                    t1.setText("");
                    p1.setText("");
                }
            }
        });

        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIRegister();
                frame.dispose();
            }
        });

    }

    public static void main(String[] args) {
        new GUILogin();
    }
}

