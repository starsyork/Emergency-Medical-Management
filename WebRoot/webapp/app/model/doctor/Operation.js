Ext.define('Wj.model.doctor.Operation', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sorType: 'asInt' },
		{ name: 'pid', sorType: 'asInt' },
		{ name: 'applydoc', sorType: 'asString' },
		{ name: 'operationtime', sortType: 'asString',convert: Wj.util.convertTime  },
		{ name: 'operationdoc', sorType: 'asString' },
		{ name: 'applytime', sortType: 'asString' ,convert: Wj.util.convertTime  },
		{ name: 'status', sortType: 'asString' },	
		{ name: 'content', sortType: 'asString' },
		{ name: 'illustration', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.Operation,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});