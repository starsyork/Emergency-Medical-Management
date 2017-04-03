Ext.define('Wj.model.Report', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sorType: 'asInt' },
		{ name: 'pid', sorType: 'asInt' },
		{ name: 'time', sortType: 'asString' },
		{ name: 'type', sortType: 'asString' },
		{ name: 'measure', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.Reports,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});