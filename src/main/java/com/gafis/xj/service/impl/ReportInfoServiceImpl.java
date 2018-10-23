package com.gafis.xj.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gafis.xj.dao.ReportInfoMapper;
import com.gafis.xj.model.ReportInfo;
import com.gafis.xj.service.IReportInfoService;

@Service
public class ReportInfoServiceImpl implements IReportInfoService{

	@Autowired
	private ReportInfoMapper reportInfoMapper;
	
	@Override
	public ReportInfo findReportInfoById(String pkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveReportInfo(ReportInfo reportInfo) {
		// TODO Auto-generated method stub
		return reportInfoMapper.insert(reportInfo);
	}

	
	@Override
	@Transactional
	public void saveImportReport(String reportStr) {
		//判断如果第一个字符为65279， 去掉
		if(reportStr.charAt(0) == 65279){
			reportStr = reportStr.substring(1);
		}
		String [] str=reportStr.replaceAll(" ", "").split("&");
		String [] value;
		ReportInfo reportInfo=new ReportInfo();
		//上报对象赋值
		for(int i=0;i<str.length;i++){
			value=str[i].split("=");
			switch(value[0]){
				case "sitenumber":
					reportInfo.setSitenumber(value[1]);
					break;
				case "cardid":
					reportInfo.setCardid(value[1]);
					break;
				case "reportresult":
					reportInfo.setReportresult(value[1]);
					break;
				case "failedreason":
					reportInfo.setFailedreason(value[1]);
					break;
				case "reportdate":
					reportInfo.setReportdate(value[1]);
					break;
			}
		}
		reportInfo.setCreateDate(new Date());
		if(reportInfo.getCardid()!=null&&!"".equals(reportInfo.getCardid())){
		  reportInfoMapper.insert(reportInfo);
		}
	}

}
