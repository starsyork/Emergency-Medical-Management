Ext.define('Wj.model.admin.Sector', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'secName', sortType: 'asString' },
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.Sectors,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	}

});