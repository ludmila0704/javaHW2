package seminar2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/*1. Напишите метод, который принимает на вход строку (String)
и определяет является ли строка палиндромом (возвращает boolean значение).
 2. Напишите метод, который составит строку, состоящую из 100 повторений
 слова TEST и метод, который запишет эту строку в простой текстовый файл, обработайте исключения.
 */
public class hw2 {

    static Logger logger = Logger.getLogger("Lesson2");

    public static void main(String[] args) {
        System.out.println("Решение первой задачи:");
        String strPal = inputString();

        if (strPal.isBlank()) {System.out.println("Вы ввели пустую строку. Попробуйте заново.");}
        else {System.out.println("Это палиндром? " + isPalindrom(strPal));}

        System.out.println("Решение второй задачи:");
        String strTest = getStrTest();

        if (strTest.isBlank()) {errorBlank();}
        else {writeToFile(strTest);}

    }

    private static boolean isPalindrom(String str) {

        str = str.trim().toLowerCase().replaceAll("\\s", "").replaceAll("\\,","");
        //System.out.println(str);

        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {

                return false;
            }
        }
        return true;
    }

    private static void errorBlank(){

        try {
            throw new RuntimeException("Строка пустая");

        } catch (RuntimeException e) {
            logger.severe(e.getMessage());
        }
    }
    private static String inputString() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.printf("Введите строку для определения палиндрома:  ");
        String inputStr = scanner1.nextLine();
        scanner1.close();
        return inputStr;
    }

    public static String getStrTest() {
        StringBuilder sb = new StringBuilder();
        int count = 100;
        for (int i = 0; i < count; i++) {
            sb.append("TEST");
        }
        return sb.toString();
    }

    public static void writeToFile(String str) {
        File file = new File("src/main/resources/files/text.txt");
        logger.setLevel(Level.ALL);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        //path=("src/main/resources/files/text.txt");
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(str);
            pw.close();
        } catch (FileNotFoundException e) {
            logger.severe("File not found" + e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            System.out.println("File is recorded in " + file);
        }
    }
}

