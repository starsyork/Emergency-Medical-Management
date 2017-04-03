Ext.define('Wj.controller.Nurse', {
	extend: 'Ext.app.Controller',

	views: [
		'Nurse',
		'Banner',
		'Content',
		'nurse.PtForm',
		'nurse.Nav',
		'nurse.PtAdvice',
		'nurse.PtCondition',
		'nurse.Report',
		'nurse.PtUndealt',
		'nurse.PtOtherUndealt',
		'nurse.PtInspection',
		'nurse.AdviceWin',
		'nurse.UndealtWin',
		'nurse.DisaReport',
		'nurse.DutyManage',
		'nurse.NurseAddDuty',
		'nurse.DutyManage',
		'nurse.PsyAssess',
		'nurse.VolunteerTask',
		'nurse.AddTask',
		'nurse.AddDisa',
		'nurse.PtPhoto',
		'imgviewer.imageviewer'
	],

	stores: [
		'nurse.Nav',
		'nurse.PtAdvice',
		'nurse.NewAdvice',
		'nurse.PtCondition',
		'Report',
		'nurse.PtUndealt',
		'nurse.PtOtherUndealt',
		'nurse.NewAdvice',
		'nurse.UnAllocBed',
		'nurse.Doctors',
		'BedStatus',
		'inspection.Main',
		'inspection.Detail',
		'nurse.DisaReport',
		'nurse.DutyManage',
		'nurse.PsyAssess',
		'nurse.VolunteerTask',
		'admin.Sector',//获取分区
		'surgydoctor.SurgyInspectMain'
	],

	refs: [{
		selector: 'nurse > content',
		ref: 'content'
	}, {
		selector: 'nurse > container > nursenav',
		ref: 'nursenav'
	}, {
		selector: 'nurse > container > #ptform',
		ref: 'navptform'
	}, {
		selector: 'nurse > content > nurseptadv',
		ref: 'ptadv'
	}, {
		selector: 'nurse > content > nurseptadv > grid',
		ref: 'advgrid'
	}, {
		selector: 'nurse > content > nurseptadv > form',
		ref: 'advform'
	}, {
		selector: 'nurse > content > nurseptinspection',
		ref: 'nurseptinspection'
	}, {
		selector: 'nurse > content > nurseptinspection > #main_grid',
		ref: 'nursemaing'
	}, {
		selector: 'nurse > content > nurseptinspection > #detail_grid',
		ref: 'nursedetailg'
	}, {
		selector: 'nurse > content > nurseptcondition',
		ref: 'ptcondition'
	}, {
		selector: 'nurse > content > nurseptcondition > form',
		ref: 'ptcondfom'
	}, {
		selector: 'nurse > content > nursepsyassess > grid',
		ref: 'ptcondgrid'
	}, {
		selector: 'ptundealt',
		ref: 'ptundealt'
	}, {
		selector: 'ptundealt > grid',
		ref: 'ptundealtgird'
	}, {
		selector: 'ptundealt > form',
		ref: 'ptundealtform'
	}, {
		selector: 'ptotherundealt',
		ref: 'ptotherundealt'
	}, {
		selector: 'ptotherundealt > grid',
		ref: 'ptotherundealtgird'
	}, {
		selector: 'ptotherundealt > form',
		ref: 'ptotherundealtform'
	}, {
		selector: 'undealtwin',
		ref: 'undealtwin'
	}, {
		selector: 'undealtwin > form',
		ref: 'undealtform'
	}, {
		selector: 'advwin',
		ref: 'advwin'
	}, {
		selector: 'advwin > form',
		ref: 'advwinform'
	}, 
	
	
	{
		selector: 'content > nursedutymanage',
		ref: 'nursedutymanage'
	},{
		selector: 'nursedutymanage > grid > pagebar',
		ref: 'nursedutymanagepagebar'
	}, {
		selector: 'nursedutymanage > grid > toolbar > #remove',
		ref: 'nursedutymanageremove'
	},
	
	{
		selector: 'nurseaddduty',
		ref: 'nurseaddduty'
	},
	
	
	{
		selector: 'content > nursedisareport',
		ref: 'nursedisareport'
	},{  selector: 'nursedisareport > grid > pagebar',//
		ref: 'nursedisareportpagebar'
	}, {
		selector: 'nursedisareport > grid > toolbar > #remove',
		ref: 'nursedisareportremove'
	},
	
	
	{
		selector: 'content > nursevolunteertask > grid',
		ref: 'nursevolunteertask' 
	},{  
		selector: 'nursevolunteertask > grid > pagebar',//
		ref: 'nursevolunteertaskpagebar'
	},{
		selector: 'nursevolunteertask > grid > toolbar > #remove',
		ref: 'nursevolunteertaskremove'
	},
	
	
		{
		selector: 'nurseaddtask',
		ref: 'nurseaddtask'
	},
		{
		selector: 'nurseaddtask > form',
		ref: 'nurseaddtaskform'
	},
	{
		selector: 'nurse > content > nurseptphoto',
		ref: 'nurseptphoto'
	},{
		selector: 'nurse > content > nurseptphoto > #ptphotogrid',//伤员影像数据
		ref: 'nurseptphotogrid'
	}
	
	],

	init: function(){

		this.control({

			'nurse': {

				afterrender: function(){

					this.initContentView();

				}

			},

			'nurse > content': {

				boxready: function(t, opt){

					console.log('------------loginData-----------------');
					console.log(Wj.tmp.loginData);
					var d = Wj.tmp.loginData;
					var cp = Wj.cp;

					if(d){

						cp.set('Wj-userName', d.userName);
						cp.set('Wj-roleStr', d.roleStr);
						cp.set('Wj-sector', d.sector);
						cp.set('Wj-nurseId', d.id);

					} else {

						var d = {
							userName: cp.get('Wj-userName'),
							roleStr: cp.get('Wj-roleStr'),
							sector: cp.get('Wj-sector'),
							id: cp.get('Wj-nurseId')
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
					this.getContent().setActiveTab(this.getPtcondition());

				},

				tabchange: function(t, nt, ot, opt){

					var sl = this.getNursenav().getSelectionModel().getSelection();
					if(sl && sl[0]){
						
						var id = sl[0].data.ptid;
						var name = sl[0].data.name;
						var st = nt.down('grid').getStore();

						st.getProxy().extraParams = { id: id };	// patient's id
						st.load({
							scope: nt.down('grid'),
							callback: function(r, o, suc){
								if(suc && r && r.length){
									this.getSelectionModel().select(0);
								}
							}
						});

						if(nt.down('#addcondform')){

							var fm = nt.down('#addcondform').getForm();
							fm.findField('id').setValue(id);
							fm.findField('name').setValue(name);

						}

					}
					
				}

			},

			'nurse > container > nursenav': {

				afterrender: function(){

					this.testUndealt();
					setTimeout(this.testNewAdvice, 8000);

					this.getStore('nurse.Nav').load({
						scope: this,
						callback: function(r, o, suc){
							if(suc && r && r.length){
								this.getNursenav().getSelectionModel().select(0);
							}
						}
					});
				},

				selectionchange: function(m, r){
					
					if(r && r[0]){

						this.getNursenav().down('#ttb').down('#rmvpt').setDisabled(false);

						this.getNavptform().loadRecord(r[0]);

						var id = r[0].data.ptid,
							name = r[0].data.name,
							ptfm = this.getPtcondfom(),
							fm, con, activeTab, gr;

						if (id) {

							con = this.getContent();
							activeTab = con.getActiveTab();
							gr = activeTab.down('grid');

							if (gr) {

								console.log(id);

								gr.getStore().getProxy().extraParams = { id: id };
								gr.getStore().loadPage(1, {
									scope: gr,
									callback: function(r, o, suc){
										if(suc && r && r.length){
											gr.getSelectionModel().select(0);
										} else {
											gr.getStore().removeAll();
										}
									}
								});

								if(activeTab.down('#addcondform')){

									fm = activeTab.down('#addcondform').getForm();
									fm.findField('id').setValue(id);
									fm.findField('name').setValue(name);

								}
							
							}

							ptfm.getForm().reset();
							ptfm.getForm().findField('name').setValue(name);
							ptfm.getForm().findField('ptid').setValue(id);

						} else {

							con = this.getContent();
							activeTab = con.getActiveTab();
							gr = activeTab.down('grid');
							gr.getStore().removeAll();

							this.getStore('nurse.PtAdvice').getProxy().extraParams = {};
						}

					} else {
						this.getNursenav().down('#ttb').down('#rmvpt').setDisabled(true);
					}
				},

				edit: function(e, event){
					console.log(event);
					
					var url = Wj.url.ModifyBedState;
					var sta;
					if(event.newValues.statusStr == '空')
						sta = 0;
					else
						sta = 2;

					var par = { id: event.newValues.bedNum, status: sta };
					var r = event.record;

					Wj.util.pushData.call(this, url, par, function(){
						Ext.Msg.alert('提示', '修改成功! ', function(){
							// r.commit();
							this.refreshPtNav(this.getNursenav().getSelectionModel().getSelection()[0].index);
						}, this);
					}, function() {
						this.refreshPtNav(this.getNursenav().getSelectionModel().getSelection()[0].index);
					});

				}

			},

			'nurse > content > nurseptadv > grid': {

				selectionchange: function(m, r){

					if(r && r[0]){

						var fm = this.getAdvform();
						fm.getForm().reset();

						this.getAdvform().getForm().loadRecord(r[0]);

						if(r[0].data.state == '已执行' || r[0].data.state == '已停止'){
							
							fm.down('fieldset').down('#fa_exNote').setDisabled(true);
							fm.down('button').setDisabled(true);

						} else {

							fm.down('fieldset').down('#fa_exNote').setDisabled(false);
							fm.down('button').setDisabled(false);

						}

					}

				}
			},

			'nurse > content > nurseptcondition > grid': {

				selectionchange: function(m, r){

					if(r && r[0]){

						var fm = this.getPtcondfom(),
							data = this.getNursenav().getSelectionModel().getSelection()[0].data,
							name = data.name,
							ptid = data.ptid;

						fm.getForm().reset();

						fm.getForm().loadRecord(r[0]);
						fm.getForm().findField('name').setValue(name);
						fm.getForm().findField('ptid').setValue(ptid);

					}
				}
			},

			'nurse > content > nurseptinspection > #main_grid': {

				selectionchange: function(m, r){

					if(r && r[0]){

						console.log(this);
						console.log(this.getNursedetailg());

						id = r[0].data.id,
							dg = this.getNursedetailg(),
							store = dg.getStore();

						console.log('in #main_grid, id:', id);
						
						// adjust paging tool.
						
						store.getProxy().extraParams = {
							id: id
						};

						dg.down('pagebar').moveFirst();
					
					} else {

						this.getNursedetailg().getStore().removeAll();
						
					}
				}
			},

			'ptundealt > grid': {

				afterrender: function(t){

					var gr = t;

					gr.getStore().load({
						scope: this,
						callback: function(r, o, suc){
							gr.getSelectionModel().select(0);
						}
					});
				},

				selectionchange: function(m, r){

					if(r && r[0]){

						var fm = this.getPtundealtform().getForm();
						fm.loadRecord(r[0]);

						var cob = this.getPtundealtform().down('#alloc_bed').down('combobox');

						cob.getStore().load({
							scope: this,
							callback: function(r, o, suc){

								cob.setValue(r[0].data.bedNum);
							}
						});
					}

				}
			},

			'ptotherundealt > grid': {

				afterrender: function(t){

					var gr = t;

					gr.getStore().load({
						scope: this,
						callback: function(r, o, suc){
							gr.getSelectionModel().select(0);
						}
					});
				},

				selectionchange: function(m, r){

					if(r && r[0]){

						var fm = this.getPtotherundealtform().getForm();
						fm.loadRecord(r[0]);

						var cob = this.getPtotherundealtform().down('#alloc_bed').down('combobox');

						cob.getStore().load({
							scope: this,
							callback: function(r, o, suc){

								cob.setValue(r[0].data.bedNum);
							}
						});
					}
				}
			},

			'ptundealt > form > #alloc_bed > combobox': {

				afterrender: function(t, opt) {
					t.getStore().load({
						scope: this,
						callback: function(r, op, suc) {
							if(r[0])
								t.select(r[0]);
						}
					});
				},

				select: function(cob, rec, opt){
					console.log(rec);
				}
			},

			'ptotherundealt > form > #alloc_bed > combobox': {

				afterrender: function(t, opt) {
					t.getStore().load({
						scope: this,
						callback: function(r, op, suc) {
							if(r[0])
								t.select(r[0]);
						}
					});
				},

				select: function(cob, rec, opt){
					console.log(rec);
				}
			},

			'undealtwin': {

				boxready: function(t, opt) {

					t.setPosition(window.innerWidth - 320, window.innerHeight - 330);
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

			'undealtwin > grid': {

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

			'advwin': {

				boxready: function(t, opt) {

					t.setPosition(window.innerWidth - 930, window.innerHeight - 330);
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

			'advwin > grid': {

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
			
			'nursevolunteertask > grid': {
				selectionchange: function(m, r){
					if(r && r[0]){
						console.log(r[0].data.id);
						this.getNursevolunteertaskremove().setDisabled(false);
					} else {
						this.getNursevolunteertaskremove().setDisabled(true);
					}
				},
				edit: function(edit, e){
					Wj.util.editGridRecord(e, Wj.url.editVolunteerTask);					
				}
			
			},

		'nursedisareport > grid': {
				selectionchange: function(m, r){
					if(r && r[0]){
						console.log(r[0].data.id);
						this.getNursedisareportremove().setDisabled(false);
					} else {
						this.getNursedisareportremove().setDisabled(true);
					}
				},
				edit: function(edit, e){
					Wj.util.editGridRecord(e, Wj.url.EditDis);					
				}
			
			},
				
			'nurseadddisareport': {
				afterrender: function(c){
					c.down('#accept').on('click', this.confirmAddNurseNewDisaReport);
				}
			},
			

			'nurse > content > nursepsyassess > grid': {
				
				selectionchange: function(m, r){
					console.log("111"+1111);
					if(r && r[0]){

						var fm = this.getPtcondfom(),
							data = this.getNursenav().getSelectionModel().getSelection()[0].data,
							//name = data.name,
							ptid = data.ptid;
						console.log("ptid1111"+	ptid);
						fm.getForm().reset();

						fm.getForm().loadRecord(r[0]);
						//fm.getForm().findField('name').setValue(name);
						fm.getForm().findField('ptid').setValue(ptid);
						console.log("ptid2222"+	fm.getForm().findField('ptid').setValue(ptid));
					}
				}
			},
			
			
			'nursedutymanage > grid': {
				selectionchange: function(m, r){
					if(r && r[0]){
						console.log(r[0].data.id);
						this.getNursedutymanageremove().setDisabled(false);
					} else {
						this.getNursedutymanageremove().setDisabled(true);
					}
				},
				edit: function(edit, e){
					Wj.util.editGridRecord(e, Wj.url.EditDuty);					
				//
				}
			
			},
			
			//*********************************** VOLUNTEER   TASK   ***********************************
			
			
			/*	
			'nurse > content > volunteertask > grid': {

				selectionchange: function(m, r){

					if (r && r[0]) {

						this.getVolunteertask().down('toolbar').down('#remove').setDisabled(false);						

						this.getVolunteertask().getGrid().loadRecord(r[0]);
					
					} else {

						this.getVolunteertask().down('toolbar').down('#remove').setDisabled(true);

					}
				}
			},
				
		*/
			//--------------2016年5月11日21:14:51------------------
				//影像数据 表格
			'nurse > content > nurseptphoto > #ptphotogrid': {
//					afterrender: function(){
//						var th = this.getNursenav().getSelectionModel().getSelection();
//						if(th.length > 0){
//						}
//					this.getStore('surgydoctor.SurgyInspectMain').getProxy().extraParams = {
//						all: false
//					};
//
//					this.getStore('surgydoctor.SurgyInspectMain').load({
//						scope: this,
//						callback: function(r, o, suc){
//
//							if(suc && r && r.length){
//								this.getNurseptphotogrid().getSelectionModel().select(0);
//							}
//						}
//					});
//
//				},

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
							console.log('photodec:',r[0].data.illustration);
							
							//刷新 影像数据界面
							var photosrc = r[0].data.url;//影像图片 地址
							var photodec = r[0].data.illustration;//影像图片 描述
							console.log('photosrc:',r[0].data.url);
						    var oa = Ext.ComponentQuery.query('#nursephotopanel')[0];
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
						   var dc = Ext.ComponentQuery.query('#nursephotocontent')[0]; 
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
		
		});
		
	},
	
	
			
//*******************************************ZCJ END ****************************************************
	showNurseView: function(){

		console.log('-- Controller Nurse : ShowDocView --');

		var nur = Ext.widget('nurse');
		Ext.getCmp('viewport').removeAll(true);
		Ext.getCmp('viewport').add(nur);

	},

	initContentView: function(){

		this.initReportTab();
		this.initPtInspectionTab();
		this.initPtPhotoTab();
		this.initPtAdvTab();
		this.initPtConditionTab();
		this.initDisaReportTab();
		// this.InitPtAdviceTab();
		this.initDutyManageTab();//交接班管理
		this.initPsyAssessTab();//心理评估
		this.initVolunteerTaskTab();//志愿者任务发布

	},

	testUndealt: function(){

		var url = Wj.url.testUndealt;

		Ext.Ajax.request({
			url: url,
			method: 'get',
			timeout: 15000,
			scope: Wj.controller.Nurse,
			
			success: function(r, a){
				Wj.util.handleRequestSuccess(r, a, function(){

					Wj.util.t = window.setTimeout(Wj.controller.Nurse.testUndealt, 15 * 1000);

					var bt, obt, form, win,
						iu = arguments[0].results[0].isUndealt,
						controller = Wj.controller.Nurse;

					if(iu !== 0) {

						controller.showUndealtWin();
						
						form = controller.getUndealtform();
						bt = form.down('#t_u_btn');
						obt = form.down('#t_o_u_btn');

					} else {

						win = controller.getUndealtwin();
						if(win) {
							win.collapse();
							win.hide();
						}

					}

					switch (iu) {
												
						case 1:     	// 本分区有，其他分区没有
						bt.setIcon('../webapp/icon/user_add.png');
						bt.setText('未分配-本分区');
						bt.setDisabled(false);

						obt.setIcon('../webapp/icon/user_gray.png');
						obt.setText('无');
						obt.setDisabled(true);
						break;
						
						case 2:     	// 本分区没有，其他分区有
						bt.setIcon('../webapp/icon/user_gray.png');
						bt.setText('无');
						bt.setDisabled(true);

						obt.setIcon('../webapp/icon/user_add.png');
						obt.setText('未分配-其他分区');
						obt.setDisabled(false);
						break;
						
						case 3:     	// 本分区有，其他分区有
						bt.setIcon('../webapp/icon/user_add.png');
						bt.setText('未分配-本分区');
						bt.setDisabled(false);

						obt.setIcon('../webapp/icon/user_add.png');
						obt.setText('未分配-其他分区');
						obt.setDisabled(false);
						break;

						default:
						break;					
					}

				});
			},

			failure: function(){

				Wj.util.t = window.setTimeout(Wj.controller.Nurse.testUndealt, 15 * 1000);

				Wj.util.HandleRequestFailure();
			}
		});

	},

	testNewAdvice: function() {

		var url = Wj.url.testNewAdvice;

		Ext.Ajax.request({
			url: url,
			method: 'get',
			timeout: 15000,
			scope: Wj.controller.Nurse,
			
			success: function(r, a){
				Wj.util.handleRequestSuccess(r, a, function(){

					Wj.util.t = window.setTimeout(Wj.controller.Nurse.testNewAdvice, 15 * 1000);

					var res = arguments[0].results[0].isNewAdvice,
						controller = Wj.controller.Nurse,
						win;

					if (res) {

						controller.showAdviceWin();
					
					} else {

						win  = controller.getAdvwin();

						if(win) {
							win.collapse();
							win.hide();
						}
					}

				});
			},

			failure: function(){

				Wj.util.t = window.setTimeout(Wj.controller.Nurse.testNewAdvice, 15 * 1000);

				Wj.util.HandleRequestFailure();
			}
		});

	},


	handleUndealt: function(b){

		Ext.widget('ptundealt').show();

	},

	handleOtherUndealt: function(b) {

		Ext.widget('ptotherundealt').show();
	
	},

	showUndealtWin: function() {

		var win = this.getUndealtwin();

		if(!win) {
			Ext.widget('undealtwin').show();
		} else {
			win.show();
			win.expand();
			win.down('grid').getStore().load();
		}

	},

	showAdviceWin: function() {

		var win = this.getAdvwin();

		if(!win) {
			Ext.widget('advwin').show();
		} else {
			win.show();
			win.expand();
			win.down('grid').down('pagebar').moveFirst();
		}

	},

	executeAdvice: function(b){

		var sl = b.up('form').up('panel').down('grid').getSelectionModel().getSelection();
		Wj.tmp.i = sl[0].index;
		console.log(sl);

		var f = b.up('form');
		var param = {
			id: f.getForm().findField('id').value,
			note: f.getForm().findField('exNote').value
		};
		var url = Wj.url.ExecuteAdvice;

		Wj.util.pushData.call(this, url, param, function(){
			Ext.Msg.alert('提示', '执行成功！', function(){
				this.getStore('nurse.PtAdvice').load({
					scope: this,
					callback: function(){
						this.getAdvgrid().getSelectionModel().select(Wj.tmp.i);
						this.getAdvform().getForm().findField('exNote').reset();
						Wj.tmp.i = undefined;
					}
				});
			}, Wj.controller.Nurse);
		});

	},

	initPtAdvTab: function(){

		var c = this.getContent();
		var inf = Ext.widget('nurseptadv');
		c.add(inf);

	},

	initPtConditionTab: function(){

		var c = this.getContent();
		var con = Ext.widget('nurseptcondition');
		c.add(con);

	},

	initReportTab: function(){

		var c = this.getContent();
		var res = Ext.widget('nursereport');
		c.add(res);

	},
//
	initPtInspectionTab: function(){
		
		var c = this.getContent();
		var ins = Ext.widget('nurseptinspection');
		c.add(ins);
		
	},
	//
	//疫情报告
		initDisaReportTab: function(){
		
		var c = this.getContent();
		var ins = Ext.widget('nursedisareport');
		c.add(ins);
		
	},
	//交接班 管理
		initDutyManageTab: function(){
		var c = this.getContent();
		var ins = Ext.widget('nursedutymanage');
		c.add(ins);
		
	},
	//心理评估查看
		initPsyAssessTab: function(){
		var c = this.getContent();
		var ins = Ext.widget('nursepsyassess');
		c.add(ins);
		
	},
		//志愿者任务发布
		initVolunteerTaskTab: function(){
		var c = this.getContent();
		var ins = Ext.widget('nursevolunteertask');
		c.add(ins);
		
	},
	//----2016年5月11日21:18:00--------
		//影像数据
	initPtPhotoTab: function(){
		
		var c = this.getContent();
		var ins = Ext.widget('nurseptphoto');
		c.add(ins);
		
	},
	addCondition: function(b){

		var f = b.up('container').up('fieldset').up('form'),
			fm = f.getForm(),
			url = Wj.url.AddPtCondition;

		Wj.util.confirmEditOrAddForm('add', fm, url, function(){

			Ext.Msg.alert('提示', '添加成功！', function(){
				this.getPtcondition().down('grid').down('pagebar').moveFirst();
			}, Wj.controller.Nurse);

		});
	},

	editCondition: function(b) {

		var f = b.up('container').up('fieldset').up('form'),
			fm = f.getForm(),
			url = Wj.url.EditPtCondition;

		Wj.util.confirmEditOrAddForm('edit', fm, url, function(){

			Ext.Msg.alert('提示', '修改成功！', function(){
				this.getPtcondition().down('grid').down('pagebar').moveFirst();
			}, Wj.controller.Nurse);

		});
	},

	allocBedForUndealt: function(b) {

		var url = Wj.url.AllocBed,
			id = b.up('form').getForm().findField('id').value,
			bedComb = b.up('form').down('#bed_num'),
			docComb = b.up('form').down('#doc_list');

		Wj.controller.Nurse.allocBed(url, id, bedComb, docComb);
	},

	allocBedForOtherUndealt: function(b) {

		var url = Wj.url.AllocBedA,
			id = b.up('form').getForm().findField('id').value,
			bedComb = b.up('form').down('#bed_num'),
			docComb = b.up('form').down('#doc_list');

		Wj.controller.Nurse.allocBed(url, id, bedComb, docComb);
	},

	allocBed: function(url, id, bedComb, docComb){

		console.log('bed, doc: ----> ', bed, doc);
		var bedc = bedComb,
			docc = docComb,
			bed = bedComb.getValue(),
			doc = docComb.getValue();

		var par = { id: id, bedNum: bed, docId: doc };

		Wj.util.pushData.call(this, url, par, function(){

			Ext.Msg.alert('提示', '分配成功', function(){
				console.log('this:', this);

				bedc.up('window').down('grid').getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(r && r[0]){

							console.log(r);
							bedc.up('window').down('grid').getSelectionModel().select(0);

						} else {
							Ext.Msg.alert('提示', '所有伤员已分配！', function(){

								bedc.up('window').close();
								this.getUndealtwin().collapse();

							}, this);
						}

						this.getNursenav().getStore().load();
					}
				});

				bedc.clearValue();
				bedc.getStore().load();

			}, this);

		})

	},

	rmvPatient: function(b){

		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.RmvPatient;
			var par = { id: sl[0].data.ptid };

			Ext.Msg.confirm('提示', '确定对 ' + sl[0].data.name + ' 进行出院操作吗？', function(btn){

				if(btn == 'yes'){

					Wj.util.pushData.call(this, url, par, function(res){
						Ext.Msg.alert('提示', '出院成功！', function(){
							this.getNursenav().getStore().load({
								scope: this,
								callback: function(r, o, suc){
									if(r && r[0]) {
										this.getNursenav().getSelectionModel().select(0);
									}
								}
							});
						}, Wj.controller.Nurse);
					});

				}

			}, this);
		}
	},

	handleConfirmNewAdv: function(b) {

		var url = Wj.url.ConfirmNewAdvice,
			controller = Wj.controller.Nurse,
			navgrid = controller.getNursenav(),
			sl, win;

		win = controller.getAdvwin();
		win.collapse();
		
		Wj.util.pushData.call(this, url, null, function(){

			sl = navgrid.getSelectionModel().getSelection();

			if(!sl || !sl[0]) {
				navgrid.getSelectionModel().select(0);
			}

			controller.getContent().setActiveTab(controller.getPtadv());
			win.hide();

		})

	},

	refreshPtNav: function(i) {

		var index = i;
		console.log(index);

		this.getStore('nurse.Nav').load({
			scope: this,
			callback: function(r, o, suc){
				if(suc && r && r.length){
					this.getNursenav().getSelectionModel().select(index);
				}
			}
		});
	},
	//-------交接班-----------
		addDuty: function(b){

		Ext.widget('nurseaddduty').show();

	},

	confirmAddDuty: function(b){

		Wj.util.confirmEditForm(b.up('window').down('form').getForm(), Wj.url.NurseAddDuty, function(){	
			Ext.Msg.alert('提示', '添加成功！', function(){
				this.getNurseaddduty().close();
				Ext.getCmp('grid_dutymanage').getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getNursedutymanagegrid().getSelectionModel().select(0);
						}
					}
				});
			}, Wj.controller.Nurse);
		})

	},
		addTask: function(b){

		Ext.widget('nurseaddtask').show();

	},
		confirmAddTask: function(b){
		var t = b.up('window').down('form');
		console.log("t",t);
		Wj.util.confirmEditForm(b.up('window').down('form').getForm(), Wj.url.addVolunteerTask, function(){	
			Ext.Msg.alert('提示', '添加成功！', function(){
		
				this.getNurseaddtask().close();
			//	this.getAddtask().close();
				this.getNursevolunteertask().getStore().load({
					scope: this,
					callback: function(r, o, suc){
						if(suc && r && r.length) {
							this.getNursevolunteertask().getSelectionModel().select(0);
						}
					}
				});
			}, Wj.controller.Nurse);
		});

	},
	
	removeTask: function(b){
		
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.RemoveVolunteerTask;
			var par = { id: sl[0].data.id };
				
		Ext.Msg.confirm('提示', '确定删除这个任务吗？', function(btn){

			if(btn == 'yes'){
		
		Wj.util.removeGridRecord(b.up('toolbar').up('grid'), Wj.url.RemoveVolunteerTask , function() {		
			Ext.Msg.alert('提示', '删除成功！', function() {
				this.getVolunteertask().down('pagebar').moveFirst();
			}, Wj.controller.Nurse);	
		});
	
			}},this);
		
		}
	},
	
	addDisaReport: function(b) {
		Ext.widget('nurseadddisareport').show();
	},

	confirmAddNurseNewDisaReport: function(b, e) {
		console.log('-- confirmAddNurseNewDisaReport --');
		var w = b.up('window');
		var f = w.down('form').getForm();
		Wj.util.confirmEditWindowForm(w, f, Wj.url.addDisaReport, function(){
			w.close();
			console.log(Wj.controller.Nurse.getDisaReportpagebar());
			Ext.getCmp('grid_disareport').getStore().load({
				scope: Wj.controller.Nurse,
				callback: function(r, o, suc) {
					if (suc && r && r.length) {
						Wj.controller.Nurse.getDisaReportpagebar().moveFirst();
					}
				}
			});
			
		});
	},
removeNurseDisaReport: function(b) {
	
	console.log('-- removeNurseDisaReport --');
	var sl = b.up('grid').getSelectionModel().getSelection();

	if(sl && sl[0]){

		var url = Wj.url.removeDisaReport;
		var par = { id: sl[0].data.id };
			
	Ext.Msg.confirm('提示', '确定删除这个任务吗？', function(btn){

		if(btn == 'yes'){
	
			Wj.util.removeGridRecord(b.up('grid'), Wj.url.removeDisaReport, function(){
				Ext.Msg.alert('提示', '删除成功！', function(){
					Ext.getCmp('grid_dutymanage').getStore().load({
						scope: Wj.controller.Nurse,
						callback: function(r, o, suc) {
							if (suc && r && r.length) {
								this.getDisareportmgr().down('grid').getSelectionModel().select(0);
							}
						}
					});
				}, this);
			});}},this);
	
	}
},


removeDuty: function(b) {
	
	console.log('-- removeDuty --');
	var sl = b.up('grid').getSelectionModel().getSelection();

	if(sl && sl[0]){

		var url = Wj.url.removeDuty;
		var par = { id: sl[0].data.id };
			
	Ext.Msg.confirm('提示', '确定删除这个任务吗？', function(btn){

		if(btn == 'yes'){
	
			Wj.util.removeGridRecord(b.up('grid'), Wj.url.removeDuty, function(){
				Ext.Msg.alert('提示', '删除成功！', function(){
					Ext.getCmp('grid_dutymanage').getStore().load({
						scope: Wj.controller.Nurse,
						callback: function(r, o, suc) {
							if (suc && r && r.length) {
								this.getDisareportmgr().down('grid').getSelectionModel().select(0);
							}
						}
					});
				}, this);
			});}},this);
	
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
		console.log('loginDate:',d.userName);
		return d.userName;
	
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


})