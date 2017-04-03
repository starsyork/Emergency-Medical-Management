var TEMP;
var TBInfo,TBIHead,TBIEnd,TBIBody;//基本信息
var TEMP1,TInHead,TInEnd,TInsp;//检查检验
var TPHead1,TPBody1,TPEnd1,TPHead2,TPBody2,TPEnd2,TPhoto;//影像数据
var TSHead,TSBody,TSEnd,TSurg;//手术医生
var TCoHead,TCoBody,TCoEnd,TCond;//查看病情
var TCourHead,TCourBody,TCourEnd,TCournd;//查看病程
var TAdvHead,TAdvBody,TAdvEnd,TAdvnd;//主治医生医嘱
var TSAdvHead,TSAdvBody,TSAdvEnd,TSAdvnd;//手术医生医嘱
var TDispHead,TDispBody,TDispEnd,TDispnd;//处治报告
var ENT = "<br>";
var patient = {
	id:0,
	sector:"",
	docId:0,
	docName:"",
	name:"",
	sex:"",
	rfid:"",
	age:0,
	bedNum:0,
	woundType:"",
	woundTime:"",
	woundAddr:""
	
};
function setBlankspace(LEN){
	var temp = "";
	if(LEN>=0){
		for(var i = 0;i<LEN;i++){
			temp = temp + "&nbsp;";
		}
	}else{
		temp = " ";
	}
	return temp;
}

function setItem(str){
	var temp = "<div style='text-align: left;font-size: 14px'>"
				+"<b>"+blanspace3+str+"</b>"+"<br>"+"<br>";
	return temp;
}
function setLowItem(str){
	var temp = "<div style='text-align: left;font-size: 12px'>"
				+ blanspace1 + str +"<br>";
	return temp;
}
function NowDate(){
	return Ext.util.Format.date(new Date(),'Y-m-d g:i:s A');
}





