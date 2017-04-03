Ext.define('Wj.view.doctor.Nav', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.docnav',

	store: 'doctor.Patient',
	id:'id_docNavGrid',
	autoScroll: true,
	flex: 1,

	initComponent: function(){

		console.log('-- Wj.view.doctor.Nav init. --')
		
		
		this.dockedItems = [{
			xtype: 'toolbar',
			itemId: 'ttb',
			dock: 'top',

			items: [{
				text: '新增手术申请',
				icon: 'icon/add.gif',
				itemId: 'addoperation',
				handler: Wj.controller.Doctor.addOperation,
			}, '-',{
				text: '新增转院申请',
				icon: 'icon/add.gif',
				itemId: 'addtransform',
				handler: Wj.controller.Doctor.addTransfer,
			}],
		}];

		
		

		this.columns = [{
			text: '姓名',
			flex: 1,
			dataIndex: 'name',
		}, {
			text: '医生',
			flex: 1,
			dataIndex: 'docName',
		}];

		this.tbar = [{
			xtype: 'checkbox',
			fieldLabel: '查看分区内所有',
			itemId: 'all',
			listeners: {

				'change': function(t, nv, ov, opt){
					Wj.controller.Doctor.changeNavCheckBox(t, nv, ov, opt);
				}
			}
		}];

		this.callParent(arguments);

		console.log('-- Wj.view.doctor.Nav init over. --')

	}

});