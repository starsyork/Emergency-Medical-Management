Ext.require(['Ext.ux.grid.Printer']);

Ext.define('Wj.view.examdoctor.ExamPtInspection', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.examdocptinspection',

	frame: true,

	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '检查检验',
	icon: 'icon/info.png',

	initComponent: function(){

		console.log('-- Wj.view.examdoctor.ExamPtInspection init.--');

		this.items = [
		              {
				xtype: 'toolbar',
				dock: 'top',

				items: [{
					text: '上传影像',
					icon: 'icon/add.png',
					itemId: 'add',
					handler: Wj.controller.ExamDoctor.addPhoto
				}]
				},{
			xtype: 'grid',
			itemId: 'main_grid',
			 id: 'exam_ins_main_grid',
			flex: 1,
			frame: true,
			store: 'examdoctor.ExamInspectMain',
			autoScroll: true,

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'examdoctor.ExamInspectMain',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],

			columns: [{
				text: 'id',
				dataIndex: 'id',
				flex:1,
				minWidth: 40
			}, {
				text: '申请医师',
				dataIndex: 'applydoc',
				flex:1,
				minWidth: 80
			}, {
				text: '申请内容',
				dataIndex: 'content',
				flex:1,
				minWidth: 140
			},  {
				text: '检验号',
				dataIndex: 'applyId',
				flex:1,
				minWidth: 100
			},{
				text: '备注',
				dataIndex: 'illustration',
				flex:1,
				minWidth: 120,
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符',
				},
			},{
				text: '归档',
				dataIndex: 'url',
				flex:1,
				minWidth: 120
			}
		],	
			plugins: [
								Ext.create('Ext.grid.plugin.RowEditing', {
									clicksToEdit: 2,
									clicksToMoveEdit: 1,
									autoCancel: false,
								}),
							],

		}, {
			xtype: 'grid',
			itemId: 'detail_grid',
			 id: 'exam_ins_detail_grid',
			flex: 1,
			frame: true,
			store: 'examdoctor.ExamInspectDetail',
			autoScroll: true,

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'examdoctor.ExamInspectDetail',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],

			tbar: [{
				text: '打印',
				icon: 'extjs/src/ux/grid/gridPrinterCss/printer.png',
				iconCls: 'icon-print',
				handler: function(b) {
					Ext.ux.grid.Printer.printAutomatically = false;
					Ext.ux.grid.Printer.printWithData(
						b.up('grid').up('panel').down('#main_grid').getSelectionModel().getSelection()[0].data,
						b.up('grid'));
				}
			}],

			columns: [
				{
						text: 'id',
						dataIndex: 'id',
						flex:1,
						minWidth: 40
					},{
				text: '化验项目',
				dataIndex: 'item',
				flex: 1,
				minWidth: 140
			}, {
				text: '中文名称',
				dataIndex: 'itemStr',
				flex: 1.2,
				minWidth: 220
			}, {
				text: '结果',
				dataIndex: 'resultNum',
				flex: 0.9,
				minWidth: 100,
				editor: {
					xtype: 'numberfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符',
				},
			}, {
				text: '标志',
				dataIndex: 'flag',
				flex: 0.9,
				minWidth: 60,
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符',
				},
			}, {
				text: '参考范围',
				dataIndex: 'scope',
				flex: 0.9,
				minWidth: 140,
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符',
				},
			}, {
				text: '单位',
				dataIndex: 'unit',
				flex: 0.9,
				minWidth: 100,
				editor: {
					xtype: 'combobox',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符',
					store: Wj.consts.Type,
				},
			}, {
				text: '判断L',
				dataIndex: 'min',
				flex: 0.9,
				minWidth: 100,
				editor: {
					xtype: 'numberfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符',
				},
			}, {
				text: '判断H',
				dataIndex: 'max',
				flex: 0.9,
				minWidth: 100,
				editor: {
					xtype: 'numberfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符',
				},
			}, {
				text: '状态',
				dataIndex: 'status',
				flex: 0.9,
				minWidth: 30,
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

		console.log('-- Wj.view.examdoctor.ExamPtInspection init over.--');

	}

});