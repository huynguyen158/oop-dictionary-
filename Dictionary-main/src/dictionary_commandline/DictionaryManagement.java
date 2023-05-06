package dictionary_commandline;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    Scanner scan = new Scanner(System.in);
    Dictionary dic = new Dictionary();
    public final String path = "src\\dictionary_commandline\\dic.txt";

    /**
     * Hàm nhập từ.
     */
    private Word insertWord() {
        Word newWord = new Word();
        System.out.print("Nhập từ: ");
        newWord.setWord_target(scan.nextLine());
        System.out.print("Nhập nghĩa: ");
        newWord.setWord_explain(scan.nextLine());
        return newWord;
    }

    /**
     * Hàm nhập từ ở giao diện commandline.
     */
    public void insertFromCommandLine() {
        System.out.println("Nhập từ bàn phím, nhập số lượng từ: ");
        int sizeAdd = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < sizeAdd; i++) {
            System.out.printf("%d.\n", i + 1);
            dic.words.add(insertWord());
        }
    }

    /**
     * Hàm tra từ ở giao diện commandline.
     */
    public void dictionaryLookup() {
        String a;
        System.out.print("\nNhập từ cần tra: ");
        a = scan.nextLine();
        boolean check = false;
        for (int i = 0; i < dic.words.size(); i++) {
            if (dic.words.get(i).getWord_target().equals(a)) {
                System.out.println(a + "    |   " + dic.words.get(i).getWord_explain());
                check = true;
            }
        }
        if (!check) {
            System.out.println("Không tim thấy từ này!");
        }
    }

    /**
     * Hàm thêm từ ở giao diện commandline.
     */
    public void addWord() {
        System.out.print("Nhập từ muốn thêm: ");
        String newWord = scan.nextLine();
        System.out.print("Nhập nghĩa của từ: ");
        String meanNw = scan.nextLine();
        Word newWords = new Word(newWord, meanNw);

        boolean check = true;
        for (Word i : dic.words) {
            if (i.getWord_target().equals(newWord)) {
                check = false;
                System.out.println("Từ này đã có!");
                break;
            }
        }
        if (check) {
            dic.words.add(newWords);
            System.out.println("Thêm từ thành công!");
        }
    }

    /**
     * Hàm sửa từ ở giao diện commandline.
     */
    public void editWord() {
        System.out.println("Nhập từ muốn thay đổi: ");
        String word_e = scan.nextLine();
        boolean check = false;
        for (Word i : dic.words) {
            if (i.getWord_target().equals(word_e)) {
                System.out.print("Sửa từ thành: ");
                String target = scan.nextLine();
                i.setWord_target(target);
                System.out.print("Thay đổi nghĩa của từ thành: ");
                String explain = scan.nextLine();
                i.setWord_explain(explain);
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("Không tìm thấy từ cần thay đổi!");
        }
    }

    /**
     * Hàm xóa từ ở giao diện commandline.
     */
    public void removeWord() {
        System.out.print("Nhập từ cần xóa: ");
        String word_e = scan.nextLine();
        boolean check = false;
        for (Word i : dic.words) {
            if (i.getWord_target().equals(word_e)) {
                dic.words.remove(i);
                check = true;
                System.out.println("Xóa từ thành công!");
                break;
            }
        }
        if (!check) {
            System.out.println("Không có từ cần xóa trong từ điển!");
        }
    }

    /**
     * Hàm thêm từ bằng file.
     */
    public void insertFromFile() throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        String[] array = new String[2];
        int i = 0;
        while (line != null) {
            array[i] = line;
            i++;
            if (i == 2) {
                Word word = new Word();
                word.setWord_target(array[0]);
                word.setWord_explain(array[1]);
                dic.words.add(word);
                i = 0;
            }
            line = br.readLine();
        }
        br.close();
    }

    /**
     * Ham in ra Tu Dien.
     */
    public void exportToFile() throws IOException {
        FileWriter fw = new FileWriter("src\\dictionary_commandline\\test.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (Word i: dic.words) {
            String res = i.getWord_target() + "\n" + i.getWord_explain() + "\n";
            bw.write(res);
        }
        bw.flush();
        bw.close();
    }
}
