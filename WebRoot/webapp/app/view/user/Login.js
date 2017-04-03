Ext.define('Wj.view.user.Login', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.userlogin',
	id: 'user_login_win',

	requires: ['Wj.store.user.Type'],

	store: 'user.Type',

	// border: false,
	border: 20,
	style: {
	    borderColor: '#fff',
	    borderStyle: 'solid',
	},
	closable: false,
	resizable: false,
	floating: true,

	model: true,

	width: 900,
	height: 500,
	// y: 100,

	layout: {
		type: 'vbox',
		align: 'stretch',
		pack: 'start',
	},

	closable: false,
	resizable: false,
	autoShow: true,

	initComponent: function(){

		console.log('-- Wj.view.user.Login init. --');

		this.items = [{
			xtype: 'panel',
			border: false,
			flex: 1,
			bodyStyle: {
				'background-image': 'url(img/loginBack.jpg)',
				'background-position': 'center',
				'background-repeat': 'repeat',
			}
		}, {
			xtype: 'form',
			height: 40,

			layout: {
				type: 'hbox',
				align: 'center',
				pack: 'start',
			},

			border: false,
			frame: false,
			// bodyStyle: 'background:#DFE9F6',
			defaultType: 'textfield',
			fieldDefaults: {
				labelWidth: 60,
				labelAlign: 'right',
				width: 160,
				labelSeparator: ':',
				msgTarget: 'side',
				style: {
					margin: '7px 10px',
				},
			},

			items: [{
				xtype: 'combobox',
				id: 'user_type',
				name: 'userType',
				store: 'user.Type',
				fieldLabel: '用户类型',
				displayField: 'name',
				valueField: 'value',
				editable: false,
				allowBlank: false,
				blankText: '用户类型不能为空',
				tabIndex: 1,
				style: {
					margin: '7px 10px 7px 200px',
				},
			}, {
				id: 'user_name',
				// name: 'userName',
				name: 'loginName',
				fieldLabel: '用户名',
				allowBlank: false,
				blankText: "用户名不能为空",
				tabIndex: 2,
				// regex: /^[a-zA-Z]{1}([a-zA-Z0-9._]){3,11}$/,
            	// regexText: '请填写合法的用户名(必须以字母开头,长度4-12位)',
			}, {
				id: 'user_pass',
				// name: 'userPass',
				name: 'password',
				fieldLabel: '密码',
				inputType: 'password',
				allowBlank: false,
				blankText: "密码不能为空",
				tabIndex: 3,
				// regex: /^[a-zA-Z]{1}([a-zA-Z0-9._]){3,11}$/,
            	// regexText: '请填写合法的密码(必须以字母开头,长度4-12位)',
			}/*, {
				id: 'verify_code',
				name: 'vcode',
				fieldLabel: '验证码',
				allowBlank: false,
				blankText: "验证码不能为空",
				tabIndex: 4,
				regex: /^[a-zA-Z0-9]{4}$/,
            	regexText: '验证码格式不正确(请确保4位数字或字母)',
			}, {
				xtype: 'component',
				id: 'v_img',
				autoEl: {
					tag: 'img',
					align: 'center',
					//src: '../getVerify',
					src: Wj.url.VCode,
				},
				style: {
					marginLeft: '10px',
					marginTop: '7px',
				},
				tabIndex: 5,
			}*/],

		}];

		this.dockedItems = [{
			xtype: 'toolbar',
			style: {
				'background-color': '#eee',
				// 'background-color': '#DFE9F6',
			},
			dock: 'bottom',
			// ui: 'footer',
			layout: {
				align: 'left'
			},
			items: [{
				xtype: 'checkbox',
				style: {
					margin: '0px 0px 0px 400px'
				},
				hideLabel: true,
				itemId: 'is_save_pass',
				boxLabel: '记住密码',
			}, {
				xtype: 'tbseparator',
				style: {
					margin: '0px 0px 0px 20px'
				},
			}, {
				style: {
					margin: '0px 0px 0px 10px',
				},
				text: '&nbsp;&nbsp;登&nbsp;录&nbsp;&nbsp;',
				icon: 'icon/drop-yes.gif',
				itemId: 'user_login',
				tabIndex: 5,
				formBind: true,
			}/*, {
				xtype: 'tbseparator',
				style: {
					margin: '0px 0px 0px 220px',
				},
			}, {
				style: {
					margin: '4px 0px 0px 10px',
				},
				xtype: 'label',
				text: '  点击图片刷新验证码',
			}*/]
		}];

		console.log('-- Wj.view.user.Login init over. --');

		this.callParent(arguments);

	}

});