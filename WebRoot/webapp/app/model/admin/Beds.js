Ext.define('Wj.model.admin.Beds', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },		// bed's id
		{ name: 'ptid', sortType: 'asInt' },	// patient's id
		{ name: 'statusStr', sortType: 'asString' },	// bed's status
		{ name: 'name', sortType: 'asString' },
		{ name: 'bedNum', sortType: 'asInt' },
		{ name: 'rfid', sortType: 'asString' },
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.AdminBeds,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	}

});