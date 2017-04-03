Ext.define('Wj.view.doctor.Transfer', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.transfer',

	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '转院记录',
	icon: 'icon/hostranf.png',

	initComponent: function(){

		//console.log('-- Wj.view.doctor.PtCourse init.--');

		this.items = [{
			xtype: 'grid',
			title: '转院记录列表',
			id: 'doc_transport',
			flex: 1,
			frame: true,
			store: 'doctor.Transfer',
			autoScroll: true,

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'doctor.Transfer',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true,
			}],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				flex:1,
				minWidth: 60,
			}, {
				text: '转院患者编号',
				dataIndex: 'pid',
				flex:1,
				minWidth: 60,
			},  {
				text: '申请转院医生',
				dataIndex: 'dname',
				flex:1,
				minWidth: 80,
			}, {
				text: '时间',
				dataIndex: 'time',
				flex:1,
				minWidth: 150,
			}, {
				text: '所转医院',
				dataIndex: 'content',
				flex:1,
				minWidth: 200,
				editor: {
					xtype: 'combobox',
					store:'doctor.Hospital',
					valueField: 'hospName',
					displayField: 'hospName',					
				}
			}, {
				text: '补充说明',
				minWidth: 200,
				dataIndex: 'illustration',
				flex:1,
			} ,  {
				text: '转院医生',
				dataIndex: 'tname',
				flex:1,
				minWidth: 80,
			},{
				text: '转运状态',
				dataIndex: 'status',
				flex:1,
				minWidth: 60
			}]

		}];
		plugins: [
					Ext.create('Ext.grid.plugin.RowEditing', {
						clicksToEdit: 2,
						clicksToMoveEdit: 1,
						autoCancel: false,
						   saveBtnText: '保存', 
				            cancelBtnText: "取消", 
					}),
				],
		this.callParent(arguments);

		//console.log('-- Wj.view.doctor.PtCourse init over.--');

	}

});