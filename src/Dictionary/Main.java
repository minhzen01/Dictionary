package Dictionary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static DictionaryCommandline Cml = new DictionaryCommandline();

    public static void menu() throws IOException {
        int choose;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("\nMenu:");
            System.out.println("1: Dictionary CommandLine sơ khai.");
            System.out.println("2: Dictionary CommandLine cải tiến.");
            System.out.println("3: Thoát");
            System.out.print("\nChọn chức năng: ");
            choose = Integer.parseInt(scan.nextLine());
            switch (choose) {
                case 1:
                    Cml.dictionaryBasic();
                    break;
                case 2:
                    Cml.dictionaryAdvanced();
                    break;
                case 3:
                    System.out.println("Good Bye!");
                    System.exit(0);
                default:
                    break;
            }
        } while (choose != 3);
    }


    public static void main(String[] args) throws IOException {
        System.out.println("------ Dictonary CommandLine From All In Group ------");
        menu();
//        DictionaryCommandline dic = new DictionaryCommandline();
//        dic.dictionarySearcher();
    }
}
