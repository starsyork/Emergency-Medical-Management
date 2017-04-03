Ext.define('Wj.store.nurse.PtUndealt', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.nurse.PtUndealt'],
	model: 'Wj.model.nurse.PtUndealt',

	storeId: 'nurse.PtUndealt',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.PtUndealt,
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