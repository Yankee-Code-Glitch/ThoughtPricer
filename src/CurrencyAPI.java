import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyAPI {
    public static double getExchangeRate(){

        final int CONVERSION_RATE_BACKUP = 1500;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://open.er-api.com/v6/latest/USD"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //"ARS:" is 6 digits long, so we add 6 to skip ahead of where ARS is
            int startIndex = response.body().indexOf("\"ARS\":") + 6;
            int endIndex = response.body().indexOf(",", startIndex);

            String rateString = response.body().substring(startIndex, endIndex).trim();
            return  Double.parseDouble(rateString);

        } catch (Exception e) {
            IO.println(e.getMessage());
            IO.println("Could not reach currency API. Will be using default value of 1500 ARS/USD.");
            return CONVERSION_RATE_BACKUP;
        }
    }
}
