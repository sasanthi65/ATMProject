import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Login {
    Boolean isSuccessful = false;
    int lines;

    // count number of lines in text file in order to loop till the number of lines
    public void countLines() {
        lines=0;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("login.txt","rw");
            for (int i=0; randomAccessFile.readLine()!= null; i++){
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // add data into text file
    public void readLoginDetails(String accNo, String psw) throws IOException {
        String account="";
        String password="";
        int i;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("login.txt", "rw");
            for (i = 0; i < lines; i+=2) { //because there are 2 lines for one set of login credentials
                account = randomAccessFile.readLine().substring(8);
                password = randomAccessFile.readLine().substring(9);

                if (accNo.equals(account) & psw.equals(password)) {
                    JOptionPane.showMessageDialog(null, "Successful Login", "Login",JOptionPane.INFORMATION_MESSAGE);
                    isSuccessful = true;
                    break;
                }  else if (accNo.isBlank() || psw.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please enter account number and password to continue","Invalid Login",JOptionPane.ERROR_MESSAGE);
                    break;
                } else if (i ==(lines-2)){ // new account comes after 2 lines
                    JOptionPane.showMessageDialog(null, "Incorrect password or account number","Invalid Login",JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }

            // If there are no values in the login.txt file
            if (lines == 0) {
                if (accNo.isBlank() || psw.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please enter account number and password to continue", "Invalid Login",JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "User not found", "Invalid Login",JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException exception){
            exception.printStackTrace();
        }
    }

}


