
Ext.require([
    'Ext.form.field.ComboBox'
//--    'Ext.form.FieldSet',
//--    'Ext.tip.QuickTipManager',
//--    'Ext.data.*'
]);

Ext.require([
             'Ext.tree.*',
             'Ext.window.MessageBox'
//             'Ext.data.*'
//             'Ext.loader.*',
//             'Ext.tab.TabPanel'
//             'Ext.state.*'
            ]);
Ext.require(['Ext.tab.*']);
//Ext.Loader.setConfig({enabled:true});
//-- Ext.require(['*']);
//
//Ext.require([
//
//'Ext.form.*',
//'Ext.grid.*',
//'Ext.tree.*',
//'Ext.data.*',
//'Ext.util.*',
//'Ext.loader.*',
//'Ext.state.*',
////'Ext.layout.container.Column',
//'Ext.tab.TabPanel'
//]);

// Define the model for a DbType
Ext.define('DbType', {
    extend: 'Ext.data.Model',
    fields: [
        {type: 'string', name: 'id'},
        {type: 'string', name: 'name'},
    ]
});


var tree;
var treeStore;
var packageConfigPanel;
var packageConfigWindow;
var packageConfigBtn;
var rootPath = f_getRootPath();

// The data for all dbTypes
var dbTypes = [
    {"id":"ORACLE","name":"ORACLE"},
    {"id":"MYSQL","name":"MYSQL"}
];


function createStore() {
    return Ext.create('Ext.data.Store', {
        autoDestroy: true,
        model: 'DbType',
        data: dbTypes
    });
}

var dbTypeCombo;
Ext.onReady(function() {
	
	if (!Ext.isChrome && Ext.util.Cookies.get("EasyCodeBroswerCheck") != "Y"){
		Ext.MessageBox.alert('温馨提示', '内部应用，仅通过Chrome浏览器兼容性测试，建议你换成Chrome以免影响正常使用。');
		Ext.util.Cookies.set("EasyCodeBroswerCheck","Y");
	}
	
    Ext.tip.QuickTipManager.init();
    dbTypeCombo = Ext.create('Ext.form.field.ComboBox', {
    	inputId:'dbTypeCombo',
        fieldLabel: '数据库类型',
        valueField: 'id',
        renderTo: 'dbTypeCombo',
        displayField: 'name',
        width: '100%',
        labelWidth: 70,
        store: createStore(),
        queryMode: 'local',
        typeAhead: false,
        editable:false,
        emptyText:'请选择',
        listeners:{
        	select:{
        		fn:function(combo,record,index) {
        			tree.getStore().getProxy().url = 'autocode/getDbInfo.json?dbType=' + Ext.get("dbTypeCombo").dom.value;
        			treeStore.load();
        		}
        	}
        }
    });
//    dbTypeCombo.setValue("MYSQL");
});

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
var checkboxs = Ext.create('Ext.form.Panel', {
    bodyPadding: 0,
    width: '100%',
    title: '',
    bodyStyle:'background-color:#DDE7F4',
    border :false,
    renderTo: Ext.getBody(),
    xtype: 'checkboxgroup',
    defaultType: 'checkboxfield',
    layout:'column'
});

var packageNameHidden = new Ext.form.Hidden({
	name: 'packageName',
	hidden:true,
	value: ''
});

var packageNameLabel = new Ext.form.Label({
	x: 100,
	y:5,
	autoWidth : true,
	padding:'0 0 0 5',
	renderTo: Ext.getBody(),
	text:'包路径配置:'
});

//~~~~~~~~~~~~~~~~template group~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

var templateGroupCombo ;

Ext.onReady(function() {
	
    Ext.define("TemplateGroup", {
        extend: "Ext.data.Model",
        fields: [
            { name: "id" },
            { name: "name" }
        ]
    });

    var store = Ext.create("Ext.data.Store", {
        model: "TemplateGroup",
        proxy: {
            type: "ajax",
            url: "autocode/getTemplateGroup.json",
            reader: new Ext.data.JsonReader({ model: "TemplateGroup" })
        }
    });
 
	// Simple ComboBox using the data store
    templateGroupCombo = Ext.create('Ext.form.field.ComboBox', {
	    fieldLabel: '应用和模板',
	    renderTo: 'templateGroupCombo',
	    displayField: 'name',
	    width: 170,
	    labelWidth: 65,
	    store: store,
	    typeAhead: false,
	    editable:false,
	    padding:'0 0 0 5',
        name: 'TemplateGroup',
        triggerAction: 'all',
        displayField: 'name',
        valueField: 'id',
        queryMode: 'remote',
        emptyText:'请选择'
	});
    
    templateGroupCombo.on('change', function(field, newValue, oldValue, obj) {
    	 if (newValue == "" || newValue == undefined) {
    		 return;
    	 }
    	 
         Ext.Ajax.request({
     	    url: 'autocode/getTemplatesByGroupName.json',
     	    params: {
     	    	groupName : newValue
     	    },
     	    success: function(response){
     	        var text = response.responseText;
     	        var datas = Ext.decode(response.responseText);
     	        checkboxs.removeAll();
     	        checkboxs.add(datas);
     	        
     	    }
         });
 	});   
    
});

//~~~~~~~~~~~~button~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~ tree~~~~~~~~~~~~~~~~~~~~~~~*/



var seletedTablesValue;


         Ext.onReady(function() {
              treeStore = Ext.create('Ext.data.TreeStore', {
                 proxy: {
                     type: 'ajax',
                     timeout : 60000,
                     url: 'autocode/getDbInfo.json?dbType=' + dbTypeCombo.getValue()
                 },
                 nodeParam : "id",
                 sorters: [{
                     property: 'leaf',
                     direction: 'ASC'
                 }, {
                     property: 'text',
                     direction: 'ASC'
                 }]
             });

             tree = Ext.create('Ext.tree.Panel', {
                 store: treeStore,
                 rootVisible: false,
                 useArrows: true,
                 frame: true,
                 title: '数据库列表',
                 renderTo: 'tree-div',
                 width: '100%',
                 height: 700,
                 dockedItems: [{
                	 xtype: 'toolbar',
                     items: [
                             dbTypeCombo
                     ]
                 },{
                     xtype: 'toolbar',
                     items: [{
                         text: '全收起',
                         handler: function(){
                        	 tree.collapseAll();
                         }
                     },'-',{
                         text: '全不选',
                         handler: function(){
                        	 var records = tree.getView().getChecked();
            	             Ext.Array.each(records, function(rec){
            	            	 rec.set("checked",false); 
            	             });
                         }
                     }]
                 }] 
             });
             
             tree.on('checkchange', function(node, checked) {   
            	 var records = tree.getView().getChecked(),
                 names = [];
	             Ext.Array.each(records, function(rec){
	                 names.push(rec.get('text'));
	             });
          	 });   
          	   
         });
         
 
         
//~~~~~~~~~~~~~~~~~~~~~Layout~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         

         
         var vp;
         var tabs;
         
         Ext.onReady(function(){
        	  tabs = Ext.widget('tabpanel',{
            	 xtype: 'tabpanel',
                 region: 'center',
                 header:false,
                 animCollapse: false,
                 collapsible: false,
                 split: true,
                 width: '100%', // give east and west regions a width
                 minSize: 20,
                 maxSize: 100,
                 margins: '0 0 0 0',
                 activeTab: 0,
                 tabPosition: 'top',
                 items: []
             }); 
        	  
              var key = new Ext.KeyMap(document, {
           		key: 'A',  // Enter
           		//shift: true,
           		ctrl: true,
           		fn: function()
           		{
    				if (tabs.getActiveTab() == undefined){
						 Ext.MessageBox.alert('提示信息', '亲，当前没有需要复制的代码哦');
			    		 return;
					}
					
					var tabId = tabs.getActiveTab().getItemId();
					
	               	var codeContent = Ext.get(tabId + "-body").dom.innerText;
               	    if (Ext.isIE){
		        		window.clipboardData.setData("Text", codeContent);
		        	} else {
		        		var copyWin =  Ext.MessageBox.show({
		        			title: '当前浏览器不支持自动复制',
		        			msg: '请鼠标移进文本域，CTRL+C手动复制:<br/><br/><textarea  cols="60" rows="5" onmousemove="this.select();" id="copyTextarea" style="width:300px; word-wrap : normal; resize:none; ">' + codeContent + '</textarea>',
		        			width:320,
		        			buttons: Ext.MessageBox.OK,
		        			buttonText:{
		        				ok: "关闭" 
		        			}
		        		});
		        	}
           		},
           		scope: this,
           		defaultEventAction: "stopEvent"
           	});
         });
         

         
         Ext.onReady(function() {
             
             vp =  Ext.create('Ext.Viewport', {
                 layout: {
                     type: 'border',
                     padding: 5
                 },
                 defaults: {
                     split: true
                 },
                 items: [{
                     region: 'north',
                     collapsible: false,
                     title: '',
                     split: true,
                     height: 35,
                     minHeight: 35,
                     bodyStyle:'background-color:#DDE7F4',
                     border:false,
                     html: '<span style=\"color:#03336F;\"><a href="' + f_getRootPath() + '" style="text-decoration: none" title="点击进入首页"><span style=\"font-weight:bold;font-size:25px;\">Auto Code</span> | <span style=\"font-size:13px;\">良无限代码生成工具</span></a> | <a href="' + f_getRootPath() + '" style="color:red;">更多工具点这里</a></span>'
                 },{
                     region: 'west',
                     layout: 'fit',
                     split: true,
                     collapsible: true,
                     header:false,
                     width: '18%',
                     items:[
		                    tree
                     ]
                 },{
                     region: 'center',
                     layout: 'border',
                     border: false,
                     resizable:false,
                     title: '',
                     items: [{
                    	 region: 'north',
                    	 html: '',
                    	 title: '执行台',
                    	 minHeight: 110,
                    	 dockedItems: [{
                             xtype: 'toolbar',
                             dock: 'top',
                             minHeight: 30,
                             items: [
									templateGroupCombo,
									'-',checkboxs
                             ]
                         },{
                             xtype: 'toolbar',
                             dock: 'top',
                             minHeight: 30,
                             items: [	
                                 	packageNameLabel,
                                 	packageConfigBtn
                             ]
                         },{
                             xtype: 'toolbar',
                             dock: 'top',
                             minHeight: 30,
                             items: [
                                {  
								    text: '执行生成代码',
								    iconCls:'execute2-button',
								    handler: function(){
								    	
								    	 //选择的表
								    	 var records = tree.getView().getChecked();
								    	 
								    	 if (records == undefined || records.length == 0) {
								    		 Ext.MessageBox.alert('提示信息', '亲，请先在左边菜单列表中选择要处理的数据库表');
								    		 return;
								    	 }
								    	 
								    	 if (records.length > 2){
								    		 Ext.MessageBox.alert('提示信息', '亲，别太贪啦，每次最多支持两个表哦。');
								    		 return;
								    	 }
								    	 
								    	 var templateCount = 0;
								    	 var templateRecords = "";
								    	 var packageNames =packageNameHidden.getValue();
								    	 checkboxs.items.each(function(item,index,length){                             
								    	       if (item.getValue() == true) {
								    	    	   templateCount++;
								    	    	   templateRecords = templateRecords + item.getId() + ",";
								    	       }
								    	 });  
								    	 
								    	 if (templateCount == 0) {
								    		 Ext.MessageBox.alert('提示信息', '请选择应用和模板');
								    		 return;
								    	 }
								    	 
							    	     var tableRecords = "";
							             Ext.Array.each(records, function(rec){
							            	 tableRecords = tableRecords + rec.get('id') + ",";
							             });
							             
							             Ext.Ajax.request({
							          	    url: 'autocode/getTabsInfo.json',
							          	    params: {
							          	    	tableRecords : tableRecords,
							          	    	templateRecords : templateRecords,
							          	    	packageNames:packageNames
							          	    },
							          	    success: function(response){
							          	        var datas = Ext.decode(response.responseText);
										    	//移除已有tab
							          	        tabs.removeAll();
							          	        tabs.add(datas);
							          	        //激活第一个tab
							          	        tabs.setActiveTab(0);
							          	    }
							          	 });
								    }
								}
								,'-' ,{
									text: '下载打包文件',
									iconCls: 'download-button',
									handler: function(){
										//选择的表
								    	 var records = tree.getView().getChecked();    	 
								    	 if (records == undefined || records.length == 0) {
								    		 Ext.MessageBox.alert('提示信息', '亲，请先在左边菜单列表中选择要处理的数据库表');
								    		 return;
								    	 }
								    	 if (records.length > 2){
								    		 Ext.MessageBox.alert('提示信息', '亲，别太贪啦，每次最多支持两个表哦。');
								    		 return;
								    	 }
								    	 var templateCount = 0;
								    	 var templateRecords = "";
								    	 var packageNames =packageNameHidden.getValue();
								    	 checkboxs.items.each(function(item,index,length){                             
								    	       if (item.getValue() == true) {
								    	    	   templateCount++;
								    	    	   templateRecords = templateRecords + item.getId() + ",";
								    	       }
								    	 });  
								    	 
								    	 if (templateCount == 0) {
								    		 Ext.MessageBox.alert('提示信息', '请选择应用和模板');
								    		 return;
								    	 }
								    	 
							    	     var tableRecords = "";
							             Ext.Array.each(records, function(rec){
							            	 tableRecords = tableRecords + rec.get('id') + ",";
							             });
							             
							             Ext.Ajax.request({
							          	    url: 'autocode/downLoadAutoCode.json',
							          	    params: {
							          	    	tableRecords : tableRecords,
							          	    	templateRecords : templateRecords,
							          	    	packageNames:packageNames
							          	    },
							          	    success: function(response){
							          	       var obj = response.responseText;
							          	       window.open(rootPath+obj);  
							          	    }
							          	 });
									}
								}
								, '-' ,{
									text: '复制当前代码',
									iconCls: 'copy-button',
									handler: function(){
										
										if (tabs.getActiveTab() == undefined){
											 Ext.MessageBox.alert('提示信息', '亲，当前没有需要复制的代码哦');
								    		 return;
										}
										
										var tabId = tabs.getActiveTab().getItemId();
										
			                        	var codeContent = Ext.get(tabId + "-body").dom.innerText;
			                        	if (Ext.isIE){
							        		window.clipboardData.setData("Text", codeContent);
							        	} else {
							        		var copyWin =  Ext.MessageBox.show({
							        			title: '当前浏览器不支持自动复制',
							        			msg: '请鼠标移进文本域，CTRL+C手动复制:<br/><br/><textarea  cols="60" rows="5" onmousemove="this.select();" id="copyTextarea" style="width:300px; word-wrap : normal; resize:none; ">' + codeContent + '</textarea>',
							        			width:320,
							        			buttons: Ext.MessageBox.OK,
							        			buttonText:{
							        				ok: "关闭" 
							        			}
							        		});
							        	}
									}
									
								},'-',{
									text: '清空代码框',
									iconCls: 'close-button',
									handler: function(){
										tabs.items.each(function(item){
					                        if(item.closable){
					                            tabs.remove(item);
					                        }
					                    });
									}
								}
                             ]
                         }]
                     },tabs]
                 }]
             });
         });
         


         
//~~~~~~~~~~~~~~~~~~~Ajax get datas~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         
         
			 packageConfigPanel = new Ext.form.FormPanel({  
		        width: 360,  
		        height: 250,  
		        plain: true,  
		        layout: "form",  
		        defaultType: "textfield",  
		        labelWidth: 150,  
		        baseCls: "x-plain",  
		        //锚点布局-  
		        buttonAlign: "center",  
		        bodyStyle: "padding:0 0 0 0",  
		        items:  
                    [  
                    {  
                        fieldLabel: "DO包路径",
                        labelAlign:'right',
                        width: 160,  
                        id: "packageOfDO",  
                        name: "packageOfDO",  
                        maxLength: 160,  
                        maxLengthText: 'DO包路径长度不能超过160位！',  
                        allowBlank: false,  
                        blankText: "请输入DO包路径！"  
                    },{  
                        fieldLabel: "Sqlmap包路径", 
                        labelAlign:'right',
                        width: 160,  
                        id: "packageOfSqlmap",  
                        name: "packageOfSqlmap",  
                        maxLength: 160,  
                        maxLengthText: 'Sqlmap包路径长度不能超过160位！',  
                        allowBlank: false,  
                        blankText: "请输入Sqlmap包路径！"  
                    },{  
                        fieldLabel: "SpringBean配置包路径", 
                        labelAlign:'right',
                        width: 160,  
                        id: "packageOfSpring",  
                        name: "packageOfSpring",  
                        maxLength: 160,  
                        maxLengthText: 'SpringBean配置包路径长度不能超过160位！',  
                        allowBlank: false,  
                        blankText: "请输入SpringBean配置包路径！"  
                    },{  
                        fieldLabel: "DAO接口包路径", 
                        labelAlign:'right',
                        width: 160,  
                        id: "packageOfDAO",  
                        name: "packageOfDAO",  
                        maxLength: 160,  
                        maxLengthText: 'DAO接口包路径长度不能超过160位！',  
                        allowBlank: false,  
                        blankText: "请输入DAO接口包路径！"  
                    },{  
                        fieldLabel: "DAOImpl包路径", 
                        labelAlign:'right',
                        width: 160,  
                        id: "packageOfDAOImpl",  
                        name: "packageOfDAOImpl",  
                        maxLength: 160,  
                        maxLengthText: 'DAOImpl包路径长度不能超过160位！',  
                        allowBlank: false,  
                        blankText: "请输入DAOImpl包路径！"  
                    } ,{  
                        fieldLabel: "Service接口包路径", 
                        labelAlign:'right',
                        width: 160,  
                        id: "packageOfService",  
                        name: "packageOfService",
                        maxLength: 160,  
                        maxLengthText: 'Service接口包路径长度不能超过160位！',  
                        allowBlank: false,  
                        blankText: "请输入Service接口包路径！"  
                    },{  
                        fieldLabel: "ServiceImpl包路径",  
                        labelAlign:'right',
                        width: 160,  
                        id: "packageOfServiceImpl",  
                        name: "packageOfServiceImpl",  
                        maxLength: 160,  
                        maxLengthText: 'ServiceImpl包路径长度不能超过160位！',  
                        allowBlank: false,  
                        blankText: "请输入ServiceImpl包路径！"  
                    }    

                    ]  
		    });
			
			 packageConfigWindow = new Ext.Window({  
		        title: "添加包路径",  
		        width: 450,  
		        height: 300,  
		        plain: true, 
		        //不可以随意改变大小  
		        resizable: true,  
		        //是否可以拖动  
		        //draggable:false,  
		        collapsible: true, //允许缩放条  
		        closeAction: 'close',  
		        closable: true,  
		        //弹出模态窗体  
		        modal: 'true',  
		        buttonAlign: "center",  
		        items: [packageConfigPanel],  
		        buttons: [
		            {  
			            text: "确 定",  
			            minWidth: 70,  
			            handler: function() {  
			                if (packageConfigPanel.getForm().isValid()) { 
			                	var packageOfDO = packageConfigPanel.getForm().findField('packageOfDO').getValue();
		                     	var packageOfSqlmap = packageConfigPanel.getForm().findField('packageOfSqlmap').value;
		                     	var packageOfSpring = packageConfigPanel.getForm().findField('packageOfSpring').value;
		                     	var packageOfDAO = packageConfigPanel.getForm().findField('packageOfDAO').value;
		                     	var packageOfDAOImpl = packageConfigPanel.getForm().findField('packageOfDAOImpl').value;
		                     	var packageOfService = packageConfigPanel.getForm().findField('packageOfService').value;
		                     	var packageOfServiceImpl = packageConfigPanel.getForm().findField('packageOfServiceImpl').value;
								var packageName = "DO:"+packageOfDO+"##sqlmap:"+
								packageOfSqlmap+"##spring:"+packageOfSpring+"##dao:"+
								packageOfDAO+"##daoImpl:"+packageOfDAOImpl+"##service:"+
								packageOfService+"##serviceImpl:"+packageOfServiceImpl;
								packageNameHidden.setValue(packageName);
								packageConfigWindow.close();
								packageNameLabel.setVisible(true);
		                    }else {  
                              msg('信息提示', '添加时出现异常！');  
                            }  
		                        
			             } 
			        }, 
			        {  
			            text: "取 消",  
			            minWidth: 70,  
			            handler: function() {  
			                packageConfigWindow.close();  
			            }  
			        }]  
		  
		        });
			 
			 packageConfigBtn = new Ext.Button({
				 text:'<font color="red">设&nbsp;置</font>',
				 minWidth: 50,
				 xtype:'tbbutton',
				 renderTo:Ext.getBody(),
		         handler: function() {  
		        	 packageConfigWindow.show();
		         }
				 
			 });
         
         
        