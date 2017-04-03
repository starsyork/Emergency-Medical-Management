Ext.define('Wj.controller.User', {
	extend: 'Ext.app.Controller',

	views: ['user.Login', 'user.Setup'],

	refs: [{
		selector: 'userlogin',
		ref: 'userlogin'
	}, {
		selector: 'userlogin > form',
		ref: 'loginform'
	}, {
		selector: 'userlogin > form > #v_img',
		ref: 'vimg'
	}, {
		selector: 'userlogin > form > textfield[name=userType]',
		ref: 'usertype'
	}, {
		selector: 'userlogin > form > textfield[name=loginName]',
		ref: 'username'
	}, {
		selector: 'userlogin > form > textfield[name=password]',
		ref: 'userpass'
	}, {
		selector: 'userlogin > toolbar > #is_save_pass',
		ref: 'issavepass'
	}, {
		selector: 'usersetup',
		ref: 'usersetup'
	}],

	init: function(){

		console.log('-- Wj.controller.User init. --');

		this.control({

			'viewport': {
				render: this.onViewportRendered
			},

			'userlogin': {

				afterrender: function(c) {
					this.loadUserData();
				},

				boxready: function(c, w, h, opts) {
					var nav = new Ext.util.KeyNav({
						target: this.getUserlogin().getEl(),
						enter: function(e) {
							Wj.controller.User.userLogin();
						}
					});
				}
			},

			'userlogin > form > #v_img': {
				render: function(){
					this.getVimg().getEl().on('click', this.refreshVImg);
				}
			},

			'userlogin > toolbar > #user_login': {
				click: function(){

					this.userLogin();
					
				}
			},

			'usersetup > form': {

				afterrender: function(t, opt){

					var url = Wj.url.GetUserData;
					var par = {};

					Wj.util.pullData.call(t, url, par, function(res){
					
						console.log('res:');
						console.log(res);
						console.log('res.results[0]:');
						console.log(res.results[0]);

						if(res.results){
							var r = res.results;
							delete r.password;
							this.getForm().setValues(r);
						}
					});

				}
			}

		});

		console.log('-- Wj.controller.User init over. --');

	},

	onViewportRendered: function(){

		if(Wj.cp.get('Wj-status')){

			this.showView(Wj.cp.get('Wj-usertype'));
		} else
			Ext.widget('userlogin');

	},

	userLogin: function() {

		var vc = this.getLoginform().down('#verify_code');

		var loginData = {
			userType: this.getUsertype().getValue(),
			loginName: this.getUsername().getValue(),
			password: this.getUserpass().getValue()
			// vcode: vc.getValue(),
		};

		console.log('-- loginData --');
		console.log(loginData);

		this.login(loginData);
	},

	login:function(data){

		Ext.Ajax.request({
			url: Wj.url.Login,
			scope: this,
			method: 'get',
			params: data,
			success: this.handleLoginSuccess,
			failure: this.handleLoginFailed
		});

	},

	handleLoginSuccess: function(res, act){

		var r = Ext.decode(res.responseText);
			
		console.log(r);

		if(r && r.success === true){

			Wj.tmp.loginData = r.results;

			var c = Wj.controller.User;
			var da = act.params;

			var cookieData = {
				isSavePass: c.getIssavepass().getValue(),
				status: true
			};
			Ext.Object.merge(cookieData, da);
			c.setCookie(cookieData);

			c.getUserlogin().close();

			c.showView(da.userType);

		} else {

			Wj.controller.User.handleLoginFailed(res, act);
		}


	},

	handleLoginFailed: function(res, act){

		Wj.cp.set('Wj-status', false);

		var c = Wj.controller.User;
		
		var r = Ext.decode(res.responseText);
			
		if(r && r.msg){
			
			Ext.Msg.alert('登录失败', r.msg, function(){
				// this.getVimg().getEl().dom.src = Wj.url.VCode + '?dc=' +  new Date().getTime();
				// Ext.getCmp('v_img').updateLayout();
				;	// do nothing
			}, this);

		}
		else
			Ext.Msg.alert('登录失败', '请重新检查网络.');

	},

	showView: function(t){

		switch(t){
			case 0:
				this.getController('Admin').showAdminView();
				break;
			case 1:
				this.getController('Doctor').showDocView();
				break;
			case 2:
				this.getController('Nurse').showNurseView();
				break;
			case 3:
				this.getController('ExamDoctor').showExamDocView();
				break;
			case 4:
				this.getController('SurgyDoctor').showSurgyDocView();
				
				break;
			default:
				break;
		}
	},

	refreshVImg: function(e, t, eOpts){

		t.src = Wj.url.VCode + '?dc=' +  new Date().getTime();
		Ext.getCmp('v_img').updateLayout();

	},

	loadUserData: function(){

		var userData = {
			userType: Wj.cp.get('Wj-usertype'),
			loginName: Wj.cp.get('Wj-username'),
			password: Wj.cp.get('Wj-userpass'),
			isSavePass: Wj.cp.get('Wj-issavepass')
		};

		this.getUsertype().setValue(userData.userType);
		this.getUsername().setValue(userData.loginName);
		this.getUserpass().setValue(userData.password);
		this.getIssavepass().setValue(userData.isSavePass);
	},

	setCookie: function(data){
		var cp = Wj.cp;
		
		cp.set('Wj-status', data.status);
		cp.set('Wj-usertype', data.userType);
		cp.set('Wj-username', data.loginName);
		cp.set('Wj-issavepass', data.isSavePass);

		if(data.isSavePass)
			cp.set('Wj-userpass', data.password);
		else
			cp.clear('Wj-userpass');

	}

	
})