Ext.define('Wj.store.admin.Sector', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.admin.Sector'],
	model: 'Wj.model.admin.Sector',

	storeId: 'admin.Sector',
    autoLoad: true,

    // have to override proxy
    proxy: {
		type: 'ajax',
		// url: 'data/test/admin/sector/read.json',
		url: Wj.url.Sectors,
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