<c:set var="isAdmin" scope="session" value="${isAdmin}" />
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Payroll Input</a></li>
		<c:if test="${isAdmin}">
			<li><a href="#tabs-5">Pending Approvals</a></li>
			<li><a href="#tabs-4">Payroll Report</a></li>
			<li><a href="#tabs-3">Payroll Period</a></li>
			<li><a href="#tabs-2">Employee</a></li>
			<li><a href="#tabs-6">Projects</a></li>
		</c:if>
	</ul>
	<div id="tabs-1" class="tab_div">

		<form id="income-form">
			<fieldset>
				<div class="load_pay">
					<label class="detailsLbl">Payroll Period</label> <select
						id="pp_select" name="pp_select" size="1">
						<option></option>
					</select> &nbsp; <input type="button" id="load_payroll" name="load_payroll" value="Load"/>
				</div>
				<span class="detailSpan"> <label class="detailsLbl">Income
						Type</label> <select id="income_type" name="income_type"
					disabled="disabled" required>
						<option></option>
				</select>
				</span> <span class="detailSpan codeSpan"> <label class="detailsLbl">Code</label>
					<select id="income_code" name="income_code" disabled="disabled"
					required>
						<option></option>
				</select>

				</span> <span class="detailSpan"> <span id="amountSpan"> <label
						class="detailsLbl" id="amtHrLbl">Amount </label> <input
						id="amount_in" type="text" name="amount_in" disabled="disabled"
						required /></span> <span id="full-desc"></span> <span id="dateSpan">
						<label class="detailsLbl">Date </label> <input type="text"
						id="createDt_in" name="createDt_in" disabled="disabled" required />
				</span>
				</span> <span class="detailSpan detailRemarks"> <label
					class="detailsLbl">Remarks </label> <textarea id="txtRemarks"
						name="txtRemarks" class="text ui-widget-content" maxlength="250"
						disabled="disabled" required></textarea> <br /> <span
					id="availableCharSpan">(<span id="availableChar">250</span>/250)
				</span>
				</span> <br class="clearfix" />
				<div id="action_btn">
					<input type="reset" id="resetPay_btn" disabled="disabled" /> <input
						type="button" name="addPay_btn" id="addPay_btn" value="Add"
						disabled="disabled" />

				</div>
			</fieldset>
		</form>

		<hr class="seperator">

		<div id="tableSpace">
			<table class="display" id="resultGrid"></table>
			<input type="button" id="delete_btn" value="Delete" />
		</div>

	</div>
	<c:if test="${isAdmin}">
		<div id="tabs-2" class="tab_div">
			<div class="two_column_left two_column">
				<h2>Add Employee</h2>
				<form id="addUser_form">
					<fieldset>

						<span class="addSpan"> <label for="role" class="addLbl">Role</label>
							<select id="role_user" name="role_user" required>
								<option value="1">Admin</option>
								<option value="2">Regular</option>
						</select>
						</span> <span class="addSpan"> <label for="role" class="addLbl">Project</label>
							<select id="user_project" name="user_project" required>
						</select>
						</span> <span class="addSpan"> <label for="username"
							class="addLbl">Username</label> <input type="text"
							name="username" id="uname_user" required />
						</span> <span class="addSpan"> <label for="empID" class="addLbl">Employee
								ID</label> <input type="text" name="empID" id="empID_user" required />
						</span> <span class="addSpan"> <label for="firstname"
							class="addLbl">First Name</label> <input type="text"
							name="firstname" id="firstname_user" required />
						</span> <span class="addSpan"> <label for="lastname"
							class="addLbl">Last Name</label> <input type="text"
							name="lastname" id="lastname_user" required />
						</span>


						<!-- Allow form submission with keyboard without duplicating the dialog button -->
						<input type="reset" /> <input type="button" id="addUser_btn"
							value="Submit" name="addUser_btn" />
					</fieldset>
				</form>
			</div>
			<div class="two_column_right two_column">
				<h2>Update User</h2>
				<form id="updateUser_form">
					<fieldset>
						<span class="addSpan"> <label for="username" class="addLbl">Username</label>
							<input type="text" name="username" id="user-autocomplete"
							required />
						</span>

						<div id="populate-div">
							<span class="addSpan"> <label for="role" class="addLbl">Role</label>
								<select id="role_user_upd" name="role_user" required>
									<option value="1">Admin</option>
									<option value="2">Regular</option>
							</select>
							</span> <span class="addSpan"> <label for="role" class="addLbl">Project</label>
								<select id="user_project_upd" name="user_project" required>
							</select>
							</span> <span class="addSpan"> <label for="empID" class="addLbl">Employee
									ID</label> <input type="text" name="empID" id="empID_user_upd" required />
							</span> <span class="addSpan"> <label for="firstname"
								class="addLbl">First Name</label> <input type="text"
								name="firstname" id="firstname_user_upd" required />
							</span> <span class="addSpan"> <label for="lastname"
								class="addLbl">Last Name</label> <input type="text"
								name="lastname" id="lastname_user_upd" required />
							</span>


							<!-- Allow form submission with keyboard without duplicating the dialog button -->
							<input type="reset" /> <input type="button" id="updateUser_btn"
								value="Submit" name="updateUser_btn" /><br />
							<!-- <input type="button" id="deleteUser_btn"
								value="Delete" name="deleteUser_btn" /> -->
						</div>
					</fieldset>
				</form>
			</div>
		</div>
		<div id="tabs-3" class="tab_div">
			<div class="two_column_left two_column">
				<h2>Add Payroll Period</h2>
				<form id="payperiod_form">
					<fieldset>
						<span class="addSpan"> <label for="payPeriod"
							class="addLbl">Payroll Period </label> <input type="text"
							name="payPeriod" id="payperiod_add" required>
						</span> <span class="addSpan"> <label class="addLbl">Status</label>
							<select id="pp_status" name="pp_status" required>
								<option></option>
								<option value="Open">Open</option>
								<option value="Closed">Closed</option>
						</select>
						</span>
						<!-- Allow form submission with keyboard without duplicating the dialog button -->
						<input type="button" id="add_pperiod_btn" name="add_pperiod_btn"
							value="Add" />
					</fieldset>
				</form>

			</div>
			<div class="two_column_right two_column">
				<h2>Close Payroll Period</h2>

				<table class="display" id="openPayrollGrid"></table>
				<input type="button" id="close_pperiod_btn" value="Close" />
			</div>
		</div>
		<div id="tabs-4" class="tab_div">
			<div class="load_pay">
				<label class="detailsLbl">Payroll Period</label> <select
					id="pp_select_admin" size="1">
					<option></option>
				</select> &nbsp; <input type="button" id="load_payroll_admin" value="Load" />
			</div>
			<div id="inqTableSpace">
				<table class="display" id="report_grid"></table>
				<input type="button" id="generate_btn" value="Generate Excel Report" />
			</div>
		</div>

		<div id="tabs-5" class="tab_div">
			<div class="load_pay">
				<label class="detailsLbl">Payroll Period</label> <select
					id="pa_select_admin" size="1">
					<option></option>
				</select> &nbsp; <input type="button" id="load_pa_admin" value="Load" />
			</div>
			<div id="paTableSpace">
				<table class="display" id="pa_grid"></table>
				<input type="button" id="selectAll_btn" value="Select All" /> <input
					type="button" id="unSelectAll_btn" value="Unselect All" /> <input
					type="button" id="reject_btn" value="Reject" /> <input
					type="button" id="approve_btn" value="Approve" />

			</div>
		</div>
		<div id="tabs-6" class="tab_div">
			<div class="two_column_left two_column">
				<h2>Add New Project</h2>
				<form id="project_form">
					<fieldset>
						<span class="addSpan"> <label for="project_name"
							class="addLbl">Project Name</label> <input type="text"
							name="projectName" id="project_name" required="required">
						</span> <span class="addSpan"> <label class="detailsLbl">Approver</label>
							<select id="project_admin">
								<option></option>
						</select></span> <span class="addSpan"> <label for="process" class="addLbl">Process</label>
							<input type="text" name="process" id="process"
							readonly="readonly" value="Technology">
						</span>

						<!-- Allow form submission with keyboard without duplicating the dialog button -->
						<input type="button" id="add_project_btn" name="add_project_btn"
							value="Add" />
					</fieldset>
				</form>

			</div>
			<div class="two_column_right two_column">
				<h2>Update Project</h2>
				<form id="project_form_upd">
					<fieldset>
						<span class="addSpan"> <label class="addLbl">Project</label>
							<select id="project_upd" name="user_project" required>
						</select>
						</span> 
						 <span class="addSpan"> <label for="username"
							class="addLbl">Approver</label> <input type="text"
							name="curr_approver" id="curr_approver" readonly="readonly" />
						</span>
						<span class="addSpan"> <label class="addLbl">New Approver</label>
							<select id="proj_approver" name="proj_approver" required>
								<option></option>
						</select></span>
						
					
					</fieldset>
				</form>
				<input type="button" id="update_project" name="update_project"
					value="Update" />
			</div>
		</div>
	</c:if>
</div>