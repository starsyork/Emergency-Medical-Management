Ext.define('Wj.store.drug.Drug', {
	extend: 'Ext.data.Store',
	requires: 'Wj.model.drug.Drug',
	model: 'Wj.model.drug.Drug',

	autoLoad: true,
	totalPage: Wj.consts.totalPage,

	proxy: {
		type: 'ajax',
		url: Wj.url.GetDrug,
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