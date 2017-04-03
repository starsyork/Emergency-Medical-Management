Ext.define('Wj.store.nurse.PsyAssess', {
	extend: 'Ext.data.Store',
	requires: 'Wj.model.nurse.PsyAssess',
	model: 'Wj.model.nurse.PsyAssess',

	autoLoad: true,
	totalPage: Wj.consts.totalPage,

	proxy: {
		type: 'ajax',
		url: Wj.url.GetPsyAssess,
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