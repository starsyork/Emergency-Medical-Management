Ext.define('Wj.model.drug.Drugdetail', {
	extend: 'Ext.data.Model',
	fields: [
		{ name: 'id', sortType: 'asString' },
		{ name: 'drugcode', sortType: 'asString' },
		{ name: 'name', sortType: 'asString' },
//		{ name: 'drugUnitPrice', sortType: 'asInt' },
		{ name: 'amount', sortType: 'asInt' },
		{ name: 'need', sortType: 'asInt' },
		{ name: 'units', sortType: 'asString' },
		{ name: 'doseType', sortType: 'asString' },
		{ name: 'property', sortType: 'asString' },
		{ name: 'doseUnit', sortType: 'asString' },
		{ name: 'code', sortType: 'asString' },
		{ name: 'mixUnit', sortType: 'asFloat ' },
		{ name: 'indicator', sortType: 'asFloat ' },
		
		],

	proxy: {
		type: 'ajax',
		url: Wj.url.GetDrug,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});

/*
Ext.define('Wj.model.drug.Drug', {
	extend: 'Ext.data.Model',
	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'drugName', sortType: 'asString' },
//		{ name: 'drugUnitPrice', sortType: 'asInt' },
		{ name: 'drugStock', sortType: 'asInt' },
		{ name: 'drugLack', sortType: 'asInt' },
		{ name: 'drugRegDate', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.GetDrug,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});*/