import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/selin","postgres","selin1994");

        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();

        System.out.println("Connection Success");

        // 1. ÖRNEK: "workers" adında bir table oluşturup "worker_id, worker_name, worker_salary" sütunlarını ekleyin.
        boolean sql1 = st.execute("CREATE TABLE workers(worker_id VARCHAR(50), worker_name VARCHAR(2), worker_salary INT);");
        System.out.println(sql1); // False return eder. Çünkü data çağırma (SELECT) yapılmadı. Data çağrılabilirse 'True' yapar. Aksi halde 'False' return eder.

        // 2.ÖRNEK: Table'a worker_address sütunu ekleyin.
        String sql2= "ALTER TABLE workers ADD worker_address VARCHAR(80)";
        st.execute(sql2);

        // 3. ÖRNEK: workers tablosunu düşürünüz.
        String sql3 = "DROP TABLE workers";
        st.execute(sql3);

        // 4. ÖRNEK: Bağlantıyı kapat
        con.close();
        st.close();
    }
}

