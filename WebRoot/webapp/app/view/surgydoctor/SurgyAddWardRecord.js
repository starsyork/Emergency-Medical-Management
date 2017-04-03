Ext.define('Wj.view.surgydoctor.SurgyAddWardRecord', {
	extend: 'Ext.window.Window',
	alias: 'widget.surgyaddwardrecord',

	modal: true,
	closable: true,
	resizable: false,
	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '新增准备',

	width: 500,
	height: 400,

	initComponent: function(){

		this.items = [{
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
			items:[{
				fieldLabel: '伤员id',
				name: 'pid',
				itemId: 'pid',
				value: Wj.controller.SurgyDoctor.getPtId(),
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly
			},{
				fieldLabel: '手术名称',
				name: 'content',
				itemId: 'content',
				value: Wj.controller.SurgyDoctor.getSurgyName(),
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly

			},{
				fieldLabel: '手术申请号',
				name: 'applyId',
				itemId: 'applyId',
				value: Wj.controller.SurgyDoctor.getSurgyId(),
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly

			},{
				xtype: 'combobox',
				store: Wj.consts.surgyType,
				fieldLabel: '准备物品类型',
				name: 'type2',
				itemId: 'type2',
				editable: false,
				readOnly: false,
				allowBlank: false,
				blankText: '准备类型不能为空',
				listeners:{
					  'select': function()
					  {
					  var g = Ext.ComponentQuery.query('#f_content')[0];
					  
					 console.log('g:'+g);
					 console.log('value:'+g.getValue());
					 
					  	g.setValue('');				  		
					  	var t = Ext.ComponentQuery.query('#type2')[0];
					  	var type = t.getValue();
					  	console.log('type:'+type);
							val = '';
							var st = Ext.ComponentQuery.query('#f_content')[0];
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
			},
				{
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
					fieldLabel: '准备物品名称',
					name: 'drugname',
					displayField: 'content',
					valueField: 'content',
					itemId: 'f_content',
					enableKeyEvents: true,
					allowBlank: false,
					blankText: '准备物品名称不能为空',
					regex:/^\S{1,100}$/,
					regexText:'请输入100个以内字符'
				},{
				xtype: 'numberfield',
				fieldLabel: '所需数量',
				name: 'amount',
				itemId: 'drugnum',
				value:'1',
				minValue:1,
				allowDecimals:false
				
			}
				/*,{
				fieldLabel: '操作员',
				name: 'noteperson',
				itemId: 'surgy_noteperson'

			}*/
			
			]
			}]

		}];

		this.buttons = [{
			text: '确定',
			itemId: 'accept',
			icon: 'icon/accept.png',
			handler: Wj.controller.SurgyDoctor.confirmAddWardRecord
		}, {
			text: '重置',
			itemId: 'reset',
			icon: 'icon/reset.png',
			handler: function(){
				
				var fm = this.up('window').down('form').getForm();
				var n = fm.findField('name').value;
				fm.reset();
				fm.findField('name').setValue(n);

			}
		}];

		this.callParent(arguments);

	}
})