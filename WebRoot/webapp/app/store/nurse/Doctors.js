Ext.define('Wj.store.nurse.Doctors', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.nurse.Doctors'],
	model: 'Wj.model.nurse.Doctors',

	storeId: 'nurse.Doctors',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.listDoctors,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
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