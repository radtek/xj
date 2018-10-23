package com.gafis.xj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gafis.xj.dao.FileInfoMapper;
import com.gafis.xj.model.FileInfo;
import com.gafis.xj.service.IFileInfoService;
import com.gafis.xj.util.Pagination;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
@Service
public class FileInfoServiceImpl implements IFileInfoService {
	@Autowired
	private FileInfoMapper fileInfoMapper;
	
	public void saveFile(FileInfo fileInfo) {
		fileInfoMapper.insert(fileInfo);
	}

	public int updateFileInfoBySelective(FileInfo fileInfo) {
		
		return fileInfoMapper.updateByPrimaryKeySelective(fileInfo);
	}

	public Page<FileInfo> findFileList(FileInfo fileInfo,Pagination pagination) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pagination.getPage(),pagination.getLimit());
		return fileInfoMapper.selectByQuery(fileInfo);
	}

	public FileInfo getLatestFileInfo(String programType) {
		return fileInfoMapper.getLatest(programType);
	}

	public FileInfo findFileInfoById(String pkId) {
		return fileInfoMapper.selectByPrimaryKey(pkId);
	}

	@Override
	public List<FileInfo> findFileListWithOutPagination(FileInfo fileInfo) {
		return fileInfoMapper.selectByQuery(fileInfo);
	}

}
