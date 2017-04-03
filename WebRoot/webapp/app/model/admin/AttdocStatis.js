Ext.define('Wj.model.admin.AttdocStatis', {
	extend: 'Ext.data.Model',
//对于ArrdocStatis模型将会创建5个字段分别为secName name wound heal和total类型分别与sortType对应
	
	fields: [
		{ name: 'secName', sortType: 'asString' },
		{ name: 'name', sortType: 'asString' },
		{ name: 'wound', sortType: 'asInt' },
		{ name: 'heal', sortType: 'asInt' },
		{ name: 'total', sortType: 'asInt' }
	],
	//proxy(数据来源属性)
	proxy: {
		type: 'ajax',//使用AJAX请求来从服务器获取数据, 然后通常将它们放入 Store中
		url: Wj.url.GetAttdocStatis,
		//URL  Wj.url.GetAttdocStatis在app.js 中定义为Wj.url.GetAttdocStatis='../patient/getDocterStatis';
		//其中'../patient/getDocterStatis';又指向src中的com.cecwj.action.PatientAction类的geetDocterstatis
		//url: 'data/test/admin/attdocsstatistic/read.json',
	//reader(数据解析方式属性)
		/*
		 *注意：数据类型与服务器返回数据一致 服务器数据项与model中reader中一致（服务器一直以results返回） 
		 * */
		reader: {
			type: 'json',//数据类型
			root: 'results',//包含的数据项与Reader配置的Model(s)中的相符合的属性名称
			successProperty: 'success',//检索'success'标识的属性名称，该标识属性的值标示指定请求是否成功（
			totalProperty: 'total',//检索数据集中记录总数的属性名称. 只有在所有的数据集没有一次得到，而是由服务端分页得到时，该属性才需要用。默认使用total
		}
	}

});