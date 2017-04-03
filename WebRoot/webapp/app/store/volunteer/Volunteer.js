Ext.define('Wj.store.volunteer.Volunteer', {
	extend: 'Ext.data.Store',
	requires: 'Wj.model.volunteer.Volunteer',
	model: 'Wj.model.volunteer.Volunteer',

	autoLoad: true,
	totalPage: Wj.consts.totalPage,

	proxy: {
		type: 'ajax',
		url: Wj.url.GetVolu,
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
				console.log('ts:',store.getProxy());
				var r = store.getProxy().getReader().rawData;
				Wj.util.handleServerFailure(r);
			}
		}
	}

});