Ext.define('Wj.model.nurse.UnAllocBed', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'bedNum', sortType: 'asInt' },	// bedNum
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.UnAllocBed,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	}

});