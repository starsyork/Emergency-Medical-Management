Ext.define('Wj.model.user.Nurse', {
	extend: 'Ext.data.Model',
	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'loginName', sortType: 'asString' },
		{ name: 'password', sortType: 'asString' },
		{ name: 'userName', sortType: 'asString' },
		{ name: 'sector', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.GetNurse,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});