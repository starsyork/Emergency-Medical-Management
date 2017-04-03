Ext.define('Wj.view.admin.AddDisa', {
	extend: 'Wj.view.util.AddRecord',
	alias: 'widget.adddisareport',

	title: '添加疫情信息',

	width: 420,
	height: 420,

	initComponent: function(){


		this.callParent(arguments);

		this.down('form').add([
		 {
			xtype: 'textfield',
			fieldLabel: '疫情名称',
			allowBlank: false,
			blankText: '编号不能为空',
			name: 'name'
				
		}, 
		
		{
			xtype: 'numberfield',
			fieldLabel: '疫情人数',
			allowBlank: false,
			blankText: '编号不能为空',
			name: 'number'
		}, 
		
		{
			xtype: 'textfield',
			fieldLabel: '发生区域',
			allowBlank: false,
			blankText: '编号不能为空',
			name: 'zone'
		}, 
		
		{
			xtype: 'textfield',
			fieldLabel: '疫情程度',
			allowBlank: false,
			blankText: '编号不能为空',
			name: 'degree'
		}, 		
		{
			xtype: 'textfield',
			fieldLabel: '处治方法',
			allowBlank: false,
			blankText: '编号不能为空',
			name: 'Proc'
		}				
		]);

	}
})