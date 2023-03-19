import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    int lines, balance, balance2, updatedBalance, transferBalance, TransferAmount;
    String account ,account2 , getBalance, getBalance2, readBalance = "";
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    // method to count lines in text file
    public void countLines() {
        lines = 0;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("balance.txt", "rw");
            for (int i = 0; randomAccessFile.readLine() != null; i++) {
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method to calculate new balance after deposit and write details into transaction.txt
    public void writeDeposit(String accNo, int depositAmount) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("balance.txt", "rw");
        for (int i = 0; i < lines; i++) {
            String split[] = randomAccessFile.readLine().split(",");
            account = split[0];
            balance = Integer.parseInt(split[1]);

            if (account.equals(accNo)) {
                if (balance != 0 || depositAmount >= 1000) {
                    getBalance = String.valueOf(balance);
                    updatedBalance = balance + depositAmount;

                    replaceSelected(accNo, getBalance, String.valueOf(updatedBalance));
                    JOptionPane.showMessageDialog(null, "Successfully Deposited " + depositAmount + " into " + accNo
                            + ". Your Balance is " + updatedBalance);

                    // write transaction details into transaction.txt
                    try {
                        int ln = 0;
                        RandomAccessFile raf = new RandomAccessFile("transaction.txt", "rw");
                        for (int x = 0; raf.readLine() != null; x++) {
                            ln++;
                        }
                        for (int j = 0; j < ln; j++) {
                            raf.readLine();
                        }

                        raf.writeBytes(account + " ");
                        raf.writeBytes(dateFormat.format(date) + " ");
                        raf.writeBytes(String.valueOf(depositAmount) + " ");
                        raf.writeBytes("Deposit" + " ");
                        raf.writeBytes(String.valueOf(updatedBalance) + "\n");


                        raf.close();
                    } catch (FileNotFoundException exception) {
                        exception.printStackTrace();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (balance == 0 && depositAmount < 1000) {
                    JOptionPane.showMessageDialog(null, "Initial deposit amount should be greater than Rs.1000.00",
                            "Deposit Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
    }

    // Withdraw Money
    public void writeWithdraw(String accNo, int WithdrawAmount) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("balance.txt", "rw");
        for (int i = 0; i < lines; i++) {
            String split[] = randomAccessFile.readLine().split(",");
            account = split[0];
            balance = Integer.parseInt(split[1]);

            if (account.equals(accNo)) {

                if ((balance - WithdrawAmount) >= 1000) {
                    getBalance = String.valueOf(balance);
                    updatedBalance = balance - WithdrawAmount;

                    replaceSelected(accNo, getBalance, String.valueOf(updatedBalance));
                    JOptionPane.showMessageDialog(null, "Withdrew Successfully. Your Account balance is " + updatedBalance,
                            "Withdrawal Successful", JOptionPane.INFORMATION_MESSAGE);

                    // write transaction details into transaction.txt
                    try {
                        int ln = 0;
                        RandomAccessFile raf = new RandomAccessFile("transaction.txt", "rw");
                        for (int x = 0; raf.readLine() != null; x++) {
                            ln++;
                        }
                        for (int j = 0; j < ln; j++) {
                            raf.readLine();
                        }
                        raf.writeBytes(account + " ");
                        raf.writeBytes(dateFormat.format(date) + " ");
                        raf.writeBytes(String.valueOf(WithdrawAmount) + " ");
                        raf.writeBytes("withdraw" + " ");
                        raf.writeBytes(String.valueOf(updatedBalance) + "\n");
                        raf.close();
                    } catch (FileNotFoundException exception) {
                        exception.printStackTrace();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (balance - WithdrawAmount < 1000) {
                    JOptionPane.showMessageDialog(null, "Cannot withdraw from your account. Your account balance is " + balance,
                            "Withdrawal Unsuccessful", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
    }


    public void replaceSelected(String accNo, String oldBalance, String newBalance) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("balance.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            String getAccount = "";
            String oldLine = "";
            String newLine = "";

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');

                String[] split = line.split(",");
                getAccount = split[0];

                if (getAccount.equals(accNo)) {
                    oldLine = line;

                    line.split(",");
                    newLine = line.replace(getAccount + "," + oldBalance, getAccount + "," + newBalance);

                }
            }
            file.close();

            String inputStr = inputBuffer.toString();
            inputStr = inputStr.replace(oldLine, newLine);

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("balance.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    public void getBalance(String accNo) {
        String account;
        String balance;

        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile("balance.txt", "rw");
            for (int i = 0; i < lines; i++) {
                String[] split = randomAccessFile.readLine().split(",");
                account = split[0];
                balance = split[1];

                if (account.equals(accNo)) {
                    readBalance = balance;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Transfer(String accNo, String TransferAccount, int TransferAmount) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("balance.txt", "rw");
        for (int i = 0; i < lines; i++) {
            String split[] = randomAccessFile.readLine().split(",");
            account = split[0];
            balance = Integer.parseInt(split[1]);

            if (account.equals(accNo)) {
                RandomAccessFile randomaccessFile = new RandomAccessFile("balance.txt", "rw");
                for (int k = 0; k < lines; k++) {
                    String splitvalue[] = randomaccessFile.readLine().split(",");
                    account2 = splitvalue[0];
                    balance2 = Integer.parseInt(splitvalue[1]);

                    if (account2.equals(TransferAccount)) {
                        if (balance - TransferAmount >= 1000) {
                            getBalance = String.valueOf(balance);
                            getBalance2 = String.valueOf(balance2);
                            updatedBalance = balance - TransferAmount;
                            transferBalance = balance2 + TransferAmount;

                            // write transaction details into transaction.txt
                            try {
                                int ln = 0;
                                RandomAccessFile raf = new RandomAccessFile("transaction.txt", "rw");
                                for (int x = 0; raf.readLine() != null; x++) {
                                    ln++;
                                    System.out.println(ln);
                                }
                                for (int j = 0; j < ln; j++) {
                                    raf.readLine();
                                }
                                raf.writeBytes(account + " ");
                                raf.writeBytes(dateFormat.format(date) + " ");
                                raf.writeBytes(String.valueOf(TransferAmount) + " ");
                                raf.writeBytes("Withdraw" + " ");
                                raf.writeBytes(String.valueOf(updatedBalance) + "\n");

                                raf.writeBytes(account2 + " ");
                                raf.writeBytes(dateFormat.format(date) + " ");
                                raf.writeBytes(String.valueOf(TransferAmount) + " ");
                                raf.writeBytes("Deposit" + " ");
                                raf.writeBytes(String.valueOf(transferBalance) + "\n");

                                raf.close();

                            } catch (FileNotFoundException exception) {
                                exception.printStackTrace();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } else if (balance - TransferAmount < 1000) {
                            // Error message - keep min 1000
                            JOptionPane.showMessageDialog(null, "Cannot Transfer money from your account. Your account balance is " + balance,
                                    "Transfer Unsuccessful", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        replaceSelected(accNo, getBalance, String.valueOf(updatedBalance));
                        replaceSelected(TransferAccount, getBalance2, String.valueOf(transferBalance));
                        JOptionPane.showMessageDialog(null, "Transfer Successful. Your Account balance is " + updatedBalance,
                                "Transfer Successful", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
            }
        }if (!account2.equals(TransferAccount)){
            JOptionPane.showMessageDialog(null,  " Account Number "+TransferAccount +" does not match ",
                    "Transfer Unsuccessful", JOptionPane.ERROR_MESSAGE);
        }
    }
}



