Ext.define('Wj.store.examdoctor.ExamPatient', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.examdoctor.ExamPatient'],
	model: 'Wj.model.examdoctor.ExamPatient',

	storeId: 'examdoctor.ExamPatient',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.ExamDocPatients,
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