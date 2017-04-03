Ext.define('Wj.model.nurse.Doctors', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },	// docId
		{ name: 'userName', sortType: 'asString' },	// docName
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.listDoctors,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	}

});