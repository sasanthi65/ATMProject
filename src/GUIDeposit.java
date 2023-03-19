import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GUIDeposit extends Transaction {
    private ImageIcon icon;
    private JLabel lbl;

    public GUIDeposit(String accNo){

        JPanel pane = new JPanel();
        pane.setLayout(null);
        pane.setSize(550,400);

        icon = new ImageIcon(this.getClass().getResource("/logo.jpg"));
        lbl = new JLabel(icon);
        lbl.setSize(550, 400);

        JLabel l1 = new JLabel("Amount");
        l1.setFont(new Font("Tahoma",Font.BOLD,12));
        l1.setForeground(Color.BLACK);
        l1.setLocation(150, 110);
        l1.setSize(120, 30);

        JLabel l2 = new JLabel();
        l2.setFont(new Font("Tahoma",Font.PLAIN,12));
        l2.setLocation(220, 140);
        l2.setSize(200, 50);

        JLabel welcome = new JLabel("Account Number "+ accNo);
        welcome.setBounds(160,10,260,30);
        welcome.setFont(new Font("Tahoma",Font.BOLD,18));
        welcome.setForeground(Color.BLACK);

        JTextField t1 = new JTextField();
        t1.setLocation(210, 110);
        t1.setSize(140,30 );
        t1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    t1.setEditable(true);
                } else {
                    t1.setEditable(false);
                    JOptionPane.showMessageDialog(pane, "Please enter only numeric digits(0-9)");
                    t1.setEditable(true);
                }
            }
        });

        JButton deposit = new JButton("Deposit");
        deposit.setLocation(210, 190);
        deposit.setSize(120, 35);

        JButton back = new JButton("Back");
        back.setLocation(210, 250);
        back.setSize(120, 35);

        JFrame frame = new JFrame("Deposit");
        frame.setBounds(200,200,550, 400);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(pane);
        pane.add(deposit);
        pane.add(back);
        pane.add(t1);
        pane.add(welcome);
        pane.add(l1);
        pane.add(l2);
        pane.add(lbl);

        frame.setVisible(true);

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((t1.getText().isEmpty())){
                    JOptionPane.showMessageDialog(null, "Do not leave empty fields", "Error",JOptionPane.ERROR_MESSAGE);
                }else {
                    Transaction transaction = new Transaction();
                    transaction.countLines();
                    try {
                        transaction.writeDeposit(accNo, Integer.parseInt(t1.getText()));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    new GUIHome(accNo);
                    frame.dispose();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIHome(accNo);
                frame.dispose();
            }
        });
    }
}
