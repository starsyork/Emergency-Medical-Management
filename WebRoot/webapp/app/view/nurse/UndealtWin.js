Ext.define('Wj.view.nurse.UndealtWin', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.undealtwin',

	closable: false,
	resizable: false,

	floating: true,

	collapsible: true,
	animCollapse: true,
	titleCollapse: true,
	collapseDirection: 'bottom',
	collapsed: true,

	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '【提醒】有未分配的伤员！',

	width: 300,
	height: 300,

	initComponent: function(){

		this.items = [{
			xtype: 'form',
			itemId: 'udform',
			buttonAlign: 'center',
			height: 50,

			padding: '10 10 10 10',
			border: false,
			frame: true,

			layout: {
				type: 'vbox',
				pack: 'start',
				align: 'stretch',
			},

			buttons: [{
				text: '未分配-其他分区',
				itemId: 't_o_u_btn',
				icon: 'icon/user_gray.png',
				disabled: true,
				handler: Wj.controller.Nurse.handleOtherUndealt,
			}, {
				text: '未分配-本分区',
				itemId: 't_u_btn',
				icon: 'icon/user_gray.png',
				disabled: true,
				handler: Wj.controller.Nurse.handleUndealt,
			}]

		}, {
			xtype: 'grid',
			title: '本分区未分配伤员列表',
			autoScroll: true,
			store: 'nurse.PtUndealt',
			flex: 1,

			columns:[{
				text: '编号',
				dataIndex: 'id',
				width: 40,
			}, {
				text: '姓名',
				dataIndex: 'name',
				width: 100,
			}, {
				text: '性别',
				dataIndex: 'sex',
				width: 60,
			}, {
				text: 'RFID',
				dataIndex: 'rfid',
				flex: 1,
			}]

		}];

		this.callParent(arguments);

	}
})