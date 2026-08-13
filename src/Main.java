import java.util.ResourceBundle;
import java.util.Locale;

public class Main {
    public static ResourceBundle messages;

    public static void main(String[] args) {
        messages = ResourceBundle.getBundle("messages", Locale.US);

        ThoughtPricer.calculateSecondsPerCurrency();
    }
}