Ext.define('Wj.store.nurse.UnAllocBed', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.nurse.UnAllocBed'],
	model: 'Wj.model.nurse.UnAllocBed',

	storeId: 'nurse.UnAllocBed',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.UnAllocBed,
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