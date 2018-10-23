package com.gafis.xj.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gafis.xj.model.FileInfo;
import com.gafis.xj.service.IFileInfoService;

@RestController
public class FileUpload {
	
	@Autowired
	private IFileInfoService fileInfoService;
	@Value("${upload_path}") 
	private String uploadPath;
	@RequestMapping("/upload")
	public String fileUpload(@RequestParam("fileToUpload") MultipartFile file,FileInfo info){
		//存文件
		if (!file.isEmpty()) {
			try {  
				String filePath = uploadPath+"/"+info.getVersion()+ "/";  
				String path = filePath + file.getOriginalFilename();
				
				//存上传的文件信息
				if(info!=null){
	    			info.setUploadTime(new Date());
	    			info.setFileName(file.getOriginalFilename());
	    			info.setFilePath(path);
	    			fileInfoService.saveFile(info);
	    		}
			
				File dir = new File(filePath);
	            if(! dir.exists()) {
	                dir.mkdir();
	            }
	            File tempFile = null;
                tempFile =  new File(path);
                FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }
		}
		return "保存成功";
	}
	
	@RequestMapping("/download")
	public void download(@RequestParam("pkId")String pkId,HttpServletResponse  response){
		String path1="F:";
		FileInfo info= fileInfoService.findFileInfoById(pkId);
		try{
			if(info!=null){
				String filePath = path1+"/"+info.getVersion()+ "/"+info.getFileName();  
				// path是指欲下载的文件的路径。
	            File file = new File(filePath);
	            // 取得文件的后缀名。
	            String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1).toUpperCase();
	            // 以流的形式下载文件。
	            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
	            byte[] buffer = new byte[fis.available()];
	            fis.read(buffer);
	            fis.close();
	            // 清空response
	            response.reset();
	            // 设置response的Header
	            response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes()));
	            response.addHeader("Content-Length", "" + file.length());
	            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	            response.setContentType("application/octet-stream");
	            toClient.write(buffer);
	            toClient.flush();
	            toClient.close();
			}else{
				 
			}
		}catch(Exception e){}
		
	}
}
