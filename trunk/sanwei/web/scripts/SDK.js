function addObject()
{
    var str = "";
    str += "<OBJECT id='3DVPSDK' classid='clsid:9EFFB994-6E5F-4788-A496-92649ACEF949' width= '100%' height='80%'>";
    str += "<PARAM NAME='_Version' VALUE='65536'>";
    str += "<PARAM NAME='_ExtentX' VALUE='21934'>";
    str += "<PARAM NAME='_ExtentY' VALUE='15478'>";
    str += "<PARAM NAME='_StockProps' VALUE='0'>";
    var obj = document.getElementById('SDKDiv');
    obj.innerHTML += str; 
}

function OpenModel()
{
		document.getElementById("3DVPSDK").OpenModelDlg();
}

function Connection()
{
	document.getElementById("3DVPSDK").InitDBQuery2("SQL Server", "192.168.1.50", "������Ŀ����", "sa", "", 0, 0, 1, 0);
}

function SetRoamViewpoint()
{
	//�����ӵ�
	// x,y,z,��ֱ��ת��ˮƽ��ת
	//alert(document.getElementById("3DVPSDK").getRoamViewpointAttribute());
	document.getElementById("3DVPSDK").SetRoamViewpointAttribute(-193.54,50,-37.52,-90,180);
	document.getElementById("3DVPSDK").FlyToRoamViewpoint(-193.54,50,-37.52,-90,180);
}

function PlayRoamRecorder()
{
	//����·�����Զ����Σ�

	
	document.getElementById("3DVPSDK").SetRoamRecorderPath(1,"");
	document.getElementById("3DVPSDK").SaveRoamRecorderString("unite",
	"-227.06,50,-37.79,-90,180;-200.06,50,-45.79,-90,180;-180.06,50,-70.79,-90,180;-169.26,50,-63.13,-90,180;-130.26,50,-70.13,-90,180;-100.26,50,-90.13,-90,180;-96.08,50,-119.62,-90,180;-100.08,50,-130.62,-90,180;-140.08,50,-150.62,-90,180;");
	document.getElementById("3DVPSDK").PlayRoamRecorder("unite");
}

function SetPick3DPosState()
{
	//��ʰȡ����
	document.getElementById("3DVPSDK").SetPick3DPosState(true);
}

function SetDynLabelMoveState()
{
	//��ʱ����Ҫ
	document.getElementById("3DVPSDK").SetDynLabelMoveState(true);
	document.getElementById("3DVPSDK").SetPick3DPosState(true);
}

function AddDynamicLabel(x,y,z)
{
	//��ע
	alert(123);
	//id,����,���������壬x,y,z,
	//("add","����","��ע","-16#400000#����",x,y,z,600,1234,168509,0,"�������õı�ע");
	
	
	document.getElementById("3DVPSDK").UpdateDynamicLabelEx
		("add","����","��ע","-16#400000#����",	x,y,z,600,1234,168509,0,"�������õı�ע");

	//��ע��������ӵ�
	alert(document.getElementById("3DVPSDK").getRoamViewpointAttribute());
	
	//�ر�ʰȡ����
	document.getElementById("3DVPSDK").SetPick3DPosState(false);

	//�ӵ���� ������ �γ�һ���ļ� 
}
