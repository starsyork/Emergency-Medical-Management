Ext.define('Wj.store.examdoctor.ExamNewPatient', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.examdoctor.ExamNewPatient'],
	model: 'Wj.model.examdoctor.ExamNewPatient',

	storeId: 'examdoctor.ExamNewPatient',

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.ExamNewPt,
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