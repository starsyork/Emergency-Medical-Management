Ext.define('Wj.store.surgydoctor.SurgyPtAdviceLook', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyPtAdviceLook'],
	model: 'Wj.model.surgydoctor.SurgyPtAdviceLook',

	storeId: 'surgydoctor.SurgyPtAdviceLook',

    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPtAdviceLook,
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