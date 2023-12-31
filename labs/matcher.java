import java.util.regex.*;

public class matcher {
    public static void main(String[] args) {
        String text = "The price of the product is $19.99.";
        findAndPrintNumbers(text);

        String password = "Abcd1234";
        if (isPasswordValid(password)) {
            System.out.println("Valid password!");
        } else {
            System.out.println("Invalid password!");
        }

        String text1 = "Our website at https://www.example.com";
        System.out.println(replaceLinks(text1));

        String ipAddress = "192.168.25.1";
        if (isIPAddressValid(ipAddress)) {
            System.out.println("Valid IP address!");
        } else {
            System.out.println("Invalid IP address!");
        }

        String text2 = "The quick brown fox jumps over the lazy dog. ";
        findWords(text2, 'b');
    }

    public static void findAndPrintNumbers(String text) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    public static boolean isPasswordValid(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$";

        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public static String replaceLinks(String text) {
        String linkRegex = "\\b(?:https?|ftp)://\\S+\\b";

        Pattern pattern = Pattern.compile(linkRegex);
        Matcher matcher = pattern.matcher(text);

        String replacedText = matcher.replaceAll("<a href=\"$0\">$0</a>");

        return replacedText;
    }

    public static boolean isIPAddressValid(String ipAddress) {
        String ipRegex = "^(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9])\\." +
                "(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9])\\." +
                "(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9])\\." +
                "(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9])$";

        Pattern pattern = Pattern.compile(ipRegex);
        Matcher matcher = pattern.matcher(ipAddress);

        return matcher.matches();
    }


    public static void findWords(String text, char startingLetter) {
        String wordRegex = "\\b(?i)" + startingLetter + "\\w*\\b";

        Pattern pattern = Pattern.compile(wordRegex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Words starting with '" + startingLetter + "':");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
