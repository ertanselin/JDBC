import java.sql.*;

public class ExecuteUpdate01 {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/selin", "postgres", "selin1994");
        Statement st = con.createStatement();

        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

    String sql1 = "UPDATE companies \n" +
            "SET number_of_employees = 16000\n" +
            "WHERE number_of_employees < (SELECT AVG(number_of_employees) \n" +
            "                             FROM companies);";

    int updatedRows = st.executeUpdate(sql1);
    System.out.println("updatedRows = " + updatedRows );

    String sql2 = "SELECT * FROM companies";
    ResultSet resultSet = st.executeQuery(sql2);

    while (resultSet.next()) {
        System.out.println(resultSet.getInt("company_id")+"--"+resultSet.getString("company")+"--"+resultSet.getInt("number_of_employees"));

    }

    con.close();
    st.close();
    }
}
