Ext.define('Wj.view.nurse.PtAdvice', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.nurseptadv',

	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '医嘱执行',
	icon: 'icon/user_edit.png',

	initComponent: function(){

		console.log('-- Wj.view.nurse.PtInfo init.--');

		this.items = [{
			xtype: 'grid',
			flex: 1,
			frame: true,
			autoScroll: true,

			viewConfig: {
				getRowClass: function(record){
					var st = record.get('state');
					if(st == '已执行' || st == '已停止')
						return 'readonly-row';
				}
			},

			store: 'nurse.PtAdvice',

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'nurse.PtAdvice',
				displayMsg: '- 双击进行修改 - 当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true,
			}],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				width: 40,
			}, {
				text: '医嘱内容',
				dataIndex: 'content',
				width: 120,
			}, {
				text: '医嘱时效',
				dataIndex: 'type',
				width: 60,
			}, {
				text: '医嘱类型',
				dataIndex: 'type2',
				width: 60,
			}, {
				text: '医嘱状态',
				dataIndex: 'state',
				width: 60,
			}, {
				text: '开始时间',
				dataIndex: 'startTime',
				width: 120,
			}, {
				text: '结束时间',
				dataIndex: 'endTime',
				width: 120,
			}, {
				text: '剂量',
				dataIndex: 'dose',
				width: 60,
			}, {
				text: '途径',
				dataIndex: 'usage',
				width: 100,
			}, {
				text: '频次',
				dataIndex: 'frequency',
				width: 40,
			}, {
				text: '医生说明',
				dataIndex: 'spec',
				width: 120,
			}],

		}, {
			xtype: 'form',
			flex: 1,
			frame: true,

			layout: {
				xtype: 'vbox',
				align: 'stretch',
				pack: 'start',
			},

			padding: '10 10 10 10',

			items: [{
				xtype: 'fieldset',
				title: '医嘱详情',
				flex: 1,

				layout: {
					type: 'vbox',
					align: 'stretch',
					pack: 'start',
				},

				padding: '10 10 10 10',
				border: true,

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
					fieldLabel: '医嘱编号',
					name: 'id',
					itemId: 'fa_id',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					xtype: 'combobox',
					store: Wj.consts.adviceType,
					fieldLabel: '医嘱类型',
					name: 'type2',
					itemId: 'fa_type2',
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
					fieldLabel: '医嘱时效',
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
					//xtype: 'datefield',
					name: 'startTime',
					itemId: 'fa_startTime',
					fieldLabel: '开始时间',
					format: 'Y/m/d H:i',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
				//	xtype: 'datefield',
					name: 'endTime',
					itemId: 'fa_endTime',
					fieldLabel: '结束时间',
					format: 'Y/m/d H:i',
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
					name: 'frequency',
					itemId: 'fa_frequency',
					fieldLabel: '频次',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					xtype: 'textarea',
					name: 'spec',
					itemId: 'fa_spec',
					fieldLabel: '医生说明',
					// flex: 1,
					minHeight: 30,
					autoScroll: true,
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					xtype: 'textfield',
					name: 'exNote',
					itemId: 'fa_exNote',
					fieldLabel: '执行备注',
					regex:/^[\s\S]{0,100}$/,
					regexText:'请输入100个以内字符',
				}, {
					xtype: 'container',
					layout: {
						type: 'hbox',
						pack: 'end',
						align: 'center',
					},
					height: 40,

					items: [{
						xtype: 'button',
						text: '执行',
						anchor: '10%',
						icon: 'icon/accept.png',
						handler: Wj.controller.Nurse.executeAdvice,
					}]
					
				}],
			}],
		}];

		this.callParent(arguments);

		console.log('-- Wj.view.nurse.PtAdvice init over.--');

	}

});