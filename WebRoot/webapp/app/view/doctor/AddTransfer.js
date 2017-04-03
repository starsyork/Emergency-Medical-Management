Ext.define('Wj.view.doctor.AddTransfer', {
	extend: 'Ext.window.Window',
	alias: 'widget.addtransfer',

	modal: true,
	closable: true,
	resizable: false,
	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '新增转院申请',

	width: 380,
	height: 280,

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

			items:[{
					value:'转运',
					fieldStyle: Wj.consts.css_readOnly,
					fieldLabel: '类型',
					name: 'type2',
					itemId: 'add_type2',
					editable: false,
					readOnly: true,
					allowBlank: false
					//blankText: '医嘱类型不能为空',
				},{
				fieldLabel: '转院伤员编号',
				name: 'pid',
				itemId: 'add_id',
				value: Wj.controller.Doctor.getPtId(),
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly,
			}, {
				fieldLabel: '转院伤员姓名',
				name: 'ptname',
				itemId: 'add_name',
				value: Wj.controller.Doctor.getPtName(),
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly,
			},  {
				fieldLabel: '转院申请医生',
				name: 'dname',
				itemId: 'docname',
				value: Wj.controller.Doctor.getUser(),
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly,
			},{
				xtype: 'combobox',
				store: 'doctor.Hospital',
				fieldLabel: '转院名称',
				name: 'content',
				itemId: 'name',
				valueField: 'hospName',
				displayField: 'hospName',
				editable: false,
				readOnly: false,
				allowBlank: false,
				blankText: '转院名称不能为空'
			}, {
				xtype: 'datetimefield',
				name: 'time',
				itemId: 'add_startTime',
				fieldLabel: '转院申请时间',
				value: new Date(),
				format: 'Y/m/d H:i:s',
				allowBlank: false,
				blankText: '申请时间不能为空'	,		
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly,
			}, {
				xtype: 'textarea',
				name: 'illustration',
				itemId: 'add_spec',
				height: 60,
				fieldLabel: '转院注意事项',
				grow: true,
				regex:/^[\s\S]{0,200}$/,
				regexText:'请输入200个以内字符'
			}]

		}];

		this.buttons = [{
			text: '确定',
			itemId: 'accept',
			icon: 'icon/accept.png',
			handler: Wj.controller.Doctor.confirmAddTransfer,
		}, {
			text: '重置',
			itemId: 'reset',
			icon: 'icon/reset.png',
			handler: function(){
				
				var fm = this.up('window').down('form').getForm();
				var n = fm.findField('name').value;
				fm.reset();
				fm.findField('name').setValue(n);

			},
		}];

		this.callParent(arguments);

	}
})