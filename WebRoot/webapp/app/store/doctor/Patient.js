Ext.define('Wj.store.doctor.Patient', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.doctor.Patient'],
	model: 'Wj.model.doctor.Patient',

	storeId: 'doctor.Patient',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.DocPatients,
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