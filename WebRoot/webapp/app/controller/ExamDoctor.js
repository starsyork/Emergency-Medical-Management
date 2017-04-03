Ext.define('Wj.controller.ExamDoctor', {
	extend: 'Ext.app.Controller',

	views: [
		'ExamDoctor',
		'Banner',
		'Content',
		'examdoctor.ExamNav',
		'examdoctor.ExamPtForm',
		'examdoctor.ExamPtInspection',
		'examdoctor.ExamNewPtWin',
		'examdoctor.ExamPhoto',
		'examdoctor.AddPhoto',
		'imgviewer.imageviewer',
		'examdoctor.imgdelviewer'
	],

	stores: [
		'examdoctor.ExamPatient',
		'examdoctor.ExamNewPatient',
		'examdoctor.ExamInspectMain',
		'examdoctor.ExamInspectDetail',
		'surgydoctor.SurgyInspectMain'
	],

	refs: [{
		selector: 'examdoctor > content',
		ref: 'content'
	}, {
		selector: 'examdoctor > container > examdocnav',
		ref: 'examdocnav'
	}, {
		selector: 'examdoctor > container > examptform',
		ref: 'examptform'
	},  {
		selector: 'examdoctor > content > examdocptinspection',
		ref: 'examdocptinspection'
	}, {
		selector: 'examdoctor > content > examdocptinspection > #main_grid',
		ref: 'examinsmaingrid'
	}, {
		selector: 'examdoctor > content > examdocptinspection > #detail_grid',
		ref: 'examinsdetailgrid'
	},   {
		selector: 'examnewptwin',
		ref: 'examnewptwin'
	},
	 {
		selector: 'examdoctor > content > examphoto',
		ref: 'examphoto'
	}, {
		selector: 'examdoctor > form',
		ref: 'examphotoform'
	},
	{
		selector: 'addphoto',
		ref: 'addphoto'
	},{
		selector: 'examdoctor > content > examphoto',
		ref: 'examptphoto'
	},{
		selector: 'examdoctor > content > examphoto > #ptphotogrid',//伤员影像数据
		ref: 'examptphotogrid'
	}
	
	
	],

	init: function(){

		this.control({

			'examdoctor': {

				afterrender: function(){

					this.initContentView();
					
					
//					var ty='http:\/\/10.32.41.86:8080\/WJ03\/upload\/1\/223\/IMG_8377.JPG';
//					console.log('ty:',ty);
//					var m = ty.replace('\/','/');
//					console.log('m:',m);
					this.getPhotoUrl();
					


					
					
				}
			},

			'examdoctor > content': {

				boxready: function(t, opt){

					console.log('------------loginData-----------------');
					console.log(Wj.tmp.loginData);
					var d = Wj.tmp.loginData;
					var cp = Wj.cp;

					if(d){

						cp.set('Wj-userName', d.userName);
						cp.set('Wj-roleStr', d.roleStr);
						cp.set('Wj-sector', d.sector);
						cp.set('Wj-docId', d.id);
						cp.set('Wj-secId', d.secId);

					} else {

						var d = {
							userName: cp.get('Wj-userName'),
							roleStr: cp.get('Wj-roleStr'),
							sector: cp.get('Wj-sector'),
							id: cp.get('Wj-docId')
						};

					}
					
					var infobar = Ext.getCmp('infobar');
					var date = Ext.Date.format(new Date(), 'Y-m-d, l');
					var info = ' - 欢迎你，' +
						d.userName + ' ' +
						d.roleStr + '！' + ' - ' +
						date;

					if(d && infobar){
						infobar.setText(info);
					}

					/////////////////

					// open patinet condition tab when first login to nurse station.
					this.getContent().setActiveTab(this.getExamdocptinspection());

				},

				tabchange: function(t, nt, ot, opt){
//
//					var sl = this.getExamdocnav().getSelectionModel().getSelection();
//					if(sl && sl[0]){
//						
//						var id = sl[0].data.id;
//						var st = nt.down('grid').getStore();
//
//						st.getProxy().extraParams = { id: id };
//						st.load({
//							scope: nt.down('grid'),
//							callback: function(r, o, suc){
//								if(suc && r && r.length){
//									this.getSelectionModel().select(0);
//								}
//							}
//						});
//
//						// test authority to edit current selected patient
//						this.testAuthority(nt);
//
//					}
					
				}

			},

			'examdoctor > container > examdocnav': {

				afterrender: function(){

					this.getStore('examdoctor.ExamPatient').getProxy().extraParams = {
						all: false
					};

					this.getStore('examdoctor.ExamPatient').load({
						scope: this,
						callback: function(r, o, suc){

							if(suc && r && r.length){
								this.getExamdocnav().getSelectionModel().select(0);
							}
						}
					});

					this.testExamNewPt();

				},

				selectionchange: function(m, r){

					if(r && r[0]){

						this.getExamptform().getForm().loadRecord(r[0]);

						if(r[0].data.id){

							var con = this.getContent();
							var activeTab = con.getActiveTab();
							if(activeTab)
								var gr = activeTab.down('grid');

							if(gr){

								gr.getStore().getProxy().extraParams = { id: r[0].data.id,all:Wj.all };
								gr.getStore().loadPage(1, {
									scope: gr,
									callback: function(r, o, suc){
										if(suc && r && r.length){
											gr.getSelectionModel().select(0);
										}
									}
								});
							}
							
							//------影像数据--------
							
							
							
//							if(activeTab){
//								var ph = Ext.ComponentQuery.query('#photoForm >#photoIDForm')[0];
//								console.log('id:',r[0].data.id);
//						
//								ph.setValue(r[0].data.id);
//								
//								//var Ext.ComponentQuery.query('#photoForm > #photoIDForm')[0];
//								
//							}
//							if(ph){
//
//								ph.getStore().getProxy().extraParams = { id: r[0].data.id };
//								ph.getStore().loadPage(1, {
//									scope: ph,
//									callback: function(r, o, suc){
//										if(suc && r && r.length){
//											ph.getSelectionModel().select(0);
//										}
//									}
//								});
//							}

							// test autority to edit current patient
							//this.testAuthority(activeTab);


						} else {

							//this.getStore('examdoctor.ExamPtAdvice').getProxy().extraParams = {};
				

						}
						if(r && r[0]){

							id = r[0].data.id,
								dg = this.getExaminsdetailgrid(),
								store = dg.getStore();

							console.log('in #main_grid, id:', id);
							
							store.getProxy().extraParams = {
								id: id,
								all:Wj.all
							};

							// adjust paging tool.
							dg.down('pagebar').moveFirst();				
						} else {

							this.getExaminsdetailgrid().getStore().removeAll();

						}
					}
				},

			},

			'examdoctor > content > examdocptinspection > #main_grid': {

				selectionchange: function(m, r){

					if(r && r[0]){

						//console.log(this);
						//console.log(this.getExaminsdetailgrid());

						id = r[0].data.id,
							dg = this.getExaminsdetailgrid(),
							store = dg.getStore();

						console.log('in #main_grid, id:', id);
						
						store.getProxy().extraParams = {
							id: id,
							all:Wj.all
						};

						// adjust paging tool.
						dg.down('pagebar').moveFirst();				
					} else {

						this.getExaminsdetailgrid().getStore().removeAll();

					}
				},				

			
			},

			'examdoctor > content > examdocptinspection > #main_grid': {
					
				edit: function(edit, e){
					Wj.util.editGridRecord(e, Wj.url.EditInspect);	
					var gr = Ext.getCmp("exam_ins_main_grid");
					var r = gr.getSelectionModel().getSelection();
					var id = r[0].data.id;
					console.log('r id:'+r[0].data.id);
                	//刷新 Grid-----start-----
					this.RefreshGrid(gr,id);
					//刷新 Grid-----end-----
				}
			
			},
			'examdoctor > content > examdocptinspection > #detail_grid': {

			
				edit: function(edit, e){
					Wj.util.editGridRecord(e, Wj.url.EditEdetail);
					
					var gr = Ext.getCmp("exam_ins_detail_grid");
					var r = gr.getSelectionModel().getSelection();
					var id = r[0].data.id;
					console.log('r id:'+r[0].data.id);
                	//刷新 Grid-----start-----
					this.RefreshGrid(gr,id);
					//刷新 Grid-----end-----
					
					
					
				}
			
			},
			'examnewptwin': {

				boxready: function(t, opt) {

					t.setPosition(window.innerWidth - 720, window.innerHeight - 330);
					t.expand();

				},

				beforecollapse: function(p, direction, animate, opt) {

					p.animate({
						to: {
							y: window.innerHeight - 50
						}
					});
				},

				expand: function(p, opt) {

					p.animate({
						to: {
							y: window.innerHeight - 330
						}
					});
				}
			},

			'examnewptwin > grid': {

				afterrender: function(t, opt) {

					t.getStore().load({
						scope: this,
						callback: function(r, op, suc) {
							if(r[0]){							
								t.up('panel').down('form').down('#Exambtn_confirm').setDisabled(false);
							} else {							
								t.up('panel').down('form').down('#Exambtn_confirm').setDisabled(true);								
							}
						}
					});
				}
			},
			//伤员影像数据  2016年5月17日15:53:12
						'examdoctor > content > examphoto': {
					afterrender: function(){
						var th = this.getExamdocnav().getSelectionModel().getSelection();
//						console.log('th.length:',th.length);
//						console.log('th  id:',th[0].data.id);
						if(th.length > 0){
							
						
					this.getStore('surgydoctor.SurgyInspectMain').getProxy().extraParams = {
						id: th[0].data.id,
						all: true
					};

					this.getStore('surgydoctor.SurgyInspectMain').load({
						scope: this,
						callback: function(r, o, suc){
							console.log('oooo:'+Ext.getCmp('examptphoto'));
							if(suc && r && r.length){
								
								this.getExamptphotogrid().getSelectionModel().select(0);
								//Ext.getCmp('examptphoto').getSelectionModel().select(0);
								
							}
						}
					});
				}

				}
			},
			'examdoctor > content > examphoto > #ptphotogrid': {

				selectionchange: function(m, r){

					if(r && r[0]){

						id = r[0].data.id,
						
						console.log('selectid:', id);
						
						    console.log('ID:',r[0].data.id);
							console.log('photodec:',r[0].data.illustration);
							
							//刷新 影像数据界面
							var photosrc = r[0].data.url;//影像图片 地址
							var photodec = r[0].data.illustration;//影像图片 描述
							console.log('photosrc:',r[0].data.url);
						    var oa = Ext.ComponentQuery.query('#examphotopanel')[0];
							var tempurl;
						    var tempsrc;

						    if(oa && photosrc)
						    {
						    	tempurl = photosrc;
						    }else tempurl = 'resource/img/NoPhotoDis.png';
						    				
						    
						    tempsrc = tempurl.split(";");
						    
						    Wj.TEMPSRC = tempsrc;
						    Wj.imgID = 0;//image 默认  显示第一张
						    
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
								oa.doLayout();
							//影像数据   影像描述界面
						   var dc = Ext.ComponentQuery.query('#examphotocontent')[0]; 
						   var tempdec;
						   if(photodec == ""){
						   		tempdec = '暂无描述';
						   }else{
						   		tempdec = photodec;
						   }
						   dc.setValue(tempdec);
			
						
						
						
						
					} else {

						//this.getSurgyinsdetailgrid().getStore().removeAll();

					}
				}
			},
			'addphoto':{
				afterrender: function(){
								var gr =  Ext.getCmp('exam_ins_main_grid');
								var r = gr.getSelectionModel().getSelection();
								console.log('r.length:'+r.length);
								if(r.length > 0)
								{	PhotoUrl = r[0].data.url;}
								else{
								
								PhotoUrl=null;
								}
								if(PhotoUrl == null){
									PhotoUrl = 'resource/img/NoPhoto.png';
								}
								console.log('PhotoUrl New :',PhotoUrl);
								
								var oa = Ext.ComponentQuery.query('#addphotoShow')[0];

	 							var tempurl;
							    var tempsrc;

							    if(oa && PhotoUrl)
							    {
							    	tempurl = PhotoUrl;
							    }else tempurl = 'resource/img/NoPhotoDis.png';
							    				
							    
							    tempsrc = tempurl.split(";");
							    
//							    Wj.TEMPSRC = tempsrc;
//							    Wj.imgID = 0;//image 默认  显示第一张
							    
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

				
				
				}
					
					
				}
				//------------------------
			

		});
	},

	showExamDocView: function(){

		console.log('-- Controller Doctor : showExamDocView --');

		var doc = Ext.widget('examdoctor');
		Ext.getCmp('viewport').removeAll(true);
		Ext.getCmp('viewport').add(doc);

	},

	initContentView: function(){


		this.initPtInspectionTab();
		this.initPtPhotoTab();
	

	},




	initPtInspectionTab: function(){
		
		var c = this.getContent();
		console.log('C:',c);
		var ins = Ext.widget('examdocptinspection');
		c.add(ins);
		
	},
	initPtPhotoTab: function(){
		
		var c = this.getContent();
		var ins = Ext.widget('examphoto');
		c.add(ins);
		
	},

	changeNavCheckBox: function(t, nv, ov, opt) {

		console.log(nv);
		console.log(ov);
		console.log(this);

		if (nv === true) {
			 Wj.all=true;
			this.getStore('examdoctor.ExamPatient').getProxy().extraParams = {
				all:  Wj.all
			}
		} else {
			 Wj.all=false;
			this.getStore('examdoctor.ExamPatient').getProxy().extraParams = {
				all:  Wj.all
			}
		}

		this.getStore('examdoctor.ExamPatient').load({
			scope: this,
			callback: function(r, o, suc){

				if(suc && r && r.length){
					this.getExamdocnav().getSelectionModel().select(0);
				}

			}
		});

	},

	
	
	
	getPtId: function(){
	
		var sl = this.getExamdocnav().getSelectionModel().getSelection();
		if(sl && sl[0]) {
			return sl[0].data.id;
		} else {
			return null;
		}
	
	},
//getPtIPhotoUrl
	getPtName: function(){

		var sl = this.getExamdocnav().getSelectionModel().getSelection();
		if(sl && sl[0]) {
			return sl[0].data.name;
		} else {
			return null;
		}
	
	},
	getPhotoUrl:function(){
		var s = this.getStore('examdoctor.ExamInspectMain').load();
		console.log('S:',s.getTotalCount());
			
	},


	downloadDoc: function(b){

		var sl = Wj.controller.ExamDoctor.getExamptcoursegrid().getSelectionModel().getSelection();
		if(sl && sl[0])
			window.open(sl[0].data.doc);

	},

	testAuthority: function(tab) {

		var cp = Wj.cp, d = Wj.tmp.loginData, t = tab, docId, form, grid;

		docId = this.getExamdocnav().getSelectionModel().getSelection()[0].data.docId;

		if (t !== null) {

			if (!d) {
				d = {
					id: cp.get('Wj-docId')
				};
			}

			grid = t.down('grid');
			form = t.down('form');

			if (d.id !== docId) {
				
				if (grid) {
					grid.down('toolbar').setDisabled(true);
				}
				
				if (form) {
					form.setDisabled(true);
				}

			} else {

				if (grid) {
					grid.down('toolbar').setDisabled(false);
				}
				
				if (form) {
					form.setDisabled(false);
				}				
			}

		}
	},

	testExamNewPt: function() {
		
		var url = Wj.url.testExamNewPt;

		Ext.Ajax.request({
			url: url,
			method: 'get',
			timeout: 15000,
			scope: Wj.controller.ExamDoctor,
			
			success: function(r, a){
				Wj.util.handleRequestSuccess(r, a, function(){

					Wj.util.t = window.setTimeout(Wj.controller.ExamDoctor.testExamNewPt, 15 * 1000);

					var res = arguments[0].results.isNewPt,
						controller = Wj.controller.ExamDoctor,
						win;

					if (res) {

						controller.showNewPtWin();
					
					} else {

						win  = controller.getExamnewptwin();

						if(win) {
							win.collapse();
							win.hide();
						}
					}

				});
			},

			failure: function(){

				Wj.util.t = window.setTimeout(Wj.controller.ExamDoctor.testExamNewPt, 15 * 1000);

				Wj.util.HandleRequestFailure();
			}
		});
	},

	showNewPtWin: function() {

		var win = this.getExamnewptwin();

		if(!win) {
			Ext.widget('examnewptwin').show();
		} else {
			win.show();
			win.expand();
			win.down('grid').getStore().load();
		}

	},
	
	handleConfirmExamNewPt: function(b) {

		var controller = Wj.controller.ExamDoctor,
			win = controller.getExamnewptwin();

		var url = Wj.url.ConfirmExamNewPt,
			controller = Wj.controller.ExamDoctor,
			navgrid = controller.getExamdocnav(),
			sl, win = controller.getExamnewptwin();
		
		Wj.util.pushData.call(this, url, null, function(){

			navgrid.getStore().load({
				scope: this,
				callback: function(r, o, suc) {

					if(suc && r && r.length) {

						sl = navgrid.getSelectionModel().getSelection();

						if(!sl || !sl[0]) {
							navgrid.getSelectionModel().select(0);
						}
						
						win.collapse();
						win.hide();
					}
				}
			});

		});
	},
		addPhoto: function(b){

		Ext.widget('addphoto').show();

	},
		getExamPhotoId: function(){
	
		var sl = this.getExaminsmaingrid().getSelectionModel().getSelection();
		if(sl && sl[0]) {
			return sl[0].data.id;
		} else {
			return null;
		}
	
	},
		getExamPhotoName: function(){
	
		var sl = this.getExaminsmaingrid().getSelectionModel().getSelection();
		if(sl && sl[0]) {
			return sl[0].data.content;//影像名称
		} else {
			return null;
		}
	
	},
	//用于删除影像数据
	getExamPhotoApplyId: function(){
	
		var sl = Ext.getCmp('examptphoto').getSelectionModel().getSelection();
		if(sl && sl[0]) {
			return sl[0].data.id;
		} else {
			return null;
		}
	
	},
	//用于 影响数据  图片的 切换 
	FormerImg: function(){
    	var me = this;
    	
    	if(Wj.TEMPSRC.length > 0)
    	{
    		if((Wj.imgID - 1) >= 0)
    		{
	    		Wj.imgID = Wj.imgID - 1;
	    		me.getImage().el.dom.src = Wj.TEMPSRC[Wj.imgID];
	    		console.log('Former img');
	    		console.log('NowID:'+ Wj.imgID);
	    		Ext.getCmp('del_photoUrlForm').setValue(Wj.TEMPSRC[Wj.imgID]);
    		
    		}else{
	    		Ext.MessageBox.show({
		    		title:"提示",
		    		msg:"已经是第一张图片！",
		    		modal:true,
		    		buttons:Ext.Msg.OK,
		    		icon: Ext.Msg.WARNING
	    		});
    		}
    	}
    },
    NextImg: function(){
//    	console.log(" Wj.TEMPSRC.length:"+ Wj.TEMPSRC.length);
    	var me = this;
    	if(Wj.TEMPSRC.length > 0)
    	{
    		if((Wj.imgID + 1) < Wj.TEMPSRC.length)
    		{
	    		Wj.imgID = Wj.imgID + 1;
	    		me.getImage().el.dom.src = Wj.TEMPSRC[Wj.imgID];
	    		console.log('next img');
//	    		console.log('NowID:'+ Wj.imgID);
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
    },
// 	DeleteImg: function(){
//    	var me = this;
//    	console.log('delUrl:'+Wj.TEMPSRC[Wj.imgID]);
//    	var delurl = Wj.TEMPSRC[Wj.imgID];
//    	var PhotoUrl;
//		if(delurl != null){
//			
//			 
//			
//		}
//    	
//    },
    ShowDeleteImg:function()
    {
    	Ext.widget('examimgdelviewer').show();
    },
    ShowImg:function()
    {
    	Ext.widget('surgyimageviewer').show();
    },
    RefreshGrid:function(gr,id)
    {
    	console.log('gr:'+gr);
    	var pid= this.getPtId();
    	console.log('refresh grid id:'+pid);	
    	console.log('refresh grid all:'+Wj.all);	
    	var pid= this.getPtId();
		if(gr){
				console.log('reload  !');
				
			gr.getStore().getProxy().extraParams = { id: pid ,all:Wj.all};
			console.log('tempid:'+id);
		
			//gr.getStore().load();
			gr.getStore().loadPage(1, {
				scope: gr,
				callback: function(r, o, suc){
					console.log('r  :'+r);
					console.log('suc  :'+suc);
					if(suc && r && r.length){
						gr.getSelectionModel().select(0);
						console.log('reload success!');
					}
				}
			});
		}
    	
    },
  //检索功能
    SearchPt:function()
    {
    var gr = Ext.getCmp('id_examNavGrid');
    console.log('gr:'+gr);
    
		gr.getStore().load({
		scope: this,
		callback: function(r, o, suc){
			if(suc && r && r.length){
				var searchCon = Ext.getCmp('id_examSearch').getValue();
				console.log('searchCon:'+searchCon);
				var selectItem = 0;
				
				if(searchCon !== null){
					console.log('r.length:'+r.length);
					for(var i = 0;i<r.length;i++)
					{
						console.log('ID:'+i+' rfid:'+r[i].data.rfid+' name:'+r[i].data.name);
						//检索姓名 或者 rfid 床位
						if((r[i].data.rfid == searchCon )||(r[i].data.name == searchCon ))
						//||(r[i].data.bedNum == searchCon )
						{
							selectItem = i;
							console.log('selectItem:'+selectItem);

							gr.getSelectionModel().select(selectItem);
							break;
						}
					}					
				}
				
			}
		}
	});
    
    }
    //----------------

})