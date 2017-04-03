Ext.define('Wj.model.examdoctor.ExamPatient', {
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
		{ name: 'woundTime', sortType: 'asString',convert: Wj.util.convertTime },
		{ name: 'woundAddr', sortType: 'asString' },
		{ name: 'examptsec',sortType:'asString'}
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.ExamDocPatients,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});