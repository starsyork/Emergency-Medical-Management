Ext.define('Wj.view.admin.AddInjury', {
	extend: 'Wj.view.util.AddRecord',
	alias: 'widget.addinjury',

	title: '添加伤亡情况',

	width: 420,
	height: 220,

	initComponent: function(){


		this.callParent(arguments);

		this.down('form').add([{
			xtype: 'numberfield',
			fieldLabel: '轻伤',
			name: 'LInjNum',
			regex:/^\S{1,20}$/,
			regexText:'请输入20个以内字符'
		}, {
			xtype: 'numberfield',
			fieldLabel: '中伤',
			name: 'MInjNum',
			regex:/^\S{1,20}$/,
			regexText:'请输入20个以内字符'
		}, {
			xtype: 'numberfield',
			fieldLabel: '重伤',
			name: 'SInjNum',
			regex:/^\S{1,20}$/,
			regexText:'请输入20个以内字符'
		}, {
			xtype: 'numberfield',
			fieldLabel: '死亡',
			name: 'Death',
			regex:/^\S{1,20}$/,
			regexText:'请输入20个以内字符'
		},{
			xtype: 'textfield',
			fieldLabel: '更新日期',
			name: 'Date',
			regex:/^\S{1,50}$/,
			regexText:'请输入50个以内字符'
		}]);

	}
})