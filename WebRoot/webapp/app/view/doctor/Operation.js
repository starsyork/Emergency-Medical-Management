Ext.define('Wj.view.doctor.Operation', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.operation',

	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '手术记录查看',
	icon: 'icon/info.png',

	initComponent: function(){

		//console.log('-- Wj.view.doctor.PtCourse init.--');

		this.items = [{
			xtype: 'grid',
			title: '手术列表',
			id:'docOeraGrid',
			flex: 1,
			frame: true,
			store: 'doctor.Operation',
			autoScroll: true,

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'doctor.Operation',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],

			columns: [{
				text: '手术编号',
				dataIndex: 'id',
				flex:1,
				minWidth: 60
			},
//			{
//				text: '患者编号',
//				dataIndex: 'pid',
//				width: 60,
//			}, {
//				text: '患者姓名',
//				dataIndex: 'ptname',
//				width: 80,
//			}, 
				{
				text: '手术医生',
				dataIndex: 'operationdoc',
				flex:1,
				minWidth: 80
			}, {
				text: '手术时间',
				dataIndex: 'operationtime',
				flex:1,
				minWidth: 150
			}, {
				text: '手术内容',
				dataIndex: 'content',
				flex:1,
				minWidth: 200
			}, {
				text: '补充说明',
				flex:1,
				minWidth: 200,
				dataIndex: 'illustration'
			} ,{
				text: '手术状态',
				dataIndex: 'status',
				flex:1,
				minWidth: 60
			},{
				text: '申请医生',
				dataIndex: 'applydoc',
				flex:1,
				minWidth: 80
			}, {
				text: '申请时间',
				dataIndex: 'applytime',
				flex:1,
				minWidth: 150
			}]

		}];

		this.callParent(arguments);

		//console.log('-- Wj.view.doctor.PtCourse init over.--');

	}

});