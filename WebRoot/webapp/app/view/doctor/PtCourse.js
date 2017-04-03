Ext.define('Wj.view.doctor.PtCourse', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.docptcourse',

	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '管理病程',
	icon: 'icon/user_edit.png',

	initComponent: function(){

		console.log('-- Wj.view.doctor.PtCourse init.--');

		this.items = [{
			xtype: 'grid',
			id:'ptCourseGird',
			flex: 1,
			frame: true,
			store: 'doctor.PtCourse',
			autoScroll: true,

			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',

				items: [{
					text: '新增病程',
					icon: 'icon/add.png',
					itemId: 'add',
					handler: Wj.controller.Doctor.addCourse,
				}, '-', {
					text: '删除',
					icon: 'icon/delete.gif',
					itemId: 'remove',
					disabled: true,
					handler: Wj.controller.Doctor.removeCourse,
				}, '-', {
					text: '下载',
					icon: 'icon/down.gif',
					itemId: 'download',
					handler: Wj.controller.Doctor.downloadDoc,
				}],
			}, {
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'doctor.PtCourse',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true,
			}],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				width: 40,
			}, {
				text: '编写日期',
				dataIndex: 'addTime',
				width: 120,
			}, {
				text: '病程描述',
				dataIndex: 'description',
				width: 340,
			}, {
				text: '存档(.doc)',
				dataIndex: 'doc',
				width: 200,
			}],

			// plugins: [
			// 	Ext.create('Ext.grid.plugin.RowEditing', {
			// 		clicksToEdit: 2,
			// 		clicksToMoveEdit: 1,
			// 		autoCancel: false,
			// 	}),
			// ],

		}, {
			xtype: 'form',
			flex: 0.8,
			frame: true,

			layout: {
				type: 'vbox',
				align: 'stretch',
				pack: 'start',
			},

			items: [{
				xtype: 'fieldset',
				flex: 1,

				padding: '10 10 10 10',
				border: false,

				layout: {
					type: 'vbox',
					pack: 'start',
					align: 'stretch',
				},

				defaults: {
					labelSeparator: ': ',
					labelAlign: 'left',
					msgTarget: 'side',
					style: {
						margin: '4px 10px',
					},
				},

				defaultType: 'textfield',

				items: [{
					fieldLabel: '编号',
					name: 'id',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					xtype: 'datetimefield',
					name: 'addTime',
					itemId: 'f_addTime',
					fieldLabel: '编写时间',
					format: 'Y/m/d H:i:s',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					name: 'doc',
					itemId: 'f_doc',
					editable: false,
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
					fieldLabel: '存档(.doc)',
				}, {
					xtype: 'textarea',
					name: 'description',
					itemId: 'f_description',
					fieldLabel: '病程描述',
					minHeight: 300,
					flex: 1,
					grow: true,
					regex:/^[\s\S]{0,200}$/,
					regexText:'请输入200个以内字符',
				}, {
					xtype: 'filefield',
					name: 'uploadDoc',
					itemId: 'f_uploadDoc',
					fieldLabel: 'doc文件',
					buttonText: '选择文件...',
				}],
			}],

			buttons: [{
				text: '更新',
				icon: 'icon/accept.png',
				handler: Wj.controller.Doctor.submitCourse,
			}, {
				text: '重置',
				icon: 'icon/reset.png',
				handler: function(){
					var f = this.up('form');
					var r = f.getRecord();
					f.loadRecord(r);
				}
			}],

		}];

		this.callParent(arguments);

		console.log('-- Wj.view.doctor.PtCourse init over.--');

	}

});