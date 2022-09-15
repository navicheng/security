package gcxy.security.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HelloWorld {
    public static void main(String[] args) throws Exception {
        try {
            // 1.新建document对象
            Document document = new Document(PageSize.A4);// 建立一个Document对象

            // 2.建立一个书写器(Writer)与document对象关联
            File file = new File("D:\\helloworld.pdf");
            file.createNewFile();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));


            // 3.打开文档
            document.open();
            document.addTitle("Title@PDF-Java");// 标题
            document.addAuthor("Author@umiz");// 作者
            document.addSubject("Subject@iText pdf sample");// 主题
            document.addKeywords("Keywords@iTextpdf");// 关键字
            document.addCreator("Creator@umiz`s");// 创建者

            // 4.向文档中添加内容
            new HelloWorld().generatePDF(document);

            // 5.关闭文档
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 定义全局的字体静态变量
    private static Font titlefont;
    private static Font headfont;
    private static Font keyfont;
    private static Font textfont;
    // 最大宽度
    private static int maxWidth = 520;

    // 静态代码块
    static {
        try {
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            titlefont = new Font(bfChinese, 16, Font.BOLD);
            headfont = new Font(bfChinese, 14, Font.BOLD);
            keyfont = new Font(bfChinese, 10, Font.BOLD);
            textfont = new Font(bfChinese, 10, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generatePDF(Document document) throws Exception {
        PdfPTable table1 = createTable(new float[]{40, 240,60,60,60});

        table1.addCell(createCell("11",keyfont, Element.ALIGN_CENTER,5));
        table1.addCell(createCell("序号", keyfont, Element.ALIGN_CENTER,0));
        table1.addCell(createCell("检查项目", keyfont, Element.ALIGN_CENTER,0));
        table1.addCell(createCell("标准分数", keyfont, Element.ALIGN_CENTER,0));
        table1.addCell(createCell("扣减分数", keyfont, Element.ALIGN_CENTER,0));
        table1.addCell(createCell("应得分数", keyfont, Element.ALIGN_CENTER,0));


        document.add(table1);
        document.newPage();

        PdfPTable table2 = createTable(new float[]{40, 240,60,60,60});

        table2.addCell(createCell("11",keyfont, Element.ALIGN_CENTER,5));
        table2.addCell(createCell("序号", keyfont, Element.ALIGN_CENTER,0));
        table2.addCell(createCell("检查项目", keyfont, Element.ALIGN_CENTER,0));
        table2.addCell(createCell("标准分数", keyfont, Element.ALIGN_CENTER,0));
        table2.addCell(createCell("扣减分数", keyfont, Element.ALIGN_CENTER,0));
        table2.addCell(createCell("应得分数", keyfont, Element.ALIGN_CENTER,0));

        document.add(table2);
    }

    public PdfPCell createCell(String value, Font font, int align,int colspanNum) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        cell.setColspan(colspanNum);
        return cell;
    }

    public PdfPTable createTable(float[] widths) {
        PdfPTable table = new PdfPTable(widths);
        try {
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }
}
