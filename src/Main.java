import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String n;

        boolean c = false;

        do {
            System.out.println("Выбор задания: ");
            System.out.println("1: Выбор k-ого элемента из числовой последовательности 12345678910111213.....;");
            System.out.println("2: Заключить строку в рамки;");
            System.out.println("3: Угадай чило от 0 до 10;");
            System.out.println("0: Выход");
            n = in.nextLine();
            if (n.equals("1"))
                Sequence.exec();
            else if (n.equals("2"))
                LineWithin.exec();
            else if (n.equals("3"))
                GuessNumber.exec();
            else if (n.equals("0"))
                c = true;
            else
                System.out.println("Команда не найдена. Попробуй еще раз.");
            System.out.println("-----------------------");
        } while (!c);
    }
}
