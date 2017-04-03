Ext.define('Wj.view.nurse.PtInfo', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.nurseptinfo',

	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '病人详情',
	icon: 'icon/info.png',

	initComponent: function(){

		console.log('-- Wj.view.nurse.PtInfo init.--');

		this.items = [{
			xtype: 'form',
			flex: 1,
			frame: true,

			items: [{
				xtype: 'fieldset',

				padding: '10 10 10 10',
				border: false,

				layout: {
					type: 'vbox',
					pack: 'start',
					align: 'stretch',
				},

				defaults: {
					labelSeparator: ': ',
					labelAlign: 'left',
					msgTarget: 'side',
					style: {
						margin: '4px 10px',
					},
				},

				defaultType: 'textfield',

				/*items: [{
					fieldLabel: '编号',
					name: 'id',
					readOnly: true,
				}, {
					fieldLabel: '医嘱内容',
					name: 'content',
					allowBlank: false,
					blankText: '医嘱内容不能为空',
				}, {
					xtype: 'combobox',
					store: ['长期医嘱', '临时医嘱'],
					fieldLabel: '医嘱类型',
					name: 'type',
					itemId: 'fn_type',
					editable: false,
					readOnly: false,
					allowBlank: false,
					blankText: '医嘱类型不能为空',
				}, {
					name: 'state',
					itemId: 'fn_state',
					editable: false,
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
					fieldLabel: '医嘱状态',
					allowBlank: false,
					blankText: '状态不能为空',
				}, {
					xtype: 'datefield',
					name: 'startTime',
					itemId: 'fn_startTime',
					fieldLabel: '开始时间',
					format: 'Y/m/d H:i',
					readOnly: false,
					allowBlank: false,
					blankText: '下达时间不能为空',
				}, {
					xtype: 'datefield',
					name: 'endTime',
					itemId: 'fn_endTime',
					fieldLabel: '结束时间',
					format: 'Y/m/d H:i',
					readOnly: false,
					allowBlank: false,
					blankText: '下达时间不能为空',
				}, {
					name: 'dose',
					itemId: 'fn_dose',
					fieldLabel: '剂量',
				}, {
					name: 'usage',
					itemId: 'fn_usage',
					fieldLabel: '途径',
				}, {
					xtype: 'numberfield',
					name: 'frequency',
					itemId: 'fn_frequency',
					fieldLabel: '次数',
					editable: true,
					minValue: 0,
				}, {
					xtype: 'textarea',
					name: 'spec',
					itemId: 'fn_spec',
					fieldLabel: '医生说明',
					grow: true,
				}],*/

			}]
		}, {
			xtype: 'form',
			flex: 1,
			frame: true,

			padding: '10 10 10 10',

			items: [{
				xtype: 'fieldset',
				title: '医嘱',

				padding: '10 10 10 10',
				border: true,

				layout: {
					type: 'vbox',
					pack: 'start',
					align: 'stretch',
				},

				defaults: {
					labelSeparator: ': ',
					labelAlign: 'left',
					msgTarget: 'side',
					style: {
						margin: '4px 10px',
					},
				},

				defaultType: 'textfield',

				items: [{
					fieldLabel: '编号',
					name: 'id',
					itemId: 'fa_id',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					fieldLabel: '医嘱内容',
					name: 'content',
					itemId: 'fa_content',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					xtype: 'combobox',
					store: ['长期医嘱', '临时医嘱'],
					fieldLabel: '医嘱类型',
					name: 'type',
					itemId: 'fa_type',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					name: 'state',
					itemId: 'fa_state',
					editable: false,
					fieldLabel: '医嘱状态',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					xtype: 'datetimefield',
					name: 'startTime',
					itemId: 'fa_startTime',
					fieldLabel: '开始时间',
					format: 'Y/m/d H:i:s',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					xtype: 'datetimefield',
					name: 'endTime',
					itemId: 'fa_endTime',
					fieldLabel: '结束时间',
					format: 'Y/m/d H:i:s',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					name: 'dose',
					itemId: 'fa_dose',
					fieldLabel: '剂量',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					name: 'usage',
					itemId: 'fa_usage',
					fieldLabel: '途径',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					xtype: 'numberfield',
					name: 'frequency',
					itemId: 'fa_frequency',
					fieldLabel: '次数',
					minValue: 0,
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					xtype: 'textarea',
					name: 'spec',
					itemId: 'fa_spec',
					fieldLabel: '医生说明',
					height: 140,
					autoScroll: true,
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					xtype: 'text',
					name: 'exNote',
					itemId: 'fa_exNote',
					fieldLabel: '医生说明',
				}],
			}],

			buttons: [{
				text: '执行',
				icon: 'icon/accept.png',
				handler: Wj.controller.Nurse.executeAdvice,
			}],
		}];

		this.callParent(arguments);

		console.log('-- Wj.view.nurse.PtAdvice init over.--');

	}

});