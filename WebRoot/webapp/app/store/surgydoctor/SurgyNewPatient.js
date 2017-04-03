Ext.define('Wj.store.surgydoctor.SurgyNewPatient', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyNewPatient'],
	model: 'Wj.model.surgydoctor.SurgyNewPatient',

	storeId: 'surgydoctor.SurgyNewPatient',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyNewPt,
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
		},
	}

});