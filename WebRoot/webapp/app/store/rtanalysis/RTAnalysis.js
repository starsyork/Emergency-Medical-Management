Ext.define('Wj.store.rtanalysis.RTAnalysis', {
	extend: 'Ext.data.Store',
	requires: 'Wj.model.rtanalysis.RTAnalysis',
	model: 'Wj.model.rtanalysis.RTAnalysis',

	autoLoad: true,
	totalPage: Wj.consts.totalPage,

	proxy: {
		type: 'ajax',
		url: Wj.url.GetRTAnalysis,
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