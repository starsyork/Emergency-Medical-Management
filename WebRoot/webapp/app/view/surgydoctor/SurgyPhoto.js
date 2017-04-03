Ext.define('Wj.view.surgydoctor.SurgyPhoto', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.surgyphoto',

	frame: true,

	layout: {
		type: 'table',
		columns:2,
		pack: 'start',
		align: 'stretch'
	},

	title: '影像数据',
	icon: 'icon/iconphoto.png',

	initComponent: function(){

		console.log('-- Wj.view.surgydoctor.SurgyPhoto init.--');
var gridHeight = 200;
var gridWidth =  Ext.getBody().getWidth()-280;//  
console.log('gridWidth:',gridWidth);
//图片Panel的尺寸
var photoPanelH = Ext.getBody().getHeight()-100-gridHeight; //100 是banner的高度
var photoPanelW = photoPanelH*2;
//影像描述panel的尺寸
var photoDecPanelH = photoPanelH;
var photoDecPanelW = gridWidth-photoPanelW;
    this.items=[
    {
		xtype: 'grid',
		title: '伤员影像数据',
		itemId:'ptphotogrid',
		height:gridHeight,
		width:gridWidth,
		autoScroll: true,
		//store: 'surgydoctor.SurgyPhotoData',
		colspan:2,//设置跨列
		//layout:'anchor',
		frame:true,
		store: 'surgydoctor.SurgyInspectMain',
		flex:1,
		dockedItems: [{
			xtype: 'pagebar',
			dock: 'bottom',
			store: 'surgydoctor.SurgyInspectMain',
			displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
			displayInfo: true
		}],
		columns:[
			{
				text: 'id',
				dataIndex: 'id',
				flex:1,
				minWidth: 40
			}, {
				text: '申请医师',
				dataIndex: 'applydoc',
				flex:1,
				minWidth: 60
			}, {
				text: '申请内容',
				dataIndex: 'content',
				flex:1,
				minWidth: 140
			},  {
				text: '检验号',
				dataIndex: 'applyId',
				flex:1,
				minWidth: 80
			},{
				text: '备注',
				dataIndex: 'illustration',
				flex:1,
				minWidth: 100
			},{
				text: '归档',
				dataIndex: 'url',
				flex:1,
				minWidth: 390
			}]
    },
	{
		xtype:'panel',
		height:photoPanelH,
		width:photoPanelW,
		title:'影像',
		itemId:'surgyphotopanel',
		
		dockedItems: [{
			xtype: 'toolbar',
			dock: 'top',
			items: [
				{
				text: '查看图片',
				icon: 'icon/look.png',
				itemId: 'lookimg',
				handler: Wj.controller.SurgyDoctor.ShowImg
			}]
		}],
		items:[
			{
			    xtype:'box',
		        autoEl: {
		            style: 'width:350px;height:350px;margin:0px auto;' +
		            		'border:1px solid #ccc; text-align:center;' +
		            		'padding-top:20px;margin-bottom:10px',
		            tag: 'img',
		    		src:'resource/img/NoPhotoDis.png'
		    	}
    		}
		
		]
    },
	    {
			xtype:'panel',
			title:'影像描述',
			height:photoDecPanelH,
			width:photoDecPanelW,
        	items: [{  
                xtype: 'textarea',  
                itemId: 'surgyphotocontent',  
				height:photoDecPanelH,
				width:photoDecPanelW,
                readOnly:true            
    
                }  
            ]
	    }
    
    
    ]




           

			
		
		



	


		this.callParent(arguments);

		console.log('-- Wj.view.surgydoctor.SurgyPhoto init over.--');

	}

});