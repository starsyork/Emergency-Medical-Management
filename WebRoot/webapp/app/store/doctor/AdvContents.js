Ext.define('Wj.store.doctor.AdvContents', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.doctor.AdvContents'],
	model: 'Wj.model.doctor.AdvContents',

	storeId: 'doctor.AdvContents',

	autoLoad: true,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.AdvContents,
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