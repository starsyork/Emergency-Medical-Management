Ext.define('Wj.store.user.InspectDoc', {
	extend: 'Ext.data.Store',
	requires: 'Wj.model.user.InspectDoc',
	model: 'Wj.model.user.InspectDoc',

	autoLoad: true,
	totalPage: Wj.consts.totalPage,

	proxy: {
		type: 'ajax',
		url: Wj.url.GetInspectDoc,
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