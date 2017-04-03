Ext.define('Wj.store.surgydoctor.SurgyPatient', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyPatient'],
	model: 'Wj.model.surgydoctor.SurgyPatient',

	storeId: 'surgydoctor.SurgyPatient',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyDocPatients,
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