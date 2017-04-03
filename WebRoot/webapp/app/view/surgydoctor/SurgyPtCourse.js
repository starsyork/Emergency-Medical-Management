Ext.define('Wj.view.surgydoctor.SurgyPtCourse', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.surgyptcourse',

	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '病程查看',
	icon: 'icon/look.png',

	initComponent: function(){

		console.log('-- Wj.view.surgydoctor.SurgyPtCourse init.--');

		this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			store: 'surgydoctor.SurgyPtCourse',
			autoScroll: true,

			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',

				items: [{
					text: '下载',
					icon: 'icon/down.gif',
					itemId: 'download',
					handler: Wj.controller.SurgyDoctor.downloadDoc
				}]
			}, {
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'doctor.PtCourse',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				flex:1,
				minWidth: 40
			}, {
				text: '编写日期',
				dataIndex: 'addTime',
				flex:1,
				minWidth: 120
			}, {
				text: '病程描述',
				dataIndex: 'description',
				flex:1,
				minWidth: 340
			}, {
				text: '存档(.doc)',
				dataIndex: 'doc',
				flex:1,
				minWidth: 200
			}]



		}];

		this.callParent(arguments);

		console.log('-- Wj.view.surgydoctor.SurgyPtCourse init over.--');

	}

});