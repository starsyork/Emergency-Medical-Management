Ext.define('Wj.store.user.ExamDoctor', {
	extend: 'Ext.data.Store',
	requires: 'Wj.model.user.ExamDoctor',
	model: 'Wj.model.user.ExamDoctor',

	autoLoad: true,
	totalPage: Wj.consts.totalPage,

	proxy: {
		type: 'ajax',
		url: Wj.url.GetExamDoc,
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