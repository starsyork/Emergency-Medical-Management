Ext.define('Wj.view.admin.Nav', {
	extend: 'Ext.tree.Panel',
	alias: 'widget.adminnav',

	requires: ['Wj.store.nav.Admin'],

	store: 'nav.Admin',

	rootVisible: false,
	lines: false,
	useArrows: true,

	initComponent: function(){

		console.log('-- Wj.view.admin.Nav init. --')

		this.callParent(arguments);

		console.log('-- Wj.view.admin.Nav init over. --')

	}

});