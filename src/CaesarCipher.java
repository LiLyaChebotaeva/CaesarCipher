import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CaesarCipher {

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                character = (char) ((character - base + shift) % 26 + base);
            }
            result.append(character);
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }

    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("n");
            }
        }
        return content.toString();
    }

    public static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    public static void main(String[] args) {
        String TextFilePath = "Text.txt";
        String TranslateFilePath = "Translate.txt";
        String InverseFilePath = "Inverse.txt";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Введите сдвиг для шифрования: ");
            int shift = Integer.parseInt(reader.readLine());

            String text = readFile(TextFilePath);

            String encryptedText = encrypt(text, shift);
            writeFile(TranslateFilePath, encryptedText);
            System.out.println("Текст зашифрован и записан в " + TranslateFilePath);

            String decryptedText = decrypt(encryptedText, shift);
            writeFile(InverseFilePath, decryptedText);
            System.out.println("Текст расшифрован и записан в " + InverseFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите целое число для сдвига.");
        }
    }
}


