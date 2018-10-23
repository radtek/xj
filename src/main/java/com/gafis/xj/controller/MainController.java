package com.gafis.xj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gafis.xj.aspect.TestAnnotation;

@Controller
public class MainController {

		@RequestMapping("index")
		@TestAnnotation()
		public String index(String str1,String str2){
			return "main";
		}
		
		@RequestMapping("myflow")
		public String toFlow(){
			return "myflow/demo4.html";
		}
		
		@RequestMapping(value="/xml", produces = { "application/xml;charset=UTF-8" })
		@ResponseBody
		public String xmlTest(){
			
			return "";
		}
}
