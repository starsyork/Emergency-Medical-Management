Ext.define('Wj.view.doctor.AddPtAdvice', {
	extend: 'Ext.window.Window',
	requires:'Wj.MyApp.ux.DateTimeField',
	alias: 'widget.addptadv',

	modal: true,
	closable: true,
	resizable: false,
	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch',
	},

	title: '新增医嘱',

	width: 500,
	height: 480,

	initComponent: function(){

		this.items = [{
			xtype: 'form',
			flex: 1,
			id:'new_form',
			padding: '10 2 10 2',
			border: false,
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

			items:[{
				fieldLabel: '伤员编号',
				name: 'id',
				itemId: 'add_id',
				value: Wj.controller.Doctor.getPtId(),
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly,
			}, {
				fieldLabel: '伤员姓名',
				name: 'name',
				itemId: 'add_name',
				value: Wj.controller.Doctor.getPtName(),
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly,
			}, {
				xtype: 'combobox',
				store: Wj.consts.adviceType,
				fieldLabel: '医嘱类型',
				name: 'type2',
				itemId: 'add_type2',
				editable: false,
				readOnly: false,
				allowBlank: false,
				blankText: '医嘱类型不能为空',
				listeners:{
					  'select': function()
					  {
					  var g = Ext.ComponentQuery.query('#add_content')[0];
					  
					 console.log('g:'+g);
					 console.log('value:'+g.getValue());
					 
					  	g.setValue('');				  		
					  	var t = Ext.ComponentQuery.query('#add_type2')[0];
					  	var type = t.getValue();
					  	console.log('type:'+type);
							val = '';
							var st = Ext.ComponentQuery.query('#add_content')[0];
							store = st.getStore();

						store.getProxy().extraParams = {
							type2: type,
							term: val
						},
						store.load({
							callback: function(records, o, suc){
								console.log('suc:'+suc);
							}
							
							});

						console.log('store.getProxy().extraParams', store.getProxy().extraParams);

						Wj.tmp.timer = 0;
					  }
					  
					  }
					  //---------------------------------
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
				itemId: 'add_content',
				id: 'f_content',
				value:' ',
				enableKeyEvents: true,
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly,
				allowBlank: false,
				blankText: '医嘱内容不能为空',
				regex:/^\S{1,100}$/,
				regexText:'请输入100个以内字符',
			}, {
				xtype: 'combobox',
				store: ['长期医嘱', '临时医嘱'],
				fieldLabel: '医嘱时效',
				name: 'type',
				itemId: 'add_type',
				editable: false,
				allowBlank: false,
				blankText: '医嘱时效不能为空',
			}, {
				xtype: 'combobox',
				store: ['已执行', '未执行'],
				name: 'state',
				itemId: 'add_state',
				editable: false,
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly,
				fieldLabel: '医嘱状态',
				allowBlank: false,
				blankText: '状态不能为空',
			}, {
				xtype: 'datetimefield',
				name: 'startTime',
				itemId: 'add_startTime',
				fieldLabel: '开始时间',
				value: new Date(),
				format: 'Y/m/d H:i:s',
				allowBlank: false,
				blankText: '下达时间不能为空',
			}, {
				xtype: 'datetimefield',
				name: 'endTime',
				itemId: 'add_endTime',
				fieldLabel: '结束时间',
				format: 'Y/m/d H:i:s',
				allowBlank: false,
				blankText: '下达时间不能为空',
			}, {
				name: 'dose',
				itemId: 'add_dose',
				fieldLabel: '剂量',
				regex:/^\S{0,20}$/,
				regexText:'请输入20个以内字符',
			}, {
				xtype: 'combobox',
				store: Wj.consts.usage,
				editable: true,
				name: 'usage',
				itemId: 'add_usage',
				fieldLabel: '途径',
				regex:/^\S{0,20}$/,
				regexText:'请输入20个以内字符',
			}, {
				xtype: 'combobox',
				store: Wj.consts.dose,
				name: 'frequency',
				itemId: 'add_frequency',
				fieldLabel: '频次',
				editable: false,
			}, {
				xtype: 'textarea',
				name: 'spec',
				itemId: 'add_spec',
				height: 100,
				fieldLabel: '医生说明',
				grow: true,
				regex:/^[\s\S]{0,200}$/,
				regexText:'请输入200个以内字符',
			}]

		}];

		this.buttons = [{
			text: '确定',
			itemId: 'accept',
			icon: 'icon/accept.png',
			handler: Wj.controller.Doctor.confirmAddAdvice,
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