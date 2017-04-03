Ext.define('Wj.store.inspection.Main', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.inspection.Main'],
	model: 'Wj.model.inspection.Main',

	storeId: 'inspection.Main',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.InspectionMain,
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