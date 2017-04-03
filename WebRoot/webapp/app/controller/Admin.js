Ext.define('Wj.controller.Admin', {
	extend: 'Ext.app.Controller',

	views: [
		'Admin',
		'admin.Nav',
		'Banner',
		'Content',
		'admin.SecMgr',
		'admin.DocMgr',
		'util.Pagingtoolbar',
		'util.AddRecord',
		'admin.AddDoc',
		'admin.NurseMgr',
		'admin.AddNurse',
		'admin.PtStatis',
		'admin.AttdocStatis',
		'admin.BedMgr',
		'admin.VoluMgr',
		'admin.VoluPermMgr',
		'admin.DrugMgr',
		'admin.RTAnalysisMgr',
		'admin.DisaReportMgr',
		'admin.AddInjury',
		'admin.AddDrug',
		'admin.AddDisa',
		'admin.HospitalMgr'
	],

	stores: ['admin.Sector', 'admin.PtStatis','admin.AttdocStatis', 
			 'admin.Beds','user.Doctor', 'user.Nurse','admin.Hospital',
			 'volunteer.Volunteer','volunteer.VolunteerPerm',
			 'drug.Drug','drug.AllDrug','drug.Drugdetail','rtanalysis.RTAnalysis','disareport.DisaReport'],

	refs: [{
		selector: 'adminsecmgr > toolbar > #remove',
		ref: 'secremove'
	}, {
		selector: 'content',
		ref: 'content'
	}, {
		selector: 'bedmgr',
		ref: 'bedmgr'
	}, 
	{
		selector: 'hospitalmgr',
		ref: 'hospitalmgr'
	}, 
	
	//********************
	{
		selector: 'content > admindocmgr',//医生管理
		ref: 'docmgr'
	}, {
		selector: 'admindocmgr > grid > toolbar > #remove',
		ref: 'docremove'
	}, {
		selector: 'admindocmgr > grid > pagebar',
		ref: 'docpagebar'
	}, 
	//********************
	{
		selector: 'content > adminnursemgr',//护士管理
		ref: 'nursemgr'
	},//新增
	{
		selector: 'adminnursemgr > grid > toolbar > #remove',
		ref: 'nurseremove'
	}, {
		selector: 'adminnursemgr > grid > pagebar',
		ref: 'nursepagebar'
	},
	//********************
	{
		selector: 'content > adminptstatis',//病人统计
		ref: 'ptstatis'
	}, {
		selector: 'adminptstatis > grid > toolbar > #remove',
		ref: 'ptstatremove'
	}, {
		selector: 'adminptstatis > grid > pagebar',
		ref: 'ptstatpagebar'
	}, 
	//********************
	
	{
		selector: 'content > adminattdocstatis',//医生统计
		ref: 'attdocstatis'
	}, {
		selector: 'adminattdocstatis > grid > toolbar > #remove',
		ref: 'attdocstatremove'
	}, {
		selector: 'adminattdocstatis > grid > pagebar',
		ref: 'attdocstatpagebar'
	},
	//********************
	{	selector: 'content > adminvolumgr',//志愿者统计
		ref: 'volumgr'
	},
	{	selector: 'content > adminvolupermmgr',//志愿者审核
		ref: 'volupermmgr'
	},
	{
		selector: 'adminvolumgr > grid > toolbar > #remove',
		ref: 'voluremove'
	}, {
		selector: 'adminvolumgr > grid > pagebar',
		ref: 'volupagebar'
	},
	//********************
	{	selector: 'content > admindisareportmgr',//疫情报告
		ref: 'disareportmgr'
	},
	{  selector: 'admindisareportmgr > grid > pagebar',//
		ref: 'disareportpagebar'
	},
	 {
		selector: 'admindisareportmgr > grid > toolbar > #remove',
		ref: 'disareportremove'
	},
	
	//********************
	{	selector: 'content > adminrtanalysismgr',//实时态势分析
		ref: 'rtanalysismgr'
	},
	
	{	selector: 'content > adminrtanalysismgr',//实时态势分析
		ref: 'rtanalysismgr'
	},
	
	 {
		selector: 'adminrtanalysismgr > grid > pagebar',
		ref: 'rtanalysispagebar'
	},	
	//********************
	{	selector: 'content > admindrugmgr',//药品统计
		ref: 'drugmgr'
	},
	{  selector: 'admindrugmgr > grid > pagebar',//药品统计
		ref: 'drugpagebar'
	},
	{
		selector: 'admindrugmgr > grid > toolbar > #remove',
		ref: 'drugremove'
	}
	//********************

	

	],
	
	init: function(){

		console.log('-- Wj.controller.Admin init. --');

		this.control({

			'admin': {
				render: function(){
					this.initContentView(null, { data: { id: 'user_doctor' } });
				}
			},

			'admin > content': {

				boxready: function(t, opt){
					
					var infobar = Ext.getCmp('infobar'),
						date = Ext.Date.format(new Date(), 'Y-m-d, l'),
						info;

					//info = '欢迎你，管理员！' + ' - ' + date;
					info = '欢迎你，管理员！';
					if (infobar) {
						infobar.setText(info);
					}

				},

				add: function(t, c, i){
					console.log('add tab ' + i);
					t.setActiveTab(c);
				},

				tabchange: function(t, nt, ot, opt){
						
					var st = nt.down('grid').getStore();

					st.load({
						scope: nt.down('grid'),
						callback: function(r, o, suc){
							if(suc && r && r.length){
								this.getSelectionModel().select(0);
							}
						}
					});
					
				}
			},

			'adminnav': {
				itemclick: this.initContentView
			},

			'adminsecmgr': {
				selectionchange: function(m, r){
					if(r && r[0]){
						console.log(r[0].data.id);
						this.getSecremove().setDisabled(false);
					} else {
						this.getSecremove().setDisabled(true);
					}
				},

				itemdblclick: function(v, r, i, e){

					if(r)
						this.modifySector(r);
				}
			},

			'bedmgr > #secgrid': {

				afterrender: function(c) {

					c.getStore().load({
						scope: this,
						callback: function(r, o, suc) {

							if(suc && r && r.length) {
								c.getSelectionModel().select(0);
							}

						}
					});
				},

				selectionchange: function(m, r) {

					if (r && r[0]) {
						console.log(r[0].data.id);

						this.getStore('admin.Beds').load({
							params: {
								id: r[0].data.id
							},
							scope: this,
							callback: function(r, o, suc) {

								if (suc && r && r.length) {
									this.getBedmgr().down('#bedgrid').getSelectionModel().select(0);
								}
							}

						});
						
					} else {

						this.getStore('admin.Beds').removeAll();

					}

				}

			},

			'bedmgr > #bedgrid': {

				selectionchange: function(m, r) {

					if (r && r[0]) {
						console.log(r[0].data.id);
						console.log(r[0].data.bedNum);

						this.getBedmgr().down('#bedgrid').down('#delete').setDisabled(false);

					} else {

						this.getBedmgr().down('#bedgrid').down('#delete').setDisabled(true);

					}
				}

			},
			'hospitalmgr > #hospitalgrid': {

				selectionchange: function(m, r) {

					if (r && r[0]) {
						console.log(r[0].data.id);
						//console.log(r[0].data.bedNum);

						this.getHospitalmgr().down('#hospitalgrid').down('#delete').setDisabled(false);

					} else {

						this.getHospitalmgr().down('#hospitalgrid').down('#delete').setDisabled(true);

					}
				},		
			

			},
			'admindocmgr > grid': {

				selectionchange: function(m, r){
					if(r && r[0]){
						console.log(r[0].data.id);
						this.getDocremove().setDisabled(false);
					} else {
						this.getDocremove().setDisabled(true);
					}
				},

				edit: function(edit, e){
					
					Wj.util.editGridRecord(e, Wj.url.EditDoc);

				}
			},

			'adddoc': {
				afterrender: function(c){
					c.down('#accept').on('click', this.confirmAddNewDoc);
				}
			},

			'addnurse': {
				afterrender: function(c){
					c.down('#accept').on('click', this.confirmAddNewNurse);
				}
			},
//-----------2016年4月6日20:25:06新增---------------------
			'addinjury': {
				afterrender: function(c){
					c.down('#accept').on('click', this.confirmAddNewInjury);
				}
			},
//-----------2016年4月7日21:15:47新增------------------------------------
			'adddrug': {
				afterrender: function(c){
					c.down('#accept').on('click', this.confirmAddNewDrug);
				}
			},
/**/			
			'adddisareport': {
				afterrender: function(c){
					c.down('#accept').on('click', this.confirmAddAdminNewDisaReport);
				}
			},
//--------------------------------------------------------------
			'adminnursemgr > grid': {
				selectionchange: function(m, r){
					if(r && r[0]){
						console.log(r[0].data.id);
						this.getNurseremove().setDisabled(false);
					} else {
						this.getNurseremove().setDisabled(true);
					}
				},

				edit: function(edit, e){

					Wj.util.editGridRecord(e, Wj.url.EditNurse);
					
				}
			},

			'adminattdocstatis > grid': {

				afterrender: function(c) {
					
					var grid = c;	
				
					grid.getStore().load({						
						scope: this,
						callback: function(r, o, suc) {
							if(suc && r && r.length) {
								//grid.getSelectionModel().select(0);
							}
						}
					});

				}

			},

			'adminptstatis > grid': {

				afterrender: function(c) {
					
					var grid = c;
					
					grid.getStore().load({
						scope: this,
						callback: function(r, o, suc) {
							if(suc && r && r.length) {
								//grid.getSelectionModel().select(0);
							}
						}
					});
				}
			},
			//--------------新增---------
			'adminvolumgr > grid': {

				selectionchange: function(m, r){
					if(r && r[0]){
						console.log(r[0].data.id);
						this.getVoluremove().setDisabled(false);
					} else {
						this.getVoluremove().setDisabled(true);
					}
				},
				edit: function(edit, e){
					Wj.util.editGridRecord(e, Wj.url.EditNurse);					
				}
			
			},
			//志愿者审核
			'adminvolupermmgr > grid': {

				afterrender: function(c) {
					
					var grid = c;
					
					grid.getStore().load({
						scope: this,
						callback: function(r, o, suc) {
							if(suc && r && r.length) {
								//grid.getSelectionModel().select(0);
								//grid.getSelectionModel().deselect(0);
							}
							//grid.getSelectionModel().deselect(0);
						}
					});
					
					grid.getSelectionModel().deselect(0);
					console.log('grid:',grid.getSelectionModel());
				}
			},
			//药品统计
			'admindrugmgr > grid': {
				afterrender: function(c) {
					var grid = c;					
					grid.getStore().load({
						scope: this,
						callback: function(r, o, suc) {
							if(suc && r && r.length) {
								//grid.getSelectionModel().select(0);
							}
						}
					});
				},
				selectionchange: function(m, r){
					if(r && r[0]){
						console.log(r[0].data.id);
						this.getDrugremove().setDisabled(false);
					} else {
						this.getDrugremove().setDisabled(true);
					}
				},
				edit: function(edit, e){
					Wj.util.editGridRecord(e, Wj.url.EditDrug);					
				}
			
			},
			//实时态势分析
			'adminrtanalysismgr > grid': {
				afterrender: function(c) {
					var grid = c;					
					grid.getStore().load({
						scope: this,
						callback: function(r, o, suc) {
							if(suc && r && r.length) {
								//grid.getSelectionModel().select(0);
							}
						}
					});
				}
			},
			//疫情报告
			'admindisareportmgr > grid': {
				selectionchange: function(m, r){
					if(r && r[0]){
						console.log(r[0].data.id);
						this.getDisareportremove().setDisabled(false);
					} else {
						this.getDisareportremove().setDisabled(true);
					}
				},
				edit: function(edit, e){
					Wj.util.editGridRecord(e, Wj.url.EditDis);	
					Ext.getCmp('grid_disareport').getStore().load({
						scope: Wj.controller.Admin,
						callback: function(r, o, suc) {
							if (suc && r && r.length) {
								this.getDisareportmgr().down('grid').getSelectionModel().select(0);
							}
						}
					});
				}
			
			},
			

		});
	},

	showAdminView: function() {

		var ad = Ext.widget('admin');
		Ext.getCmp('viewport').removeAll(true);
		Ext.getCmp('viewport').add(ad);

	},

	initContentView: function(view, node) {

		switch(node.data.id){
			case 'user_doctor':
				this.initDocMgrView();
				break;
			case 'user_nurse':
				this.initNurseMgrView();
				break;
			//志愿者统计
			case 'statis_volunteer':
				console.log('---statis_volunteer click---');
				this.initVoluMgrView();
				
				break;
				//志愿者审核
			case 'permission_volunteer':
				console.log('---permission_volunteer click---');
				this.initVoluPermMgrView();
				
				break;
				//药品统计			
			case 'statis_drug':
				this.initDrugMgrView();
				break;
				//实时态势分析
			case 'realtime_analysis':
				this.initRTAnalysisMgrView();
				break;
				//疫情报告
			case 'disaster_report':
			console.log('---disaster_report click---');
				this.initDisaReportMgrView();
				break;
		    case 'statis_patient':
				this.initPtStatisView();
				break;
			case 'statis_attdoc':
				this.initAttdocStatisView();
				break;
			default:
				break;
		}

	},

	initDocMgrView: function() {
		
		var d = this.getDocmgr(), c = this.getContent();
		console.log('test:',d);
		if(!d){
			d = Ext.widget('admindocmgr');
			c.add(d);
		} else
			c.setActiveTab(d);
	},

	initNurseMgrView: function() {
		
		var n = this.getNursemgr(), c = this.getContent();
		console.log('getNursemgr: ', n);
		if(!n){
			n = Ext.widget('adminnursemgr');
			c.add(n);
		}
		else
			c.setActiveTab(n);
	},
	initVoluMgrView: function() {
		
		var v = this.getVolumgr();
		var c = this.getContent();
//		console.log('getVolumgr: ', n);
//		console.log('getVolumgr: ', c);
		if(!v){
			v = Ext.widget('adminvolumgr');
			c.add(v);
		}
		else
			c.setActiveTab(v);
	},
	//志愿者审核
	initVoluPermMgrView: function() {
		
		var vp = this.getVolupermmgr(), c = this.getContent();
		if(!vp){
			vp = Ext.widget('adminvolupermmgr');
			console.log('*Volumgr: ', vp);
			c.add(vp);	
			
		}
		else
			c.setActiveTab(vp);
//		var t = Ext.getCmp('vpgrid');
//
//		var m = c.down('grid');
//		var tr = m.getSelectionModel();
//		var ty =tr.deselect(0);
//		console.log('*m: ', m);
//		console.log('*tr: ', tr);
//		console.log('*ty: ', ty);
	
	},

	//药品统计
	initDrugMgrView: function() {
		
		var dg = this.getDrugmgr(), c = this.getContent();
		//dg = this.getView('admin.DrugMgr');
		console.log("drug test dg:",dg);
		if(!dg){
			dg = Ext.widget('admindrugmgr');
			c.add(dg);
			var dg1 = this.getDrugmgr();
			console.log("drug test dg1:",dg1);
		}
		else
			c.setActiveTab(dg);
	},
	//实时态势分析
	initRTAnalysisMgrView: function() {
	
		var ra = this.getRtanalysismgr(), c = this.getContent();

		if(!ra){
			ra = Ext.widget('adminrtanalysismgr');
			c.add(ra);
		}
		else
			c.setActiveTab(ra);
	},
	//疫情报告
	initDisaReportMgrView: function() {
		
		var n = this.getDisareportmgr(), c = this.getContent();

		if(!n){
			n = Ext.widget('admindisareportmgr');
			c.add(n);
		}
		else
			c.setActiveTab(n);
	},
	
	initPtStatisView: function(){
	var ps = this.getPtstatis(), c = this.getContent();
	if(!ps){
			ps = Ext.widget('adminptstatis');
			c.add(ps);
		}
		else
			c.setActiveTab(ps);
	},
	
	initAttdocStatisView: function() {
	var ps = this.getAttdocstatis(), c = this.getContent();
	if(!ps){
			ps = Ext.widget('adminattdocstatis');
			c.add(ps);
		}
		else
			c.setActiveTab(ps);
	},

	initBedMgrView: function(b) {

		Ext.widget('bedmgr').show();

	},
	initHospitalMgrView: function(b) {

		Ext.widget('hospitalmgr').show();

	},
	addSector: function(b) {
		
		Ext.Msg.prompt('添加分区', '请输入要添加的分区名称:', function(btn, t){
			if(btn == 'ok') {
				Ext.Ajax.request({
					// url: 'data/test/admin/sector/create.json',
					url: Wj.url.AddSector,
					method: 'get',
					params: { 'secName': t },
					success: function(r, a){						
						Wj.util.handleRequestSuccess(r, a, function(){
							Ext.getStore('admin.Sector').load();
						});
					},
					failure: Wj.util.handleRequestFailure
				});
			}
		});
	},

	removeSector: function(b) {

		
		console.log('-- removeSector --');
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.RmvSector;
			var par = { id: sl[0].data.id };
				
		Ext.Msg.confirm('提示', '确定删除这个分区吗？', function(btn){

			if(btn == 'yes'){
		
				Wj.util.removeGridRecord(b.up('grid'), Wj.url.RmvSector, function(){
					Ext.Msg.alert('提示', '删除成功！', function(){
						Ext.getStore('admin.Sector').load();
					}, this);
				});}},this);
		
		}

	},
addHospital: function(b) {
		
		Ext.Msg.prompt('添加医院', '请输入要添加的医院名称:', function(btn, t){
			if(btn == 'ok') {
				Ext.Ajax.request({
					// url: 'data/test/admin/sector/create.json',
					url: Wj.url.AddHospital,
					method: 'get',
					params: { 'hospName': t },
					success: function(r, a){						
						Wj.util.handleRequestSuccess(r, a, function(){
							Ext.getStore('admin.Hospital').load();
						});
					},
					failure: Wj.util.handleRequestFailure
				});
			}
		});
	},
	
removeHospital: function(b) {

		
		console.log('-- removeHospital --');
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.RmvHospital;
			var par = { id: sl[0].data.id };
				
		Ext.Msg.confirm('提示', '确定删除这个医院吗？', function(btn){

			if(btn == 'yes'){
		
				Wj.util.removeGridRecord(b.up('grid'), Wj.url.RmvHospital, function(){
					Ext.Msg.alert('提示', '删除成功！', function(){
						Ext.getStore('admin.Hospital').load();
					}, this);
				});}},this);
		
		}

	},
	
	
	addBed: function(b) {
//	var m = this.up('window');
//	console.log('mhh:',m);
		var url = Wj.url.AddBed,
			secId = this.up('window').down('#secgrid').getSelectionModel().getSelection()[0].data.id,
			par = {
				id: secId
			},
			suc;

		suc = function() {

			this.getStore('admin.Beds').load({
				params: {
					id: this.getBedmgr().down('#secgrid').getSelectionModel().getSelection()[0].data.id
				},
				scope: Wj.controller.Admin,
				callback: function(r, o, suc) {

					if (suc && r && r.length) {
						this.getBedmgr().down('#bedgrid').getSelectionModel().select(0);
					}
				}

			});
		};

		Wj.util.pushData.call(Wj.controller.Admin, url, par, suc);

	},

	removeBed: function(b) {

		
		console.log('-- removeBed --');
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.RmvBed;
			var par = { id: sl[0].data.id };
				
		Ext.Msg.confirm('提示', '确定删除这个床位吗？', function(btn){

			if(btn == 'yes'){
		

				Wj.util.removeGridRecord(b.up('grid'), Wj.url.RmvBed, function(){

					console.log('getSelection()[0].data.id',
						Wj.controller.Admin.getBedmgr().down('#secgrid').getSelectionModel().getSelection()[0].data.id);
					
					Ext.Msg.alert('提示', '删除成功！', function(){
						
						this.getStore('admin.Beds').load({
							params: {
								id: this.getBedmgr().down('#secgrid').getSelectionModel().getSelection()[0].data.id
							},
							scope: this,
							callback: function(r, o, suc) {

								if (suc && r && r.length) {
									this.getBedmgr().down('#bedgrid').getSelectionModel().select(0);
								}
							}

						});

					}, Wj.controller.Admin);

				});}},this);
		
		}

	},

	modifySector: function(rec) {
		var r = rec;
		Ext.Msg.prompt('修改分区', '新的分区名称:', function(b, t){
			if(b == 'ok'){
				Ext.Ajax.request({
					// url: 'data/test/admin/sector/update.json',
					url: Wj.url.ModifySector,
					method: 'get',
					scope: Wj.controller.Admin.modifySector,
					params: {
						'sid': r.data.id,
						'secName': t
					},
					success: function(rp, a){
						console.log(a);
						Wj.util.handleRequestSuccess(rp, a, function(){
							Ext.getStore('admin.Sector').load();
						});
					},
					failure: Wj.util.handleRequestFailure
				});
			}
		}, this, false, r.data.secName);
	},

	addDoctor: function(b) {

		Ext.widget('adddoc').show();
	},

	confirmAddNewDoc: function(b, e){
		console.log('-- confirmAddNewDoc --');
		var w = b.up('window');
		var f = w.down('form').getForm();
		Wj.util.confirmEditWindowForm(w, f, Wj.url.AddDoc, function(){
			w.close();
			console.log(Wj.controller.Admin.getDocpagebar());
			Wj.controller.Admin.getDocpagebar().moveFirst();
		});
	},

	removeDoctor: function(b) {

		console.log('-- removeDoctor --');
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.RmvDoc;
			var par = { id: sl[0].data.id };
				
		Ext.Msg.confirm('提示', '确定删除这个医生吗？', function(btn){

			if(btn == 'yes'){
		
				Wj.util.removeGridRecord(b.up('grid'), Wj.url.RmvDoc, function(){
					Ext.Msg.alert('提示', '删除成功！', function(){
						Ext.getStore('user.Doctor').load({
							scope: Wj.controller.Admin,
							callback: function(r, o, suc) {
								if (suc && r && r.length) {
									this.getDocmgr().down('grid').getSelectionModel().select(0);
								}
							}
						});
					}, this);
				});}},this);
		
		}

	},

	addNurse: function(b) {

		Ext.widget('addnurse').show();
	},

	confirmAddNewNurse: function(b, e) {
		console.log('-- confirmAddNewNurse --');
		var w = b.up('window');
		var f = w.down('form').getForm();
		Wj.util.confirmEditWindowForm(w, f, Wj.url.AddNurse, function(){
			w.close();
			console.log(Wj.controller.Admin.getNursepagebar());
			Wj.controller.Admin.getNursepagebar().moveFirst();
		});
	},

	removeNurse: function(b) {

		console.log('-- removeNurse --');
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.RmvNurse;
			var par = { id: sl[0].data.id };
				
		Ext.Msg.confirm('提示', '确定删除这个护士吗？', function(btn){

			if(btn == 'yes'){
		
				Wj.util.removeGridRecord(b.up('grid'), Wj.url.RmvNurse, function(){
					Ext.Msg.alert('提示', '删除成功！', function(){
						Ext.getStore('user.Nurse').load({
							scope: Wj.controller.Admin,
							callback: function(r, o, suc) {
								if (suc && r && r.length) {
									this.getNursemgr().down('grid').getSelectionModel().select(0);
								}
							}
						});
					}, this);
				});}},this);
		
		}

	},
	//志愿者注册 拒绝注册
	denyVoluPerm: function(b) {
		console.log('***b:',b);
		var con = b.up('grid');
		console.log('***con:',con);
		var rows = con.getSelectionModel().getSelection();
		var msg='';
		if(rows.length >0){
			for(var i= 0; i< rows.length;i++){
				msg = msg + rows[i].get('voluName') + '\n';
			}
			console.log(msg);
				Ext.MessageBox.show({
					title:'确定拒绝以下志愿者申请？',
					msg: msg,
					fn:callBack,
					buttons:Ext.Msg.YESNO,
					icon:Ext.Msg.WARNING
				},this);
				function callBack(id,msg){
					console.log('callback id',id);
				if(id =='yes'){
					
					Wj.util.denyCheckBoxGridRecord(b.up('grid'), Wj.url.DenyVoluPerm, function(){
					Ext.getStore('volunteer.VolunteerPerm').load({
						scope: Wj.controller.Admin,
						callback: function(r, o, suc) {
							if (suc && r && r.length) {
								console.log('已拒绝志愿者注册申请！');
								this.getVolupermmgr().down('grid').getSelectionModel().select(0);
							}
						}
					});
					
					});
					
				}
				}
		}else{
			Ext.MessageBox.show({
					title:'提示',
					msg: '未选中记录！请重新选择',
					fn:callBack,
					buttons:Ext.Msg.OK,
					icon:Ext.Msg.WARNING
				},this);
		}
	},
		//志愿者注册 同意注册
	agreeVoluPerm: function(b) {
		
		var con = b.up('grid');
//		console.log('con model',con.getSelectionModel());
		var rows = con.getSelectionModel().getSelection();
		var msg='';
		if(rows.length >0){
			for(var i= 0; i< rows.length;i++){
				msg = msg + rows[i].get('name') + '\n';
			}
			console.log(msg);
				Ext.MessageBox.show({
					title:'确定同意以下志愿者申请？',
					msg: msg,
					fn:callBack,
					buttons:Ext.Msg.YESNO,
					icon:Ext.Msg.WARNING
				},this);
				function callBack(id,msg){
					console.log('callback id',id);
				if(id =='yes'){					
					Wj.util.agreeCheckBoxGridRecord(b.up('grid'), Wj.url.AgreeVoluPerm, function(){
					Ext.getStore('volunteer.VolunteerPerm').load({
						scope: Wj.controller.Admin,
						callback: function(r, o, suc) {
							if (suc && r && r.length) {
								//this.getVolupermmgr().down('grid').getSelectionModel().select(0);
							}
						}
					});
					console.log('已同意志愿者注册申请！');
					});
					
				}
				}
		}else{
			Ext.MessageBox.show({
					title:'提示',
					msg: '未选中记录！请重新选择',
					fn:callBack,
					buttons:Ext.Msg.OK,
					icon:Ext.Msg.WARNING
				},this);
		}
	},
	removeVolu: function(b) {

		console.log('-- removeVolu --');
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.removeVolu;
			var par = { id: sl[0].data.id };
				
		Ext.Msg.confirm('提示', '确定删除这个志愿者吗？', function(btn){

			if(btn == 'yes'){
		

				Wj.util.removeGridRecord(b.up('grid'), Wj.url.removeVolu, function(){
					Ext.Msg.alert('提示', '删除成功！', function(){
						Ext.getStore('volunteer.Volunteer').load({
							scope: Wj.controller.Admin,
							callback: function(r, o, suc) {
								if (suc && r && r.length) {
									//this.getNursemgr().down('grid').getSelectionModel().select(0);
								}
							}
						});
					}, this);
				});
			}},this);
		
		}

		
	},
//实时态势分析
	//添加伤情
	addInjury: function(b) {

		Ext.widget('addinjury').show();
	},
	//确定按钮
	confirmAddNewInjury: function(b, e){
		console.log('-- confirmAddNewInjury --');
		var w = b.up('window');
		var f = w.down('form').getForm();
		Wj.util.confirmEditWindowForm(w, f, Wj.url.AddInjury, function(){
			w.close();		
//			console.log(Wj.controller.Admin.getRtanalysispagebar());
			Wj.controller.Admin.getRtanalysispagebar().moveFirst();
		});
	},

	//删除伤情
	removeInjury: function(b) {

		console.log('-- removeInjury --');
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.RmvInjury;
			var par = { id: sl[0].data.id };
				
		Ext.Msg.confirm('提示', '确定删除这个伤情吗？', function(btn){

			if(btn == 'yes'){
		
				Wj.util.removeGridRecord(b.up('grid'), Wj.url.RmvInjury, function(){
					Ext.Msg.alert('提示', '删除成功！', function(){
						Ext.getStore('rtanalysis.RTAnalysis').load({
							scope: Wj.controller.Admin,
							callback: function(r, o, suc) {
								if (suc && r && r.length) {
									this.getRtanalysismgr().down('grid').getSelectionModel().select(0);
								}
							}
						});
					}, this);
				});
			}},this);
		
		}

	},
	//---------2016年4月7日21:17:04新增---------
	//---------药品统计---添加和删除-------------
	addDrug: function(b) {
		Ext.widget('adddrug').show();
	},
	confirmAddNewDrug: function(b, e) {
		console.log('-- confirmAddNewDrug --');
		var w = b.up('window');
		var f = w.down('form').getForm();
		Wj.util.confirmEditWindowForm(w, f, Wj.url.AddDrug, function(){
			w.close();
			console.log(Wj.controller.Admin.getDrugpagebar());
			Wj.controller.Admin.getDrugpagebar().moveFirst();
		});
	},

	removeDrug: function(b) {

		console.log('-- removeDrug --');
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.RmvDrug;
			var par = { id: sl[0].data.id };
				
		Ext.Msg.confirm('提示', '确定删除这个药品吗？', function(btn){

			if(btn == 'yes'){
		
				Wj.util.removeGridRecord(b.up('grid'), Wj.url.RmvDrug, function(){
					Ext.Msg.alert('提示', '删除成功！', function(){
						Ext.getStore('drug.Drug').load({
							scope: Wj.controller.Admin,
							callback: function(r, o, suc) {
								if (suc && r && r.length) {
									this.getDrugmgr().down('grid').getSelectionModel().select(0);
								}
							}
						});
					}, this);
				});
			}},this);
		
		}

	
	},
		
		addDisaReport: function(b) {
			Ext.widget('adddisareport').show();
		},

		confirmAddAdminNewDisaReport: function(b, e) {
			console.log('-- confirmAddAdminNewDisaReport --');
			var w = b.up('window');
			var f = w.down('form').getForm();
			Wj.util.confirmEditWindowForm(w, f, Wj.url.addDisaReport, function(){
				w.close();
			//	console.log(Wj.controller.Admin.getDisareportpagebar());
				Wj.controller.Admin.getDisareportpagebar().moveFirst();
			});
		},
	removeDisaReport: function(b) {
	
		console.log('-- removeDisaReport --');
		var sl = b.up('grid').getSelectionModel().getSelection();

		if(sl && sl[0]){

			var url = Wj.url.removeDisaReport;
			var par = { id: sl[0].data.id };
				
		Ext.Msg.confirm('提示', '确定删除这个疫情吗？', function(btn){

			if(btn == 'yes'){
		
				Wj.util.removeGridRecord(b.up('grid'), Wj.url.removeDisaReport, function(){
					Ext.Msg.alert('提示', '删除成功！', function(){
						Ext.getCmp('grid_disareport').getStore().load({
							scope: Wj.controller.Admin,
							callback: function(r, o, suc) {
								if (suc && r && r.length) {
									this.getDisareportmgr().down('grid').getSelectionModel().select(0);
								}
							}
						});
					}, this);
				});
			}},this);
		
		}
		
	}

	

})