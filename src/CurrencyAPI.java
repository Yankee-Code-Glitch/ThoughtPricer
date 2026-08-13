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
            String body = response.body();

            int sourceCurrencyStartIndex = body.indexOf("\"" + foodContext.sourceCurrency() + "\":");
            if (sourceCurrencyStartIndex == -1) {
                throw new IllegalArgumentException(Main.messages.getString("error.api.unsupported.currency"));
            }
            int sourceCurrencyEndIndex = body.indexOf(",", sourceCurrencyStartIndex);
            if (sourceCurrencyEndIndex == -1) {
                sourceCurrencyEndIndex = body.indexOf("}", sourceCurrencyStartIndex);
            }

            int targetCurrencyStartIndex = body.indexOf("\"" + foodContext.targetCurrency() + "\":");
            if (targetCurrencyStartIndex == -1) {
                throw new IllegalArgumentException(Main.messages.getString("error.api.unsupported.currency"));
            }
            int targetCurrencyEndIndex = body.indexOf(",", targetCurrencyStartIndex);
            if (targetCurrencyEndIndex == -1) {
                targetCurrencyEndIndex = body.indexOf("}", targetCurrencyStartIndex);
            }

            String sourceRate = body.substring(sourceCurrencyStartIndex + foodContext.sourceCurrency().length() + 3, sourceCurrencyEndIndex).trim();
            String targetRate = body.substring(targetCurrencyStartIndex + foodContext.targetCurrency().length() + 3, targetCurrencyEndIndex).trim();

            return Double.parseDouble(targetRate) / Double.parseDouble(sourceRate);

        } catch (Exception e) {
            if (foodContext.shouldConvert()) {
                IO.println(e.getMessage());
                IO.println(String.format(Main.messages.getString("error.api.unreachable"), foodContext.targetCurrency()));
            }
            return CONVERSION_RATE_BACKUP;
        }
    }
}