Ext.require(['Ext.ux.grid.SurgyWardRecPrinter']);
Ext.define('Wj.view.surgydoctor.SurgyPrepare', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.surgyprepare',

	frame: true,

	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '术前准备',
	icon: 'icon/info.png',

	initComponent: function(){

		console.log('-- Wj.view.surgydoctor.SurgyPrepare init.--');

		this.items = [{
			xtype: 'grid',
			itemId: 'main_grid',
			// id: 'doc_ins_main_grid',
			flex: 1,
			frame: true,
			store: 'surgydoctor.SurgyPrepareMain',
			autoScroll: true,

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'surgydoctor.SurgyPrepareMain',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],	tbar: [{
				xtype: 'toolbar',
				dock: 'top',
				items: [{
					text: '确认完成',
					icon: 'icon/add.png',
					itemId: 'add',
					handler: Wj.controller.SurgyDoctor.confirmSurgy
				}]
			},
			],

			columns: [
				
					{
				text: '编号',
				dataIndex: 'id',
				flex:1,
				minWidth: 40
			},  {
				text: '手术名称',
				dataIndex: 'content',
				flex:1,
				minWidth: 90
			},{
				text: '备注',
				dataIndex: 'illustration',
				flex:1,
				minWidth: 120
			},{
				text: '申请医生',				
				dataIndex: 'applydoc',
				flex:1,
				minWidth: 150,
				
			}, {
				text: '申请日期',				
				dataIndex: 'applytime',
				flex:1,
				minWidth: 150,
				
			}, {
				text: '手术申请号',
				dataIndex: 'applyId',
				flex:1,
				minWidth: 100
			}
/*			, {
				text: '检验号',
				dataIndex: 'inspectNumber',
				flex:1,
				minWidth: 100
			},
			{
				text: '检查检验医生',
				dataIndex: 'inspectDoc',
				flex:1,
				minWidth: 100
			},{
				text: '审核人',
				dataIndex: 'verifier',
				flex:1,
				minWidth: 50
			}, {
				text: '床号',
				dataIndex: 'bed',
				flex:1,
				minWidth: 60
			}, 
				{
				text: '姓名',
				dataIndex: 'name',
				flex:1,
				minWidth: 70
			}, 
			{
				text: '性别',
				dataIndex: 'sex',
				flex:1,
				minWidth: 60
			}, {
				text: '年龄',
				dataIndex: 'age',
				flex:1,
				minWidth: 60
			}, 
			{
				text: '科别',
				dataIndex: 'kb',
				flex:1,
				minWidth: 80
			}
*/			
			]

		}, 
			{
			xtype: 'grid',
			itemId: 'detail_grid',
			flex: 1,
			frame: true,
			store: 'surgydoctor.SurgyPrepareDetail',
			autoScroll: true,
			dockedItems: [					
			{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'surgydoctor.SurgyPrepareDetail',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],

			tbar: [{
				text: '打印',
				icon: 'extjs/src/ux/grid/gridPrinterCss/printer.png',
				iconCls: 'icon-print',
				handler: function(b) {
					Ext.ux.grid.SurgyWardRecPrinter.printAutomatically = false;
					Ext.ux.grid.SurgyWardRecPrinter.printWithData(
						b.up('grid').up('panel').down('#main_grid').getSelectionModel().getSelection()[0].data,
						b.up('grid'));
				}
			},
			{
				xtype: 'toolbar',
				dock: 'top',
				items: [{
					text: '新增',
					icon: 'icon/add.png',
					itemId: 'add',
					handler: Wj.controller.SurgyDoctor.addWardRecord
				}, '-', {
					text: '删除',
					icon: 'icon/delete.gif',
					itemId: 'remove',
					disabled: false,
					handler: Wj.controller.SurgyDoctor.removeWardRecord
				}]
			}
			],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				flex: 1,
				minWidth: 80
			}, {
				text: '药品名称',
				dataIndex: 'drugname',
				flex: 0.9,
				minWidth: 100
			}, {			
				text: '数量',
				dataIndex: 'amount',				
				flex: 0.9,
				minWidth: 80,
			},  {
				text: '手术名称',
				dataIndex: 'content',
				flex:1,
				minWidth: 90
			}, {
				text: '手术申请号',
				dataIndex: 'applyId',
				flex:1,
				minWidth: 100
			}
/*			,
			{
				text: '操作员',
				dataIndex: 'noteperson',
				flex:1,
				minWidth: 50
			}, 
				{
				text: '药品类型',
				dataIndex: 'drugtype',
				flex: 1.2,
				minWidth: 220
			}*/
			]

		}
		
		];

		this.callParent(arguments);

		console.log('-- Wj.view.surgydoctor.SurgyPtInspectData init over.--');

	}

});