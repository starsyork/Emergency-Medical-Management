Ext.define('Wj.store.examdoctor.ExamInspectMain', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.examdoctor.ExamInspectMain'],
	model: 'Wj.model.examdoctor.ExamInspectMain',

	storeId: 'examdoctor.ExamInspectMain',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.ExamInspectMain,
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