import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class E11 {
    public static void main(String[] args) throws MalformedURLException {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("OPTIONS");

            System.out.println("Resposta: " + conn.getHeaderField("Allow"));
            System.out.println("Status: " + conn.getResponseCode());

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
