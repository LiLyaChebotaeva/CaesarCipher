import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CaesarCipher {

    // Метод для шифрования текста с помощью шифра Цезаря
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                // Шифруем символ
                character = (char) ((character + shift - base) % 26 + base);
            }
            result.append(character);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String inputFilePath = "Text.txt";  // Путь к входному файлу
        String outputFilePath = "Translate.txt"; // Путь к выходному файлу
        int shift = 3; // Сдвиг для шифра Цезаря

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String encryptedLine = encrypt(line, shift);
                writer.write(encryptedLine);
                writer.newLine();
            }

            System.out.println("Шифрование завершено. Результ в " + outputFilePath);

        } catch (IOException e) {
            System.err.println("Ошибка с файлом: " + e.getMessage());
        }
    }
}

