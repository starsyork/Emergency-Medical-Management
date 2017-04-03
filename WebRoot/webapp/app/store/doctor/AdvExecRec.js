Ext.define('Wj.store.doctor.AdvExecRec', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.doctor.AdvExecRec'],
	model: 'Wj.model.doctor.AdvExecRec',

	storeId: 'doctor.AdvExecRec',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.AdvExecRec,
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