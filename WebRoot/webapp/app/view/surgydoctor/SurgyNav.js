Ext.define('Wj.view.surgydoctor.SurgyNav', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.surgydocnav',

	store: 'surgydoctor.SurgyPatient',
	id:'id_surgyNavGrid',
	autoScroll: true,
	flex: 1,

	initComponent: function(){

		console.log('-- Wj.view.surgydoctor.SurgyNav init. --')

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
			fieldLabel: '所有病人数据',
			itemId: 'all',
			listeners: {

				'change': function(t, nv, ov, opt){
					Wj.controller.SurgyDoctor.changeSurgyNavCheckBox(t, nv, ov, opt);
				}
			}
		}];

		this.callParent(arguments);

		console.log('-- Wj.view.surgydoctor.SurgyNav init over. --')

	}

});