Ext.define('Wj.store.user.Type', {
	extend: 'Ext.data.Store',
	fields: ['name', 'value'],

	storeId: 'user.Type',
    autoLoad: true,

	data: [{
		"name" : "管理员",
		"value" : 0
	}, {
		"name" : "主治医生",
		"value" : 1
	}, {
		"name" : "护士",
		"value" : 2
	},
	 {
		"name" : "检查检验医生",
		"value" : 3
	},
	{	 
		"name" : "手术医生",
		"value" : 4	
	}]
});