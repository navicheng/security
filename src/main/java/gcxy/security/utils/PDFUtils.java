package gcxy.security.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gcxy.security.bean.Chart;
import gcxy.security.bean.ChartRule;
import gcxy.security.bean.Checkjson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class PDFUtils {

    private static Font titlefont;
    private static Font headfont;
    private static Font keyfont;
    private static Font textfont;
    // 最大宽度
    private static int maxWidth = 520;

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


    public static Document generateFile(String path) {
        Document document = new Document(PageSize.A4);

        // 2.建立一个书写器(Writer)与document对象关联

        File file = new File(path);

        try {

            File fileParent = file.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            writer.setPageEvent(new HeaderFooter());
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static void fileOperation(Document document, Checkjson checkjson) {
        document.open();

        try {
            new PDFUtils().generatePDF(document, checkjson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        document.close();


    }

    public static void fileDrawOperation(Document document, Checkjson checkjson) {
        document.open();

        try {
            new PDFUtils().generateDrawPDF(document, checkjson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        document.close();


    }

    public static void fileOperation(Document document, Checkjson checkjson,String signPicPath,String signPicPathB) {
        document.open();

        try {
            new PDFUtils().generatePDF(document, checkjson,signPicPath,signPicPathB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        document.close();


    }

    public void generatePDF(Document document, Checkjson checkjson) throws Exception {
        PdfPTable tableOne = createTable(new float[]{60, 60, 60, 60, 60, 60, 60, 60});

        tableOne.addCell(createCell(checkjson.getChapterTitle()+"检查表", keyfont, Element.ALIGN_CENTER, 40, 8,1));
        tableOne.addCell(createCell("检查日期", keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableOne.addCell(createCell(new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ").format(new Date()), keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableOne.addCell(createCell("检查地点", keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableOne.addCell(createCell(checkjson.getCheckLocation(), keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableOne.addCell(createCell("序号", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell("检查项目", keyfont, Element.ALIGN_CENTER, 20, 4,1));
        tableOne.addCell(createCell("标准分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell("扣减分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell("实得分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        ArrayList<Chart> charts = checkjson.getCharts();
        HashMap<String,Float> title=new HashMap();
        String titles[] = new String[charts.size()];
        float realScores[] = new float[charts.size()];
        float scores[]=new float[charts.size()];
        int count=0;
        float score=0;
        for (int i = 0; i < charts.size(); i++) {
            if (!title.containsKey(charts.get(i).getChartTitle())) {
                title.put(charts.get(i).getChartTitle(),charts.get(i).getScore() - charts.get(i).getRealScore());
                titles[i]=charts.get(i).getChartTitle();
                realScores[i]=charts.get(i).getRealScore();
                scores[i]=charts.get(i).getScore();
            }
            else{
                score=charts.get(i).getScore() - charts.get(i).getRealScore();
                title.put(charts.get(i).getChartTitle(),title.get(charts.get(i).getChartTitle())+score);
            }
        }

        for(int i=0;i<charts.size();i++) {
            if (titles[i] != null) {
                tableOne.addCell(createCell(String.valueOf(count + 1), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                tableOne.addCell(createCell(titles[i], keyfont, Element.ALIGN_CENTER, 20, 4, 1));
                tableOne.addCell(createCell(String.valueOf(scores[i]), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                tableOne.addCell(createCell(String.valueOf(title.get(charts.get(i).getChartTitle())), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                tableOne.addCell(createCell(String.valueOf(scores[i]-title.get(charts.get(i).getChartTitle())), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                count++;
            }
        }
        tableOne.addCell(createCell(String.valueOf(title.size() + 1), keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell("总分", keyfont, Element.ALIGN_CENTER, 20, 4,1));
        tableOne.addCell(createCell("100", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell(String.valueOf(100.0 - checkjson.getRealScore()), keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell(String.valueOf(checkjson.getRealScore()), keyfont, Element.ALIGN_CENTER, 20, 1,1));
        document.add(tableOne);
        document.newPage();


        PdfPTable tableTwo = createTable(new float[]{25, 60, 80, 80, 80, 30, 30, 30});

        tableTwo.addCell(createCell(checkjson.getChapterTitle()+"检查表扣分细则", keyfont, Element.ALIGN_CENTER, 40, 8,1));
        tableTwo.addCell(createCell("检查日期", keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableTwo.addCell(createCell(new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ").format(new Date()), keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableTwo.addCell(createCell("检查地点", keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableTwo.addCell(createCell(checkjson.getCheckLocation(), keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableTwo.addCell(createCell("序号", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("检查项目", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("扣分细则", keyfont, Element.ALIGN_CENTER, 20, 3,1));
        tableTwo.addCell(createCell("标准分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("扣减分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("实得分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        int tag=1;//序号
        for (int i=0;i<charts.size();i++){
            StringBuffer RuleContent = new StringBuffer();
            String temp;
            ArrayList<ChartRule> rules = charts.get(i).getRules();
            for (int j=0;j<rules.size();j++){
                if(rules.get(j).isChecked()) {
                    tableTwo.addCell(createCell(String.valueOf(tag), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
//                    if (j == 0) {
                    tableTwo.addCell(createCell(charts.get(i).getChartTitle(), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
//                    }
                    temp=rules.get(j).getRuleContent();
                    if(temp.length()>28) {
                        RuleContent.append(temp).insert(28,"\n");
                        tableTwo.addCell(createCell(String.valueOf(RuleContent), keyfont, Element.ALIGN_CENTER, 40, 3, 1));
                    }else
                    {
                        tableTwo.addCell(createCell(rules.get(j).getRuleContent(), keyfont, Element.ALIGN_CENTER, 20, 3, 1));
                    }
                    tableTwo.addCell(createCell(String.valueOf(rules.get(j).getMaxScore()), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                    tableTwo.addCell(createCell(String.valueOf(rules.get(j).getRealScore()), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                    tableTwo.addCell(createCell(String.valueOf(rules.get(j).getMaxScore() - rules.get(j).getRealScore()), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                    tag++;
                }
            }
        }
        document.add(tableTwo);

    }


    public void generatePDF(Document document, Checkjson checkjson,String signPicPath,String signPicPathB) throws Exception {
        //添加签名
        Image image = Image.getInstance(signPicPath);
        image.setAlignment(Image.ALIGN_CENTER);
        image.scalePercent(40); //依照比例缩放
        Image imageB = Image.getInstance(signPicPathB);
        image.setAlignment(Image.ALIGN_CENTER);
        image.scalePercent(40); //依照比例缩放

        PdfPTable tableOne = createTable(new float[]{60, 60, 60, 60, 60, 60, 60, 60});

        tableOne.addCell(createCell(checkjson.getChapterTitle()+"检查表", keyfont, Element.ALIGN_CENTER, 40, 8,1));
        tableOne.addCell(createCell("检查日期", keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableOne.addCell(createCell(new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ").format(new Date()), keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableOne.addCell(createCell("检查地点", keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableOne.addCell(createCell(checkjson.getCheckLocation(), keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableOne.addCell(createCell("序号", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell("检查项目", keyfont, Element.ALIGN_CENTER, 20, 4,1));
        tableOne.addCell(createCell("标准分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell("扣减分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell("实得分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        ArrayList<Chart> charts = checkjson.getCharts();
        HashMap<String,Float> title=new HashMap();
        String titles[] = new String[charts.size()];
        float realScores[] = new float[charts.size()];
        float scores[]=new float[charts.size()];
        int count=0;
        float score=0;
        for (int i = 0; i < charts.size(); i++) {
            if (!title.containsKey(charts.get(i).getChartTitle())) {
                title.put(charts.get(i).getChartTitle(),charts.get(i).getScore() - charts.get(i).getRealScore());
                titles[i]=charts.get(i).getChartTitle();
                realScores[i]=charts.get(i).getRealScore();
                scores[i]=charts.get(i).getScore();
            }
            else{
                score=charts.get(i).getScore() - charts.get(i).getRealScore();
                title.put(charts.get(i).getChartTitle(),title.get(charts.get(i).getChartTitle())+score);
            }
        }

        for(int i=0;i<charts.size();i++) {
            if (titles[i] != null) {
                tableOne.addCell(createCell(String.valueOf(count + 1), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                tableOne.addCell(createCell(titles[i], keyfont, Element.ALIGN_CENTER, 20, 4, 1));
                tableOne.addCell(createCell(String.valueOf(scores[i]), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                tableOne.addCell(createCell(String.valueOf(title.get(charts.get(i).getChartTitle())), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                tableOne.addCell(createCell(String.valueOf(scores[i]-title.get(charts.get(i).getChartTitle())), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                count++;
            }
        }
        tableOne.addCell(createCell(String.valueOf(title.size() + 1), keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell("总分", keyfont, Element.ALIGN_CENTER, 20, 4,1));
        tableOne.addCell(createCell("100", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell(String.valueOf(100.0 - checkjson.getRealScore()), keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell(String.valueOf(checkjson.getRealScore()), keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableOne.addCell(createCell("被检查方签字", keyfont, Element.ALIGN_CENTER, 50, 1,1));
        tableOne.addCell(createCell(image, keyfont, Element.ALIGN_CENTER, 50, 7,1));
        tableOne.addCell(createCell("检查方签字", keyfont, Element.ALIGN_CENTER, 50, 1,1));
        tableOne.addCell(createCell(imageB, keyfont, Element.ALIGN_CENTER, 50, 7,1));

        document.add(tableOne);
        document.newPage();


        PdfPTable tableTwo = createTable(new float[]{25, 60, 80, 80, 80, 30, 30, 30});

        tableTwo.addCell(createCell(checkjson.getChapterTitle()+"检查表扣分细则", keyfont, Element.ALIGN_CENTER, 40, 8,1));
        tableTwo.addCell(createCell("检查日期", keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableTwo.addCell(createCell(new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ").format(new Date()), keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableTwo.addCell(createCell("检查地点", keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableTwo.addCell(createCell(checkjson.getCheckLocation(), keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableTwo.addCell(createCell("序号", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("检查项目", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("扣分细则", keyfont, Element.ALIGN_CENTER, 20, 3,1));
        tableTwo.addCell(createCell("标准分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("扣减分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("实得分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        int tag=1;//序号
        for (int i=0;i<charts.size();i++){
            StringBuffer RuleContent = new StringBuffer();
            String temp;
            ArrayList<ChartRule> rules = charts.get(i).getRules();

            for (int j=0;j<rules.size();j++){
                if(rules.get(j).isChecked()) {
                    tableTwo.addCell(createCell(String.valueOf(tag), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
//                    if (j == 0) {
                    tableTwo.addCell(createCell(charts.get(i).getChartTitle(), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
//                    }
                    temp=rules.get(j).getRuleContent();
                    if(temp.length()>28) {
                        RuleContent.append(temp).insert(28,"\n");
                        tableTwo.addCell(createCell(String.valueOf(RuleContent), keyfont, Element.ALIGN_CENTER, 40, 3, 1));
                    }else
                    {
                        tableTwo.addCell(createCell(rules.get(j).getRuleContent(), keyfont, Element.ALIGN_CENTER, 20, 3, 1));
                    }
                    tableTwo.addCell(createCell(String.valueOf(rules.get(j).getMaxScore()), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                    tableTwo.addCell(createCell(String.valueOf(rules.get(j).getRealScore()), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                    tableTwo.addCell(createCell(String.valueOf(rules.get(j).getMaxScore() - rules.get(j).getRealScore()), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                    tag++;
                }
            }
        }
        document.add(tableTwo);
    }

    public void generateDrawPDF(Document document, Checkjson checkjson) throws Exception {
        ArrayList<Chart> charts = checkjson.getCharts();
        PdfPTable tableTwo = createTable(new float[]{25, 25, 60, 107, 107, 30, 30, 30});
        tableTwo.addCell(createCell(checkjson.getChapterTitle()+"检查表扣分细则", keyfont, Element.ALIGN_CENTER, 40, 8,1));
        tableTwo.addCell(createCell("检查日期", keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableTwo.addCell(createCell(new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ").format(new Date()), keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableTwo.addCell(createCell("检查地点", keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableTwo.addCell(createCell(checkjson.getCheckLocation(), keyfont, Element.ALIGN_CENTER, 30, 2,1));
        tableTwo.addCell(createCell("序号", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("类型", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("检查项目", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("扣分细则", keyfont, Element.ALIGN_CENTER, 20, 2,1));
        tableTwo.addCell(createCell("标准分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("扣减分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("实得分数", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        int tag=1;//序号
        for (int i=0;i<charts.size();i++){
            StringBuffer RuleContent = new StringBuffer();
            String temp;
            ArrayList<ChartRule> rules = charts.get(i).getRules();
            int []type = new int[charts.size()];
            for (int j=0;j<rules.size();j++){
                tableTwo.addCell(createCell(String.valueOf(tag), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                if(type[i]==0){
                    tableTwo.addCell(createCell(charts.get(i).getTypeName(), keyfont, Element.ALIGN_CENTER, 20, 1, rules.size()));
                    type[i]=1;
                }
                tableTwo.addCell(createCell(charts.get(i).getChartTitle(), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                temp=rules.get(j).getRuleContent();
                if(temp.length()>28) {
                    RuleContent.append(temp).insert(28,"\n");
                    tableTwo.addCell(createCell(String.valueOf(RuleContent), keyfont, Element.ALIGN_CENTER, 40, 2, 1));
                }else
                {
                    tableTwo.addCell(createCell(rules.get(j).getRuleContent(), keyfont, Element.ALIGN_CENTER, 20, 2, 1));
                }
                tableTwo.addCell(createCell(String.valueOf(rules.get(j).getMaxScore()), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                tableTwo.addCell(createCell(String.valueOf(rules.get(j).getRealScore()), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                tableTwo.addCell(createCell(String.valueOf(rules.get(j).getMaxScore() - rules.get(j).getRealScore()), keyfont, Element.ALIGN_CENTER, 20, 1, 1));
                tag++;
            }
        }
        tableTwo.addCell(createCell(String.valueOf(tag), keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("总分", keyfont, Element.ALIGN_CENTER, 20, 4,1));
        tableTwo.addCell(createCell("100", keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell(String.valueOf(100.0 - checkjson.getRealScore()), keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell(String.valueOf(checkjson.getRealScore()), keyfont, Element.ALIGN_CENTER, 20, 1,1));
        tableTwo.addCell(createCell("被检查方签字", keyfont, Element.ALIGN_CENTER, 50, 1,1));
        tableTwo.addCell(createCell(String.valueOf(" "), keyfont, Element.ALIGN_CENTER, 50, 7,1));
        tableTwo.addCell(createCell("检查方签字", keyfont, Element.ALIGN_CENTER, 50, 1,1));
        tableTwo.addCell(createCell(String.valueOf(" "), keyfont, Element.ALIGN_CENTER, 50, 7,1));
        document.add(tableTwo);

    }


    public PdfPCell createCell(String value, Font font, int align, int height, int colspanNum,int rowspanNum) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        cell.setColspan(colspanNum);
        cell.setRowspan(rowspanNum);
        cell.setFixedHeight(height);
        return cell;
    }

    /**
     * 创建含有图片的单元格
     * @param image
     * @param font
     * @param align
     * @param height
     * @param colspanNum
     * @param rowspanNum
     * @return
     */
    public PdfPCell createCell(Image image, Font font, int align, int height, int colspanNum,int rowspanNum) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setImage(image);
        cell.setColspan(colspanNum);
        cell.setRowspan(rowspanNum);
        cell.setFixedHeight(height);
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

