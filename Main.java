import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board b = new Board();
        System.out.println("Please input 'easy' or 'normal' or 'hard'");
        b.chooseDifficulty(in.next());
        b.generateBoard(in.nextInt(), in.nextInt());

    }
}
