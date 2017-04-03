Ext.define('Wj.view.admin.HospitalMgr', {
	extend: 'Ext.window.Window',
	alias: 'widget.hospitalmgr',

	modal: true,
	closable: true,
	resizable: false,
	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '医院管理',

	width: 350,
	height: 400,

	initComponent: function(){

		this.items = [ {
			xtype: 'grid',
			itemId: 'hospitalgrid',
			width: 360,
			autoScroll: true,
			store: 'admin.Hospital',

			tbar: [{
				icon: 'icon/add.png',
				itemId: 'add',
				handler: Wj.controller.Admin.addHospital,
			}, {
				icon: 'icon/delete.gif',
				itemId: 'delete',
				disabled: true,
				handler: Wj.controller.Admin.removeHospital,
			}],

			columns: [{
				text: '分区名称',
				dataIndex: 'hospName',
				flex: 1,

			}	],

		}];

		this.callParent(arguments);

	}
})