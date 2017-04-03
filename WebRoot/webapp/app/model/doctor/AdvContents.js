Ext.define('Wj.model.doctor.AdvContents', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'content', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.AdvContents,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});