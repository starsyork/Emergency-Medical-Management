Ext.define('Wj.store.doctor.VolunteerTask', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.doctor.VolunteerTask'],
	model: 'Wj.model.doctor.VolunteerTask',

	storeId: 'doctor.VolunteerTask',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.DocVolunteerTask,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	},

	listeners: {
		load: function(store, record, success){
			if(!success){
				var r = store.getProxy().getReader().rawData;
				Wj.util.handleServerFailure(r);
			}
		},
	}

});