Ext.define('Wj.view.nurse.PtUndealt', {
	extend: 'Ext.window.Window',
	alias: 'widget.ptundealt',

	modal: true,
	closable: true,
	resizable: false,
	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '分配床位 - 指定医生',

	width: 500,
	height: 400,

	initComponent: function(){

		this.items = [{
			xtype: 'grid',
			frame: true,
			autoScroll: true,
			
			store: 'nurse.PtUndealt',

			columns: [{
				text: '姓名',
				dataIndex: 'name',
				width: 100,
			}],

		}, {
			xtype: 'form',
			flex: 1,
			padding: '10 10 10 10',
			border: false,
			bodyStyle: 'background:#DFE9F6',

			items:[{
				xtype: 'fieldset',
				title: '病人信息',
				itemId: 'pt_info',

				padding: '10 10 10 10',
				border: true,
				bodyStyle: 'background:#DFE9F6',
				defaults: {
					labelSeparator: ' : ',
					msgTarget: 'side',
					anchor: '100%',
					style: {
						margin: '4px 10px',
					},
				},
				defaultType: 'textfield',

				items: [{
					xtype: 'container',
					height: 24,
					layout: {
						type: 'hbox',
						align: 'stretch',
						pack: 'start',
					},
					items: [{
						xtype: 'textfield',
						labelSeparator: ': ',
						labelAlign: 'left',
						msgTarget: 'side',
						style: {
							margin: '2px 2px',
						},
						fieldLabel: '编号',
						labelWidth: 60,
						width: 140,
						name: 'id',
						readOnly: true,
						fieldStyle: Wj.consts.css_readOnly,
					}, {
						xtype: 'textfield',
						labelSeparator: ': ',
						labelAlign: 'left',
						msgTarget: 'side',
						style: {
							margin: '2px 2px',
						},
						fieldLabel: '姓名',
						labelWidth: 60,
						width: 140,
						name: 'name',
						readOnly: true,
						fieldStyle: Wj.consts.css_readOnly,
					}],
				}, {
					xtype: 'container',
					height: 24,
					layout: {
						type: 'hbox',
						align: 'stretch',
						pack: 'start',
					},
					items: [{
						xtype: 'textfield',
						labelSeparator: ': ',
						labelAlign: 'left',
						msgTarget: 'side',
						style: {
							margin: '2px 2px',
						},
						fieldLabel: '性别',
						labelWidth: 60,
						width: 140,
						name: 'sex',
						readOnly: true,
						fieldStyle: Wj.consts.css_readOnly,
					}, {
						xtype: 'textfield',
						labelSeparator: ': ',
						labelAlign: 'left',
						msgTarget: 'side',
						style: {
							margin: '2px 2px',
						},
						fieldLabel: '年龄',
						labelWidth: 60,
						width: 140,
						name: 'age',
						readOnly: true,
						fieldStyle: Wj.consts.css_readOnly,
					}],
				}, {
					fieldLabel: 'RFID',
					name: 'rfid',
					anchor: '100%',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					fieldLabel: '受伤类型',
					name: 'woundType',
					anchor: '100%',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					fieldLabel: '受伤时间',
					name: 'woundTime',
					anchor: '100%',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					fieldLabel: '受伤地点',
					name: 'woundAddr',
					anchor: '100%',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}, {
					fieldLabel: '床位号',
					name: 'bedNum',
					anchor: '100%',
					readOnly: true,
					fieldStyle: Wj.consts.css_readOnly,
				}],

			}, {
				xtype: 'fieldset',
				title: '分配',
				itemId: 'alloc_bed',

				height: 74,
				layout: {
					type: 'hbox',
					align: 'center',
					pack: 'start',
				},

				padding: '10 10 10 10',
				border: true,
				bodyStyle: 'background:#DFE9F6',
				defaults: {
					labelSeparator: ' : ',
					msgTarget: 'side',
					anchor: '100%',
					style: {
						margin: '4px 10px',
					},
				},
				defaultType: 'textfield',

				items: [{
					xtype: 'combobox',
					itemId: 'bed_num',
					store: 'nurse.UnAllocBed',
					labelSeparator: ': ',
					labelAlign: 'right',
					msgTarget: 'side',
					style: {
						margin: '2px 2px',
					},
					fieldLabel: '床位号',
					name: 'bedNum',
					displayField: 'bedNum',
					valueField: 'bedNum',
					editable: false,
					labelWidth: 60,
					width: 140,
				}, {
					xtype: 'combobox',
					itemId: 'doc_list',
					store: 'nurse.Doctors',
					labelSeparator: ': ',
					labelAlign: 'right',
					msgTarget: 'side',
					style: {
						margin: '2px 2px',
					},
					fieldLabel: '医生',
					name: 'docId',
					displayField: 'userName',
					valueField: 'id',
					editable: false,
					labelWidth: 60,
					width: 140,
				}],
			}],

			buttons:[{
				text: '分配',
				icon: 'icon/add.png',
				disabled: false,
				handler: Wj.controller.Nurse.allocBedForUndealt,
			}],

		}];

		this.callParent(arguments);

	}
})