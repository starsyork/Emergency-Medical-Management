Ext.define('Wj.store.nurse.PtOtherUndealt', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.nurse.PtOtherUndealt'],
	model: 'Wj.model.nurse.PtOtherUndealt',

	storeId: 'nurse.PtOtherUndealt',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.PtOtherUndealt,
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