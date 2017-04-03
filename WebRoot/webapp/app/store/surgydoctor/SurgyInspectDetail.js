Ext.define('Wj.store.surgydoctor.SurgyInspectDetail', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.surgydoctor.SurgyInspectDetail'],
	model: 'Wj.model.surgydoctor.SurgyInspectDetail',

	storeId: 'surgydoctor.SurgyInspectDetail',
	pageSize: Wj.consts.totalPage,

    // have to override proxy
    proxy: {
		type: 'ajax',
		url: Wj.url.SurgyInspectDetail,
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