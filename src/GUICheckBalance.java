import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICheckBalance extends Transaction {

    public GUICheckBalance(String accNo, String balance){

        JPanel pane = new JPanel();
        pane.setLayout(null);
        pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Check Balance"));
        pane.setSize(400,300);

        JLabel l1 = new JLabel("Account Number "+accNo);
        l1.setFont(new Font("Tahoma",Font.PLAIN,12));
        l1.setLocation(180, 30);
        l1.setSize(170, 30);

        JLabel l2 = new JLabel("Balance is Rs."+balance);
        l2.setFont(new Font("Tahoma",Font.BOLD,30));
        l2.setLocation(100, 100);
        l2.setSize(350, 30);

        JButton back = new JButton("Back");
        back.setLocation(180, 220);
        back.setSize(120, 35);

        JFrame frame = new JFrame("Deposit");
        frame.setBounds(200,200,500, 350);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(pane);
        pane.add(back);
        pane.add(l1);
        pane.add(l2);
        frame.setVisible(true);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIHome(accNo);
                frame.dispose();
            }
        });
    }
}
