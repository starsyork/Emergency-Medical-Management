Ext.define('Wj.model.surgydoctor.SurgyAdvContents', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'content', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.SurgyAdvContents,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});