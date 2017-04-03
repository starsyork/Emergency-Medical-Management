Ext.define('Wj.view.nurse.NurseAddDuty', {
	extend: 'Ext.window.Window',
	alias: 'widget.nurseaddduty',

	modal: true,
	closable: true,
	resizable: false,
	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '新增交接班记录',

	width: 500,
	height: 600,

	initComponent: function(){

		this.items = [{
			xtype: 'form',
			flex: 1,
			padding: '10 2 10 2',
			border: false,
			bodyStyle: 'background:#DFE9F6',
			defaults: {
				labelSeparator: ' : ',
				msgTarget: 'side',
				anchor: '100%',
				style: {
					margin: '4px 10px'
				}
			},
			defaultType: 'textfield',

			items:[
/*			{
				fieldLabel: '编号',
				name: 'id',
				itemId: 'nurse_id'
				//value: Wj.controller.SurgyDoctor.getPtId(),
				//readOnly: true,
				//fieldStyle: Wj.consts.css_readOnly
			},{
			xtype: 'combobox',
			editable: false,
			fieldLabel: '分区',
			name: 'worksec',
			store: 'admin.Sector',
			valueField: 'secName',
			displayField: 'secName'
			},{
				fieldLabel: '岗位名称',
				name: 'workname',
				itemId: 'surgy_surgyid'
//				value: Wj.controller.SurgyDoctor.getSurgyId(),
//				readOnly: true,
//				fieldStyle: Wj.consts.css_readOnly

			},
*/			
				{
				xtype: 'textarea',
				name: 'workoldcon',
				itemId: 'nurse_workoldcon',
				height: 100,
				fieldLabel: '当值情况',
				grow: true
			},
				{
				xtype: 'textarea',
				name: 'worknewcon',
				itemId: 'nurse_worknewcon',
				height: 100,
				fieldLabel: '跟进事项',
				grow: true
			},{
				fieldLabel: '交班人',
				name: 'workupper',
				itemId: 'nurse_workupper'
			},{
				fieldLabel: '接班人',
				name: 'workdownloader',
				itemId: 'nurse_workdownloader'
			},{
				xtype: 'textarea',
				name: 'workupstuff',
				itemId: 'nurse_workupstuff',
				height: 100,
				fieldLabel: '交班物品',
				grow: true
			},{
				xtype: 'textarea',
				name: 'workdownstuff',
				itemId: 'nurse_workdownstuff',
				height: 100,
				fieldLabel: '接班物品',
				grow: true
			},{
				xtype: 'datetimefield',
				fieldLabel: '时间',
				value: new Date(),
				format: 'Y/m/d H:i:s',
				name: 'workupdowndate',
				itemId: 'nurse_workupdowndate'
					
			},{
				fieldLabel: '领班/负责人',
				name: 'workleader',
				itemId: 'nurse_workleader'
			}
			
			]

		}];

		this.buttons = [{
			text: '确定',
			itemId: 'accept',
			icon: 'icon/accept.png',
			handler: Wj.controller.Nurse.confirmAddDuty
		}, {
			text: '重置',
			itemId: 'reset',
			icon: 'icon/reset.png',
			handler: function(){
				
				var fm = this.up('window').down('form').getForm();
				var n = fm.findField('workupdowndate').value;
				fm.reset();
			//	fm.findField('workupdowndate').setValue(n);

			}
		}];

		this.callParent(arguments);

	}
})