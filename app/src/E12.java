import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;

public class E12 {
    public static void main(String[] args) {
        System.out.println("GET:");
        get();

        System.out.println("GET ISBN:");
        String isbn = getISBN();
        System.out.println(isbn);

        System.out.println("POST:");
        int id = post(isbn, "book", 20.0, 33);

        System.out.println("PUT:");
        put(id, isbn, "book", 30.0, 21);

        System.out.println("DELETE:");
        delete(id);

    }

    public static void get() {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items");

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

    public static String getISBN() {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/randomisbn");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println(conn.getResponseCode());
            return response.toString();

        } catch (Exception e) {
            System.out.println("Erro ao gerar ISBN: " + e.getMessage());
            return null;
        }
    }

    public static int post(String isbn13, String type, double price, int numberInStock) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Monta o JSON dinamicamente usando os parâmetros
            String json = "{\n" +
                    "    \"isbn13\": \"" + isbn13 + "\",\n" +
                    "    \"type\": \"" + type + "\",\n" +
                    "    \"price\": " + price + ",\n" +
                    "    \"numberinstock\": " + numberInStock + "\n" +
                    "}";

            try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
                out.writeBytes(json);
                out.flush();
            }

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

            System.out.println(conn.getResponseCode());
            System.out.println(response.toString());

            return extractIdFromResponseSimple(response.toString());

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return 0;
        }
    }

    public static void put(int id, String isbn13, String type, double price, int numberInStock) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Monta o JSON dinamicamente usando os parâmetros
            String json = "{\n" +
                    "    \"isbn13\": \"" + isbn13 + "\",\n" +
                    "    \"type\": \"" + type + "\",\n" +
                    "    \"price\": " + price + ",\n" +
                    "    \"numberinstock\": " + numberInStock + "\n" +
                    "}";

            try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
                out.writeBytes(json);
                out.flush();
            }
            
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

    private static int extractIdFromResponseSimple(String jsonResponse) {
        try {
            // Exemplo da resposta: {"id":31,"type":"book",...}
            String[] parts = jsonResponse.split("\"id\":");
            if (parts.length > 1) {
                String part = parts[1];
                // Pega tudo até a primeira vírgula ou chave
                part = part.split("[,}]")[0].trim();
                return Integer.parseInt(part);
            }
        } catch (Exception e) {
            System.out.println("Erro ao extrair id: " + e.getMessage());
        }
        return -1; // se der erro ou não achar
    }

    public static void delete(int id) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items/"+id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("Status" + conn.getResponseCode());
            System.out.println(response.toString());

        } catch (Exception e) {
            System.out.println("Erro delete: " + e);
        }
    }
}
