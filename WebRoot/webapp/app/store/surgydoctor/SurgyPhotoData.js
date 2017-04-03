Ext.define('Wj.store.surgydoctor.SurgyPhotoData', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyPhotoData'],
	model: 'Wj.model.surgydoctor.SurgyPhotoData',

	storeId: 'surgydoctor.SurgyPhotoData',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPhotoData,
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