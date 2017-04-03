Ext.define('Wj.model.nurse.VolunteerTask', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sorType: 'asInt' },
		{ name: 'taskpeople', sorType: 'asString' },
		{ name: 'tasktype', sorType: 'asString' },
		{ name: 'details', sorType: 'asString' },
		{ name: 'time', sortType: 'asString' ,convert: Wj.util.convertTime},
		{ name: 'status', sortType: 'asString' },	
		{ name: 'number', sortType: 'asInt' }
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.NurseVolunteerTask,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});