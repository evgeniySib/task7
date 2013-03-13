import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SimpleServlet", urlPatterns = {"/simple"})


public class Hello extends HttpServlet {
    @Resource(name = "jdbc/testDS")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        DbController dbconn = null;
        try {
            dbconn = new DbController(ds);
            dbconn.createTable("Guestbook");

        } catch (SQLException e) {
        }

        List<Record> msg = new ArrayList<>();
        try {
            msg = dbconn.getRecords();
            req.setAttribute("guestMsg", msg);
        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
        }
        try {
            dbconn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        req.getRequestDispatcher("WEB-INF/test.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        DbController dbconn = null;

        List<Record> msg = new ArrayList<>();
        try {
            dbconn = new DbController(ds);
            dbconn.setTableName("Guestbook");

        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
        }
        if (dbconn != null) {
            if ((req.getParameter("name") == "")) {
                dbconn.addRecord(req.getParameter("message"));
            } else {
                dbconn.addRecord(req.getParameter("name"), req.getParameter("message"));
            }
        }
        try {
            msg = dbconn.getRecords();
            dbconn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("guestMsg", msg);
        req.getRequestDispatcher("/WEB-INF/test.jsp").forward(req, resp);
    }
}