import java.util.Scanner;

public class GuessNumber {
    static int exec() {
        boolean num = false;
        int i = 0;
        short z = (short) (Math.random() * 11);
        Scanner scanner = new Scanner(System.in);
        short c;

        while (!num) {
            try {
                System.out.print("Введи число от 0 до 10: ");
                c = Short.parseShort(scanner.nextLine());
            }
            catch (Exception e) {
                System.out.println("Ну просил же, число))");
                continue ;
            }
            if (c == z) {
                num = true;
                System.out.println(i == 0 ? "Сильно! Мой маленький экстрасенс)"
                        : "Молодец! Но можно и быстрее)");
                System.out.println("Повторим? (yes/no)");
                String q = scanner.nextLine();
                if (q.equals("yes"))
                    return (exec());
            }
            else if (c < 0 || c > 10)
                System.out.println("ОТ 0 до 10!!!!!!1!!1111!");
            else if (c < z)
                System.out.println("Чуть-чуть по-больше, попробуй еще раз");
            else if (c > z)
                System.out.println("Чуть-чуть по-меньше, попробуй еще раз");
            i++;
        }
        return (1);
    }
}
