Ext.define('Wj.view.examdoctor.ExamNav', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.examdocnav',

	store: 'examdoctor.ExamPatient',
	id:'id_examNavGrid',
	autoScroll: true,
	flex: 1,

	initComponent: function(){

		console.log('-- Wj.view.examdoctor.ExamNav init. --')

		this.columns = [{
			text: '姓名',
			flex: 1,
			dataIndex: 'name'
		}, 
			{
			text: '分区',
			flex: 1,
			dataIndex: 'examptsec'
		},{
			text: '医生',
			flex: 1,
			dataIndex: 'docName'
		}];

		this.tbar = [{
			xtype: 'checkbox',
			fieldLabel: '查看所有数据',
			itemId: 'all',
			listeners: {

				'change': function(t, nv, ov, opt){
					Wj.controller.ExamDoctor.changeNavCheckBox(t, nv, ov, opt);
				}
			}
		}];

		this.callParent(arguments);

		console.log('-- Wj.view.examdoctor.ExamNav init over. --')

	}

});