Ext.define('Wj.model.doctor.PtCourse', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sorType: 'asInt' },
		{ name: 'pid', sorType: 'asInt' },
		{ name: 'addTime', sortType: 'asString' ,convert: Wj.util.convertTime},
		{ name: 'description', sortType: 'asString' },	// 病程描述
		{ name: 'doc', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.DocPtCourse,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	}

});