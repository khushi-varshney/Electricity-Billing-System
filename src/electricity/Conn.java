package electricity;

import java.sql.*;

public class Conn {
    Statement s;
    Connection c;

    Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "W7301@jqir#");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
