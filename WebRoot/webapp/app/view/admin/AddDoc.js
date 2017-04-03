Ext.define('Wj.view.admin.AddDoc', {
	extend: 'Wj.view.util.AddRecord',
	alias: 'widget.adddoc',

	title: '添加医生账号',

	width: 420,
	height: 220,

	initComponent: function(){


		this.callParent(arguments);

		this.down('form').add([{
			xtype: 'textfield',
			fieldLabel: '用户名',
			name: 'loginName',
			// regex: /^[a-zA-Z]{1}([a-zA-Z0-9._]){3,11}$/,
            // regexText: '请填写合法的用户名(必须以字母开头,长度4-12位)',
		}, {
			xtype: 'textfield',
			fieldLabel: '密码',
			name: 'password',
			// regex: /^[a-zA-Z]{1}([a-zA-Z0-9._]){3,11}$/,
            // regexText: '请填写合法的密码(必须以字母开头,长度4-12位)',
		}, {
			xtype: 'textfield',
			fieldLabel: '姓名',
			name: 'userName',
			regex:/^\S{1,20}$/,
			regexText:'请输入20个以内字符',
		}, {
			xtype: 'combobox',
			editable: false,
			fieldLabel: '分区',
			name: 'sector',
			store: 'admin.Sector',
			valueField: 'secName',
			displayField: 'secName',
		},{
			xtype: 'combobox',
			editable: false,
			fieldLabel: '角色',
			name: 'roleStr',
			store: Wj.consts.Doc,
			valueField: 'roleStr',
			displayField: 'roleStr',
		},
		
		]);

	}
})