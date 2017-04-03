Ext.define('Wj.store.disareport.DisaReport', {
	extend: 'Ext.data.Store',
	requires: 'Wj.model.disareport.DisaReport',
	model: 'Wj.model.disareport.DisaReport',

	autoLoad: true,
	totalPage: Wj.consts.totalPage,

	proxy: {
		type: 'ajax',
		url: Wj.url.GetDisaReport,
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