Ext.define('Wj.view.admin.AttdocStatis',{
extend:'Ext.panel.Panel',
alias:'widget.adminattdocstatis',

requires: ['Wj.view.util.Pagingtoolbar'],

    closable: true,
	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch',
		defaults: {
          bodyStyle: 'padding:10px'}
	},

	title: '医生病人统计',

	initComponent: function(){

		console.log('-- Wj.view.admin.AttdocStatis init.--');
		
	this.items = [{
			xtype: 'grid',
			flex: 1,
			store:'admin.AttdocStatis',
			frame: true,
			autoScroll: true,

	    dockedItems : [{
			xtype:'pagebar',
			dock:'bottom',
			store: 'admin.AttdocStatis',
			displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
			displayInfo: true
			}],
			
		columns: [{
			text: '分区',
			flex: 1,
			minWidth: 200,
			dataIndex: 'secName',
            readOnly:true
		}, {
			text: '医生',
			flex: 1,
			minWidth: 140,
			dataIndex: 'name',
            readOnly:true
		},{
			text: '病人',
			columns:[{
			    text: '受伤',
			    flex: 1,
				width: 200,
				dataIndex: 'wound',
				readOnly:true
			},{
				text: '伤愈',
				flex: 1,
				width: 200,
				dataIndex: 'heal',
				readOnly:true
			},{
				text: '合计',
				flex: 1,
				width: 200,
				dataIndex: 'total',
                readOnly:true
			}]
		}],
	}];

	this.callParent(arguments);

	console.log('-- Wj.view.admin.AttdocStatis init over.--');
	}

});