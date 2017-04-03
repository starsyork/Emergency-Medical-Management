Ext.define('Wj.view.doctor.PtCondition', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.docptcondition',

	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '查看病情',
	icon: 'icon/info.png',

	initComponent: function(){

		console.log('-- Wj.view.doctor.PtCondition init.--');

		this.items = [{
			xtype: 'grid',
			title: '病情列表',
			id:'ptConGrid',
			flex: 1,
			frame: true,
			store: 'doctor.PtCondition',
			autoScroll: true,

			dockedItems: [/*{
				xtype: 'toolbar',
				dock: 'top',

				items: [{
					text: '添加',
					icon: 'icon/add.png',
					itemId: 'add',
					handler: Wj.controller.Admin.addDoctor,
				}, {
					text: '删除',
					icon: 'icon/delete.gif',
					itemId: 'remove',
					disabled: true,
					handler: function(){
						alert('remove!');
					},
				}],
			}, */{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'doctor.PtCondition',
				displayMsg: '- 双击进行修改 - 当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true,
			}],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				width: 60,
			}, {
				text: '脉搏',
				dataIndex: 'pulse',
				width: 80,
			}, {
				text: '呼吸频率',
				dataIndex: 'breath',
				width: 100,
			}, {
				text: '舒张压',
				dataIndex: 'diastolic',
				width: 100,
			}, {
				text: '收缩压',
				dataIndex: 'systolic',
				width: 100,
			}, {
				text: '体温',
				dataIndex: 'temperature',
				width: 80,
			}, {
				text: '病情描述',
				dataIndex: 'comment',
				flex: 1,
				minWidth: 320,
			}, {
				text: '时间',
				dataIndex: 'time',
				width: 140,
			}],

			// plugins: [
			// 	Ext.create('Ext.grid.plugin.RowEditing', {
			// 		clicksToEdit: 2,
			// 		clicksToMoveEdit: 1,
			// 		autoCancel: false,
			// 	}),
			// ],

		}];

		this.callParent(arguments);

		console.log('-- Wj.view.doctor.PtCondition init over.--');

	}

});