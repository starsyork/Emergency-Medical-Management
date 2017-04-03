Ext.define('Wj.store.Hospital', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.Hospital'],
	model: 'Wj.model.Hospital',


    autoLoad: true,

	proxy: {
		type: 'ajax',
		url: Wj.url.GetHospital,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
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