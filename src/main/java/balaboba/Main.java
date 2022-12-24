package balaboba;

import java.io.IOException;

public class Main {
    public static void main(String... args) throws IOException {
        String pathToFile = args[0]; // путь к файлу, где есть текст, который надо продолжить
        Balaboba balaboba = new Balaboba(pathToFile);
        balaboba.writeToFileContinuedText();
    }
}
