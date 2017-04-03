Ext.define('Wj.view.admin.VoluMgr', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.adminvolumgr',

	requires: ['Wj.view.util.Pagingtoolbar'],

	closable: true,
	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '志愿者统计',

	initComponent: function(){

		console.log('-- Wj.view.admin.VoluMgr init.--');

		this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			store: 'volunteer.Volunteer',
			autoScroll: true,

			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',

				items: [{
					text: '删除',
					icon: 'icon/delete.gif',
					itemId: 'remove',
					disabled: true,
					handler: Wj.controller.Admin.removeVolu
				}]
			}, {
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'volunteer.Volunteer',
				displayMsg: '- 双击进行修改 - 当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],

			columns: [{
				text: '编号',
				dataIndex: 'user.id',
				flex: 1,
				minWidth: 40,
				allowBlank: false,
				blankText: '编号不能为空'
			},{
				text: '用户名',
				flex: 1,
				minWidth: 40,
				dataIndex: 'user.loginName',
				allowBlank: false,
				blankText: '姓名不能为空',
			}, {
				text: '密码',
				flex: 1,
				minWidth: 40,
				dataIndex: 'user.password',
				allowBlank: false,
				blankText: '姓名不能为空',
			},{
				text: '姓名',
				flex: 1,
				minWidth: 40,
				dataIndex: 'name',
				allowBlank: false,
				blankText: '姓名不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			},{
				text: '性别',
				flex: 1,
				minWidth: 10,
				dataIndex: 'sex',
				allowBlank: false,
				blankText: '年龄不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				},
			renderer : function(val, meta) {
			     switch (val) {
			     case 1:
			      return '男';
			      break;
			     case 2:
			      return '女';
			      break;
			     }
			     return val;
			    }
			},  {
				text: '年龄',
				flex: 1,
				minWidth: 10,
				dataIndex: 'age',
				allowBlank: false,
				blankText: '技能不能为空，可以填“待定”',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}			, {
				text: '家庭地址',
				flex: 1,
				minWidth: 100,
				dataIndex: 'address',
				allowBlank: false,
				blankText: '技能不能为空，可以填“待定”',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}	, {
				text: '联系电话',
				flex: 1,
				minWidth: 100,
				dataIndex: 'phone',
				allowBlank: false,
				blankText: '技能不能为空，可以填“待定”',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}, {
				text: '身份证号',
				flex: 1,
				minWidth: 140,
				dataIndex: 'IDcard',
				allowBlank: false,
				blankText: '身份证号不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			},{
				text: '技能',
				flex: 1,
				minWidth: 40,
				dataIndex: 'specialty',
				allowBlank: false,
				blankText: '技能不能为空，可以填“待定”',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}],

			plugins: [
				Ext.create('Ext.grid.plugin.RowEditing', {
					clicksToEdit: 2,
					clicksToMoveEdit: 1,
					autoCancel: false
				})
			]

		}];

		this.callParent(arguments);

		console.log('-- Wj.view.admin.VoluMgr init over.--');

	}

});