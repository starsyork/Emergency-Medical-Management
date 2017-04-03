Ext.define('Wj.model.surgydoctor.SurgyInspectMain', {
	extend: 'Ext.data.Model',

	fields: [
	 		{ name: 'id', sortType: 'asInt' },	// id
	 		{ name: 'pid', sortType: 'asInt' },
	 		//	{ name: 'name', sortType: 'asString' },	// 姓名
	 		//	{ name: 'sex', sortType: 'asString', convert: Wj.util.convertSex },	// 性别
	 		//	{ name: 'age', sortType: 'asInt' },	// 年龄
	 		//	{ name: 'kb', sortType: 'asString' },	// 科别
	 		//	{ name: 'bed', sortType: 'asInt' },	// 床号
	 		//	{ name: 'diag', sortType: 'asString' },	// 诊断
	 		//	{ name: 'sampleType', sortType: 'asString' },	// 标本类型
	 			{ name: 'applydoc', sortType: 'asString' },		// 申请医师
	 			{ name: 'content', sortType: 'asString' },	// 申请内容
	 		//	{ name: 'inspectKb', sortType: 'asString' },	// 检验科别
	 			{ name: 'reporter', sortType: 'asString' },	// 报告人
	 		//	{ name: 'collater', sortType: 'asString' },	// 审核人
	 			{ name: 'illustration', sortType: 'asString' },	// 备注
	 		//	{ name: 'inspectNumber', sortType: 'asString' },	// 检验号
	 		//	{ name: 'date', sortType: 'asString' },	// 归档日期
	 			{ name: 'applyId', sortType: 'asString' },	// 申请号
	 		//	{ name: 'mkNum', sortType: 'asString' },	// 标识号
	 		//	{ name: 'sampleNum', sortType: 'asString' },	// 样品号
	 		//	{ name: 'exec', sortType: 'asString' },	// 执行 （00?）
	 		//	{ name: 'smpTime', sortType: 'asString' }, // 采标本时间
	 		//	{ name: 'rcvTime', sortType: 'asString' },	// 收标本（时间）
	 			{ name: 'time', sortType: 'asString',convert: Wj.util.convertTime },	// 报告时间
	 			{ name: 'url', sortType: 'asString' },	// 报告时间
	 			{ name: 'photodec', sortType: 'asString'},//影像描述
	 			{ name: 'status', sortType: 'asString' }	// 判断H
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.SurgyInspectMain,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}
});