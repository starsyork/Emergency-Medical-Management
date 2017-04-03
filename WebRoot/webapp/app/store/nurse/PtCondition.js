Ext.define('Wj.store.nurse.PtCondition', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.nurse.PtCondition'],
	model: 'Wj.model.nurse.PtCondition',

	storeId: 'nurse.PtCondition',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.NursePtCondition,
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