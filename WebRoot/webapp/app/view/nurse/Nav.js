Ext.define('Wj.view.nurse.Nav', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.nursenav',

	store: 'nurse.Nav',
	id:'id_nurseNavGrid',
	autoScroll: true,
	flex: 1,

	initComponent: function(){

		console.log('-- Wj.view.nurse.Nav init. --');

		this.dockedItems = [{
			xtype: 'toolbar',
			itemId: 'ttb',
			dock: 'top',

			items: [{
				text: '伤愈',
				icon: 'icon/delete.gif',
				itemId: 'rmvpt',
				disabled: true,
				handler: Wj.controller.Nurse.rmvPatient,
			}, '-'],
		}, {
			xtype: 'toolbar',
			dock: 'bottom',
			items: [{
				xtype: 'label',
				text: '- 双击修改床位状态 -',
			}]
		}];

		this.columns = [{
			text: '姓名',
			flex: 1,
			dataIndex: 'name',
		}, {
			text: '床位',
			flex: 1,
			dataIndex: 'bedNum',
		}, {
			text: '状态',
			flex: 1,
			dataIndex: 'statusStr',
			editor: {
				xtype: 'combobox',
				store: ['空', '已分配'],
			},
		}, {
			text: '医生',
			flex: 1,
			dataIndex: 'docName',
		}];

		this.plugins = [
			Ext.create('Ext.grid.plugin.RowEditing', {
				clicksToEdit: 2,
				clicksToMoveEdit: 1,
				autoCancel: false,
			}),
		],

		this.callParent(arguments);

		console.log('-- Wj.view.nurse.Nav init over. --');

	}

});