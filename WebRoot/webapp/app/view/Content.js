Ext.define('Wj.view.Content', {
	extend: 'Ext.tab.Panel',
	alias: 'widget.content',

	layout: 'fit',
	frame: true,

	tbar: [{
		xtype: 'label',
		padding: '2 2 2 2',
		id: 'infobar',
		text: '欢迎！',
	}],

	initComponent: function(){

		console.log('-- Wj.view.Content init. --');

		this.callParent(arguments);

		console.log('-- Wj.view.Content init over. --');

	}
});