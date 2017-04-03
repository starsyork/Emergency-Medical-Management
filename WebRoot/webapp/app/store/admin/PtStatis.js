Ext.define('Wj.store.admin.PtStatis',{
    extend:'Ext.data.Store',
    requires:['Wj.model.admin.PtStatis'],
    model:'Wj.model.admin.PtStatis',
  
  	storeId: 'admin.PtStatis',
    autoLoad: false,

    // have to override proxy
    proxy: {
		type: 'ajax',
		//url: 'data/test/admin/ptstatistic/read.json',
		url: Wj.url.GetPtStatis,
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