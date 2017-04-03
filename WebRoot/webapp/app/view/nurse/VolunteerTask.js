Ext.define('Wj.view.nurse.VolunteerTask', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.nursevolunteertask',

	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '志愿者任务管理',
	icon: 'icon/voluicon.png',

	initComponent: function(){

		console.log('-- Wj.view.nurse.VolunteerTask init.--');

		this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			store: 'nurse.VolunteerTask',
			autoScroll: true,

			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',

				items: [{
					text: '新增任务',
					icon: 'icon/add.png',
					itemId: 'add',
					handler: Wj.controller.Nurse.addTask
				}, '-', {
					text: '删除任务',
					icon: 'icon/delete.gif',
					itemId: 'remove',
					//disabled: true,
					handler: Wj.controller.Nurse.removeTask
				}

				]
			}, {
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'nurse.VolunteerTask',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				flex:1,
				minWidth: 40
			}, {
				text: '类型',
				dataIndex: 'tasktype',
				flex:1,
				editor: {
				xtype: 'textfield'
				},
				minWidth: 60
			}, {
				text: '详细信息',
				dataIndex: 'details',
				flex:1,
				minWidth: 200,
				editor: {
					xtype: 'textfield'
				}
			}, {
				text: '添加日期',
				dataIndex: 'time',
				flex:1,
				minWidth: 130
			}, {
				text: '发布人',
				dataIndex: 'taskpeople',
				flex:1,
				minWidth: 80,
				editor: {
				xtype: 'textfield'
				}
			}, {
				text: '状态',
				dataIndex: 'status',
				flex:1,
				minWidth: 100,
				editor: {
					xtype: 'textfield'
				}
			}, {
				text: '还需增援人数',
				dataIndex: 'number',
				flex:1,
				minWidth: 100,
				editor: {
					xtype: 'textfield'
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

		console.log('-- Wj.view.nurse.VolunteerTask init over.--');

	}

});