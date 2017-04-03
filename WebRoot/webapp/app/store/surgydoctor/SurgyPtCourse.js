Ext.define('Wj.store.surgydoctor.SurgyPtCourse', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyPtCourse'],
	model: 'Wj.model.surgydoctor.SurgyPtCourse',

	storeId: 'surgydoctor.SurgyPtCourse',
	pageSize: Wj.consts.totalPage,

    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPtCourse,
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