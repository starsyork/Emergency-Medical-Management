Ext.define('Wj.store.doctor.Hospital', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.doctor.Hospital'],
	model: 'Wj.model.doctor.Hospital',

	storeId: 'doctor.Hospital',
    autoLoad: true,

    // have to override proxy
    proxy: {
		type: 'ajax',
		// url: 'data/test/admin/sector/read.json',
		url: Wj.url.Hospital,
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
		}
	}

});