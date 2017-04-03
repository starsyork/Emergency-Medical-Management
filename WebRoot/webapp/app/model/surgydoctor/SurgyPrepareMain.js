Ext.define('Wj.model.surgydoctor.SurgyPrepareMain', {
	extend: 'Ext.data.Model',

	fields: [
		{ name: 'id', sortType: 'asInt' },	// id
	//	{ name: 'name', sortType: 'asString' },	// 姓名
	//	{ name: 'sex', sortType: 'asString' },	// 性别
	//	{ name: 'age', sortType: 'asInt' },	// 年龄
	//	{ name: 'kb', sortType: 'asString' },	// 科别
	//	{ name: 'bed', sortType: 'asInt' },	// 床号
		{ name: 'content', sortType: 'asString' },//手术名称
		{ name: 'applyId', sortType: 'asString' },//手术申请号
		{ name: 'illustration', sortType: 'asString' },	// 备注
		{ name: 'applydoc', sortType: 'asString' },	
		{ name: 'applytime', sortType: 'asString' ,convert : function(v,rec){  
           console.log('v'+v);
			if(!v){  
                return "";  
            }  
            v = v.replace(new RegExp("-","gm"), "/").replace("T"," ");  
            console.log('v'+v);        
            return v;  
             }  
           },	// 日期	
	
	],

	proxy: {
		type: 'ajax',
		url: Wj.url.SurgyPrepareMain,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	}
});