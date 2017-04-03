Ext.define('Wj.view.Banner', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.banner',

	border: true,
	frame: true,
	height: 100,

	html: '<div align=center style="height:100px;line-height:100px;background-color:transparent;' + 
		'"><div align=center style="height:100px;line-height:90px;' +
		'background-image:url(img/banner4.jpg)"><div style="width:900px;height:100px;position:relative;">' +
		'<img src="img/wj_logo.gif" style="position:absolute;' + 
		'width:90px;height:90px;left:70px;top:2px;"/>' +
		/* + '<img src="img/logo140X50.png"' +
		' style="height:70px;width:70px;vertical-align:top;"/>'*/'<span style="vertical-align:middle;' + 
		'color:#FFF;text-shadow:0 0 20px #222;font-weight:bold"><font size=6.8em>应急救援移动医疗信息系统Web工作站' +
		'</font></span></div></div></div>',

	initComponent: function(){

		console.log('-- Wj.view.Banner init. --');

		this.dockedItems = [{
			xtype: 'toolbar',
			dock: 'right',
			layout: 'vbox',
			items: [{
				xtype: 'button',
				itemId: 'log_out',
				text: '退出',
				flex: 1,
				handler: function(){
					Wj.cp.clear('Wj-status');
					window.location = 'index.htm';
				},
			}, {
				xtype: 'tbseparator',
			}, {
				xtype: 'button',
				itemId: 'modify_pass',
				text: '用户设置',
				flex: 1,
				handler: Wj.util.userSetup,
			}]
		}];

		this.callParent(arguments);

		console.log('-- Wj.view.Banner init over. --');

	},

});