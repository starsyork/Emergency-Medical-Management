Ext.define('Wj.store.surgydoctor.SurgyAdvExecRec', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyAdvExecRec'],
	model: 'Wj.model.surgydoctor.SurgyAdvExecRec',

	storeId: 'surgydoctor.SurgyAdvExecRec',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyAdvExecRec,
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