Ext.define('Wj.view.doctor.VolunteerTask', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.volunteertask',

	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '志愿者任务管理',
	icon: 'icon/voluicon.png',

	initComponent: function(){

		//console.log('-- Wj.view.doctor.PtCourse init.--');

		this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			store: 'doctor.VolunteerTask',
			autoScroll: true,

			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',

				items: [{
					text: '新增任务',
					icon: 'icon/add.png',
					itemId: 'add',
					handler: Wj.controller.Doctor.addTask
				}, '-', {
					text: '删除任务',
					icon: 'icon/delete.gif',
					itemId: 'remove',
					disabled: true,
					handler: Wj.controller.Doctor.removeTask
				}
//				, '-', {
//					text: '下载',
//					icon: 'icon/down.gif',
//					itemId: 'download',
//					handler: Wj.controller.Doctor.downloadDoc,
//				}
				]
			}, {
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'doctor.VolunteerTask',
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
				minWidth: 60
			}, {
				text: '详细信息',
				dataIndex: 'details',
				flex:1,
				minWidth: 200,
				editor: {
					xtype: 'textfield'
					// regex: /^[a-zA-Z]{1}([a-zA-Z0-9._]){3,11}$/,
     //        		regexText: '请填写合法的密码(必须以字母开头,长度4-12位)',
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

			}, {
				text: '状态',
				dataIndex: 'status',
				flex:1,
				minWidth: 100,
			

			}, {
				text: '还需增援人数',
				dataIndex: 'number',
				flex:1,
				minWidth: 100,

			}],

			plugins: [
						Ext.create('Ext.grid.plugin.RowEditing', {
							clicksToEdit: 2,
							clicksToMoveEdit: 1,
							autoCancel: false,
				            saveBtnText: '保存', 
				            cancelBtnText: "取消", 
						})
					]

		}];

		this.callParent(arguments);

		console.log('-- Wj.view.doctor.PtCourse init over.--');

	}

});