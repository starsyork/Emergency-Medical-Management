Ext.define('Wj.store.doctor.PtCondition', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.doctor.PtCondition'],
	model: 'Wj.model.doctor.PtCondition',

	storeId: 'doctor.PtCondition',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.DocPtCondition,
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