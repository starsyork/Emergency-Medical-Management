Ext.define('Wj.store.BedStatus', {
	extend: 'Ext.data.Store',
	requires: ['Wj.model.BedStatus'],
	model: 'Wj.model.BedStatus',

	storeId: 'BedStatus',
    autoLoad: true,

    // have to override proxy
    data: [
		{ status: 1, str: '等待' },
		{ status: 2, str: '已分配' },
	],

});