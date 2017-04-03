Ext.define('Wj.view.nurse.PtInspection', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.nurseptinspection',

	frame: true,

	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '检查检验',
	icon: 'icon/info.png',

	initComponent: function(){

		console.log('-- Wj.view.nurse.PtInspection init.--');

		this.items = [
//			{
//			xtype: 'grid',
//			itemId: 'main_grid',
//			// id: 'doc_ins_main_grid',
//			flex: 1,
//			frame: true,
//			store: 'inspection.Main',
//			autoScroll: true,
//
//			dockedItems: [{
//				xtype: 'pagebar',
//				dock: 'bottom',
//				store: 'inspection.Main',
//				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
//				displayInfo: true,
//			}],
//
//			columns: [{
//				text: 'id',
//				dataIndex: 'id',
//				width: 40,
//			}/*, {
//				text: '条形码号',
//				dataIndex: 'testno',
//				width: 70,
//			}*/, {
//				text: '姓名',
//				dataIndex: 'name',
//				width: 70,
//			}, {
//				text: '性别',
//				dataIndex: 'sex',
//				width: 60,
//			}, {
//				text: '年龄',
//				dataIndex: 'age',
//				width: 60,
//			}, {
//				text: '科别',
//				dataIndex: 'kb',
//				width: 80,
//			}, {
//				text: '床号',
//				dataIndex: 'bed',
//				width: 60,
//			}, {
//				text: '诊断',
//				dataIndex: 'diag',
//				width: 120,
//			}, {
//				text: '标本类型',
//				dataIndex: 'sampleType',
//				width: 70,
//			}, {
//				text: '申请医师',
//				dataIndex: 'applyDoctor',
//				width: 80,
//			}, {
//				text: '申请内容',
//				dataIndex: 'applyContent',
//				width: 140,
//			}, {
//				text: '检验科别',
//				dataIndex: 'inspectKb',
//				width: 100,
//			}, {
//				text: '报告人',
//				dataIndex: 'reporter',
//				width: 80,
//			}, {
//				text: '审核人',
//				dataIndex: 'collater',
//				width: 80,
//			}, {
//				text: '备注',
//				dataIndex: 'note',
//				width: 120,
//			}, {
//				text: '检验号',
//				dataIndex: 'inspectNumber',
//				width: 100,
//			}, {
//				text: '归档日期',
//				dataIndex: 'date',
//				width: 120,
//			}],
//
//		}, 
		{
			xtype: 'grid',
			itemId: 'detail_grid',
			// id: 'doc_ins_detail_grid',
			flex: 1,
			frame: true,
			store: 'inspection.Detail',
			autoScroll: true,

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'inspection.Detail',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true,
			}],

			columns: [{
				text: '化验项目',
				dataIndex: 'item',
				flex: 1,
				minWidth: 100,
			}, {
				text: '中文名称',
				dataIndex: 'itemStr',
				flex: 1.2,
				minWidth: 150,
			}, {
				text: '结果',
				dataIndex: 'resultNum',
				flex: 0.9,
				minWidth: 50,
			}, {
				text: '标志',
				dataIndex: 'flag',
				flex: 0.9,
				minWidth: 50,
			}, {
				text: '参考范围',
				dataIndex: 'scope',
				flex: 0.9,
				minWidth: 80,
			}, {
				text: '单位',
				dataIndex: 'unit',
				flex: 0.9,
				minWidth: 30,
			}, {
				text: '判断L',
				dataIndex: 'min',
				flex: 0.9,
				minWidth: 30,
			}, {
				text: '判断H',
				dataIndex: 'max',
				flex: 0.9,
				minWidth: 30,
			}, {
				text: '状态',
				dataIndex: 'status',
				flex: 0.9,
				minWidth: 30,
			}],

		}];

		this.callParent(arguments);

		console.log('-- Wj.view.nurse.PtInspection init over.--');

	}

});