Ext.define('Wj.view.examdoctor.imgdelviewer', {
   //extend: 'Ext.container.Container',
     extend:'Ext.window.Window',
	alias: 'widget.examimgdelviewer',
	
	itemId:'itemid_imgdelviewer',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    
	    title:'删除图片',


		width: 500,
		height: 500,
    
    config: {
        isMoving: false,
        imageWidth: null,
        imageHeight: null,
        originalImageWidth: null,
        originalImageHeight: null,
        clickX: null,
        clickY: null,
        lastMarginX: null,
        lastMarginY: null,
        rotation: 0
    },

    initComponent: function () {
        var me = this;

        me.tooltips = me.tooltips || {};

        me.tooltips = Ext.applyIf(me.tooltips, {
            stretchHorizontally: 'Stretch horizontally',
            stretchVertically: 'Stretch vertically',
            stretchOptimally: 'Stretch optimally',
            zoomIn: 'Zoom in',
            zoomOut: 'Zoom out',
            rotateClockwise: 'Rotate clockwise',
            rotateAntiClockwise: 'Rotate anticlockwise'
        });

        me.items = [{
            xtype: 'toolbar',
            defaults: {
                tooltipType: 'title'
            },
            items: [{
                xtype: 'button',
                tooltip: me.tooltips.stretchHorizontally,
                icon: 'resource/images/imageviewer/stretch_horizontally.png',
                listeners: { click: me.stretchHorizontally, scope: me }
            }, {
                xtype: 'button',
                tooltip: me.tooltips.stretchVertically,
                icon: 'resource/images/imageviewer/stretch_vertically.png',
                listeners: { click: me.stretchVertically, scope: me }
            }, {
                xtype: 'button',
                tooltip: me.tooltips.stretchOptimally,
                icon: 'resource/images/imageviewer/stretch_optimally.png',
                listeners: { click: me.stretchOptimally, scope: me }
            }, {
                xtype: 'button',
                tooltip: "放大",
                icon: 'resource/images/imageviewer/zoom_in.png',
                listeners: { click: me.zoomIn, scope: me }
            }, {
                xtype: 'button',
                tooltip: "缩小",
                icon: 'resource/images/imageviewer/zoom_out.png',
                listeners: { click: me.zoomOut, scope: me }
            }, {
                xtype: 'button',
                tooltip: "顺时针旋转",
                icon: 'resource/images/imageviewer/arrow_rotate_clockwise.png',
                listeners: { click: me.rotateClockwise, scope: me }
            }, {
                xtype: 'button',
                tooltip: "逆时针旋转",
                icon: 'resource/images/imageviewer/arrow_rotate_anticlockwise.png',
                listeners: { click: me.rotateAntiClockwise, scope: me }
            },
            {
                xtype: 'button',
                tooltip: "前一张",
                icon: 'resource/images/imageviewer/formerimg.png',
                listeners: { click: Wj.controller.ExamDoctor.FormerImg, scope: me }
            },{
                xtype: 'button',
                tooltip:"下一张",
                icon: 'resource/images/imageviewer/nextimg.png',
                listeners: { click: Wj.controller.ExamDoctor.NextImg, scope: me }
            },
            {
                xtype: 'button',
                tooltip:"删除",
                icon: 'icon/delete.gif',
                listeners: { click:me.DeleteImage , scope: me }
                //Wj.controller.ExamDoctor.DeleteImg
            }]
        }, 
        {
            xtype: 'container',
            itemId: 'imagecontainer',
            flex: 1,
            style: {
                overflow: 'hidden',
                backgroundColor: '#f2f1f0',
                padding: '10px',
                cursor: 'move'
            },
            items: [{
                xtype: 'image',
                mode: 'element',
                id:'id_delImag',
                //src: me.src,
                src: Wj.TEMPSRC[Wj.imgID],
                style: {
                    boxShadow: '0 0 5px 5px #888'
                },
                listeners: {
                    render: function (image) {
						
                        image.el.dom.onload = function () {
                            me.setRotation(0);
                            me.rotateImage();
                            me.setOriginalImageWidth(image.el.dom.width);
                            me.setOriginalImageHeight(image.el.dom.height);
                            me.setImageWidth(image.el.dom.width);
                            me.setImageHeight(image.el.dom.height);
                            me.stretchOptimally();
                            console.log('me.src:'+image.el.dom.src);
                        };
                    }
                }
            },
            {  
         xtype:'form',
 		id:'id_delphotoForm',
		itemId:'itemid_delphotoForm',
        frame: true,

        defaults: {
            anchor: '100%',
            allowBlank: false,
            msgTarget: 'side',
            labelWidth: 80
        },


        items: [
        
        	{
            xtype: 'textfield',
            fieldLabel: '伤员检验id',
           //itemId:'add_photoIDForm',
            hidden:false,
            hideLabel:false,
            name:'id',
            value: Wj.controller.ExamDoctor.getExamPhotoApplyId()
        },
        	{
            xtype: 'textfield',
            fieldLabel: 'delurl',
            id:'del_photoUrlForm',
            hidden:false,
            hideLabel:false,
            name:'delurl',
            value: Wj.TEMPSRC[Wj.imgID]
        }]
            
            }
            
            ]
        }
        
        ];

        me.callParent();
    },

    initEvents: function () {
        var me = this;

        me.mon(me.getImageContainer().getEl(), {
            mouseup: me.mouseup,
            mousedown: me.mousedown,
            mousemove: me.mousemove,
            scope: me
        });

        me.callParent();
    },

    stretchHorizontally: function () {
        var me = this,
            imageContainerWidth = me.getImageContainer().getWidth();

        me.setImageSize({
            width: imageContainerWidth - 20,
            height: me.getOriginalImageHeight() * (imageContainerWidth - 20) / me.getOriginalImageWidth()
        });

        me.centerImage();
    },

    stretchVertically: function () {
        var me = this,
            imageContainerHeight = me.getImageContainer().getHeight();

        me.setImageSize({
            width: me.getOriginalImageWidth() * (imageContainerHeight - 20) / me.getOriginalImageHeight(),
            height: imageContainerHeight - 20
        });

        me.centerImage();
    },

    stretchOptimally: function () {
        var me = this,
            imageContainer = me.getImageContainer(),
            adjustedImageSize = me.getAdjustedImageSize();

        if (adjustedImageSize.width * imageContainer.getHeight() / adjustedImageSize.height > imageContainer.getWidth()) {
            me.stretchHorizontally();
        } else {
            me.stretchVertically();
        }
    },

    centerImage: function () {
        var me = this,
            imageContainer = me.getImageContainer(),
            adjustedImageSize = me.getAdjustedImageSize();

        me.setMargins({
            top: (imageContainer.getHeight() - adjustedImageSize.height - 20) / 2,
            left: (imageContainer.getWidth() - adjustedImageSize.width - 20) / 2
        });
    },

    mousedown: function (event) {
        var me = this,
            margins = me.getMargins();

        event.stopEvent();

        me.setClickX(event.getPageX());
        me.setClickY(event.getPageY());
        me.setLastMarginY(margins.top);
        me.setLastMarginX(margins.left);

        me.setIsMoving(true);
    },

    mousemove: function (event) {
        var me = this;

        if (me.getIsMoving()) {
            me.setMargins({
                top: me.getLastMarginY() - me.getClickY() + event.getPageY(),
                left: me.getLastMarginX() - me.getClickX() + event.getPageX()
            });
        }
    },

    mouseup: function () {
        var me = this;

        if (me.getIsMoving()) {
            me.setClickX(null);
            me.setClickY(null);
            me.setLastMarginX(null);
            me.setLastMarginY(null);
            me.setIsMoving(false);
        }
    },

    zoomOut: function (btn, event, opts) {
        var me = this,
            margins = me.getMargins(),
            adjustedImageSize = me.getAdjustedImageSize();

        me.setMargins({
            top: margins.top + adjustedImageSize.height * 0.05,
            left: margins.left + adjustedImageSize.width * 0.05
        });

        me.setImageSize({
            width: adjustedImageSize.width * 0.9,
            height: me.getOriginalImageHeight() * adjustedImageSize.width * 0.9 / me.getOriginalImageWidth()
        });

        event.stopEvent();
    },

    zoomIn: function (btn, event, opts) {
        var me = this,
            margins = me.getMargins(),
            adjustedImageSize = me.getAdjustedImageSize();

        me.setMargins({
            top: margins.top - adjustedImageSize.height * 0.05,
            left: margins.left - adjustedImageSize.width * 0.05
        });

        me.setImageSize({
            width: adjustedImageSize.width * 1.1,
            height: me.getOriginalImageHeight() * adjustedImageSize.width * 1.1 / me.getOriginalImageWidth()
        });

        event.stopEvent();
    },

    rotateClockwise: function () {
        var me = this,
            rotation = me.getRotation();

        rotation += 90;

        if (rotation > 360) {
            rotation -= 360;
        }

        me.setRotation(rotation);
        me.rotateImage();
    },

    rotateAntiClockwise: function () {
        var me = this,
            rotation = me.getRotation();

        rotation -= 90;

        if (rotation < 0) {
            rotation += 360;
        }

        me.setRotation(rotation);
        me.rotateImage();
    },

    rotateImage: function () {
        var me = this,
            tmpOriginalWidth,
            transformStyle = 'rotate(' + me.getRotation() + 'deg)';

        tmpOriginalWidth = me.getOriginalImageWidth();
        me.setOriginalImageWidth(me.getOriginalImageHeight());
        me.setOriginalImageHeight(tmpOriginalWidth);

        me.getImage().getEl().applyStyles({
            'transform': transformStyle,
            '-o-transform': transformStyle,
            '-ms-transform': transformStyle,
            '-moz-transform': transformStyle,
            '-webkit-transform': transformStyle
        });

        me.setMargins(me.getMargins());
    },

    setMargins: function (margins) {
        var me = this,
            rotation = me.getRotation(),
            adjustedImageSize = me.getAdjustedImageSize(),
            imageContainer = me.getImageContainer(),
            imageContainerWidth = imageContainer.getWidth(),
            imageContainerHeight = imageContainer.getHeight();

        if (adjustedImageSize.width > imageContainerWidth - 20) {
            if (margins.left > 0) {
                margins.left = 0;
            } else if (margins.left < imageContainerWidth - adjustedImageSize.width - 20) {
                margins.left = imageContainerWidth - adjustedImageSize.width - 20;
            }
        } else {
            if (margins.left < 0) {
                margins.left = 0;
            } else if (margins.left > imageContainerWidth - adjustedImageSize.width - 20) {
                margins.left = imageContainerWidth - adjustedImageSize.width - 20;
            }
        }

        if (adjustedImageSize.height > imageContainerHeight - 20) {
            if (margins.top > 0) {
                margins.top = 0;
            } else if (margins.top < imageContainerHeight - adjustedImageSize.height - 20) {
                margins.top = imageContainerHeight - adjustedImageSize.height - 20;
            }
        } else {
            if (margins.top < 0) {
                margins.top = 0;
            } else if (margins.top > imageContainerHeight - adjustedImageSize.height - 20) {
                margins.top = imageContainerHeight - adjustedImageSize.height - 20;
            }
        }

        if (rotation === 90 || rotation === 270) {
            var marginAdjustment = (me.getImageHeight() - me.getImageWidth()) / 2;
            margins.top = margins.top - marginAdjustment;
            margins.left = margins.left + marginAdjustment;
        }

        me.getImage().getEl().setStyle('margin-left', margins.left + 'px');
        me.getImage().getEl().setStyle('margin-top', margins.top + 'px');
    },

    getMargins: function () {
        var me = this,
            rotation = me.getRotation(),
            imageEl = me.getImage().getEl();

        var margins = {
            top: parseInt(imageEl.getStyle('margin-top'), 10),
            left: parseInt(imageEl.getStyle('margin-left'), 10)
        };

        if (rotation === 90 || rotation === 270) {
            var marginAdjustment = (me.getImageHeight() - me.getImageWidth()) / 2;
            margins.top = margins.top + marginAdjustment;
            margins.left = margins.left - marginAdjustment;
        }

        return margins;
    },

    getAdjustedImageSize: function () {
        var me = this,
            rotation = me.getRotation();

        if (rotation === 90 || rotation === 270) {
            return {
                width: me.getImageHeight(),
                height: me.getImageWidth()
            };
        } else {
            return {
                width: me.getImageWidth(),
                height: me.getImageHeight()
            };
        }
    },

    setImageSize: function (size) {
        var me = this,
            rotation = me.getRotation();

        if (rotation === 90 || rotation === 270) {
            me.setImageWidth(size.height);
            me.setImageHeight(size.width);
        } else {
            me.setImageWidth(size.width);
            me.setImageHeight(size.height);
        }
    },

    applyImageWidth: function (width) {
        var me = this;
        me.getImage().setWidth(width);
        return width;
    },

    applyImageHeight: function (height) {
        var me = this;
        me.getImage().setHeight(height);
        return height;
    },

    getImage: function () {
        return this.query('image')[0];
    },

    getImageContainer: function () {
        return this.query('#imagecontainer')[0];
    },
    
    DeleteImage:function()
    {
    		var PhotoUrl;
    			//var form = this.up('form').getForm();
			 var form = Ext.getCmp('id_delphotoForm').getForm();
			  //var form = Ext.ComponentQuery.query('#itemid_imgdelviewer > #itemid_delphotoForm')[0].getForm();
			   console.log('form:'+form);
			  console.log('oa:'+ Ext.ComponentQuery.query('#examphotopanel')[0]);
			//  var local = 
              if( form.isValid()) {
              	console.log('ready submit!');
					form.submit({
						
		                url: Wj.url.EditDocPtCourse,
		                success: function(f, act){
		                	
                			
                			console.log('PhotoUrl OLD :',PhotoUrl);
                			PhotoUrl = act.result[0];
							if(PhotoUrl == null){
								PhotoUrl = 'resource/img/NoPhoto.png';
							}
							console.log('PhotoUrl New :',PhotoUrl);
							
							var oa = Ext.ComponentQuery.query('#examphotopanel')[0];
 							var tempurl;
						    var tempsrc;

						    if(oa && PhotoUrl)
						    {
						    	tempurl = PhotoUrl;
						    }else tempurl = 'resource/img/NoPhotoDis.png';
						    				
						    
						    tempsrc = tempurl.split(";");

						    
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
 //****************************************************************
        
		                	Ext.Msg.alert('提示', '删除照片成功！', function() {
		            			console.log('act msg:',act.result.msg);
		
		                	});
		                	
		                	//Wj.controller.ExamDoctor.NextImg();
		                	var me = this;
		                	if(Wj.TEMPSRC.length > 0)
		                	{
		                		if((Wj.imgID + 1) < Wj.TEMPSRC.length)
		                		{
		            	    		Wj.imgID = Wj.imgID + 1;
		            	    		Ext.getCmp('id_delImag').el.dom.src = Wj.TEMPSRC[Wj.imgID];
		            	    		console.log('next img');
//		            	    		console.log('NowID:'+ Wj.imgID);
		            	    		Ext.getCmp('del_photoUrlForm').setValue(Wj.TEMPSRC[Wj.imgID]);
		                		
		                		}else{
		            	    		Ext.MessageBox.show({
		            		    		title:"提示",
		            		    		msg:"已经是最后一张图片！",
		            		    		modal:true,
		            		    		buttons:Ext.Msg.OK,
		            		    		icon: Ext.Msg.WARNING
		            	    		});
		                		}
		                	}
		                	
		                	
		                	
		                	
		                	
		                	//刷新 Grid-----start-----
							var gr = Ext.getCmp("examptphoto");
							console.log('gr:'+gr);
							 var selected = gr.getSelectionModel().getLastSelected();
								console.log('selected'+selected);
							   var idx = Ext.data.StoreManager.lookup(gr.getStore()).indexOf(selected);
							   
							   console.log('idx'+idx);
							var tempid = Wj.controller.ExamDoctor.getPtId();							
							if(gr){
									console.log('reload  !');
								gr.getStore().getProxy().extraParams = { id: tempid };
								console.log('tempid:'+tempid);
								gr.getStore().loadPage(1, {
									scope: gr,
									callback: function(r, o, suc){
										console.log('r  :'+r);
										console.log('suc  :'+suc);
										if(suc && r && r.length){
											gr.getSelectionModel().select(idx);
											console.log('reload success!');
										}
									}
								});
							}
							//刷新 Grid-----end-----

		                	
		                },
		                failure: function(f, act){
		                	Ext.Msg.alert('提示', '删除失败，检查网络！', function() {
		                	});
							console.log('act:',act);
		                }
		            });
     
				}else{
				console.log('form is not ready!');
				
				}
    
    }
    
});
