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
		// ���Ŀ¼�����ڣ�������
		java.io.File folder = new java.io.File(save_path);
		if (!folder.exists()) {
			folder.mkdirs();
		}*/
	   
	   request.setCharacterEncoding("utf-8");  
       //��ô����ļ���Ŀ������  
       DiskFileItemFactory factory = new DiskFileItemFactory();  
       //��ȡ�ļ��ϴ���Ҫ�����·����upload�ļ�������ڡ�  
       String path = request.getSession().getServletContext().getRealPath("/upload");
       System.out.println("path = " + path);
       //������ʱ����ļ��Ĵ洢�ң�����洢�ҿ��Ժ����մ洢�ļ����ļ��в�ͬ����Ϊ���ļ��ܴ�Ļ���ռ�ù����ڴ��������ô洢�ҡ�  
       factory.setRepository(new File(path));  
       //���û���Ĵ�С�����ϴ��ļ���������������ʱ���ͷŵ���ʱ�洢�ҡ�  
       factory.setSizeThreshold(1024*1024);  
       //�ϴ��������ࣨ��ˮƽAPI�ϴ�������  
       ServletFileUpload upload = new ServletFileUpload(factory);  
         
       try{  
           //���� parseRequest��request������  ����ϴ��ļ� FileItem �ļ���list ��ʵ�ֶ��ļ��ϴ���  
           List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
           for(FileItem item:list){  
               //��ȡ���������֡�  
               String name = item.getFieldName(); 
               System.out.println("name = " + name);
               //�����ȡ�ı���Ϣ����ͨ���ı���Ϣ����ͨ��ҳ�����ʽ���������ַ�����  
               if(item.isFormField()){  
                   //��ȡ�û�����������ַ�����  
                   String value = item.getString(); 
                   System.out.println("is form field value = " + value);
                   request.setAttribute(name, value);  
               }  
               //���������ǷǼ��ַ���������ͼƬ����Ƶ����Ƶ�ȶ������ļ���  
               else{   
                   //��ȡ·����  
                   String value = item.getName();  
                   System.out.println(" value = " + value);
                   //ȡ�����һ����б�ܡ�  
                   int start = value.lastIndexOf("\\");  
                   //��ȡ�ϴ��ļ��� �ַ������֡�+1��ȥ����б�ܡ�  
                   String filename = value.substring(start+1);  
                   System.out.println(" filename = " + filename);
                   request.setAttribute(name, filename);  
                     
                   /*�������ṩ�ķ���ֱ��д���ļ��С� 
                    * item.write(new File(path,filename));*/  
                   //�յ�д�����յ��ļ��С�  
                   OutputStream out = new FileOutputStream(new File(path,filename));  
                   InputStream in = item.getInputStream();  
                     
                   int length = 0;  
                   byte[] buf = new byte[1024];  
                   System.out.println("��ȡ�ļ�����������:"+ item.getSize());  
                     
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
               + "temp"; // ��ʱĿ¼
       System.out.println("temp=" + temp);
       String loadpath = request.getSession().getServletContext()
               .getRealPath("/")
               + "Image"; // �ϴ��ļ����Ŀ¼
       System.out.println("loadpath=" + loadpath);
       DiskFileItemFactory fu = new DiskFileItemFactory(); // ��Ҫ����commons-fileupload-1.2.2.jar
                                                   // http://commons.apache.org/fileupload/
       fu.setSizeMax(1 * 1024 * 1024); // ���������û��ϴ��ļ���С,��λ:�ֽ�
       fu.setSizeThreshold(4096); // �������ֻ�������ڴ��д洢������,��λ:�ֽ�
       fu.setRepositoryPath(temp); // ����һ���ļ���С����getSizeThreshold()��ֵʱ���ݴ����Ӳ�̵�Ŀ¼

       // ��ʼ��ȡ�ϴ���Ϣ
       int index = 0;
       List fileItems = null;

       try {
           fileItems = fu.parseRequest(request);
           System.out.println("fileItems=" + fileItems);
       } catch (Exception e) {
           e.printStackTrace();
       }

       Iterator iter = fileItems.iterator(); // ���δ���ÿ���ϴ����ļ�
       while (iter.hasNext()) {
           FileItem item = (FileItem) iter.next();// �������������ļ�������б���Ϣ
           if (!item.isFormField()) {
               String name = item.getName();// ��ȡ�ϴ��ļ���,����·��
               name = name.substring(name.lastIndexOf("\\") + 1);// ��ȫ·������ȡ�ļ���
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

           } else// ȡ�������ļ�������б���Ϣ
           {
               String fieldvalue = item.getString();
               // �����������ӦдΪ��(תΪUTF-8����)
               // String fieldvalue = new
               // String(item.getString().getBytes(),"UTF-8");
           }
       }
       String text1 = "11";
       response.sendRedirect("result.jsp?text1=" + text1);
   */}
}
