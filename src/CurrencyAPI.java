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

            int sourceCurrencyStartIndex = response.body().indexOf("\"" + foodContext.sourceCurrency() + "\":");
            int sourceCurrencyEndIndex = response.body().indexOf(",", sourceCurrencyStartIndex);

            if (sourceCurrencyStartIndex == -1) {
                throw new IllegalArgumentException("This currency is not supported.");
            }

            if (sourceCurrencyEndIndex == -1) {
                sourceCurrencyEndIndex = response.body().indexOf("}");
            }

            //"AAA:" is 6 digits long, so we add 6 to skip ahead of where the currency code is
            int targetCurrencyStartIndex = response.body().indexOf("\"" + foodContext.targetCurrency() + "\":");
            int targetCurrencyEndIndex = response.body().indexOf(",", targetCurrencyStartIndex);

            if (targetCurrencyStartIndex == -1) {
                throw new IllegalArgumentException("This currency is not supported.");
            }

            if (targetCurrencyEndIndex == -1) {
                targetCurrencyEndIndex = response.body().indexOf("}");
            }

            String sourceCurrency = response.body().substring(sourceCurrencyStartIndex + foodContext.sourceCurrency().length() + 3, sourceCurrencyEndIndex).trim();
            String targetCurrency = response.body().substring(targetCurrencyStartIndex + foodContext.targetCurrency().length() + 3, targetCurrencyEndIndex).trim();
            return Double.parseDouble(targetCurrency) / Double.parseDouble(sourceCurrency);

        } catch (Exception e) {
            if (foodContext.choice()) {
                IO.println(e.getMessage());
                IO.println("Could not reach currency API. The conversion to " + foodContext.targetCurrency() + " will not happen.\n");
            }
            return CONVERSION_RATE_BACKUP;
        }
    }
}
