Ext.define('Wj.view.admin.AddDrug', {
	extend: 'Wj.view.util.AddRecord',
	alias: 'widget.adddrug',

	title: '添加药品',

	width: 420,
	height: 420,

	initComponent: function(){


		this.callParent(arguments);

		this.down('form').add([
		 {
			xtype: 'textfield',
			fieldLabel: '药品编码',
			name: 'drugcode'
		},{
			xtype: 'textfield',
			fieldLabel: '药品名称',
			//store:
			name: 'name'
		}, {
			xtype: 'numberfield',
			fieldLabel: '药品规格',
			name: 'spec'
		}, {
			xtype: 'textfield',
			fieldLabel: '药品单位',
			name: 'units'
		},	
		 {
			xtype: 'textfield',
			fieldLabel: '剂量类型',
			name: 'doseType'
		}, 
		 {
			xtype: 'textfield',
			fieldLabel: '药品特性',
			name: 'property'
		}, 
		 {
			xtype: 'numberfield',
			fieldLabel: '混合单位',
			name: 'mixUnit'
		}, 
		{
			xtype: 'textfield',
			fieldLabel: '剂量单位',
			name: 'doseUnit'
		}, 
		{
			xtype: 'numberfield',
			fieldLabel: '药品指标',
			name: 'indicator'
		},	 
		{
			xtype: 'numberfield',
			fieldLabel: '药品数量',
			name: 'amount'
		}
		
		]);

	}
})