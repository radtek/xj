package com.gafis.xj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gafis.xj.aspect.LogAnnotation;
import com.gafis.xj.model.FileInfo;
import com.gafis.xj.service.IFileInfoService;
import com.gafis.xj.util.Pagination;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONObject;

@Controller
public class VersionController {
	
	@Autowired
	private IFileInfoService fileInfoService; 
	
	 @RequestMapping("/versionEdit")
	  public String versionEdit(Model model ){
		model.addAttribute("name", "aa");
	    return "version/versionEdit";
	  }
	  	
	  
	  @RequestMapping("/versionList")
	  public String list(Model model){
	    return "version/versionList";
	  }
	  
	  @ResponseBody
	  @RequestMapping("/versionListData")
	  @LogAnnotation
	  public Map<Object, Object> listData(FileInfo fileInfo,Pagination pagination){
		  Map<Object, Object> map=new HashMap<>();
		  int limit=20;
		  int page=1;
		  if(pagination.getPage()!=null&&pagination.getPage()!=0){
			  page=pagination.getPage();
		  }
		  pagination=new Pagination(page, limit);
		  Page<FileInfo> list=fileInfoService.findFileList(fileInfo, pagination);
		  PageInfo<FileInfo> pageInfo = new PageInfo<>(list);
		  map.put("total", pageInfo.getTotal());
		  map.put("rows", pageInfo.getList());
		  return map;
	  }
	  
	  @ResponseBody
	  @RequestMapping("/versionEnable")
	  @LogAnnotation
	  public String versionEnable(String pkId){
		  FileInfo fileInfo=fileInfoService.findFileInfoById(pkId);
		  FileInfo newFile=new FileInfo();
		  newFile.setProgramType(fileInfo.getProgramType());
		  newFile.setEnableFlag("1");
		  List<FileInfo> list=fileInfoService.findFileListWithOutPagination(newFile);
		  if(list!=null&&list.size()>0){
			  return "failure";
		  }else{
			  fileInfo.setEnableFlag("1");
			  fileInfoService.updateFileInfoBySelective(fileInfo);
			  return "success";
		  }
		  
	  }
	  
	  @ResponseBody
	  @RequestMapping("/versionDisable")
	  public String versionDisable(String pkId){
		  FileInfo fileInfo=fileInfoService.findFileInfoById(pkId);
		  fileInfo.setEnableFlag("0");
		  fileInfoService.updateFileInfoBySelective(fileInfo);
		  return "success";
	  }
	  
	  
	  @ResponseBody
	  @RequestMapping("/collector/program")
	  public String program(String programtype,String version,HttpServletRequest request){
		  FileInfo fileInfo=fileInfoService.getLatestFileInfo(programtype);
		  JSONObject jsonObject=new JSONObject();
		  int isUpdate=0;
		  if(fileInfo!=null){
			  //版本号判断
			try {
				isUpdate=compareVersion(version,fileInfo.getVersion());
			} catch (Exception e) {
				 jsonObject.put("code", "102");
				 return jsonObject.toString();
			}
			if(isUpdate<0){
				//当前传入的版本号小于最新版本号，需要更新
				JSONObject data=new JSONObject();
				String reqUrl=request.getRequestURL().toString();
				String url=reqUrl.substring(0, reqUrl.lastIndexOf("collector/program"))+"download?pkId="+fileInfo.getPkId();
				jsonObject.put("code", "101");
				data.put("url", url);
				data.put("version", fileInfo.getVersion());
				jsonObject.put("data", data);
				return jsonObject.toString();
			}else if(isUpdate>0){
				return "102";
			}else{
				return "100";
			}  
			 
		  }else{
			  return "102";
		  }
	  }
	  

		 /** 
		 * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0 
		 * @param version1 
		 * @param version2 
		 */  
		public static int compareVersion(String version1, String version2) throws Exception {  
		    if (version1 == null || version2 == null) {  
		        throw new Exception("compareVersion error:illegal params.");  
		    }  
		    String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用.；  
		    String[] versionArray2 = version2.split("\\.");  
		    int idx = 0;  
		    int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值  
		    int diff = 0;  
		    while (idx < minLength  
		            && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度  
		            && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符  
		        ++idx;  
		    }  
		    //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；  
		    diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;  
		    return diff;  
		}  
}
