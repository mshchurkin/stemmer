import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)  {

        String filePath = "tale.pdf";//путь к файлу

        List<String> stopWords=new ArrayList<>();
        //stopWords.add("коробки");//стоп слова

        System.out.println(textStemmer.stemText(textWorker.textImport(filePath),5,stopWords));
    }
}
