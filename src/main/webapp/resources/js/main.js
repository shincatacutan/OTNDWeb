$(function() {
	// getUser();
	initButtons();
	getPayPeriods("#pp_select");

	initAddPayDetails();
	initLoadPayrollBtn();
	initFunctionBtns();

	/* Admin Functions */
	initAdminAddUser();
	initAdminUpdateUser();

	initAddPayPeriod();
	initPayrollTabs();
	initPendingApprovals();

	initAddProject();

	var amount = $("#amount_in");
	amount.blur(function(e) {
		var x = $(this).val();
		$(this).val(x.replace(",", ""));
	});

	$("#txtRemarks").on('change keyup paste', function() {
		var txtRemarks = $("#txtRemarks");
		var remarksLength = txtRemarks.val().length;
		var maxLength = 250;
		if (remarksLength > maxLength) {
			txtRemarks.val(txtRemarks.val().substring(0, maxLength));
		}
		$("#availableChar").html(250 - txtRemarks.val().length);
	});
	$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
	
	initDropdownHandlers();
});

jQuery.extend(jQuery.fn, {
	projects : function(data) {
		var input = $(this);
		input.find('option').remove().end();
		input.append($('<option>', {
			value : "",
			text : ""
		}))
		$.each(data, function(i, data) {
			input.append($('<option>', {
				value : data.id,
				text : data.code,
				"data-manager": data.manager
			}));
		});

	}
});

var format = function(val) {
	return val.replace('\d+(\.\d{1,2})?')
}

var initPayrollTabs = function() {
	$("#tabs").tabs({
		activate : function(event, ui) {
			switch (ui.newPanel.selector) {
			case "#tabs-4":
				initLoadPayperiodsAdmin();
				initGenerateExcelBtn();
				break;
			case "#tabs-3":
				loadOpenPeriods();
				break;
			case "#tabs-1":
				getPayPeriods("#pp_select");
				break;
			case "#tabs-5":
				getPayPeriods("#pa_select_admin");
				break;
			case "#tabs-6":
				getAdmins("#project_admin");
				getProjects("#project_upd");
				getAdmins("#proj_approver");
				initUpdateProject();
				onChangeUpdateProj();
				break;
			default:
				$("#updateUser_form")[0].reset();
				$("#populate-div").hide();
				// tab2
				getProjects("#user_project");
				getProjects("#user_project_upd");
				initDeleteUser(); 	
				loadAllUsernames();
			}
		}
	});
}

var onChangeUpdateProj = function(){
	$("#project_upd").on("change", function(){
		var selected = $(this).find('option:selected');
		var curr = selected.data('manager');
		$("#curr_approver").val(curr);
	})
}
var loadUserInfo = function(username) {

	if (username != "-21") {
		$.ajax({
			url : contextPath + "/getUserInfo",
			type : "POST",
			data : {
				'username' : username
			},
			accept : 'application/json',
			success : function(data) {

				$("#empID_user_upd").val(data.empID);
				$("#lastname_user_upd").val(data.lastName);
				$("#firstname_user_upd").val(data.firstName);
				$("#role_user_upd").val(data.roleType.id);
				$("#user_project_upd").val(data.project.id);
				$("#populate-div").show();
			},
			error : function(e) {
				// //console.log(e);
			}
		});
	}
}

var initUpdateProject = function() {
	// 
	$("#update_project").on("click", function(){
		if ($("#project_form_upd").valid()) {
			$.ajax({
				url : contextPath + "/updateProject",
				type : "POST",
				accept : 'application/json',
				data : {
					'projectID' : $("#project_upd").val(),
					'projectName':$("#project_upd option:selected").text(),
					'manager' : $("#proj_approver").val()
				},
				success : function() {
					showNotify("Project has been updated.")
					$("#project_form_upd")[0].reset();
				},
				error : function(e) {
					showNotify("Update is unsuccessful.");
				}

			});
		}
	})
	

}

var loadAllUsernames = function() {

	$.ajax({
		url : contextPath + "/getAllUsernames",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			console.log(data)
			$('#user-autocomplete').typeahead({
				source : data,
				onSelect : function(item) {
					loadUserInfo(item.value);
				}
			});
		},
		error : function(e) {
			// //console.log(e);
		}

	});
}

var getProjects = function(id) {
	//
	var select = $(id);
	$.ajax({
		url : contextPath + "/getProjects",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			select.projects(data);
		},
		error : function(e) {
			// console.log(e);
		}
	});
}

var initDeleteUser = function(){
	$("#deleteUser_btn").click(function(){
		if (confirm("Delete selected user?")) {
			$.ajax({
				url : contextPath + "/deleteUser",
				type : "POST",
				accept : 'application/json',
				data : {
					'ntID' : $("#user-autocomplete").val()
				},
				success : function(data) {
					showNotify("User has been deleted.")
				},
				error : function(e) {
					showNotify("Delete is not successful.")
					// console.log(e);
				}
			});
		}
		
	});
}

var initAddProject = function() {
	$("#add_project_btn").click(function(event) {
		if ($("#project_form").valid()) {

			$.ajax({
				url : contextPath + "/addProject",
				type : "POST",
				accept : 'application/json',
				data : {
					'projectName' : $("#project_name").val(),
					'admin' : $("#project_admin").val(),
					'process' : $("#process").val()
				},
				success : function(data) {
					showNotify("Project has been added.")
				},
				error : function(e) {
					showNotify("The project already exists.")
					// console.log(e);
				}
			});
		}
	})
}

var initPendingApprovals = function() {
	$('#load_pa_admin').click(function(event) {
		var selectedOption = $('#pa_select_admin option:selected').text();
		if (selectedOption == "") {
			showNotify("Please select a period.")
			// showNotify("Please select a period.");
			return;
		}
		loadIncomePeriodDetails(2, '#pa_grid');
		$('#selectAll_btn').show();
		$('#unSelectAll_btn').show();

		$('#approve_btn').show();
		$('#reject_btn').show();
	});
}

var loadOpenPeriods = function() {
	$.ajax({
		url : contextPath + "/getOpenPayrolls",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			// console.log(data)
			loadToPayrollGrid(data, "#openPayrollGrid");
		},
		error : function(e) {
			// console.log(e);
		}
	});
}

var loadToPayrollGrid = function(data, tableID) {
	if ($.fn.dataTable.isDataTable(tableID)) {
		var table = $(tableID).DataTable();
		table.clear();
		table.rows.add(data);
		table.draw();
	} else {
		var table = $(tableID).dataTable({
			"data" : data,
			"columns" : [ {
				"title" : "Payroll Period",
				"data" : "period",
				"class" : "dt-left"
			}, {
				"title" : "Status",
				"class" : "dt-left",
				"data" : "status"
			} ]
		});

		$(tableID + ' tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('#close_pperiod_btn').click(function() {

			var newInstance = $("#openPayrollGrid").DataTable();
			var closeMe = newInstance.rows('.selected').data();
			if (closeMe.length == 0) {
				showNotify("Please select a period to close.")
			} else {
				if (confirm("Close selected period?")) {
					var period = closeMe[0].period;
					
					$.ajax({
						url : contextPath + "/closePayrollPeriod",
						type : "GET",
						data : {
							'payPeriod' : period
						},
						accept : 'application/json',
						success : function(data) {
							newInstance.row('.selected').remove().draw();
						},
						error : function(e) {
							// console.log(e);
						}
					});
				}
			}

		});
	}

}

var initGenerateExcelBtn = function() {
	var payperiod = $("#pp_select_admin");
	var generateBtn = $('#generate_btn');

	generateBtn.on('click', function() {
		var dataGrid = $("#report_grid").DataTable();
		var size = dataGrid.rows()[0].length;

		if (size > 0) {
			window.location = contextPath + "/download.do?payPeriod="
					+ payperiod.val();
		} else {
			showNotify("No data to generate.")
		}

	});
}

var initLoadPayrollBtn = function() {
	var payrollBtn = $('#load_payroll');

	payrollBtn.click(function(event) {

		// add button event onclick for load//
		$.ajax({
			url : contextPath + "/getIncomeTypes",
			type : "POST",
			accept : 'application/json',
			success : function(data) {
				var incomeTypeInput = $('#income_type');
				incomeTypeInput.empty();
				incomeTypeInput.append($('<option>', {
					text : ""
				}));
				$.each(data, function(i, data) {
					incomeTypeInput.append($('<option>', {
						value : data,
						text : data
					}));
				});
			},
			error : function(e) {
				// console.log(e);
			}
		});

		if (typeof String.prototype.trim !== 'function') {
			String.prototype.trim = function() {
				return this.replace(/^\s+|\s+$/g, '');
			}
		}

		loadIncomePeriodDetails(0, '#resultGrid');

		var selected = $("#pp_select option:selected").text();
		var status = selected.indexOf('Closed');
		if (status > 0) {

			showNotify("Payroll period is already closed.")
			enableIncomeDetailForm(false);
			showHideButtonDelete(false);
		} else {
			enableIncomeDetailForm(true);
			showHideButtonDelete(true);
		}

	});

	$('#income_type').change(function(event) {
		loadIncomeCodesByType(this.value);
		changeInputByIncomeType(this.value);
		$("#full-desc").html("");
	})
}
var showNotify = function(msg) {
	$.notify({
		// options
		message : msg
	}, {
		// settings
		type : 'warning',
		offset : 50,
		delay : 7000,
		placement : {
			from : "top",
			align : "center"
		}
	});
}

var enableIncomeDetailForm = function(boolean) {
	var ids = [ "#income_type", "#income_code", "#amount_in", "#createDt_in",
			"#txtRemarks" ]

	$.each(ids, function(i, ids) {
		$(ids).prop("disabled", !boolean)
	});

	var btnid = [ "#addPay_btn", "#resetPay_btn" ]
	$.each(btnid, function(i, btnid) {
		$(btnid).button("option", "disabled", !boolean);
	});
}

var loadIncomePeriodDetails = function(detailLevel, tableID) {
	var payperiod;
	switch (detailLevel) {
	case 0: // regular user
		payperiod = $("#pp_select").val();
		break;
	case 1: // admin report generate
		payperiod = $("#pp_select_admin").val();
		break;
	case 2: // admin pending approvals
		payperiod = $("#pa_select_admin").val();
		break;
	default:

	}

	$.ajax({
		url : contextPath + "/getIncomeDetails",
		type : "POST",
		data : {
			'payPeriod' : payperiod,
			'detailLevel' : detailLevel
		},
		accept : 'application/json',
		success : function(data) {
			paintTable(data, tableID);
		},
		error : function(e) {
			// console.log(e);
		}
	});
}

var initAddPayDetails = function() {
	jQuery.validator.setDefaults({
		debug : true,
		success : "valid"
	});
	$("#addPay_btn").click(
			function(event) {
				var incomeDetailsIds = [ "#income_type", "#income_code",
						"#amount_in", "#txtRemarks", "#createDt_in" ];
				var incomeForm = $("#income-form")
				incomeForm.validate({
					rules : {
						amount_in : {
							required : true,
							number : true
						}
					}
				});

				if (incomeForm.valid()) {
					var payperiod = $("#pp_select").val();
					var incomeType = $("#income_type").val();
					var incomeCode = $("#income_code").val();
					var detailValue = isDateEntry == true ? $("#createDt_in")
							.val() : $("#amount_in").val();
					var remarks = $("#txtRemarks").val();
					$.ajax({
						url : contextPath + "/addPayrollDetails",
						type : "POST",
						data : {
							'payPeriod' : payperiod,
							'incomeType' : incomeType,
							'incomeCode' : incomeCode,
							'detailValue' : detailValue,
							'remarks' : remarks
						},
						accept : 'application/json',
						success : function(data) {
							// console.log(data);
							showNotify("Income detail is saved.")
							$("#income-form")[0].reset();
							loadIncomePeriodDetails(0, '#resultGrid');
						},
						error : function(e) {
							showNotify("Income detail was not saved. Please refresh browser.")
						}

					});
				}

				event.preventDefault();
			});

}
var initAddPayPeriod = function() {

	$("#add_pperiod_btn").click(function(event) {
		var payPeriodForm = $('#payperiod_form');
		payPeriodForm.validate();

		if (payPeriodForm.valid()) {
			var payperiod = $("#payperiod_add").val();
			var status = $("#pp_status").val();
			$.ajax({
				url : contextPath + "/addPayPeriod",
				type : "POST",
				data : {
					'payPeriod' : payperiod,
					'status' : status
				},
				accept : 'application/json',
				success : function(data) {
					showNotify("Pay period was added.")
					$("#payperiod_form")[0].reset();
					loadOpenPeriods();
				},
				error : function(e) {
				}

			});
		}

		event.preventDefault();
	});
}

var initLoadPayperiodsAdmin = function() {
	getPayPeriods("#pp_select_admin");
	$('#load_payroll_admin').click(function(event) {
		var selectedOption = $('#pp_select_admin option:selected').text();
		if (selectedOption == "") {
			showNotify("Please select a period.");
			return;
		}
		loadIncomePeriodDetails(1, '#report_grid');
		$('#generate_btn').show();
	});
}

var initAdminUpdateUser = function() {
	$("#updateUser_btn").click(function(event) {
		var addUserForm = $('#updateUser_form');
		addUserForm.validate();

		if (addUserForm.valid()) {
			var username = $("#user-autocomplete").val();
			var empID = $("#empID_user_upd").val();
			var firstname = $("#firstname_user_upd").val();
			var lastname = $("#lastname_user_upd").val();
			var role = $("#role_user_upd").val();
			var project = $("#user_project_upd").val();

			$.ajax({
				url : contextPath + "/updateUser",
				type : "POST",
				accept : 'application/json',
				data : {
					'ntID' : username,
					'empID' : empID,
					'firstName' : firstname,
					'lastName' : lastname,
					'roleId' : role,
					'project' : project
				},
				success : function(data) {
					showNotify("The user is updated.")
					addUserForm[0].reset();
				},
				error : function(e) {
					showNotify("The user update is unsuccessful.");
				}
			});
		}

	});

}

var initAdminAddUser = function() {
	$("#addUser_btn").click(function(event) {
		var addUserForm = $('#addUser_form');
		addUserForm.validate();

		if (addUserForm.valid()) {
			var username = $("#uname_user").val();
			var empID = $("#empID_user").val();
			var firstname = $("#firstname_user").val();
			var lastname = $("#lastname_user").val();
			var role = $("#role_user").val();
			var project = $("#user_project").val();

			$.ajax({
				url : contextPath + "/addUser",
				type : "POST",
				accept : 'application/json',
				data : {
					'ntID' : username,
					'empID' : empID,
					'firstName' : firstname,
					'lastName' : lastname,
					'roleId' : role,
					'project' : project
				},
				success : function(data) {
					showNotify("The user is added.")
					$('#addUser_form')[0].reset();
				},
				error : function(e) {
					showNotify("The username is already on the record");
				}
			});
		}

	});

}

var getAdmins = function(id) {
	$.ajax({
		url : contextPath + "/getAdmins",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			console.log(data);
			$(id).empty();
			$(id).append($('<option>', {
				value : "",
				text : ""
			}))
			$.each(data, function(i, data) {
				$(id).append($('<option>', {
					value : data.networkID,
					text : data.firstName + " " + data.lastName
				}));
			});
		},
		error : function(e) {
			// console.log(e);
		}
	});

}

var getPayPeriods = function(id) {
	$.ajax({
		url : contextPath + "/getPayPeriods",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			$(id).empty();
			$.each(data, function(i, data) {
				if (id == "#pp_select") {
					// if (data.status == "Open")
					$(id).append($('<option>', {
						value : data.period,
						text : data.period + " (" + data.status + ") "
					}));
				} else {
					$(id).append($('<option>', {
						value : data.period,
						text : data.period + " (" + data.status + ") "
					}));
				}

			});
		},
		error : function(e) {
			// console.log(e);
		}
	});

}
var isDateEntry;

var changeInputByIncomeType = function(incomeType) {
	if (incomeType == "LWOP") {
		hideDetailInput("dateSpan");
		isDateEntry = true;
	} else {
		hideDetailInput("amountSpan");
		isDateEntry = false;
		var amtHrLbl = document.getElementById("amtHrLbl");
		if (incomeType == "OT_ND") {
			amtHrLbl.innerHTML = "Hours";
		} else {
			amtHrLbl.innerHTML = "Amount";
		}
	}
}

var hideDetailInput = function(id) {
	if (id == "dateSpan") {
		$('#amountSpan').hide();
		$('#amountSpan').css("display", "none");
		$('#dateSpan').show();
		$('#dateSpan').css("display", "inline");
	} else {
		$('#dateSpan').hide();
		$('#dateSpan').css("display", "none");
		$('#amountSpan').show();
		$('#amountSpan').css("display", "inline");
	}
}

var loadIncomeCodesByType = function(incomeType) {
	$.ajax({
		url : contextPath + "/getCodesByType",
		type : "POST",
		accept : 'application/json',
		data : {
			'incomeType' : incomeType
		},
		success : function(data) {
			var codeInput = $('#income_code');
			codeInput.empty();
			codeInput.append($('<option>', {
				text : ""
			}));
			$.each(data, function(i, data) {
				$('#income_code').append($('<option>', {
					value : data.id,
					text : data.desc
				}));
			});

			$('#income_code').change(
					function() {
						var val = this.value;
						var result = $.grep(data, function(e) {
							return e.id == val;
						});
						if (val != "" || null != val) {
							if (result.length != 0) {
								var fullDesc = null == result[0].fullDesc ? ""
										: result[0].fullDesc;
								$("#full-desc").html(fullDesc);
							}
						}
					});
		},
		error : function(e) {
			// console.log(e);
		}
	});
}
var getUser = function() {
	var network = new ActiveXObject("WScript.Network");
	var networkId = network.UserName;
	var networkId = "asanju3";

	$
			.ajax({
				url : contextPath + "/getUser",
				type : "POST",
				accept : 'application/json',
				data : {
					"empID" : networkId
				},
				success : function(emp) {
					// console.log(emp)
					// $("#fullname").html(emp.firstName + " " + emp.lastName);
					// $("#empID").html(emp.empID);
					// $("#ntid").html(emp.networkID);
					// $("#manager").html(emp.manager);
					// $("#project").html(emp.project);
					// $('#load_payroll').button("option", "disabled", false);
				},
				error : function(e) {
					showNotify("The user is not yet in the database. Please contact administrator.");
					$('#load_payroll').button("option", "disabled", true);

				}
			});
}

var initButtons = function() {
	// $( "#tabs" ).tabs();
	$(":button").button();
	$(":reset").button();
	$('#createDt_in').datepicker({
		maxDate : '0'
	});
	$('#payperiod_add').datepicker();
	$("#tabs").tabs();
	showHideButtonDelete(false);
	hideDetailInput("amountSpan");
	$('#generate_btn').hide();
	$('#approve_btn').hide();
	$('#reject_btn').hide();
	$('#selectAll_btn').hide();
	$('#unSelectAll_btn').hide();
}

var showHideButtonDelete = function(show) {
	if (show) {
		$('#delete_btn').show();
	} else {
		$('#delete_btn').hide();
	}
}

var paintTable = function(oData, tableID) {
	// console.log(oData)
	if ($.fn.dataTable.isDataTable(tableID)) {
		var table = $(tableID).DataTable();
		table.clear();
		table.rows.add(oData);
		table.draw();
	} else {
		var table = $(tableID).dataTable(
				{
					"data" : oData,
					"columns" : [
							{
								"title" : "Pay Period",
								"data" : "payrollPeriod",
								"class" : "dt-left",
								"render" : function(obj) {
									return '<span>' + obj.period.monthOfYear
											+ '/' + obj.period.dayOfMonth + '/'
											+ obj.period.year + '</span>'
								}
							},
							{
								"title" : "Type",
								"class" : "dt-left",
								"data" : "incomeType.id"
							},
							{
								"title" : "Description",
								"class" : "dt-left",
								"data" : "incomeType.desc"
							},
							{
								"title" : "Remarks",
								"class" : "dt-longer",
								"data" : "remarks",

							},
							{
								"title" : "Hours/Amount/Date",
								"class" : "dt-left",
								"data" : "prodHrsAmt"
							},
							{
								"title" : "Employee",
								"class" : "dt-left",
								"data" : "empId",
								"render" : function(data){
									return data.firstName +" "+data.lastName;
								}
							},
							{
								"title" : "Team",
								"class" : "dt-left",
								"data" : "empId.project.code"
							},
							{
								"title" : "Create Date",
								"class" : "dt-left",
								"data" : "createDate",
								"render" : function(obj) {
									return '<span>' + obj.monthOfYear + '/'
											+ obj.dayOfMonth + '/' + obj.year
											+ '</span>'
								}
							}, {
								"title" : "Approval Status",
								"class" : "dt-left",
								"data" : "status"
							}, {
								"title" : "Approver",
								"class" : "dt-left",
								"data" : "approver"
							} ]
				});

		$(tableID + ' tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				// table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});
	}
};

var initFunctionBtns = function() {
	$('#delete_btn').click(function() {
		var newInstance = $("#resultGrid").DataTable();
		var deletePayIds = newInstance.rows('.selected').data();
		if (deletePayIds.length == 0) {
			showNotify("Please select a row to delete.")
		} else {

			if (confirm("Delete selected item?")) {
				$.each(deletePayIds, function(key, value) {
					deletePayrollDetail(value["id"]);
					newInstance.row('.selected').remove().draw();
				});
			}
		}
	});

	$('#approve_btn').click(function() {
		approvePayroll(true);
	});

	$('#reject_btn').click(function() {
		approvePayroll(false);
	});

	$('#selectAll_btn').click(function() {
		$('#pa_grid tbody tr').addClass('selected');
	});

	$('#unSelectAll_btn').click(function() {
		$('#pa_grid tbody tr').removeClass('selected');
	});
}

var approvePayroll = function(isApproved) {
	var newInstance = $("#pa_grid").DataTable();
	var approveIDs = newInstance.rows('.selected').data();
	var message = '';
	if (approveIDs.length != 0) {
		if (isApproved) {
			message = "Approve selected item?";
		} else {
			message = "Reject selected item?";
		}
		if (confirm(message)) {
			$.each(approveIDs, function(key, value) {
				approvePayrollDetail(value["id"], isApproved);
				newInstance.row('.selected').remove().draw();
			});
		}
	} else {
		var alertMsg;
		if (isApproved) {
			alertMsg = "approve";
		} else {
			alertMsg = "reject";
		}
		showNotify("Please select a row to " + alertMsg + ".");
	}

}

var approvePayrollDetail = function(incomeId, isApproved) {
	$.ajax({
		url : contextPath + "/approveIncomeDetail",
		type : "POST",
		data : {
			"incomeID" : incomeId,
			"isApproved" : isApproved
		},
		accept : 'application/json',
		success : function(msg) {
			// console.log(msg)
		},
		error : function(e) {
			showNotify("Error encountered during detail approval.");
		}
	});
}

var deletePayrollDetail = function(incomeId) {
	$.ajax({
		url : contextPath + "/deleteIncomeDetail",
		type : "POST",
		data : {
			"incomeID" : incomeId
		},
		accept : 'application/json',
		success : function(msg) {
		},
		error : function(e) {
			showNotify("Error encountered. Please refresh browser.");
		}
	});
}

var initDropdownHandlers = function(){
	$("#pp_select").mousedown(onMouseDownHandler);
	$("#pa_select_admin").mousedown(onMouseDownHandler);
	$("#pp_select_admin").mousedown(onMouseDownHandler);
	
	$("#pa_select_admin").click(onClickHandler);
	$("#pp_select_admin").click(onClickHandler);
	$("#pp_select").click(onClickHandler);
	
	
}

function onMouseDownHandler(e){
	var el = e.currentTarget;
	
    if(el.hasAttribute('size') && el.getAttribute('size') == '1'){
    	e.preventDefault();    
    }
}
function onClickHandler(e) {
 	var el = e.currentTarget; 

    if (el.getAttribute('size') == '1') {
        el.className += " selectOpen";
        el.setAttribute('size', '5');
    }
    else {
        el.className = '';
        el.setAttribute('size', '1');
    }
}