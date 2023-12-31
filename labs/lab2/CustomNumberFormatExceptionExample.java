package lab3;

class CustomNumberFormatException extends NumberFormatException {
    public CustomNumberFormatException(String message) {
        super(message);
    }
}

public class CustomNumberFormatExceptionExample {
    public static void main(String[] args) {
        String str = "12";

        try {
            int number = parseIntWithCustomException(str);
            System.out.println("Число: " + number);
        } catch (CustomNumberFormatException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    public static int parseIntWithCustomException(String str) throws CustomNumberFormatException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new CustomNumberFormatException("Элемент "  + str + " не является числом: ");
        }
    }
}

