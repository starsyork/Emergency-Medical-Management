Ext.define('Wj.model.examdoctor.ExamNewPatient', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'name', sortType: 'asInt' },
		{ name: 'sex', sortType: 'asString' },
		{ name: 'rfid', sortType: 'asString' },
		{ name: 'age', sortType: 'asInt' },
		{ name: 'bedNum', sortType: 'asInt' },
		{ name: 'woundType', sortType: 'asString' },
		{ name: 'woundTime', sortType: 'asString',convert: Wj.util.convertTime },
		{ name: 'woundAddr', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.ExamNewPt,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});