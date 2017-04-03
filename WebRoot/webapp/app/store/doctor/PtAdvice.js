Ext.define('Wj.store.doctor.PtAdvice', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.doctor.PtAdvice'],
	model: 'Wj.model.doctor.PtAdvice',

	storeId: 'doctor.PtAdvice',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.DocPtAdvice,
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