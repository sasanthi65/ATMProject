import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIHome extends Transaction{
    private ImageIcon icon ;
    private JLabel lbl ;

    public GUIHome(String accNo){

        JPanel pane = new JPanel();
        pane.setLayout(null);
        pane.setSize(600,350);

        icon = new ImageIcon(this.getClass().getResource("/logo.jpg"));
        lbl = new JLabel(icon);
        lbl.setSize(600, 350);

        JLabel welcome = new JLabel("Account Number: ");
        welcome.setFont(new Font("Tahoma",Font.BOLD,14));
        welcome.setBounds(200,10,150,30);
        welcome.setForeground(Color.BLACK);

        JLabel accountNumber = new JLabel(accNo);
        accountNumber.setFont(new Font("Tahoma",Font.BOLD,14));
        accountNumber.setBounds(330,10,80,30);
        accountNumber.setForeground(Color.BLACK);

        JButton deposit = new JButton("Deposit");
        deposit.setLocation(100, 80);
        deposit.setSize(150, 35);

        JButton withdraw = new JButton("Withdraw");
        withdraw.setLocation(100, 160);
        withdraw.setSize(150, 35);

        JButton balance = new JButton("Check Balance");
        balance.setLocation(100, 240);
        balance.setSize(150, 35);

        JButton history = new JButton("Transaction History");
        history.setLocation(350, 80);
        history.setSize(150, 35);

        JButton transfer = new JButton("Transfer");
        transfer.setLocation(350, 160);
        transfer.setSize(150, 35);

        JButton logout = new JButton("Logout");
        logout.setLocation(350, 240);
        logout.setSize(150, 35);

        JFrame frame = new JFrame("Home");
        frame.setBounds(200,200,615, 389);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        frame.add(pane);
        pane.add(welcome);
        pane.add(accountNumber);
        pane.add(deposit);
        pane.add(withdraw);
        pane.add(balance);
        pane.add(history);
        pane.add(transfer);
        pane.add(logout);
        pane.add(lbl);
        frame.setVisible(true);

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIDeposit(accountNumber.getText());
                frame.dispose();
            }
        });

        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIWithdraw(accountNumber.getText());
                frame.dispose();
            }
        });

        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transaction tr = new Transaction();
                new GUITransfer(accountNumber.getText(),tr.account2, TransferAmount);
                frame.dispose();
            }
        });

        balance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transaction tr = new Transaction();
                tr.countLines();
                tr.getBalance(accountNumber.getText());
                new GUICheckBalance(accountNumber.getText(),tr.readBalance);
                frame.dispose();
            }
        });
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUITransactions(accountNumber.getText());
                frame.dispose();
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null,"Do you want to exit?","Logout",JOptionPane.YES_NO_OPTION);
                switch (response){
                    case 0:
                        new GUILogin();
                        frame.dispose();
                        break;
                }
            }
        });
    }
}
