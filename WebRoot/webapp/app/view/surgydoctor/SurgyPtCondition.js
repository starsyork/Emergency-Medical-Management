Ext.define('Wj.view.surgydoctor.SurgyPtCondition', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.surgyptcondition',

	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '病情查看',
	icon: 'icon/look.png',

	initComponent: function(){

		console.log('-- Wj.view.surgydoctor.SurgyPtCondition init.--');

		this.items = [{
			xtype: 'grid',
			title: '病情列表',
			flex: 1,
			frame: true,
			store: 'surgydoctor.SurgyPtCondition',
			autoScroll: true,

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'surgydoctor.SurgyPtCondition',
				displayMsg: '- 双击进行修改 - 当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				flex:1,
				minWidth: 60
			}, {
				text: '脉搏',
				dataIndex: 'pulse',
				flex:1,
				minWidth: 80
			}, {
				text: '呼吸频率',
				dataIndex: 'breath',
				flex:1,
				minWidth: 80
			}, {
				text: '舒张压',
				dataIndex: 'diastolic',
				flex:1,
				minWidth: 80
			}, {
				text: '收缩压',
				dataIndex: 'systolic',
				flex:1,
				minWidth: 80
			}, {
				text: '体温',
				dataIndex: 'temperature',
				flex:1,
				minWidth: 60
			}, {
				text: '病情描述',
				dataIndex: 'comment',
				flex: 1,
				minWidth: 320
			}, {
				text: '时间',
				dataIndex: 'time',
				flex:1,
				minWidth: 140
			}]


		}];

		this.callParent(arguments);

		console.log('-- Wj.view.surgydoctor.SurgyPtCondition init over.--');

	}

});