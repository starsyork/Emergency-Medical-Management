Ext.define('Wj.store.doctor.PtBaseInfo', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.doctor.PtBaseInfo'],
	model: 'Wj.model.doctor.PtBaseInfo',

	storeId: 'doctor.PtBaseInfo',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.DocPatients,
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
		},
	}

});