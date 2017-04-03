Ext.define('Wj.view.admin.BedMgr', {
	extend: 'Ext.window.Window',
	alias: 'widget.bedmgr',

	modal: true,
	closable: true,
	resizable: false,
	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '床位管理',

	width: 500,
	height: 400,

	initComponent: function(){

		this.items = [{
			xtype: 'grid',
			itemId: 'secgrid',
			flex: 1,
			frame: true,
			autoScroll: true,
			
			store: 'admin.Sector',

			columns: [/*{
				text: '编号',
				dataIndex: 'id',
				width: 40,
			}, */{
				text: '分区名称',
				dataIndex: 'secName',
				flex: 1,
			}],

		}, {
			xtype: 'grid',
			itemId: 'bedgrid',
			width: 360,
			autoScroll: true,
			store: 'admin.Beds',

			tbar: [{
				icon: 'icon/add.png',
				itemId: 'add',
				handler: Wj.controller.Admin.addBed,
			}, {
				icon: 'icon/delete.gif',
				itemId: 'delete',
				disabled: true,
				handler: Wj.controller.Admin.removeBed,
			}],

			columns: [{
				text: '床位',
				dataIndex: 'bedNum',
				width: 60,
			}, {
				text: '状态',
				dataIndex: 'statusStr',
				width: 80,
			}, {
				text: '姓名',
				dataIndex: 'name',
				width: 100,
			}, {
				text: 'RFID',
				dataIndex: 'rfid',
				width: 100,
			}],

		}];

		this.callParent(arguments);

	}
})