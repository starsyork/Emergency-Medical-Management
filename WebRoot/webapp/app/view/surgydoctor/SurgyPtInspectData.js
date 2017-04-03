Ext.require(['Ext.ux.grid.Printer']);
Ext.define('Wj.view.surgydoctor.SurgyPtInspectData', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.surgyptinspectdata',

	frame: true,

	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '检验数据',
	icon: 'icon/info.png',

	initComponent: function(){

		console.log('-- Wj.view.surgydoctor.SurgyPtInspectData init.--');

		this.items = [
		{
			xtype: 'grid',
			itemId: 'detail_grid',
			// id: 'doc_ins_detail_grid',
			flex: 1,
			frame: true,
			store: 'surgydoctor.SurgyInspectDetail',
			autoScroll: true,

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'surgydoctor.SurgyInspectDetail',
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

			columns: [{
				text: '化验项目',
				dataIndex: 'item',
				flex: 1,
				minWidth: 140,
				
			}, {
				text: '中文名称',
				dataIndex: 'itemStr',
				flex: 1.2,
				minWidth: 220,
			}, {
				text: '结果',
				dataIndex: 'result',
				flex: 0.9,
				minWidth: 100,
				editor: {
					xtype: 'numberfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}, {
				text: '标志',
				dataIndex: 'flag',
				flex: 0.9,
				minWidth: 60,
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}, {
				text: '参考范围',
				dataIndex: 'scope',
				flex: 0.9,
				minWidth: 140,
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}, {
				text: '单位',
				dataIndex: 'unit',
				flex: 0.9,
				minWidth: 100,
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}, {
				text: '判断L',
				dataIndex: 'min',
				flex: 0.9,
				minWidth: 100,
				editor: {
					xtype: 'numberfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}, {
				text: '判断H',
				dataIndex: 'max',
				flex: 0.9,
				minWidth: 100,
				editor: {
					xtype: 'numberfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
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

		console.log('-- Wj.view.surgydoctor.SurgyPtInspectData init over.--');

	}

});