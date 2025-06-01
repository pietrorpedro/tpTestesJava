import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class E9 {
        public static void main(String[] args) throws MalformedURLException {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities/9");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            BufferedReader reader;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }
            reader.close();

            System.out.println("Status: " + conn.getResponseCode());
            System.out.println(response.toString());

            // get

            URL urlGet = new URL("https://apichallenges.eviltester.com/sim/entities/9");

            HttpURLConnection connGet = (HttpURLConnection) urlGet.openConnection();
            connGet.setRequestMethod("GET");

            BufferedReader readerGet;
            if (connGet.getResponseCode() >= 200 && connGet.getResponseCode() < 300) {
                readerGet = new BufferedReader(new InputStreamReader(connGet.getInputStream()));
            } else {
                readerGet = new BufferedReader(new InputStreamReader(connGet.getErrorStream()));
            }

            String lineGet;
            StringBuilder responseGet = new StringBuilder();
            while ((lineGet = readerGet.readLine()) != null) {
                responseGet.append(lineGet).append("\n");
            }
            readerGet.close();

            System.out.println("Status: " + connGet.getResponseCode());
            System.out.println(responseGet.toString());

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
