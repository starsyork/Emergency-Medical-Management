Ext.define('Wj.model.surgydoctor.SurgyPrepareDetail', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asString' },	// 编号
		//{ name: 'drugtype', sortType: 'asString' },	// 药品类型
		{ name: 'drugname', sortType: 'asString'},	// 药品名称
		{ name: 'amount', sortType: 'asString' },	// 数量
	    { name: 'content', sortType: 'asString' },	//手术名称
		{ name: 'applyId', sortType: 'asString' },	// 手术申请号
		//{ name: 'noteperson', sortType: 'asString' }	// 操作员

	],

	proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPrepareDetail,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});