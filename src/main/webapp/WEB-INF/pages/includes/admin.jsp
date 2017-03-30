<div id="adminDiv">
	<div id="addUser">
		<h1>Add User</h1>
		<form id="addUser_form">
			<fieldset>
				<span class="addSpan"> <label for="username" class="addLbl">Username</label>
					<input type="text" name="username" id="uname_user" value="" />
				</span> <span class="addSpan"> <label for="empID" class="addLbl">Employee
						ID</label> <input type="text" name="empID" id="empID_user" value="" />
				</span> <span class="addSpan"> <label for="firstname" class="addLbl">First
						Name</label> <input type="text" name="firstname" id="firstname_user"
					value="" />
				</span> <span class="addSpan"> <label for="lastname" class="addLbl">Last
						Name</label> <input type="text" name="lastname" id="lastname_user"
					value="" />
				</span> <span class="addSpan"> <label for="role" class="addLbl">Role</label>
					<select id="role_user">
						<option value="1">Admin</option>
						<option value="2">Regular</option>
					</select>
				</span>
				
				<span class="addSpan"> <label for="project" class="addLbl">Last
						Name</label> <input type="text" name="project" id="project_user"
					value="" />
				</span>
				
				<span class="addSpan"> <label for="manager" class="addLbl">Last
						Name</label> <input type="text" name="manager" id="manager_user"
					value="" />
				</span>


				<!-- Allow form submission with keyboard without duplicating the dialog button -->
				<input type="button" id="addUser_btn" value="Submit" />
			</fieldset>
		</form>
	</div>

</div>