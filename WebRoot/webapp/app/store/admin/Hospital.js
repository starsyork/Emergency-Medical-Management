Ext.define('Wj.store.admin.Hospital', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.admin.Hospital'],
	model: 'Wj.model.admin.Hospital',

	storeId: 'admin.Hospital',
    autoLoad: true,

    // have to override proxy
    proxy: {
		type: 'ajax',
		// url: 'data/test/admin/sector/read.json',
		url: Wj.url.Hospital,
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
		}
	}

});