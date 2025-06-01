import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class E1 {
    public static void main(String[] args) throws MalformedURLException {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }
            reader.close();
            
            System.out.println("Status: " + conn.getResponseCode());
            System.out.println(response.toString());

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
