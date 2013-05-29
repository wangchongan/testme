//生成标签页
var tab = new Ext.TabPanel({
			region:'center',
			deferredRender:false,
			activeTab:0,
			resizeTabs:true, // turn on tab resizing
			minTabWidth: 115,
			tabWidth:135,
			enableTabScroll:true
		});

Ext.onReady(function(){
   //layout
   var viewport = new Ext.Viewport({
		layout:'border',
		items:[{
				region:'north',
				el: 'north',
				height:55
			},{
				region:'south',
				el: 'south',
				height:25
				},{
				region:'west',
				id:'west-panel',
				split:true,
				width: 200,
				minSize: 175,
				maxSize: 400,
				margins:'0 0 0 0',
				layout:'accordion',
				collapsible :true,
				layoutConfig:{ animate:true },
			    items: [
				    {
						title:'配置管理',
						border:false,
						html:'<div id="configManage" style="overflow:auto;width:100%;height:100%"></div>'
						//iconCls:'nav'
				    },{
						title:'测试单元管理',
						border:false,
						//iconCls:'settings',
						html:'<div id="testUnitManage" style="overflow:auto;width:100%;height:100%"></div>'
				    },{
						title:'测试流程管理',
						border:false,
						//iconCls:'settings',
						html:'<div id="testFlowManage" style="overflow:auto;width:100%;height:100%"></div>'
				    },{
						title:'任务管理',
						border:false,
						//iconCls:'settings',
						html:'<div id="taskManage" style="overflow:auto;width:100%;height:100%"></div>'
				    }]
				},
	        tab//初始标签页
		 ]
	});
	
	 //tree1
   var root1=new Ext.tree.TreePanel({
		    id:"root1",
		    text:"配置管理"
		});

	 var c11=new Ext.tree.TreePanel({
		id:'c11',
		icon:'res/extjs/images/img/im2.gif',
		text:'系统配置',
		listeners:{    
        'click':function(node, event) {    
            event.stopEvent();    
            var n = tab.getComponent(node.id);    
            if (!n) { //判断是否已经打开该面板    
                 n = tab.add({    
                    'id':node.id,    
                    'title':node.text,    
                     closable:true,  //通过html载入目标页    
                     html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="ok.vm"></iframe>'   
                 });    
             }    
             tab.setActiveTab(n);    
         }    
		}
	});

    var c12=new Ext.tree.TreePanel({
		id:'c12',
		icon:'res/extjs/images/img/im2.gif',
		text:'查询配置',
		listeners:{    
        'click':function(node, event) {    
            event.stopEvent();    
            var n = tab.getComponent(node.id);    
            if (!n) { //判断是否已经打开该面板    
                 n = tab.add({    
                    'id':node.id,    
                    'title':node.text,    
                     closable:true,  //通过html载入目标页    
                     html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="ok.vm"></iframe>'   
                 });    
             }    
             tab.setActiveTab(n);    
         }    
		}
	});
	  var c13=new Ext.tree.TreePanel({
		id:'c13',
		icon:'res/extjs/images/img/im2.gif',
		text:'导入配置',
		listeners:{    
        'click':function(node, event) {    
            event.stopEvent();    
            var n = tab.getComponent(node.id);    
            if (!n) { //判断是否已经打开该面板    
                 n = tab.add({    
                    'id':node.id,    
                    'title':node.text,    
                     closable:true,  //通过html载入目标页    
                     html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="ok.vm"></iframe>'   
                 });    
             }    
             tab.setActiveTab(n);    
         }    
		}
	});
	 
	  var c14=new Ext.tree.TreePanel({
		id:'c44',
		icon:'res/extjs/images/img/im2.gif',
		text:'自定义配置',
		listeners:{    
        'click':function(node, event) {    
            event.stopEvent();    
            var n = tab.getComponent(node.id);    
            if (!n) { //判断是否已经打开该面板    
                 n = tab.add({    
                    'id':node.id,    
                    'title':node.text,    
                     closable:true,  //通过html载入目标页    
                     html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="ok.vm"></iframe>'   
                 });    
             }    
             tab.setActiveTab(n);    
         }    
		}
	});

	//tree2
    var root2=new Ext.tree.TreePanel({
		    id:"root2",
		    text:"测试单元管理"
		});
    var c21=new Ext.tree.TreePanel({
		id:'c21',
		icon:'res/extjs/images/img/im2.gif',
		text:'查询测试单元',
		listeners:{    
        'click':function(node, event) {    
            event.stopEvent();    
            var n = tab.getComponent(node.id);    
            if (!n) { //判断是否已经打开该面板    
                 n = tab.add({    
                    'id':node.id,    
                    'title':node.text,    
                     closable:true,  //通过html载入目标页    
                     html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="ok.vm"></iframe>'   
                 });    
             }    
             tab.setActiveTab(n);    
         }    
		}
	});
	var c22=new Ext.tree.TreePanel({
		id:'c22',
		icon:'res/extjs/images/img/im2.gif',
		text:'新增测试单元',
		listeners:{    
        'click':function(node, event) {    
            event.stopEvent();    
            var n = tab.getComponent(node.id);    
            if (!n) { //判断是否已经打开该面板    
                 n = tab.add({    
                    'id':node.id,    
                    'title':node.text,    
                     closable:true,  //通过html载入目标页    
                     html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="ok.vm"></iframe>'   
                 });    
             }    
             tab.setActiveTab(n);    
         }    
		}
	});

	//tree3
    var root3=new Ext.tree.TreePanel({
		    id:"root3",
		    text:"测试流程管理"
		});
    var c31=new Ext.tree.TreePanel({
		id:'c31',
		icon:'res/extjs/images/img/im2.gif',
		text:'查询测试流程',
		listeners:{    
        'click':function(node, event) {    
            event.stopEvent();    
            var n = tab.getComponent(node.id);    
            if (!n) { //判断是否已经打开该面板    
                 n = tab.add({    
                    'id':node.id,    
                    'title':node.text,    
                     closable:true,  //通过html载入目标页    
                     html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="ok.vm"></iframe>'   
                 });    
             }    
             tab.setActiveTab(n);    
         }    
		}
	});
	var c32=new Ext.tree.TreePanel({
		id:'c32',
		icon:'res/extjs/images/img/im2.gif',
		text:'新增测试流程',
		listeners:{    
        'click':function(node, event) {    
            event.stopEvent();    
            var n = tab.getComponent(node.id);    
            if (!n) { //判断是否已经打开该面板    
                 n = tab.add({    
                    'id':node.id,    
                    'title':node.text,    
                     closable:true,  //通过html载入目标页    
                     html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="ok.vm"></iframe>'   
                 });    
             }    
             tab.setActiveTab(n);    
         }    
		}
	});
	//tree4
    var root4=new Ext.tree.TreePanel({
		    id:"root4",
		    text:"任务管理"
		});
    var c41=new Ext.tree.TreePanel({
		id:'c41',
		icon:'res/extjs/images/img/im2.gif',
		text:'任务列表',
		listeners:{    
        'click':function(node, event) {    
            event.stopEvent();    
            var n = tab.getComponent(node.id);    
            if (!n) { //判断是否已经打开该面板    
                 n = tab.add({    
                    'id':node.id,    
                    'title':node.text,    
                     closable:true,  //通过html载入目标页    
                     html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="testTask"></iframe>'   
                 });    
             }    
             tab.setActiveTab(n);    
         }    
		}
	});
	var c42=new Ext.tree.TreePanel({
		id:'c42',
		icon:'res/extjs/images/img/im2.gif',
		text:'查询任务',
		listeners:{    
        'click':function(node, event) {    
            event.stopEvent();    
            var n = tab.getComponent(node.id);    
            if (!n) { //判断是否已经打开该面板    
                 n = tab.add({    
                    'id':node.id,    
                    'title':node.text,    
                     closable:true,  //通过html载入目标页    
                     html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="ok.vm"></iframe>'   
                 });    
             }    
             tab.setActiveTab(n);    
         }    
		}
	});

	var tree1=new Ext.tree.TreePanel({
		renderTo:"configManage",
		root:root1,    //对应 根节点
		animate:true,
		enableDD:false,
		border:false,
		rootVisible:false,
		containerScroll: true
	});

	var tree2=new Ext.tree.TreePanel({
		renderTo:"testUnitManage",
		root:root2,    //对应 根节点
		animate:true,
		enableDD:false,
		border:false,
		rootVisible:false,
		containerScroll: true
	});
	
	var tree3=new Ext.tree.TreePanel({
		renderTo:"testFlowManage",
		root:root3,    //对应 根节点
		animate:true,
		enableDD:false,
		border:false,
		rootVisible:false,
		containerScroll: true
	});
	var tree4=new Ext.tree.TreePanel({
		renderTo:"taskManage",
		root:root4,    //对应 根节点
		animate:true,
		enableDD:false,
		border:false,
		rootVisible:false,
		containerScroll: true
	});
	
	
	c11.setRootNode(tree1);
	c12.setRootNode(tree1);
	c13.setRootNode(tree1);
	c14.setRootNode(tree1);
	
	c21.setRootNode(root2);
	c22.setRootNode(root2);
	
	c31.setRootNode(root3);
	c32.setRootNode(root3);
	c41.setRootNode(root4);
	c42.setRootNode(root4);
	
});