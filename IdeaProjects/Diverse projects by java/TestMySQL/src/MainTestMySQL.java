import java.sql.*;


public class MainTestMySQL
{
    private static final String url = "jdbc:mysql://localhost:3306/productsdb";
    private static final String user = "root";
    private static final String password = "Nutrilite89lololo98";

    private static Connection con;

    public static void main(String args[]) throws Exception
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);

            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            st.addBatch("truncate table users");
            st.addBatch("Insert into users (Name, Age) Values ('Dima', 34)");
            st.addBatch("Insert into users (Name, Age) Values ('Max', 44)");
            st.addBatch("Insert into users (Name, Age) Values ('POP', 54)");
            st.executeBatch();

        }catch(SQLException e) {
            e.printStackTrace();
        }

    }
}
