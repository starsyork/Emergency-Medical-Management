Ext.define('Wj.view.Admin', {
	extend: 'Ext.container.Container',
	alias: 'widget.admin',

	requires: [
		'Wj.view.Banner',
		'Wj.view.admin.Nav',
		'Wj.view.admin.SecMgr',
		'Wj.view.Content'
	],
	
	layout: 'border',

	initComponent: function(){

		console.log('-- Wj.view.Admin init. --');

		this.items = [{
			region: 'north',
			xtype: 'banner'
		}, {
			xtype: 'container',
			region: 'west',
			width: 200,
			layout: {
				type: 'vbox',
				align: 'stretch',
				pack: 'start'
			},
			items: [{
				xtype: 'adminnav',
				flex: 1,
				title: '功能导航'
			},{
				xtype: 'form',
				frame: true,
				height: 34,
				buttonAlign: 'center',
				buttons: [{
					text: '床位管理',
					icon: 'icon/info.png',
					handler: Wj.controller.Admin.initBedMgrView
				},{
					text: '医院管理',
					icon: 'icon/info.png',
					handler: Wj.controller.Admin.initHospitalMgrView
				}]
			},{
				xtype: 'adminsecmgr',
				flex: 1,
				frame: true,
				title: '分区管理'
			}]
		}, {
			region: 'center',
			xtype: 'content'
		}];

		this.callParent(arguments);

		console.log('-- Wj.view.Admin init over. --');

	}
})