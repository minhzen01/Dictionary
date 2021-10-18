package Dictionary;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Scanner;

public class DictionaryManagement {

    public Dictionary dic = new Dictionary();
    Scanner sc = new Scanner(System.in);

    public Dictionary getDic() {
        return dic;
    }

    public Word insertNewWord(){
        Word Word = new Word();
        System.out.print("Nhập từ: ");
        Word.setWord_target(sc.nextLine());
        System.out.print("   Nhập nghĩa: ");
        Word.setWord_explain(sc.nextLine());
        return Word;
    }

    public void insertFromCommandline(){
        System.out.print("Số lượng từ muốn nhập thêm: ");
        int sizeAdd = Integer.parseInt(sc.nextLine());
        for (int i = 1; i < sizeAdd + 1; i++) {
            System.out.printf("%d. ", i);
            dic.getListWord().add(insertNewWord());
        }
    }

    public void insertFromFile() throws FileNotFoundException {
        Word newWord;
        File file = new File("src/Dictionary/dictionaries.txt");
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            newWord = new Word(line);
            dic.addWord(newWord);
        }
    }

    public int dictionaryLookup() {
        System.out.println("Nhập từ cần tìm: ");
        String wordSeach = sc.nextLine();

        int check = Collections.binarySearch(dic.getListWord(), new Word(wordSeach, null));
        if (check >= 0) {
            System.out.println("Nghĩa: " + dic.getListWord().get(check).getWord_explain());
        } else {
            System.out.println("Từ trên không có trong từ điển!");
            check = -1;
        }
        return check;
    }

    public int dictionaryLookup(String en) {
        return Collections.binarySearch(dic.getListWord(), new Word(en, null));
    }

    public void addWord() {
        System.out.print("Nhập từ muốn thêm : ");
        String engWord = sc.nextLine();
        System.out.print("Nhập nghĩa : ");
        String vieWord = sc.nextLine();
        dic.addWord(new Word(engWord, vieWord));
        dic.sortDic();
    }

    /**
     * add có tham số.
     * @param engWord target
     * @param vieWord explane
     */
    public void addWord(String engWord, String vieWord) {
        dic.addWord(new Word(engWord, vieWord));
        dic.sortDic();
    }

    public Dictionary editWord() {
        System.out.println("Nhập từ cẩn chỉnh sửa : ");
        String editWord = sc.nextLine();
        for (int i = 0; i < dic.getListWord().size(); i++) {
            if (editWord.equals(dic.getListWord().get(i).getWord_target())) {
                System.out.println("Sửa thành: ");
                dic.getListWord().get(i).setWord_target(sc.nextLine());
                System.out.println("Nghĩa: ");
                dic.getListWord().get(i).setWord_explain(sc.nextLine());
            }
        }
        return dic;
    }

    public void removeWord() {
        System.out.print("Nhập từ cần xóa: ");
        String engWord = sc.nextLine();
        boolean check = false;
        for (Word i: dic.getListWord()) {
            if (i.getWord_target().equalsIgnoreCase(engWord)) {
                dic.getListWord().remove(i);
                System.out.println("Đã xóa thành công!");
                check = true;
            }
        }
        if (!check) {
            System.out.println("Không có từ này trong từ điển!");
        }
    }

    /**
     * remove có tham số.
     * @param engWord
     */
    public void removeWord(String engWord) {
        for (int i = 0; i< dic.getListWord().size(); i++) {
            if (dic.getListWord().get(i).getWord_target().equalsIgnoreCase(engWord)) {
                String target = dic.getListWord().get(i).getWord_target();
                String explane = dic.getListWord().get(i).getWord_explain();
                dic.removeWord(target, explane);
            }
        }
    }

    public void dictionaryExportToFile() {
        try {
            File file = new File("E:\\Project JAVA\\TestTuDien1\\src\\Dictionary\\dicForWrite.txt");
            FileWriter fw = new FileWriter(file);

            for (int i = 0; i < dic.getListWord().size(); i++) {
                String line = dic.getListWord().get(i).getWord_explain() + " : " + dic.getListWord().get(i).getWord_target() + "\n";
                fw.write(line);
            }
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    //    public void insertFromFile(String markUp) throws FileNotFoundException {
//        String line;
//        File myObj = null;
//            if (markUp.equals("markUp")){
//                myObj = new File("E:\\Project JAVA\\TestTuDien\\src\\Dictionary\\DictionaryMark.txt");
//
//            }
//            else if (markUp.equals("ViToEn")){
//                myObj = new File("E:\\Project JAVA\\TestTuDien\\src\\Dictionary\\dicForWrite.txt");
//            }
//        assert myObj != null;
//        Scanner myReader = new Scanner(myObj);
//        while (myReader.hasNextLine()) {
//            line = myReader.nextLine();
//            int pos = line.indexOf(':');
//            dic.addWord(new Word(line.substring(0,pos),line.substring(pos+1)));
//        }
//    }


//    public List<String> prefixSeach(String prefix) {
//        List<String> words = new ArrayList<>();
//        for (int i = 0; i < dic.getListWord().size(); i++) {
//            if (dic.getWord(i).getWord_target().equals(prefix)) {
//                words.add(dic.getWord(i).getWord_explain());
//            }
//        }
//        return words;
//    }
//
//    public String get(String selectedItem) {
//        for (int i = 0; i < dic.getListWord().size(); i++) {
//            if (dic.getWord(i).getWord_target().equals(selectedItem)) {
//                return dic.getWord(i).getWord_explain();
//            }
//        }
//        return null;
//    }
//
//    public String showSomeWords(String pre) {
//        StringBuilder show = new StringBuilder();
//        for (int i = 0; i < dic.getListWord().size(); i++) {
//            if (dic.getWord(i).getWord_target().indexOf(pre) == 0)
//                show.append(dic.getWord(i).getWord_target()).append(" : ").append(dic.getWord(i).getWord_explain()).append("\n\n");
//        }
//        return show.toString();
//    }
//
//    public String Search(String target){
//        String result = "";
//        for (int i = 0; i < dic.getListWord().size(); i++) {
//            if (dic.getWord(i).getWord_target().indexOf(target) == 0){
//                if (dic.getWord(i).getWord_target().substring(0,target.length()).equals(target)
//                    &&(dic.getWord(i).getWord_target().substring(target.length(),target.length() + 1).equals("")
//                    ||(dic.getWord(i).getWord_target().substring(target.length(),target.length() + 1).equals(","))
//                    ||(dic.getWord(i).getWord_target().substring(target.length(),target.length() + 1).equals(";"))
//                    ||(dic.getWord(i).getWord_target().startsWith(" ", target.length()))))
//                    result = dic.getWord(i).getWord_explain();
//            }
//        }
//        return result;
//    }
//
//    public String showAllWords() {
//        StringBuilder show = new StringBuilder();
//        for (int i = 0; i < dic.getListWord().size(); i++) {
//            show.append(dic.getWord(i).getWord_target()).append(" : ").append(dic.getWord(i).getWord_explain()).append("\n\n");
//        }
//        return show.toString();
//    }
//

}