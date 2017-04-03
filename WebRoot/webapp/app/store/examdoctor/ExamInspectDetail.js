Ext.define('Wj.store.examdoctor.ExamInspectDetail', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.examdoctor.ExamInspectDetail'],
	model: 'Wj.model.examdoctor.ExamInspectDetail',

	storeId: 'examdoctor.ExamInspectDetail',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.ExamInspectDetail,
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