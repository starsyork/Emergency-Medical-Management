Ext.define('Wj.view.surgydoctor.SurgyPtAdviceLook', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.surgyptadvicelook',

	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '医嘱查看',
	icon: 'icon/look.png',

	initComponent: function(){

		console.log('-- Wj.view.surgydoctor.SurgyPtAdviceLook init.--');

		this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			autoScroll: true,

			viewConfig: {
				getRowClass: function(record){
					var st = record.get('state');
					if(st == '已执行' || st == '已停止')
						return 'readonly-row';
				}
			},

			store: 'surgydoctor.SurgyPtAdviceLook',

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'surgydoctor.SurgyPtAdviceLook',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				flex:1,
				minWidth: 40
			}, {
				text: '医嘱内容',
				dataIndex: 'content',
				flex:1,
				minWidth: 120
			}, {
				text: '医嘱时效',
				dataIndex: 'type',
				flex:1,
				minWidth: 60
			}, {
				text: '医嘱类型',
				dataIndex: 'type2',
				flex:1,
				minWidth: 60
			}, {
				text: '医嘱状态',
				dataIndex: 'state',
				flex:1,
				minWidth: 60
			}, {
				text: '开始时间',
				dataIndex: 'startTime',
				flex:1,
				minWidth: 120
			}, {
				text: '结束时间',
				dataIndex: 'endTime',
				flex:1,
				minWidth: 120
			}, {
				text: '剂量',
				dataIndex: 'dose',
				flex:1,
				minWidth: 60
			}, {
				text: '途径',
				dataIndex: 'usage',
				flex:1,
				minWidth: 100
			}, {
				text: '频次',
				dataIndex: 'frequency',
				flex:1,
				minWidth: 80
			}, {
				text: '医生说明',
				dataIndex: 'spec',
				flex:1,
				minWidth: 120
			}]

			
		}];

		this.callParent(arguments);

		console.log('-- Wj.view.surgydoctor.SurgyPtAdviceLook init over.--');

	}

});