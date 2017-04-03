Ext.define('Wj.model.nurse.PsyAssess', {
	extend: 'Ext.data.Model',
	fields: [
		{ name: 'id', sortType: 'asString' },//编号
	//	{ name: 'sec', sortType: 'asString' },//分区
		{ name: 'ptname', sortType: 'asString' },//姓名
		{ name: 'psychologyStatus', sortType: 'asString' },//当前心理状态
		{ name: 'nursename', sortType: 'asString' },//记录人
		{ name: 'time', sortType: 'asString' ,convert: Wj.util.convertTime}//时间
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.GetPsyAssess,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});