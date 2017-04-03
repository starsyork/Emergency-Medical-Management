Ext.define('Wj.model.nurse.NewAdvice', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'name', sortType: 'asString' },
		{ name: 'bedNum', sortType: 'asInt' },
		{ name: 'type', sortType: 'asString' },
		{ name: 'content', sortType: 'asString' },
		{ name: 'startTime', sortType: 'asString' ,convert: Wj.util.convertTime},
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.NurseNewAdvice,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	}

});