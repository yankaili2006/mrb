package com.mrb.servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

public class UploadServlet extends HttpServlet {

	private boolean isMultipart;
	private int maxFileSize = 20 * 1024 * 1024;
	private int maxMemSize = 20 * 1024 * 1024;
	private File file;
	Logger log = Logger.getLogger(this.getClass());
	
	String filePath = new String("/tmp/");

	public void init() {
		// Get the file location where it would be stored.
		String prefix = getServletContext().getRealPath("/"); // 获取工程的根路径
		filePath = prefix + "/" + getInitParameter("file-upload-path"); // 从Web.xml中读取Log4J的配置文件

		File fileRoot = new File(filePath);
		if (!fileRoot.isDirectory()) {
			fileRoot.mkdir();
			System.out.println("mkdir: " + filePath);
		}
		System.out.println("RootPath:" + filePath);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		request.setCharacterEncoding("utf-8");

		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File(filePath));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);

		try {
			List fileItems = upload.parseRequest(request);

			Iterator i = fileItems.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();

					String newFilePath = filePath + fileName;
					file = new File(newFilePath);

					fi.write(file);
					out.print(fileName);
				}
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		throw new ServletException("GET method used with "
				+ getClass().getName() + ": POST method required.");
	}
}