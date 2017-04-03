Ext.define('Wj.store.nurse.PtAdvice', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.nurse.PtAdvice'],
	model: 'Wj.model.nurse.PtAdvice',

	storeId: 'nurse.PtAdvice',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.NursePtAdvice,
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