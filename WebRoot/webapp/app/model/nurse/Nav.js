Ext.define('Wj.model.nurse.Nav', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },		// bed's id
		{ name: 'ptid', sortType: 'asInt' },	// patient's id
		{ name: 'statusStr', sortType: 'asString' },	// bed's status
		{ name: 'name', sortType: 'asString' },
		{ name: 'bedNum', sortType: 'asInt' },
		{ name: 'sex', sortType: 'asString', convert: Wj.util.convertSex },
		{ name: 'rfid', sortType: 'asString' },
		{ name: 'age', sortType: 'asInt' },
		{ name: 'woundType', sortType: 'asString' },
		{ name: 'woundTime', sortType: 'asString' ,convert: Wj.util.convertTime},
		{ name: 'woundAddr', sortType: 'asString' },
		{ name: 'docId', sortType: 'asInt' },
		{ name: 'docName', sortType: 'asString' },
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.NurseNav,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	}

});