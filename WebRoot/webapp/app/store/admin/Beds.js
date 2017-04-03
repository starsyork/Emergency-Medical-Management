Ext.define('Wj.store.admin.Beds',{
    extend:'Ext.data.Store',
    requires:['Wj.model.admin.Beds'],
    model:'Wj.model.admin.Beds',
  
  	storeId: 'admin.Beds',
    autoLoad: false,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.AdminBeds,
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