import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class XatBlogAPI {
    private static final String BASE_URL = "https://api.xatblog.net/";
    private static final Gson gson = new Gson();

    private Map<String, Object> sendRequest(String path) throws IOException {
        URL url = new URL(BASE_URL + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            return gson.fromJson(response.toString(), Map.class);
        } else {
            return Map.of("error", "Request failed with response code " + responseCode);
        }
    }

    public Map<String, Object> latest() throws IOException {
        return sendRequest("/latest");
    }

    public Map<String, Object> prices() throws IOException {
        return sendRequest("/prices");
    }

    public Map<String, Object> countdown() throws IOException {
        return sendRequest("/countdown");
    }

    public Map<String, Object> promoted() throws IOException {
        return sendRequest("/promoted");
    }

    public Map<String, Object> activePawns() throws IOException {
        return sendRequest("/activepawns");
    }

    public Map<String, Object> regToId(String username) throws IOException {
        String endpoint = "/reg2id/" + username;
        return sendRequest(endpoint);
    }

    public Map<String, Object> idToReg(String id) throws IOException {
        String endpoint = "/id2reg/" + id;
        return sendRequest(endpoint);
    }

    public Map<String, Object> namePrice(String username) throws IOException {
        String endpoint = "/nameprice/" + username;
        return sendRequest(endpoint);
    }

    public Map<String, Object> chatPrice(String chatname) throws IOException {
        String endpoint = "/chatprice/" + chatname;
        return sendRequest(endpoint);
    }

    public Map<String, Object> chatInfo(String chatname) throws IOException {
        String endpoint = "/chatinfo/" + chatname;
        return sendRequest(endpoint);
    }

    public Map<String, Object> powerSearch(String powername) throws IOException {
        String endpoint = "/powersearch/" + powername;
        return sendRequest(endpoint);
    }

    public Map<String, Object> powerInfo(String powername) throws IOException {
        String endpoint = "/powerinfo/" + powername;
        return sendRequest(endpoint);
    }

    public Map<String, Object> powerLogs(String powername) throws IOException {
        String endpoint = "/powerlogs/" + powername;
        return sendRequest(endpoint);
    }

    public Map<String, Object> promoPrice(int hours, String language) throws IOException {
        String endpoint = "/promoprice/" + hours + "/" + language;
        return sendRequest(endpoint);
    }

    public Map<String, Object> promoPrice(int hours) throws IOException {
        return promoPrice(hours, "en");
    }

    public Map<String, Object> daysToXats(int amount) throws IOException {
        String endpoint = "/dx/" + amount;
        return sendRequest(endpoint);
    }

    public Map<String, Object> xatsToDays(int amount) throws IOException {
        String endpoint = "/x2d/" + amount;
        return sendRequest(endpoint);
    }

    public Map<String, Object> verifyBanner(String url) throws IOException {
        String endpoint = "/verifybanner/" + url;
        return sendRequest(endpoint);
    }

    public Map<String, Object> userGifts(String userOrId) throws IOException {
        String endpoint = "/usergifts/" + userOrId;
        return sendRequest(endpoint);
    }

    public Map<String, Object> jinxList() throws IOException {
        return sendRequest("/jinxlist");
    }

    public Map<String, Object> jinxList(String powername) throws IOException {
        String endpoint = "/jinxlist/" + powername;
        return sendRequest(endpoint);
    }
}