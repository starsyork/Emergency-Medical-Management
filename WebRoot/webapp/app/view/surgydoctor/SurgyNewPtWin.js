Ext.define('Wj.view.surgydoctor.SurgyNewPtWin', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.surgynewptwin',

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
		align: 'stretch'
	},

	title: '【提醒】有新伤员到达！',

	width: 700,
	height: 300,

	initComponent: function(){

		this.items = [{
			xtype: 'form',
			itemId: 'cform',
			buttonAlign: 'center',
			height: 50,

			padding: '10 10 10 10',
			border: false,
			frame: true,

			layout: {
				type: 'vbox',
				pack: 'start',
				align: 'stretch'
			},

			buttons: [{
				text: '确认',
				itemId: 'btn_confirm',
				icon: 'icon/accept.png',
				disabled: true,
				handler: Wj.controller.SurgyDoctor.handleConfirmSurgyNewPt
			}]

		}, {
			xtype: 'grid',
			title: '新伤员一览表',
			autoScroll: true,
			store: 'surgydoctor.SurgyNewPatient',
			flex: 1,

			columns:[{
				text: '编号',
				dataIndex: 'id',
				width: 40
			}, {
				text: 'RFID',
				dataIndex: 'rfid',
				width: 80
			}, {
				text: '床号',
				dataIndex: 'bedNum',
				width: 40
			}, {
				text: '姓名',
				dataIndex: 'name',
				width: 100
			}, {
				text: '性别',
				dataIndex: 'sex',
				width: 40
			}, {
				text: '年龄',
				dataIndex: 'age',
				width: 40
			}, {
				text: '受伤类型',
				dataIndex: 'woundType',
				width: 120
			}, {
				text: '受伤时间',
				dataIndex: 'woundTime',
				width: 120
			}, {
				text: '受伤地点',
				dataIndex: 'woundAddr',
				flex: 1
			}]

		}];

		this.callParent(arguments);

	}
})