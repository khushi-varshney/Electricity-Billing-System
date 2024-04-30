package electricity;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    JButton create, back;
    Choice accountType;
    JTextField meter, username, name, password;
    Signup() {

        setBounds(450, 150, 700, 400);
        getContentPane().setBackground(Color.YELLOW);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(176, 218, 230), 2), "Create Account",
                TitledBorder.LEADING, TitledBorder.TOP, null, Color.PINK));
        add(panel);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        setVisible(true);

        JLabel heading = new JLabel("Create Account As");
        heading.setBounds(100, 50, 140, 20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(heading);

        accountType = new Choice();
        accountType.add("ADMIN");
        accountType.add("CUSTOMER");
        accountType.setBounds(260, 50, 150, 20);
        panel.add(accountType);

        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100, 90, 140, 20);
        lblmeter.setForeground(Color.GRAY);
        lblmeter.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);

        meter = new JTextField();
        meter.setBounds(260, 90, 150, 20);
        meter.setVisible(false);
        panel.add(meter);

        JLabel lblusername = new JLabel("User-Name");
        lblusername.setBounds(100, 130, 140, 20);
        lblusername.setForeground(Color.GRAY);
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblusername);

        username = new JTextField();
        username.setBounds(260, 130, 150, 20);
        panel.add(username);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 170, 140, 20);
        lblname.setForeground(Color.GRAY);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblname);

        name = new JTextField();
        name.setBounds(260, 170, 150, 20);
        panel.add(name);

        
        meter.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe){
                
            }
            @Override
            public void focusLost(FocusEvent fe){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no='"+meter.getText()+"'");
                    while(rs.next()){
                        name.setText(rs.getString("name"));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100, 210, 140, 20);
        lblpassword.setForeground(Color.GRAY);
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblpassword);

        password = new JTextField();
        password.setBounds(260, 210, 150, 20);
        panel.add(password);

        accountType.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ae){
                String user = accountType.getSelectedItem();
                if(user.equals("CUSTOMER")){
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                }else{
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });

        create = new JButton("Create");
        create.setBackground(Color.GREEN);
        create.setForeground(Color.RED);
        create.addActionListener(this);
        create.setBounds(140, 260, 120, 30);
        panel.add(create);

        back = new JButton("Back");
        back.setBackground(Color.GREEN);
        back.setForeground(Color.RED);
        back.addActionListener(this);
        back.setBounds(300, 260, 120, 30);
        panel.add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("./iconss/signupimage.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(415, 30, 200, 200);
        panel.add(image);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == create) {
            String atype = accountType.getSelectedItem();
            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            String smeter = meter.getText();

            try{
                Conn c = new Conn();
                String query = null;
                if(atype.equals("ADMIN")){
                      query = "insert into login values('"+smeter+"', '"+susername+"', '"+sname+"', '"+spassword+"', '"+atype+"')";
                } else{
                    query = "update login set username = '"+susername+"', password ='"+spassword+"', user = '"+atype+"' where meter_no ='"+smeter+"'";
                }
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new login();
            }catch(Exception e){
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
