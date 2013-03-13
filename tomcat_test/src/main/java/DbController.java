

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DbController {
    private DataSource ds;
    private int id = 0;
    private Connection conn;
    private Statement statement;
    private String tName;

    private Calendar calendar = GregorianCalendar.getInstance();

    public DbController(DataSource ds) throws SQLException {
        this.ds = ds;
        conn = ds.getConnection();
        statement = conn.createStatement();
    }

    public void createTable(String tName) throws SQLException {
        this.tName = tName;
        statement.execute("CREATE TABLE " + tName + "(id INTEGER PRIMARY KEY AUTO_INCREMENT, postDate DATE, nickName VARCHAR(10), message TEXT)");
    }

    public void setTableName(String tName) {
        this.tName = tName;
    }

    public void addRecord(String nickName, String message) {
        try {
            statement.execute("INSERT INTO " + tName + "(postDate, nickName, message) VALUES('" + new Date(calendar.getTime().getTime()) + "','" + nickName + "','" + message + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRecord(String message) {
        try {
            statement.execute("INSERT INTO " + tName + "(postDate, nickName, message) VALUES('" + new Date(calendar.getTime().getTime()) + "','Anonimous','" + message + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Record> getRecords() throws SQLException {
        ResultSet rs;
        rs = statement.executeQuery("SELECT ID, postDate, nickName, message FROM " + tName);
        List<Record> records = new ArrayList<>();
        while (rs.next()) {
            Record rec = new Record();
            rec.setId(rs.getInt("ID"));
            rec.setPostDate(rs.getDate("postDate"));
            rec.setNickName(rs.getString("nickName"));
            rec.setMessage(rs.getString("message"));
            records.add(rec);

        }
        rs.close();


        return records;
    }

    public void close() throws SQLException {
        statement.close();
        conn.close();
    }
}
