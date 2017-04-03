Ext.define('Wj.store.doctor.NewPatient', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.doctor.NewPatient'],
	model: 'Wj.model.doctor.NewPatient',

	storeId: 'doctor.NewPatient',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.NewPt,
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