Ext.define('Wj.view.nurse.PsyAssess', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.nursepsyassess',

	requires: ['Wj.view.util.Pagingtoolbar',
				'Ext.ux.exporter.Exporter'
			  ],

	closable: false,//如果 设置为true 该Tab是可以关闭的
	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},
	icon: 'icon/psyassess.png',
	title: '心理评估',

	initComponent: function(){
	Ext.Loader.setConfig({ enabled: true }); 
	Ext.Loader.setPath('Ext.ux.exporter', '../webapp/extjs/src/ux/exporter'); 
		console.log('-- Wj.view.nurse.DutyManage init.--');

		this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			store: 'nurse.PsyAssess',
			autoScroll: true,
			itemId:'grid_psyassess',
			dockedItems: [ {
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'nurse.PsyAssess',
				displayMsg: '- 双击进行修改 - 当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}
			
			



			],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				flex: 0.6,
				minWidth: 40,
				allowBlank: false,
				blankText: '编号不能为空'
			},
			 {
				text: '姓名',
				flex: 1,
				minWidth: 140,
				dataIndex: 'ptname',
				allowBlank: false,
				blankText: '姓名不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}, {
				text: '当前心理状态',
				flex: 1,
				minWidth: 240,
				dataIndex: 'psychologyStatus',
				allowBlank: false,
				blankText: '当前心理状态不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			},
			{
				text: '记录人',
				flex: 1,
				minWidth: 240,
				dataIndex: 'nursename',
				allowBlank: false,
				blankText: '记录人不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,50}$/,
					regexText:'请输入50个以内字符'
				}
			},
			{
				text: '时间',
				flex: 1,
				minWidth: 180,
				dataIndex: 'time',
				allowBlank: false,
				renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s'),
				blankText: '时间不能为空',
				editor: {
					xtype: 'textfield'
				}
			}
/*			, {
				text: '分区',
				flex: 1,
				minWidth: 90,
				dataIndex: 'sec',
				allowBlank: false,
				blankText: '分区不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}				
			}*/
			
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

		console.log('-- Wj.view.nurse.PsyAssess init over.--');

	}


});