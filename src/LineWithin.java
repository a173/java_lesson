import java.util.Scanner;

public class LineWithin {
    static int exec() {
        Scanner in = new Scanner(System.in);
        int w;
        int h;
        int b1;
        int del;
        int len;
        String text;

        try {
            System.out.print("Введи длину: ");
            w = Integer.parseInt(in.nextLine());
            System.out.print("Введи высоту: ");
            h = Integer.parseInt(in.nextLine());
            System.out.print("Введи текст: ");
            text = in.nextLine();
        }
        catch (Exception e) {
            System.out.println("Ты где-то ошибся");
            return (exec());
        }
        if ((len = text.length()) == 0 || len > w - 2 || (w - len) % 2 != 0  || h < 3) {
            System.out.println("Ты что-то где-то не рассчитал)");
            return (exec());
        }
        del = w / 2;
        b1 = -1;
        while (++b1 < h) {
            int b2 = -1;
            if (b1 == 0 || b1 == h - 1) {
                while (++b2 < w)
                    System.out.print("*");
            } else {
                if (b1 == del)
                    while (++b2 < w)
                        if (b2 < (w - len) / 2 || b2 >= (w - len) / 2 + len)
                            System.out.print(b2 == 0 || b2 == w - 1 ? "*" : " ");
                        else {
                            System.out.print(text);
                            b2 += len - 1;
                        }
                while (++b2 < w) {
                    System.out.print(b2 == 0 || b2 == w - 1 ? "*" : " ");
                }
            }
            System.out.println();
        }
        return (1);
    }
}
