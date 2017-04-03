Ext.define('Wj.view.nurse.AdviceWin', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.advwin',

	closable: false,
	resizable: false,

	floating: true,

	collapsible: true,
	animCollapse: true,
	titleCollapse: true,
	collapseDirection: 'bottom',
	collapsed: true,

	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '【提醒】有新的医嘱到达！',

	width: 600,
	height: 300,

	initComponent: function(){

		this.items = [{
			xtype: 'form',
			itemId: 'cform',
			buttonAlign: 'center',
			height: 50,

			padding: '10 10 10 10',
			border: false,
			frame: true,

			layout: {
				type: 'vbox',
				pack: 'start',
				align: 'stretch',
			},

			buttons: [{
				text: '确认',
				itemId: 'btn_confirm',
				icon: 'icon/accept.png',
				disabled: true,
				handler: Wj.controller.Nurse.handleConfirmNewAdv,
			}]

		}, {
			xtype: 'grid',
			title: '新医嘱列表',
			autoScroll: true,
			store: 'nurse.NewAdvice',
			flex: 1,

			columns:[{
				text: '编号',
				dataIndex: 'id',
				width: 40,
			}, {
				text: '姓名',
				dataIndex: 'name',
				width: 100,
			}, {
				text: '床号',
				dataIndex: 'bedNum',
				width: 40,
			}, {
				text: '医嘱时效',
				dataIndex: 'type',
				width: 100,
			}, {
				text: '医嘱内容',
				dataIndex: 'content',
				flex: 1,
			}, {
				text: '开始时间',
				dataIndex: 'startTime',
				width: 120,
			}],

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'nurse.NewAdvice',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true,
			}],

		}];

		this.callParent(arguments);

	}
})