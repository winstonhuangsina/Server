package com.bao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
   @SuppressWarnings("deprecation")
public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
	   
/*
		String save_path = "./upload/";
		// 如果目录不存在，则建立。
		java.io.File folder = new java.io.File(save_path);
		if (!folder.exists()) {
			folder.mkdirs();
		}*/
	   
	   request.setCharacterEncoding("utf-8");  
       //获得磁盘文件条目工厂。  
       DiskFileItemFactory factory = new DiskFileItemFactory();  
       //获取文件上传需要保存的路径，upload文件夹需存在。  
       String path = request.getSession().getServletContext().getRealPath("/upload");
       System.out.println("path = " + path);
       //设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。  
       factory.setRepository(new File(path));  
       //设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。  
       factory.setSizeThreshold(1024*1024);  
       //上传处理工具类（高水平API上传处理？）  
       ServletFileUpload upload = new ServletFileUpload(factory);  
         
       try{  
           //调用 parseRequest（request）方法  获得上传文件 FileItem 的集合list 可实现多文件上传。  
           List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
           for(FileItem item:list){  
               //获取表单属性名字。  
               String name = item.getFieldName(); 
               System.out.println("name = " + name);
               //如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。  
               if(item.isFormField()){  
                   //获取用户具体输入的字符串，  
                   String value = item.getString(); 
                   System.out.println("is form field value = " + value);
                   request.setAttribute(name, value);  
               }  
               //如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。  
               else{   
                   //获取路径名  
                   String value = item.getName();  
                   System.out.println(" value = " + value);
                   //取到最后一个反斜杠。  
                   int start = value.lastIndexOf("\\");  
                   //截取上传文件的 字符串名字。+1是去掉反斜杠。  
                   String filename = value.substring(start+1);  
                   System.out.println(" filename = " + filename);
                   request.setAttribute(name, filename);  
                     
                   /*第三方提供的方法直接写到文件中。 
                    * item.write(new File(path,filename));*/  
                   //收到写到接收的文件中。  
                   OutputStream out = new FileOutputStream(new File(path,filename));  
                   InputStream in = item.getInputStream();  
                     
                   int length = 0;  
                   byte[] buf = new byte[1024];  
                   System.out.println("获取文件总量的容量:"+ item.getSize());  
                     
                   while((length = in.read(buf))!=-1){  
                       out.write(buf,0,length);  
                   }  
                   in.close();  
                   out.close();  
               }  
           }  
       }catch(Exception e){  
           e.printStackTrace();  
       }  
         
	   
	   /*

       String temp = request.getSession().getServletContext().getRealPath("/")
               + "temp"; // 临时目录
       System.out.println("temp=" + temp);
       String loadpath = request.getSession().getServletContext()
               .getRealPath("/")
               + "Image"; // 上传文件存放目录
       System.out.println("loadpath=" + loadpath);
       DiskFileItemFactory fu = new DiskFileItemFactory(); // 需要导入commons-fileupload-1.2.2.jar
                                                   // http://commons.apache.org/fileupload/
       fu.setSizeMax(1 * 1024 * 1024); // 设置允许用户上传文件大小,单位:字节
       fu.setSizeThreshold(4096); // 设置最多只允许在内存中存储的数据,单位:字节
       fu.setRepositoryPath(temp); // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录

       // 开始读取上传信息
       int index = 0;
       List fileItems = null;

       try {
           fileItems = fu.parseRequest(request);
           System.out.println("fileItems=" + fileItems);
       } catch (Exception e) {
           e.printStackTrace();
       }

       Iterator iter = fileItems.iterator(); // 依次处理每个上传的文件
       while (iter.hasNext()) {
           FileItem item = (FileItem) iter.next();// 忽略其他不是文件域的所有表单信息
           if (!item.isFormField()) {
               String name = item.getName();// 获取上传文件名,包括路径
               name = name.substring(name.lastIndexOf("\\") + 1);// 从全路径中提取文件名
               long size = item.getSize();
               if ((name == null || name.equals("")) && size == 0)
                   continue;
               int point = name.indexOf(".");
               name = (new Date()).getTime()
                       + name.substring(point, name.length()) + index;
               index++;
               File fNew = new File(loadpath, name);
               try {
                   item.write(fNew);
               } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

           } else// 取出不是文件域的所有表单信息
           {
               String fieldvalue = item.getString();
               // 如果包含中文应写为：(转为UTF-8编码)
               // String fieldvalue = new
               // String(item.getString().getBytes(),"UTF-8");
           }
       }
       String text1 = "11";
       response.sendRedirect("result.jsp?text1=" + text1);
   */}
}
