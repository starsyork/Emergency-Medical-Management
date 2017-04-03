Ext.define('Wj.view.util.AddRecord', {
	extend: 'Ext.window.Window',
	alias: 'widget.addrecord',

	modal: true,
	closable: true,
	resizable: false,
	layout: 'fit',

	width: 200,
	height: 200,

	initComponent: function(){

		this.items = [{
			xtype: 'form',
			padding: '10 2 10 2',
			border: false,
			// bodyStyle: 'background:#DFE9F6',
			frame: true,
			defaults: {
				labelSeparator: ' : ',
				msgTarget: 'side',
				anchor: '100%',
				style: {
					margin: '4px 10px',
				},
			},
			defaultType: 'textfield',

		}];

		this.buttons = [{
			text: '确定',
			itemId: 'accept',
			icon: 'icon/accept.png',
		}, {
			text: '重置',
			itemId: 'reset',
			icon: 'icon/reset.png',
			handler: function(){
				this.up('window').down('form').getForm().reset();
			},
		}];

		this.callParent(arguments);
	}

});