Ext.define('Wj.view.doctor.PtForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.ptform',

	padding: '10 10 10 10',
	border: false,
	frame: true,

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
			margin: '2px 2px',
		},
	},

	defaultType: 'textfield',

	initComponent: function(){

		this.items = [{
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
				fieldLabel: '姓名',
				labelWidth: 40,
				width: 110,
				name: 'name',
				readOnly: true,
			}, {
				xtype: 'textfield',
				labelSeparator: ': ',
				labelAlign: 'left',
				msgTarget: 'side',
				style: {
					margin: '2px 2px',
				},
				fieldLabel: '性别',
				labelWidth: 40,
				width: 110,
				name: 'sex',
				readOnly: true,
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
				fieldLabel: '年龄',
				labelWidth: 40,
				width: 110,
				name: 'age',
				readOnly: true,
			}, {
				xtype: 'textfield',
				labelSeparator: ': ',
				labelAlign: 'left',
				msgTarget: 'side',
				style: {
					margin: '2px 2px',
				},
				fieldLabel: '床位',
				labelWidth: 40,
				width: 110,
				name: 'bedNum',
				readOnly: true,
			}],
		}, {
			fieldLabel: 'ID',
			name: 'id',
			anchor: '100%',
			readOnly: true,
		}, {
			fieldLabel: '受伤类型',
			name: 'woundType',
			anchor: '100%',
			readOnly: true,
		}, {
			fieldLabel: '受伤时间',
			name: 'woundTime',
			anchor: '100%',
			readOnly: true,
		}, {
			fieldLabel: '受伤地点',
			name: 'woundAddr',
			anchor: '100%',
			readOnly: true,
		},
	
	
		
		
		
		
		
		];

		this.callParent(arguments);

	},

});