import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)  {

        String filePath = "check.pdf";//путь к файлу

        List<String> stopWords=new ArrayList<>();
        stopWords.add("коробки");//стоп слова

        System.out.println(textStemmer.stemText(textWorker.textImport(filePath),50,stopWords));
    }
}
