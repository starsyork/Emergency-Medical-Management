Ext.define('Wj.store.user.SurgyDoctor', {
	extend: 'Ext.data.Store',
	requires: 'Wj.model.user.SurgyDoctor',
	model: 'Wj.model.user.SurgyDoctor',

	autoLoad: true,
	totalPage: Wj.consts.totalPage,

	proxy: {
		type: 'ajax',
		url: Wj.url.GetSurgyDoc,
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