package electricity;

import java.sql.*;

public class Conn {
    Statement s;
    Connection c;

    Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "your_password");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
