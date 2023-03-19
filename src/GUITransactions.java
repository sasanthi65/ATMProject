import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUITransactions {
    public GUITransactions(String accNo) {

        JTable table = new JTable();
        Object[] columns = {"Account Number", "Date", "Time", "Amount", "Transaction Type", "Balance"};
        DefaultTableModel model = new DefaultTableModel();

        Object[] Row = new Object[7];

        JFrame frame = new JFrame("Transaction History");
        frame.setBounds(100, 100, 700, 550);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().setForeground(Color.LIGHT_GRAY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel welcome = new JLabel("Account Number  "+ accNo);
        welcome.setBounds(250,10,260,30);
        welcome.setFont(new Font("Tahoma",Font.BOLD,18));
        welcome.setForeground(Color.BLACK);
        frame.getContentPane().add(welcome);

        JButton back = new JButton("Back");
        back.setLocation(280, 430);
        back.setSize(120, 35);
        frame.getContentPane().add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIHome(accNo);
                frame.dispose();
            }
        });

        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setSelectionForeground(Color.black);
        table.setFont(new Font("Thahoma", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(140);
        table.getColumnModel().getColumn(1).setPreferredWidth(140);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(3).setPreferredWidth(140);
        table.getColumnModel().getColumn(4).setPreferredWidth(140);
        table.getColumnModel().getColumn(5).setPreferredWidth(140);
        table.setEnabled(false);
        table.setAutoCreateRowSorter(true);

        JScrollPane pane = new JScrollPane(table);
        pane.setBackground(Color.lightGray);
        pane.setForeground(Color.white);
        pane.setBounds(40, 50, 600, 350);
        frame.getContentPane().add(pane);

        frame.revalidate();
        frame.setVisible(true);

        File input = new File("transaction.txt");
        FileReader fr = null;

        String searchWord, str;
        searchWord = accNo;

        try {
            fr = new FileReader(input);
            BufferedReader br = new BufferedReader(fr);

            while ((str = br.readLine()) != null) {
                if (str.contains(searchWord)) {
                    model = (DefaultTableModel) table.getModel();
                    Object[] lines = str.lines().toArray();

                    for (int i = 0; i < lines.length; i++) {
                        String[] row = lines[i].toString().split(" ");
                        model.addRow(row);
                    }
                }
            }
            fr.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"No transactions to show");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}



