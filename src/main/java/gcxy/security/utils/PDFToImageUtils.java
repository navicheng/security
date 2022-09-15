package gcxy.security.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PDFToImageUtils {
    public static File pdf2png(File file) {
        // 将pdf装图片 并且自定义图片得格式大小

        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 144); // Windows native DPI
                // BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
                ImageIO.write(image, "PNG", new File(file.getName().replace(".pdf","")));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  new File(file.getName()+".png");
    }
}
