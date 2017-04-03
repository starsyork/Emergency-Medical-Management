Ext.define('Wj.model.user.Doctor', {
	extend: 'Ext.data.Model',
	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'loginName', sortType: 'asString' },
		{ name: 'password', sortType: 'asString' },
		{ name: 'userName', sortType: 'asString' },
		{ name: 'sector', sortType: 'asString' },
		{ name: 'roleStr', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.GetDoc,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});