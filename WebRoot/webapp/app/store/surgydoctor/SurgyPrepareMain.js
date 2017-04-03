Ext.define('Wj.store.surgydoctor.SurgyPrepareMain', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyPrepareMain'],
	model: 'Wj.model.surgydoctor.SurgyPrepareMain',

	storeId: 'surgydoctor.SurgyPrepareMain',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPrepareMain,
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