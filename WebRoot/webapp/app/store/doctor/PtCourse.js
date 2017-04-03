Ext.define('Wj.store.doctor.PtCourse', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.doctor.PtCourse'],
	model: 'Wj.model.doctor.PtCourse',

	storeId: 'doctor.PtCourse',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.DocPtCourse,
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