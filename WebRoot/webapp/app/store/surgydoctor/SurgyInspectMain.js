Ext.define('Wj.store.surgydoctor.SurgyInspectMain', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyInspectMain'],
	model: 'Wj.model.surgydoctor.SurgyInspectMain',

	storeId: 'surgydoctor.SurgyInspectMain',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyInspectMain,
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