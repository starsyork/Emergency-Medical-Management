Ext.define('Wj.view.admin.DrugMgr', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.admindrugmgr',

	requires: ['Wj.view.util.Pagingtoolbar'],

	closable: true,
	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '药品统计',

	initComponent: function(){

		console.log('-- Wj.view.admin.DrugMgr init.--');

		this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			store: 'drug.Drug',
			autoScroll: true,

			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',

				items: [{
					text: '添加',
					icon: 'icon/add.png',
					itemId: 'add',
					handler: Wj.controller.Admin.addDrug
				}, {
					text: '删除',
					icon: 'icon/delete.gif',
					itemId: 'remove',
					disabled: true,
					handler: function(){
						alert('remove!');
					},
					handler: Wj.controller.Admin.removeDrug
				}]
			}, {
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'drug.Drug',
				displayMsg: '- 双击进行修改 - 当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],

			columns: [{
				text: '序号',
				dataIndex: 'id',
				flex: 0.6,
				minWidth: 40,
				allowBlank: false,
				blankText: '编号不能为空',
				hidden: true, 
				hideLabel:true ,
			},
			{
				text: '编号',
				dataIndex: 'drugcode',
				flex: 0.6,
				minWidth: 40,
				allowBlank: false,
				blankText: '编号不能为空'
			}, {
				text: '名称',
				flex: 1,
				minWidth: 140,
				dataIndex: 'name',
				allowBlank: false,
				blankText: '姓名不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}, 
			{
				text: '库存/单位',
				flex: 1,
				minWidth: 200,
				dataIndex: 'amount',
				allowBlank: false,
				blankText: '库存不能为空',
				editor: {
					xtype: 'numberfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			},
			{
				text: '短缺/单位',
				flex: 1,
				minWidth: 200,
				dataIndex: 'need',
				allowBlank: false,	
				editor: {
					xtype: 'numberfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				},
				renderer:function(value){
					if(value > 0){
						var vs = value.toString();
						return "<span style='color:red;'>"+vs+"</span>";
					}
					if(value == 0){
						var vs = value.toString();
						return "<span style='color:black;'>"+vs+"</span>";
					}
				}
				
			},
			{
				text: '入库时间',
				flex: 1,
				minWidth: 200,
				dataIndex: 'entrytime',
				allowBlank: false,
				blankText: '入库时间不能为空',
				editor: {
					xtype: 'datefield',
					name: 'entrytime',
					itemId: 'entrytime',					
					format: 'Y/m/d',
					readOnly: false,
					allowBlank: false,
					blankText: '入库时间不能为空',
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

		}];

		this.callParent(arguments);

		console.log('-- Wj.view.admin.DrugMgr init over.--');

	}

});