import java.sql.*;

public class Execute02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/selin", "postgres", "selin1994");
        Statement st = con.createStatement();

        // Bunları bir method'a kaydedebiliriz. Mesela connectionPostgre diye class açarız. Çağırdığımızda yukarıdaki bağlama işlemleri otomatik olarak yapılır.

        String sql1 = "SELECT country_name, region_id FROM countries WHERE region_id = 1";
        boolean r1 = st.execute(sql1);
        System.out.println(r1); // table boş da olsa çağrılabiliyorsa 'True' return eder.

        // Çağırılan recordları(satırları) görmek için executeQuery() methodu kullanıyoruz.

        ResultSet resultSet1 = st.executeQuery(sql1);

        while (resultSet1.next()) {

            System.out.println(resultSet1.getString(1));
        }

        // 2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2 = "SELECT country_id, country_name FROM countries WHERE region_id > 2;";
        ResultSet resultSet2 = st.executeQuery(sql2);

        while (resultSet2.next()) {

            System.out.println(resultSet2.getString("country_id") + "--" + resultSet2.getString("country_name"));
        }

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.

        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies);";
        st.executeQuery(sql3);
        ResultSet resultSet3 = st.executeQuery(sql3);

        while (resultSet3.next()) {
            System.out.println(resultSet3.getInt("company_id") + "--" + resultSet3.getString("company") + "--" + resultSet3.getInt("number_of_employees"));

        }

        con.close();
        st.close();
    }

}