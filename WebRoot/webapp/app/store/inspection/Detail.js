Ext.define('Wj.store.inspection.Detail', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.inspection.Detail'],
	model: 'Wj.model.inspection.Detail',

	storeId: 'inspection.Detail',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.InspectionDetail,
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