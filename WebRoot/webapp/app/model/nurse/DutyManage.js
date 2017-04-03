Ext.define('Wj.model.nurse.DutyManage', {
	extend: 'Ext.data.Model',
	fields: [
		//{ name: 'id', sortType: 'asString' },//编号
	//	{ name: 'worksec', sortType: 'asString' },//分区
	//	{ name: 'workname', sortType: 'asString' },//岗位名称
		{ name: 'workoldcon', sortType: 'asString' },//当值情况
		{ name: 'worknewcon', sortType: 'asString' },//跟进事项
		{ name: 'workupper', sortType: 'asString' },//交班人
		{ name: 'workdownloader', sortType: 'asString' },//接班人
		{ name: 'workupstuff', sortType: 'asString' },//交班物品
		{ name: 'workdownstuff', sortType: 'asString' },//接班物品
		{ name: 'workupdowndate', sortType: 'asString' },//时间
		{ name: 'workleader', sortType: 'asString' }//领班/负责人
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.GetDutyManage,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});