Ext.define('Wj.model.rtanalysis.RTAnalysis', {
	extend: 'Ext.data.Model',
	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'LInjNum', sortType: 'asInt' },
		{ name: 'MInjNum', sortType: 'asInt' },
		{ name: 'SInjNum', sortType: 'asInt' },
		{ name: 'totalDeIn', sortType: 'asInt' },
		{ name: 'date', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.GetRTAnalysis,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});