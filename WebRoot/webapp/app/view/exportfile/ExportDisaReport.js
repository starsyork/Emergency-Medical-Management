function createExportPanel(){
		new Ext.create('Ext.panel.Panel',{
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
                enableSourceEdit: false  
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
			
		})
		
}
	function setExportButton(button){  
    	Downloadify.create(button.getId(), {               
              // 导出的文档名称  
              filename: function(){                      
                      return "疫情报告.doc";  
        },  
               // 要导出的数据  
               data: function(){   
            var headerStart = "<html xmlns:v='urn:schemas-microsoft-com:vml' " +  
            "xmlns:o='urn:schemas-microsoft-com:office:office'" +  
            "xmlns:w='urn:schemas-microsoft-com:office:word'" +  
            "xmlns:m='http://schemas.microsoft.com/office/2004/12/omml'" +  
            "xmlns='http://www.w3.org/TR/REC-html40'> ";  
            var headerEnd = "</html> ";  
            return headerStart+Ext.getCmp("content").getValue()+headerEnd;  
        },  
        onComplete: function(){ alert('文件导出成功！'); },  
        onCancel: function(){ alert('您取消了文件导出！'); },  
        onError: function(){ alert('文件导出失败！'); },  
        swf: '../webapp/extjs/exporter/downloadify.swf',  
        downloadImage: '../webapp/extjs/exporter/images/export-word.png',  
        width: 58,  
        height: 28,  
        transparent: true,  
        append: false  
    });  
} 