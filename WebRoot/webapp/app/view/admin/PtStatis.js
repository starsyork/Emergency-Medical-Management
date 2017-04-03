Ext.define('Wj.view.admin.PtStatis',{
extend:'Ext.panel.Panel',
alias:'widget.adminptstatis',
//alias 别名 类名称简短的别名列表
requires: ['Wj.view.util.Pagingtoolbar'],
//requires是引入extjs的封装类

    closable: true,
	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch',
		defaults: {
          bodyStyle: 'padding:10px'}
	},

	title: '分区病人统计',

	initComponent: function(){

		console.log('-- Wj.view.admin.PtStatis init.--');
		
	this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			store:'admin.PtStatis',
			autoScroll: true,
			
			dockedItems : [{
				xtype:'pagebar',
				dock:'bottom',
				store: 'admin.PtStatis',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true,
			}],
			
			columns: [{
				text: '分区',
				flex: 1,
				minWidth: 160,
				dataIndex: 'secName',
                readOnly:true
			}, {
				text: '病人',
				columns:[{
					text: '机械性外伤',
					width: 150,
					dataIndex: 'machine',
					readOnly:true
				},{
					text: '埋压窒息伤',
					width: 150,
					dataIndex: 'pressasphyxia',
					readOnly:true
				},/*{
					text: '完全性饥饿',
					width: 150,
					dataIndex: 'hunger',
	                readOnly:true
				},*/{
					text: '淹溺',
					width: 150,
					dataIndex: 'drowning',
					readOnly:true
				},{
					text: '烧伤',
					width: 150,
					dataIndex: 'burn',
					readOnly:true
				},{
					text: '冻伤',
					width: 150,
					dataIndex: 'coldinjury',
					readOnly:true
				},{
					text: '中毒',
					width: 150,
					dataIndex: 'poisoning',
					readOnly:true
				},{
					text: '合计',
					width: 150,
					dataIndex: 'total',
					readOnly:true
				}]
			}]
		}];
		
		this.callParent(arguments);

		console.log('-- Wj.view.admin.PtStatis init over.--');
	}
});