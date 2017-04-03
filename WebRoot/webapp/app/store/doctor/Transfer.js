Ext.define('Wj.store.doctor.Transfer', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.doctor.Transfer'],
	model: 'Wj.model.doctor.Transfer',

	storeId: 'doctor.Transfer',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.Transfer,
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