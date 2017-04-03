Ext.define('Wj.model.doctor.Transfer', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sorType: 'asInt' },
		{ name: 'ptname', sorType: 'asString' },
		{ name: 'dname', sorType: 'asString' },
		{ name: 'tname', sorType: 'asString' },
		{ name: 'pid', sorType: 'asInt' },
		{ name: 'time', sortType: 'asString' ,convert: Wj.util.convertTime},
		{ name: 'content', sortType: 'asString' },	
		{ name: 'applyId', sortType: 'asInt' },
		{ name: 'status', sortType: 'asString' },
		{ name: 'illustration', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.Transfer,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	}

});