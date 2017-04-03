Ext.define('Wj.store.doctor.Operation', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.doctor.Operation'],
	model: 'Wj.model.doctor.Operation',

	storeId: 'doctor.Operation',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.Operation,
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