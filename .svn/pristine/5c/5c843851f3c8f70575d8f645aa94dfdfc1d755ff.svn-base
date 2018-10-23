package com.gafis.xj.dao;

 

import java.util.List;

import com.gafis.xj.model.FileInfo;
import com.github.pagehelper.Page;

public interface FileInfoMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(FileInfo record);

    
    FileInfo selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
    
    
    
    /**
     * 获取指定类型最新控件版本信息
     * @param type
     * @return
     */
    FileInfo getLatest(String type);
    
    
    /**
     * 获取更新程序列表
     * @param fileInfo
     * @return
     */
    Page<FileInfo> selectByQuery(FileInfo fileInfo);
    
    
    
    
}