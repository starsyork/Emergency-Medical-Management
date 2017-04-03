Ext.require(['Ext.ux.grid.Printer' ]);

Ext.define('Wj.view.examdoctor.AddPhoto', {
	extend: 'Ext.window.Window',
	alias: 'widget.addphoto',

	frame: true,

	layout: {
		type: 'table',
		columns:2,
		pack: 'start',
		align: 'stretch'
	},

	title: '影像数据',
	icon: 'icon/iconphoto.png',
	itemId:'photoPanel',
	x : 100,// 设置初始位置横坐标
	y : 0,// 设置初始位置纵坐标
	enableDragDrop:true,
	collapsible : true, // 面板可以折叠
	titleCollapse : true,// 单击表头任何地方都可以折叠
	floating : true,// 设置浮动，能否拖动成功就靠它了
	draggable : {
	insertProxy : true,
	onDrag : function(e) {
	var pel = this.proxy.getEl();
	this.x = pel.getLeft(true);
	this.y = pel.getTop(true);

	var s = this.panel.getEl().shadow;
	if (s) {
	s.realign(this.x, this.y, pel.getWidth(),
	pel.getHeight());
	}
	}
	},
	initComponent: function(){

		console.log('-- Wj.view.examdoctor.AddPhoto init.--');
var UploadPanelH = 200;
var UploadPanelW =  Ext.getBody().getWidth()-280;//150 = 40(左侧label宽度）+ 110（文本框的宽度）
console.log('UploadPanelW:',UploadPanelW);
//图片Panel的尺寸
var photoPanelH = Ext.getBody().getHeight()-100-UploadPanelH; //100 是banner的高度
var photoPanelW = photoPanelH;
//影像描述panel的尺寸
var photoDecPanelH = photoPanelH;
var photoDecPanelW = UploadPanelW-photoPanelW;

    var PhotoUrl;

    this.items=[
{
    
    
    
 		xtype:'form',
 		itemId:'photoForm',
 		id:'Id_photoForm',
 		height:UploadPanelH,
        width: UploadPanelW,
        frame: true,
        title: '上传图像文件',
        collapsible: true, 
        //bodyPadding: '10 10 6',
        colspan:2,//设置跨列

	layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	},
        defaults: {
            anchor: '100%',
            allowBlank: false,
            msgTarget: 'side',
            labelWidth: 80
        },


        items: [
        
        	{
            xtype: 'textfield',
            fieldLabel: '手术编号',
           itemId:'add_photoIDForm',
            name:'id',
            value: Wj.controller.ExamDoctor.getExamPhotoId()
        },
        	{
            xtype: 'textfield',
            fieldLabel: '名称',
            itemId:'add_photoNameForm',
             emptyText: '输入影像名称',
            name:'description',
            value: Wj.controller.ExamDoctor.getExamPhotoName()
        },
        	{
				xtype: 'datetimefield',
				name: 'addTime',
				itemId: 'add_addTime',
				fieldLabel: '上传时间',
				format: 'Y/m/d H:i:s',
				value: new Date(),
				readOnly: true,
				fieldStyle: Wj.consts.css_readOnly
			},{
            xtype: 'filefield',
           itemId:'photopathForm',
            emptyText: '选择图像文件',
            fieldLabel: '路径',
            name: 'uploadDoc',
            buttonText: '选择文件',
            buttonConfig: {
                iconCls: 'upload-icon'
            }
        }],

        buttons: [{
            text: '上传',
            handler: function(){
                var form = this.up('form').getForm();

              if(form.isDirty() && form.isValid()) {
					form.submit({
		                url: Wj.url.EditInspect,
		                success: function(f, act){
		                	
                			
                			console.log('PhotoUrl OLD :',PhotoUrl);
                			PhotoUrl = act.result.msg;
							if(PhotoUrl == null){
								PhotoUrl = 'resource/img/NoPhoto.png';
							}
							console.log('PhotoUrl New :',PhotoUrl);
							
							var oa = Ext.ComponentQuery.query('#addphotoShow')[0];
							
							
//							oa.removeAll();
//							oa.add(	{
//						    xtype:'box',
//						    itemId:'photoShowbox',
//					        autoEl: {
//					            style: 'width:250px;height:250px;margin:0px auto;' +
//					            		'border:1px solid #ccc; text-align:center;' +
//					            		'padding-top:2px;margin-bottom:2px',
//					            tag: 'img',
//					            id: 'imageshow',
//					            src:PhotoUrl
//					    	}
//			
//					    	
//			    		});
//							oa.doLayout();
 //****************************************************************
 							var tempurl;
						    var tempsrc;

						    if(oa && PhotoUrl)
						    {
						    	tempurl = PhotoUrl;
						    }else tempurl = 'resource/img/NoPhotoDis.png';
						    				
						    
						    tempsrc = tempurl.split(";");
						    
//						    Wj.TEMPSRC = tempsrc;
//						    Wj.imgID = 0;//image 默认  显示第一张
						    
						    console.log("tempsrc Length:"+tempsrc.length);
						    for(var j =0;j<tempsrc.length;j++){
						    	console.log("URL["+j+"]:"+tempsrc[j]);
						    }
						    var width = "100px",height = "100px";
						    
						    oa.removeAll();
						    
						    if(tempsrc.length > 0)
						    {
								for(var i= 0;i < tempsrc.length;i++){
									oa.add({
								    xtype:'box',
							        autoEl: {
							            style: 'width:'+width+';height:'+height+';margin:0px auto;' +
							            		'border:1px solid #ccc; text-align:center;' +
							            		'padding-top:2px;margin-bottom:2px',
							            tag: 'img',
							            src:tempsrc[i]
							    		}
					    			});
								}
						    
						    }else{
						    	//暂无图片  处理
						    	oa.add({
								    xtype:'box',
							        autoEl: {
							            style: 'width:'+width+';height:'+height+';margin:0px auto;' +
							            		'border:1px solid #ccc; text-align:center;' +
							            		'padding-top:2px;margin-bottom:2px',
							            tag: 'img',
							            src:'resource/img/NoPhotoDis.png'
							    		}
					    			});
						    }
							
							

                			
        
		                	Ext.Msg.alert('提示', '上传成功！', function() {
		            			console.log('act msg:',act.result.msg);
		
		                	});
		                	
		                	//刷新 Grid-----start-----
							var gr = Ext.getCmp("exam_ins_main_grid");
							console.log('gr:'+gr);
							var gr1 = Ext.getCmp("examptphoto");
							console.log('gr:'+gr);
							var tempid = Wj.controller.ExamDoctor.getPtId();	
							Wj.controller.ExamDoctor.RefreshGrid(gr,tempid);
							Wj.controller.ExamDoctor.RefreshGrid(gr1,tempid);
							//刷新 Grid-----end-----
		                	
		                	
		                	
		                },
		                failure: function(f, act){
		                	Ext.Msg.alert('提示', '上传失败，检查网络！', function() {
		                	});
							console.log('act:',act);
		                }
		            });
     
				}  			
             }
         
            
        },{
            text: '重置',
            handler: function() {
                this.up('form').getForm().reset();
            }
        }]
   
    },{
		xtype:'panel',
		height:photoPanelH,
		width:photoPanelW,
		title:'影像所见',
		itemId:'addphotoShow',
		
		items:[
			{
			    xtype:'box',
			    itemId:'photoShowbox',
		        autoEl: {
		            style: 'width:250px;height:250px;margin:0px auto;' +
		            		'border:1px solid #ccc; text-align:center;' +
		            		'padding-top:2px;margin-bottom:2px',
		            tag: 'img',
		            id: 'imageshow',
		            src:'resource/img/NoPhoto.png' 
		    	}
			}
		
		]
    },
	    {
			xtype:'form',
			title:'诊断结果及分析',
			itemId:'photodec',
			frame:false,
			layout: {
					type: 'vbox',
					pack: 'start',
					align: 'stretch'
				},
			defaults: {
	            anchor: '100%',
	            allowBlank: false
	
	        },
		height:photoDecPanelH,
		width:photoDecPanelW,
        	items: [
			{
			    xtype: 'textfield',
			    fieldLabel: '伤员id',
			   itemId:'add_photoIDForm',
			    name:'id',
			    hidden: true, 
			    hideLabel:true ,
			    value: Wj.controller.ExamDoctor.getExamPhotoId()
			},
        	 {
				xtype: 'textarea',
				name:'illustration',
				itemId: 'photodec_content',
				id:'examillustn_con',
				height: photoDecPanelH,
				grow : true,
				frame:false,
				growMax : 8,
				regex:/^[\s\S]{0,500}$/,
				regexText:'请输入500个以内字符'
			},
            
            ],  
        tbar: ['->',{  
                xtype: 'button',  
                text: '保存',  
                iconCls:'icon/accept.png', 
                width: 58,  
                height: 20,  
                handler:function(){  
					   var form = this.up('form').getForm();
					  // var form = Ext.getCmp('Id_photoForm').getForm();
					    console.log('form:',form);
					   //var g = Ext.ComponentQuery.query('#photodec_content')[0];
					   var g = Ext.getCmp('examillustn_con');
					   console.log('g:',g);
					   var tcontent = g.getValue();
					   console.log('tcontent:',tcontent);
		              if(form.isDirty() &&form.isValid()) {
							form.submit({
				                url: Wj.url.EditInspect,
				                success: function(f, act){
				                	Ext.Msg.alert('提示', '保存成功！', function() {
				                		Ext.getCmp('exam_ins_main_grid').getStore().getProxy().extraParams = {
			                				all:  Wj.all
			                			}
				                		Ext.getCmp('exam_ins_main_grid').getStore().load({
				        					scope: this,
				        					callback: function(r, o, suc){
				        						if(suc && r && r.length) {
				        						//	this.getDocptinspection().getSelectionModel().select(0);
				        						}
				        					}
				        				})
		                			});
				                },
				                failure: function(f, act){
			                		Ext.Msg.alert('提示', '保存失败，检查网络！', function() {
			                			Ext.getCmp('exam_ins_main_grid').getStore().getProxy().extraParams = {
			                				all:  Wj.all
			                			}
			                			Ext.getCmp('exam_ins_main_grid').getStore().load({
				        					scope: this,
				        					callback: function(r, o, suc){
				        						if(suc && r && r.length) {
				        						//	this.getDocptinspection().getSelectionModel().select(0);
				        						}
				        					}
				        				})
			                			});
		                		},
		                		
							})
		              }
                	
                	
                	
                }  
            }]
	    }
	    
    
    
    ]



		this.callParent(arguments);

		console.log('-- Wj.view.examdoctor.AddPhoto init over.--');

	}

});