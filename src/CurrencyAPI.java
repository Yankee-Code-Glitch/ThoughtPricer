import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyAPI {
    public static double getExchangeRate(FoodContext foodContext) {

        final int CONVERSION_RATE_BACKUP = 1;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://open.er-api.com/v6/latest/USD"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();

            int sourceCurrencyStartIndex = jsonResponse.indexOf("\"" + foodContext.sourceCurrency() + "\":");
            int sourceCurrencyEndIndex = jsonResponse.indexOf(",", sourceCurrencyStartIndex);

            if (sourceCurrencyStartIndex == -1) {
                throw new IllegalArgumentException("This currency is not supported.");
            }

            if (sourceCurrencyEndIndex == -1) {
                sourceCurrencyEndIndex = jsonResponse.indexOf("}");
            }

            int targetCurrencyStartIndex = jsonResponse.indexOf("\"" + foodContext.targetCurrency() + "\":");
            int targetCurrencyEndIndex = jsonResponse.indexOf(",", targetCurrencyStartIndex);

            if (targetCurrencyStartIndex == -1) {
                throw new IllegalArgumentException("This currency is not supported.");
            }

            if (targetCurrencyEndIndex == -1) {
                targetCurrencyEndIndex = jsonResponse.indexOf("}");
            }

            //Add 3 to the length to account for the quotation marks and colon
            String sourceCurrency = jsonResponse.substring(sourceCurrencyStartIndex + foodContext.sourceCurrency().length() + 3, sourceCurrencyEndIndex).trim();
            String targetCurrency = jsonResponse.substring(targetCurrencyStartIndex + foodContext.targetCurrency().length() + 3, targetCurrencyEndIndex).trim();
            return Double.parseDouble(targetCurrency) / Double.parseDouble(sourceCurrency);

        } catch (Exception e) {
            if (foodContext.shouldConvert()) {
                IO.println(e.getMessage());
                IO.println("Could not reach currency API. The conversion to " + foodContext.targetCurrency() + " will not happen.\n");
            }
            return CONVERSION_RATE_BACKUP;
        }
    }
}
