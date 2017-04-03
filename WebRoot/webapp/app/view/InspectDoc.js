Ext.define('Wj.view.InspectDoc', {
	extend: 'Ext.container.Container',
	alias: 'widget.inspectdoc',

	requires: [
		'Wj.view.Banner',
		'Wj.view.inspectdoc.Nav',
		//'Wj.view.Content',
		//'Wj.view.inspectdoc.InspectDocContent'
	],
	
	layout: 'border',



	initComponent: function(){

		console.log('-- Wj.view.InspectDoc init. --');
		var BannerHeight =  100;
		var InptFunItemWidth =150;
		this.items = [{
			region: 'north',
			xtype: 'banner'
		}, {
			xtype: 'container',
			region: 'west',
			width: InptFunItemWidth,
			height:(Ext.getBody().getHeight()-BannerHeight),
			layout: {
				type: 'vbox',
				align: 'stretch',
				pack: 'start'
			},
			items:[
			
			{
				xtype: 'inspectfunitem'
				//title: '检查检验'
			}
			
			]

		}, 
		{
			xtype: 'container',
			region: 'west',
			width: Ext.getBody().getWidth()-BannerHeight,///2,
			layout: {
				type: 'vbox',
				align: 'stretch',
				pack: 'start'
			},
			items:[
			
			{
				xtype: 'inspectdocnav'
				//title: '检查检验'
			}
			
			]

		},
//			{
//			xtype: 'container',
//			region: 'east',
//			//Banner height is 100
//			width: Ext.getBody().getWidth()/2,
//			layout: {
//				type: 'vbox',
//				align: 'stretch',
//				pack: 'start'
//			},
//			items:[
//			
//			{
//				
//			    xtype: 'inspectinfo',
//				height:(Ext.getBody().getHeight()-100)/2
//				
//			},{
//							
//				xtype: 'chemistryinfo',
//				height:(Ext.getBody().getHeight()-100)/2
//			
//			
//			}
//			
//			]
//
//		}
		];

		this.callParent(arguments);

		console.log('-- Wj.view.InspectDoc init over. --');

	}
})