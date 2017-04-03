Ext.define('Wj.view.nurse.Report', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.nursereport',

	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '处治报告',
	icon: 'icon/info.png',

	initComponent: function(){

		console.log('-- Wj.view.nurse.Report init.--');

		this.items = [{
			xtype: 'grid',
			title: '处治报告列表',
			flex: 1,
			frame: true,
			store: 'Report',
			autoScroll: true,

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'Report',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true,
			}],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				width: 120,
			}, {
				text: '时间',
				dataIndex: 'time',
				width: 160,
			}, {
				text: '类型',
				dataIndex: 'type',
				width: 200,
			}, {
				text: '处治内容',
				flex: 1,
				dataIndex: 'measure',
			}],

		}];

		this.callParent(arguments);

		console.log('-- Wj.view.nurse.Report init over.--');

	}

});