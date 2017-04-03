Ext.define('Wj.store.nurse.Nav', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.nurse.Nav'],
	model: 'Wj.model.nurse.Nav',

	storeId: 'nurse.Nav',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.NurseNav,
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