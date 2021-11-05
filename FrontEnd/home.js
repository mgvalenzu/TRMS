//Retrieve the employee object from storage - global scope as this page should only relate to the logged in employee
var employee = JSON.parse(localStorage.getItem('currEmployee'));
var currID = employee["id"]; // currently logged in empoloyees id
//Labelemployee & department
const name = employee['username'];
const welcome = document.getElementById('welcome');
welcome.innerHTML += name;

const departmentName = employee['departmentID']['name'];
const departmentHeader = document.getElementById('department');
departmentHeader.innerHTML += JSON.stringify(departmentName);



// Get all reimbursements for a particular employee given the passed id
async function getReimbursements(id){
    // const id = employee['id'];
    const url = `http://localhost:7000/reimbursements/employees/${id}`;
    const httpResponse = await fetch(url);
    const body = await httpResponse.json();

    localStorage.setItem('reimbursements', JSON.stringify(body));

    return JSON.stringify(body);
}

async function getAllBencoReimbursements() {
    const url = `http://localhost:7000/reimbursements`;
    const httpResponse = await fetch(url);
    const body = await httpResponse.json();

    let bencoReimbursements = []
    for(let r of body){
        if (r['approvalStatus'] === "Pending Benefits Coordinator Approval") {
            bencoReimbursements.push(r);
        }
    }

    return bencoReimbursements;
}


// get all reimbursements where this employee is the supervisor, returns subordinates. 
//  Only gets requests that are still active, does not include completed requests
async function getAllReimbursementsWhereSupervisor(){ 
    const supervisorID = employee['id'];
    // const urlEmployees = `http://localhost:7000/employees/${supervisorID}`
    const url = `http://localhost:7000/employees/employees/${supervisorID}`; // this just gets us all the employees
    const httpResponse = await fetch(url);
    const body = await httpResponse.json();

    //Now need to get all of the reimbursements for each of these subordinates
    let allReimbursements = [];
    for(let sub in body){
        let id = body[sub]["id"]; //ID of subordinate in this iteration
        let currReimbursements = JSON.parse(await getReimbursements(`${id}`)) // Get all the reimbursements for this subordinate
        
        //validate the reimbursement requests are still active
        for(let r of currReimbursements) {
            if(r['approvalStatus'] !== "Denied" && r['approvalStatus'] !== "Approved"){
                allReimbursements.push(r);
            }
        }
    }

    return allReimbursements;
}

async function updateReimbursement(reimbursement){
    let id = reimbursement['id'];

    const url = `http://localhost:7000/reimbursements/${id}`;
    const httpResponse = await fetch(url, {
        method: "PUT",
        body: JSON.stringify(reimbursement)
    });
    const body = await httpResponse.json();
    console.log(body);
    return body;

}


//Returns only those reimbursements for a given department - then filter out only the ones with status pending dept head approval
async function getAllReimbursementsForDepartment(){
    const url = `http://localhost:7000/reimbursements`;
    const httpResponse = await fetch(url);
    const body = await httpResponse.json();
    console.log(employee['departmentID']['id'])
    let validDeptReimbursements = [];

    for(let reimbursement of body){
        let department = reimbursement['employeeID']['departmentID'];
        let departmentID = department['id'];
        if(departmentID === employee['departmentID']['id']){ // If reimbursement is within the same department
            if(reimbursement['approvalStatus'] === "Pending Department Head Approval"){ // if this reimbursement is pending depthead approval
                validDeptReimbursements.push(reimbursement);
            }
        }
    }

    console.log(validDeptReimbursements);
    return validDeptReimbursements;
}



// Populate the home page based on Role - everyone will get the default active and past reimbursements
//populate generic supervisors table
async function populateSupervisor(subordinateReimbursements) {
    console.log(subordinateReimbursements);

    let info = ['id', 'approvalStatus', 'submissionDate'];
    for (let r of subordinateReimbursements) { // Iterate through all subordinate reimbursements
        let tBody;
        if(employee['isDepartmentHead']){
            tBody = document.getElementById('deptBody');
        } else if(employee['isBenco']){
            tBody = document.getElementById('bencoBody');
        }else if(r['approvalStatus'] === 'Pending Supervisor Approval'){
            tBody = document.getElementById('supervisorBody');
        } else if (r['approvalStatus'] == 'Pending Benefits Coordinator Approval') {
            tBody = document.getElementById('bencoBody');
        } else{
            tBody = document.getElementById('deptBody');
        }

        let tRow = document.createElement('tr');
        tRow.className = "bodyRow";

       
        //Insert info into this particular row
        for(let i of info) {
            let td = document.createElement('td');
            td.innerHTML = JSON.stringify(r[i]);
            tRow.appendChild(td);
        }

        // Add button(s) for Role functionality
        let buttonTD = document.createElement('td');

        let viewButton = document.createElement('button');
        let reviseButton = document.createElement("button");
        let acceptButton = document.createElement("button");
        let denyButton = document.createElement("button");
        viewButton.class = 'supervisorButton';
        reviseButton.class="supervisorButton";
        acceptButton.class="supervisorButton";
        denyButton.class="supervisorButton";

        viewButton.innerHTML = 'View';
        reviseButton.innerHTML = 'Request Revision';
        acceptButton.innerHTML = 'Accept';
        denyButton.innerHTML = 'Deny';

        viewButton.onclick = function() {
            console.log("view the request");
            console.log(r);
            let viewID = r['id'];
            localStorage.setItem('viewReimbursementID', JSON.stringify(viewID));
            location.assign('./viewForm.html')
        }


        reviseButton.onclick = function() {
            //'send' back to employee to revise the request.
            console.log('Reivse this')
            r['approvalStatus'] = 'Pending Revision';
            updateReimbursement(r);
            location.assign('./home.html')

        }

        denyButton.onclick = function(){
            console.log(r['approvalStatus']);
            r['approvalStatus'] = 'Denied';
            updateReimbursement(r);
            location.assign('./home.html')

        }

        acceptButton.onclick = function() { // needs work here
            // Accept and update for this step of the pipeline
            if(employee['isBenco']) {
                r['approvalStatus'] = 'Approved';
            } else if (employee['isDepartmentHead']){
                console.log('move along to benco')
                r['approvalStatus'] = 'Pending Benefits Coordinator Approval';
            }else {
                console.log('supervisor approval')
                r['approvalStatus'] = 'Pending Department Head Approval';
            }
            updateReimbursement(r);
            location.assign('./home.html')
        }
        
        buttonTD.appendChild(viewButton);
        buttonTD.appendChild(reviseButton);
        buttonTD.appendChild(acceptButton);
        buttonTD.appendChild(denyButton);

        tRow.appendChild(buttonTD);
        tBody.appendChild(tRow);
    }
    
}

// function populateBenco() {
//     console.log('will populate the benco')
// }


// function editForm(requestID) {
//     console.log(requestID);
// }




// populate the active and past reimbursements upon entering the home page
async function populateHome() {
    const subordinateReimbursements = await getAllReimbursementsWhereSupervisor();
    const body = JSON.parse(await getReimbursements(currID));

    // Populate the home page based on title position
    let bencoDiv = document.getElementById('benco');
    let pastDiv = document.getElementById('past');
    let activeDiv = document.getElementById('active');
    let supervisorDiv = document.getElementById('supervisor');
    let deptHeadDiv = document.getElementById('deptHead');

    if (employee['isBenco']) { // if this user is a benco
        const bencoReimbursements = await getAllBencoReimbursements();
        pastDiv.style = 'display: none;'
        activeDiv.style = 'display: none;'
        bencoDiv.style = 'display: contents;'
        populateSupervisor(bencoReimbursements);
    } else if(JSON.stringify(subordinateReimbursements) != "[]"){ // if this user DOES have subordinates
        // const subordinateReimbursements = await getAllReimbursementsWhereSupervisor();
        bencoDiv.style = 'display: none;';
        pastDiv.style = 'display: contents;';
        activeDiv.style = 'display: contents;';
        supervisorDiv.style = 'display: contents;';
        populateSupervisor(subordinateReimbursements);
    } else if(employee['isDepartmentHead']) {
        const departmentReimbursements = await getAllReimbursementsForDepartment()
        pastDiv.style = 'display: contents;';
        activeDiv.style = 'display: contents;';
        deptHeadDiv.style = 'display: contents;';
        populateSupervisor(departmentReimbursements);
        
    }

    
    // First need to get all the reimbursement from DB
    // const body = JSON.parse(localStorage.getItem('reimbursements'));

    // Now that we have the reimbursements we can populate the 'active' and 'past' reimbursements for this employee
    for(let r of body) { // iterate through our array of reimbursements

        //validate whether this will go into the Active or Past reimbursement tables
        let bodyType;
        if(r['approvalStatus'] === 'Approved' || r['approvalStatus'] === 'Denied') {
            bodyType = "pastBody"; 
        } else {
            bodyType = "activeBody";

        }
        let status = JSON.stringify(r['approvalStatus']);
        let info = ['id', 'approvalStatus', 'submissionDate'];
        if(bodyType == 'pastBody'){
            info = ['id', 'approvalStatus', 'submissionDate', 'finalGrade'];
        }else {
            info = info;
        }
        

        let activeTable = document.getElementById(bodyType); // get table body element
        let tr = document.createElement('tr'); // create the new row
        tr.className = 'bodyRow';

        // Input the info from reimbursment object
        for(let i of info) { 
            let td = document.createElement('td');
            td.innerHTML = JSON.stringify(r[i]);
            tr.appendChild(td);
        }

        //Other info we needd
        const eventName = r['eventID']['eventName'];
        let tdEvent = document.createElement('td');
        tdEvent.innerHTML = JSON.stringify(eventName);
        tr.appendChild(tdEvent);

        activeTable.appendChild(tr); // append row to table

        //add buttons to options - edit for lower level empl view and approve/deny for supervisor
        //if (employee is supervisor to anyone or isBenco){
        // Then give them permission to deal with their request }
        if(bodyType == 'activeBody'){ // only give active requests the edit button
            let tdButton = document.createElement('td');
            let Button = document.createElement('button');
            Button.innerHTML = 'Edit';
            let requestID = r['id'];
            Button.onclick = function() {
                // console.log(requestID);
                localStorage.setItem('editReimbursementID', JSON.stringify(requestID));
                location.assign('./editForm.html')
            }
            tdButton.appendChild(Button);
            tr.appendChild(tdButton);
        }

    }
} 


function newForm() {
    location.assign('./form.html');
}

function logout() {
    location.assign('./login.html');
}
