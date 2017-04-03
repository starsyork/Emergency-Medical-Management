Ext.define('Wj.view.admin.RTAnalysisMgr', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.adminrtanalysismgr',

	requires: ['Wj.view.util.Pagingtoolbar'],

	closable: true,
	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '实时态势统计',

	initComponent: function(){
	
		console.log('-- Wj.view.admin.RTAnalysisMgr init.--');
	var maxInjdata = 0;//伤亡最大值
	var InjMaxNum = 0;//伤亡情况 图标 纵坐标最大值
	this.GetInjMax();
	var maxDrudata = 0;//药品最大值
	var DrugMaxNum = 0;//药品 图标 纵坐标最大值
	this.GetDrugMax();
	console.log('this.DrugMaxNum:',this.DrugMaxNum);
		this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			store: 'rtanalysis.RTAnalysis',
			autoScroll: true,
			//hight:400,
			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',
			}, {
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'rtanalysis.RTAnalysis',
				displayMsg: '- 双击进行修改 - 当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				flex: 1,
				minWidth: 40,
				allowBlank: false,
				hidden: true, 
				hideLabel:true ,
			}, 
			{
				text: '轻伤',
				dataIndex: 'LInjNum',
				flex: 1,
				minWidth: 100,
				allowBlank: false,
			}, {
				text: '中伤',
				flex: 1,
				minWidth: 100,
				dataIndex: 'MInjNum',
				allowBlank: false,				
			}, {
				text: '重伤',
				flex: 1,
				minWidth: 100,
				dataIndex: 'SInjNum',
				allowBlank: false,			
			},{
				text: '总伤亡',
				flex: 1,
				minWidth: 100,
				dataIndex: 'totalDeIn',
				allowBlank: false,				
			},
			 {
				text: '更新日期',
				flex: 1,
				minWidth: 200,
				dataIndex: 'date',
				allowBlank: false,
				renderer: Ext.util.Format.dateRenderer('Y/m/d'),
				
			}]
/*
			plugins: [
				Ext.create('Ext.grid.plugin.RowEditing', {
					clicksToEdit: 2,
					clicksToMoveEdit: 1,
					autoCancel: false
				})
			]
*/
		},
//----------伤亡情况------------------
		Ext.create('Ext.panel.Panel',{
			title:'伤亡情况',
			layout:'fit',
			flex:1,
			collapsible: true, 
			split: true,  
		items:[
		{
            xtype: 'chart',
            style: 'background:#fff',
            flex:1,
            animate: true,
            store: 'rtanalysis.RTAnalysis',
            legend: {
                position: 'right'
            },
            theme: 'Category1',
             shadow: true,
            axes:[{
					type:'Numeric',
					position:'left',
					minimun:0,
					maximum:InjMaxNum=this.GetInjMax(),//纵坐标最大值，动态从数据中获取
					fields:['LInjNum','MInjNum','SInjNum','totalDeIn'],
					title:'人数'
				},{
					type:'Category',
					position:'bottom',
					fields:['date'],
					title:'时间'
				}],
			series:[{
					type:'line',
					highlight:{
						size:7,
						radius:7
					},
					//fill:true,//配置是否填充折线与坐标轴之间的空间
					axis:'left',
					xField:'date',
					yField:'LInjNum',
					title:'轻伤',
					markerCfg:{
						type:'circle',
						radius:4
					},
					selectionTolerance:4,
					showInLegend:true,
					showMarkers:true
				},
				{
					type:'line',
					highlight:{
						size:7,
						radius:7
					},
					axis:'left',
					xField:'date',
					yField:'MInjNum',
					title:'中伤',
					markerCfg:{
						type:'circle',
						radius:4
					},
					selectionTolerance:4,
					showInLegend:true,
					showMarkers:true
				},{
					type:'line',
					highlight:{
						size:7,
						radius:7
					},
					axis:'left',
					xField:'date',
					yField:'SInjNum',
					title:'重伤',
					markerCfg:{
						type:'circle',
						radius:4
					},
					selectionTolerance:4,
					showInLegend:true,
					showMarkers:true
				},
				{
					type:'line',
					highlight:{
						size:7,
						radius:7
					},
					axis:'left',
					xField:'date',
					yField:'totalDeIn',
					title:'总伤亡',
					markerCfg:{
						type:'circle',
						radius:4
					},
					selectionTolerance:4,
					showInLegend:true,
					showMarkers:true
				}
				
				]
			
		}]

	}),
	
	//------------药品需求------------------------------
	Ext.create('Ext.panel.Panel',{
	title:'药品需求',
	layout:'fit',
	flex:1,
	collapsible: true, 
	split: false,  
items:[
{
    xtype: 'chart',
    style: 'background:#fff',
   // flex:1,

    animate: {
        easing: 'bounceOut',
        duration: 550
    },
    store: 'drug.AllDrug',
    legend: {
        position: 'right'
    },
   theme: 'Category3',
    //shadow: true,
    axes:[{
			type:'Numeric',
			position:'left',
			minimun:0,
			maximum:this.DrugMaxNum,
			fields:['amount','need'],
			title:'数量'
		},{
			type:'Category',
			position:'bottom',
			fields:['name'],
			title:'药品名称'
		}],

	series:[{
			type:'column',
			highlight:{
				size:7,
				radius:7
			},
			axis:'left',
			xField:'name',
			yField:['amount','need'],
			title:['库存','短缺'],
			markerCfg:{
				type:'circle',
				radius:4
			},
			selectionTolerance:10,
			showInLegend:true,
			showMarkers:true,
			label:{
				field:['amount','need'],
				display:'insideEnd',//'outside
				font:'17px Arial',
				renderer:function(v){
					return v;
				}
			}
		}
		
		]
	
}]

})	

];

		this.callParent(arguments);

		console.log('-- Wj.view.admin.RTAnalysisMgr init over.--');

	},
	//获取伤亡总人数的最大值，便于确定  图表纵坐标最大值
	GetInjMax:function(){

		var store = Ext.getStore('rtanalysis.RTAnalysis').load({
					//scope: Wj.controller.Admin,
					callback: function(r, o, suc) {
	
					}
				});
		var total = store.getTotalCount();
		maxInjdata = 0;//初始化最大值
		var temp = 0;
		for(var i=0;i<total;i++){
			temp = store.getAt(i).get("totalDeIn"); //获得data里的数据
			if(temp > maxInjdata ){
				maxInjdata = temp;
			}
		}
//		var count = store.getCount(); //获得数据总共有多少条
	//	this.InjMaxNum = maxInjdata + 5;
		return maxInjdata+5;
		console.log('maxInjdata:',maxInjdata);
	},
	
	GetDrugMax:function(){

		var store = Ext.getStore('drug.AllDrug').load({
					callback: function(r, o, suc) { }
				});
		var total = store.getTotalCount();
		maxDrugdata = 0;//初始化最大值
		var temp = 0;
		var temp1 = 0;
		var temp2 = 0;
		for(var i=0;i<total;i++){
			temp1 = store.getAt(i).get("amount"); //获得data里的数据
			temp2 = store.getAt(i).get("need"); //获得data里的数据
			if(temp1 > temp2 ){
				temp = temp1;
			}else{
				temp = temp2;
			}
			if(temp > maxDrugdata){
				maxDrugdata = temp;
			}
		}
		this.DrugMaxNum = maxDrugdata + 0;
		console.log('maxDrugdata:',maxDrugdata);
	}	
	
});