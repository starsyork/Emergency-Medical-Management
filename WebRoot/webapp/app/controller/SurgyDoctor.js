Ext.define('Wj.controller.SurgyDoctor', {
	extend: 'Ext.app.Controller',

	views: [
		'SurgyDoctor',
		'Banner',
		'Content',
		'surgydoctor.SurgyNav',
		'surgydoctor.SurgyPtForm',
		'surgydoctor.SurgyPtInspectData',
		'surgydoctor.SurgyNewPtWin',
		'surgydoctor.SurgyPhoto',
		'surgydoctor.SurgyPrepare',
		'surgydoctor.SurgyPtAdvice',
		'surgydoctor.SurgyAddPtAdvice',
		'surgydoctor.SurgyAddWardRecord',
		'surgydoctor.SurgyPtCourse',
		'surgydoctor.SurgyPtCondition',
		'surgydoctor.SurgyPtAdviceLook',
		'imgviewer.imageviewer'
	],

	stores: [
		'surgydoctor.SurgyPatient',
		'surgydoctor.SurgyNewPatient',
		'surgydoctor.SurgyInspectMain',
		'surgydoctor.SurgyInspectDetail',
		'surgydoctor.SurgyPrepareMain',
		'surgydoctor.SurgyPrepareDetail',
		'surgydoctor.SurgyPtAdvice',
		'surgydoctor.SurgyAdvExecRec',
		'surgydoctor.SurgyAdvContents',
		'surgydoctor.SurgyPhotoData',
		'surgydoctor.SurgyPtCourse',
		'surgydoctor.SurgyPtCondition',
		'surgydoctor.SurgyPtAdviceLook'
	],

	refs: [{
		selector: 'surgydoctor > content',
		ref: 'content'
	}, {
		selector: 'surgydoctor > container > surgydocnav',
		ref: 'surgydocnav'
	}, {
		selector: 'surgydoctor > container > surgyptform',
		ref: 'surgyptform'
	},  {
		selector: 'surgydoctor > content > surgyptinspectdata',
		ref: 'surgyptinspectdata'
	},
//	{
//		selector: 'surgydoctor > content > surgyptinspectdata > #main_grid',
//		ref: 'surgyinsmaingrid'
//	},
	 {
		selector: 'surgydoctor > content > surgyptinspectdata > #detail_grid',
		ref: 'surgyinsdetailgrid'
	},   {
		selector: 'surgynewptwin',
		ref: 'surgynewptwin'
	},
	 {
		selector: 'surgydoctor > content > surgyphoto',
		ref: 'surgyphoto'
	},
	  {
		selector: 'surgydoctor > content > surgyprepare',
		ref: 'surgyprepare'
	}, 
	 {
		selector: 'surgydoctor > content > surgyprepare > #main_grid',
		ref: 'surgypreparemaingrid'
	}, {
		selector: 'surgydoctor > content > surgyprepare > #detail_grid',
		ref: 'surgypreparedetailgrid'
	},
	 {
		selector: 'surgydoctor > content > surgyptadvice',
		ref: 'surgyptadv'
	}, {
		selector: 'surgydoctor > content > surgyptadvice > grid',
		ref: 'surgyptadvgrid'
	}, {
		selector: 'surgydoctor > content > surgyptadvice > form',
		ref: 'surgyptadvform'
	},{
		selector: 'surgydoctor > content > surgyptadvice > form > fieldset > grid',
		ref: 'surgyadvexecrecgrid'
	},{
		selector: 'surgyaddptadv > form > #add_content',
		ref: 'surgyaddptadv'
	
	},{
		selector: 'surgyaddwardrecord',
		ref: 'surgyaddwardrecord'
	
	},{
		selector: 'surgyaddwardrecord > form',
		ref: 'surgyaddwardrecord'
	
	},
//	{
//		
//		selector: 'surgyaddwardrecord > form',
//		ref: 'surgyaddwardrecordform'
//	},
	{
		selector: 'surgydoctor > content > surgyphoto > #ptphotogrid',//伤员影像数据
		ref: 'surgyptphotogrid'
	},{
		selector: 'surgydoctor > content > surgyptcourse > grid',
		ref: 'surgyptcoursegrid'
	},{
		selector: 'surgydoctor > content > surgyptadvicelook > grid',
		ref: 'surgyptadvicelook'
	},{
		selector: 'surgydoctor > content > surgyptcondition > grid',
		ref: 'surgyptcondition'
	},
	{
		selector:'surgydoctor > content > surgyptcourse > grid',
		ref: 'surgyptcourse'
	},
	{
		selector:'surgydoctor > content > surgyimageviewer',
		ref: 'surgyimageviewer'
	}
		
	],

	init: function(){

		this.control({

			'surgydoctor': {

				afterrender: function(){

					this.initContentView();
				}
			},

			'surgydoctor > content': {

				boxready: function(t, opt){

					console.log('------------loginData-----------------');
//					console.log(Wj.tmp.loginData);
//					var d = Wj.tmp.loginData;
//					var cp = Wj.cp;
//
//					if(d){
//
//						cp.set('Wj-userName', d.userName);
//						cp.set('Wj-roleStr', d.roleStr);
//						cp.set('Wj-sector', d.sector);
//						cp.set('Wj-docId', d.id);
//						cp.set('Wj-secId', d.secId);
//
//					} else {
//
//						var d = {
//							userName: cp.get('Wj-userName'),
//							roleStr: cp.get('Wj-roleStr'),
//							sector: cp.get('Wj-sector'),
//							id: cp.get('Wj-docId')
//						};
//
//					}
//					
//					var infobar = Ext.getCmp('infobar');
//					var date = Ext.Date.format(new Date(), 'Y-m-d, l');
//					var info = ' - 当前病区：' +
//						d.sector + ' - 欢迎你，' +
//						d.userName + ' ' +
//						d.roleStr + '！' + ' - ' +
//						date;
//
//					if(d && infobar){
//						infobar.setText(info);
//					}

					/////////////////

					// open patinet condition tab when first login to nurse station.
					this.getContent().setActiveTab(this.getSurgyprepare());
					this.getContent().setActiveTab(this.getSurgyptinspectdata());

				},

				tabchange: function(t, nt, ot, opt){

					var sl = this.getSurgydocnav().getSelectionModel().getSelection();
					if(sl && sl[0]){
						
						var id = sl[0].data.id;
						var st = nt.down('grid').getStore();

						st.getProxy().extraParams = { id: id ,all:Wj.Surgyall};
						st.load({
							scope: nt.down('grid'),
							callback: function(r, o, suc){
								if(suc && r && r.length){
									this.getSelectionModel().select(0);
								}
							}
						});

						// test authority to edit current selected patient
						//this.testAuthority(nt);

					}
					
				}

			},

			'surgydoctor > container > surgydocnav': {

				afterrender: function(){

					this.getStore('surgydoctor.SurgyPatient').getProxy().extraParams = {
						all:Wj.surgyall
					};

					this.getStore('surgydoctor.SurgyPatient').load({
						scope: this,
						callback: function(r, o, suc){

							if(suc && r && r.length){
								this.getSurgydocnav().getSelectionModel().select(0);
							}
						}
					});

					this.testSurgyNewPt();

				},

				selectionchange: function(m, r){

					if(r && r[0]){

						this.getSurgyptform().getForm().loadRecord(r[0]);

						if(r[0].data.id){

							var con = this.getContent();
							var activeTab = con.getActiveTab();
							//console.log('activeTab:',activeTab);
							if(activeTab)
								var gr = activeTab.down('grid');

							if(gr){

								gr.getStore().getProxy().extraParams = { id: r[0].data.id ,all:Wj.surgyall};
								gr.getStore().loadPage(1, {
									scope: gr,
									callback: function(r, o, suc){
										if(suc && r && r.length){
											gr.getSelectionModel().select(0);
										}
									}
								});
							}

						   
								
								
								
								
							// test autority to edit current patient
							//this.testAuthority(activeTab);
						} else {
							//this.getStore('examdoctor.ExamPtAdvice').getProxy().extraParams = {};
						}
					}
				}
			},
			//影像数据 表格
			'surgydoctor > content > surgyphoto > #ptphotogrid': {
					afterrender: function(){
						var th = this.getSurgydocnav().getSelectionModel().getSelection();
						if(th.length > 0){
						}
					this.getStore('surgydoctor.SurgyInspectMain').getProxy().extraParams = {
						all: false
					};

					this.getStore('surgydoctor.SurgyInspectMain').load({
						scope: this,
						callback: function(r, o, suc){

							if(suc && r && r.length){
								this.getSurgyptphotogrid().getSelectionModel().select(0);
							}
						}
					});

				},

				selectionchange: function(m, r){

					if(r && r[0]){

						id = r[0].data.id,
						//	dg = this.getSurgyinsdetailgrid(),
						//	store = dg.getStore();

						console.log('selectid:', id);
						
						//store.getProxy().extraParams = {
						//	id: id
						//};

						// adjust paging tool.
						//dg.down('pagebar').moveFirst();
						
						
						    console.log('ID:',r[0].data.id);
							console.log('photodec:',r[0].data.photodec);
							
							//刷新 影像数据界面
							var photosrc = r[0].data.url;//影像图片 地址
							var photodec = r[0].data.photodec;//影像图片 描述
							console.log('photosrc:',r[0].data.url);
						    var oa = Ext.ComponentQuery.query('#surgyphotopanel')[0];
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
						   var dc = Ext.ComponentQuery.query('#surgyphotocontent')[0]; 
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
			
			
			//检查数据
			'surgydoctor > content > surgyptinspectdata > #main_grid': {

				selectionchange: function(m, r){

					if(r && r[0]){

						//console.log(this);
						//console.log(this.getSurgyinsdetailgrid());

						id = r[0].data.id,
							dg = this.getSurgyinsdetailgrid(),
							store = dg.getStore();

						console.log('in #main_grid, id:', id);
						
						store.getProxy().extraParams = {
							id: id,
							all:Wj.surgyall
						};

						// adjust paging tool.
						dg.down('pagebar').moveFirst();				
					} else {

						this.getSurgyinsdetailgrid().getStore().removeAll();

					}
				}
			},
			//-----------------术前准备-------------------------
			'surgydoctor > content > surgyprepare > #main_grid': {
				afterrender: function(){
					
					console.log('afdfasfasdfa');
					this.getStore('surgydoctor.SurgyPrepareMain').getProxy().extraParams = {
						all:Wj.surgyall
				
					};
					this.getStore('surgydoctor.SurgyPrepareMain').load({
						scope: this,
						callback: function(r, o, suc){
//							console.log('r:',r);
//							console.log('o:',o);
//							console.log('suc:',suc);
							
							if(suc && r && r.length){
								this.getSurgypreparemaingrid().getSelectionModel().select(0);
								//console.log('121342154asdfa');
								
							}
						}
					});
						
					//this.testNewPt();
				},

				selectionchange: function(m, r){

					if(r && r[0]){

	//					console.log(this);
	//					console.log(this.getSurgypreparedetailgrid());
						console.log("r【0】",r[0]);
						id = r[0].data.applyId,
							dg = this.getSurgypreparedetailgrid(),
							store = dg.getStore();

						console.log('in #main_grid, id:', id);
						
						store.getProxy().extraParams = {
							id: id,
							all: Wj.surgyall
						};

						// adjust paging tool.
						dg.down('pagebar').moveFirst();				
					} else {

						this.getSurgypreparedetailgrid().getStore().removeAll();

					}
				}
			},
			//术前准备  新增查房记录
			'surgyaddwardrecord': {
				afterrender: function(c){
					c.down('#accept').on('click', this.confirmAddWardRecord);
					console.log('hit accept');
				}
			},
			'surgydoctor > content > surgyprepare > #detail_grid': {
				afterrender: function(){

					this.getStore('surgydoctor.SurgyPrepareDetail').getProxy().extraParams = {
						all: false
					};

					this.getStore('surgydoctor.SurgyPrepareDetail').load({
						scope: this,
						callback: function(r, o, suc){
							console.log('r:',r);
							console.log('o:',o);
							console.log('suc:',suc);
							if(suc && r && r.length){
								this.getSurgypreparedetailgrid().getSelectionModel().select(0);
							}
						}
					});

					//this.testNewPt();

				}
			},

			//-------------新伤员-----------------------

			'surgynewptwin': {

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

			'surgynewptwin > grid': {

				afterrender: function(t, opt) {

					t.getStore().load({
						scope: this,
						callback: function(r, op, suc) {
							if(r[0]){
								t.up('panel').down('form').down('#btn_confirm').setDisabled(false);
							} else {
								t.up('panel').down('form').down('#btn_confirm').setDisabled(true);
							}
						}
					});
				}
			},
			//----术后医嘱--------------
			
			'surgydoctor > content > surgyptadvice > grid': {
			afterrender: function(){

					this.getStore('surgydoctor.SurgyPtAdvice').getProxy().extraParams = {
						all: false
					};

					this.getStore('surgydoctor.SurgyPtAdvice').load({
						scope: this,
						callback: function(r, o, suc){

							if(suc && r && r.length){
								this.getSurgyptadvgrid().getSelectionModel().select(0);
							}
						}
					})
			},

				selectionchange: function(m, r){

					if(r && r[0]){

						this.getSurgyptadvgrid().down('toolbar').down('#remove').setDisabled(false);

						this.getSurgyptadvform().getForm().loadRecord(r[0]);

						var recstore = this.getSurgyadvexecrecgrid().getStore();
						recstore.getProxy().extraParams.id = r[0].data.id;
						console.log('advice id: ' + r[0].data.id);
						recstore.load();


						var st = r[0].data.state;
						var tp = r[0].data.type;
						var fds = this.getSurgyptadvform().down('fieldset').query('field');
						
						if(st == '已执行' || st == '已停止'){

							for(var f in fds){
								fds[f].setReadOnly(true);
							}

							this.getSurgyptadvform().down('#f_endTime').setDisabled(true);
							
						} else {
							
							for(var f in fds){
								if(fds[f].name == 'id' || fds[f].name == 'type' || fds[f].name == 'state')
									fds[f].setReadOnly(true);
								else
									fds[f].setReadOnly(false);
							}

							if(st == '未执行'){		// suggests that the advice type is for short term
								this.getSurgyptadvform().down('#f_endTime').setDisabled(true);
							} else {	// then for long term but the advice has been stopped
								this.getSurgyptadvform().down('#f_endTime').setDisabled(false);
							}
						}

						if(tp == '长期医嘱'){
							this.getSurgyptadvform().down('#stop_exec').setDisabled(false);
						} else {
							this.getSurgyptadvform().down('#stop_exec').setDisabled(true);
						}


						for(var f in fds){
							if(fds[f].readOnly == true)
								fds[f].setFieldStyle(Wj.consts.css_readOnly);
							else
								fds[f].setFieldStyle(Wj.consts.css_notReadOnly);
						}

					} else
						this.getSurgyptadvgrid().down('toolbar').down('#remove').setDisabled(true);
				}
			},
			//术后医嘱  新增术后医嘱
			'surgyaddwardrecord': {
				afterrender: function(c){
					c.down('#accept').on('click', this.confirmAddAdvice);
					console.log('hit accept');
				}
			},
			'surgyaddptadv > form > #add_content': {


				keyup: function(t, e, opt) {

					var key = String.fromCharCode(e.button + 1);
					console.log('surgyaddptadv:',e.button + 1);

					var type = t.up('form').getForm().findField('type2').value,
						val = t.getValue(),
						ts = Wj.consts.adviceType,
						store = t.getStore();

					store.getProxy().extraParams = {
						type2: type,
						term: val
					}

					console.log('store.getProxy().extraParams', store.getProxy().extraParams);


				},

				blur: function(t, eventObj, opts) {

					var url = Wj.url.DoseUnit,
						par = {
							type2: t.up('form').getForm().findField('type2').getValue(),
							content: t.getValue()
						};

					console.log(par);

					Wj.util.pullData.call(t, url, par, function(res) {

						if (res.success && res.results.unit) {
							this.up('form').getForm().findField('dose').setValue(res.results.unit);
						}

					});

				}
			},
	
			'surgydoctor > content > surgyptadvice > form > fieldset > #f_content': {


				keyup: function(t, e, opt) {

					var key = String.fromCharCode(e.button + 1);
					console.log('surgy ptadvice:',e.button + 1);

					var type = t.up('form').getForm().findField('type2').value,
						val = t.getValue(),
						ts = Wj.consts.adviceType,
						store = t.getStore();

					store.getProxy().extraParams = {
						type2: type,
						term: val
					}

					console.log('store.getProxy().extraParams', store.getProxy().extraParams);


				},

				blur: function(t, eventObj, opts) {

					var url = Wj.url.DoseUnit,
						par = {
							type2: t.up('form').getForm().findField('type2').getValue(),
							content: t.getValue()
						};

					console.log(par);

					Wj.util.pullData.call(t, url, par, function(res) {

						if (res.success && res.results.unit) {
							this.up('form').getForm().findField('dose').setValue(res.results.unit);
						}

					});

				}
			},
			//-------------术前准备  新增手术药品-----------
			'surgyaddwardrecord > form > fieldset > #f_content': {
				keyup: function(t, e, opt) {

					var key = String.fromCharCode(e.button + 1);
					console.log( 'butn:',e.button + 1);


					var type = t.up('form').getForm().findField('type2').value,
						val = t.getValue(),
						ts = Wj.consts.surgyType,
						
						store = t.getStore();

					store.getProxy().extraParams = {
						type2: type,
						term: val
					}

					console.log('store.getProxy().extraParams', store.getProxy().extraParams);


				},
				blur: function(t, eventObj, opts) {

					var url = Wj.url.DoseUnit,
						par = {
							type2: t.up('form').getForm().findField('type2').getValue(),
							content: t.getValue()
						};

					console.log(par);

					Wj.util.pullData.call(t, url, par, function(res) {

						if (res.success && res.results.unit) {
							this.up('form').getForm().findField('dose').setValue(res.results.unit);
						}

					});

				}
			},
			//医嘱查看
			'surgydoctor > content > surgyptadvicelook > grid':{
			afterrender: function(){

					this.getStore('surgydoctor.SurgyPtAdviceLook').getProxy().extraParams = {
						all: false
					};

					this.getStore('surgydoctor.SurgyPtAdviceLook').load({
						scope: this,
						callback: function(r, o, suc){

							if(suc && r && r.length){
								this.getSurgyptadvicelook().getSelectionModel().select(0);
							}
						}
					})
					
					console.log('Init ptadvice ');
			}
			},
						//病情查看
			'surgydoctor > content > surgyptcondition > grid':{
			afterrender: function(){

					this.getStore('surgydoctor.SurgyPtCondition').getProxy().extraParams = {
						all: false
					};

					this.getStore('surgydoctor.SurgyPtCondition').load({
						scope: this,
						callback: function(r, o, suc){

							if(suc && r && r.length){
								this.getSurgyptcondition().getSelectionModel().select(0);
							}
						}
					})
					
					//console.log('Init ptcondition ');
			}
			},
			//病程查看
			'surgydoctor > content > surgyptcourse > grid':{
			afterrender: function(){
			var th = this.getSurgydocnav().getSelectionModel().getSelection();
			console.log('th:',th[0].data.id);
			if(th.length >0 ){
			
					this.getStore('surgydoctor.SurgyPtCourse').getProxy().extraParams = {
						id:th[0].data.id
					};

					this.getStore('surgydoctor.SurgyPtCourse').load({
						scope: this,
						callback: function(r, o, suc){

							if(suc && r && r.length){
								this.getSurgyptcourse().getSelectionModel().select(0);
							}
						}
					})
			}
					
			}
			}
				

			
			
			

		});
	},

	showSurgyDocView: function(){

		console.log('-- Controller Doctor : showSurgyDocView --');

		var doc = Ext.widget('surgydoctor');
		Ext.getCmp('viewport').removeAll(true);
		Ext.getCmp('viewport').add(doc);

	},

	initContentView: function(){


		this.initPtInspectionTab();
		this.initPtPhotoTab();
		this.initSurgyPrepareTab();
		this.initSurgyPtAdviceTab();//术后医嘱
		this.initSurgyPtCourseTab();//病程查看
		this.initSurgyPtConditionTab();//病情查看
		this.initSurgyPtAdviceLookTab();//医嘱查看，数据来自于 主治医师
		//this.initSurgyPhotoViewerTab();
	

	},




	initPtInspectionTab: function(){
		
		var c = this.getContent();
		var ins = Ext.widget('surgyptinspectdata');
		c.add(ins);
		
	},
	initPtPhotoTab: function(){
		
		var c = this.getContent();
		var ins = Ext.widget('surgyphoto');
		c.add(ins);
		
	},
		initSurgyPrepareTab: function(){
		
		var c = this.getContent();
		var ins = Ext.widget('surgyprepare');
		c.add(ins);
		
	},
		initSurgyPtAdviceTab: function(){
		
		var c = this.getContent();
		var adv = Ext.widget('surgyptadvice');
		c.add(adv);		
	},
		initSurgyPtCourseTab: function(){
		
		var c = this.getContent();
		var adv = Ext.widget('surgyptcourse');
		c.add(adv);	
	},
		initSurgyPtConditionTab: function(){
		
		var c = this.getContent();
		var adv = Ext.widget('surgyptcondition');
		c.add(adv);
	},
		initSurgyPtAdviceLookTab: function(){
		
		var c = this.getContent();
		var adv = Ext.widget('surgyptadvicelook');
		c.add(adv);
	},
//	initSurgyPhotoViewerTab: function(){
//		
//		var c = this.getContent();
//		var adv = Ext.widget('surgyimageviewer');
//		c.add(adv);
//	},
	changeSurgyNavCheckBox: function(t, nv, ov, opt) {
		
		 id= this.getPtId();
		if (nv === true) {
			Wj.surgyall=true;
			this.getStore('surgydoctor.SurgyPatient').getProxy().extraParams = {
				id:id,
				all: Wj.surgyall
			}
		} else {
			Wj.surgyall=false;
			this.getStore('surgydoctor.SurgyPatient').getProxy().extraParams = {
				id:id,
				all: Wj.surgyall
			}
		}

		this.getStore('surgydoctor.SurgyPatient').load({
			scope: this,
			callback: function(r, o, suc){

				if(suc && r && r.length){
					this.getSurgydocnav().getSelectionModel().select(0);
				}

			}
		});

	},
	
	getPtId: function(){
	
		var sl = this.getSurgydocnav().getSelectionModel().getSelection();
		if(sl && sl[0]) {
			return sl[0].data.id;
		} else {
			return null;
		}
	
	},

	getPtName: function(){

		var sl = this.getSurgydocnav().getSelectionModel().getSelection();
		if(sl && sl[0]) {
			return sl[0].data.name;
		} else {
			return null;
		}
	
	},


	downloadDoc: function(b){

		var sl = Wj.controller.SurgyDoctor.getSurgyptcoursegrid().getSelectionModel().getSelection();
		if(sl && sl[0] && sl[0].data.doc)
		{	
			window.open(sl[0].data.doc);
		}else{
			Ext.Msg.alert('下载失败！', '未选择记录或无此存档', function(){
				
			}, Wj.controller.SurgyDoctor);
		}
		

	},

	testAuthority: function(tab) {

		var cp = Wj.cp, d = Wj.tmp.loginData, t = tab, docId, form, grid;

		docId = this.getSurgydocnav().getSelectionModel().getSelection()[0].data.docId;

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

	testSurgyNewPt: function() {
		
		var url = Wj.url.testSurgyNewPt;

		Ext.Ajax.request({
			url: url,
			method: 'get',
			timeout: 15000,
			scope: Wj.controller.SurgyDoctor,
			
			success: function(r, a){
				Wj.util.handleRequestSuccess(r, a, function(){

					Wj.util.t = window.setTimeout(Wj.controller.SurgyDoctor.testSurgyNewPt, 15 * 1000);

					var res = arguments[0].results.isNewPt,
						controller = Wj.controller.SurgyDoctor,
						win;

					if (res) {

						controller.showNewPtWin();
					
					} else {

						win  = controller.getSurgynewptwin();

						if(win) {
							win.collapse();
							win.hide();
						}
					}

				});
			},

			failure: function(){

				Wj.util.t = window.setTimeout(Wj.controller.SurgyDoctor.testSurgyNewPt, 15 * 1000);

				Wj.util.HandleRequestFailure();
			}
		});
	},

	showNewPtWin: function() {

		var win = this.getSurgynewptwin();

		if(!win) {
			Ext.widget('surgynewptwin').show();
		} else {
			win.show();
			win.expand();
			win.down('grid').getStore().load();
		}

	},
	
	handleConfirmNewPt: function(b) {

		var controller = Wj.controller.SurgyDoctor,
			win = controller.getSurgynewptwin();

		var url = Wj.url.ConfirmSurgyNewPt,
			controller = Wj.controller.SurgyDoctor,
			navgrid = controller.getSurgydocnav(),
			sl, win = controller.getSurgynewptwin();
		
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
	//------------------术前准备-----------------------
		addWardRecord: function(b){

		Ext.widget('surgyaddwardrecord').show();

	},
	//----------术前准备------确认增加记录---------------
	confirmAddWardRecord: function(b){

		Wj.util.confirmEditForm(b.up('window').down('form').getForm(), Wj.url.AddWardRecord, function(){	
			
			Ext.Msg.alert('提示', '添加成功！', function(){
				this.getSurgyaddwardrecord().close();
				this.getSurgypreparedetailgrid().getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getSurgypreparedetailgrid().getSelectionModel().select(0);
						}
					}
				});
			}, Wj.controller.SurgyDoctor);
		})

	},
	//-------术前准备--------删除记录----------------------
		removeWardRecord: function(b){


			
			console.log('-- removeSector --');
			var g = b.up('toolbar').up('grid');
			var sl = g.getSelectionModel().getSelection();

			if(sl && sl[0]){

				var url = Wj.url.RmvWardRecord;
				var par = { id: sl[0].data.id };
					
			Ext.Msg.confirm('提示', '确定删除这个分区吗？', function(btn){

				if(btn == 'yes'){
			
					if(sl.length>0){

						Wj.util.removeGridRecord(b.up('toolbar').up('grid'), Wj.url.RmvWardRecord, function(){
							Ext.Msg.alert('提示', '删除成功！', function(){
								this.getSurgypreparedetailgrid().down('pagebar').moveFirst();
							}, Wj.controller.SurgyDoctor);
						});
					}else{
						Wj.util.procFailed();
					
					}
				}},this);
			
			}

		
			
		
			
	
		
			
	
	
	},
	
	confirmSurgy: function(b){

		var g = b.up('toolbar').up('grid');
		var sl = g.getSelectionModel().getSelection();
		console.log("sl"+sl);
	//	var gr = Ext.getCmp("main_grid").getForm();
		//var r = gr.getSelectionModel().getSelection();
		var id = sl[0].data.id;
		console.log('r id:'+id);
		var param = {
				id:id,
				all:Wj.surgyall
		};
		var url = Wj.url.confirmSurgy;

		Wj.util.pushData.call(this, url, param, function(){
			Ext.Msg.alert('提示', '执行成功！', function(){
				this.getStore('surgydoctor.SurgyPrepareMain').load({
					scope: this,
					callback: function(){
						this.getSurgypreparedetailgrid().getSelectionModel().select(0);											
					}
				});
			}, Wj.controller.ExamDoctor);
		});

	},
	
	
	//--------------术前准备------获取手术名称---------
		getSurgyName: function(){

		var sl = this.getSurgypreparemaingrid().getSelectionModel().getSelection();
		if(sl && sl[0]) {
			return sl[0].data.content;
		} else {
			return null;
		}
	
	},
	//--------------术前准备------获取手术申请号---------
		getSurgyId: function(){

		var sl = this.getSurgypreparemaingrid().getSelectionModel().getSelection();
		if(sl && sl[0]) {
			return sl[0].data.applyId;
		} else {
			return null;
		}
	
	},
	//--------------术后医嘱---------------------
		addAdvice: function(b){

		Ext.widget('surgyaddptadv').show();

	},

	confirmAddAdvice: function(b){

		Wj.util.confirmEditForm(b.up('window').down('form').getForm(), Wj.url.SurgyAddPtAdvice, function(){	
			Ext.Msg.alert('提示', '添加成功！', function(){
				this.getSurgyaddptadv().close();
				this.getSurgyptadvgrid().getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getSurgyptadvgrid().getSelectionModel().select(0);
						}
					}
				});
			}, Wj.controller.SurgyDoctor);
		})

	},

	editAdvice: function(b){

		var fp = b.up('form'), i = fp.getRecord().index;
		Wj.tmp.i = i;

		Wj.util.confirmEditOrAddForm('edit', fp.getForm(), Wj.url.SurgyEditDocPtAdvice, function(){
			Ext.Msg.alert('提示', '更新成功！', function(){
				this.getSurgyptadvgrid().getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getSurgyptadvgrid().getSelectionModel().select(Wj.tmp.i);
						}
						Wj.tmp.i = undefined;
					}
				});
			}, Wj.controller.SurgyDoctor);
		});

	},

	removeAdvice: function(b){

		
		console.log('-- removeSector --');
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.SurgyRmvPtAdvice;
			var par = { id: sl[0].data.id };
				
		Ext.Msg.confirm('提示', '确定删除这条医嘱吗？', function(btn){

			if(btn == 'yes'){
		

				Wj.util.removeGridRecord(b.up('toolbar').up('grid'), Wj.url.SurgyRmvPtAdvice, function(){
					Ext.Msg.alert('提示', '删除成功！', function(){
						this.getSurgyptadvgrid().down('pagebar').moveFirst();
					}, Wj.controller.SurgyDoctor);
				});
				}},this);
		
		}

	

	},

	stopAdvice: function(b){

		var fp = b.up('form'), r = fp.getRecord(), i = r.index, v = r.data;
		Wj.tmp.i = i;
		v.state = '已停止';
		b.up('form').getForm().setValues(v);
		Wj.util.confirmEditForm(fp.getForm(), Wj.url.SurgyStopAdvice, function(){
			Ext.Msg.alert('提示', '成功停止执行！', function(){
				this.getSurgyptadvgrid().getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getSurgyptadvgrid().getSelectionModel().select(Wj.tmp.i);
						}
						Wj.tmp.i = undefined;
					}
				});
			}, Wj.controller.SurgyDoctor);
		});
	},
	//------------------------2016年5月5日20:43:54-----新伤员提醒-----
		testSurgyNewPt: function() {	
		
		var url = Wj.url.testSurgyNewPt;
		
		Ext.Ajax.request({
			url: url,
			method: 'get',
			timeout: 15000,
			scope: Wj.controller.SurgyDoctor,
			
			success: function(r, a){
				Wj.util.handleRequestSuccess(r, a, function(){
					
					Wj.util.t = window.setTimeout(Wj.controller.SurgyDoctor.testSurgyNewPt, 15 * 1000);
					
					var res = arguments[0].results.isNewPt,
						controller = Wj.controller.SurgyDoctor,
						win;
					console.log("res:"+res);
					if (res) {
						
						controller.showNewPtWin();
						
					} else {
						
						win  = controller.getSurgynewptwin();
						
						if(win) {
							win.collapse();
							win.hide();
						}
					}
					
				});
			},

			failure: function(){
				console.log('Failed!!!');
				Wj.util.t = window.setTimeout(Wj.controller.SurgyDoctor.testNewPt, 15 * 1000);
				Wj.util.HandleRequestFailure();
			}
		});
	},
		showNewPtWin: function() {

		var win = this.getSurgynewptwin();

		if(!win) {
			Ext.widget('surgynewptwin').show();
		} else {
			win.show();
			win.expand();
			win.down('grid').getStore().load();
		}

	},
	handleConfirmSurgyNewPt: function(b) {

		var controller = Wj.controller.SurgyDoctor,
			win = controller.getSurgynewptwin();

		var url = Wj.url.ConfirmSurgyNewPt,
			controller = Wj.controller.SurgyDoctor,
			navgrid = controller.getSurgydocnav(),
			sl, win = controller.getSurgynewptwin();
		
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
 
    ShowImg:function()
    {
    	Ext.widget('surgyimageviewer').show();
    },
    //检索功能
    SearchPt:function()
    {
    var gr = Ext.getCmp('id_surgyNavGrid');
    console.log('gr:'+gr);
    
		gr.getStore().load({
		scope: this,
		callback: function(r, o, suc){
			if(suc && r && r.length){
				var searchCon = Ext.getCmp('id_surgySearch').getValue();
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