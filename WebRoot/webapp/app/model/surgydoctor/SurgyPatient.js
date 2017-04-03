Ext.define('Wj.model.surgydoctor.SurgyPatient', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'sector', sortType: 'asInt' },
		// { name: 'secName', sortType: 'asString' },
		{ name: 'docId', sortType: 'asInt' },
		{ name: 'docName', sortType: 'asString' },
		{ name: 'name', sortType: 'asString' },
		{ name: 'sex', sortType: 'asString'},
		{ name: 'rfid', sortType: 'asString' },
		{ name: 'age', sortType: 'asInt' },
		{ name: 'bedNum', sortType: 'asInt' },
		{ name: 'woundType', sortType: 'asString' },
		{ name: 'woundTime', sortType: 'asString' },
		{ name: 'woundAddr', sortType: 'asString' },
		{ name: 'examptsec',sortType:'asString'},
		{ name: 'surgyptsec',sortType:'asString'},
		{ name: 'photourl',sortType:'asString'},
		{ name: 'photodec',sortType:'asString'}
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.SurgyDocPatients,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});