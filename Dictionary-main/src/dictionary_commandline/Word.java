package dictionary_commandline;

public class Word {

    private String word_target; //tu moi
    private String word_explain; //giai nghia

    /** Tu. */
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word () {

    }

    /** them giai nghia cho tu moi */
    public void setWord_explain(String word_explain) {
        if(word_explain.equals("")) {
            return;
        }
        this.word_explain = word_explain;
    }

    /** Them tu moi */
    public void setWord_target(String word_target) {
        if (word_target.equals("")) {
            return;
        }
        this.word_target = word_target;
    }

    /** Lay giai nghia cua tu */
    public String getWord_explain() {
        return word_explain;
    }

    /** Lay tu */
    public String getWord_target() {
        return word_target;
    }
}