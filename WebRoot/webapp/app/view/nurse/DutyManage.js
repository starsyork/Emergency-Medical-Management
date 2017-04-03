Ext.define('Wj.view.nurse.DutyManage', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.nursedutymanage',

	requires: ['Wj.view.util.Pagingtoolbar',
				'Ext.ux.exporter.Exporter'
			  ],

	closable: false,//如果 设置为true 该Tab是可以关闭的
	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},
	icon: 'icon/hands.png',
	title: '交接班',

	initComponent: function(){
	Ext.Loader.setConfig({ enabled: true }); 
	Ext.Loader.setPath('Ext.ux.exporter', '../webapp/extjs/src/ux/exporter'); 
		console.log('-- Wj.view.nurse.DutyManage init.--');

		this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			store: 'nurse.DutyManage',
			autoScroll: true,
			itemId:'grid_dutymanage',
			id:'grid_dutymanage',
			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',

				items: [{
					text: '添加',
					icon: 'icon/add.png',
					itemId: 'add',
					handler: Wj.controller.Nurse.addDuty
				}, {
					text: '删除',
					icon: 'icon/delete.gif',
					itemId: 'remove',
					disabled: true,
					handler: Wj.controller.Nurse.removeDuty
				},
				
				Ext.create('Ext.ux.exporter.Button',{
			          component:Ext.ComponentQuery.query('#grid_dutymanage')[0],
                      text: 'Excel',
                      downloadName: "交接班记录表"
                      
			})
				]
			}, {
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'nurse.DutyManage',
				displayMsg: '- 双击进行修改 - 当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}
			
			



			],

			columns: [
/*			{
				text: '编号',
				dataIndex: 'id',
				flex: 0.6,
				minWidth: 40,
				allowBlank: false,
				blankText: '编号不能为空'
			}, {
				text: '分区',
				flex: 1,
				minWidth: 90,
				dataIndex: 'worksec',
				allowBlank: false,
				blankText: '分区不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			},
			 {
				text: '岗位名称',
				flex: 1,
				minWidth: 140,
				dataIndex: 'workname',
				allowBlank: false,
				blankText: '岗位名称不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}, 
*/
			{
				text: '当值情况',
				flex: 1,
				minWidth: 240,
				dataIndex: 'workoldcon',
				allowBlank: false,
				blankText: '当值情况不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			},
			{
				text: '跟进事项',
				flex: 1,
				minWidth: 240,
				dataIndex: 'worknewcon',
				allowBlank: false,
				blankText: '跟进事项不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,50}$/,
					regexText:'请输入50个以内字符'
				}
			},
			{
				text: '交班人',
				flex: 1,
				minWidth: 70,
				dataIndex: 'workupper',
				allowBlank: false,
				blankText: '交班人不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,50}$/,
					regexText:'请输入50个以内字符'
				}
			},
						{
				text: '接班人',
				flex: 1,
				minWidth:70,
				dataIndex: 'workdownloader',
				allowBlank: false,
				blankText: '接班人不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,50}$/,
					regexText:'请输入50个以内字符'
				}
			},
						{
				text: '交班物品',
				flex: 1,
				minWidth: 200,
				dataIndex: 'workupstuff',
				allowBlank: false,
				blankText: '交班物品不能为空',
				editor: {
					xtype: 'textfield'
				}
			},
						{
				text: '接班物品',
				flex: 1,
				minWidth: 200,
				dataIndex: 'workdownstuff',
				allowBlank: false,
				blankText: '接班物品不能为空',
				editor: {
					xtype: 'textfield'
				}
			},
			{
				text: '时间',
				flex: 1,
				minWidth: 180,
				dataIndex: 'workupdowndate',
				allowBlank: false,
				renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s'),
				blankText: '时间不能为空',
				editor: {
					xtype: 'textfield'
				}
			},{
				text: '领班/负责人',
				flex: 1,
				minWidth: 70,
				dataIndex: 'workleader',
				allowBlank: false,
				blankText: '去向不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,50}$/,
					regexText:'请输入50个以内字符'
				}
			}
			],

			plugins: [
				Ext.create('Ext.grid.plugin.RowEditing', {
					clicksToEdit: 2,
					clicksToMoveEdit: 1,
					autoCancel: false
				})
			]

		}

		
		
		];
		

		this.callParent(arguments);

		console.log('-- Wj.view.nurse.DutyManage init over.--');

	}


});