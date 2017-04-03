Ext.define('Wj.view.Nurse', {
	extend: 'Ext.container.Container',
	alias: 'widget.nurse',
	
	requires: [
		'Wj.view.Banner',
		'Wj.view.nurse.Nav',
		'Wj.view.Content',
	],

	layout: 'border',
	
	initComponent: function(){

		console.log('-- Wj.view.Nurse init. --');

		this.items = [{
			region: 'north',
			xtype: 'banner',
		}, {
			xtype: 'container',
			region: 'west',
			width: 260,
			layout: {
				type: 'vbox',
				align: 'stretch',
				pack: 'start',
			},
			
			items: [{
				xtype: 'nursenav',
				flex: 1,
			}, {
				xtype: 'ptform',
				itemId: 'ptform',
				minHeight: 150,
			},
			{
				xtype: 'container',
				height: 24,
				layout: {
					type: 'hbox',
					align: 'stretch',
					pack: 'start'
				},
				items:[	
				{
					xtype: 'textfield',
					id:'id_nurseSearch',
					//labelSeparator: ': ',
					labelAlign: 'left',
					msgTarget: 'side',
					style: {
						margin: '2px 2px'
					},
					//fieldLabel: '年龄',
					labelWidth: 40,
					flex:1,
					width: 170,
					name: 'search',
					listeners: {
		                specialkey: function(field, e){
		                    // e.HOME, e.END, e.PAGE_UP, e.PAGE_DOWN,
		                    // e.TAB, e.ESC, arrow keys: e.LEFT, e.RIGHT, e.UP, e.DOWN
		                    if (e.getKey() == e.ENTER) {
		                        var gr = Ext.getCmp('id_nurseNavGrid');
		                        console.log('gr:'+gr);
		                        
		                    		gr.getStore().load({
		                    		scope: this,
		                    		callback: function(r, o, suc){
		                    			if(suc && r && r.length){
		                    				var searchCon = Ext.getCmp('id_nurseSearch').getValue();
		                    				console.log('searchCon:'+searchCon);
		                    				var selectItem = 0;
		                    				
		                    				if(searchCon !== null){
		                    					console.log('r.length:'+r.length);
		                    					for(var i = 0;i<r.length;i++)
		                    					{
//		                    						console.log('ID:'+i+'rfid:'+r[i].data.rfid+'name:'+r[i].data.name);
		                    						//检索姓名 或者 rfid 床位
		                    						if((r[i].data.rfid == searchCon )||(r[i].data.name == searchCon )
		                    							||(r[i].data.bedNum == searchCon ))
		                    						{
		                    							selectItem = i;
		                    							//console.log('selectItem:'+selectItem);

		                    							gr.getSelectionModel().select(selectItem);
		                    							break;
		                    						}
		                    					}					
		                    				}
		                    				
		                    			}
		                    		}
		                    	});
		                        
		                        }
		                }
					}

				},{
					xtype:'button',
					text: '搜索伤员',
					icon: 'icon/look.png',
					id: 'id_nurseSearchbtn',
					handler: Wj.controller.Nurse.SearchPt
				}]
			}	
			
			
			],
		}, {
			region: 'center',
			xtype: 'content',
		}];

		this.callParent(arguments);

		console.log('-- Wj.view.Nurse init over. --');

	}
})