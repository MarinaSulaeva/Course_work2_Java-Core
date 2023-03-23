package FunctionProgram;


import java.util.*;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        System.out.println("Введите текст");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        statisticsForText(s);
    }

    public static void statisticsForText(String s) {
        s = s.replaceAll("[^A-Za-zА-Яа-яЁё0-9]", " ");
        String[] text = s.split("\\s+");
        int t = text.length;
        System.out.println("В тексте " + t + " слов");
        System.out.println("Топ 10 слов");
        Map<String, Long> mapString = Arrays.stream(text)
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));
        mapString.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.<String, Long>comparingByKey()))
                .limit(10)
                .forEach(System.out::println);
    }

}
