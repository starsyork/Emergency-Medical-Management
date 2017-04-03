Ext.define('Wj.store.nurse.NewAdvice', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.nurse.NewAdvice'],
	model: 'Wj.model.nurse.NewAdvice',

	storeId: 'nurse.NewAdvice',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.NurseNewAdvice,
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