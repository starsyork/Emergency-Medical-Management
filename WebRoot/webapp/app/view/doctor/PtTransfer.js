Ext.require(['Ext.ux.grid.Printer']);

Ext.define('Wj.view.doctor.PtTransfer', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.pttransfer',

	frame: true,
	requires: ['Wj.view.util.Pagingtoolbar',
				'Ext.ux.exporter.Exporter'
			  ],
	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '转运管理',
	icon: 'icon/transfer.png',

	initComponent: function(){
		
	Ext.Loader.setConfig({ enabled: true }); 
	Ext.Loader.setPath('Ext.ux.exporter', '../webapp/extjs/src/ux/exporter'); 
		console.log('-- Wj.view.doctor.PtTransfer init.--');

		this.items = [
//----------------------------------------------------------------------------		
		
		Ext.create('Ext.panel.Panel',{
			title:'文件导出',
			//id:'trancontent',
			flex:1,
			frame: true,  
        	layout: 'fit',
        	collapsible: true, 
        	autoScroll: true,
        
        	items: [{  
                xtype: 'htmleditor',  
                id: 'trancontent',  
                name: 'content',  
            	//store: 'surgydoctor.SurgyInspectDetail',
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
//        tbar: ['-',{  
//                xtype: 'button',  
//                text: '导出',  
//                iconCls:'icon/accept.png',
//                width: 58,  
//                height: 28, 
//                listeners:{  
//                    afterrender: this.setExportButton  
//                }  
//            }],
            dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',

				items: [
					{
					text: '上传',
					icon: 'icon/uploadfile.png',
					itemId: 'uploaddoc'
					//handler: Wj.controller.Doctor.addInspection,
				},'-',{  
                xtype: 'button',  
                text: '导出',  
                iconCls:'icon/accept.png',
                width: 58,  
                height: 28, 
                listeners:{  
                    afterrender: this.setExportButton  
                }} ]

				}]
            				
		
		})
		

		
		
//-----------------------------------------------------------------------------		
		];

		this.callParent(arguments);

		console.log('-- Wj.view.doctor.PtTransfer init over.--');

	},
	
	setExportButton:function(button){ 
	 	console.log('Hit doc!');
    	Downloadify.create(button.getId(), {               
              // 导出的文档名称  
              filename: function(){                      
                      return "转运报告.doc";  
        },  
//	 	 Downloadify.create(Ext.getCmp('myexportdoc'), {               
//              // 导出的文档名称  
//              filename: function(){                      
//                      return "转运报告.doc";  
//        },  
               // 要导出的数据  
        data: function(){   
            var headerStart = "<html " +
            "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>";  
//            var headerStart = " xmlns:v='urn:schemas-microsoft-com:vml'"+
//"xmlns:o='urn:schemas-microsoft-com:office:office'"+
//"xmlns:w='urn:schemas-microsoft-com:office:word'"+
//"xmlns:m='http://schemas.microsoft.com/office/2004/12/omml'"+
//"xmlns='http://www.w3.org/TR/REC-html40'>"+
//"<head>"+
//"<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>"+
//"<meta name=ProgId content=Word.Document>"+
//"</head>";

            var headerEnd = "</html> ";  
            
            console.log('Content:',Ext.getCmp("trancontent").getValue());
            return headerStart+Ext.getCmp("trancontent").getValue()+headerEnd;  
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

});