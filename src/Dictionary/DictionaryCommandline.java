package Dictionary;

import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline {

    DictionaryManagement manage = new DictionaryManagement();
    Scanner sc = new Scanner(System.in);

    public DictionaryManagement getManage() {
        return manage;
    }

    public void showAllWords() {
        if (manage.getDic().getListWord().size() == 0) {
            System.out.println("Không có từ nào trong từ điển!");
        } else {
            System.out.printf("%-6s%c %-20s%c %-20s%n", "No", '|', "English", '|', "Vietnamese");
            for (int i = 0; i < manage.getDic().getListWord().size(); i++) {
                System.out.printf("%-6d%c %-20s%c %-20s%n", i + 1, '|', manage.getDic().getWord(i).getWord_target(), '|', manage.getDic().getWord(i).getWord_explain());
            }
        }
    }

    public void dictionaryBasic() throws IOException {
        manage.insertFromFile();
        int choose;
        do {
            System.out.println("\n---- Dictionary Command Line Sơ Khai ----\n");
            System.out.println("1: Thêm từ vào từ điển");
            System.out.println("2: Hiển thị danh sách các từ trong từ điển");
            System.out.println("3: Quay lại menu");
            System.out.print("\nChọn chức năng: ");
            choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    manage.insertFromCommandline();
                    break;
                case 2:
                    this.showAllWords();
                    break;
                case 3:
                    Main.menu();
                    break;
                default:
                    break;
            }
        } while (choose != 3);

    }

    public void dictionaryAdvanced() throws IOException {
        manage.insertFromFile();
        //manage.dictionaryExportToFile();
        int choose;
        do {
            System.out.println("------ Dictionary CommandLine Cải Tiến ------");
            System.out.println("1: Tìm kiếm từ");
            System.out.println("2: Hiển thị danh sách dữ liệu của từ điển");
            System.out.println("3: Thêm từ");
            System.out.println("4: Xóa từ");
            System.out.println("5: Sửa từ");
            System.out.println("6: Search");
            System.out.println("7: Quay lại menu");
            System.out.print("\nChọn chức năng: ");
            choose = Integer.parseInt(sc.nextLine());

            switch (choose) {
                case 1:
                    manage.dictionaryLookup();
                    break;
                case 2:
                    showAllWords();
                    break;
                case 3:
                    manage.addWord();
                    break;
                case 4:
                    manage.removeWord();
                    break;
                case 5:
                    manage.editWord();
                    break;
                case 6:
                    dictionarySearcher();
                    break;
                case 7:
                    Main.menu();
                    break;
                default:
                    break;
            }
        } while (choose != 7);
    }

    public void dictionarySearcher() {
        System.out.print("Nhập từ bạn muốn tìm kiếm: ");
        String s = sc.nextLine();
        System.out.printf("%c %-20s%c %-20s%n", '|' ,"English", '|', "Vietnamese");
        manage.getDic().getListWord().forEach((i) -> {
            int index = i.getWord_target().indexOf(s);
            if (index == 0) {
                System.out.printf("%c %-20s%c %-20s%n", '|', i.getWord_target(), '|', i.getWord_explain());
            }
        });
    }

}


