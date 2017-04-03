Ext.define('Wj.store.nav.Admin', {
	extend : 'Ext.data.TreeStore',
	storeId: 'nav.Admin',

	root: {
	children:[{
	text:'医务人员管理',
		expanded: true,
		children: [{
			text : '医生',
			leaf : true,
			id : 'user_doctor'
		}, {
			text : '护士',
			leaf : true,
			id : 'user_nurse'
		}]
	},{
		text:'志愿者管理',
		expanded:true,
		children:[{
		text:'志愿者统计',
		leaf:true,
		id:'statis_volunteer'
		},{
		text:'志愿者审核',
		leaf:true,
		id:'permission_volunteer'
		}]
	}
//	,
//	{
//		text:'药品管理',
//		expanded:true,
//		children:[{
//		text:'药品统计',
//		leaf:true,
//		id:'statis_drug'
//		}]
//	}
//		
	,{
	text:'病人统计',
		expanded:true,
		children:[{
		text:'分区病人统计',
		leaf:true,
		id:'statis_patient'
		},{
		text:'医生病人统计',
		leaf:true,
		id:'statis_attdoc'
		}]
	},
		{
		text:'实时态势分析',
		expanded:true,
		children:[
		{
			text:'药品统计',
			leaf:true,
			id:'statis_drug'
		},
		{
			text:'实时态势分析',
			leaf:true,
			id:'realtime_analysis'
		}]
	},
	{
		text:'疫情管理',
		expanded:true,
		children:[{
		text:'疫情报告',
		leaf:true,
		id:'disaster_report'
		}]
	},
	/*{
		text:'后方医院管理',
		expanded:true,
		children:[
		{
			text:'医院管理',
			leaf:true,
			id:'hospital_mgr'
		},
		]
	}
	
	{
		text:'工作站情况',
		expanded:true,
		children:[
		{
			text:'医生工作站',
			leaf:true,
			id:'doc_webworkshop'
		},
		{
			text:'护士工作站',
			leaf:true,
			id:'nurse_webworkshop'
		}]
	}
	
	*/
	]
		}
	}
);
