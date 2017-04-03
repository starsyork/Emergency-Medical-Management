Ext.define('Wj.model.surgydoctor.SurgyPtCondition', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'pulse', sortType: 'asString', convert: Wj.util.convertZeroToNull },
		{ name: 'breath', sortType: 'asString', convert: Wj.util.convertZeroToNull },
		{ name: 'diastolic', sortType: 'asString', convert: Wj.util.convertZeroToNull },
		{ name: 'systolic', sortType: 'asString', convert: Wj.util.convertZeroToNull },
		{ name: 'temperature', sortType: 'asString', convert: Wj.util.convertZeroToNull },
		{ name: 'comment', sortType: 'asString' },
		{ name: 'time', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPtCondition,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});