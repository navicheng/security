package gcxy.security.utils;

import org.apache.poi.util.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtils {
    /**
     * 文件下载
     * @param request
     * @param response
     * @param file
     * @throws IOException
     */
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file) throws IOException {


        if (!file.exists())
            throw new IOException("下载文件不存在");
        String userAgent = request.getHeader("User-Agent");
        String fileName;
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            // IE浏览器
            fileName = java.net.URLEncoder.encode(file.getName(), "UTF-8");
        } else {
            // 非IE浏览器
            fileName = "\"" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1") + "\"";
            //fileName = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
        }
        response.addHeader("Content-Type", "application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName + ";filename*=UTF-8''" + fileName);
//        System.out.println(file.getName());
        InputStream is = new FileInputStream(file);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }

    /**
     * 上传文件
     * @param file
     * @param path
     */
    public static void uploadFile(MultipartFile file, String path){
        String filename=file.getOriginalFilename();

        File des=new File(path+filename+".jpg");
        File fileParent = des.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        try {
            file.transferTo(des);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 上传文件
     * @param file
     * @param path
     */
    public static void uploadDrawFile(MultipartFile file, String path){
        String filename=file.getOriginalFilename();

        File des=new File(path+filename+".pdf");
        File fileParent = des.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        try {
            file.transferTo(des);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
