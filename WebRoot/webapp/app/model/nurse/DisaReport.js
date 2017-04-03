Ext.define('Wj.model.nurse.DisaReport', {
	extend: 'Ext.data.Model',
	fields: [
	 		{ name: 'id', sortType: 'asInt' },
			{ name: 'name', sortType: 'asString' },
			{ name: 'number', sortType: 'asInt' },
			{ name: 'zone', sortType: 'asString' },
			{ name: 'time', sortType: 'asString' ,convert: Wj.util.convertTime},
			{ name: 'degree', sortType: 'asString'},
			{ name: 'proc', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.GetDisaReport,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});