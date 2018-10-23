package com.gafis.xj.service;

import java.util.List;

import com.gafis.xj.model.FileInfo;
import com.gafis.xj.util.Pagination;
import com.github.pagehelper.Page;

/**
 * 更新程序管理接口
 * @author sun
 *
 */
public interface IFileInfoService {
	
	/**
	 * 根据id获取文件信息
	 * @param pkId
	 * @return
	 */
	FileInfo findFileInfoById(String pkId);
	
	/**
	 * 保存更新程序
	 * @param fileInfo
	 */
	void saveFile(FileInfo fileInfo);
	
	
	/**
	 * 更新信息
	 * @param fileInfo
	 */
	int updateFileInfoBySelective(FileInfo fileInfo);
	
	
	/**
	 * 获取控制程序列表
	 * @param fileInfo
	 * @return
	 */
	Page<FileInfo> findFileList(FileInfo fileInfo,Pagination pagination);
	
	/**
	 * 获取控制程序列表
	 * @param fileInfo
	 * @return
	 */
	List<FileInfo> findFileListWithOutPagination(FileInfo fileInfo);
	
	/**
	 * 根据程序类型获取最新更新程序
	 * @param programType 程序类型
	 * @return
	 */
	FileInfo getLatestFileInfo(String programType);
	
	
}
