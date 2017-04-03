Ext.define('Wj.view.nurse.AddTask', {
	extend: 'Ext.window.Window',
	alias: 'widget.nurseaddtask',

	modal: true,
	closable: true,
	resizable: false,
	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '新增志愿者任务',

	width: 350,
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
				fieldLabel: '发布人',
				name: 'taskpeople',
				itemId: 'taskpeople',
				value: Wj.controller.Nurse.getUser(),
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly
			}, {
				xtype: 'combobox',
				store: Wj.consts.volunteerTask,
				fieldLabel: '任务类型',
				name: 'type',
				itemId: 'tasktype',
				editable: false,
				readOnly: false,
				allowBlank: false,
				blankText: '志愿者类型不能为空'
			}, {
				xtype: 'textarea',
				name: 'details',
				itemId: 'taskdetails',
				height: 50,
				fieldLabel: '任务详细内容',
				grow: true,
				regex:/^[\s\S]{0,200}$/,
				regexText:'请输入200个以内字符'
			},{
				xtype: 'datetimefield',
				name: 'time',
				itemId: 'volunteerdate',
				fieldLabel: '任务开始时间',
				value: new Date(),
				format: 'Y/m/d H:i:s',
				allowBlank: false,
				blankText: '下达时间不能为空'
			},{
				fieldLabel: '任务状态',
				name: 'status',
				itemId: 'taskstate',
				value: "未领取",
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly
			},{
                xtype: 'numberfield',
                fieldLabel: '所需人数',
                name: 'number',
                value: 1,
                minValue: 1,
                maxValue: 500
            }
            ]

		}];

		this.buttons = [{
			text: '确定',
			itemId: 'accept',
			icon: 'icon/accept.png',
			handler: Wj.controller.Nurse.confirmAddTask
		}, {
			text: '重置',
			itemId: 'reset',
			icon: 'icon/reset.png',
			handler: function(){
				
				var fm = this.up('window').down('form').getForm();
				var n = fm.findField('type').value;
				fm.reset();
			//	fm.findField('name').setValue(n);

			}
		}];

		this.callParent(arguments);

	}
})