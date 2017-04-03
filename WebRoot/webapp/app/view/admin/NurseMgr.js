Ext.define('Wj.view.admin.NurseMgr', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.adminnursemgr',

	requires: ['Wj.view.util.Pagingtoolbar'],

	closable: true,
	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '护士账号管理',

	initComponent: function(){

		console.log('-- Wj.view.admin.NurseMgr init.--');

		this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			store: 'user.Nurse',
			autoScroll: true,

			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',

				items: [{
					text: '添加',
					icon: 'icon/add.png',
					itemId: 'add',
					handler: Wj.controller.Admin.addNurse,
				}, {
					text: '删除',
					icon: 'icon/delete.gif',
					itemId: 'remove',
					disabled: true,
					handler: function(){
						alert('remove!');
					},
					handler: Wj.controller.Admin.removeNurse,
				}],
			}, {
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'user.Nurse',
				displayMsg: '- 双击进行修改 - 当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true,
			}],

			columns: [{
				text: '编号',
				flex: 1,
				minWidth: 40,
				dataIndex: 'id',
				allowBlank: false,
				blankText: '编号不能为空',
			}, {
				text: '用户名',
				flex: 1,
				minWidth: 120,
				dataIndex: 'loginName',
				allowBlank: false,
				blankText: '用户名不能为空',
				editor: {
					xtype: 'textfield',
					// regex: /^[a-zA-Z]{1}([a-zA-Z0-9._]){3,11}$/,
            		// regexText: '请填写合法的用户名(必须以字母开头,长度4-12位)',
				},
			}, {
				text: '密码',
				flex: 1,
				minWidth: 120,
				dataIndex: 'password',
				allowBlank: false,
				blankText: '密码不能为空',
				editor: {
					xtype: 'textfield',
					// regex: /^[a-zA-Z]{1}([a-zA-Z0-9._]){3,11}$/,
            		// regexText: '请填写合法的密码(必须以字母开头,长度4-12位)',
				},
			}, {
				text: '姓名',
				flex: 1,
				minWidth: 140,
				dataIndex: 'userName',
				allowBlank: false,
				blankText: '姓名不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符',
				},
			}, {
				text: '分区',
				flex: 1,
				minWidth: 200,
				dataIndex: 'sector',
				allowBlank: false,
				blankText: '分区不能为空',
				editor: {
					xtype: 'combobox',
					store: 'admin.Sector',
					valueField: 'secName',
					displayField: 'secName',
				},
			}],

			plugins: [
				Ext.create('Ext.grid.plugin.RowEditing', {
					clicksToEdit: 2,
					clicksToMoveEdit: 1,
					autoCancel: false,
				}),
			],

		}];

		this.callParent(arguments);

		console.log('-- Wj.view.admin.NurseMgr init over.--');

	}

});