Ext.define('Wj.view.admin.DisaReportMgr', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.admindisareportmgr',

	requires: ['Wj.view.util.Pagingtoolbar',
				'Ext.ux.exporter.Exporter'
			  ],

	closable: true,
	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '疫情报告',

	initComponent: function(){
	Ext.Loader.setConfig({ enabled: true }); 
	Ext.Loader.setPath('Ext.ux.exporter', '../webapp/extjs/src/ux/exporter'); 
		console.log('-- Wj.view.admin.DisaReportMgr init.--');

		this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			store: 'disareport.DisaReport',
			autoScroll: true,
			id:'grid_disareport',
			itemId:'grid_disareport',
			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',

				items: [{
					text: '添加',
					icon: 'icon/add.png',
					itemId: 'add',
					handler: Wj.controller.Admin.addDisaReport
				}, {
					text: '删除',
					icon: 'icon/delete.gif',
					itemId: 'remove',
					disabled: true,
					handler: function(){
						alert('remove!');
					},
					handler: Wj.controller.Admin.removeDisaReport
				},
				
				Ext.create('Ext.ux.exporter.Button',{
			          //component: Ext.getCmp('grid_disareport'),
			          component:Ext.ComponentQuery.query('#grid_disareport')[0],
                      text: 'Excel',
                      downloadName: "疫情数据"
                      
			})
				]

				
				
			}, {
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'disareport.DisaReport',
				displayMsg: '- 双击进行修改 - 当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}
			],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				flex: 0.6,
				minWidth: 40,
				allowBlank: false,
				blankText: '编号不能为空',
				hidden: true, 
				hideLabel:true ,
			}, {
				text: '疫情名称',
				flex: 1,
				minWidth: 100,
				dataIndex: 'name',
				allowBlank: false,
				blankText: '病人姓名不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			},
			 {
				text: '受灾人数',
				flex: 1,
				minWidth: 80,
				dataIndex: 'number',
				allowBlank: false,
				blankText: '疫情人数不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}, {
				text: '疫情位置',
				flex: 1,
				minWidth: 150,
				dataIndex: 'zone',
				allowBlank: false,
				blankText: '疫情位置不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			},{				
				text: '发生时间',
				flex: 1,
				minWidth: 160,
				dataIndex: 'time',
				format: 'Y/m/d H:i:s',
				allowBlank: false,
				blankText: '发生时间不能为空',			
			},{
				text: '处治办法',
				flex: 1,
				minWidth: 200,
				dataIndex: 'proc',
				allowBlank: false,
				blankText: '疫情程度不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,50}$/,
					regexText:'请输入50个以内字符'
				}
			},
			{
				text: '疫情程度',
				flex: 1,
				minWidth: 200,
				dataIndex: 'degree',
				allowBlank: false,
				blankText: '疫情程度不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,50}$/,
					regexText:'请输入50个以内字符'
				}
			}
			
			],

			plugins: [
				Ext.create('Ext.grid.plugin.RowEditing', {
					clicksToEdit: 2,
					clicksToMoveEdit: 1,
					autoCancel: false
				})
			]

		},
		//--------------------------文件导出------------
/*	 Ext.create('Ext.panel.Panel',{
			title:'文件导出',
			id:'contentPanel',
			flex:1,
			frame: true,  
        	layout: 'fit',
        	collapsible: true, 
        	items: [{  
                xtype: 'htmleditor',  
                id: 'content',  
                name: 'content',  
                overflowY: 'scroll',  
                enableColors: true,  
                enableAlignments: true,  
                enableFont: true,  
                enableFontSize: true,  
                enableFormat: true,  
                enableLinks: false,  
                enableLists: true,  
                enableSourceEdit: false ,
                            
           	fontFamilies : ['宋体','黑体','隶书',
				           	'Arial','Courier New','Tahoma',
				           	'Times New Roman','Verdana'],
            defaultFont: '宋体'
             
             
            }],  
        tbar: ['->',{  
                xtype: 'button',  
                text: '导出',  
                iconCls:'icon/accept.png',// 'icon-export-word',  
                width: 58,  
                height: 28, 
                listeners:{  
                    afterrender: this.setExportButton  
                }  
            }]
            				
		
		})*/
		
		
		];
		

		this.callParent(arguments);

		console.log('-- Wj.view.admin.DisaReportMgr init over.--');

	},
/*	
	 setExportButton:function(button){ 
	 	console.log('Hit doc!');
    	Downloadify.create(button.getId(), {               
              // 导出的文档名称  
              filename: function(){                      
                      return "疫情报告.doc";  
        },  
               // 要导出的数据  
               data: function(){   
            var headerStart = "<html " +
            "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>";  
            var headerEnd = "</html> ";  
            return headerStart+Ext.getCmp("content").getValue()+headerEnd;  
        },  
        onComplete: function(){ 
        		Ext.MessageBox.show({
					title:'提示',
					msg: '文件导出成功！',
					//fn:callBack,
					buttons:Ext.Msg.OK,
					icon:Ext.Msg.INFO
				},this);
        },  
        onCancel: function(){ 
                Ext.MessageBox.show({
					title:'提示',
					msg: '您取消了文件导出！',
					buttons:Ext.Msg.OK,
					icon:Ext.Msg.INFO
				},this);},  
        onError: function(){ Ext.MessageBox.show({
					title:'提示',
					msg: '文件导出失败！',
					buttons:Ext.Msg.OK,
					icon:Ext.Msg.INFO
				},this);; },  
        swf: '../webapp/extjs/src/ux/exporter/downloadify.swf',  
        downloadImage: '../webapp/extjs/src/ux/exporter/images/export-word.png',  
        width: 58,  
        height: 28,  
        transparent: true,  
        append: false  
    }) 
	 }
	 */
	
 
	

});