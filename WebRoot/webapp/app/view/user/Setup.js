Ext.define('Wj.view.user.Setup', {
	extend: 'Ext.window.Window',
	alias: 'widget.usersetup',

	modal: true,
	closable: true,
	resizable: false,
	layout: 'fit',

	title: '用户设置',

	width: 400,
	height: 260,

	initComponent: function(){

		this.items = [{
			xtype: 'form',
			padding: '10 2 10 2',
			border: false,
			frame: true,
			// bodyStyle: 'background:#DFE9F6',
			defaults: {
				labelSeparator: ' : ',
				msgTarget: 'side',
				anchor: '100%',
				style: {
					margin: '4px 10px',
				},
			},
			defaultType: 'textfield',

			items:[{
				xtype:'textfield',
                fieldLabel: '旧密码*',
                name: 'oldPassword',
                id:'oldPassword',
                inputType: 'password',
                allowBlank: false,
                blankText: '密码不能为空',
          //       regex: /^[a-zA-Z]{1}([a-zA-Z0-9._]){3,11}$/,
        		// regexText: '请填写合法的密码(必须以字母开头,长度4-12位)',
            }, {
            	xtype:'textfield',
                fieldLabel: '新密码',
                name: 'password',
                id:'newPassword',
                inputType: 'password',
                // regex: /^[a-zA-Z]{1}([a-zA-Z0-9._]){3,11}$/,
        		// regexText: '请填写合法的密码(必须以字母开头,长度4-12位)',
            }, {
            	xtype:'textfield',
                fieldLabel: '新密码确认',
                name: 'newPassword1',
                inputType: 'password',
                vtype:'password',
                vtypeText:"两次口令不一致",
                confirmTo:'newPassword',
            }, {
				fieldLabel: '用户名*',
				name: 'loginName',
				itemId: 'userName',
				allowBlank: false,
				blankText: '用户名不能为空',
				// regex: /^[a-zA-Z]{1}([a-zA-Z0-9._]){3,11}$/,
        		// regexText: '请填写合法的用户名(必须以字母开头,长度4-12位)',
			}, {
				fieldLabel: '姓名*',
				name: 'userName',
				itemId: 'name',
				allowBlank: false,
				blankText: '姓名不能为空',
				regex:/^\S{1,20}$/,
				regexText:'请输入20个以内字符',
			}]

		}];

		this.buttons = [{
			text: '确定',
			itemId: 'accept',
			icon: 'icon/accept.png',
			handler: Wj.util.confirmUserSetup,
		}, {
			text: '重置',
			itemId: 'reset',
			icon: 'icon/reset.png',
			handler: function(b){

				var fm = b.up('window').down('form').getForm(),
					url = Wj.url.GetUserData,
					par = {};

				Wj.util.pullData.call(this, url, par, function(res){

					if(res.results){
						fm.reset();
						var r = res.results;
						delete r.password;
						fm.setValues(r);
					}

				});
				
			},
		}];

		this.callParent(arguments);

	}
})