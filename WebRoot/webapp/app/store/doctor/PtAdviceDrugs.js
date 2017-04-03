// Ext.define('Wj.store.doctor.PtAdviceDrugs', {
// 	extend: 'Ext.data.Store',
// 	requires: ['Wj.model.doctor.PtAdviceDrugs'],
// 	model: 'Wj.model.doctor.PtAdviceDrugs',

// 	storeId: 'doctor.PtAdviceDrugs',

//     // have to override proxy
//     proxy: {
// 		type: 'ajax',
// 		url: Wj.url.DocPtAdviceDrugs,
// 		reader: {
// 			type: 'json',
// 			root: 'results',
// 			successProperty: 'success',
// 			totalProperty: 'total',
// 		}
// 	},

// 	listeners: {
// 		load: function(store, record, success){
// 			if(!success){
// 				var r = store.getProxy().getReader().rawData;
// 				Wj.util.handleServerFailure(r);
// 			}
// 		},
// 	}

// });