function delConfirm(){
	Ext.MessageBox.confirm("请确认","确认删除该系统么?");
}
function addSystem(){
	Ext.MessageBox.show({
        title:"新增系统",
        msg:"输入系统名称",
        buttons:{"ok":"确定"},
        fn:function(e,text){
    		if (e=="ok" && text != '') {
    			Ext.getCmp('grid').doLayout();
    		} else if(e=="ok" && text == '') {
    			Ext.MessageBox.alert("新增失败","未输入系统名称");
    		}
		},
        width:200,
        icon:Ext.MessageBox.INFO,
        multiline:true
   });

    Ext.MessageBox.prompt("新增系统", "输入系统名称",function (e,text){ 
		if (e=="ok" && text != '') {
			Ext.getCmp('grid').doLayout();
		} else if(e=="ok" && text == '') {
			Ext.MessageBox.alert("新增失败","未输入系统名称");
		}

});
}

	Ext.onReady(function() {
		var cloumns = new Ext.grid.ColumnModel([               
                    {
					header : "系统名称",
					width : 200,
					sortable : true,
					dataIndex : 'name'
				}, {
					header : "备注",
					width : 250,
					sortable : true,
					dataIndex : 'remark'
				}, {
					header : "操作",
					width : 200,
					sortable : true,
					dataIndex : 'operation',
					renderer : function() {
						return "<a href='#' onclick='delConfirm()'>删除</a>";
					}
				}
 				]);                           

		//构造数据 
		var myData = [['LP4PL','良物流系统'],['LPSCM','良品SCM系统']];

		var ds = new Ext.data.Store( {
			proxy : new Ext.data.MemoryProxy(myData),
			reader : new Ext.data.ArrayReader( {}, [ {
				name : 'name',
				mapping : 0
			}, {
				name : 'remark',
				mapping : 1
			}])
		});

		ds.load();
		var grid = new Ext.grid.GridPanel( {
			el : 'grid',
			ds : ds,
			stripeRows : true,
			width : 660,
			height : 400,
			cm : cloumns,
			title : '系统列表',
			tbar : [ {
				text : "新增系统",
                tooltip : "hi",
				handler : function() {
					addSystem();
				}
			}]
		});
		grid.render();
	});