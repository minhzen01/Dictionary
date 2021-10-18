package Dictionary;

public class Word implements Comparable<Word> {
    private String word_target;
    private String word_explain;

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word() {
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public Word(String lineInFile) {
        String[] arr = lineInFile.split("\\t");
        if (arr.length > 1) {
            this.word_target = arr[0];
            this.word_explain = arr[1];
        } else if (arr.length == 1) {
            word_target = arr[0];
        } else {
            word_target = "";
            word_explain = "";
        }
    }


    @Override
    public int compareTo(Word word) {
        return this.word_target.compareToIgnoreCase(word.word_target);
    }
}