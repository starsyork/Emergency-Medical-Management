Ext.define('Wj.model.drug.AllDrug', {
	extend: 'Ext.data.Model',
	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'drugcode', sortType: 'asString' },
		{ name: 'name', sortType: 'asString' },
//		{ name: 'drugUnitPrice', sortType: 'asInt' },
		{ name: 'amount', sortType: 'asInt' },
		{ name: 'need', sortType: 'asInt' },
		{ name: 'entryTime', sortType: 'asString' ,convert: Wj.util.convertTime}
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.GetAllDrugA,
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