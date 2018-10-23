
// 保存验证非空；验证不通过返回false；str为验证控件id (或id,id)
function saveValiNoEmpty(str) {
	var bool = true;
	var arrayObj = str.split(",");
	for ( var i = 0; i < arrayObj.length; i++) {
		var str = "#" + arrayObj[i];
		var strP = "#" + arrayObj[i] + "P";
		var o = $("#frameContent").contents().find(str).val();
		if ($.trim(o).length == 0) {
			$("#frameContent").contents().find(strP).html(
					"<font color='red'>空</font>");
			$("#frameContent").contents().find(str).css( {
				border : "#CC0000 1px solid"
			});
			bool = false;
		} else {
			$("#frameContent").contents().find(strP).html("");
			$("#frameContent").contents().find(str).css( {
				border : "#C0D1E2 1px solid"
			});
		}
	}
	return bool;
}

// 验证控件非空，验证不通过返回false。str为验证控件id (或id,id)
function validNoEmpty(str) {
	var bool = true;
	var arrayObj = str.split(",");
	for ( var i = 0; i < arrayObj.length; i++) {
		var str = "#" + arrayObj[i];
		var strP = "#" + arrayObj[i] + "P";

		var obj = $(str).val();
		if ($.trim(obj).length == 0) {
			$(strP).html("<font color='red'>空</font>");
			$(str).css( {
				border : "#CC0000 1px solid"
			});
			bool = false;
		} else {
			$(strP).html("");
			$(str).css( {
				border : "#C0D1E2 1px solid"
			});
		}
	}
	return bool;
}

// 验证是否为ip
function validIsIp(str){
	var bool = true;
	var arrayObj = str.split(",");
	for ( var j = 0; j < arrayObj.length; j++) {
		var str = "#" + arrayObj[j];
		var strP = "#" + arrayObj[j] + "P";
		var o = $("#frameContent").contents().find(str).val();
		
		if(o!=''){
		   var arr=o.split("\n");  
		   var regip= /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/;
		   for (var i=0;i<arr.length ;i++ ){ 
		      flag_ip=regip.test(arr[i]);
		      if(!flag_ip){
		         $("#frameContent").contents().find(strP).html("<font color='red'>格式错误</font>");
				 $("#frameContent").contents().find(str).css( {border : "#CC0000 1px solid"});
		         bool = false;
		      }else {
			  	 $("#frameContent").contents().find(strP).html("");
			     $("#frameContent").contents().find(str).css( {border : "#C0D1E2 1px solid" });
		      }
		   }
		}
	}
	return bool;
}

//验证MAC地址
function validMac(inputIds){
	var bool = true;
	var arrayObj = inputIds.split(",");
	for ( var i = 0; i < arrayObj.length; i++) {
		var str = "#" + arrayObj[i];
		var strP = "#" + arrayObj[i] + "P";
		
		var obj = trim($(str).val());
		if(obj){
			//验证手机号码格式
			var r=/[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}/; 
			if (!r.test(obj)) {
				$(strP).html("<font color='red'>格式例：00:24:21:19:BD:E4</font>");
				$(str).css({border : "#CC0000 1px solid"});
				bool = false;
			} else {
				$(strP).html("");
				$(str).css({border : "#c0d1e2 1px solid"});
			}
		}else {
			$(strP).html("");
			$(str).css({border : "#c0d1e2 1px solid"});
		}
		$(str).val(obj);
	}
	return bool;
}

// 验证输入是否为数字
function valiIsNaN(str){
	var bool = true;
	var arrayObj = str.split(",");
	for ( var j = 0; j < arrayObj.length; j++) {
		var str = "#" + arrayObj[j];
		var strP = "#" + arrayObj[j] + "P";
		var o = $("#frameContent").contents().find(str).val();
		
		if(!isNaN(o)){//数字 
			$("#frameContent").contents().find(strP).html("");
	     	$("#frameContent").contents().find(str).css( {border : "#C0D1E2 1px solid" });
		} else{//非数字 
			$("#frameContent").contents().find(strP).html("<font color='red'>格式错误</font>");
		 	$("#frameContent").contents().find(str).css( {border : "#CC0000 1px solid"});
		 	bool = false;
		} 
	}
	return bool;
}



//=============================身份证号唯一验证=================================//
	/**  
	 * 身份证15位编码规则：dddddd yymmdd xx p   
	 * dddddd：地区码   
	 * yymmdd: 出生年月日   
	 * xx: 顺序类编码，无法确定   
	 * p: 性别，奇数为男，偶数为女  
	 * <p />  
	 * 身份证18位编码规则：dddddd yyyymmdd xxx y   
	 * dddddd：地区码   
	 * yyyymmdd: 出生年月日   
	 * xxx:顺序类编码，无法确定，奇数为男，偶数为女   
	 * y: 校验码，该位数值可通过前17位计算获得  
	 * <p />  
	 * 18位号码加权因子为(从右到左) Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2,1 ]  
	 * 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]   
	 * 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )   
	 * i为身份证号码从右往左数的 2...18 位; Y_P为脚丫校验码所在校验码数组位置  
	 *   
	 */  
	  
	var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子   
	var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X   
	function IdCardValidate(idCard) {   
	    idCard = trim(idCard.replace(/ /g, ""));   
	    if (idCard.length == 15) {   
	        return isValidityBrithBy15IdCard(idCard);   
	    } else if (idCard.length == 18) {   
	        var a_idCard = idCard.split("");// 得到身份证数组   
	        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   
	            return true;   
	        }else {   
	            return false;   
	        }   
	    } else {   
	        return false;   
	    }   
	}   
	/**  
	 * 判断身份证号码为18位时最后的验证位是否正确  
	 * @param a_idCard 身份证号码数组  
	 * @return  
	 */  
	function isTrueValidateCodeBy18IdCard(a_idCard) {   
	    var sum = 0; // 声明加权求和变量   
	    if (a_idCard[17].toLowerCase() == 'x') {   
	        a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作   
	    }   
	    for ( var i = 0; i < 17; i++) {   
	        sum += Wi[i] * a_idCard[i];// 加权求和   
	    }   
	    valCodePosition = sum % 11;// 得到验证码所位置   
	    if (a_idCard[17] == ValideCode[valCodePosition]) {   
	        return true;   
	    } else {   
	        return false;   
	    }   
	}   
	/**  
	 * 通过身份证判断是男是女  
	 * @param idCard 15/18位身份证号码   
	 * @return 'female'-女、'male'-男  
	 */  
	function maleOrFemalByIdCard(idCard){   
	    idCard = trim(idCard.replace(/ /g, ""));// 对身份证号码做处理。包括字符间有空格。   
	    if(idCard.length==15){   
	        if(idCard.substring(14,15)%2==0){   
	            return 'female';   
	        }else{   
	            return 'male';   
	        }   
	    }else if(idCard.length ==18){   
	        if(idCard.substring(14,17)%2==0){   
	            return 'female';   
	        }else{   
	            return 'male';   
	        }   
	    }else{   
	        return null;   
	    }
	}   
	 /**  
	  * 验证18位数身份证号码中的生日是否是有效生日  
	  * @param idCard 18位书身份证字符串  
	  * @return  
	  */  
	function isValidityBrithBy18IdCard(idCard18){   
	    var year =  idCard18.substring(6,10);   
	    var month = idCard18.substring(10,12);   
	    var day = idCard18.substring(12,14);   
	    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
	    // 这里用getFullYear()获取年份，避免千年虫问题   
	    if(temp_date.getFullYear()!=parseFloat(year)   
	          ||temp_date.getMonth()!=parseFloat(month)-1   
	          ||temp_date.getDate()!=parseFloat(day)){   
	            return false;   
	    }else{   
	        return true;   
	    }   
	}   
	  /**  
	   * 验证15位数身份证号码中的生日是否是有效生日  
	   * @param idCard15 15位书身份证字符串  
	   * @return  
	   */  
	  function isValidityBrithBy15IdCard(idCard15){   
	      var year =  idCard15.substring(6,8);   
	      var month = idCard15.substring(8,10);   
	      var day = idCard15.substring(10,12);   
	      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
	      // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
	      if(temp_date.getYear()!=parseFloat(year)   
	              ||temp_date.getMonth()!=parseFloat(month)-1   
	              ||temp_date.getDate()!=parseFloat(day)){   
	                return false;   
	        }else{   
	            return true;   
	        }   
	  }   
	//去掉字符串头尾空格   
	function trim(str) {   
	    return str.replace(/(^\s*)|(\s*$)/g, "");   
	}
	
//验证
function validIdCardNo(pkId){
	var idCardNo = $("#idCardNo").val();
	if($.trim(idCardNo).length == 0) {
		$("#idCardNoP").html("");
		$("#idCardNoAlert").html("");
		$("#idCardNo").css({border:"#C0D1E2 1px solid"});
	} else {
		$("#idCardNoP").html("");
		if(IdCardValidate(idCardNo)==false) {
			$("#idCardNoAlert").html("<font color='red'>格式错</font>");
			$("#idCardNo").css({border:"#666666 1px solid"});
		} else {
			$("#idCardNoAlert").html("");
	    	$("#idCardNo").css({border:"#C0D1E2 1px solid"});

	    	$.ajax({
				  url: "/web/dna/repeatIdCardNo.do",
				  type: "POST",
				  datatype:'json',
				  data: {idCardNo : idCardNo, pkId : pkId},
				  success: function(opt){
						var rows = opt.rows;
						if(rows==null) {
							$("#idCardNoAlert").html("");
						} else {
							var tableHead = "<table border=0 style='color:red;border-collapse:collapse;border:none;'>";
							var tableFirstRow = "<tr style='color:#000066' align='center'><td style='border:solid #000 1px;'><b>实验室编号</b></td><td style='border:solid #000 1px;'><b>库别</b></td><td style='border:solid #000 1px;'><b>人员系统编号</b></td><td style='border:solid #000 1px;'><b>人员姓名</b></td><td style='border:solid #000 1px;'><b>试剂盒</b></td><td style='border:solid #000 1px;'><b>录入位点数</b></td></tr>";;
							var tableRow = "";
							for(var i=0;i<rows.length;i++) {
								tableRow += "<tr><td style='border:solid #000 1px;'>"+rows[i].sampleLabNo+"</td><td style='border:solid #000 1px;'>"+rows[i].sampleTypeName+"</td><td style='border:solid #000 1px;'>"+rows[i].personnelNo+"</td><td style='border:solid #000 1px;'>"+rows[i].personnelName+"</td><td style='border:solid #000 1px;'>"+rows[i].reagentKit+"</td><td style='border:solid #000 1px;' align='center'>"+rows[i].alleleCount+"</td></tr>";
							}
							var tableBottom = "</table>";
							$("#idCardNoP").append(tableHead+tableFirstRow+tableRow+tableBottom);
						}
				  }
			});
		}
	}
}
//=================================身份证号唯一验证end==============================//

function textAreaLength(field, maxlimit) {
	if(field.value.length > maxlimit) {
		field.value = field.value.substring(0, maxlimit);
	}
}
//验证是否为有效数值
function validDouble(inputIds){
	var bool = true;
	var arrayObj = inputIds.split(",");
	for ( var i = 0; i < arrayObj.length; i++) {
		var str = "#" + arrayObj[i];
		var strP = "#" + arrayObj[i] + "P";

		var obj = $(str).val();
		if(obj){
			if (isNaN(obj)) {
				$(strP).html("<font color='red'>格式错误</font>");
				$(str).css({border : "#CC0000 1px solid"});
				bool = false;
			} else {
				$(strP).html("");
				$(str).css({border : "#c0d1e2 1px solid"});
			}
		}
	}
	return bool;
}

//验证是否为有效整数
function validInteger(inputIds){
	var bool = true;
	var arrayObj = inputIds.split(",");
	for ( var i = 0; i < arrayObj.length; i++) {
		var str = "#" + arrayObj[i];
		var strP = "#" + arrayObj[i] + "P";
		
		var obj = $(str).val();
		if(obj){
			if (!isNumber(obj)) {
				$(strP).html("<font color='red'>格式错误</font>");
				$(str).css({border : "#CC0000 1px solid"});
				bool = false;
			} else {
				$(strP).html("");
				$(str).css({border : "#c0d1e2 1px solid"});
			}
		}
	}
	return bool;
}

//验证是否为正整数
function isNumber( s ){   
	var regu = "^[0-9]+$"; 
	var re = new RegExp(regu); 
	if (s.search(re) != -1) { 
		return true; 
	} else { 
		return false; 
	} 
}

//验证是否为数字
function isNaNs(inputIds){
	var bool = true;
	var arrayObj = inputIds.split(",");
	for ( var i = 0; i < arrayObj.length; i++) {
		var str = "#" + arrayObj[i];
		var strP = "#" + arrayObj[i] + "P";
		
		var obj = $(str).val();
		if(obj){
			if (isNaN(obj)) {
				$(strP).html("<font color='red'>格式错误</font>");
				$(str).css({border : "#CC0000 1px solid"});
				bool = false;
			} else {
				$(strP).html("");
				$(str).css({border : "#c0d1e2 1px solid"});
			}
		}
	}
	return bool;
}
