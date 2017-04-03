Ext.define('Wj.store.surgydoctor.SurgyPtAdvice', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyPtAdvice'],
	model: 'Wj.model.surgydoctor.SurgyPtAdvice',

	storeId: 'surgydoctor.SurgyPtAdvice',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPtAdvice,
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