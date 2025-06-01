import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class E5 {
    public static void main(String[] args) throws MalformedURLException {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json = "{\"name\": \"aluno\"}";

            DataOutputStream data = new DataOutputStream(conn.getOutputStream());
            data.writeBytes(json);
            data.flush();
            data.close();

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

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
