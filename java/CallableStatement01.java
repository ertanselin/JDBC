import java.sql.*;

public class CallableStatement01 {
    /*
   Java'da methodlar return type sahibi olsa da, void olsa da method olarak adlandırılır.
   SQL'de ise data return ediyorsa "function" denir. Return yapmıyorsa "procedure" diye adlandırılır.
    */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/selin", "postgres", "selin1994");
        Statement st = con.createStatement();

        //1. Örnek: İki parametre ile çalışıp bu parametreleri toplayarak return yapan bir fonksiyon oluşturun.
        //1. Adım: Fonksiyon kodunu yazınız.
        String sql1 = "CREATE OR REPLACE FUNCTION toplama(x NUMERIC, y NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";
        //2. Adım: Fonksiyonu oluştur.
        st.execute(sql1);

        //3. Adım: Fonsiyonu çağır.
        CallableStatement cst01 = con.prepareCall("{ ? = call toplama(?, ?)}");

        //4. Adım: Return için registerOutParameter() methodunu kullanarak return data tipini belirt. Ardından parametrelerimi gir.
        cst01.registerOutParameter(1,Types.NUMERIC);
        cst01.setInt(2,6);
        cst01.setInt(3,-4);

        //5. Adım: CallableStatement objesini çalıştır.
        cst01.execute();

        //6. Adım: Sonucu çağırmak için uygun data tipinde get() methodunu kullan.
        System.out.println(cst01.getBigDecimal(1));

    }
}

// Hocam IDE'yi kapattığımızda yaptığımız çalışmalar silinir mi? Nasıl kaydedeceğiz?