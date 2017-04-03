Ext.define('Wj.view.doctor.AddPtCourse', {
	extend: 'Ext.window.Window',
	alias: 'widget.addptcourse',

	title: '新增病程',

	modal: true,
	closable: true,
	resizable: false,
	layout: 'fit',

	width: 500,
	height: 400,

	initComponent: function(){


		this.items = [{
			xtype: 'form',
			padding: '10 2 10 2',
			border: false,
			bodyStyle: 'background:#DFE9F6',
			defaults: {
				labelSeparator: ' : ',
				msgTarget: 'side',
				anchor: '100%',
				style: {
					margin: '8px 10px',
				},
			},
			defaultType: 'textfield',

			items: [{
				name: 'id',
				itemId: 'addId',
				fieldLabel: '伤员编号',
				value: Wj.controller.Doctor.getPtId(),
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly,
			}, {
				xtype: 'datetimefield',
				name: 'addTime',
				itemId: 'add_addTime',
				fieldLabel: '编写时间',
				format: 'Y/m/d H:i:s',
				value: new Date(),
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly,
			}, {
				xtype: 'textarea',
				name: 'description',
				itemId: 'add_description',
				fieldLabel: '病程描述',
				height: 220,
				grow: true,
				// allowBlank: false,
				// blankText: '病程描述不能为空',
				regex: /^[\s\S]{0,200}$/,
				regexText:'请输入200个以内字符',
			}, {
				xtype: 'filefield',
				name: 'uploadDoc',
				itemId: 'add_uploadDoc',
				fieldLabel: 'doc文件',
				buttonText: '选择文件...',
			}]

		}];

		this.buttons = [{
			text: '确定',
			itemId: 'accept',
			icon: 'icon/accept.png',
			handler: Wj.controller.Doctor.confirmAddCourse,
		}, {
			text: '重置',
			itemId: 'reset',
			icon: 'icon/reset.png',
			handler: function(){

				this.up('window').down('form').getForm().reset();

			},
		}];

		this.callParent(arguments);

	}
})