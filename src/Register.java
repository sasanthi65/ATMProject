import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Register {
    Boolean complete = false;
    int lines;

    public void countLines() {
        lines=0;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("customerDetails.txt","rw");
            for (int i=0; randomAccessFile.readLine()!= null; i++){
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeRegisterDetails(String accNo, String custName, String nic, String tel,
                                     String address, String gender,String psw, String confpsw) throws IOException {
        if (custName.isBlank()|| nic.isBlank()|| tel.isBlank()|| address.isBlank()){
            JOptionPane.showMessageDialog(null,"Cannot leave empty fields",
                    "Registration Unsuccessful",JOptionPane.ERROR_MESSAGE);
        } else if (nic.length()< 9 || nic.length() > 12){
            JOptionPane.showMessageDialog(null,"Please enter valid NIC number",
                    "Registration Unsuccessful",JOptionPane.ERROR_MESSAGE);
        } else if (tel.length() != 10){
            JOptionPane.showMessageDialog(null,"Please enter valid telephone number",
                    "Registration Unsuccessful",JOptionPane.ERROR_MESSAGE);
        } else if (psw.isBlank()){
            JOptionPane.showMessageDialog(null,"Please enter Password",
                    "Registration Unsuccessful",JOptionPane.ERROR_MESSAGE);
        }else if (confpsw.isBlank()){
            JOptionPane.showMessageDialog(null, "Please confirm password",
                    "Registration Unsuccessful", JOptionPane.ERROR_MESSAGE);
        }else if (!(psw.equals(confpsw))){
            JOptionPane.showMessageDialog(null,"Password does not match",
                    "Registration Unsuccessful",JOptionPane.ERROR_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null,"Your Account number is "+accNo,
                    "Registration Successful",JOptionPane.INFORMATION_MESSAGE);
            complete = true;
        }


        if (complete == true) {
            // write registration details into customerDetails.txt file
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile("customerDetails.txt", "rw");
                for (int i = 0; i < lines; i++) {
                    randomAccessFile.readLine();
                }
                randomAccessFile.writeBytes("\r\n");
                randomAccessFile.writeBytes("Account:" + accNo + "\r\n");
                randomAccessFile.writeBytes("Customer Name:" + custName + "\r\n");
                randomAccessFile.writeBytes("NIC:" + nic + "\r\n");
                randomAccessFile.writeBytes("Telephone Number:" + tel + "\r\n");
                randomAccessFile.writeBytes("Address:" + address + "\r\n");
                randomAccessFile.writeBytes("Gender:"+gender+"\r\n");

            } catch (IOException exception) {
                exception.printStackTrace();
            }

            // write login details into login.txt file
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile("login.txt", "rw");
                for (int i = 0; i < lines; i++) {
                    randomAccessFile.readLine();
                }
                randomAccessFile.writeBytes("Account:" + accNo + "\r\n");
                randomAccessFile.writeBytes("Password:" + psw + "\r\n");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            RandomAccessFile randomAccessFile = new RandomAccessFile("balance.txt","rw");
            for (int i = 0; i < lines; i++){
                randomAccessFile.readLine();
            }
            randomAccessFile.writeBytes(accNo +","+"0"+ "\r\n");
        }
    }
}

