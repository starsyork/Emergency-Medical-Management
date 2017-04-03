Ext.define('Wj.model.surgydoctor.SurgyAdvExecRec', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'exEr', sortType: 'asString' },		// execute person
		{ name: 'exTime', sortType: 'asString' ,convert: Wj.util.convertTime},	// execute time
		{ name: 'exNote', sortType: 'asString' } 	// execute note
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.SurgyAdvExecRec,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});