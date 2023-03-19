import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GUITransfer extends Transaction{
    private ImageIcon icon;
    private JLabel lbl;

    public GUITransfer(String accNo, String TransferAccount, int TransferAmoount ){

        JPanel pane = new JPanel();
        pane.setLayout(null);
        pane.setSize(550,300);

        icon = new ImageIcon(this.getClass().getResource("/logo.jpg"));
        lbl = new JLabel(icon);
        lbl.setSize(550, 400);

        JLabel l1 = new JLabel("Account Transfer");
        l1.setFont(new Font("Tahoma",Font.BOLD,12));
        l1.setForeground(Color.BLACK);
        l1.setLocation(120, 110);
        l1.setSize(120, 30);

        JLabel l2 = new JLabel("Amount");
        l2.setFont(new Font("Tahoma",Font.BOLD,12));
        l2.setForeground(Color.BLACK);
        l2.setLocation(120, 150);
        l2.setSize(120, 30);

        JLabel welcome = new JLabel("Account Number "+ accNo);
        welcome.setBounds(170,10,260,30);
        welcome.setFont(new Font("Tahoma",Font.BOLD,18));
        welcome.setForeground(Color.BLACK);

        JTextField t1 = new JTextField();
        t1.setLocation(260, 110);
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

        JTextField t2 = new JTextField();
        t2.setLocation(260, 150);
        t2.setSize(140,30 );
        t2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    t2.setEditable(true);
                } else {
                    t2.setEditable(false);
                    JOptionPane.showMessageDialog(pane, "Please enter only numeric digits(0-9)");
                    t2.setEditable(true);
                }
            }
        });

        JButton transfer = new JButton("Transfer");
        transfer.setLocation(210, 200);
        transfer.setSize(120, 35);

        JButton back = new JButton("Back");
        back.setLocation(210, 250);
        back.setSize(120, 35);
        back.setFocusable(false);

        JFrame frame = new JFrame("Money Transfer");
        frame.setBounds(200,200,550, 400);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(pane);
        pane.add(t1);
        pane.add(t2);
        pane.add(transfer);
        pane.add(back);
        pane.add(welcome);
        pane.add(l1);
        pane.add(l2);
        pane.add(lbl);

        frame.setVisible(true);

        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((t1.getText().isEmpty() || (t2.getText().isEmpty()))) {
                    JOptionPane.showMessageDialog(null, "Can not leave empty fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Transaction transaction = new Transaction();
                    transaction.countLines();
                    try {
                        transaction.Transfer(accNo, t1.getText(), Integer.parseInt(t2.getText()));
                        //transaction.Transfer();
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

