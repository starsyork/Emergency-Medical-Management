Ext.define('Wj.view.nurse.PtCondition', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.nurseptcondition',

	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '填写病情',
	icon: 'icon/user_edit.png',

	initComponent: function(){

		console.log('-- Wj.view.nurse.PtCondition init.--');

		this.items = [{
			xtype: 'grid',
			title: '病情列表',
			flex: 1,
			frame: true,
			store: 'nurse.PtCondition',
			autoScroll: true,

			dockedItems: [{
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'nurse.PtCondition',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true,
			}],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				width: 40,
			}, {
				text: '时间',
				dataIndex: 'time',
				width: 120,
			}, {
				text: '脉搏',
				dataIndex: 'pulse',
				width: 40,
			}, {
				text: '呼吸频率',
				dataIndex: 'breath',
				width: 70,
			}, {
				text: '舒张压',
				dataIndex: 'diastolic',
				width: 60,
			}, {
				text: '收缩压',
				dataIndex: 'systolic',
				width: 60,
			}, {
				text: '体温',
				dataIndex: 'temperature',
				width: 40,
			}, {
				text: '病情描述',
				dataIndex: 'comment',
				width: 260,
			}],

		}, {
			xtype: 'form',
			itemId: 'addcondform',
			flex: 1,
			frame: true,

			padding: '10 10 10 10',

			layout: {
				type: 'vbox',
				pack: 'start',
				align: 'stretch',
			},

			items: [{
				xtype: 'fieldset',
				title: '添加病情',
				flex: 1,

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
					itemId: 'id',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					fieldLabel: '伤员编号',
					name: 'ptid',
					itemId: 'ptid',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					fieldLabel: '姓名',
					name: 'name',
					itemId: 'name',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					xtype: 'numberfield',
					fieldLabel: '脉搏',
					name: 'pulse',
					itemId: 'pulse',
					minValue: 0,
					maxValue: 999,
				}, {
					xtype: 'numberfield',
					fieldLabel: '呼吸频率',
					name: 'breath',
					itemId: 'breath',
					minValue: 0,
					maxValue: 999,
				}, {
					xtype: 'numberfield',
					name: 'diastolic',
					itemId: 'diastolic',
					fieldLabel: '舒张压',
					minValue: 0,
					maxValue: 999,
				}, {
					xtype: 'numberfield',
					name: 'systolic',
					itemId: 'systolic',
					fieldLabel: '收缩压',
					minValue: 0,
					maxValue: 999,
				}, {
					xtype: 'numberfield',
					name: 'temperature',
					itemId: 'temperature',
					fieldLabel: '体温(℃)',
					minValue: 0,
					maxValue: 999,
				}, {
					xtype: 'textarea',
					name: 'comment',
					itemId: 'comment',
					fieldLabel: '病情描述',
					minHeight: 100,
					flex: 1,
					autoScroll: true,
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
						margin: '0 20 0 0',
						text: '添加',
						itemId: 'add',
						anchor: '10%',
						icon: 'icon/add.png',
						handler: Wj.controller.Nurse.addCondition,
					}, {
						xtype: 'button',
						margin: '0 20 0 0',
						text: '更新',
						itemId: 'update',
						anchor: '10%',
						icon: 'icon/accept.png',
						handler: Wj.controller.Nurse.editCondition,
					}]
					
				}],
			}],
		}];

		this.callParent(arguments);

		console.log('-- Wj.view.nurse.PtCondition init over.--');

	}

});