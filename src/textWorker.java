import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class textWorker {

    /**
     * Gets text from pdf file
     *
     * @param filePath report pdf file path
     * @return
     */
    public static String textImport(String filePath) {


        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper;

        String parsedText = "";
        try {
            pdfStripper = new PDFTextStripper();
            pdDoc = PDDocument.load(new File(filePath));
            parsedText = (pdfStripper.getText(pdDoc));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
            } catch (Exception e1) {
                e.printStackTrace();
            }
        }
        return parsedText;
    }
}
