package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class login extends JFrame implements ActionListener{
    JButton login, cancel, signup;
    JTextField username, password;
    Choice loginin;
    login(){
        super("LOGIN PAGE");
        getContentPane().setBackground(Color.PINK);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(300,20,100,20);
        add(lblusername);

        username= new JTextField();
        username.setBounds(400,20,150,20);
        add(username);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300,60,100,20);
        add(lblpassword);

        password= new JTextField();
        password.setBounds(400,60,150,20);
        add(password);

        JLabel logininas = new JLabel("Loggin In As");
        logininas.setBounds(300,100,100,20);
        add(logininas);

        loginin = new Choice();
        loginin.add("Admin");
        loginin.add("Customer");
        loginin.setBounds(400,100,150,20);
        add(loginin);
         
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("iconss/logins.jpg"));
        Image i2 = i1.getImage().getScaledInstance(20, 16, Image.SCALE_DEFAULT);

        login = new JButton("LOGIN", new ImageIcon(i2));
        login.setBounds(330,160,100,30);
        login.addActionListener(this);
        add(login);

        ImageIcon i3= new ImageIcon(ClassLoader.getSystemResource("iconss/cancel.png"));
        Image i4 = i3.getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT);
        cancel = new JButton("CANCEL", new ImageIcon(i4));
        cancel.setBounds(450,160,100,30);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i5= new ImageIcon(ClassLoader.getSystemResource("iconss/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT);
        signup = new JButton("SIGN UP",new ImageIcon(i6));
        signup.setBounds(380,200,100,30);
        signup.addActionListener(this);
        add(signup);

        ImageIcon i7= new ImageIcon(ClassLoader.getSystemResource("iconss/second.png"));
        Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(0,0,250,250);
        add(image);


        setSize(640,300);
        setLocation(400,200);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==login){
            String susername= username.getText();
            String spassword = password.getText();
            String user = loginin.getSelectedItem();

            try{
                Conn c= new Conn();
                String query = "select * from login where username = '"+susername+"' and password = '"+spassword+"' and user = '"+user+"' ";

                ResultSet rs = c.s.executeQuery(query);

                if(rs.next()){
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Project(user, meter);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    username.setText("");
                    password.setText("");
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==cancel){
            setVisible(false);

        }else if(ae.getSource()==signup){
            setVisible(false);
            new Signup();
        }
    }
    
    public static void main(String[] args){
        new login();
    }
}
