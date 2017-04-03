Ext.define('Wj.controller.Doctor', {
	extend: 'Ext.app.Controller',

	views: [
		'Doctor',
		'Banner',
		'Content',
		'doctor.Nav',
		'doctor.PtForm',
		'doctor.PtCondition',
		'doctor.PtCourse',
		'doctor.PtInspection',
		'doctor.PtAdvice',
		'doctor.AddPtAdvice',
		'doctor.AddPtCourse',
		'doctor.Report',
		'doctor.NewPtWin',
		'doctor.AddInspection',
		'doctor.AddTest',
		'doctor.VolunteerTask',
		'doctor.AddTask',
		'doctor.AddOperation',
		'doctor.AddTransfer',
		'doctor.Operation',
		'doctor.Transfer',
		'doctor.PtTransfer',
		'doctor.PtPhoto',
		'imgviewer.imageviewer'
		
	],

	stores: [
		'doctor.Patient',
		'doctor.NewPatient',
		'doctor.PtAdvice',
		'doctor.AdvExecRec',
		'doctor.AdvContents',
		'doctor.PtCondition',
		'doctor.PtCourse',
		'Report',
		'inspection.Main',
		'inspection.Detail',
		'doctor.VolunteerTask',
		'doctor.Operation',
		'doctor.Transfer',
		'doctor.PtBaseInfo',
		'surgydoctor.SurgyInspectMain',
		'surgydoctor.SurgyPtAdvice',
		'doctor.Hospital'
	],

	refs: [{
		selector: 'doctor > content',
		ref: 'content'
	}, {
		selector: 'doctor > container > docnav',
		ref: 'docnav'
	}, {
		selector: 'doctor > container > ptform',
		ref: 'ptform'
	}, {
		selector: 'doctor > content > docreport',
		ref: 'docreport'
	}, {
		selector: 'doctor > content > docptadvice',
		ref: 'ptadv'
	}, {
		selector: 'doctor > content > docptadvice > grid',
		ref: 'ptadvgrid'
	}, {
		selector: 'doctor > content > docptadvice > form',
		ref: 'ptadvform'
	}, {
		selector: 'doctor > content > docptinspection',
		ref: 'docptinspection'
	}, {
		selector: 'doctor > content > docptinspection > #main_grid',
		ref: 'insmaingrid'
	}, {
		selector: 'doctor > content > docptinspection > #detail_grid',
		ref: 'insdetailgrid'
	}, {
		selector: 'addptadv',
		ref: 'addadv'
	}, {
		selector: 'addptadv > form',
		ref: 'addadvform'
	}, {
		selector: 'doctor > content > docptadvice > form > fieldset > grid',
		ref: 'advexecrecgrid'
	}, {
		selector: 'doctor > content > docptcondition > grid',
		ref: 'ptconditiongrid'
	}, {
		selector: 'doctor > content > docptcourse > grid',
		ref: 'ptcoursegrid'
	}, {
		selector: 'doctor > content > docptcourse > form',
		ref: 'ptcourseform'
	}, {
		selector: 'newptwin',
		ref: 'newptwin'
	},{
		selector: 'addinspection',
		ref: 'addins'
	}, {
		selector: 'addinspection > form',
		ref: 'addinsform'
	}, 
	{
		selector: 'addtest',
		ref: 'addtest'
	}, {
		selector: 'addptest > form',
		ref: 'addtestform'
	},{
		selector: 'doctor > content > volunteertask > grid',
		ref: 'volunteertask'
	}, 
	{
		selector: 'volunteertask > grid > toolbar > #remove',
		ref: 'removetask'
	},{
		selector: 'addoperation',
		ref: 'addoperation'
	}, {
		selector: 'addoperation > form',
		ref: 'addoperationform'
	},{
		selector: 'addtransfer',
		ref: 'addtransfer'
	},
	{
		selector: 'addtransfer > form',
		ref: 'addtransferform'
	},{
		selector: 'addtask',
		ref: 'addtask'
	}, {
		selector: 'addtask > form',
		ref: 'addtaskform'
	},{
		selector: 'doctor > content > pttransfer > grid',
		ref: 'pttransfer'
	},{
		selector: 'doctor > content > pttransfer > Infogird',
		ref: 'pttransferInfogird'
	},
	
	{
		selector: 'doctor > content > transfer > grid',
		ref: 'transfer'
	},
	{
		selector: 'transfer > form',
		ref: 'transferform'
	},
	{
		selector: 'doctor > content > ptphoto',
		ref: 'ptphoto'
	},
	{
		selector: 'doctor > content > ptphoto > #ptphotogrid',//伤员影像数据
		ref: 'ptphotogrid'
	}
	],

	init: function(){

		this.control({

			'doctor': {

				afterrender: function(){

					this.initContentView();
					
					var fileObj;
					var tempurl = "http://10.32.41.113:8082/WJ03/Inspect/4D0P12016-05-1215-30-51.png";
					//"_blank","height=0,width=0,toolbar=no,menubar=no,scrollbars=no,resizable=on,location=no,status=no"
//					var tempurl = "http://localhost:8082/WJ03/webapp/index.htm";
//					fileObj=window.open(tempurl);
//					//fileObj.window.close();
//					fileObj.document.execCommand('SaveAs',true,'mycodes.txt');
//					fileObj.close();
//					document.location = tempurl;
//					document.execCommand('SaveAs',false,'E:\\4D0P12016-05-1215-30-51.png');
				}
			},

			'doctor > content': {

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
					var info = ' - 当前病区：' +
						d.sector + ' - 欢迎你，' +
						d.userName + ' ' +
						d.roleStr + '！' + ' - ' +
						date;

					if(d && infobar){
						infobar.setText(info);
					}

					/////////////////

					// open patinet condition tab when first login to nurse station.
					this.getContent().setActiveTab(this.getPtadv());

				},

				tabchange: function(t, nt, ot, opt){

					var sl = this.getDocnav().getSelectionModel().getSelection();
					if(sl && sl[0]){
						
						var id = sl[0].data.id;
						var st = nt.down('grid').getStore();

						st.getProxy().extraParams = { id: id };
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

			'doctor > container > docnav': {

				afterrender: function(){

					this.getStore('doctor.Patient').getProxy().extraParams = {
						all: false
					};

					this.getStore('doctor.Patient').load({
						scope: this,
						callback: function(r, o, suc){

							if(suc && r && r.length){
								this.getDocnav().getSelectionModel().select(0);
							}
						}
					});

					this.testNewPt();

				},

				selectionchange: function(m, r){

					if(r && r[0]){

						this.getPtform().getForm().loadRecord(r[0]);

						if(r[0].data.id){

							var con = this.getContent();
							var activeTab = con.getActiveTab();
							if(activeTab)
								var gr = activeTab.down('grid');

							if(gr){

								gr.getStore().getProxy().extraParams = { id: r[0].data.id };
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
							this.PtDataProcess(r);
						} else {

							this.getStore('doctor.PtAdvice').getProxy().extraParams = {};
							this.getStore('doctor.PtCondition').getProxy().extraParams = {};
							this.getStore('doctor.PtCourse').getProxy().extraParams = {};

						}
					}
				}
			},

			'doctor > content > docptinspection > #main_grid': {

				selectionchange: function(m, r){

					if(r && r[0]){

						console.log(this);
						console.log(this.getInsdetailgrid());

						id = r[0].data.id,
							dg = this.getInsdetailgrid(),
							store = dg.getStore();

						console.log('in #main_grid, id:', id);
						
						store.getProxy().extraParams = {
							id: id
						};

						// adjust paging tool.
						dg.down('pagebar').moveFirst();				
					} else {

						this.getInsdetailgrid().getStore().removeAll();

					}
				}
			},

			'doctor > content > docptadvice > grid': {

				selectionchange: function(m, r){

					if(r && r[0]){

						this.getPtadvgrid().down('toolbar').down('#remove').setDisabled(false);

						this.getPtadvform().getForm().loadRecord(r[0]);

						var recstore = this.getAdvexecrecgrid().getStore();
						recstore.getProxy().extraParams.id = r[0].data.id;
						console.log('advice id: ' + r[0].data.id);
						recstore.load();


						var st = r[0].data.state;
						var tp = r[0].data.type;
						var fds = this.getPtadvform().down('fieldset').query('field');
						
						if(st == '已执行' || st == '已停止'){

							for(var f in fds){
								fds[f].setReadOnly(true);
							}

							this.getPtadvform().down('#f_endTime').setDisabled(true);
							
						} else {
							
							for(var f in fds){
								if(fds[f].name == 'id' || fds[f].name == 'type' || fds[f].name == 'state')
									fds[f].setReadOnly(true);
								else
									fds[f].setReadOnly(false);
							}

							if(st == '未执行'){		// suggests that the advice type is for short term
								this.getPtadvform().down('#f_endTime').setDisabled(true);
							} else {	// then for long term but the advice has been stopped
								this.getPtadvform().down('#f_endTime').setDisabled(false);
							}
						}

						if(tp == '长期医嘱'){
							this.getPtadvform().down('#stop_exec').setDisabled(false);
						} else {
							this.getPtadvform().down('#stop_exec').setDisabled(true);
						}


						for(var f in fds){
							if(fds[f].readOnly == true)
								fds[f].setFieldStyle(Wj.consts.css_readOnly);
							else
								fds[f].setFieldStyle(Wj.consts.css_notReadOnly);
						}

					} else
						this.getPtadvgrid().down('toolbar').down('#remove').setDisabled(true);
				}
			},

			
			'doctor > content > docptadvice > form > fieldset > #f_content': {

			
				keyup: function(t, e, opt) {

					var key = String.fromCharCode(e.button + 1);
					console.log(e.button + 1);

				
					var type = t.up('form').getForm().findField('type2').value,
						val = t.getValue(),
						ts = Wj.consts.adviceType,
						store = t.getStore();
					console.log('type'+type);
					store.getProxy().extraParams = {
						type2: type,
						term: val
					},

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

			// listener of addform

			'addptadv > form': {

				afterrender: function(t, opt){
					console.log(5555);
					var n = this.getPtName();
					if(n !== null) {
						t.getForm().findField('name').setValue(n);
					}

				}

			},

			'addptadv > form > #add_type': {

				select: function(cb, r, opt){
					console.log(666);
					var f = cb.up('form');

					if(r[0].raw[0] == '长期医嘱'){
						
						f.down('#add_endTime').setDisabled(false);
						f.down('#add_state').bindStore(['有效', '已停止']);
						f.down('#add_state').setValue('有效');

					} else if(r[0].raw[0] == '临时医嘱'){
						
						f.down('#add_endTime').setDisabled(true);
						f.down('#add_state').bindStore(['已执行', '未执行']);
						f.down('#add_state').setValue('未执行');

					}
				}
			},

			'addptadv > form > #add_type2': {

				select: function(cb, r, opt){

					var f = cb.up('form'),
					cont = f.down('#add_content');

					console.log(r);

					if(r[0].raw[0]) {
						console.log('not readonly!');
						cont.setReadOnly(false);
						cont.setFieldStyle(Wj.consts.css_notReadOnly);
					} else {
						console.log('readonly!');
						cont.setReadOnly(true);
						cont.setFieldStyle(Wj.consts.css_readOnly);
					}

				},
				
				
				blur: function(t, eventObj, opts) {
					console.log('8888');
					var url = Wj.url.DoseUnit,
						par = {
							type2: t.up('form').getForm().findField('type2').getValue(),
							content: t.getValue()
						};

					if (t.readOnly !== true) {
						
						console.log(par);

						Wj.util.pullData.call(t, url, par, function(res) {

							console.log(res);

							if (res.success && res.results.unit) {
								this.up('form').getForm().findField('dose').setValue(res.results.unit);
							}

						});
					} 
					
				}
				
				
			},

			'addptadv > form > #add_content': {


				
				keyup: function(t, e, opt){
					console.log('7777');
					var key = String.fromCharCode(e.button + 1);
					console.log(e.button + 1);
					
					/*if(!(/[a-zA-Z0-9]/.test(key))) {
						return false;
					}*/

					var type = t.up('form').getForm().findField('type2').value,
						val = t.getValue(),
						ts = Wj.consts.adviceType,
						store = t.getStore();
					console.log('type'+type);
					console.log('val'+val);
					store.getProxy().extraParams = {
						type2: type,
						term: val
					},

					console.log('store.getProxy().extraParams', store.getProxy().extraParams);

					Wj.tmp.timer = 0;

					
				},

				blur: function(t, eventObj, opts) {
					console.log('8888');
					var url = Wj.url.DoseUnit,
						par = {
							type2: t.up('form').getForm().findField('type2').getValue(),
							content: t.getValue()
						};

					if (t.readOnly !== true) {
						
						console.log(par);

						Wj.util.pullData.call(t, url, par, function(res) {

							console.log(res);

							if (res.success && res.results.unit) {
								this.up('form').getForm().findField('dose').setValue(res.results.unit);
							}

						});
					} 
					
				}

			},

			// listeners for ptcourse view

			'doctor > content > docptcourse > grid': {

				selectionchange: function(m, r){

					if (r && r[0]) {

						this.getPtcoursegrid().down('toolbar').down('#remove').setDisabled(false);
						this.getPtcoursegrid().down('toolbar').down('#download').setDisabled(false);						
						
						this.getPtcourseform().getForm().loadRecord(r[0]);
					
					} else {

						this.getPtcoursegrid().down('toolbar').down('#remove').setDisabled(true);
						this.getPtcoursegrid().down('toolbar').down('#download').setDisabled(true);

					}
				}
			},

			'newptwin': {

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

			'newptwin > grid': {

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

		

			//*****************************************************ZCJ********************************************
		
			// listener of addform

			'addinspection > form': {

				afterrender: function(t, opt){
					//console.log(5555);
					var n = this.getPtName();
					if(n !== null) {
						t.getForm().findField('name').setValue(n);
					}

				}

			},


			'addinspection > form > #add_content': {

				
				afterrender:function(cb, r, opt){

					var f = cb.up('form'),
						cont = f.down('#add_content');

					console.log(r);
					
						console.log('not readonly!');
						cont.setReadOnly(false);
						cont.setFieldStyle(Wj.consts.css_notReadOnly);																																										
					
					},
				keyup: function(t, e, opt){

					var key = String.fromCharCode(e.button + 1);
					console.log(e.button + 1);
					
					/*if(!(/[a-zA-Z0-9]/.test(key))) {
						return false;
					}*/

					var type = t.up('form').getForm().findField('type2').value,
						val = t.getValue(),
						ts = Wj.consts.adviceType,
						store = t.getStore();
					  
					store.getProxy().extraParams = {
						type2: "检查",
						term: val
					},

					console.log('store.getProxy().extraParams', store.getProxy().extraParams);

					Wj.tmp.timer = 0;
																							
				},

				blur: function(t, eventObj, opts) {

					var url = Wj.url.DoseUnit,
						par = {
							type2: t.up('form').getForm().findField('type2').getValue(),
							content: t.getValue()
						};

					if (t.readOnly !== true) {
						
						console.log(par);

						Wj.util.pullData.call(t, url, par, function(res) {

							console.log(res);

							if (res.success && res.results.unit) {
								this.up('form').getForm().findField('dose').setValue(res.results.unit);
							}

						});
					} 
					
				}

			},
			
			

//***************************************  Test   ************************************************
			
			
			

			'addtest > form': {

				afterrender: function(t, opt){
					//console.log(5555);
					var n = this.getPtName();
					if(n !== null) {
						t.getForm().findField('name').setValue(n);
					}

				}

			},


			'addtest > form > #add_content': {

				
				afterrender:function(cb, r, opt){

					var f = cb.up('form'),
						cont = f.down('#add_content');

					console.log(r);
					
						console.log('not readonly!');
						cont.setReadOnly(false);
						cont.setFieldStyle(Wj.consts.css_notReadOnly);					
					
					},
				keyup: function(t, e, opt){

					var key = String.fromCharCode(e.button + 1);
					console.log(e.button + 1);
					
					/*if(!(/[a-zA-Z0-9]/.test(key))) {
						return false;
					}*/

					var type = t.up('form').getForm().findField('type2').value,
						val = t.getValue(),
						ts = Wj.consts.adviceType,
						store = t.getStore();
					  
					store.getProxy().extraParams = {
						type2: type,
						term: val
					},

					console.log('store.getProxy().extraParams', store.getProxy().extraParams);

					Wj.tmp.timer = 0;
					
				},

				blur: function(t, eventObj, opts) {

					var url = Wj.url.DoseUnit,
						par = {
							type2: t.up('form').getForm().findField('type2').getValue(),
							content: t.getValue()
						};

					if (t.readOnly !== true) {
						
						console.log(par);

						Wj.util.pullData.call(t, url, par, function(res) {

							console.log(res);

							if (res.success && res.results.unit) {
								this.up('form').getForm().findField('dose').setValue(res.results.unit);
							}

						});
					} 
					
				}

			},
			
///////////////////////////////// OPERATION  ////////////////////////
			
			'addoperation > form': {

				afterrender: function(t, opt){
					//console.log(5555);
					var n = this.getPtName();
					if(n !== null) {
						t.getForm().findField('name').setValue(n);
					}

				}

			},

			'addoperation > form > #add_content': {

				
				afterrender:function(cb, r, opt){

					var f = cb.up('form'),
						cont = f.down('#add_content');

					console.log(r);
					
						console.log('not readonly!');
						cont.setReadOnly(false);
						cont.setFieldStyle(Wj.consts.css_notReadOnly);																		
																													
					},
				keyup: function(t, e, opt){

					var key = String.fromCharCode(e.button + 1);
					console.log(e.button + 1);
					
					/*if(!(/[a-zA-Z0-9]/.test(key))) {
						return false;
					}*/

					var type = t.up('form').getForm().findField('type2').value,
						val = t.getValue(),
						ts = Wj.consts.adviceType,
						store = t.getStore();
					  
					store.getProxy().extraParams = {
						type2: type,
						term: val
					},

					console.log('store.getProxy().extraParams', store.getProxy().extraParams);

					Wj.tmp.timer = 0;
		
				},

				blur: function(t, eventObj, opts) {

					var url = Wj.url.DoseUnit,
						par = {
							type2: t.up('form').getForm().findField('type2').getValue(),
							content: t.getValue()
						};

					if (t.readOnly !== true) {
						
						console.log(par);

						Wj.util.pullData.call(t, url, par, function(res) {

							console.log(res);

							if (res.success && res.results.unit) {
								this.up('form').getForm().findField('dose').setValue(res.results.unit);
							}

						});
					} 
					
				}

			},
			
			
			
			
			
//*********************************** VOLUNTEER   TASK   ***********************************
			
			
			
//			'doctor > content > volunteertask > grid': {
//
//				selectionchange: function(m, r){
//
//					if (r && r[0]) {
//
//						this.getVolunteertask().down('toolbar').down('#remove').setDisabled(false);						
//	
//						this.getVolunteertask().getGrid().loadRecord(r[0]);
//					
//					} else {
//
//						this.getVolunteertask().down('toolbar').down('#remove').setDisabled(true);
//
//					}
//				}
//			},
				

			'doctor > content > volunteertask > grid': {

				selectionchange: function(m, r){
					if(r && r[0]){
						console.log(r[0].data.id);
						this.getRemovetask().setDisabled(false);
					} else {
						this.getRemovetask().setDisabled(true);
					}
				},

				edit: function(edit, e){
					
					Wj.util.editGridRecord(e, Wj.url.editVolunteerTask);

				}
			},
			
			'doctor > content > transfer > grid ': {
				selectionchange: function(m, r){
					
				},
				edit: function(edit, e){
					
					Wj.util.editGridRecord(e, Wj.url.editVolunteerTask);

				}
			},					
		//*******************************************ZCJ END ****************************************************
			//------------2016年5月10日10:12:41-------------------------
					//影像数据 表格
			'doctor > content > ptphoto > #ptphotogrid': {
					afterrender: function(){
						var th = this.getDocnav().getSelectionModel().getSelection();
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
						id = r[0].data.id;
//							console.log('selectid:', id);
//						    console.log('ID:',r[0].data.id);
//							console.log('photodec:',r[0].data.photodec);
							
							//刷新 影像数据界面
							var photosrc = r[0].data.url;//影像图片 地址
							var photodec = r[0].data.illustration;//影像图片 描述
							console.log('photosrc:',r[0].data.url);
						    var oa = Ext.ComponentQuery.query('#ptphotopanel')[0];
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
						   var dc = Ext.ComponentQuery.query('#ptphotocontent')[0]; 
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
			}
//*****------------------------------------------------------------------------------------------------
		});
	},
	
	
	
	

	

	showDocView: function(){

		console.log('-- Controller Doctor : showDocView --');

		var doc = Ext.widget('doctor');
		Ext.getCmp('viewport').removeAll(true);
		Ext.getCmp('viewport').add(doc);

	},

	initContentView: function(){

		this.initReportTab();
		this.initPtTransferTab();
		
		this.initPtConditionTab();
		this.initPtInspectionTab();
		this.initPtPhotoTab();
		this.initOperationTab();
		this.initPtCourseTab();
		this.initPtAdviceTab();
		this.initVolunteerTaskTab();
		this.initTransferTab();


	},

	initReportTab: function(){

		var c = this.getContent();
		var res = Ext.widget('docreport');
		c.add(res);

	},

	initPtConditionTab: function(){

		var c = this.getContent();
		var con = Ext.widget('docptcondition');
		c.add(con);

	},

	initPtCourseTab: function(){
		
		var c = this.getContent();
		var cou = Ext.widget('docptcourse');
		c.add(cou);
		
	},

	initPtInspectionTab: function(){
		
		var c = this.getContent();
		var ins = Ext.widget('docptinspection');
		c.add(ins);
		
	},

	initPtAdviceTab: function(){
		
		var c = this.getContent();
		var adv = Ext.widget('docptadvice');
		c.add(adv);
		
	},
	
	   initVolunteerTaskTab: function(){
		
		var c = this.getContent();
		var adv = Ext.widget('volunteertask');
		c.add(adv);
		
	},
	
    initOperationTab: function(){
		
		var c = this.getContent();
		var adv = Ext.widget('operation');
		c.add(adv);
		
	},
	
	
	//转院查看
	initTransferTab: function(){
		
		var c = this.getContent();
		var adv = Ext.widget('transfer');
		c.add(adv);
		
	},
		//转运管理
	initPtTransferTab: function(){
		
		var c = this.getContent();
		var adv = Ext.widget('pttransfer');
		c.add(adv);
		
	},
	//影像数据
	initPtPhotoTab: function(){
		
		var c = this.getContent();
		var ins = Ext.widget('ptphoto');
		c.add(ins);
		
	},

	changeNavCheckBox: function(t, nv, ov, opt) {

		console.log(nv);
		console.log(ov);
		console.log(this);

		if (nv === true) {
			this.getStore('doctor.Patient').getProxy().extraParams = {
				all: true
			};
		} else {
			this.getStore('doctor.Patient').getProxy().extraParams = {
				all: false
			};
		}

		this.getStore('doctor.Patient').load({
			scope: this,
			callback: function(r, o, suc){

				if(suc && r && r.length){
					this.getDocnav().getSelectionModel().select(0);
				}

			}
		});

	},

	getPtId: function(){
	
		var sl = this.getDocnav().getSelectionModel().getSelection();
		if(sl && sl[0]) {
			return sl[0].data.id;
		} else {
			return null;
		}
	
	},
	
	
	getUser: function(){
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
		
		return d.userName;
	
	},
	

	getPtName: function(){

		var sl = this.getDocnav().getSelectionModel().getSelection();
		if(sl && sl[0]) {
			return sl[0].data.name;
		} else {
			return null;
		}
	
	},

	addAdvice: function(b){

		Ext.widget('addptadv').show();

	},
	
	addInspection: function(b){

		Ext.widget('addinspection').show();

	},
	
	addTest: function(b){

		Ext.widget('addtest').show();

	},
	
	
	addTask: function(b){

		Ext.widget('addtask').show();

	},
	
	
	addOperation: function(b){

		Ext.widget('addoperation').show();

	},
	
	
	addTransfer: function(b){

		Ext.widget('addtransfer').show();

	},

	confirmAddAdvice: function(b){

		Wj.util.confirmEditForm(b.up('window').down('form').getForm(), Wj.url.AddPtAdvice, function(){	
			Ext.Msg.alert('提示', '添加成功！', function(){
				this.getAddadv().close();
				this.getPtadvgrid().getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getPtadvgrid().getSelectionModel().select(0);
						}
					}
				});
			}, Wj.controller.Doctor);
		});

	},

	

	
//**************************************************************************************	
	
	
		
	
	
	confirmAddInspection: function(b){
         console.log(123),
		Wj.util.confirmEditForm(b.up('window').down('form').getForm(), Wj.url.AddInspection, function(){	
			Ext.Msg.alert('提示', '添加检查成功！', function(){
				this.getAddins().close();
				this.getDocptinspection().getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getDocptinspection().getSelectionModel().select(0);
						}
					}
				}
				);
			}, Wj.controller.Doctor);
		});

	},
	
	
	
	confirmAddTest: function(b){
		console.log(123),
		Wj.util.confirmEditForm(b.up('window').down('form').getForm(), Wj.url.AddTest, function(){	
			Ext.Msg.alert('提示', '添加检验成功！', function(){
				this.getAddtest().close();
				Ext.getCmp("doc_ins_detail_grid").getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getDocptinspection().getSelectionModel().select(0);
						}
					}
				}
				);
			}, Wj.controller.Doctor);
		});

	},
	
	
	
	confirmAddTask: function(b){
		console.log(123);
	//	var t = b.up('window').down('form');//.getForm();addVolunteerTask
		//console.log('t:',t);
		Wj.util.confirmEditForm(b.up('window').down('form').getForm(), Wj.url.addVolunteerTask, function(){	
			console.log(12349);
			Ext.Msg.alert('提示', '添加成功！', function(){
				console.log(1234);
				this.getAddtask().close();
				console.log(1235);
				this.getVolunteertask().getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getVolunteertask().getSelectionModel().select(0);
						}
					}
				});
			}, Wj.controller.Doctor);
		});

	},
	
	
	
	
	confirmAddOperation: function(b){
        //console.log(336),
		Wj.util.confirmEditForm(b.up('window').down('form').getForm(), Wj.url.AddOperation, function(){	
			Ext.Msg.alert('提示', '添加手术成功！', function(){
				this.getAddoperation().close();
				Ext.getCmp("docOeraGrid").getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getDocptinspection().getSelectionModel().select(0);
						}
					}
				}
				);
			}, Wj.controller.Doctor);
		});

	},
	
	
	
	
	confirmAddTransfer: function(b){
        //console.log(336),
		Wj.util.confirmEditForm(b.up('window').down('form').getForm(), Wj.url.AddTransfer, function(){	
			Ext.Msg.alert('提示', '添加转院信息成功！', function(){
				this.getAddtransfer().close();
				Ext.getCmp("doc_transport").getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							//this.getPttransfer().getSelectionModel().select(0);
						}
					}
				}
				);
			}, Wj.controller.Doctor);
		});

	},
	
	
	
	
	
	removeTask: function(b){
		
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.RemoveVolunteerTask;
			var par = { id: sl[0].data.ptid };
		
		
		Ext.Msg.confirm('提示', '确定删除这个任务吗？', function(btn){

			if(btn == 'yes'){
		
		Wj.util.removeGridRecord(b.up('toolbar').up('grid'), Wj.url.RemoveVolunteerTask , function() {		
			Ext.Msg.alert('提示', '删除成功！', function() {
				this.getVolunteertask().down('pagebar').moveFirst();
			}, Wj.controller.Doctor);	
		});
	
			}},this);
		
		}
	},

	
	
	
	
	
	
//*****************************************************************************************************

	editAdvice: function(b){

		var fp = b.up('form'), i = fp.getRecord().index;
		Wj.tmp.i = i;

		Wj.util.confirmEditOrAddForm('edit', fp.getForm(), Wj.url.EditDocPtAdvice, function(){
			Ext.Msg.alert('提示', '更新成功！', function(){
				this.getPtadvgrid().getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getPtadvgrid().getSelectionModel().select(Wj.tmp.i);
						}
						Wj.tmp.i = undefined;
					}
				});
			}, Wj.controller.Doctor);
		});

	},

	removeAdvice: function(b){
		
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.RmvPtAdvice;
			var par = { id: sl[0].data.id };
		
		
		Ext.Msg.confirm('提示', '确定删除这个医嘱吗？', function(btn){

			if(btn == 'yes'){
		
				Wj.util.removeGridRecord(b.up('toolbar').up('grid'), Wj.url.RmvPtAdvice, function(){
					Ext.Msg.alert('提示', '删除成功！', function(){
						this.getPtadvgrid().down('pagebar').moveFirst();
					}, Wj.controller.Doctor);
				});
	
			}},this);
		
		}
	



	},

	stopAdvice: function(b){
			console.log('stopAdvice')
		var fp = b.up('form'), r = fp.getRecord(), i = r.index, v = r.data;
			console.log('getForm'+fp);
		Wj.tmp.i = i;
		v.state = '已停止';
		b.up('form').getForm().setValues(v);
	
		Wj.util.confirmEditForm(fp.getForm(), Wj.url.StopAdvice, function(){
			
			Ext.Msg.alert('提示', '成功停止执行！', function(){
				this.getPtadvgrid().getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getPtadvgrid().getSelectionModel().select(Wj.tmp.i);
						}
						Wj.tmp.i = undefined;
					}
				});
			}, Wj.controller.Doctor);
		});
	},

	submitCourse: function(b){

		var fp = b.up('form'), i = fp.getRecord().index;
		Wj.tmp.i = i;

		var fm = fp.getForm();
		if(fm.isDirty() && fm.isValid()){
			fm.submit({
                url: Wj.url.EditInspect,
                waitMsg: '正在上传请稍候...',
                success: function(f, act){
                	Ext.Msg.alert('提示', '更新成功', function(){
                		this.getPtcoursegrid().getStore().load({
							scope: this,
							callback: function(r, o, suc){
								if(suc && r && r.length) {
									this.getPtcoursegrid().getSelectionModel().select(Wj.tmp.i);
								}
								Wj.tmp.i = undefined;
							}
						});
                	}, Wj.controller.Doctor);
                },
                failure: function(f, act){
                	switch(act.failureType){
                		case Ext.form.action.Action.CONNECT_FAILURE:
                			Wj.util.handleRequestFailure(f);
                			break;
                		case Ext.form.action.Action.SERVER_INVALID:
                			Ext.Msg.alert('提示', action.result.msg);
                	}
                }
            });
		}
	},

	addCourse: function(b){

		Ext.widget('addptcourse').show();
	
	},

	confirmAddCourse: function(b){

		var w = b.up('window'), fm = w.down('form').getForm();

		if(fm.isDirty() && fm.isValid()) {
			
			fm.submit({
                
                url: Wj.url.AddPtCourse,
                //waitMsg: '正在上传请稍候...',
                
                success: function(f, act){
                	Ext.Msg.alert('提示', '添加成功', function() {
                		
                		this.getPtcoursegrid().getStore().load({
							scope: this,
							callback: function(){
								this.getPtcoursegrid().getSelectionModel().select(0);
								w.close();
							}
						});

                	}, Wj.controller.Doctor);
                },

                failure: function(f, act){

                	switch(act.failureType){
                		
                		case Ext.form.action.Action.CONNECT_FAILURE:
                			Wj.util.handleRequestFailure(f);
                			break;
                		
                		case Ext.form.action.Action.SERVER_INVALID:
                			Ext.Msg.alert('提示', action.result.msg);
                			break;
                	}
                }
            });
		}
	},

	removeCourse: function(b){
		console.log('Course')
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.RmvPtCourse;
			var par = { id: sl[0].data.id };
		
		
		Ext.Msg.confirm('提示', '确定删除这条病程吗？', function(btn){

			if(btn == 'yes'){
				Wj.util.removeGridRecord(b.up('toolbar').up('grid'), Wj.url.RmvPtCourse, function() {

					Ext.Msg.alert('提示', '删除成功！', function() {
						this.getPtcoursegrid().down('pagebar').moveFirst();
					}, Wj.controller.Doctor);
				
				});
	
			}},this);
		
		}
	



	
	

	
	},

	downloadDoc: function(b){

		var sl = Wj.controller.Doctor.getPtcoursegrid().getSelectionModel().getSelection();
		if(sl && sl[0])
			window.open(sl[0].data.doc);

	},

	testAuthority: function(tab) {

		var cp = Wj.cp, d = Wj.tmp.loginData, t = tab, docId, form, grid;

		docId = this.getDocnav().getSelectionModel().getSelection()[0].data.docId;

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

	testNewPt: function() {	
		
		var url = Wj.url.testNewPt;
		
		Ext.Ajax.request({
			url: url,
			method: 'get',
			timeout: 15000,
			scope: Wj.controller.Doctor,
			
			success: function(r, a){
				Wj.util.handleRequestSuccess(r, a, function(){
					
					Wj.util.t = window.setTimeout(Wj.controller.Doctor.testNewPt, 15 * 1000);
					
					var res = arguments[0].results[0].isNewPt,
						controller = Wj.controller.Doctor,
						win;
					
					if (res) {
						
						controller.showNewPtWin();
						
					} else {
						
						win  = controller.getNewptwin();
						
						if(win) {
							win.collapse();
							win.hide();
						}
					}
					
				});
			},

			failure: function(){
				Wj.util.t = window.setTimeout(Wj.controller.Nurse.testNewPt, 15 * 1000);
				Wj.util.HandleRequestFailure();
			}
		});
	},

	showNewPtWin: function() {

		var win = this.getNewptwin();

		if(!win) {
			Ext.widget('newptwin').show();
		} else {
			win.show();
			win.expand();
			win.down('grid').getStore().load();
		}

	},
	
	handleConfirmNewPt: function(b) {

		var controller = Wj.controller.Doctor,
			win = controller.getNewptwin();

		var url = Wj.url.ConfirmNewPt,
			controller = Wj.controller.Doctor,
			navgrid = controller.getDocnav(),
			sl, win = controller.getNewptwin();
		
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
	//------------------------------2016年5月11日19:23:54--------------------
	PtDataProcess: function(sl){
		
		//var sl = this.getDocnav().getSelectionModel().getSelection();
		var id;
		var Ih;
		var sel;

		if(sl && sl[0]){
			id = sl[0].data.id;
			console.log('IH ID:',id);
		}
		var baseStore = Ext.StoreMgr.get('doctor.PtBaseInfo');
		baseStore.addListener('load',function(store,records,success){
		for(var i = 0;i<records.length; i++){
			var rec = records[i];			
			if(id === rec.get('id')){
				Ih = i;
				sel = records[Ih];
				var sector;
		if(Wj.cp.get('Wj-sector') !== "")
			sector = Wj.cp.get('Wj-sector');
		else{
			sector = " ";
		}
			var temp1 = docvalue + "<div style='text-align: center;font-size: 13px;'>分区：" + sector + blanspace3
				+ "床号："+ sel.get('bedNum') + blanspace3 +
				"ID："+ sel.get('id') + "</div>"+ENT+ENT;


//			var temp2 = "<div style='text-align: left;font-size: 12px'>"
//				+blanspace1+"姓名：" + sel.get('name') + setBlankspace(BlanksLen - sel.get('name').length*3) 
//				+ "性别："+ sel.get('sex')+"<br>" 
//				+blanspace1+"年龄："+ sel.get('age') +setBlankspace(BlanksLen - String(sel.get('age')).length*2) +"RFID："+ sel.get('rfid')+"<br>" 
//				+blanspace1+"受伤类型："+ sel.get('woundType') + setBlankspace(BlanksLen-6 - sel.get('woundType').length*3) +"受伤时间："+ sel.get('woundTime')+"<br>" 
//				+blanspace1+"受伤地点："+ sel.get('woundAddr')+"</div>"+"<br>"+"<br>";

			
				TBIHead = hline + setItem("基本信息")
						+TCSS
						+"<table frame='box' align='center'>"
						+"<tr>"
						+"<th style= font-size:"+thfontSize+">姓名</th>"
						+"<th style= font-size:"+thfontSize+">性别</th>"
						+"<th style= font-size:"+thfontSize+">年龄</th>"
						+"<th style= font-size:"+thfontSize+">RFID</th>"
						+"<th style= font-size:"+thfontSize+">受伤类型</th>"
						+"<th style= font-size:"+thfontSize+">受伤时间</th>"
						+"<th style= font-size:"+thfontSize+">受伤地点</th>"
						+"</tr>";
				TBIBody = "<tr>"
						+"<td class = 'onecenter'>"+sel.get('name')+"</td>"
						+"<td class = 'onecenter'>"+sel.get('sex')+"</td>"
						+"<td class = 'onecenter'>"+sel.get('age')+"</td>"
						+"<td class = 'onecenter'>"+sel.get('rfid')+"</td>"
						+"<td class = 'onecenter'>"+sel.get('woundType')+"</td>"
						+"<td class = 'onecenter'>"+sel.get('woundTime')+"</td>"
						+"<td class = 'onecenter'>"+sel.get('woundAddr')+"</td>"
						+"</tr>";
				TBIEnd = "</table>"+ENT;
					TBInfo = TBIHead + TBIBody + TBIEnd;
					
				TEMP = temp1 + TBInfo;
			//console.log('TEMP:',TEMP);
				TEND = ENT+ENT+"<div style='font-size: 14px;text-align: left;'>负责人：                             "+ENT+"时间："+NowDate()+"</div><br>";
			Ext.getCmp('trancontent').setValue(TEMP+TInsp+TPhoto+TSurg+
												TCond+TCournd+TAdvnd+TDispnd+TEND);
			//console.log("Ih:",Ih);	

				
			}
		};
		
			//alert(msg.join('\n'));
			
		});
		baseStore.load();
		
		
//-----------------------------------检查检验-----------------------------
		if(sl && sl[0]){
			
		var id = sl[0].data.id;
		console.log('st:',id);
		var st = Ext.getCmp("doc_ins_detail_grid").getStore();
		console.log('st:',st);
		st.getProxy().extraParams = { id: id };
		st.load({			
			callback: function(records, o, suc){
				console.log("records:",records);
				TEMP1="";//清空
				for(var i = 0;i<records.length; i++){
					var rec = records[i];
					console.log("rec:",rec);
//					console.log("id:",id);
					console.log("rec.get('mid'):",rec.get('mid'));
					if(id === rec.get('mid')){
						Ih = i;
						sel = records[Ih];
						//console.log("Ih:",Ih);

						TEMP1 = TEMP1
								+"<tr>"
								+"<td class = 'onecenter'>"+sel.get('item')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('itemStr')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('resultNum')+"</td>"
								+"</tr>";
						}
					};
					if(Ih === 0){
						TEMP1 =setLowItem("暂无");
						TInHead =hline + setItem("检查检验");
					}else{
						TInHead =hline + setItem("检查检验")
						+TCSS
						+"<table frame='box' align='center'>"
						+"<tr>"
						+"<th style= font-size:"+thfontSize+">化验项目</th>"
						+"<th style= font-size:"+thfontSize+">中文名称</th>"
						+"<th style= font-size:"+thfontSize+">结果</th>"
						+"</tr>";
					}
					
					TInEnd = "</table>";
					TInsp = TInHead+TEMP1+TInEnd;
	
					Ext.getCmp('trancontent').setValue(TEMP+TInsp);
				}
						
			});
						
		}
//-----------------------------------影像数据-----------------------------
		if(sl && sl[0]){
			
		var id = sl[0].data.id;
		var st = Ext.getCmp("docptphoto").getStore();
		st.getProxy().extraParams = { id: id };
		console.log('id'+id);
		st.load({
			
			callback: function(records, o, suc){
				TPBody1="";//清空
				TPBody2="";//清空
				Ih = 0;
				for(var i = 0;i<records.length; i++){
					var rec = records[i];
					if(id === rec.get('pid')){
						Ih = i;
						sel = records[Ih];
						
						var ur =sel.get('url');
						console.log('ur'+ur);
						var b=ur.split(';');						
						TPBody1 = TPBody1
								+"<tr>"
								+"<td class = 'onecenter'>"+sel.get('dname')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('content')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('applyId')+"</td>"
								+"</tr>";						
						TPBody2 = TPBody2
								+"<tr>"
						//	for(i=0;i<b.length;i++){
						//		console.log('b'+b[i]);
								+"<td>"+"<img src="+b[1]+">"+"</td>"
								
						//		}
								//+"<td>"+"<img src='resource/img/NoPhotoDis.png'"+"/>"+"</td>"
								+"<td class = 'onecenter'>"+sel.get('illustration')+"</td>"
								+"</tr>";
//						saveImg(sel.get('url'));
							
						}
					};
					if(Ih === 0){
						TPBody1 =setLowItem("暂无");
						TPHead1 =hline + setItem("影像数据");
						TPEnd1 = "<br>";
						TPHead2 = " ";TPBody2 = " ";TPEnd2=" ";
					}else{
						TPHead1 =hline + setItem("影像数据")
								+TCSS
								+"<table frame='box' align='center'>"
								+"<tr>"
								
								+"<th style= font-size:"+thfontSize+">申请医师</th>"
								+"<th style= font-size:"+thfontSize+">申请内容</th>"
								+"<th style= font-size:"+thfontSize+">检验号</th>"

								+"</tr>";
						TPEnd1 = "</table>";
						TPHead2 =ENT + "<table frame='box' align='center'>"
								+"<tr>"
								+"<th style= font-size:"+thfontSize+">图像</th>"
								+"<th style= font-size:"+thfontSize+">描述</th>"
								+"</tr>";
						TPEnd2 = "</table>"+ENT;
					}

					
					
					TPhoto = TPHead1+TPBody1+TPEnd1+TPHead2+TPBody2+TPEnd2;
	
					Ext.getCmp('trancontent').setValue(TEMP+TInsp+TPhoto+TSurg);
				}
						
			});
						
		}
		//-----------------------------------手术记录查看-----------------------------
		if(sl && sl[0]){		
		var id = sl[0].data.id;
		var st = Ext.getCmp("docOeraGrid").getStore();
		//console.log('st:',st);
		st.getProxy().extraParams = { id: id };
		st.load({
			
			callback: function(records, o, suc){
				TSBody="";//清空
				Ih = 0;
				console.log('records'+records);
				for(var i = 0;i<records.length; i++){
					var rec = records[i];
					if(id === rec.get('pid')){
						Ih = i;
						sel = records[Ih];
						//console.log("Ih surgy:",Ih);

						TSBody = TSBody
								+"<tr>"
								+"<td class = 'onecenter'>"+sel.get('id')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('did')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('time')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('content')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('illustration')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('status')+"</td>"
								+"</tr>";
						}
					};
					if(Ih === 0){
						TSBody =setLowItem("暂无");
						TSHead =hline + setItem("手术记录");
					}else{
						TSHead =hline + setItem("手术记录")
						+TCSS
						+"<table frame='box' align='center'>"
						+"<tr>"
						+"<th style= font-size:"+thfontSize+">手术编号</th>"
						+"<th style= font-size:"+thfontSize+">手术医生</th>"
						+"<th style= font-size:"+thfontSize+">手术时间</th>"
						+"<th style= font-size:"+thfontSize+">手术内容</th>"
						+"<th style= font-size:"+thfontSize+">补充说明</th>"
						+"<th style= font-size:"+thfontSize+">手术状态</th>"
						+"</tr>";
					}
					
					TSEnd = "</table>"+ENT;
					TSurg = TSHead+TSBody+TSEnd;
	
					Ext.getCmp('trancontent').setValue(TEMP+TInsp+TPhoto+TSurg);
				}
						
			});
						
		}
		//-----------------------------------病情查看-----------------------------
		if(sl && sl[0]){		
		var id = sl[0].data.id;
		var st = Ext.getCmp("ptConGrid").getStore();
		//console.log('st:',st);
		st.getProxy().extraParams = { id: id };
		st.load({
			
			callback: function(records, o, suc){
				TCoBody="";//清空
				Ih = 0;
				for(var i = 0;i<records.length; i++){
					var rec = records[i];
				//	console.log('id:'+id+"   pid:"+rec.get('pid'));
					if(id === rec.get('pid')){
						Ih = i;
						sel = records[Ih];
					//	console.log("Ih Cond:",sel);
						//console.log("sel.get('id'):",sel.get('id'));
						TCoBody = TCoBody
								+"<tr>"
								+"<td class = 'onecenter'>"+sel.get('id')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('pulse')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('breath')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('diastolic')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('systolic')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('temperature')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('comment')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('time')+"</td>"
								+"</tr>";
						}
					};
					if(Ih === 0){
						TCoBody =setLowItem("暂无");
						TCoHead =hline + setItem("病情记录");
					}else{
						TCoHead =hline + setItem("病情记录")
						+TCSS
						+"<table frame='box' align='center'>"
						+"<tr>"
						+"<th style= font-size:"+thfontSize+">编号</th>"
						+"<th style= font-size:"+thfontSize+">脉搏</th>"
						+"<th style= font-size:"+thfontSize+">呼吸频率</th>"
						+"<th style= font-size:"+thfontSize+">舒张压</th>"
						+"<th style= font-size:"+thfontSize+">收缩压</th>"
						+"<th style= font-size:"+thfontSize+">体温</th>"
						+"<th style= font-size:"+thfontSize+">病情描述</th>"
						+"<th style= font-size:"+thfontSize+">时间</th>"
						+"</tr>";
					}
					
					TCoEnd = "</table>"+ENT;
					TCond = TCoHead+TCoBody+TCoEnd;
	
					Ext.getCmp('trancontent').setValue(TEMP+TInsp+TPhoto+TSurg+TCond);
				}
						
			});
						
		}
		//-----------------------------------病程查看-----------------------------
		if(sl && sl[0]){		
		var id = sl[0].data.id;
		var st = Ext.getCmp("ptCourseGird").getStore();
		//console.log('st:',st);
		st.getProxy().extraParams = { id: id };
		st.load({
			
			callback: function(records, o, suc){
				TCourBody="";//清空
				Ih = 0;
				for(var i = 0;i<records.length; i++){
					var rec = records[i];
					//console.log('id:'+id+"   pid:"+rec.get('pulse'));
					if(id === rec.get('pid')){
						Ih = i;
						sel = records[Ih];
						//console.log("Ih Cond:",Ih);

						TCourBody = TCourBody
								+"<tr>"
								+"<td class = 'onecenter'>"+sel.get('id')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('addTime')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('description')+"</td>"
								+"</tr>";
						}
					};
					if(Ih === 0){
						TCourBody =setLowItem("暂无");
						TCourHead =hline + setItem("病程记录");
					}else{
						TCourHead =hline + setItem("病程记录")
						+TCSS
						+"<table frame='box' align='center'>"
						+"<tr>"
						+"<th style= font-size:"+thfontSize+">编号</th>"
						+"<th style= font-size:"+thfontSize+">编写日期</th>"
						+"<th style= font-size:"+thfontSize+">病程描述</th>"
						+"</tr>";
					}
					
					TCourEnd = "</table>"+ENT;
					TCournd = TCourHead+TCourBody+TCourEnd;
	
					Ext.getCmp('trancontent').setValue(TEMP+TInsp+TPhoto+TSurg+TCond+TCournd);
				}
						
			});
						
		}
		//-----------------------------------主治医生医嘱查看-----------------------------
		if(sl && sl[0]){		
		var id = sl[0].data.id;
		var st = Ext.getCmp("ptAdviceGrid").getStore();
		//console.log('st:',st);
		st.getProxy().extraParams = { id: id };
		st.load({
			
			callback: function(records, o, suc){
				TAdvBody="";//清空
				Ih = 0;
				for(var i = 0;i<records.length; i++){
					var rec = records[i];
					//console.log('id:'+id+"   pid:"+rec.get('pulse'));
					if(id === rec.get('pid')){
						Ih = i;
						sel = records[Ih];
						//console.log("Ih Cond:",Ih);

						TAdvBody = TAdvBody
								+"<tr>"
								+"<td class = 'onecenter'>"+sel.get('id')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('content')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('type')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('type2')+"</td>"
						
								+"<td class = 'onecenter'>"+sel.get('state')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('startTime')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('endTime')+"</td>"
								
								
								+"<td class = 'onecenter'>"+sel.get('dose')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('usage')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('frequency')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('spec')+"</td>"
								+"</tr>";
						}
					};
					if(Ih === 0){
						TAdvBody =setLowItem("暂无");
						TAdvHead =hline + setItem("主治医生医嘱");
					}else{
						TAdvHead =hline + setItem("主治医生医嘱")
						+TCSS
						+"<table frame='box' align='center'>"
						+"<tr>"
						+"<th style= font-size:"+thfontSize+">编号</th>"
						+"<th style= font-size:"+thfontSize+">医嘱内容</th>"
						+"<th style= font-size:"+thfontSize+">医嘱时效</th>"
						+"<th style= font-size:"+thfontSize+">医嘱类型</th>"
						+"<th style= font-size:"+thfontSize+">医嘱状态</th>"
						+"<th style= font-size:"+thfontSize+">开始时间</th>"
						+"<th style= font-size:"+thfontSize+">结束时间</th>"
						+"<th style= font-size:"+thfontSize+">剂量</th>"
						+"<th style= font-size:"+thfontSize+">途径</th>"
						+"<th style= font-size:"+thfontSize+">频次</th>"
						+"<th style= font-size:"+thfontSize+">医生说明</th>"
						+"</tr>";
					}
					
					TAdvEnd = "</table>"+ENT;
					TAdvnd = TAdvHead+TAdvBody+TAdvEnd;
	
					Ext.getCmp('trancontent').setValue(TEMP+TInsp+TPhoto+TSurg+TCond+TCournd+TAdvnd);
				}
						
			});
						
		}
				//-----------------------------------处治报告查看-----------------------------
		if(sl && sl[0]){		
		var id = sl[0].data.id;
		var st = Ext.getCmp("doc_ReportGrid").getStore();
		//console.log('st:',st);
		st.getProxy().extraParams = { id: id };
		st.load({
			
			callback: function(records, o, suc){
				TDispBody="";//清空
				Ih = 0;
				for(var i = 0;i<records.length; i++){
					var rec = records[i];
					//console.log('id:'+id+"   pid:"+rec.get('pulse'));
					if(id === rec.get('pid')){
						Ih = i;
						sel = records[Ih];
						//console.log("Ih Cond:",Ih);

						TDispBody = TDispBody
								+"<tr>"
								+"<td class = 'onecenter'>"+sel.get('id')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('measure')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('type')+"</td>"
								+"<td class = 'onecenter'>"+sel.get('time')+"</td>"
								+"</tr>";
						}
					};
					if(Ih === 0){
						TDispBody =setLowItem("暂无");
						TDispHead =hline + setItem("处治报告");
					}else{
						TDispHead =hline + setItem("处治报告")
						+TCSS
						+"<table frame='box' align='center'>"
						+"<tr>"
						+"<th style= font-size:"+thfontSize+">编号</th>"
						+"<th style= font-size:"+thfontSize+">处治内容</th>"
						+"<th style= font-size:"+thfontSize+">类型</th>"
						+"<th style= font-size:"+thfontSize+">时间</th>"
						+"</tr>";
					}
					
					TDispEnd = "</table>"+ENT;
					TDispnd = TDispHead+TDispBody+TDispEnd;
					TEND = ENT+ENT+"<div style='font-size: 14px;text-align: left;'>负责人"+ENT+"时间："+NowDate()+"</div><br>";
					Ext.getCmp('trancontent').setValue(TEMP+TInsp+TPhoto+TSurg+TCond+TCournd+TAdvnd+TDispnd+TEND);
					
				}
						
			});
						
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
    	 console.log('SearchPt');
    var gr = Ext.getCmp('id_docNavGrid');
    console.log('gr:'+gr);
    
		gr.getStore().load({
		scope: this,
		callback: function(r, o, suc){
			if(suc && r && r.length){
				var searchCon = Ext.getCmp('id_docSearch').getValue();
				console.log('searchCon:'+searchCon);
				var selectItem = 0;
				
				if(searchCon !== null){
					console.log('r.length:'+r.length);
					for(var i = 0;i<r.length;i++)
					{
//						console.log('ID:'+i+'rfid:'+r[i].data.rfid+'name:'+r[i].data.name);
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
    //----------------


});