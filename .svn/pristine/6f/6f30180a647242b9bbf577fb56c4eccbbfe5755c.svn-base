var pageSize = 20;//datagride每页显示的条数

//添加tab
function addTab(url,title,tabId,scroll,callback) {
	if(!tabId){
		tabId = 'tt';
	}
	if(!scroll){//如果不指定滚动条，默认没有滚动条
		scroll = 'no';
	}
	var content = "<iframe id='tabIframe' scrolling='"+scroll+"' frameborder='0' src='"+url+"' style='width:1200px;height:600px;'></iframe>";
	var exist = $('#'+tabId).tabs('exists',title);
	if(exist){
		$('#'+tabId).tabs('select',title);
		if (callback) {
			callback();
		}
	}else{
		$('#'+tabId).tabs('add',{
			title:title,
			width:document.body.clientWidth-14,
			height:document.body.clientHeight,
			content:content,
			closable:true
		});
	}
}

//刷新tab页面内容
function updateTabContext(title,tabId){
	if(!tabId){
		tabId = 'tt';
	}
	var tab = $('#'+tabId).tabs('getSelected');
	$('#'+tabId).tabs('update', {
		tab: tab,
		options: {
			title: title
		}
	});
}

//将当前选中的tab替换为新的
function replaceSelectedTab(url,title,tabId) {
	if(!tabId){
		tabId = 'tt';
	}
	//关闭当前选中的Tab
	var pp = $('#'+tabId).tabs('getSelected');
	$('#'+tabId).tabs('close', pp.panel('options').title);
	var content = "<iframe id='iframe' scrolling='no' frameborder='0' src='"+url+"' style='width:100%;height:100%;'></iframe>";
	$('#'+tabId).tabs('add',{
		title:title,
		width:document.body.clientWidth-14,
		height:document.body.clientHeight,
		content:content,
		closable:true
	});
}
//刷新当前tab的title
function updTabTitle(title, tabId) {
	if(!tabId){
		tabId = 'tt';
	}
	var pp = $('#'+tabId).tabs('getSelected');
	var tab = pp.panel('options').tab;
	$(tab).find("span.tabs-title").html(title);
	pp.panel('options').title=title;
	 
}
//刷新列表
function datagridReload(listId){
	if(!listId){
		listId = 'list';
	}
	$('#'+listId).datagrid('reload');
}
//关闭当前选中的Tab
function closeSelectedTab(tabId){
	if(!tabId){
		tabId = 'tt';
	}
	var pp = $('#'+tabId).tabs('getSelected');
	$('#'+tabId).tabs('close', pp.panel('options').title);
}