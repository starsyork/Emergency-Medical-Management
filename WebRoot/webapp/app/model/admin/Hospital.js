Ext.define('Wj.model.admin.Hospital', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'hospName', sortType: 'asString' },
	],
	proxy: {
		type: 'ajax',
		url: Wj.url.getHospital,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}
});