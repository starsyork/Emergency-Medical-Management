Ext.define('Wj.model.surgydoctor.SurgyPtCourse', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sorType: 'asInt' },
		{ name: 'addTime', sortType: 'asString' },
		{ name: 'description', sortType: 'asString' },	// 病程描述
		{ name: 'doc', sortType: 'asString' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPtCourse,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});