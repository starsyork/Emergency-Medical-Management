Ext.define('Wj.model.volunteer.VolunteerPerm', {
	extend: 'Ext.data.Model',
	fields: [
		{ name: 'user.id', sortType: 'asInt' },
		{ name: 'name', sortType: 'asString' },
		{ name: 'sex', sortType: 'asInt' },
		{ name: 'specialty', sortType: 'asString' },
		{ name: 'IDcard', sortType: 'asString' },
		{ name: 'age', sortType: 'asString' },
		{ name: 'phone', sortType: 'asString' },
		{ name: 'address', sortType: 'asString' },
		{ name: 'user.loginName', sortType: 'asString' },
		{ name: 'user.password', sortType: 'asString' }
		
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.GetVoluPerm,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});