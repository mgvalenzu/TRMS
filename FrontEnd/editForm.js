var employee = JSON.parse(localStorage.getItem('currEmployee'));
var editReimbursementID = JSON.parse(localStorage.getItem('editReimbursementID'))
// console.log(editReimbursementID);

// Funtion to send http request to server to get all events in DB
async function getAllEvents(){
    const url = `http://localhost:7000/events`;
    const httpResponse = await fetch(url);
    const body = await httpResponse.json();
    localStorage.setItem('events', JSON.stringify(body)); // store events

    return body
}

async function populatePreviousForm () {
    // Grab employee id element to then populate with employee info
    const idInput = document.getElementById('idInput');
    idInput.value = employee['id'];

    //populate Event related fields. First need to grab all events and place them in the event dropdown
    // getAllEvents();
    const events = await getAllEvents();

    //Place in event dropdown - create option for each event
    const eventDrop = document.getElementById('eventSelect');
    for(let event of events) {
        //Create option with appropriote event 
        const option = document.createElement('option');
        option.value = event['eventName'];
        option.innerHTML = event['eventName'];
        option.className = 'eventOptions';

        //Add option to dropdown 
        eventDrop.appendChild(option);
    }
    // Preset event here
    // eventSelect();

    // Fill in rest of the form
    const editReimbursement = await getReimbursementByID(editReimbursementID);
    // console.log(editReimbursement);
    
    let eventSelect = document.getElementById('eventSelect');
    let justification = document.getElementById('justificationInput');
    let eventID = document.getElementById('eventID');
    let eventType = document.getElementById('eventType');
    let coverage = document.getElementById('eventCoverage');
    let eventDate = document.getElementById('eventDate');
    let eventCost = document.getElementById('eventCost');
    let eventLocation = document.getElementById('eventLocation');
    let eventDescription = document.getElementById('descriptionInput');

    eventSelect.value = editReimbursement['eventID']['eventName'];
    justification.value = editReimbursement['justification'];
    eventID.value = editReimbursement['eventID']['id'];
    eventType.value = editReimbursement['eventID']['eventType'];
    coverage.value = editReimbursement['eventID']['coverage'];
    eventDate.value = editReimbursement['eventID']['eventDate'] //come back to date need to fix else where
    eventCost.value = editReimbursement['eventID']['eventCost'];
    eventLocation.value = editReimbursement['eventID']['eventLocation'];
    eventDescription.value = editReimbursement['eventID']['description'];
}


// upon clicking one of the events, populate the rest of the form to contain all the event information.
function eventSelect() {
    // Get all events from storage
    const events = JSON.parse(localStorage.getItem('events'));

    //find option selected
    const options = document.getElementsByClassName('eventOptions');
    let option = null;
    for (let o of options) {
        if (o.selected) {
            option = o;
            break;
        }
    }

    //Match selected option to our events so we can get all the information from that event to populate form
    let event = null;
    for(let e of events) {
        if(e['eventName'] == option.value) {
            event = e;
            break;
        }
    }
    
    //Now populate the rest of the pertinant event fields with event info
    const eventID = document.getElementById('eventID');
    const eventType = document.getElementById(`${event['eventType']}`);
    const eventCoverage = document.getElementById(`${event['coverage']}`);
    const eventDate = document.getElementById('eventDate');
    const eventCost = document.getElementById('eventCost');
    const eventLocation = document.getElementById('eventLocation');
    const description = document.getElementById('descriptionInput');

    eventID.value = event['id'];
    eventType.selected = true;
    eventCoverage.selected = true;
    // We will come back to the date issue
    eventDate.value = event['eventDate']; // This line should work but we need to figure out our date formating from the DB and back end
    eventCost.value = event['eventCost'];
    eventLocation.value = event['eventLocation'];
    description.value = event['description'];
}

//Submit Form - makes http post request to server and adds new reimbursement to DB
async function submitForm() {
    // Grab all the information from the reimbursement form
    const eventID = document.getElementById('eventID'); // only need the ID to get event for this project will assume no one will alter the other fields 
    const event = await getEvent(eventID.value);    // get event object
    const employeeID = document.getElementById('idInput');
    const eventSelect = document.getElementById('eventSelect');
    const justification = document.getElementById('justificationInput');

    // console.log(event)
    //Make http request to server to add reimbursement to DB
    const editR = await getReimbursementByID(editReimbursementID);
    // console.log(editR['approvalStatus'])
    let status = editR['approvalStatus'];
    const input = {
        "justification" : justification.value,
        "approvalStatus" : `${status}`,
        "finalGrade" : "In-Progress",
        "employeeID" : employee,
        "eventID" : event
    };
    // console.log(input['approvalStatus']);

    const added = await updateReimbursement(input);
    // We could try to add the added reimbursement to the html rn and maybe itll be loaded when we go to home page.
    // console.log(added);
    location.assign('./home.html');
}

async function getEvent(id){
    // console.log(id);
    const url = `http://localhost:7000/events/${id}`;
    const httpResponse = await fetch(url);
    const body = await httpResponse.json();

    return body;
}

//Change this to updateReimbursement - name is changed but code is not yet get to this
async function updateReimbursement(data) {
    const url = `http://localhost:7000/reimbursements/${editReimbursementID}`;
    const httpResponse = await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(data)
    });
    const body = await httpResponse.json();
    // console.log(body);

    return body;
}

async function getReimbursementByID(id) {
    const url = `http://localhost:7000/reimbursements/${id}`;
    const httpResponse = await fetch(url);
    const body = await httpResponse.json();

    return body;
}