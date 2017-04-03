Ext.define('Wj.view.admin.SecMgr', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.adminsecmgr',

	collapsible: true,
	collapseDirection: 'bottom',

	store: 'admin.Sector',
	autoScroll: true,

	initComponent: function(){

		console.log('-- Wj.view.admin.SecMgr init.--');

		this.dockedItems = [{
			xtype: 'toolbar',
			dock: 'top',
			
			items: [{
				icon: 'icon/refresh.gif',
				itemId: 'update',
				handler: function(b){
					Ext.getStore('admin.Sector').load();
				}
			}, {
				icon: 'icon/add.png',
				itemId: 'add',
				handler: Wj.controller.Admin.addSector,
			}, {
				icon: 'icon/delete.gif',
				itemId: 'remove',
				disabled: true,
				handler: Wj.controller.Admin.removeSector,
			}],

		}],

		this.columns = [{
			text: '分区名称',
			dataIndex: 'secName',
			flex: 1,
		}]

		this.callParent(arguments);

		console.log('-- Wj.view.admin.SecMgr init over.--');

	}

});