Ext.ns('Wj', 'Wj.controller', 'Wj.consts', 'Wj.error', 'Wj.util', 'Wj.url', 'Wj.tmp','Wj.all','Wj.surgyall');

Wj.cp = Ext.create('Ext.state.CookieProvider', {
	expires: new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 7))
});

Ext.application({
	
	name: 'Wj',
	appFolder: 'app',

	views: ['Viewport', 'admin.SecMgr', 'admin.DocMgr', 'admin.PtStatis','admin.AttdocStatis','util.Pagingtoolbar'],
	models: ['user.Doctor', 'user.Nurse', 'admin.Sector','admin.PtStatis','admin.AttdocStatis'],
	stores: ['user.Type', 'nav.Admin', 'admin.Sector','admin.PtStatis','admin.AttdocStatis'
			 //'volunteer.Volunteer','volunteer.VolunteerPerm',
			// 'drug.Drug','rtanalysis.RTAnalysis','disareport.DisaReport'
			 ],
	controllers: ['User', 'Admin', 'Doctor', 'Nurse','ExamDoctor','SurgyDoctor'],

	launch: function() {

		Wj.controller.User = this.getController('User');
		Wj.controller.Admin = this.getController('Admin');
		Wj.controller.Doctor = this.getController('Doctor');
		Wj.controller.Nurse = this.getController('Nurse');
		Wj.controller.ExamDoctor = this.getController('ExamDoctor');
		Wj.controller.SurgyDoctor = this.getController('SurgyDoctor');
		Wj.all=false;
		Wj.surgyall=false;
		Ext.create('Wj.view.Viewport', {
			layout: 'fit',
			items:[{
				xtype: 'panel',
				border: false,
				bodyStyle: {
					'background-image': 'url(img/main-back6.png)',
					// 'background-color': '#DFE9F6',
					'background-position': 'center',
					'background-repeat': 'repeat'
				}
			}]
		});

		Ext.EventManager.addListener(Ext.getBody(), 'keydown', function(e) {
			console.log(e);
			console.log(e.getTarget());
			console.log(e.getTarget().type);
			var t = e.getTarget().type;
			if (t !== 'text' && t !== 'textarea' && t !== 'password' && e.getKey() === 8) {	// prevent backspace.
				e.preventDefault();
			}
		});
	}
});

////////////////consts//////////////////////////

Wj.consts.totalPage = 40;
Wj.consts.css_readOnly = 'background-color: #DFE9F6; background-image: none;';
Wj.consts.css_notReadOnly = 'background-color: #fff; background-image: none;';

Wj.consts.adviceType = ['药疗', '处置', '护理', '膳食',];
Wj.consts.usage = ['口服', '静滴', '静推', '滴斗入', '肌肉注射'];
Wj.consts.dose = ['1/日', '2/日', '3/日', '4/日', '5/日', '6/日', '8/日', '1/晚', '1/12小时', '1/8小时',
		'1/6小时', '1/4小时', '1/2小时', '1/1小时', '1/30分钟', '即刻', '必要时', '术晨', '术前',
		'术前30\'', '1/周', '2/周', '3/周', '1/单日', '1/双日', '1/早', '1/午', '1/5\' *2', '1/5\' *3',
		'1/5\' *6', '15滴/1分钟'];
//--------2016年4月21日16:40:53新增-----------------
Wj.consts.surgyType = ['药疗', '处置', '护理','麻醉'];
//-----------------------------------------------
Wj.consts.volunteerTask = ['心理辅导','紧急救助','其他'];
Wj.consts.transferName = ['武警总医院','协和医院','首钢医院'];
Wj.consts.Doc = ['主治医生','检查检验医生','手术医生','转运医生'];
Wj.consts.Type =['ml','g','支'];
////////////////url//////////////////////////


// Wj.url.UserSetup = 'data/test/userSetup.json';
Wj.url.UserSetup ='../usermanager/modify';
// Wj.url.GetUserData = 'data/test/userData.json';
Wj.url.GetUserData ='../usermanager/getUser';

Wj.url.Hospital='../hospital/getAllHospital';
Wj.url.AddHospital='../hospital/addHospital';
Wj.url.RmvHospital='../hospital/deleteHospital';
Wj.url.modifyHosptail='../hospital/modifyHosptail';
//admin

//Wj.url.VCode = 'data/test/test1.jpg';
Wj.url.VCode ='../getVcodeNew';

// Wj.url.Login = 'data/test/userlogin.json';
Wj.url.Login = '../login';

// Wj.url.GetDoc = 'data/test/doctor/read.json';
Wj.url.GetDoc ='../usermanager/getDoctor';
// Wj.url.GetNurse = 'data/test/admin/nurse/read.json';
Wj.url.GetNurse ='../usermanager/getNurse';


//-----------------2016年3月29日15:37:38新增------------------
 //Wj.url.GetVolu = 'data/test/admin/volunteer/read.json';
Wj.url.GetVolu ='../usermanager/getAllVolunteerA'; 
//  Wj.url.GetVoluPerm = 'data/test/admin/volunteer/voluperm.json';
Wj.url.GetVoluPerm ='../usermanager/getundVolunteer';
//**********************************************************
  
 //-----------------2016年3月30日10:33:55新增------------------
// Wj.url.GetDrug = 'data/test/admin/drug/read.json';
 Wj.url.GetDrug = '../term/getAllDrug'  
Wj.url.GetAllDrug='../term/getAllDrugA' 	 
Wj.url.EditDrug='../term/UpdataDrug' 	
Wj.url.RmvDrug = '../term/DeleteDrug';
//Wj.url.RmvDrug = 'data/test/admin/drug/destroy.json';
Wj.url.AddDrug = '../term/AddDrug';
//Wj.url.AddDrug = 'data/test/admin/drug/destroy.json';
	
 // Wj.url.GetRTAnalysis = 'data/test/admin/rtanalysis/read.json';
 Wj.url.GetRTAnalysis = '../usermanager/getFirstPatientTime' 
 //  Wj.url.GetDisaReport = 'data/test/admin/disareport/read.json';
 Wj.url.GetDisaReport = '../Epidemic/getEpidemic'
 //**********************************************************
 //---------------2016年4月5日17:24:25新增------------	 
   Wj.url.AgreeVoluPerm ='../usermanager/modifyVolunteerA';//同意志愿者注册
 //Wj.url.AgreeVoluPerm = 'data/test/admin/volunteer/update.json';
   Wj.url.DenyVoluPerm ='../usermanager/deleteVolunteer';
  //Wj.url.DenyVoluPerm = 'data/test/admin/volunteer/update.json';//拒绝注册
//***************************************************
 //--------------2016年4月6日20:00:18新增--------------
Wj.url.RmvInjury = 'data/test/admin/rtanalysis/destroy.json';
//Wj.url.RmvInjury ='../usermanager/deleteInjury';
 Wj.url.AddInjury = 'data/test/admin/rtanalysis/create.json';
//Wj.url.AddInjury ='../usermanager/addInjury';
 //*************************************************
 //--------------2016年4月7日21:19:52新增-药品统计-------

Wj.url.EditDis='../usermanager/addDrug';
 //*************************************************
 //--------------2016年4月12日修改-疫情报告-------
  Wj.url.EditDis='../Epidemic/UpdataEpidemic';
  Wj.url.removeDisaReport='../Epidemic/deleteEpidemic';
  Wj.url.addDisaReport='../Epidemic/AddEpidemic';
  //*************************************************
 
  
  
Wj.url.Sectors = '../sector/getAllSection';
// Wj.url.Sectors = 'data/test/admin/sector/read.json';



// Wj.url.AddSector = 'data/test/admin/sector/create.json';
Wj.url.AddSector = '../sector/addSection';
// Wj.url.RmvSector = 'data/test/admin/sector/destroy.json';
Wj.url.RmvSector = '../sector/deleteSection';
// Wj.url.ModifySector = 'data/test/admin/sector/update.json';
Wj.url.ModifySector = '../sector/modifySector';

// Wj.url.AddDoc = 'data/test/admin/doctor/create.json';
Wj.url.AddDoc ='../usermanager/addDoctor';
// Wj.url.AddNurse = 'data/test/admin/nurse/create.json';
Wj.url.AddNurse = '../usermanager/addNurse';
// Wj.url.RmvDoc = 'data/test/admin/doctor/destroy.json';
Wj.url.RmvDoc ='../usermanager/deleteDoctor';
// Wj.url.RmvNurse = 'data/test/admin/nurse/destroy.json';
Wj.url.RmvNurse ='../usermanager/deleteNurse';
// Wj.url.EditDoc = 'data/test/admin/doctor/update.json';
Wj.url.EditDoc ='../usermanager/modifyUser';
// Wj.url.EditNurse = 'data/test/admin/nurse/update.json';
Wj.url.EditNurse='../usermanager/modifyUser';
Wj.url.EditVolu='../usermanager/modifyVolunteer';
// Wj.url.GetPtStatis='data/test/admin/ptstatistic/read.json';
Wj.url.GetPtStatis='../patient/getPatientStatis';
// Wj.url.GetAttdocStatis='data/test/admin/attdocstatistic/read.json';
Wj.url.GetAttdocStatis='../patient/getDocterStatis';

Wj.url.AdminBeds = '../bed/getBedsBySid';
Wj.url.RmvBed = '../bed/deleteBed';
Wj.url.AddBed = '../bed/addBed';

//doctor

//Wj.url.DoseUnit = 'data/test/doseUnit.json';
Wj.url.DoseUnit = '../term/getUnit';

// Wj.url.DocPatients = 'data/test/doctor/docPatients.json';	// doctor station nav list
Wj.url.DocPatients ='../patient/getDoctorPatient';
// Wj.url.DocPtAdvice = 'data/test/doctor/docPtAdvice.json';	// doctor's advice to specific patient
Wj.url.DocPtAdvice='../docAdvice/getDocAdviceByPid';
// Wj.url.EditDocPtAdvice = 'data/test/doctor/editDocPtAdvice.json';
Wj.url.EditDocPtAdvice ='../docAdvice/editDocAdvice';
// Wj.url.RmvPtAdvice ='data/test/doctor/rmvDocPtAdvice.json';
Wj.url.RmvPtAdvice ='../docAdvice/deleteDocAdvice';
// Wj.url.AddPtAdvice = 'data/test/doctor/editDocPtAdvice.json';
Wj.url.AddPtAdvice ='../docAdvice/addDocAdvice';

// Wj.url.AdvExecRec = 'data/test/doctor/advExeRec.json';
Wj.url.AdvExecRec = '../handler/getHandlers';

//Wj.url.AdvContents = 'data/test/doctor/AdvContents.json';
Wj.url.AdvContents = '../term/getTerms';
// Wj.url.DocPtCondition = 'data/test/doctor/ptCondition.json';
Wj.url.DocPtCondition = '../pcondition/getPConditions';

// Wj.url.DocPtCourse = 'data/test/doctor/ptCourse.json';
Wj.url.DocPtCourse ='../disProcess/getDisProcessByPid';


Wj.url.EditDocPtCourse = '../inspect/deleteImg';


Wj.url.EditInspect = '../inspect/editInspect';
Wj.url.EditEdetail = '../edetail/UpdataEdetail'
// Wj.url.RmvPtCourse = 'data/test/doctor/rmvPtCourse.json';
Wj.url.RmvPtCourse ='../disProcess/deleteDisProcess';
// Wj.url.AddPtCourse = 'data/test/doctor/editDocPtCourse.json';
Wj.url.AddPtCourse ='../disProcess/addDisProcess';
// Wj.url.StopAdvice = 'data/test/Doctoror/editDocPtAdvice.json';
Wj.url.StopAdvice='../docAdvice/stopDocAdvice';

//Wj.url.NewPt = 'data/test/doctor/docPatients.json';
Wj.url.NewPt = '../patient/getDoctorNewPatient';
//Wj.url.testNewPt = 'data/test/doctor/testNewPt.json';
Wj.url.testNewPt ='../patient/testDoctorNewPatient';
//Wj.url.ConfirmNewPt = 'data/test/nurse/ConfirmNewAdv.json';
Wj.url.ConfirmNewPt ='../patient/testConDoctorNewPatient';
//nurse

// Wj.url.testUndealt = 'data/test/nurse/testUndealt.json';
Wj.url.testUndealt = '../patient/testUndealt';

Wj.url.listDoctors = '../usermanager/getDoctorBySid';

// Wj.url.NurseNav = 'data/test/nurse/nav.json';
Wj.url.NurseNav = '../bed/getBedsWeb';

//Wj.url.LoadAdvice = 'data/test/nurse/loadAdvice.json';
// Wj.url.NursePtAdvice = 'data/test/doctor/docPtAdvice.json';
Wj.url.NursePtAdvice ='../docAdvice/getDocAdviceByPid';
// Wj.url.ExecuteAdvice = 'data/test/nurse/executeAdvice.json';
Wj.url.ExecuteAdvice = '../handler/addHandler';

//Wj.url.NurseNewAdvice = 'data/test/doctor/docPtAdvice.json';
Wj.url.NurseNewAdvice = '../docAdvice/getNewDocAdvice';
//Wj.url.testNewAdvice = 'data/test/nurse/testNewAdvice.json';
Wj.url.testNewAdvice ='../docAdvice/testNewAdvice';
//Wj.url.ConfirmNewAdvice = 'data/test/nurse/ConfirmNewAdv.json';
Wj.url.ConfirmNewAdvice = '../docAdvice/testConDocAdvice';

// Wj.url.NursePtCondition = 'data/test/nurse/ptCondition.json';
Wj.url.NursePtCondition = '../pcondition/getPConditions'; 
// Wj.url.AddPtCondition = 'data/test/nurse/addPtCondition.json';
Wj.url.AddPtCondition = '../pcondition/addPCondition';
Wj.url.EditPtCondition = '../pcondition/updatePCondition';

Wj.url.Reports = '../disposal/getDisposals';

// Wj.url.PtUndealt = 'data/test/nurse/ptUndealt.json';	// patients have no beds arranged
Wj.url.PtUndealt = '../patient/getPatientUndealt';	// patients have no beds arranged

Wj.url.PtOtherUndealt = '../patient/getPatientUndealtA';	// patients have no beds arranged

// Wj.url.UnAllocBed = 'data/test/nurse/unAllocBed.json';	// get bedNum that havn't arranged 
Wj.url.UnAllocBed = '../bed/getUnAllocBed';	// get bedNum that havn't arranged 
// Wj.url.AllocBed = 'data/test/nurse/allocBed.json';
Wj.url.AllocBed = '../bed/allocBed';
// Wj.url.AllocBedA = 'data/test/nurse/allocBedA.json';
Wj.url.AllocBedA = '../bed/allocBedA';
// Wj.url.RmvPatient = 'data/test/nurse/rmvPatient.json';

Wj.url.RmvPatient = '../patient/removePatient';
// Wj.url.ModifyBedState = 'data/test/nurse/modifyBedState.json';
Wj.url.ModifyBedState = '../bed/modifyStatus';

//Wj.url.InspectionMain = 'data/test/inspectMain.json';
//Wj.url.InspectionMain = '/WJ/examination/getExaminationMain';
//Wj.url.InspectionMain = '../examination/getExaminationMain';
//Wj.url.InspectionDetail = 'data/test/inspectDetail.json';
Wj.url.InspectionDetail = '../apply/getAllExamApply';

//---------------2016年4月11日10:37:27新增--检查检验医生----------
//Wj.url.GetInspectPt = 'data/test/inspectdoc/NewInspectPt.json';
//Wj.url.GetInspectPt ='../usermanager/getInspectPt';
 //********************************************************
 //---------------2016年4月14日10:10:50-根据id获取待检查病人详细信息--------
//---------------2016年4月26日修改--------
//Wj.url.GetInPtInfo = 'data/test/inspectdoc/InptInfo.json';
//Wj.url.GetInPtInfo ='../usermanager/getInPtInfo';
//Wj.url.GetInptChemisInfo = 'data/test/inspectdoc/InptChemisInfo.json';
//Wj.url.GetInptChemisInfo ='../usermanager/getInptChemisInfo';
//Wj.url.GetInPtNum = 'data/test/inspectdoc/InptNum.json';
//Wj.url.GetInPtNum ='../usermanager/getInptNum';
//***************************************************************
//------------------2016年4月15日14:19:10 new 检查检验医生 ExamDoctor----------------
//Wj.url.ExamNewPt = 'data/test/examdoctor/docPatients.json';
Wj.url.ExamNewPt = '../apply/ExamNewPt';

//Wj.url.testExamNewPt = 'data/test/examdoctor/testNewPt.json';
Wj.url.testExamNewPt ='../apply/getNewInspectionPt';

//Wj.url.ConfirmNewPt = 'data/test/nurse/ConfirmNewAdv.json';
//Wj.url.ConfirmExamNewPt ='data/test/nurse/ConfirmNewAdv.json';;
Wj.url.ConfirmExamNewPt ='../apply/ConfirmExamNewPt';
// Wj.url.ExamDocPatients = 'data/test/examdoctor/docPatients.json';	// doctor station nav list
Wj.url.ExamDocPatients ='../apply/getApplyByStatus';
 
Wj.url.GetExamDoc = 'data/test/examdoctor/read.json';
//Wj.url.GetExamDoc ='../usermanager/getExamDoctor';

//Wj.url.ExamInspectMain = 'data/test/inspectMain.json';
//Wj.url.ExamInspectMain = '/WJ/examination/getExaminationMain';
Wj.url.ExamInspectMain = '../apply/getInspectApply';
//Wj.url.ExamInspectDetail = 'data/test/inspectDetail.json';
Wj.url.ExamInspectDetail = '../apply/getExamApply';
//Wj.url.InspectionDetail = '../edetail/getEdetail';
//****************************************************************************
//---------------------2016年4月16日15:22:42新增 手术医生------------------------
//Wj.url.SurgyNewPt = 'data/test/examdoctor/docPatients.json';
Wj.url.SurgyNewPt = '../apply/SurgyNewPt';

//Wj.url.testSurgyNewPt = 'data/test/examdoctor/testNewPt.json';
//Wj.url.testSurgyNewPt ='../apply/getNewSurgyPt';
Wj.url.testSurgyNewPt='../apply/getNewSurgyPt';
Wj.url.ConfirmSurgyNewPt = '../apply/ConfirmSurgyNewPt';
//Wj.url.ConfirmSurgyNewPt ='../patient/testConDoctorNewPatient';

// Wj.url.SurgyDocPatients = 'data/test/examdoctor/docPatients.json';	// doctor station nav list
Wj.url.SurgyDocPatients ='../apply/getSurgyApply';
 
Wj.url.GetSurgyDoc = 'data/test/examdoctor/read.json';
//Wj.url.GetSurgyDoc ='../usermanager/getExamDoctor';

//Wj.url.SurgyInspectMain = 'data/test/inspectMain.json';
Wj.url.SurgyInspectMain = '../apply/getAllInspectApply';
//Wj.url.SurgyInspectDetail = 'data/test/inspectDetail.json';
Wj.url.SurgyInspectDetail = '../apply/getAllExamApply';
//Wj.url.SurgyInspectDetail = '../preparation/getPreparation';

//Wj.url.SurgyPrepareMain = 'data/test/surgydoctor/SurgyPrepareMain.json';
Wj.url.SurgyPrepareMain = '../apply/getOperationApply';
//Wj.url.SurgyPrepareDetail = 'data/test/surgydoctor/SurgyPrepareDetail.json';
Wj.url.SurgyPrepareDetail = '../preparation/getPreparation';

//Wj.url.SurgyPtAdvice = 'data/test/surgydoctor/SurgyPtAdvice.json';	//术后医嘱
Wj.url.SurgyPtAdvice='../docAdvice/getDocAdviceByPid';

//Wj.url.SurgyAdvExecRec = 'data/test/surgydoctor/SurgyadvExeRec.json';
Wj.url.SurgyAdvExecRec = '../handler/getHandlers';

//*************************************************************************
//-------------------------2016年4月17日21:26:27新增       手术医生工作站---------------------
//Wj.url.AddWardRecord = 'data/test/surgydoctor/AddWardRecord.json'; 
Wj.url.AddWardRecord ='../preparation/AddPreparation';
Wj.url.confirmSurgy = '../apply/SubmitOperation';
//**********************************************************************
//------------------------2016年4月18日10:05:52新增-----手术医生工作站-----------------
//Wj.url.RmvWardRecord ='data/test/surgydoctor/rmvDocWardRecord.json';
Wj.url.RmvWardRecord ='../surgydocWardRecord/rmvDocWardRecord';

//Wj.url.SurgyEditDocPtAdvice = 'data/test/surgydoctor/editDocPtAdvice.json';
Wj.url.SurgyEditDocPtAdvice ='../docAdvice/editDocAdvice';

//Wj.url.SurgyRmvPtAdvice ='data/test/surgydoctor/editDocPtAdvice.json';
Wj.url.SurgyRmvPtAdvice ='../docAdvice/deleteDocAdvice';
//Wj.url.SurgyAddPtAdvice ='data/test/surgydoctor/editDocPtAdvice.json';
Wj.url.SurgyAddPtAdvice ='../docAdvice/addDocAdvice';

//Wj.url.SurgyStopAdvice = 'data/test/surgydoctor/editDocPtAdvice.json';
Wj.url.SurgyStopAdvice='../docAdvice/stopDocAdvice';
//Wj.url.SurgyAdvContents = 'data/test/doctor/AdvContents.json';
Wj.url.SurgyAdvContents = '../term/getTerms';

//Wj.url.SurgyPhotoData = 'data/test/surgydoctor/SurgyPhotoData.json';
//Wj.url.SurgyPhotoData = '../term/getTerms';
//********************************************************************
//-nurse--新增
//-------------------2016年4月20日21:21:12新增--------------------
//Wj.url.GetDutyManage = 'data/test/nurse/dutymanage.json';

//******************************************************************
//-----------------2016年4月21日20:42:08新增--------------------------
// Wj.url.SurgyPtCourse = 'data/test/doctor/ptCourse.json';
Wj.url.SurgyPtCourse ='../disProcess/getDisProcessByPid';
// Wj.url.SurgyPtCondition = 'data/test/doctor/ptCondition.json';
Wj.url.SurgyPtCondition = '../pcondition/getPConditions';
Wj.url.SurgyPtAdviceLook = '../docAdvice/getDocAdviceByPid';	//医嘱查看，数据来自于主治医生
//Wj.url.SurgyPtAdviceLook='../docAdvice/getSurgyAdviceByPid';
//*****************************************************************
//-----------------------2016年4月22日16:13:21新增------------------
//Wj.url.NurseAddDuty ='data/test/surgydoctor/editDocPtAdvice.json';
Wj.url.NurseAddDuty ='../duty/addDuty';
Wj.url.GetDutyManage = '../duty/getDuty';
Wj.url.EditDuty='../duty/updateDuty';
Wj.url.removeDuty = '../duty/deleteDuty';
//**************************************************************************
//------------------2016年4月25日19:53:36新增----------------------

//Wj.url.GetPsyAssess ='data/test/nurse/PsyAssess.json';
Wj.url.GetPsyAssess ='../psychology/getPsychology';
Wj.url.NurseVolunteerTask='../task/getTask';
//Wj.url.NurseVolunteerTask='data/test/doctor/volunteerTask.json';

//-------------Doctor-更改----------
Wj.url.removeVolu='../usermanager/deleteVolunteer';
Wj.url.AddInspection ='../apply/AddApply';
//Wj.url.AddInspection='data/test/addInspection.json';

Wj.url.AddTest='../apply/AddApply';
//Wj.url.AddTest='data/test/addTest.json';
//Wj.url.DocVolunteerTask='../apply/AddApply';
Wj.url.DocVolunteerTask='../task/getTask';
//Wj.url.DocVolunteerTask='data/test/doctor/volunteerTask.json';
//Wj.url.addVolunteerTask='../apply/AddApply';
Wj.url.addVolunteerTask='../task/addTask';
//Wj.url.addVolunteerTask='data/test/doctor/addVolunteerTask.json';

//Wj.url.editVolunteerTask='data/test/doctor/editVolunteerTask.json';
Wj.url.editVolunteerTask='../task/edtailTask';

//Wj.url.RemoveVolunteerTask='data/test/doctor/removeVolunteerTask.json';
Wj.url.RemoveVolunteerTask='../task/deleteTask';

//Wj.url.AddOperation ='data/test/doctor/addOperation.json';
Wj.url.AddOperation ='../apply/AddApply';

//Wj.url.AddTransfer ='data/test/doctor/addTransfer.json';
Wj.url.AddTransfer='../apply/AddApply';
//手术记录查看
//Wj.url.Operation = 'data/test/doctor/Operation.json';
Wj.url.Operation = '../apply/getAllOperationApply';

//Wj.url.Transfer= 'data/test/doctor/Transfer.json';
Wj.url.Transfer= '../apply/getAllTransApply';
//************************************************

////////////////error tip////////////////////

Wj.error.FT = '出错了';	// fail title
Wj.error.ND = '获取数据发生错误，请检查网络状态!';	// net down
Wj.error.SF = '登录状态消失，请重新登录';	// status failed

///////////////////Hack/////////////////////////

Ext.apply(Ext.form.VTypes, {
    password: function(val, field) {
        if(field.confirmTo) {
            var pwd = Ext.getCmp(field.confirmTo);
            return(val == pwd.getValue());
        }
        return true;
    }
});

////////////////utilities////////////////////

Wj.util.handleRequestSuccess = function(r, a, suc, fai){

	console.log('-- func: Wj.util.handleRequestSuccess --');
	
	var res = Ext.decode(r.responseText);
	if(res && res.success == true){
		if(suc)
			suc.call(this, res);
	} else {
		if(fai)
			Wj.util.handleServerFailure.call(this, res, fai);
		else
			Wj.util.handleServerFailure.call(this, res);
	}
};

Wj.util.handleServerFailure = function(r, f){

	console.log('-- func: Wj.util.handleServerFailure --');

	var f = f;

	if(r && r.msg == 'statusfailed'){
		Ext.Msg.alert(Wj.error.FT, Wj.error.SF, function(){
			Wj.cp.set('Wj-status', false);
			window.location = 'index.htm';
			if(f)
				f.call(this);
		}, this);
	} else if(r && r.msg){
		Ext.Msg.alert(Wj.error.FT, r.msg, f, this);
	} else
		Ext.Msg.alert(Wj.error.FT, Wj.error.ND, f, this);
};

Wj.util.handleRequestFailure = function(f){

	console.log('-- func: Wj.util.handleRequestFailure --');

	Ext.Msg.alert(Wj.error.FT, Wj.error.ND, f, this);
};

Wj.util.userSetup = function(b){

	Ext.widget('usersetup').show();

};

Wj.util.confirmUserSetup = function(b){
	
	var w = b.up('window'), f = w.down('form');
	var fm = f.getForm();
	var url = Wj.url.UserSetup;

	Wj.util.confirmEditWindowForm(w, fm, url, function() {
		
		var v = w.down('form').getForm().getFieldValues(true),
			np = v.newPassword,
			ln = v.loginName,
			un = v.userName,
			cp = Wj.cp;
		
		if (np && cp.get('Wj-issavepass'))
			cp.set('Wj-userpass', np);
		if (ln)
			cp.set('Wj-username', ln);
		if(un)
			cp.set('Wj-userName', un);

		Ext.Msg.alert('提示', '修改成功！', function(){
			w.close();
			window.location = 'index.htm';
		}, this);
		
	});

}

Wj.util.confirmEditWindowForm = function(w, fm, url, suc){

	console.log('-- func: Wj.util.confirmEditWindowForm --');
	
	var w = w;

	if(fm.isValid()){
		var v = fm.getValues(false, false, true, false);	// asString, dirtyOnly, includeEmptyText, useDataValues
		console.log(v);
		Ext.Ajax.request({
			url: url,
			params: v,
			scope: Wj.util.confirmEditWindowForm,
			method: 'post',
			success: function(r, a){
				Wj.util.handleRequestSuccess(r, a, suc);
			},
			failure: Wj.util.handleRequestFailure
		});
	}
};

Wj.util.confirmEditForm = function(fm, url, suc){

	console.log('-- func: Wj.util.confirmEditForm --');

	var f = fm;

	if(f.isValid()){
		var v = f.getValues(false, false, true, false);	// asString, dirtyOnly, includeEmptyText, useDataValues
		console.log('form submit params: ');
		console.log(v);
		console.log('URL:',url);
		Ext.Ajax.request({
			url: url,
			params: v,
			scope: Wj.util.confirmEditForm,
			method: 'post',
			success: function(r, a){
				Wj.util.handleRequestSuccess(r, a, suc);
			},
			failure: Wj.util.handleRequestFailure
		});
	}
};

/**
 * this function get values of form and show it in the confirm dialog, after user's confirmation then
 * call the ConfirmEditForm() function to post it to server and deal with the rest.
 * @param act 'add' or 'edit', else throw error.
 * @param fm form.getForm()
 * @param url url to post request
 * @param suc callback for success
 */
Wj.util.confirmEditOrAddForm = function(act, fm, url, suc) {

	console.log('-- func: Wj.util.confirmEditOrAddForm --');

	var v, msg, rmsg = '', f = fm;
	
	v = f.getValues(false, false, true, false);
	
	if (act === 'add') {
		msg = '确定添加以下新记录么？';
	} else if (act === 'edit') {
		msg = '确定修改为以下记录么？';
	} else {		// error
		throw new Error('in ConfirmEditOrAddForm act is not valid: act = ' + act);
	}

	for (attr in v) {
		if (v.hasOwnProperty(attr)) {
			rmsg += attr + ' = ' + v[attr] + ' ';
		}
	}

	if (f.isValid()) {

		Ext.Msg.confirm('提示', msg + rmsg, function(btn){
			if (btn === 'yes') {
				Wj.util.confirmEditForm(fm, url, suc);
			}
		}, this);
	}

};

Wj.util.removeGridRecord = function(g, url, suc){

	console.log('-- func: Wj.util.removeGridRecord --');

	var r = g.getSelectionModel().getSelection();
	
	Ext.Ajax.request({
		url: url,
		method: 'get',
		scope: Wj.util.removeGridRecord,
		params: { id: r[0].data.id },
		
		success: function(r, a){
			Wj.util.handleRequestSuccess(r, a, suc);
		},
		failure: Wj.util.handleRequestFailure
	});
};
//-----------2016年4月5日21:06:02 新增----------
Wj.util.denyCheckBoxGridRecord = function(g, url, suc){

	console.log('-- func: Wj.util.denyCheckBoxGridRecord --');

	var r = g.getSelectionModel().getSelection();
		console.log('ID Length:',r.length);
		for(var i =0;i < r.length;i++){
			console.log('select ID :',r[i].data.id);
			Ext.Ajax.request({
				url: url,
				method: 'get',
				scope: Wj.util.denyCheckBoxGridRecord,
				params: { id: r[i].data.id },
				
				success: function(r, a){
					Wj.util.handleRequestSuccess(r, a, suc);
				},
				failure: Wj.util.handleRequestFailure
			});
		}
		Wj.util.procSuccess();

};
//操作成功 
Wj.util.procSuccess = function(){
	
			Ext.MessageBox.show({
				title:'提示',
				msg:'操作成功！',
				modal:true,
				buttons:Ext.Msg.OK,
				icon:Ext.Msg.INFO
				
			});
			
}
//*****************************************************
//--------2016年4月6日10:44:25新增
Wj.util.agreeCheckBoxGridRecord = function(g, url, suc){

	console.log('-- func: Wj.util.agreeCheckBoxGridRecord --');

	var r = g.getSelectionModel().getSelection();
		console.log('ID Length:',r.length);
		for(var i =0;i < r.length;i++){
			console.log('select ID :',r[i].data.id);
			Ext.Ajax.request({
				url: url,
				method: 'get',
				scope: Wj.util.agreeCheckBoxGridRecord,
				params: { id: r[i].data.id },
				
				success: function(r, a){
					Wj.util.handleRequestSuccess(r, a, suc);
				},
				failure: Wj.util.handleRequestFailure
			});
		}
		Wj.util.procSuccess();

};
//*******************************************************
Wj.util.editGridRecord = function(e, url, suc){

	console.log('-- func: Wj.util.editGridRecord --');

	var e = e, suc = suc;

	var s = e.store, r = e.record, n = e.newValues, o = e.originalValues;
	console.log('newValue:');
	console.log(n);
	console.log('oldValue:');
	console.log(o);
	
	var p = { id: e.originalValues.id };

	for(var v in n){
		if(n[v] !== o[v])
			p[v] = n[v];
	}

	Ext.Ajax.request({
		url: url,
		method: 'post',
		scope: Wj.util.editGridRecord,
		params: p,
		success: function(r, a){
			Wj.util.handleRequestSuccess.call(this, r, a, function(){
				Ext.Msg.alert('提示', '修改成功!', function(){
					if(suc)
						suc.call(this);
					e.record.commit();
				});
			}, function(){
					e.record.reject();
			});
		},
		failure: function(){
			Wj.util.handleRequestFailure.call(this, function(){
				e.record.reject();
			});
		}
	});
};

Wj.util.pullData = function(url, par, suc){

	Ext.Ajax.request({
		url: url,
		method: 'get',
		scope: this,
		params: par,
		success: function(r, a){
			Wj.util.handleRequestSuccess.call(this, r, a, suc);
		},
		failure: Wj.util.handleRequestFailure
	});

};

Wj.util.pushData = function(url, par, suc, fail){

	Ext.Ajax.request({
		url: url,
		method: 'post',
		scope: this,
		params: par,
		success: function(r, a){
			Wj.util.handleRequestSuccess.call(this, r, a, suc, fail);
		},
		failure: Wj.util.handleRequestFailure
	});

};

// convert sex data from 1/2 to characters.
Wj.util.convertSex = function(val, rec) {
	
	switch(val) {
		case 1:
			return '男';
			break;
		case 2:
			return '女';
			break;
		default:
			return '';
			break;
	}
};
Wj.util.convertTime = function(v,rec){  
  
		if(!v){  
         return "";  
     }  
		
		var arr = v.match(new RegExp('T'));
		if(arr){
				v = v.replace(new RegExp("-","gm"), "/").replace("T"," "); 
		}
           
     return v;  
      }
Wj.util.convertZeroToNull = function(val, rec) {

	if (val === 0) {
		return '';
	} else {
		return val;
	}
};

Wj.util.markResult = function(val, rec) {

	//console.log(rec);
	if (val < rec.data.min) {
		return '<span style="color:green;">' + val + '</span>';
	} else if (val > rec.data.max) {
		return '<span style="color:red;">' + val + '</span>';
	} else return val;
};

/* Add test data to database.
 * @param targetUrl url to add data.
 * @param extraParam params to pass to server.
 * @param num how many records do you want to add.
 * @param dataFunc function to generate test data.(return data object to pass to the par)
 * @param sucHand function invoked when every recored added successfully.
 */
Wj.util.addData = function(targetUrl, dataFunc, extraParam, num, sucHand) {

	/* add test data to database. */
	////////////////////////////////////////////////////////////////

	var url = targetUrl,
		param = extraParam,
		len = num,
		genData = dataFunc,
		suc = sucHand,
		index = 0,
		prop;

	function test_add() {

		console.log('!test_add()...');

		if (index >= len)
			return;

		var data = genData(index);
		console.log('data from genData:', data);
		for (prop in data) {
			param[prop] = data[prop];
		}

		console.log('url to add:', url);
		console.log('and par:', param);

		Wj.util.pullData.call(this, url, param, function() {
			suc();
			test_add();
		});

		index++;

	};

	test_add();

	/* add test data to database. */
	////////////////////////////////////////////////////////////////

};

/* Add test single data to database.
 * @param targetUrl url to add data.
 * @param extraParam params to pass to server.
 * @param singleData data to add to the database.
 * @param sucHand function invoked when every recored added successfully.
 */
Wj.util.addSingleData = function(targetUrl, singleData, extraParam, sucHand) {

	/* add test data to database. */
	////////////////////////////////////////////////////////////////

	var url = targetUrl,
		param = extraParam,
		data = singleData,
		suc = sucHand,
		prop;

	console.log('!test_addSingleData()...');
	console.log('data pass to database:', data);

	for (prop in data) {
		param[prop] = data[prop];
	}

	console.log('url to add:', url);
	console.log('and par:', param);

	Wj.util.pushData.call(this, url, param, function() {
		suc();
	});

	/* add test data to database. */
	////////////////////////////////////////////////////////////////

};
