Ext.define('Wj.model.surgydoctor.SurgyPtAdviceLook', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },
		{ name: 'type', sortType: 'asString' },		// 长期/临时
		{ name: 'type2', sortType: 'asString' },	// 医嘱类型
		{ name: 'content', sortType: 'asString' },
		{ name: 'startTime', sortType: 'asString' },
		{ name: 'endTime', sortType: 'asString' },
		{ name: 'state', sortType: 'asString' },
		{ name: 'spec', sortType: 'asString' },
		{ name: 'dose', sortType: 'asString' },	// drug dose
		{ name: 'usage', sortType: 'asString' },	// usage of drug
		{ name: 'frequency', sortType: 'asInt' }	// use times
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPtAdviceLook,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});