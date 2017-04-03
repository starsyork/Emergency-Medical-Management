Ext.define('Wj.store.nurse.DutyManage', {
	extend: 'Ext.data.Store',
	requires: 'Wj.model.nurse.DutyManage',
	model: 'Wj.model.nurse.DutyManage',

	autoLoad: true,
	totalPage: Wj.consts.totalPage,

	proxy: {
		type: 'ajax',
		url: Wj.url.GetDutyManage,
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