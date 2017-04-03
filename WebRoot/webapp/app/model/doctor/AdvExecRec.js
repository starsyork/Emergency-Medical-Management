Ext.define('Wj.model.doctor.AdvExecRec', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'exEr', sortType: 'asString' },		// execute person
		{ name: 'exTime', sortType: 'asString' },	// execute time
		{ name: 'exNote', sortType: 'asString' }, 	// execute note
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.AdvExecRec,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	}

});