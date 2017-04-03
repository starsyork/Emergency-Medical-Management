Ext.define('Wj.view.admin.VoluPermMgr', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.adminvolupermmgr',

	requires: [    
				'Ext.grid.*',
			    'Ext.data.*',
			    'Ext.selection.CheckboxModel',
			    'Wj.view.util.Pagingtoolbar'
			  ],

	closable: true,
	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},

	title: '志愿者待审核',

	initComponent: function(){

		console.log('-- Wj.view.admin.VoluPermMgr init.--');
		
		this.items= [	
			Ext.create('Ext.grid.Panel', {
			        //store: getLocalStore(),
			        selModel: Ext.create('Ext.selection.CheckboxModel'),
					store: 'volunteer.VolunteerPerm',
			        columnLines: true,
			        frame: true,
			        autoScroll: true,
			        title: '列表',
			        //iconCls: 'icon-grid',
			        flex:1,
			   
			       
			        //-------------------------
			  dockedItems: [{
				xtype: 'toolbar',
				dock: 'bottom',
				layout: {
                	pack: 'center'
            	},
            //	padding:'10',、、影响高度
				items: [
				
				{
					text: '同意',
					icon: 'icon/btn_agree.png',
					itemId: 'vpagree',
					handler: Wj.controller.Admin.agreeVoluPerm
					
				}, {
					text: '拒绝',
					icon: 'icon/btn_deny.png',
					itemId: 'vpdeny',
					disabled: false,
					handler: function(){
						alert('remove!');
					},
					handler: Wj.controller.Admin.denyVoluPerm
				}
				
				]
			}],
			        //*******************
			    columns: [{
				text: '编号',
				dataIndex: 'user.id',
				flex: 1,
				minWidth: 40,
				allowBlank: false,
				blankText: '编号不能为空'
			},{
				text: '用户名',
				flex: 1,
				minWidth: 40,
				dataIndex: 'user.loginName',
				allowBlank: false,
				blankText: '姓名不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}, {
				text: '姓名',
				flex: 1,
				minWidth: 40,
				dataIndex: 'name',
				allowBlank: false,
				blankText: '姓名不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			},{
				text: '性别',
				flex: 1,
				minWidth: 10,
				dataIndex: 'sex',
				allowBlank: false,
				blankText: '年龄不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				},
			renderer : function(val, meta) {
			     switch (val) {
			     case 1:
			      return '男';
			      break;
			     case 2:
			      return '女';
			      break;
			     }
			     return val;
			    }
			},  {
				text: '年龄',
				flex: 1,
				minWidth: 10,
				dataIndex: 'age',
				allowBlank: false,
				blankText: '技能不能为空，可以填“待定”',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}			, {
				text: '家庭地址',
				flex: 1,
				minWidth: 100,
				dataIndex: 'address',
				allowBlank: false,
				blankText: '技能不能为空，可以填“待定”',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}	, {
				text: '联系电话',
				flex: 1,
				minWidth: 100,
				dataIndex: 'phone',
				allowBlank: false,
				blankText: '技能不能为空，可以填“待定”',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}, {
				text: '身份证号',
				flex: 1,
				minWidth: 140,
				dataIndex: 'IDcard',
				allowBlank: false,
				blankText: '身份证号不能为空',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			},{
				text: '技能',
				flex: 1,
				minWidth: 40,
				dataIndex: 'specialty',
				allowBlank: false,
				blankText: '技能不能为空，可以填“待定”',
				editor: {
					xtype: 'textfield',
					regex:/^\S{1,20}$/,
					regexText:'请输入20个以内字符'
				}
			}
			]

			        
			        //-----------------------------
			        }),
			       Ext.create('Ext.panel.Panel',{
	
			        	 dockedItems: [{
							xtype: 'toolbar',
							dock: 'bottom',
							layout: {
										type: 'hbox',
			                			pack: 'center',
										align: 'stretch'
			            	},
			            	items:[
				      		{		
								xtype: 'pagebar',
								dock: 'bottom',
								store: 'volunteer.VolunteerPerm',
								displayMsg: '- 双击进行修改 - 当前页面显示结果 {0} - {1} ,总条数：{2}',
								displayInfo: true
							}
			            	]
			        	 }]			        	
			        })			       			  				
		];

		this.callParent(arguments);
		

		console.log('-- Wj.view.admin.VoluPermMgr init over.--');

	}

});