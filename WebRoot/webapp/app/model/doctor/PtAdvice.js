Ext.define('Wj.model.doctor.PtAdvice', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'pid', sortType: 'asInt' },
		{ name: 'type', sortType: 'asString' },		// 长期/临时
		{ name: 'type2', sortType: 'asString' },	// 医嘱类型
		{ name: 'content', sortType: 'asString' },
		{ name: 'startTime', sortType: 'asString' ,convert: Wj.util.convertTime},
		{ name: 'endTime', sortType: 'asString',convert: Wj.util.convertTime },
		{ name: 'state', sortType: 'asString' },
		{ name: 'spec', sortType: 'asString' },
		{ name: 'dose', sortType: 'asString' },	// drug dose
		{ name: 'usage', sortType: 'asString' },	// usage of drug
		{ name: 'frequency', sortType: 'asInt' },	// use times
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.DocPtAdvice,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	}

});