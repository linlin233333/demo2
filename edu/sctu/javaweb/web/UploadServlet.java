package web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/upfile.jsp").forward(req, resp);
    }

    /**
     * 解析上传的图片路径 图片的信息
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setAttribute("message", "");
        req.setAttribute("path", "");
        String filename = null;
        // 设置上传图片的保存路径
        String savePath = this.getServletContext().getRealPath("/images");
        File file = new File(savePath);
        // 判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath + "目录不存在，需要创建");
            // 创建目录
            file.mkdir();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        // 3、判断提交上来的数据是否是上传表单的数据
        if (!ServletFileUpload.isMultipartContent(req)) {
            // 按照传统方式获取数据
            return;
        }
        try {
            List<FileItem> list = upload.parseRequest(req);
            System.out.println(list.toString());// 文件的路径 以及保存的路径
            for (FileItem item : list) {
                filename = item.getName();// 获得一个项的文件名称
                if (filename == null || filename.trim().equals("")) {// 如果為空則跳過
                    continue;
                }
                // 報錯 需要過濾文件名稱 java.io.FileNotFoundException:
                // G:\测试02\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\FaceUp\WEB-INF\images\C:\Users\Ray\Pictures\2.jpeg
                // (文件名、目录名或卷标语法不正确。)

                filename = filename.substring(filename.lastIndexOf("\\") + 1);
//				System.out.print(filename);
                if (filename.substring(filename.lastIndexOf(".") + 1).equals("png")
                        || filename.substring(filename.lastIndexOf(".") + 1).equals("jpg")
                        || filename.substring(filename.lastIndexOf(".") + 1).equals("jpeg")) {
                    InputStream in = item.getInputStream();// 獲得上傳的輸入流
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);// 指定web-inf目錄下的images文件
                    req.setAttribute("path",  "images"+"\\" + filename);

                    int len = 0;
                    byte buffer[] = new byte[1024];
                    while ((len = in.read(buffer)) > 0)// 每次讀取
                    {
                        out.write(buffer, 0, len);
                    }
                    in.close();
                    out.close();
                    item.delete();
                } else {  //必须是图片才能上传否则失败
                    return ;
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        req.setAttribute("message", "上传成功");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}