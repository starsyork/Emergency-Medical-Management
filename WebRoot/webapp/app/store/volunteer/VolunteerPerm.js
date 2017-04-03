Ext.define('Wj.store.volunteer.VolunteerPerm', {
	extend: 'Ext.data.Store',
	requires: 'Wj.model.volunteer.VolunteerPerm',
	model: 'Wj.model.volunteer.VolunteerPerm',

	autoLoad: true,
	totalPage: Wj.consts.totalPage,

	proxy: {
		type: 'ajax',
		url: Wj.url.GetVoluPerm,
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