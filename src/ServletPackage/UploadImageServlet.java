package ServletPackage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "UploadImageServlet")
@WebServlet(name = "UploadImageServlet", urlPatterns = "/ServletPackage/UploadImageServlet")
public class UploadImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*设置编码*/
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text;charset=UTF-8");

        String result = "";

        PrintWriter printWriter = response.getWriter();

        printWriter.println("hhh");

        String fname = "";
        String path = request.getServletContext().getRealPath("/");

        System.out.println("获取根目录：" + path);

        //get you file name
        Part fileName = request.getPart("yourImage");

        if (fileName.getSize() > 1024*1024) {
            //your file is so big that i should deleter it!
            fileName.delete();
        } else {
            // save image

            // create image path
            path = path + "image\\";

            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }

            String h=fileName.getHeader("content-disposition");
            //得到文件名
            fname=h.substring(h.lastIndexOf("fileName")+10,h.length()-1);
            System.out.println("文件名：" + fname);

            fileName.write(path+fname);

            //获得当前上传文件的路径
            result = path+fname;




        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
