Ext.define('Wj.model.admin.PtStatis', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'secName', sortType: 'asString' },
		{ name: 'machine', sortType: 'asInt' },
		{ name: 'pressasphyxia', sortType: 'asInt' },
		{ name: 'hunger', sortType: 'asInt' },
		{ name: 'drowning', sortType: 'asInt' },
		{ name: 'burn', sortType: 'asInt' },
		{ name: 'coldinjury', sortType: 'asInt' },
		{ name: 'poisoning', sortType: 'asInt' },
		{ name: 'total', sortType: 'asInt' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.GetPtStatis,
		//url: 'data/test/admin/ptstatistic/read.json',
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	}

});