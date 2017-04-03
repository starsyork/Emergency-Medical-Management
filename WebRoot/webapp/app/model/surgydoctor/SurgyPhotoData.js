Ext.define('Wj.model.surgydoctor.SurgyPhotoData', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asString' },//病人id
		{ name: 'ptname', sortType: 'asString' },//病人姓名
		{ name: 'photoid', sortType: 'asString' },//影像编号
		{ name: 'kb', sortType: 'asString' },//科别
		{ name: 'photoDate', sortType: 'asString' },//拍摄日期
		{ name: 'photoDoc', sortType: 'asString' },//医生
		{ name: 'photoOpe', sortType: 'asString' },//操作员
		{ name: 'photoVerifier', sortType: 'asString'},//审核人
		{ name: 'photourl', sortType: 'asString'},//影像图片地址
		{ name: 'photodec', sortType: 'asString'}//影像描述
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPhotoData,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}

});