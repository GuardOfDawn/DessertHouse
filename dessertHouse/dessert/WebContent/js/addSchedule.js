function storeChange(obj){
	document.getElementById('scheduleStoreInfo').style.display='block';
	var storeparts = obj.value.split(";");
	document.getElementById('provinceAutoInput').value= storeparts[2];
	document.getElementById('cityAutoInput').value= storeparts[3];
	document.getElementById('storeNameAutoInput').value= storeparts[1];
	document.getElementById('storeLocAutoInput').value= storeparts[4];
}
function changeShowState(tableId,day,addId)
{  
	if(document.getElementById(day).value==0){
		document.getElementById(day).src="/dessert/image/minus.jpg"
		document.getElementById(day).value="1"
		document.getElementById(tableId).style.display='block'
		document.getElementById(addId).style.display='block'
	}
	else{
		document.getElementById(day).src="/dessert/image/plus.jpg"
		document.getElementById(day).value="0"
		document.getElementById(tableId).style.display='none'
		document.getElementById(addId).style.display='none'
	}
}
function displayItemAddWindow(tableId){
	document.getElementById('light').style.display='block';
	document.getElementById('day').value=tableId;
}
function selChange(obj){
	var parts = obj.value.split(";");
	document.getElementById('nameAutoInput').value= parts[1];
	document.getElementById('typeAutoInput').value= parts[2];
	document.getElementById('productImg').src= "/dessert"+parts[3];
}
function closeScheduleItem(){
	document.getElementById('light').style.display='none';
	document.getElementById('idInput').value='null';
	document.getElementById('nameAutoInput').value= '';
	document.getElementById('typeAutoInput').value= '';
	document.getElementById('priceInput').value='';
	document.getElementById('countInput').value='';
	document.getElementById('productImg').src='';
	document.getElementById('day').value='';
}
function addScheduleItem(){
	//新增记录
	var tableId = document.getElementById('day').value;
	var table = document.getElementById(tableId);
	var newRow = table.insertRow(table.rows.length);
    var newCel1 = newRow.insertCell(0);
    var newCel2 = newRow.insertCell(1);
    var newCel3 = newRow.insertCell(2);
    var newCel4 = newRow.insertCell(3);
    var newCel5 = newRow.insertCell(4);
    var newCel6 = newRow.insertCell(5);
    var newCel7 = newRow.insertCell(6);
    var newCel8 = newRow.insertCell(7);
    var parts = document.getElementById('idInput').value.split(";");
    var id = parts[0];
    newCel1.innerHTML = id;
    newCel2.innerHTML = parts[1];
    newCel3.innerHTML = parts[2];
    newCel4.innerHTML = "<img width='60' height='40' src='/dessert"+parts[3]+"'/>";
    newCel5.innerHTML = document.getElementById('priceInput').value;
    newCel6.innerHTML = document.getElementById('countInput').value;
    newCel7.innerHTML = "<input class='submit' type='button' name='modifyItem' value='修改' onclick=\"modifyRow(\'"+tableId+"\',\'"+id+"\')\" />"
    newCel8.innerHTML = "<input class='submit' type='button' name='deleteItem' value='删除' onclick=\"deleteRow(\'"+tableId+"\',\'"+id+"\')\" />"
    //不显示添加窗口
	document.getElementById('light').style.display='none';
    //清除记录
	document.getElementById('idInput').value='null';
	document.getElementById('nameAutoInput').value= '';
	document.getElementById('typeAutoInput').value= '';
	document.getElementById('priceInput').value='';
	document.getElementById('countInput').value='';
	document.getElementById('productImg').src='';
	document.getElementById('day').value='';
}
function modifyRow(tableId,productId)
{  
	var table = document.getElementById(tableId);
	var rows = table.rows;
	for(var i=1;i<rows.length;i++){
		if(rows[i].cells[0].innerHTML==productId){
			document.getElementById('productIdM').value=productId;
			document.getElementById('nameAutoInputM').value= rows[i].cells[1].innerHTML;
			document.getElementById('typeAutoInputM').value= rows[i].cells[2].innerHTML;
			var parts = rows[i].cells[3].innerHTML.split("src=\"");
			var imagepath = parts[1].substring(0,parts[1].length-2);
			document.getElementById('productImgM').src=imagepath;
			document.getElementById('priceInputM').value=rows[i].cells[4].innerHTML;
			document.getElementById('countInputM').value=rows[i].cells[5].innerHTML;
			document.getElementById('dayM').value=tableId;
			document.getElementById('lightForModify').style.display='block';
			break;
		}
	}
}
function closeScheduleItemM(){
	document.getElementById('lightForModify').style.display='none';
	document.getElementById('productIdM').value='';
	document.getElementById('nameAutoInputM').value= '';
	document.getElementById('typeAutoInputM').value= '';
	document.getElementById('priceInputM').value='';
	document.getElementById('countInputM').value='';
	document.getElementById('productImgM').src='';
	document.getElementById('dayM').value='';
}
function modifyScheduleItem(){
	var tableId = document.getElementById('dayM').value;
	var table = document.getElementById(tableId);
	var rows = table.rows;
    var productId = document.getElementById('productIdM').value;
	for(var i=1;i<rows.length;i++){
		if(rows[i].cells[0].innerHTML==productId){
			rows[i].cells[4].innerHTML=document.getElementById('priceInputM').value;
			rows[i].cells[5].innerHTML=document.getElementById('countInputM').value;
			//clear contents
			document.getElementById('lightForModify').style.display='none';
			document.getElementById('productIdM').value='';
			document.getElementById('nameAutoInputM').value= '';
			document.getElementById('typeAutoInputM').value= '';
			document.getElementById('priceInputM').value='';
			document.getElementById('countInputM').value='';
			document.getElementById('productImgM').src='';
			document.getElementById('dayM').value='';
			break;
		}
	}
}
function deleteRow(tableId,productId)
{  
	var table = document.getElementById(tableId);
	var rows = table.rows;
	for(var i=1;i<rows.length;i++){
		if(rows[i].cells[0].innerHTML==productId){
			table.deleteRow(i);
			break;
		}
	}
}