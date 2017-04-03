Ext.define('Wj.model.inspection.Detail', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'mid', sortType: 'asInt' },
		{ name: 'item', sortType: 'asString' },	// 化验项目
		{ name: 'itemStr', sortType: 'asString' },	// 中文名称
		{ name: 'resultNum', sortType: 'asString', convert: Wj.util.markResult },	// 结果
		{ name: 'flag', sortType: 'asString' },	// 标志
		{ name: 'scope', sortType: 'asString' },	// 参考范围
		{ name: 'unit', sortType: 'asString' },	// 单位
		{ name: 'min', sortType: 'asFloat' },	// 判断L
		{ name: 'max', sortType: 'asFloat' },	// 判断H
		{ name: 'status', sortType: 'asString' },	// 标志
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.InspectionDetail,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total',
		}
	}

});