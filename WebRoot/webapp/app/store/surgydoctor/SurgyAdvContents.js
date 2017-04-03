Ext.define('Wj.store.surgydoctor.SurgyAdvContents', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyAdvContents'],
	model: 'Wj.model.surgydoctor.SurgyAdvContents',

	storeId: 'surgydoctor.SurgyAdvContents',

	autoLoad: true,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyAdvContents,
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