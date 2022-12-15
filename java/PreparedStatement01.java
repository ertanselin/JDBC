import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/selin", "postgres", "selin1994");
        Statement st = con.createStatement(); // bunu kullanmadık. connection yaptık.


        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        // 1. Adım: Prepared statement query'si oluşturun.

        // String sql1 = "UPDATE companies SET number_of_employees = 9999 WHERE company = 'IBM';";
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?;";

        // 2. Adım: Prepared statement objesini oluşturun.
        PreparedStatement pst1 = con.prepareStatement(sql1); // statement oluşturmadık çünkü daha statementimiz hazır değil.

        // 3. Adım: set...() methodları ile soru işaretlerinin yerine değer atayın.
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");

        //4. Adım: Query'yi çalıştır.
        int guncellenenSatirSayisi = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet = st.executeQuery(sql2);

        while (resultSet.next()) {
            System.out.println(resultSet.getInt("company_id") + "--" + resultSet.getString("company") + "--" + resultSet.getInt("number_of_employees"));

            // Adım 3 'ü değiştirdikçe diğer satırları da güncelleyebiliriz.

            // GOOGLE için güncelleme

        }

            pst1.setInt(1, 5555);
            pst1.setString(2, "GOOGLE");

            int guncellenenSatirSayisiGoogle = pst1.executeUpdate();
            System.out.println("guncellenenSatirSayisiGoogle = " + guncellenenSatirSayisiGoogle);

            String sql3 = "SELECT * FROM companies";
            ResultSet resultSet2 = st.executeQuery(sql3);

            while (resultSet2.next()) {
                System.out.println(resultSet2.getInt("company_id") + "--" + resultSet2.getString("company") + "--" + resultSet2.getInt("number_of_employees"));

            }

        }

    }