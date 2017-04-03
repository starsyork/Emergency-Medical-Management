Ext.define('Wj.store.drug.AllDrug', {
	extend: 'Ext.data.Store',
	requires: 'Wj.model.drug.AllDrug',
	model: 'Wj.model.drug.AllDrug',

	autoLoad: true,
	proxy: {
		type: 'ajax',
		url: Wj.url.GetAllDrug,
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