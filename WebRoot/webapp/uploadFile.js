Ext.onReady(function() {  
  
    var fileForm = new Ext.form.FormPanel({  
        title : "",  
        renderTo : "fileUpload",  
        fileUpload : true,  
        layout : "form",  
        id : "fileUploadForm",  
        items : [{  
                    id : 'upload',  
                    name : 'upload',  
                    inputType : "file",  
                    fieldLabel : '上传图片',  
                    xtype : 'textfield',  
                    anchor : '40%'  
  
                }, {  
                      
                    xtype : 'box',  
                    id : 'browseImage',  
                    fieldLabel : "预览图片",  
                    autoEl : {  
                        width : 300,  
                        height : 350,  
                        tag : 'img',  
                        // type : 'image',  
                        src : Ext.BLANK_IMAGE_URL,  
                        style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',  
                        complete : 'off',  
                        id : 'imageBrowse'  
                    }  
  
                }],  
                  
                //form事件  
        listeners : {  
            'render' : function(f) {  
                //  
                this.form.findField('upload').on('render', function() {  
                    //通過change事件,图片也动态跟踪选择的图片变化  
                    Ext.get('upload').on('change',  
                            function(field, newValue, oldValue) {  
  
                                //得到选择的图片路径  
                                var url = 'file://'  
                                        + Ext.get('upload').dom.value;  
                                          
                            //  alert("url = " + url);  
                                //是否是规定的图片类型  
                                if (img_reg.test(url)) {  
  
                                    if (Ext.isIE) {  
                                        var image = Ext.get('imageBrowse').dom;  
                                        image.src = Ext.BLANK_IMAGE_URL;// 覆盖原来的图片  
                                        image.filters  
                                                .item("DXImageTransform.Microsoft.AlphaImageLoader").src = url;  
  
                                    }// 支持FF  
                                    else {  
                                        Ext.get('imageBrowse').dom.src = Ext  
                                                .get('upload').dom.files  
                                                .item(0).getAsDataURL()  
                                    }  
                                }  
                            }, this);  
                }, this);  
            }  
        },  
        buttons : [{  
                    text : "提交",  
                    name : "submit",  
                    handler : submit  
                }]  
    });  
  
    // 上传图片类型  
    var img_reg = /\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([gG][iI][fF]){1}$|\.([pP][nN][gG]){1}$|\.([bB][mM][pP]){1}$/  
  
});  
  
//上傳圖片到服务器，  
function submit() {  
    Ext.getCmp("fileUploadForm").getForm().submit({  
  
                url : "http://127.0.0.1:8080/WJ02/uploadAction.action",  
                method : "POST",  
                success : function(form, action) {  
                    alert("success");  
                },  
                failure : function(form, action) {  
                    alert("failure");  
                }  
            });  
}  
