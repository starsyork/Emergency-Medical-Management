Ext.define('Wj.view.surgydoctor.SurgyPtAdvice', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.surgyptadvice',

	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '术后医嘱',
	icon: 'icon/user_edit.png',

	initComponent: function(){

		console.log('-- Wj.view.surgydoctor.SurgyPtAdvice init.--');

		this.items = [{
			xtype: 'grid',
			itemId:'ptadvice_grid',
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

			store: 'surgydoctor.SurgyPtAdvice',

			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',

				items: [{
					text: '新增医嘱',
					icon: 'icon/add.png',
					itemId: 'add',
					handler: Wj.controller.SurgyDoctor.addAdvice
				}, '-', {
					text: '删除',
					icon: 'icon/delete.gif',
					itemId: 'remove',
					disabled: true,
					handler: Wj.controller.SurgyDoctor.removeAdvice
				}]
			}, {
				xtype: 'pagebar',
				dock: 'bottom',
				store: 'surgydoctor.SurgyPtAdvice',
				displayMsg: '当前页面显示结果 {0} - {1} ,总条数：{2}',
				displayInfo: true
			}],

			columns: [{
				text: '编号',
				dataIndex: 'id',
				width: 40
			}, {
				text: '医嘱内容',
				dataIndex: 'content',
				width: 120
			}, {
				text: '医嘱时效',
				dataIndex: 'type',
				width: 60
			}, {
				text: '医嘱类型',
				dataIndex: 'type2',
				width: 60
			}, {
				text: '医嘱状态',
				dataIndex: 'state',
				width: 60
			}, {
				text: '开始时间',
				dataIndex: 'startTime',
				width: 120
			}, {
				text: '结束时间',
				dataIndex: 'endTime',
				width: 120
			}, {
				text: '剂量',
				dataIndex: 'dose',
				width: 60
			}, {
				text: '途径',
				dataIndex: 'usage',
				width: 100
			}, {
				text: '频次',
				dataIndex: 'frequency',
				width: 80
			}, {
				text: '医生说明',
				dataIndex: 'spec',
				width: 120
			}]

		}, {
			xtype: 'form',
			flex: 0.8,
			frame: true,

			layout: {
				type: 'vbox',
				align: 'stretch',
				pack: 'start'
			},

			items: [{
				xtype: 'fieldset',
				flex: 1,

				padding: '10 10 10 10',
				border: false,

				layout: {
					type: 'vbox',
					pack: 'start',
					align: 'stretch'
				},

				defaults: {
					labelSeparator: ': ',
					labelAlign: 'left',
					msgTarget: 'side',
					style: {
						margin: '4px 10px'
					}
				},

				defaultType: 'textfield',

				items: [{
					fieldLabel: '编号',
					name: 'id',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly
				}, {
					xtype: 'combobox',
					store: Wj.consts.adviceType,
					fieldLabel: '医嘱类型',
					name: 'type2',
					itemId: 'f_type2',
					editable: false,
					readOnly: false,
					allowBlank: false,
					blankText: '医嘱类型不能为空'
				}, {
					xtype: 'combobox',
					store: 'doctor.AdvContents',
					autoScroll: true,
					autoSelect: true,
					queryMode: 'remote',
					queryParam: 'term',
					queryDelay: 800,
					queryCaching: true,
					minChars: 1,
					// triggerAction: 'all',
					fieldLabel: '医嘱内容',
					name: 'content',
					displayField: 'content',
					valueField: 'content',
					itemId: 'f_content',
					enableKeyEvents: true,
					allowBlank: false,
					blankText: '医嘱内容不能为空',
					regex:/^\S{1,100}$/,
					regexText:'请输入100个以内字符'
				}, {
					xtype: 'combobox',
					store: ['长期医嘱', '临时医嘱'],
					fieldLabel: '医嘱时效',
					name: 'type',
					itemId: 'f_type',
					editable: false,
					readOnly: false,
					allowBlank: false,
					blankText: '医嘱时效不能为空'
				}, {
					name: 'state',
					itemId: 'f_state',
					editable: false,
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
					fieldLabel: '医嘱状态',
					allowBlank: false,
					blankText: '状态不能为空'
				}, {
					xtype: 'datetimefield',
					name: 'startTime',
					itemId: 'f_startTime',
					fieldLabel: '开始时间',
					format: 'Y/m/d H:i:s',
					readOnly: false,
					allowBlank: false,
					blankText: '下达时间不能为空'
				}, {
					xtype: 'datetimefield',
					name: 'endTime',
					itemId: 'f_endTime',
					fieldLabel: '结束时间',
					format: 'Y/m/d H:i:s',
					readOnly: false,
					allowBlank: false,
					blankText: '下达时间不能为空'
				}, {
					name: 'dose',
					itemId: 'f_dose',
					fieldLabel: '剂量',
					regex:/^\S{0,20}$/,
					regexText:'请输入20个以内字符'
				}, {
					xtype: 'combobox',
					store: Wj.consts.usage,
					editable: true,
					name: 'usage',
					itemId: 'f_usage',
					fieldLabel: '途径',
					regex:/^\S{0,20}$/,
					regexText:'请输入20个以内字符'
				}, {
					xtype: 'combobox',
					store: Wj.consts.dose,
					name: 'frequency',
					itemId: 'f_frequency',
					fieldLabel: '频次',
					editable: false
				}, {
					xtype: 'textarea',
					name: 'spec',
					itemId: 'f_spec',
					fieldLabel: '医生说明',
					grow: true,
					regex:/^[\s\S]{0,200}$/,
					regexText:'请输入200个以内字符'
				}, {
					xtype: 'grid',
					minHeight: 60,
					// maxHeight: 200,
					itemId:'advexecrec_grid',
					flex: 1,
					title: '医嘱执行记录',
					style: {
						marginTop:'10px'
					},

					autoScroll: true,

					store: 'surgydoctor.SurgyAdvExecRec',

					columns: [{
						text: '编号',
						dataIndex: 'id',
						width: 40
					}, {
						text: '执行人',
						dataIndex: 'exEr',
						width: 100
					}, {
						text: '执行时间',
						dataIndex: 'exTime',
						width: 120
					}, {
						text: '执行备注',
						dataIndex: 'exNote',
						width: 200
					}],

					dockedItems: [{
						xtype: 'pagebar',
						dock: 'bottom',
						store: 'surgydoctor.SurgyAdvExecRec',
						displayMsg: '- 双击进行修改 - 当前页面显示结果 {0} - {1} ,总条数：{2}',
						displayInfo: true
					}]

				}]

			}],

			buttons: [{
				text: '停止执行',
				itemId: 'stop_exec',
				icon: 'icon/error.png',
				disabled: false,
				handler: Wj.controller.SurgyDoctor.stopAdvice
			}, {
				text: '更新',
				icon: 'icon/accept.png',
				handler: Wj.controller.SurgyDoctor.editAdvice
			}, {
				text: '重置',
				icon: 'icon/reset.png',
				handler: function(){
					var f = this.up('form');
					var r = f.getRecord();
					f.loadRecord(r);
				}
			}]
			
		}];

		this.callParent(arguments);

		console.log('-- Wj.view.surgydoctor.SurgyPtAdvice init over.--');

	}

});