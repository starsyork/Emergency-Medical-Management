Ext.define('Wj.store.surgydoctor.SurgyPtCondition', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyPtCondition'],
	model: 'Wj.model.surgydoctor.SurgyPtCondition',

	storeId: 'surgydoctor.SurgyPtCondition',
    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPtCondition,
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