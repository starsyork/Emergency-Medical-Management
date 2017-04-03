Ext.define('Wj.store.nurse.VolunteerTask', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.nurse.VolunteerTask'],
	model: 'Wj.model.nurse.VolunteerTask',

	storeId: 'nurse.VolunteerTask',
	pageSize: Wj.consts.totalPage,

    proxy: {
		type: 'ajax',
		url: Wj.url.NurseVolunteerTask,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	},

	listeners: {
		load: function(store, record, success){
			if(!success){
				var r = store.getProxy().getReader().rawData;
				Wj.util.handleServerFailure(r);
			}
		}
	}

});