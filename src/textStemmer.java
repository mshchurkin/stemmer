import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.russianStemmer;

import java.util.*;

public class textStemmer {

    static Map<String, Integer> wordsWeight = new HashMap<>();
    static Map<String, Integer> sentencesWeight = new HashMap<>();
    static SnowballStemmer snowballStemmer = new russianStemmer();

    /**
     * stemmer text normalization
     *
     * @param percent text cut off percent
     * @param text    non-normalized text
     * @return normalized text
     */
    public static String stemText(String text, Integer percent, List<String> stopWords) {

        String normalizedText = "";

        String sentences[] = text.split("[.!?]\\s*");

        String words[][] = new String[sentences.length][];

        for (int i = 0; i < sentences.length; i++) {
            words[i] = sentences[i].split("[\\p{Punct}\\s]+");
        }

        for (int i = 0; i < sentences.length; i++)
            for (int j = 0; j < words[i].length; j++) {
                snowballStemmer.setCurrent(words[i][j].toLowerCase());
                boolean flag = snowballStemmer.stem();
                if (flag) {
                    String temp = snowballStemmer.getCurrent();
                    if (wordsWeight.containsKey(temp)) {
                        int val = wordsWeight.get(temp).intValue();
                        wordsWeight.replace(temp, ++val);
                    } else {
                        wordsWeight.put(temp, 1);
                    }
                }
            }
        for (int k = 0; k < stopWords.size(); k++)
            for (int i = 0; i < wordsWeight.size(); i++) {
                snowballStemmer.setCurrent(stopWords.get(k));
                boolean flag = snowballStemmer.stem();
                if (flag) {
                    String temp = snowballStemmer.getCurrent();
                    if (wordsWeight.containsKey(temp)) {
                        wordsWeight.remove(temp);
                    }
                }
            }

        System.out.println();
        System.out.println();
        System.out.println("Слова");
        System.out.println();

        for (
                int k = 0;
                k < sentences.length; k++)

        {
            Iterator<Map.Entry<String, Integer>> entries = wordsWeight.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Integer> entry = entries.next();
                if (sentences[k].trim().toLowerCase().contains(entry.getKey())) {
                    if (sentencesWeight.containsKey(sentences[k])) {
                        Integer val = sentencesWeight.get(sentences[k]);
                        val = val + entry.getValue();
                        sentencesWeight.replace(sentences[k], val);
                    } else {
                        sentencesWeight.put(sentences[k], 1);
                    }
                }
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        }

        List sortedSentences = new ArrayList(sentencesWeight.entrySet());

        Collections.sort(sortedSentences, new
                Comparator() {
                    public int compare(Object obj1, Object obj2) {
                        return ((Comparable) ((Map.Entry) (obj2)).getValue
                                ()).compareTo(((Map.Entry) (obj1)).getValue());
                    }
                });
        Integer sentencesCount = sortedSentences.size() * percent / 100;

        System.out.println();
        System.out.println();
        System.out.println("Предложения");
        System.out.println();
        for (
                int i = 0; i < sortedSentences.size(); i++)

        {
            System.out.println(sortedSentences.get(i));
        }

        System.out.println();
        System.out.println();
        System.out.println("Отчет");
        System.out.println();
        for (
                int i = 0;
                i < sentencesCount; i++)

        {
            normalizedText = normalizedText + ((HashMap.Entry<String, Integer>) sortedSentences.get(i)).getKey() + "\n";
        }
        return normalizedText;
    }

}