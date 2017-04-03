Ext.define('Wj.store.surgydoctor.SurgyPrepareDetail', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyPrepareDetail'],
	model: 'Wj.model.surgydoctor.SurgyPrepareDetail',

	storeId: 'surgydoctor.SurgyPrepareDetail',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPrepareDetail,
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